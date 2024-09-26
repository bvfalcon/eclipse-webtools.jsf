/*******************************************************************************
 * Copyright (c) 2011, 2012 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Andrew McCulloch - initial API and implementation
 *    Ian Trimble/Oracle - maintenance
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;

/**
 * Provides a configuration model specified as JSF 2.x annotations.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Andrew McCulloch - Oracle 
 */
public class AnnotationJSFAppConfigProvider extends AbstractJSFAppConfigProvider {

    static final String MANAGED_BEAN_ANNOTATION_CLASS = "jakarta.faces.bean.ManagedBean"; //$NON-NLS-1$
    static final String REFERENCED_BEAN_ANNOTATION_CLASS = "jakarta.faces.bean.ReferencedBean"; //$NON-NLS-1$
    static final String FACES_COMPONENT_ANNOTATION_CLASS = "jakarta.faces.component.FacesComponent"; //$NON-NLS-1$
    static final String FACES_CONVERTER_ANNOTATION_CLASS = "jakarta.faces.convert.FacesConverter"; //$NON-NLS-1$
    static final String FACES_RENDERER_ANNOTATION_CLASS = "jakarta.faces.render.FacesRenderer"; //$NON-NLS-1$
    static final String FACES_VALIDATOR_ANNOTATION_CLASS = "jakarta.faces.validator.FacesValidator"; //$NON-NLS-1$
    
    static final String NONE_SCOPED_ANNOTATION_CLASS = "jakarta.faces.bean.NoneScoped"; //$NON-NLS-1$
    static final String VIEW_SCOPED_ANNOTATION_CLASS = "jakarta.faces.bean.ViewScoped"; //$NON-NLS-1$
    static final String SESSION_SCOPED_ANNOTATION_CLASS = "jakarta.faces.bean.SessionScoped"; //$NON-NLS-1$
    static final String APPLICATION_SCOPED_ANNOTATION_CLASS = "jakarta.faces.bean.ApplicationScoped"; //$NON-NLS-1$
    static final String CUSTOM_SCOPED_ANNOTATION_CLASS = "jakarta.faces.bean.CustomScoped";  //$NON-NLS-1$

    static final String CDI_NAMED_BEAN_ANNOTATION_CLASS = "jakarta.inject.Named"; //$NON-NLS-1$
    static final String CDI_MODEL_BEAN_ANNOTATION_CLASS = "jakarta.enterprise.inject.Model"; //$NON-NLS-1$

    static final String CDI_REQUEST_SCOPED_ANNOTATION_CLASS = "jakarta.enterprise.context.RequestScoped"; //$NON-NLS-1$
    static final String CDI_CONVERSATION_SCOPED_ANNOTATION_CLASS = "jakarta.enterprise.context.ConversationScoped"; //$NON-NLS-1$
    static final String CDI_SESSION_SCOPED_ANNOTATION_CLASS = "jakarta.enterprise.context.SessionScoped"; //$NON-NLS-1$
    static final String CDI_APPLICATION_SCOPED_ANNOTATION_CLASS = "jakarta.enterprise.context.ApplicationScoped"; //$NON-NLS-1$

    /**
     * Cached {@link FacesConfigType} instance.
     */
    private FacesConfigType facesConfig = null;

    @Override
    public synchronized FacesConfigType getFacesConfigModel() {
        if (facesConfig == null) {
            try {
                discoverFacesConfig();
            } catch (CoreException ce) {
                JSFCorePlugin.log(IStatus.ERROR, ce.getLocalizedMessage(), ce);
                facesConfig = null;
            }

            if (facesConfig != null) {
                jsfAppConfigLocater.getJSFAppConfigManager().addFacesConfigChangeAdapter(facesConfig);
            }
        }
        return facesConfig;
    }

    @Override
    public void releaseFacesConfigModel() {
        jsfAppConfigLocater.getJSFAppConfigManager().removeFacesConfigChangeAdapter(facesConfig);
        facesConfig = null;
    }

    private void discoverFacesConfig() throws CoreException {
        facesConfig = FacesConfigFactory.eINSTANCE.createFacesConfigType();
        IJavaProject jProject = JavaCore.create(this.jsfAppConfigLocater.getJSFAppConfigManager().getProject());
        IPackageFragmentRoot[] roots = jProject.getAllPackageFragmentRoots();
        List<IPackageFragmentRoot> scannableRoots = new ArrayList<IPackageFragmentRoot>();
        if (roots != null) {
            for (IPackageFragmentRoot root : roots) {
                AnnotationPackageFragmentRoot facesRoot = new AnnotationPackageFragmentRoot(root);
                if (facesRoot.canContainAnnotatedComponents()) {
                    scannableRoots.add(root);
                }
            }
        }

        if (!scannableRoots.isEmpty()) {
            findAnnotatedComponents(jProject, scannableRoots);
        }
    }

    private final void findAnnotatedComponents(final IJavaProject jProject, final List<IPackageFragmentRoot> scannableRoots)
            throws CoreException {
        SearchPattern pattern = orPattern(null, jProject.findType(MANAGED_BEAN_ANNOTATION_CLASS));
        pattern = orPattern(pattern, jProject.findType(REFERENCED_BEAN_ANNOTATION_CLASS));
        pattern = orPattern(pattern, jProject.findType(FACES_COMPONENT_ANNOTATION_CLASS));
        pattern = orPattern(pattern, jProject.findType(FACES_CONVERTER_ANNOTATION_CLASS));
        pattern = orPattern(pattern, jProject.findType(FACES_RENDERER_ANNOTATION_CLASS));
        pattern = orPattern(pattern, jProject.findType(FACES_VALIDATOR_ANNOTATION_CLASS));
        pattern = orPattern(pattern, jProject.findType(CDI_NAMED_BEAN_ANNOTATION_CLASS));
        pattern = orPattern(pattern, jProject.findType(CDI_MODEL_BEAN_ANNOTATION_CLASS));

        if (pattern != null) {
            SearchEngine engine = new SearchEngine();
            SearchParticipant[] participants = new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() };
            IJavaSearchScope scope = SearchEngine.createJavaSearchScope(scannableRoots.toArray(new IJavaElement[scannableRoots
                    .size()]), IJavaSearchScope.SOURCES | IJavaSearchScope.APPLICATION_LIBRARIES);

            SearchRequestor requestor = new AnnotationSearchRequestor(facesConfig);
            engine.search(pattern, participants, scope, requestor, new NullProgressMonitor());
        }
    }

    private SearchPattern orPattern(SearchPattern pattern, IJavaElement element) {
        if (element == null) {
            return pattern;
        }
        if (pattern == null) {
            return SearchPattern.createPattern(element, IJavaSearchConstants.REFERENCES);
        }
        return SearchPattern.createOrPattern(pattern, SearchPattern.createPattern(element, IJavaSearchConstants.REFERENCES));
    }
}