/*******************************************************************************
 * Copyright (c) 2006, 2010 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.palette.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jst.jsf.common.internal.JSFUtil;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory;
import org.eclipse.jst.jsf.core.internal.CompositeTagRegistryFactory.TagRegistryIdentifier;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.ITagRegistryListener;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.TagRegistryChangeEvent;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry.TagRegistryChangeEvent.EventType;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.DesignerPaletteCustomizationsHelper;
import org.eclipse.jst.pagedesigner.editors.palette.IEntryChangeListener;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteConstants;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteContext;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemManager;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocumentFactory;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 *  Manages tag library palette by palette context.   Capable of handling JSP and XHTML content types.
 *  
 *  Callers must use getInstance(IPaletteContext), and when done, call release(IFile).   
 *  
 * @author mengbo and others
 */
public class PaletteItemManager implements IPaletteItemManager,
		IPaletteConstants, ITagRegistryListener {
	
	private static final boolean DEBUG = false;

	
	private static Map<TagRegistryIdentifier, PaletteItemManager> _managers = new HashMap<TagRegistryIdentifier, PaletteItemManager>();
	private static ReentrantLock MANAGER_LOCK = new ReentrantLock();
	private static long MANAGER_LOCK_TIMEOUT = 120;
	
	private Set<IFile> _files = new HashSet<IFile>();
	private TagRegistryIdentifier _tagRegId;
	private List<PaletteDrawer> _paletteCategories = new ArrayList<PaletteDrawer>();
	private CopyOnWriteArrayList<IEntryChangeListener> _listeners = new CopyOnWriteArrayList<IEntryChangeListener>();
	private AtomicBoolean IS_DISPOSED = new AtomicBoolean();

	private PaletteHelper _paletteHelper;

	private ITagRegistry _tagRegistry;


	/**
	 * Return singleton paletteItemManager for a given project.  Will only work for JSPs.
	 * @param project
	 * @return PaletteItemManager
	 * @deprecated - use getInstance(paletteContext)
	 */
	public static PaletteItemManager getInstance(final IProject project) {
		if (project == null) {
			// sometimes when the editor is editing a file in jar file, may not
			// be able to
			// get the project.
			return getInstance(createPaletteContext(null));
		}		
		//relies on JSP file extension for content type
		return getInstance(createPaletteContext(project.getFile("dummy.jsp")));  //$NON-NLS-1$
	}
	
	/**
	 * @param paletteContext
	 * @return PaletteItemManager instance shared with all files with same palette context in a project
	 * 				May return null if locking issue 
	 */
	public static PaletteItemManager getInstance(final IPaletteContext paletteContext) {	
		boolean hasLock = false;
		try {
			if (MANAGER_LOCK.tryLock(MANAGER_LOCK_TIMEOUT, TimeUnit.SECONDS)){
				hasLock = true;
				final TagRegistryIdentifier regId = getTagRegistryIdentifier(paletteContext);
				if (regId == null) {
					PDPlugin.log(new Status(IStatus.ERROR, PDPlugin.getPluginId(), "Unable to display palette for "+paletteContext.getFile().getName()+".  Unknown content type for file."));  //$NON-NLS-1$//$NON-NLS-2$
					return null;
				}
				PaletteItemManager manager = _managers.get(regId);
				if (manager == null) {
					 manager = new PaletteItemManager(regId);
					_managers.put(regId, manager);
					manager.init();
				} 
				manager.addFile(paletteContext.getFile());
				return manager;
				
			}
			//if we get here then the lock has timed out
			PDPlugin.log(new Status(Status.ERROR, PDPlugin.getPluginId(), "(getInstance()) Failed to get managers lock for" + paletteContext.getFile().toString())); //$NON-NLS-1$
			
		} catch (InterruptedException e) {
			PDPlugin.log("Failed in PaletteItemManager.getInstance(PaletteContext", e); //$NON-NLS-1$
		} finally {
			if (hasLock)
				MANAGER_LOCK.unlock();
		}
		return null;
	}
	
	private static TagRegistryIdentifier getTagRegistryIdentifier(
			final IPaletteContext paletteContext) {

		final IFile file = paletteContext.getFile();
		if (file != null) {
	        final IContentTypeManager typeManager = Platform.getContentTypeManager();
	        final IContentType contentType = 
	            typeManager.findContentTypeFor(file.getName());
	        
	        if (contentType != null)
	        {
	            return new TagRegistryIdentifier(file.getProject(), contentType);
	        }
	        return null;
		}
		//to support legacy null projects.   Allows HTML and JSF tag libs to be displayed.
	    return new TagRegistryIdentifier(null, org.eclipse.jst.pagedesigner.utils.JSFUtil.JSF_CONTENTTYPE);

	}

	/**
	 * @param file
	 * @return IPaletteContext
	 */
	public static IPaletteContext createPaletteContext(final IFile file) {
		return new IPaletteContext() {
			public IFile getFile() {
				return file;
			}

			public Object getAdapter(Class adapter) {				
				return null;
			}
		};
	}
	private void addFile(final IFile file) {
		synchronized (_files) {
			_files.add(file);
		}
		
	}
	
	/**
	 * Indicates that the file no longer needs the paletteItemManager, freeing the manager to be released after last reference
	 * @param paletteContext
	 */
	public void release(final IPaletteContext paletteContext) { 
		final IFile file = paletteContext.getFile();
		boolean isEmpty = false;
		synchronized (_files) {
			if (_files.contains(file)) {
				_files.remove(file);
				if (_files.isEmpty())
					isEmpty = true;
			}
		}

		if (isEmpty && IS_DISPOSED.compareAndSet(false, true)) {
			removeTagRegistryListeners(this);
			boolean hasLock = false;
			try {
				if (MANAGER_LOCK.tryLock(MANAGER_LOCK_TIMEOUT, TimeUnit.SECONDS)) {
					hasLock = true;
					_managers.remove(_tagRegId);
				}
				else {
					PDPlugin.log(new Status(Status.ERROR, PDPlugin.getPluginId(), "(Release) Failed to get managers lock for" + paletteContext.getFile().toString())); //$NON-NLS-1$
				}
			} catch (InterruptedException e) {
				PDPlugin.log("Failed to release paletteItemManager for" + paletteContext.getFile().toString(), e); //$NON-NLS-1$
			} finally {
				if (hasLock)
					MANAGER_LOCK.unlock();
			}
		}
	}

	private static void removeTagRegistryListeners(final PaletteItemManager manager) {
		if (manager.getTagRegistry() != null)
			manager.getTagRegistry().removeListener(manager);
	}

	private ITagRegistry getTagRegistry() {		
		return _tagRegistry;
	}

	/**
	 * For JUnit testing purposes only
	 */
	public static void clearPaletteItemManager() {
		
		boolean hasLock = false;
		try {
			if (MANAGER_LOCK.tryLock(MANAGER_LOCK_TIMEOUT, TimeUnit.SECONDS)){
				hasLock = true;
				if (_managers == null)
					return;

				for (final PaletteItemManager manager : _managers.values()) {
					PaletteItemManager.removeTagRegistryListeners(manager);		
					manager._files.clear();
				}							
				_managers.clear();
			} else {
				//if we get here then the lock has timed out
				PDPlugin.log(new Status(Status.ERROR, PDPlugin.getPluginId(), "(clear) Failed to get managers lock")); //$NON-NLS-1$
			}
			
		} catch (InterruptedException e) {
			PDPlugin.log("Failed in clearPaletteItemManager", e); //$NON-NLS-1$
		} finally {
			if (hasLock)
				MANAGER_LOCK.unlock();
		}

	}
	
	private PaletteItemManager(final TagRegistryIdentifier regId) {
		_paletteHelper = new PaletteHelper(this);
		if (regId != null) {
			_tagRegId = regId;
//			init();
		}		
	}
	

	public List getAllCategories() {	
		synchronized (_paletteCategories) {
			final List<PaletteDrawer> readOnlyCategories = new ArrayList<PaletteDrawer>(_paletteCategories);			
			return Collections.unmodifiableList(readOnlyCategories);
		}		
	}

	/**
	 * Initializes the palette items for the current project
	 */
	protected synchronized void init() {
		synchronized (_paletteCategories) {
			_paletteCategories.clear();
		}
		
		initTagRegistry();

		DesignerPaletteCustomizationsHelper.loadUserCustomizations(this);
		
		sortCategories();
	}

	/**
	 * Sort palette categories
	 */
	protected void sortCategories() {
		//note that once we store ordering customizations, we will need to do something different
		synchronized(_paletteCategories) {
			Collections.sort(_paletteCategories, new Comparator(){
	
				public int compare(Object o1, Object o2) {
					String label1 = ((PaletteEntry)o1).getLabel();
					String label2 = ((PaletteEntry)o2).getLabel();
					
					return label1.compareTo(label2);
				}
				
			});
		}
	}

	/**
	 * Reinitializes the palatteItemManager and informs all palette roots that use the manager to refresh
	 */
	public void reset() {
		init();
		fireModelChanged(null, null);
	}
	
	private void initTagRegistry() {
		registerHTMLCategory();
		if (isJSP(_tagRegId))
			registerJSPCategory();			
		
		registerTagsFromTagRegistry();	
	}

	private boolean isJSP(final TagRegistryIdentifier tagRegistryId) {
		final IContentType ct = tagRegistryId.getContentType();
		if (JSFUtil.isJSFContentType(ct.getId()))
			return true;
		return false;
	}

	private void registerTagsFromTagRegistry() {
		_tagRegistry = getTagRegistry(_tagRegId);
		if (_tagRegistry != null) {
			for (final Namespace ns : _tagRegistry.getAllTagLibraries()) {							
				_paletteHelper.configPaletteItemsByNamespace(this, ns);			
			}
		}
	}

	private ITagRegistry getTagRegistry(final TagRegistryIdentifier regId) {
		ITagRegistry reg = null;
		if (regId.getProject() != null) {
			reg = CompositeTagRegistryFactory.getInstance().getRegistry(regId);
			if (reg != null) {
				reg.addListener(this);
			}
		}
		return reg;
	}

	private void registerHTMLCategory() {
		final CMDocument doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.HTML_DOC_TYPE);
		_paletteHelper.getOrCreateTaglibPaletteDrawer(this, doc, CMDocType.HTML_DOC_TYPE);
	}

	private void registerJSPCategory() {
		final CMDocument doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.JSP11_DOC_TYPE);
		_paletteHelper.getOrCreateTaglibPaletteDrawer(this, doc, CMDocType.JSP11_DOC_TYPE);
	}

//	/**
//	 * Search Classpath entry list to find if the entry is jar library and the
//	 * library have the tld descriptor, if have ,build a palette category mapping
//	 * the tld descriptor.
//	 * 
//	 * @param project
//	 */
//	private void registerTldFromClasspath(final IProject project) {
//		if (project != null) {
//			ITaglibRecord[] tldrecs = TaglibIndex.getAvailableTaglibRecords(project.getFullPath());
//			for (int i=0;i<tldrecs.length;i++){				
//				_paletteHelper.configPaletteItemsByTLD(this, getCurProject(), tldrecs[i]);			
//			}
//		}			
//	}

	/**
	 * @param id (most likely the uri)
	 * @param label 
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer findOrCreateCategory(final String id, final String label) {
		TaglibPaletteDrawer category = getTaglibPalletteDrawer(id);
		if (category == null)
			category = createTaglibPaletteDrawer(id, label);
		return category;
	}

	/**
	 * @param uri
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer findCategoryByURI(final String uri) {
		TaglibPaletteDrawer category;
		for (final Iterator iter = getAllCategories().iterator(); iter.hasNext();) {
			category = (TaglibPaletteDrawer) iter.next();
			if (uri.equals(category.getURI())) {
				return category;
			}
		}
		return null;
	}

	public TaglibPaletteDrawer createTaglibPaletteDrawer(final String uri, final String label) {
		final TaglibPaletteDrawer r = new TaglibPaletteDrawer(uri, label);
		synchronized(_paletteCategories) {
			_paletteCategories.add(r);
		}
		return r;
	}

	public TaglibPaletteDrawer getTaglibPalletteDrawer(final String uri) {
		for (final Iterator iter = getAllCategories().iterator(); iter.hasNext();) {
			final TaglibPaletteDrawer cat = (TaglibPaletteDrawer) iter.next();
			if (uri.equalsIgnoreCase(cat.getId())) {
				return cat;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.pagedesigner.editors.palette.IPaletteItemManager#addEntryChangeListener(com.sybase.stf.jmt.pagedesigner.editors.palette.IEntryChangeListener)
	 */
	public void addEntryChangeListener(final IEntryChangeListener listener) {
		_listeners.addIfAbsent(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.pagedesigner.editors.palette.IPaletteItemManager#removeEntryChangeListener(com.sybase.stf.jmt.pagedesigner.editors.palette.IEntryChangeListener)
	 */
	public void removeEntryChangeListener(final IEntryChangeListener listener) {		
		_listeners.remove(listener);
	}

	/**
	 * Notify model change event
	 * 
	 * @param oldDefinitions
	 * @param newDefinitions
	 */
	private void fireModelChanged(final List oldDefinitions, final List newDefinitions) {
		if (_listeners == null) {
			return;
		}
		for (final Iterator<IEntryChangeListener> it= _listeners.iterator();it.hasNext();){
			final IEntryChangeListener listener = it.next();
			listener.modelChanged(oldDefinitions, newDefinitions);
		}	
	}
	
	/**
	 * Informs all paletteItemManagers, except the notifying paletteManager, of updates to the customizations
	 * All palette viewer roots will be notifed of possible updates
	 * @param notifyingManager 
	 */
	public static void notifyPaletteItemManagersOfCustomizationsUpdate(final IPaletteItemManager notifyingManager){
		boolean hasLock = false;
		try {
			if (MANAGER_LOCK.tryLock(MANAGER_LOCK_TIMEOUT, TimeUnit.SECONDS)){
				hasLock = true;
				for (Iterator it=_managers.values().iterator();it.hasNext();){
					final PaletteItemManager mgr = (PaletteItemManager)it.next();
					if (mgr != null && notifyingManager != mgr)
						mgr.reset();
				}
			} 
			else {
				//if we get here then the lock has timed out
				PDPlugin.log(new Status(Status.ERROR, PDPlugin.getPluginId(), "Failed to get managers lock in notifyPaletteItemManagersOfCustomizationsUpdate")); //$NON-NLS-1$
			}
			
		} catch (InterruptedException e) {
			PDPlugin.log("Failed in notifyPaletteItemManagersOfCustomizationsUpdate", e); //$NON-NLS-1$
		} finally {
			if (hasLock)
				MANAGER_LOCK.unlock();
		}

	}

	public void registryChanged(final TagRegistryChangeEvent event) {
		final EventType eventType = event.getType();
		switch (eventType) {
			case ADDED_NAMESPACE:
				addNamespaces(event.getAffectedObjects());
				break;
			case REMOVED_NAMESPACE:
				removeNamespaces(event.getAffectedObjects());
				break;
			case CHANGED_NAMESPACE:
				changeNamespaces(event.getAffectedObjects());
				break;
			case REGISTRY_DISPOSED:
				break;
	
			default:
				break;
		}
		
		DesignerPaletteCustomizationsHelper.loadUserCustomizations(this);
		sortCategories();
		
		fireModelChanged(null, null);
	}


	private void addNamespaces(final List<? extends Namespace> affectedObjects) {
		synchronized (_paletteCategories) {
			for (final Namespace ns : affectedObjects) {
				if (DEBUG)
					System.out.println("Add NS: "+ns.getNSUri()+"["+System.currentTimeMillis()+"]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				_paletteHelper.configPaletteItemsByNamespace(this, ns);
			}
		}
	}
	
	private void removeNamespaces(final List<? extends Namespace> affectedObjects) {
		final List<Integer> drawersToRemove = new ArrayList<Integer>();
		synchronized (_paletteCategories) {
			for (final Namespace ns : affectedObjects) {
				for (int i=_paletteCategories.size() - 1; i >= 0; i--) {//gather in reverse order
					final PaletteDrawer drawer = _paletteCategories.get(i);
					if (drawer.getId().equals(ns.getNSUri())) {
						if (DEBUG)
							System.out.println("Remove NS: "+drawer.getId() +"["+System.currentTimeMillis()+"]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						drawersToRemove.add(new Integer(i));	
					}
				}
			}	
			if (! drawersToRemove.isEmpty()) {
				Collections.sort(drawersToRemove, new Comparator<Integer>() {//reverse order sort

					public int compare(Integer o1, Integer o2) {
						if (o1.intValue() > o2.intValue())
							return -1;
						else if (o1.intValue() < o2.intValue())
							return 1;
							
						return 0;
					}
				});
				for (Integer index : drawersToRemove) {
					_paletteCategories.remove(index.intValue());				
				}
			}
		}
	}

	private void changeNamespaces(final List<? extends Namespace> affectedObjects) {
		//for now, remove then add
		removeNamespaces(affectedObjects);
		addNamespaces(affectedObjects);		
	}

	public TagRegistryIdentifier getTagRegistryIdentifier() {		
		return _tagRegId;
	}

	/**
	 * @return helper
	 */
	public PaletteHelper getPaletteHelper() {
		return _paletteHelper;
	}

}
