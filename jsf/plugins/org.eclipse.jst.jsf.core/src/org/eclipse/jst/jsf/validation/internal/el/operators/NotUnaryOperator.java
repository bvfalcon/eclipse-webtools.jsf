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
import org.eclipse.jst.jsf.common.internal.types.BooleanLiteralType;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.MethodType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercionException;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Represents te EL unary operators "!" and "not" on a ValueType
 * as described by JSP.2.3.6.2
 * 
 * @author cbateman
 *
 */
/*package*/ class NotUnaryOperator extends UnaryOperator 
{
    NotUnaryOperator(DiagnosticFactory diagnosticFactory) 
    {
        super(diagnosticFactory);
    }

    public Diagnostic validate(ValueType type)
    {
    	if (TypeConstants.TYPE_JAVAOBJECT.equals(type.getSignature())) {
    		return Diagnostic.OK_INSTANCE;
    	}
        boolean canCoerce =
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(type.getSignature()));

        if (canCoerce)
        {
            // check for constant evaluation
            if (type instanceof LiteralType)
            {
                try
                {
                    Boolean coercedValue = ((LiteralType)type).coerceToBoolean();
                    
                    // we are logically notting, so coerced is true, then false
                    // if false then true
                    return _diagnosticFactory.create_UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME_ID(
                                 "not" //$NON-NLS-1$
                                 , Boolean.valueOf(!coercedValue.booleanValue()).toString()); 
                        
                }
                catch (TypeCoercionException tce)
                {
                    throw new AssertionError("coerce should not throw exception"); //$NON-NLS-1$
                }
            }
            
            return Diagnostic.OK_INSTANCE;
        }
        return _diagnosticFactory.create_UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN();
    }
    
    public ValueType performOperation(ValueType type)
    {
        boolean canCoerce =
            TypeCoercer.canCoerceToBoolean(TypeTransformer.transformBoxPrimitives(type.getSignature()));

        if (canCoerce)
        {
            if (type instanceof LiteralType)
            {
                try
                {
                    Boolean coercedValue = ((LiteralType)type).coerceToBoolean();
                    
                    // we are logically notting, so coerced is true, then false
                    // if false then true
                    return 
                        coercedValue.booleanValue() ? 
                                  BooleanLiteralType.FALSE :
                                      BooleanLiteralType.TRUE;
                }
                catch (TypeCoercionException tce)
                {
                    throw new AssertionError("coerce should not throw exception"); //$NON-NLS-1$
                }
            }
            
            return new ValueType(TypeConstants.TYPE_BOOLEAN, type.getAssignability());
        }
        return null;
    }

    @Override
    public Diagnostic validate(MethodType type) {
        boolean canCoerce = TypeCoercer.canCoerceToBoolean(
                TypeTransformer.transformBoxPrimitives(Signature.getReturnType(type.getSignature())));
        if (canCoerce) {
            return Diagnostic.OK_INSTANCE;
        }
        return _diagnosticFactory.create_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING();
    }
}
