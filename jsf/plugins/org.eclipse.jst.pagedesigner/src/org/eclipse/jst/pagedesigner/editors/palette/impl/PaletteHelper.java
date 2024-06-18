/*******************************************************************************
 * Copyright (c) 2006, 2024 Sybase, Inc. and others.
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

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IProject;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IImageDescriptorProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.editors.palette.IPaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolCreationAdapter;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;

/**
 * Helper class.
 */
public class PaletteHelper {

	// pattern to strip all <x> and </x> HTML tags
	final private static Pattern removeHTMLTags = Pattern
			.compile("<[/?\\w\\s=\"\\.\\#]+>"); //$NON-NLS-1$

	// pattern to find all runs of spaces longer than one
	final private static Pattern trimInteriorWhitespace = Pattern
			.compile("[ ]+"); //$NON-NLS-1$

	// pattern to find all new lines for removal
	final private static Pattern removeNewLines = Pattern.compile("[\n]"); //$NON-NLS-1$

	private final static ImageDescriptor DEFAULT_SMALL_ICON = JSFUICommonPlugin
			.getDefault().getImageDescriptor(
					JSFSharedImages.DEFAULT_PALETTE_TAG_IMG);

	private final static ImageDescriptor DEFAULT_LARGE_ICON = PDPlugin
			.getDefault().getImageDescriptor(
					"palette/GENERIC/large/PD_Palette_Default.gif"); //$NON-NLS-1$

	// how many characters to truncate a palette item's description to.
	// TODO: add preference?
	// the soft length is the ideal length we try to truncate to. We first
	// try to find a period (end of sentence; TODO: should have a character
	// class)
	// inside the first SOFT_LENGTH chars at which to truncate a description
	// string.
	// if we can't find one then we search for the first one between SOFT_LENGTH
	// and min(HARD_LENGTH, str.length()). If found, we truncate there. If not,
	// we truncate to HARD_LENGTH-" ...".length() and append the ellipsis.
	// In all cases the truncated description string returned should <=
	// HARD_LENGTH.
	// private final static int DESCRIPTION_TRUNCATE_SOFT_LENGTH = 150;
	private final static int DESCRIPTION_TRUNCATE_HARD_LENGTH = 250;

//	private PaletteItemManager _paletteManager;

	/*package*/ PaletteHelper(final PaletteItemManager manager) {
//		_paletteManager = manager;
	}
	/**
	 * Creates a TaglibPaletteDrawer with TagTool palette entries for each tag
	 * from the CMDocument
	 * 
	 * @param manager
	 * @param tldRec
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer configPaletteItemsByTLD(
			final IPaletteItemManager manager, final ITaglibRecord tldRec) {

		final String tldURI = CMUtil.getURIFromTaglibRecord(tldRec, manager.getTagRegistryIdentifier().getProject());

		final CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
		final TLDDocument doc = (TLDDocument) factory.createCMDocument(tldRec);

		return getOrCreateTaglibPaletteDrawer(manager, doc, tldURI);
	}

	/**
	 * @param manager
	 * @param doc
	 * @param tldURI
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer getOrCreateTaglibPaletteDrawer(
			final IPaletteItemManager manager, final CMDocument doc, final String tldURI) {
		
		TaglibPaletteDrawer category = findCategory(manager, tldURI);
		if (category != null)
			return category;
		
		final IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(manager.getTagRegistryIdentifier().getProject());
		final ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
		final Model model = query.findTagLibraryModel(tldURI);
//		final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
//				.createMetaDataModelContext(manager.getTagRegistryIdentifier().getProject(), tldURI);
//		final Model model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		category = createTaglibPaletteDrawer(manager, doc, model, query);

		if (category != null) {
			loadTags(category, doc, model, query);
			sortTags(category.getChildren());
		}
		return category;
	}

	private void sortTags(final List tags) {
		// note that once we store ordering customizations, we will need to do
		// something different
		// it will also be complicated if we decide to do 181958 and 181866
		Collections.sort(tags, new Comparator() {

			public int compare(final Object o1, final Object o2) {
				final String label1 = ((PaletteEntry) o1).getLabel();
				final String label2 = ((PaletteEntry) o2).getLabel();

				if (label1 == null) {
					// if both null, then equal
					if (label2 == null) {
						return 0;
					}
					// otherwise, sort label 2 before
					return 1;
				}

				if (label2 == null) {
					// if label1 not null, then sort it first
					return -1;
				}
				return label1.compareTo(label2);
			}

		});

	}

	private void loadTags(final TaglibPaletteDrawer category, final CMDocument doc,
			final Model model, final ITaglibDomainMetaDataQuery query) {

		if (model != null) {// load from metadata - should always drop in here
			final Trait trait = query.findTrait(model,
					"paletteInfos"); //$NON-NLS-1$
			if (trait != null) {
				final PaletteInfos tags = (PaletteInfos) trait.getValue();
				for (final Iterator it = tags.getInfos().iterator(); it.hasNext();) {
					final PaletteInfo tag = (PaletteInfo) it.next();
					createTagEntry(category, tag, doc);
				}
			} else {
				for (final Iterator it = model.getChildEntities().iterator(); it
						.hasNext();) {
					final Entity tagAsEntity = (Entity) it.next();
					createTagEntry(category, tagAsEntity, doc, query);
				}
			}
		} else {// fail safe loading from cmDoc... should no longer go in here
			loadFromCMDocument(category, doc);
		}

	}

	private TaglibPaletteDrawer createTaglibPaletteDrawer(
			final IPaletteItemManager manager, final CMDocument doc, final Model model, ITaglibDomainMetaDataQuery query) {

		TaglibPaletteDrawer category = null;
		if (model != null) {
			// do we create it?
			final boolean isHidden = getBooleanTagTraitValue(query, model, "hidden", false); //$NON-NLS-1$
			if (isHidden) {
				return null;
			}

			String label = getStringTagTraitValue(query, model,
					"display-label", model.getId()); //$NON-NLS-1$
			label = label.equals("") ? model.getId() : label; //$NON-NLS-1$
			category = manager.createTaglibPaletteDrawer(model.getId(), label);

			String desc = getStringTagTraitValue(query, model,
					"description", model.getId()); //$NON-NLS-1$
			category.setDescription(formatDescription(desc));

			final ImageDescriptor largeIconImage = getImageDescriptorFromTagTraitValueAsString(query, 
					model, "small-icon", null); //$NON-NLS-1$
			if (largeIconImage != null)
				category.setLargeIcon(largeIconImage);

			final String prefix = getStringTagTraitValue(query, model,
					"default-prefix", null); //$NON-NLS-1$
			category.setDefaultPrefix(prefix);

			final boolean isVisible = !(getBooleanTagTraitValue(query, model,
					"expert", false)); //$NON-NLS-1$
			category.setVisible(isVisible);

			category.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);

		}
		return category;
	}

	private TaglibPaletteDrawer findCategory(
			final IPaletteItemManager manager, final String tldURI) {
		
		TaglibPaletteDrawer lib = null;
		for (final Iterator it = manager.getAllCategories().iterator(); it.hasNext();) {
			lib = (TaglibPaletteDrawer) it.next();
			if (tldURI.equals(lib.getURI()))
				return lib;
		}
		return null;
	}

	/*
	 * (non-JavaDoc) This method will read information from the CMDocument to
	 * create the tag entries. It will check the existing items in the registry.
	 * If the corresponding tag is not in palette manager, then it will create
	 * one, and mark the newly created item as "expert". Otherwise, it will
	 * check whether the tld contains more information than the palette manager,
	 * and adding those information to it (such as description, icons for tags)
	 * 
	 * @param category
	 * 
	 * @param cmdoc
	 */
	private void loadFromCMDocument(final TaglibPaletteDrawer category,
			final CMDocument cmdoc) {

		final CMNamedNodeMap nodeMap = cmdoc.getElements();
		for (int i = 0, size = nodeMap.getLength(); i < size; i++) {
			final CMElementDeclaration eledecl = (CMElementDeclaration) nodeMap
					.item(i);
			final String tagName = eledecl.getElementName();
			TagToolPaletteEntry item;
			if (tagName.equalsIgnoreCase(IHTMLConstants.TAG_INPUT)) {// TODO:
																		// fix
																		// this
																		// nonsense!
				final StringBuffer name = new StringBuffer(category.getURI());
				name.append(":").append(tagName).append(":").append(tagName); //$NON-NLS-1$ //$NON-NLS-2$
				item = category.getTagPaletteEntryById(name.toString());
			} else {
				item = category.getTagPaletteEntryByTagName(tagName);
			}
			if (item == null) {
				createTagEntry(category, eledecl);

			}
		}
	}

	private void createTagEntry(final TaglibPaletteDrawer category,
			final PaletteInfo info, final CMDocument doc) {

		final Boolean hidden = info.getHidden();
		if ((hidden != null) && (hidden.booleanValue()))// do not create a
														// palette entry
			return;

		final IMetaDataSourceModelProvider sourceProvider = ((Trait) info
				.eContainer().eContainer()).getSourceModelProvider();
		final String tagName = info.getTag();
		final String id = info.getId();
		final String label = info.getDisplayLabel();
		final String desc = formatDescription(info.getDescription());
		final ImageDescriptor smallIcon = getImageDescriptorFromString(
				sourceProvider, info.getSmallIcon(), DEFAULT_SMALL_ICON);
		final ImageDescriptor largeIcon = getImageDescriptorFromString(
				sourceProvider, info.getLargeIcon(), DEFAULT_LARGE_ICON);
		final Boolean expert = info.getExpert();

		internalCreateTagEntry(doc, category, id, tagName, label, desc,
				smallIcon, largeIcon, (expert != null && expert.booleanValue()));

	}

	private void createTagEntry(final TaglibPaletteDrawer category,
			final PaletteInfo info, final Namespace ns) {

		final Boolean hidden = info.getHidden();
		if ((hidden != null) && (hidden.booleanValue()))// do not create a
														// palette entry
			return;

		final IMetaDataSourceModelProvider sourceProvider = ((Trait) info
				.eContainer().eContainer()).getSourceModelProvider();
		final String tagName = info.getTag();
		final String id = info.getId();
		final String label = info.getDisplayLabel();
		final String desc = formatDescription(info.getDescription());
		final ImageDescriptor smallIcon = getImageDescriptorFromString(
				sourceProvider, info.getSmallIcon(), DEFAULT_SMALL_ICON);
		final ImageDescriptor largeIcon = getImageDescriptorFromString(
				sourceProvider, info.getLargeIcon(), DEFAULT_LARGE_ICON);
		final Boolean expert = info.getExpert();

		internalCreateTagEntry(ns, category, id, tagName, label, desc,
				smallIcon, largeIcon, (expert != null && expert.booleanValue()));

	}

	private void createTagEntry(final TaglibPaletteDrawer category,
			final Entity entity, final CMDocument doc, final ITaglibDomainMetaDataQuery query) {

		final boolean hidden = getBooleanTagTraitValue(query, entity, "hidden", false); //$NON-NLS-1$
		if (hidden)// do not create a palette entry
			return;

		final String tagName = entity.getId();
		final String label = getStringTagTraitValue(query, entity, "display-label", tagName); //$NON-NLS-1$
		final String desc = formatDescription(getStringTagTraitValue(query, entity,
				"description", tagName)); //$NON-NLS-1$
		final ImageDescriptor smallIcon = getImageDescriptorFromTagTraitValueAsString(query,
				entity, "small-icon", DEFAULT_SMALL_ICON); //$NON-NLS-1$
		final ImageDescriptor largeIcon = getImageDescriptorFromTagTraitValueAsString(query,
				entity, "large-icon", DEFAULT_LARGE_ICON); //$NON-NLS-1$
		final boolean expert = getBooleanTagTraitValue(query, entity, "expert", false); //$NON-NLS-1$

		internalCreateTagEntry(doc, category, tagName, tagName, label, desc,
				smallIcon, largeIcon, expert);

	}

	private void createTagEntry(final TaglibPaletteDrawer category,
			final Entity entity, final Namespace ns, final ITaglibDomainMetaDataQuery query) {

		final boolean hidden = getBooleanTagTraitValue(query, entity, "hidden", false); //$NON-NLS-1$
		if (hidden)// do not create a palette entry
			return;

		final String tagName = entity.getId();
		final String label = getStringTagTraitValue(query, entity, "display-label", tagName); //$NON-NLS-1$
		final String desc = formatDescription(getStringTagTraitValue(query, entity,
				"description", tagName)); //$NON-NLS-1$
		final ImageDescriptor smallIcon = getImageDescriptorFromTagTraitValueAsString(query,
				entity, "small-icon", DEFAULT_SMALL_ICON); //$NON-NLS-1$
		final ImageDescriptor largeIcon = getImageDescriptorFromTagTraitValueAsString(query,
				entity, "large-icon", DEFAULT_LARGE_ICON); //$NON-NLS-1$
		final boolean expert = getBooleanTagTraitValue(query, entity, "expert", false); //$NON-NLS-1$

		internalCreateTagEntry(ns, category, tagName, tagName, label, desc,
				smallIcon, largeIcon, expert);

	}

	private TagToolPaletteEntry internalCreateTagEntry(final TaglibPaletteDrawer category, 
			final String id, final String tagName,
			final String label, String desc, final ImageDescriptor smallIcon,
			final ImageDescriptor largeIcon, final boolean expert) {
		
		final ITagDropSourceData data = new TagToolCreationAdapter(category
				.getURI(), tagName, category.getDefaultPrefix(), id);
		final TagToolPaletteEntry item = new TagToolPaletteEntry(data, label,
				desc, smallIcon, largeIcon);
		item.setId(id);

		item.setVisible(!expert);
		category.add(item);
		item.setParent(category);

		return item;
	}

	private TagToolPaletteEntry internalCreateTagEntry(final CMDocument doc,
			final TaglibPaletteDrawer category, final String id, final String tagName,
			final String label, final String desc, final ImageDescriptor smallIcon,
			final ImageDescriptor largeIcon, final boolean expert) {
		
		if (verifyPresentInContentModel(doc, tagName)) {
			return internalCreateTagEntry(category, id, tagName, label, desc,
					smallIcon, largeIcon, expert);
		}
		return null;
	}

	private TagToolPaletteEntry internalCreateTagEntry(final Namespace ns,
			final TaglibPaletteDrawer category, final String id, final String tagName,
			final String label, final String desc, final ImageDescriptor smallIcon,
			final ImageDescriptor largeIcon, final boolean expert) {
		
		if (verifyPresentInContentModel(ns, tagName)) {
			return internalCreateTagEntry(category, id, tagName, label, desc,
					smallIcon, largeIcon, expert);
		}
		return null;
	}

	private boolean verifyPresentInContentModel(final CMDocument doc,
			final String tagName) {
		
		return doc.getElements().getNamedItem(tagName) != null;
	}

	private boolean verifyPresentInContentModel(final Namespace ns,
			final String tagName) {
		
		return ns.getViewElement(tagName) != null;
	}

	private boolean getBooleanTagTraitValue(final ITaglibDomainMetaDataQuery query, final Entity entity, final String key,
			final boolean defaultValue) {
		final Trait trait = query.findTrait(entity, key);
		if (trait != null) {
			return TraitValueHelper.getValueAsBoolean(trait);
		}
		return defaultValue;
	}

	private String getStringTagTraitValue(final ITaglibDomainMetaDataQuery query, final Entity entity, final String key,
			final String defaultValue) {
		
		final Trait trait = query.findTrait(entity, key);
		if (trait != null) {
			final String val = TraitValueHelper.getValueAsString(trait);
			if (val != null)
				return val;
		}
		return defaultValue;
	}

	private ImageDescriptor getImageDescriptorFromTagTraitValueAsString(final ITaglibDomainMetaDataQuery query, 
			final Entity entity, final String key, final ImageDescriptor defaultValue) {
		final Trait t = query.findTrait(entity, key);
		if (t != null) {
			final String imgDesc = TraitValueHelper.getValueAsString(t);
			return getImageDescriptorFromString(t.getSourceModelProvider(),
					imgDesc, defaultValue);
		}
		return defaultValue;
	}

	private ImageDescriptor getImageDescriptorFromString(
			final IMetaDataSourceModelProvider sourceModelProvider, final String imgDesc,
			final ImageDescriptor defaultValue) {
		
		ImageDescriptor image = defaultValue;
		if (imgDesc != null) {
			final IImageDescriptorProvider imageProvider = (IImageDescriptorProvider) sourceModelProvider
					.getAdapter(IImageDescriptorProvider.class);
			if (imageProvider != null) {
				image = imageProvider.getImageDescriptor(imgDesc);
			}
		}
		return image;
	}

	private void createTagEntry(final TaglibPaletteDrawer category,
			final CMElementDeclaration eledecl) {

		final String tagName = eledecl.getElementName();
		String label = null;
		String desc = null;

		if (eledecl instanceof TLDElementDeclaration) {
			final TLDElementDeclaration tag = (TLDElementDeclaration) eledecl;
			label = tag.getDisplayName();
			desc = tag.getDescription();
		}

		if (label == null || label.equals("")) //$NON-NLS-1$
			label = tagName;

		if (desc == null)
			desc = ""; //$NON-NLS-1$
		else
			desc = formatDescription(desc);

		final TagToolPaletteEntry item = internalCreateTagEntry(category, tagName,
				tagName, label, desc, getDefaultSmallIcon(),
				getDefaultLargeIcon(), false);
		item.setToolProperty("CMElementDeclaration", eledecl); //$NON-NLS-1$

	}

	/**
	 * @return DEFAULT_LARGE_ICON
	 */
	private ImageDescriptor getDefaultLargeIcon() {
		return DEFAULT_LARGE_ICON;
	}

	/**
	 * @return DEFAULT_SMALL_ICON
	 */
	private ImageDescriptor getDefaultSmallIcon() {
		return DEFAULT_SMALL_ICON;
	}

	private String formatDescription(final String desc) {
		// TODO: modify and use a formatter in the future?
		final String aDesc = filterConvertString(desc);
		if (aDesc != null) {
			if (aDesc.length() > DESCRIPTION_TRUNCATE_HARD_LENGTH) {
				final StringBuffer result = new StringBuffer(aDesc.substring(0,
						DESCRIPTION_TRUNCATE_HARD_LENGTH));
				result.append("..."); //$NON-NLS-1$
				return result.toString();
			}
			return aDesc;

		}
		return ""; //$NON-NLS-1$
	}

	private String filterConvertString(final String text) {
		if (text == null) {
			return ""; //$NON-NLS-1$
		}

		String result = removeHTMLTags.matcher(text).replaceAll(""); //$NON-NLS-1$
		result = removeNewLines.matcher(result).replaceAll(" "); //$NON-NLS-1$
		result = trimInteriorWhitespace.matcher(result).replaceAll(" "); //$NON-NLS-1$

		return result;
	}

	/**
	 * @param manager
	 * @param ns
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer configPaletteItemsByNamespace(
			final PaletteItemManager manager,
			final Namespace ns) {
		
		return getOrCreateTaglibPaletteDrawer(manager, ns);
	}

	/**
	 * @param manager
	 * @param ns 
	 * @return TaglibPaletteDrawer
	 */
	public TaglibPaletteDrawer getOrCreateTaglibPaletteDrawer(
			final IPaletteItemManager manager, final Namespace ns) {
		
		TaglibPaletteDrawer category = findCategory(manager, ns.getNSUri());
		if (category != null)
			return category;

		final IProject project = manager.getTagRegistryIdentifier().getProject();
		final IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		final ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
		final Model model = query.findTagLibraryModel(ns.getNSUri());
//		final ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper
//				.createMetaDataModelContext(project, ns.getNSUri());
//		final Model model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		category = createTaglibPaletteDrawer(manager,  ns, model, query);

		if (category != null) {
			loadTags(category, ns, model, query);
			sortTags(category.getChildren());
		}
		return category;
	}

	private TaglibPaletteDrawer createTaglibPaletteDrawer(
			final IPaletteItemManager manager, final Namespace ns, final Model model, final ITaglibDomainMetaDataQuery query) {

		TaglibPaletteDrawer category = null;
		if (model != null) {
			// do we create it?
			final boolean isHidden = getBooleanTagTraitValue(query, model, "hidden", false); //$NON-NLS-1$
			if (isHidden) {
				return null;
			}

			String label = getStringTagTraitValue(query, model,
					"display-label", model.getId()); //$NON-NLS-1$
			label = label.equals("") ? model.getId() : label; //$NON-NLS-1$
			category = manager.createTaglibPaletteDrawer(model.getId(), label);

			final String desc = getStringTagTraitValue(query, model,
					"description", model.getId()); //$NON-NLS-1$
			category.setDescription(formatDescription(desc));

			final ImageDescriptor largeIconImage = getImageDescriptorFromTagTraitValueAsString(query, 
					model, "small-icon", null); //$NON-NLS-1$
			if (largeIconImage != null)
				category.setLargeIcon(largeIconImage);

			final String prefix = getStringTagTraitValue(query, model,
					"default-prefix", null); //$NON-NLS-1$
			category.setDefaultPrefix(prefix);

			final boolean isVisible = !(getBooleanTagTraitValue(query, model,
					"expert", false)); //$NON-NLS-1$
			category.setVisible(isVisible);

			category.setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);

		}
		return category;
	}

	private void loadTags(final TaglibPaletteDrawer category,
			final Namespace ns, final Model model, final ITaglibDomainMetaDataQuery query) {

		if (model != null) {// load from metadata - should always drop in here
			final Trait trait = query.findTrait(model,
					"paletteInfos"); //$NON-NLS-1$
			if (trait != null) {
				final PaletteInfos tags = (PaletteInfos) trait.getValue();
				for (Iterator it = tags.getInfos().iterator(); it.hasNext();) {
					final PaletteInfo tag = (PaletteInfo) it.next();
					createTagEntry(category, tag, ns);
				}
			} else {
				for (final Iterator it = model.getChildEntities().iterator(); it
						.hasNext();) {
					final Entity tagAsEntity = (Entity) it.next();
					createTagEntry(category, tagAsEntity, ns, query);
				}
			}
		} else {// fail safe loading from cmDoc... should no longer go in here
			loadFromNamespace(category, ns);
		}
	}

	private void loadFromNamespace(final TaglibPaletteDrawer category,
			final Namespace ns) {

		for (Object velem : ns.getViewElements()) {
			final ITagElement tag = (ITagElement) velem;
			final TagToolPaletteEntry item = category.getTagPaletteEntryByTagName(tag
					.getName());
			if (item == null) {
				createTagEntry(category, tag);
			}
		}
	}

	private void createTagEntry(final TaglibPaletteDrawer category,
			final ITagElement tag) {

		final String tagName = tag.getName();
		String label = tagName;
		String desc = ""; //$NON-NLS-1$

		// if (eledecl instanceof TLDElementDeclaration){
		// TLDElementDeclaration tag = (TLDElementDeclaration)eledecl;
		// label = tag.getDisplayName();
		// desc = tag.getDescription();
		// }

		//			if (label == null || label.equals("")) //$NON-NLS-1$
		// label = tagName;
		//			
		// if (desc == null )
		//				desc = ""; //$NON-NLS-1$
		// else
		// desc = formatDescription(desc);

		// TagToolPaletteEntry item =
		internalCreateTagEntry(category, tagName, tagName, label, desc,
				getDefaultSmallIcon(), getDefaultLargeIcon(), false);
		//			item.setToolProperty("CMElementDeclaration", eledecl); //$NON-NLS-1$

	}
}
