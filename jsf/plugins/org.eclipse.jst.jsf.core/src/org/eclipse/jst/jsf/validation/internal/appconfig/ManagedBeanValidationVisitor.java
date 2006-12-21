package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Managed bean validator
 * 
 * @author cbateman
 *
 */
public class ManagedBeanValidationVisitor extends EObjectValidationVisitor 
{
    /**
     * @param version
     */
    public ManagedBeanValidationVisitor(final String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_ManagedBean()
                ,version);
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        ManagedBeanType managedBean = (ManagedBeanType) object;
        
        // TODO: validate managedBeanName is a valid Java id
//        final String managedBeanName = 
//            managedBean.getManagedBeanName().getTextContent();
        validateScope(managedBean.getManagedBeanScope(), messages, file);
        validateClass(managedBean.getManagedBeanClass(), messages, file);
        validateEntryTypes(managedBean, messages, file);
    }
    
    private void validateScope(ManagedBeanScopeType scope, List messages, IFile file)
    {
        if (scope != null && scope.getTextContent() != null)
        {
            addMessageInfo(messages
                    , AppConfigValidationUtil.validateManagedBeanScope(scope)
                    , scope, file);
        }
    }
    
    private void validateClass(ManagedBeanClassType classType, List messages, IFile file)
    {
        if (classType != null)
        {
            String classTypeValue = classType.getTextContent();
            addMessageInfo(messages
                    , AppConfigValidationUtil.validateClassName
                        (classTypeValue == null ? "" : classTypeValue, 
                                null, file.getProject()), classType, file);
        }
    }
    
    private void validateEntryTypes(ManagedBeanType managedBeanType, List messages, IFile file)
    {
        // TODO: do a bean look-up for targetName to verify that it a) matches the type
        // and b) exists on the bean
        if (managedBeanType.getManagedBeanName()!= null
                && managedBeanType.getManagedBeanClass() != null)
        {
            final String propertyName =
                managedBeanType.getManagedBeanName().getTextContent();
            final String propertyClass =
                managedBeanType.getManagedBeanClass().getTextContent();
            
            if (propertyName == null || propertyClass == null
                    || "".equals(propertyName.trim())
                    || "".equals(propertyClass.trim()))
            {
                return;
            }
            
            IMessage message = null;
            EObject eObj = null;
            if (managedBeanType.getMapEntries() != null)
            {
                eObj = managedBeanType.getMapEntries();
                message =
                    AppConfigValidationUtil
                        .validateMapEntries
                            (propertyName
                            , propertyClass
                            , (MapEntriesType) eObj
                            , file.getProject());
            }
            else if (managedBeanType.getListEntries() != null)
            {
                eObj = managedBeanType.getListEntries();
                message =
                    AppConfigValidationUtil
                        .validateListEntries(
                            propertyName
                            , propertyClass
                            , (ListEntriesType) eObj
                            , file.getProject());
            }
            addMessageInfo(messages, message, eObj, file);
        }
    }
    
    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
            new ManagedPropertyValidationVisitor(getVersion())
        };
    }
}
