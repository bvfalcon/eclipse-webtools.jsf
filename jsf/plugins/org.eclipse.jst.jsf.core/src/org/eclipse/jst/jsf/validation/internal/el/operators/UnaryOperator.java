/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
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

package org.eclipse.jst.jsf.validation.internal.el.operators;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.types.MethodType;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParserConstants;
import org.eclipse.jst.jsp.core.internal.java.jspel.Token;

/**
 * Encapsulates an EL unary operator
 * @author cbateman
 *
 */
public abstract class UnaryOperator 
{
    /**
     * The common factory used to construct diagnostics
     */
    protected final DiagnosticFactory     _diagnosticFactory;
    
    /**
     * @param token
     * @return true if the token is a unary operator
     */
    public static boolean isUnaryOperator(Token token)
    {
        return (token.kind == JSPELParserConstants.MINUS)
                  || (token.kind == JSPELParserConstants.NOT1)
                  || (token.kind == JSPELParserConstants.NOT2)
                  || (token.kind == JSPELParserConstants.EMPTY);
    }

    /**
     * @param token 
     * @param diagnosticFactory 
     * @return a new UnaryOperator instance matching token 
     */
    public static UnaryOperator createUnaryOperator(Token token, DiagnosticFactory diagnosticFactory)
    {
        if (!isUnaryOperator(token))
        {
            throw new IllegalArgumentException("token must be a unary operator"); //$NON-NLS-1$
        }
        
        switch(token.kind)
        {
            case JSPELParserConstants.MINUS:
                return new MinusUnaryOperator(diagnosticFactory);

            case JSPELParserConstants.NOT1:
            case JSPELParserConstants.NOT2:
                return new NotUnaryOperator(diagnosticFactory);
                
            case JSPELParserConstants.EMPTY:
                return new EmptyUnaryOperator(diagnosticFactory);
        }

        // should never get here because all four ops are covered
        throw new AssertionError();
    }

    /**
     * Constructor
     */
    UnaryOperator(DiagnosticFactory diagnosticFactory) 
    {
        /* no construction or sub-classing outside package*/
        _diagnosticFactory = diagnosticFactory;
    }
    
    /**
     * If ValueType is a literal and the operation can be performed, then
     * the return must be a new LiteralType transformed using this operator.
     * 
     * If ValueType is not a literal and the operaton can be performed, then
     * the return is a new ValueType transformed per the rules of the operator
     * (i.e. if it is a string type and the operator is "!", then the string
     * must be coerced to a boolean and this is what will be returned)
     * 
     * If the operation cannot be performed on ValueType, return null
     * 
     * @param type
     * @return a new value type after the operation is performed
     */
    public abstract ValueType performOperation(ValueType type);
    
    /**
     * @param type
     * @return a Diagnostic interpreting whether it is valid to perform the
     * operation on this type
     */
    public abstract Diagnostic validate(ValueType type);

    /**
     * @param type
     * @return a Diagnostic interpreting whether it is valid to perform the
     * operation on this type
     */
    public abstract Diagnostic validate(MethodType type);
}
