/*******************************************************************************
 * Copyright (c) 2007, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.designtime.symbols;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.ERuntimeSource;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.validation.internal.appconfig.AppConfigValidationUtil;
import org.w3c.dom.Element;

/**
 * A framework provided variable factory for EL model objects that are
 * constructed based on DataModel's dervied from an EL expression.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractDataModelVariableFactory
{
    private final JSFSymbolFactory _symbolFactory = new JSFSymbolFactory();

    /**
     * @param elText
     *            The EL expression text. Must not be null
     * @param elContext
     *            The document context pointing to elText in the source
     *            document. Must not be null
     * @param file
     *            The workspace resource that contains elText. Must not be null.
     * @return the value expression resolved from elText or null if it cannot be
     *         resolved or elText doesn't resolve to value expression (i.e. is a
     *         method expression)
     * @deprecated use JSFSymbolFactory.getValueTypeFromEL instead.
     */
    public final ValueType createValueExpression(final String elText,
            final IStructuredDocumentContext elContext, final IFile file)
    {
        return getSymbolFactory().getValueTypeFromEL(elText, elContext, file);
    }

    /**
     * @return the symbol factory used by this variable factory
     */
    public final JSFSymbolFactory getSymbolFactory()
    {
        return _symbolFactory;
    }

    /**
     * @param symbolName
     *            The name of the symbol to be created. Must not be null
     * @param signature
     *            The type signature of the array type. Must not be null
     * @param javaProject
     *            must not be null
     * @return a symbol based approximating an implicit DataModel wrapper for an
     *         array
     * @deprecated use JSFSymbolFactory.createArraySymbol instead
     */
    protected final ISymbol createArraySymbol(final String symbolName,
            final String signature, final IJavaProject javaProject)
    {
        return getSymbolFactory().createArraySymbol(symbolName, signature,
                ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, javaProject);
    }

    /**
     * Implements default rules for taking an non-array value expression and
     * resolving it to a ISymbol modelling a JSF DataModel
     * 
     * @param symbolName
     * @param valueType
     * @param javaProject
     * @return a symbol where valueType is considered to be the base type upon
     *         which a data model would be created. This combines the
     *         possibility that the value expression is either an explicit
     *         DataModel implementation or a non-DataModel type for which JSF be
     *         default provides an implicit wrapper model.
     * 
     * List is treated as a special case here, since their are two subcases: 1)
     * the list is a raw type, in which case it must be treated as implicit and
     * opaque (as to the type of the variable created) 2) the list has Java 5
     * type argument information that can be used to infer the type of the row
     * variable
     */
    public ISymbol createFromType(String symbolName, ValueType valueType,
            IJavaProject javaProject)
    {
        return internalCreateFromBaseType(symbolName, valueType, javaProject);
    }

    private ISymbol internalCreateFromBaseType(String symbolName,
            ValueType valueType, IJavaProject javaProject)
    {
        // based on JSF 1.1 spec section 4.2.1.4 the data model
        // value binding can be one of a number of object that will
        // get an implicit DataModel wrapper at runtime

        // could be an array
        if (Signature.getArrayCount(valueType.getSignature()) > 0)
        {
            return getSymbolFactory()
                    .createArraySymbol(symbolName, valueType.getSignature(),
                            ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL,
                            javaProject);
        }

        // if is a list, then we have extra work to do if it
        // is generic and has info about its contents
        if (valueType.isInstanceOf(TypeConstants.TYPE_LIST))
        {
            return getSymbolFactory().createFromList(symbolName, valueType, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, null, javaProject);
        }
        // if is JSTL ResultSet, java ResultSet or DataModel
        // return the default symbol -- in the absence of definite
        // template info, these row containers are opaque to us
        else if (valueType
                .isInstanceOf(TypeConstants.TYPE_JAVAX_SERVLET_JSP_JSTL_SQL_RESULT)
                || valueType.isInstanceOf(TypeConstants.TYPE_JAKARTA_SERVLET_JSP_JSTL_SQL_RESULT)
                || valueType.isInstanceOf(TypeConstants.TYPE_RESULT_SET)
                || valueType.isInstanceOf(TypeConstants.TYPE_DATA_MODEL)
                || valueType.isInstanceOf(TypeConstants.TYPE_DATA_MODEL_JAKARTA))
        {
            return getSymbolFactory().createDefaultSymbol(symbolName, ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, Messages
                    .getString("AbstractDataModelVariableFactory.DataModel.Symbol.RowVariable.DetailedDescription")); //$NON-NLS-1$
        }

        // in other cases, we assume that the value is an explicit single row
        // scalar object
        return getSymbolFactory().createScalarSymbol(symbolName, valueType.getSignature(),
                ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, javaProject);
    }

    /**
     * @param symbolName
     *            The name of the symbol to create. Must not be null.
     * @param valueType
     *            The value expression representing the implicit list. The
     *            signature on the valueType must be a list. Must not be null.
     * @param javaProject
     *            The JavaProject whose classpath will be used to resolve types.
     *            Must not be null.
     * 
     * @return a symbol that approximates as best as possible an implicit
     *         DataModel for java.util.List value expressions. If the List has
     *         resolvable Java 5 type arguments, then a scalar symbol will be
     *         created using this type information. If it is a raw type, then
     *         createDefaultSymbol() is called
     * @deprecated use JSFSymbolFactory.createFromList
     */
    protected final ISymbol createFromList(String symbolName,
            ValueType valueType, IJavaProject javaProject)
    {
        return getSymbolFactory().createFromList(symbolName, valueType,
                ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, null,
                javaProject);
    }

    /**
     * @param symbolName
     *            The name of the symbol to create. Must not be null.
     * @param signature
     *            The fully resolved type signature of the scalar. Must not be
     *            null.
     * @param javaProject
     *            The JavaProject whose classpath is to be used to resolve type
     *            information for signture. Must not be null.
     * @return a symbol approximating a scalar object DataModel wrapper. The row
     *         variable for the data model becomes of type signature
     * @deprecated use JSFSymbolFactory.createScalarSymbol instead.
     */
    protected final ISymbol createScalarSymbol(final String symbolName,
            final String signature, final IJavaProject javaProject)
    {
        return getSymbolFactory().createScalarSymbol(symbolName, signature,
                ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL, javaProject);
    }

    /**
     * @param symbolName
     *            The name of the symbol to create. Must not be null
     * @return a default symbol that eliminates bogus warnings for this
     *         dataTable's row variable in cases where something better is
     *         resolvable. Note that this is not ideal, since will result in any
     *         property being accepted on the variable with this name.
     * @deprecated use JSFSymbolFactory.createDefaultSymbol instead.
     */
    public final ISymbol createDefaultSymbol(final String symbolName)
    {
        return getSymbolFactory()
                .createDefaultSymbol(
                        symbolName,
                        ERuntimeSource.TAG_INSTANTIATED_SYMBOL_LITERAL,
                        Messages
                                .getString("AbstractDataModelVariableFactory.DataModel.Symbol.RowVariable.DetailedDescription")); //$NON-NLS-1$
    }

    /**
     * @param dataTableElement
     *            the DOM element that has a "value" attribute. Must not be
     *            null.
     * @return the el text from the 'value attribute of a dataTable element or
     *         null if not found
     */
    protected static String getELText(final Element dataTableElement)
    {
        assert dataTableElement != null;
        String attrVal = dataTableElement
                .getAttribute(IJSFConstants.ATTR_VALUE);

        if (attrVal != null)
        {
            return AppConfigValidationUtil.extractELExpression(attrVal)
                    .getElText();
        }
        return null;
    }

    /**
     * @return the variable source name. Protects against null in the abstract
     *         method
     */
    protected final String internalGetVariableSourceName()
    {
        String variableSourceName = getVariableSourceName();

        if (variableSourceName == null)
        {
            JSFCorePlugin.log("Missing variableSourceName", new Throwable()); //$NON-NLS-1$
            return "**missing variable source name**"; //$NON-NLS-1$
        }

        return variableSourceName;
    }

    /**
     * @return a user displayable name for the source of variables created by
     *         this factory Must not return null.
     */
    protected abstract String getVariableSourceName();
}
