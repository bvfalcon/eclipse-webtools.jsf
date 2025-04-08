/*******************************************************************************
 * Copyright (c) 2006, 2010 Oracle Corporation.
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
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.FloatLiteralType;
import org.eclipse.jst.jsf.common.internal.types.IAssignable;
import org.eclipse.jst.jsf.common.internal.types.IntegerLiteralType;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.MethodType;
import org.eclipse.jst.jsf.common.internal.types.StringLiteralType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercionException;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Represents the EL unary operator '-' on a ValueType 
 * as described by JSP.2.3.5.4
 * 
 * 
 * @author cbateman
 *
 */
/*package*/class MinusUnaryOperator extends UnaryOperator 
{
    private static final String UNARY_MINUS = "unary minus"; //$NON-NLS-1$

    MinusUnaryOperator(DiagnosticFactory diagnosticFactory) 
    {
        super(diagnosticFactory);
    }

    public Diagnostic validate(ValueType type)
    {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(type.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}
        // must coerce to numeric type
        try
        {
            // if coerceTypeNumber doesn't throw an exception, then
            // give the benefit of the doubt
            final String coercedType =
                TypeCoercer.
                    coerceToNumber(TypeTransformer.
                            transformBoxPrimitives(type.getSignature()));
            
            if (TypeCoercer.typeIsNull(coercedType))
            {
                // null always coerces to 0L on this operator
                return _diagnosticFactory.
                    create_UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO();
            }

            // JSP.2.3.5.4, step 2 if BigDecimal or BigInteger, then can't be
            // literal and retains type
            if (TypeConstants.TYPE_BIG_DOUBLE.equals(coercedType)
                    || TypeConstants.TYPE_BIG_INTEGER.equals(coercedType))
            {
                return Diagnostic.OK_INSTANCE;
            }

            // JSP.2.4.5.4, step 3: if String
            // note, use uncoerced type, since type coercer will return null for strings
            if (TypeCoercer.typeIsString(type.getSignature()))
            {
                // if it's a string and we have the value, we can determine for
                // sure whether or not it's coercable to a number
                // per JSP.2.3.5.4 step 3.1
                if (type instanceof StringLiteralType)
                {
                    String literalValue = ((LiteralType)type).getLiteralValue();
                    if (literalValue.indexOf('.') > -1
                            || literalValue.indexOf('e') > -1
                            || literalValue.indexOf('E') > -1)
                    {
                        // if it coerces to double, then it's a double
                        ((LiteralType)type).coerceToNumber(Double.class);
                        // this is okay, because an expression like #{-3.3} can't be folded
                        return Diagnostic.OK_INSTANCE;
                    }
                    // per JSP.2.3.5.4, step 3.2 try to coerce to long
                    // if it coerces to long, then it's a long
                    ((LiteralType)type).coerceToNumber(Long.class);
                    // this is okay, because an expression like #{-3} can't be folded
                    return Diagnostic.OK_INSTANCE;
                }
                // if non-literal string, warn that coercion to number is not 
                // guaranteed since Long.valueOf and Double.valueOf 
                // (unlike Boolean.valueOf) throw NumberFormatExceptions
                return _diagnosticFactory.create_UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED(UNARY_MINUS);
            }
            
            // JSP.2.3.5.4, step 4, for all numeric types, retain type,
            // validate constant folding
            // note that this return true for big int and decimal, so those cases
            // must already have been handled.
            if (TypeCoercer.typeIsNumeric(coercedType))
            {
                // otherwise, we are fine
                return Diagnostic.OK_INSTANCE;
            }
            
        }
        catch (TypeCoercionException tce)
        {
            // fallthrough to error below
        }
        // otherwise, error
        return _diagnosticFactory.create_UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION(UNARY_MINUS);
    }
    
    /**
     * Based on JSP.2.3.5.4
     * 
     * @param type
     * @return type of type after a minus is applied or null if unknown
     */
    public ValueType performOperation(ValueType type)
    {
        try
        {
            final String  boxedType = 
                TypeTransformer.transformBoxPrimitives(type.getSignature());
            // check for valid type coercion 
            String coercedType = TypeCoercer.coerceToNumber(boxedType);

            if (TypeCoercer.typeIsNull(coercedType))
            {
                // null always coerces to 0L on this operator
                return new IntegerLiteralType(0L);
            }

            // JSP.2.3.5.4, step 2 if BigDecimal or BigInteger, then can't be
            // literal and retains type
            if (TypeConstants.TYPE_BIG_DOUBLE.equals(coercedType))
            {
                return new ValueType(TypeConstants.TYPE_BIG_DOUBLE, IAssignable.ASSIGNMENT_TYPE_RHS);
            }
            else if (TypeConstants.TYPE_BIG_INTEGER.equals(coercedType))
            {
                return new ValueType(TypeConstants.TYPE_BIG_INTEGER, IAssignable.ASSIGNMENT_TYPE_RHS);
            }
            
            if (TypeCoercer.typeIsString(type.getSignature()))
            {
                // if it's string and we have the value, we can determine for
                // sure whether or not it's coercable to a number
                // per JSP.2.3.5.4 step 3
                if (type instanceof StringLiteralType)
                {
                    String literalValue = ((LiteralType)type).getLiteralValue();
                    if (literalValue.indexOf('.') > -1
                            || literalValue.indexOf('e') > -1
                            || literalValue.indexOf('E') > -1)
                    {
                        // if it coerces to double, then it's a double
                        Number value = ((LiteralType)type).coerceToNumber(Double.class);
                        return new FloatLiteralType(-1 * value.doubleValue());
                    }
    
                    // if it coerces to long, then it's a long
                    Number value = ((LiteralType)type).coerceToNumber(Long.class);
                    return new IntegerLiteralType(-1 * value.longValue());
                }
                
                // otherwise, just return a long typed value
                return new ValueType(Signature.SIG_LONG, IAssignable.ASSIGNMENT_TYPE_RHS);
            }
            
            // JSP.2.3.5.4
            // big integer and big decimal retain type
            // all numeric types retain type
            if (TypeCoercer.typeIsNumeric(boxedType))
            {
                // integer and float literals are special because -1 or -1.0
                // is syntically minusOp(1) and minusOp(1.0)
                if (type instanceof IntegerLiteralType)
                {
                    return new IntegerLiteralType(-1 * ((IntegerLiteralType)type).coerceToNumber(Long.class).longValue());
                }
                else if (type instanceof FloatLiteralType)
                {
                    return new FloatLiteralType(-1 * ((FloatLiteralType)type).coerceToNumber(Double.class).doubleValue());
                }
                return type;
            }
           
            // all other cases, return null
            // even is type represents a String, without it's value, we have
            // no idea how to coerce it without it's value
            // fall through and return null
        }
        catch (TypeCoercionException tce)
        {
            // do nothing, fall through and return null
        }
        
        return null;
    }

    @Override
    public Diagnostic validate(MethodType type) {
        return _diagnosticFactory.create_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING();
    }
}
