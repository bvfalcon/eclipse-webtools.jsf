/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Matthias Fuessel -- extracted from https://bugs.eclipse.org/bugs/show_bug.cgi?id=215461
 *    Cameron Bateman/Oracle - integrated and moved data table code here.
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.symbols;

import java.util.Arrays;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.common.util.TypeUtil;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.IObjectSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.InitializedSymbolFactory;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;
import org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.designtime.resolver.StructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.IValidationReporter;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.ReporterAdapter;
import org.eclipse.jst.jsf.validation.internal.el.ELExpressionValidator;
import org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator;

/**
 * Factory for creating symbols for variables/message bundles that are
 * contributed by components/tags. This Factory can be used by descendants of
 * {@link org.eclipse.jst.jsf.context.symbol.source.AbstractContextSymbolFactory} in order to create the symbols they want
 * to contribute.
 * 
 * It provides also methods for getting the resulting {@link ValueType} from an
 * EL expression and the "row" type signature from an DataModel/Array/List
 * {@link ValueType}.
 * 
 * NOTE: C.B March 26, 2008 -- commented out portions are left for the future
 * because the rely on enhancements to the symbol model that could not be made
 * in the current release.  Other portions of the original ComponentSymbolFactory
 * have been moved to {@link org.eclipse.jst.jsf.context.symbol.InitializedSymbolFactory}
 * This reflects the split between things deemed to be generic EL (put in common
 * plugin) and things deemed to be JSF-specific (put here).
 * 
 */
public final class JSFSymbolFactory extends InitializedSymbolFactory
{
    /**
     * @param elText The EL expression text. Must not be null
     * @param elContext The document context pointing to elText in the source document. Must not be null
     * @param file The workspace resource that contains elText. Must not be null.
     * @param symbolResolverFactory 
    * @return the value expression resolved from elText or null if it cannot
     * be resolved or elText doesn't resolve to value expression (i.e. is a method expression)
      */
    public ValueType getValueTypeFromEL(final String elText,
            final IStructuredDocumentContext elContext, final IFile file, IStructuredDocumentSymbolResolverFactory symbolResolverFactory)
    {
        assert elText != null;
        assert elContext != null;
        assert file != null;

        // TODO investigate if infinite recursion; use ASTResolver instead??
        final IValidationReporter  reporter = new ReporterAdapter();
        final ELExpressionValidator validator = 
            new ELExpressionValidator(
                elContext, elText, symbolResolverFactory, reporter);
        validator.validateXMLNode();
        final IExpressionSemanticValidator semValidator = validator
                .getSemanticValidator();
        if (semValidator != null
                && semValidator.getExpressionType() instanceof ValueType)
        {
            return (ValueType) semValidator.getExpressionType();
        }
        return null;
    }

    /**
     * Convenience for {@link JSFSymbolFactory#getValueTypeFromEL(String, IStructuredDocumentContext, IFile, IStructuredDocumentSymbolResolverFactory)}
     * using StructuredDocumentSymbolResolverFactory.getInstance().
     * 
     * @param elText
     * @param elContext
     * @param file
     * @return a ValueType or null
     */
    public ValueType getValueTypeFromEL(final String elText,
            final IStructuredDocumentContext elContext, final IFile file)
    {
        return getValueTypeFromEL(elText, elContext, file, StructuredDocumentSymbolResolverFactory.getInstance());
    }

    /**
     * @param type
     * @return <code>true</code>, if the given {@link ValueType} represents
     *         an instance of <code>javax.faces.model.DataModel</code> or
     *         <code>jakarta.faces.model.DataModel</code>
     */
    private boolean isFacesDataModel(ValueType type)
    {
        return type.isInstanceOf(TypeConstants.TYPE_DATA_MODEL) || type.isInstanceOf(TypeConstants.TYPE_DATA_MODEL_JAKARTA);
    }

    /**
     * Tries to guess the row type of a <code>javax.faces.DataModel</code>
     * instance. This will only work if <code>type</code> is a descendant that
     * narrows down the return type of "getRowData()".
     * 
     * @param type
     * @return the row type of the given DataModel. Will return
     *         <code>null</code> if <code>type</code> is no DataModel or if
     *         nothing more specific than <code>Object</code> can be
     *         determined
     */
    public String getRowSignatureFromDataModel(ValueType type)
    {
        if (type instanceof IObjectSymbolBasedValueType)
        {
            ISymbol resSymbol = ((IObjectSymbolBasedValueType) type)
                    .getSymbol().call(
                            "getRowData", ECollections.emptyEList(), "res"); //$NON-NLS-1$ //$NON-NLS-2$
            if (resSymbol != null && resSymbol instanceof IObjectSymbol)
            {
                // TODO full signature
                final String signature = ((IObjectSymbol) resSymbol)
                        .getTypeDescriptor().getTypeSignature();
                if (!TypeConstants.TYPE_JAVAOBJECT.equals(signature))
                {
                    return signature;
                }
            }
        }
        return null;
    }

    /**
     * @param type
     * @return <code>true</code>, if <code>type</code> is a collection or
     *         array
     */
    public boolean isContainerType(ValueType type)
    {
        return type.isArray()
                || type.isInstanceOf(TypeConstants.TYPE_COLLECTION);
    }

    /**
     * @param type -
     *            the type of the <code>value</code> property
     * @return the type signature of the row variable for every type that
     *         <code>UIData</code> takes as <code>value</code> property.
     *         Will return <code>null</code> if no type or nothing more
     *         specific than <code>Object</code> can be determined
     */
    public String getRowSignatureFromValueType(ValueType type)
    {
        if (isContainerType(type))
        {
            return getElementSignatureFromContainerType(type);
        }
        if (isFacesDataModel(type))
        {
            return getRowSignatureFromDataModel(type);
        }
        // Otherwise, according to jsf spec, treat value as single row:
        // TODO full signature
        return type.getSignature();
    }

    /**
     * @param symbolName The name of the symbol to be created. Must not be null
     * @param signature The type signature of the array type. Must not be null
     * @param source the runtime source
     * @param javaProject must not be null
     * @return a symbol based approximating an implicit DataModel wrapper for an array
     */
    public final ISymbol createArraySymbol(final String symbolName, final String signature, final ERuntimeSource source, final IJavaProject javaProject)
    {
        assert symbolName != null;
        assert signature != null;
        assert javaProject != null;
        
        final String arrayElementType = Signature.getElementType(signature);
        final int arrayCount = Signature.getArrayCount(signature);
        String  adjustedSignature = null;
        
        // if it is a single array nesting, then it will just be the element type,
        // but if it is a multi-dim array, then the scalar element will be an array
        // with one less nesting level.  It's a strange corner case to have an implicit
        // array of something as a row type, but it is a valid case.  I suppose
        // it may be happen if you want to have tables of tables in which the nested tables
        // in turn use nested variables as their type...
        if (arrayCount > 0)
        {
            adjustedSignature = Signature.createArraySignature(arrayElementType, arrayCount-1);
        }
        else
        {
            adjustedSignature = arrayElementType;
        }
        
        return createScalarSymbol(symbolName, adjustedSignature, source, javaProject);
    }

    /**
     * @param symbolName The name of the symbol to create. Must not be null.
     * @param signature The fully resolved type signature of the scalar.  Must not be null.
     * @param source 
     * @param javaProject The JavaProject whose classpath is to be used to resolve type information for signture. Must not be null.
     * @return a symbol approximating a scalar object DataModel wrapper.  The row variable for the
     * data model becomes of type signature
     */
    public final ISymbol createScalarSymbol(final String symbolName, final String signature, final ERuntimeSource source, final IJavaProject javaProject)
    {
        assert symbolName != null;
        assert signature != null;
        assert javaProject != null;

        final String elementType = Signature.getElementType(signature);
        
        IJavaTypeDescriptor2 desc = 
            SymbolFactory.eINSTANCE.createIJavaTypeDescriptor2();
        final int arrayCount = Signature.getArrayCount(signature);
        if (arrayCount > 0)
        {
            desc.setArrayCount(arrayCount);
        }
        
        IType type = TypeUtil.resolveType(javaProject, elementType);
        if (type != null)
        {
            desc.setType(type);
        }
        else
        {
            desc.setTypeSignatureDelegate(Signature.getTypeErasure(signature));
        }
        desc.getTypeParameterSignatures().addAll(Arrays.asList(Signature.getTypeArguments(signature)));

        IComponentSymbol symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName(symbolName);
        symbol.setTypeDescriptor(desc);
        symbol.setRuntimeSource(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
        return symbol;
    }

    /**
     * @param symbolName The name of the symbol to create. Must not be null.
     * @param valueType The value expression representing the implicit list.  The signature
     * on the valueType must be a list. Must not be null.
     * @param source 
     * @param description 
     * @param javaProject The JavaProject whose classpath will be used to resolve types.  Must not be null.
     * 
     * @return a symbol that approximates as best as possible an implicit DataModel for java.util.List value expressions.  If the List has
     * resolvable Java 5 type arguments, then a scalar symbol will be created
     * using this type information.  If it is a raw type, then 
     * createDefaultSymbol() is called
     */
    public final ISymbol createFromList(String symbolName, ValueType valueType, ERuntimeSource source, String description, IJavaProject javaProject) 
    {
        assert symbolName != null;
        assert valueType != null;
        assert javaProject != null;
        assert TypeConstants.TYPE_LIST.equals(valueType.getSignature());
        
        final String[] typeArguments = valueType.getTypeArguments();
        
        if (typeArguments != null && typeArguments.length > 0)
        {
            // a list has a single type argument
            final String typeArg = typeArguments[0];

            if (Signature.getTypeSignatureKind(typeArg) == Signature.CLASS_TYPE_SIGNATURE)
            {
                return createScalarSymbol(symbolName, typeArg, source, javaProject);
            }
        }
        
        // if no resolvable type signatures, do the default thing
        return createDefaultSymbol(symbolName, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, description);
    }

    /**
     * @param symbolName The name of the symbol to create.  Must not be null
     * @param source 
     * @param description 
     * @return a default symbol that eliminates bogus warnings for this dataTable's
     * row variable in cases where something better is resolvable.  Note that this is
     * not ideal, since will result in any property being accepted on the variable with
     * this name.
     */
    public final ISymbol createDefaultSymbol(final String symbolName, final ERuntimeSource source, final String description)
    {
        assert symbolName != null;
        
        final IMapTypeDescriptor typeDesc = 
            SymbolFactory.eINSTANCE.createIBoundedMapTypeDescriptor();
        // empty map source
        typeDesc.setMapSource(new HashMap());
        final IComponentSymbol symbol = 
            SymbolFactory.eINSTANCE.createIComponentSymbol();
        symbol.setName(symbolName);
        symbol.setTypeDescriptor(typeDesc);
        symbol.setDetailedDescription(description);
        symbol.setRuntimeSource(source);
        return symbol;
    }

    /**
     * FUTURE use: added to support future API feature.  Should not be used.
     * @param symbolName
     * @param bundleName
     * @param project
     * @return an instance symbol for the message bundle
     */
    public IInstanceSymbol createMessageBundleSymbol(final String symbolName, final String bundleName, IJavaProject project)
    {
        // FUTURE USE
        throw new UnsupportedOperationException("see https://bugs.eclipse.org/bugs/show_bug.cgi?id=215461"); //$NON-NLS-1$
    }

    /**
     * FUTURE use: added to support future API feature.  Should not be used.
     * @param symbolName
     * @param fullyQualifiedName 
     * @param source 
     * @param description 
     * @param javaProject 
     * @return an instance symbol for the message bundle
     */
    public IBeanInstanceSymbol createManagedBeanSymbol(final String symbolName, final String fullyQualifiedName,
            ERuntimeSource source, final String description, final IJavaProject javaProject)
    {
        // FUTURE USE
        throw new UnsupportedOperationException("see https://bugs.eclipse.org/bugs/show_bug.cgi?id=215461"); //$NON-NLS-1$
    }

//    /  /**
//  * @param symbolName
//  * @param signature
//  * @param javaProject
//  * @return a symbol for a bean
//  */
// public IComponentBeanSymbol createBeanSymbolFromSignature(
//         final String symbolName, final String signature,
//         final IJavaProject javaProject)
// {
//     IJavaTypeDescriptor2 desc = createTypeDescriptorFromSignature(
//             signature, javaProject);
//
//     IComponentBeanSymbol symbol = SymbolFactory.eINSTANCE
//             .createIComponentBeanSymbol();
//     symbol.setName(symbolName);
//     symbol.setTypeDescriptor(desc);
//     symbol.setRuntimeSource(ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL);
//     return symbol;
// }

// /**
//  * @param symbolName
//  * @param bundleName
//  * @param project
//  * @return a symbol for a message bundle
//  */
// public IMessageBundleSymbol createMessageBundleSymbolFromBundleName(final String symbolName, final String bundleName, IJavaProject project) {
//     final IMessageBundleTypeDescriptor typeDesc = SymbolFactory.eINSTANCE.createIMessageBundleTypeDescriptor();
//     try {
//         Map mapSource = ResourceBundleMapSourceFactory.getResourceBundleMapSource(project.getProject(), bundleName);
//         typeDesc.setMapSource(mapSource);
//     } catch (JavaModelException e) {
//         JSFCorePlugin.log(e, "Error creating resource map for bundle '"bundleName  "'"); //$NON-NLS-1$ //$NON-NLS-2$
//     } catch (IOException e) {
//         JSFCorePlugin.log(e, "Error creating resource map for bundle '"bundleName  "'"); //$NON-NLS-1$ //$NON-NLS-2$
//     } catch (CoreException e) {
//         JSFCorePlugin.log(e, "Error creating resource map for bundle '"bundleName  "'"); //$NON-NLS-1$ //$NON-NLS-2$
//     }
//     final IMessageBundleSymbol symbol = SymbolFactory.eINSTANCE.createIMessageBundleSymbol();
//     symbol.setName(symbolName);
//     symbol.setTypeDescriptor(typeDesc);
//     symbol.setDetailedDescription(NLS.bind(Messages.getString("ComponentSymbolFactory.IMessageBundleSymbol.detailedDescription"), bundleName));  //$NON-NLS-1$
//     return symbol;
// }
//    /**
//     * Sets the symbol to be defined only inside the given element
//     * 
//     * @param symbol
//     * @param slement
//     */
//    public void setDefinitionRange(final IComponentVar symbol,
//            final Element slement)
//    {
//        if (slement instanceof IndexedRegion)
//        {
//            final IndexedRegion defRegion = (IndexedRegion) slement;
//            final int defOffset = defRegion.getStartOffset();
//            final int defLength = defRegion.getLength();
//            symbol.setDefinitionRange(defOffset, defLength);
//        }
//    }

}