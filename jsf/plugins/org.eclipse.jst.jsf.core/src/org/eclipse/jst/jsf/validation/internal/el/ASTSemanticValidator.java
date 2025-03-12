/*******************************************************************************
 * Copyright (c) 2006, 2009 Oracle Corporation.
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

package org.eclipse.jst.jsf.validation.internal.el;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.BooleanLiteralType;
import org.eclipse.jst.jsf.common.internal.types.FloatLiteralType;
import org.eclipse.jst.jsf.common.internal.types.IntegerLiteralType;
import org.eclipse.jst.jsf.common.internal.types.LiteralType;
import org.eclipse.jst.jsf.common.internal.types.MethodType;
import org.eclipse.jst.jsf.common.internal.types.NullLiteralType;
import org.eclipse.jst.jsf.common.internal.types.SignatureBasedType;
import org.eclipse.jst.jsf.common.internal.types.StringLiteralType;
import org.eclipse.jst.jsf.common.internal.types.TypeCoercer;
import org.eclipse.jst.jsf.common.internal.types.TypeTransformer;
import org.eclipse.jst.jsf.common.internal.types.ValueType;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IPropertySymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.internal.util.IObjectSymbolBasedValueType;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.resolver.IStructuredDocumentSymbolResolverFactory;
import org.eclipse.jst.jsf.designtime.resolver.ISymbolContextResolver;
import org.eclipse.jst.jsf.validation.internal.IJSFViewValidator.IValidationReporter;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;
import org.eclipse.jst.jsf.validation.internal.el.operators.BinaryOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.BracketOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.DotOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.TernaryChoiceOperator;
import org.eclipse.jst.jsf.validation.internal.el.operators.UnaryOperator;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTAddExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTAndExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTChoiceExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTEqualityExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTFunctionInvocation;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTLiteral;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTMultiplyExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTOperatorExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTOrExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTRelationalExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTUnaryExpression;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTValue;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTValuePrefix;
import org.eclipse.jst.jsp.core.internal.java.jspel.ASTValueSuffix;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParser;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParserConstants;
import org.eclipse.jst.jsp.core.internal.java.jspel.JSPELParserVisitor;
import org.eclipse.jst.jsp.core.internal.java.jspel.ParseException;
import org.eclipse.jst.jsp.core.internal.java.jspel.SimpleNode;
import org.eclipse.jst.jsp.core.internal.java.jspel.Token;


class ASTSemanticValidator implements JSPELParserVisitor, IExpressionSemanticValidator
{
    private final IFile                             _targetFile;
    private final ASTExpression                     _expr;
    private final IStructuredDocumentContext        _context;
    private final ISymbolContextResolver            _symbolResolver;
//    private final List<IMessage>                    _messages;
    private final EvaluationTracker                 _tracker;
    private final DiagnosticFactory                 _diagnosticFactory;
    private final IValidationReporter               _reporter;

    ASTSemanticValidator(final ASTExpression expr,
            final IStructuredDocumentContext context,
            final IStructuredDocumentSymbolResolverFactory symbolResolverFactory,
            final IValidationReporter reporter)
    {
        final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                .getWorkspaceContextResolver(context);

        if (resolver != null)
        {
            _targetFile = (IFile) resolver.getResource();
        }
        else
        {
            _targetFile = null;
        }

        _expr = expr;
        _context = context;
        _symbolResolver = symbolResolverFactory.getSymbolContextResolver(context);
        _tracker = new EvaluationTracker();
        _diagnosticFactory = new DiagnosticFactory();
        _reporter = reporter;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator#validate()
     */
    public void validate()
    {
        _expr.jjtAccept(this, _tracker);
    }

    public Object visit(final ASTAddExpression node, final Object data)
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
    }

    public Object visit(final ASTAndExpression node, final Object data)
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
    }

    public Object visit(final ASTChoiceExpression node, final Object data)
    {
        if (node.jjtGetNumChildren() != 3)
        {
            throw new AssertionError("Binary operators should always have two sub-expressions");  //$NON-NLS-1$
        }

        // evaluate choice argument
        node.jjtGetChild(0).jjtAccept(this, data);
        final ValueType choiceArg = ((EvaluationTracker)data).getValueType();
        // evaluate when true argument
        node.jjtGetChild(1).jjtAccept(this, data);
        final ValueType whenTrueArg = ((EvaluationTracker)data).getValueType();
        //evaluate when false argument
        node.jjtGetChild(2).jjtAccept(this, data);
        final ValueType whenFalseArg = ((EvaluationTracker)data).getValueType();


        if (choiceArg != null && whenTrueArg != null && whenFalseArg != null)
        {
            final TernaryChoiceOperator operator =
                new TernaryChoiceOperator(_diagnosticFactory);

            final Diagnostic diagnostic =
                operator.validate(choiceArg/* whenTrueArg, whenFalseArg*/);

            if (diagnostic.getSeverity() != Diagnostic.OK)
            {
                final Token firstToken = node.getFirstToken();
                final int offset = _context.getDocumentPosition() + firstToken.beginColumn - 1;
                final int length = node.getLastToken().endColumn - firstToken.beginColumn+1;
                _reporter.report(diagnostic, offset, length);
            }

            ((EvaluationTracker)data).setType(operator.perform(choiceArg, whenTrueArg, whenFalseArg));
        }
        else
        {
            ((EvaluationTracker)data).setType(null);
        }

        return data;
    }

    public Object visit(final ASTEqualityExpression node, final Object data)
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
    }

    public Object visit(final ASTExpression node, final Object data) {
        return node.childrenAccept(this, data);
    }

    public Object visit(final ASTFunctionInvocation node, final Object data)
    {
        // when we see a function invocation, null the type
        // we do not validate function invocations currently
        final Object retVal = node.childrenAccept(this, data);
        ((EvaluationTracker)data).setType(null);
        return retVal;
    }

    public Object visit(final ASTLiteral node, final Object data)
    {
        // note, there is an implicit assumption here that literals
        // are all terminals (leafs in the tree)
        if (node.jjtGetNumChildren() > 0)
        {
            throw new AssertionError("Literals should be terminal"); //$NON-NLS-1$
        }

        LiteralType type = null;

        final Token  literalToken = node.getFirstToken();

        switch (literalToken.kind)
        {
            case JSPELParserConstants.STRING_LITERAL:
                type = new StringLiteralType(stripQuotes(literalToken.image));
                break;

            case JSPELParserConstants.INTEGER_LITERAL:
                type = new IntegerLiteralType(Long.parseLong(literalToken.image));
                break;

            case JSPELParserConstants.FLOATING_POINT_LITERAL:
                type = new FloatLiteralType(Double.parseDouble(literalToken.image));
                break;

            case JSPELParserConstants.FALSE:
                type = BooleanLiteralType.FALSE;
                break;

            case JSPELParserConstants.TRUE:
                type = BooleanLiteralType.TRUE;
                break;

            case JSPELParserConstants.NULL:
                type = NullLiteralType.SINGLETON;
                break;

            default:
                JSFCorePlugin.log("Unknown EL literal: " +literalToken.toString(), new Throwable("This throwable simply used to mark a stack trace")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        ((EvaluationTracker)data).setType(type);
        return data;
    }

    private String stripQuotes(final String stringLiteral)
    {
        if (stringLiteral.startsWith("'") //$NON-NLS-1$
                || stringLiteral.startsWith("\"")) //$NON-NLS-1$

        {
            if (stringLiteral.length() > 2)
            {
                // take 'literal' -> literal
                return stringLiteral.substring(1, stringLiteral.length()-1);
            }
            // if only two characters, then the empty string
            return ""; //$NON-NLS-1$
        }

        return stringLiteral;
    }

    public Object visit(final ASTMultiplyExpression node, final Object data)
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
    }

    public Object visit(final ASTOrExpression node, final Object data)
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
    }

    public Object visit(final ASTRelationalExpression node, final Object data)
    {
        performBinaryEvaluation(node, (EvaluationTracker)data);
        return data;
    }

    public Object visit(final ASTUnaryExpression node, final Object data)
    {
        // assertion here is that this expression decomposes:
        // UnaryExpr -> Value
        // UnaryExpr -> UnaryOp UnaryExpression
        // since UnaryOp is a terminal (-,!,not,empty) node will
        // always have exactly one child
        node.childrenAccept(this, data);
        final SignatureBasedType type = ((EvaluationTracker)data).getType();

        if (type != null)
        {
            final Token  firstToken = node.getFirstToken();
            if (UnaryOperator.isUnaryOperator(firstToken))
            {
                if (type instanceof ValueType)
                {
                    final UnaryOperator unaryOp = UnaryOperator.createUnaryOperator(firstToken, _diagnosticFactory);
                    final Diagnostic diagnostic = unaryOp.validate((ValueType)type);

                    if (diagnostic.getSeverity() != Diagnostic.OK)
                    {
                        final int offset = _context.getDocumentPosition() + firstToken.beginColumn - 1;
                        final int length = node.getLastToken().endColumn - firstToken.beginColumn+1;
                        _reporter.report(diagnostic, offset, length);
                    }

                    ((EvaluationTracker)data).
                    setType(unaryOp.performOperation ((ValueType)type));
                }
                else if (type instanceof MethodType) {
                    final UnaryOperator unaryOp = UnaryOperator.createUnaryOperator(firstToken, _diagnosticFactory);
                    final Diagnostic diagnostic = unaryOp.validate((MethodType)type);

                    if (diagnostic.getSeverity() != Diagnostic.OK)
                    {
                        final int offset = _context.getDocumentPosition() + firstToken.beginColumn - 1;
                        final int length = node.getLastToken().endColumn - firstToken.beginColumn+1;
                        _reporter.report(diagnostic, offset, length);
                    }

//                    ((EvaluationTracker)data).setType(unaryOp.performOperation ((MethodType)type));
                }
                // cannot apply operations to method bindings
                else
                {
                    final int offset = _context.getDocumentPosition() +
                    firstToken.beginColumn - 1;
                    final int length = node.getLastToken().endColumn -
                    firstToken.beginColumn+1;

                    Diagnostic diagnostic = 
                        _diagnosticFactory.create_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING();
                    
                    _reporter.report(diagnostic, offset, length);
                }
            }
        }

        return data;
    }

    public Object visit(final ASTValue node, final Object data) {
        final ValueExpressionTracker  tracker = new ValueExpressionTracker();

        ((EvaluationTracker)data).setValueTracker(tracker);

        node.childrenAccept(this, data);

        final SignatureBasedType type = ((EvaluationTracker)data).getType();

        // now check the tracker.  If the last property in the expression
        // is non-null (i.e. the value has one or more suffices) then we
        // to very the leaf node (i.e. 'z' in #{x.y.z}) is more than just
        // an intermediate value used to get to other properties
        if (type instanceof IObjectSymbolBasedValueType
                && ((IObjectSymbolBasedValueType)type).getSymbol() instanceof IPropertySymbol
                && ((IPropertySymbol)((IObjectSymbolBasedValueType)type).getSymbol()).isIntermediate())
        {
            final int offset = tracker.getCurPropertySymbolOffset();
            final int length = tracker.getCurPropertySymbolLength();
            final Diagnostic diagnostic = 
                _diagnosticFactory.create_MEMBER_IS_INTERMEDIATE(
                        ((IPropertySymbol)((IObjectSymbolBasedValueType)type).getSymbol()).getName());
            _reporter.report(diagnostic, offset, length);
        }

        return data;
    }

    public Object visit(final ASTValuePrefix node, final Object data)
    {
        if (node.jjtGetNumChildren() == 0)
        {
            final Token token = node.getFirstToken();
            final String image = token.image;

            final ISymbol symbol = _symbolResolver.getVariable(image);

            if (symbol == null)
            {
                final int offset =
                    _context.getDocumentPosition() + token.beginColumn - 1;
                final int length = token.endColumn - token.beginColumn + 1;

                final Diagnostic diagnostic =
                    _diagnosticFactory.create_VARIABLE_NOT_FOUND(image);

                if (diagnostic.getSeverity() != Diagnostic.OK)
                {
                    _reporter.report(diagnostic, offset, length);
                }
            }
            else if (symbol instanceof IInstanceSymbol)
            {
                final IObjectSymbolBasedValueType symbolType =
                    IObjectSymbolBasedValueType.getInstance(symbol);
                ((EvaluationTracker) data).setType(symbolType);
            }
        }

        return node.childrenAccept(this, data);
    }

    public Object visit(final ASTValueSuffix node, final Object data)
    {
        final ValueExpressionTracker tracker = ((EvaluationTracker) data).getValueTracker();
        final SignatureBasedType type = ((EvaluationTracker) data).getType();

        if (type instanceof IObjectSymbolBasedValueType)
        {
            final IObjectSymbolBasedValueType symbolType =
                (IObjectSymbolBasedValueType) type;
            final Token firstToken = node.getFirstToken();

            if (node.jjtGetNumChildren() == 0
                    && firstToken.kind == JSPELParserConstants.DOT)
            {
                final Token dotId = node.getLastToken();

                final int offset =
                    _context.getDocumentPosition() + dotId.beginColumn - 1;
                final int length = dotId.endColumn - dotId.beginColumn + 1;

                final DotOperator dotOp = new DotOperator(_diagnosticFactory, _targetFile, _symbolResolver);

                final StringLiteralType  suffixLiteral = new StringLiteralType(dotId.image);
                final Diagnostic diagnostic =
                    dotOp.validate(symbolType,
                            suffixLiteral);

                if (diagnostic.getSeverity() != Diagnostic.OK)
                {
                    _reporter.report(diagnostic, offset, length);
                    ((EvaluationTracker) data).setType(null);
                }
                else
                {
                    //                    // if the base (value-a) is a map, then using the bracket value-a['y'] type
                    //                    // syntax is recommended.  Note that we do this here instead of
                    //                    // DotOperator so that we don't tie the default property resolver
                    //                    // behaviour to that operator class.  If someone changes the rules
                    //                    // of how the prop resolver interprets the base, then they may want to
                    //                    // write their own validator that doesn't do this
                    //                    if (symbolType.getSymbol().supportsCoercion(TypeConstants.TYPE_MAP))
                    //                    {
                    //                        _messages.add(ValidationMessageFactory.createFromDiagnostic(
                    //                                DiagnosticFactory.create_BINARY_OP_DOT_WITH_VALUEA_MAP_SHOULD_USE_ARRAY
                    //                                    (symbolType.getSymbol().getName(), dotId.image),
                    //                                        startOffset, length, _targetFile));
                    //                    }

                    ((EvaluationTracker) data).setType(dotOp.performOperation(symbolType,
                            suffixLiteral));
                    tracker.setCurMemberSymbol(offset, length);
                }

                // we finished with the single dot suffix here
                return data;
            }
            else if (firstToken.kind == JSPELParserConstants.LBRACKET)
            {
                final EvaluationTracker subExprTracker = new EvaluationTracker();
                node.childrenAccept(this, subExprTracker);

                final SignatureBasedType subExprType = subExprTracker.getType();

                if (subExprType instanceof ValueType)
                {
                    final Token lastToken = node.getLastToken();
                    final int offset =
                        _context.getDocumentPosition() + firstToken.beginColumn - 1;
                    final int length = lastToken.endColumn - firstToken.beginColumn + 1;

                    final BracketOperator  bracketOperator = new BracketOperator(_diagnosticFactory, _targetFile, _symbolResolver);

                    final Diagnostic diagnostic =
                        bracketOperator.validate(symbolType,
                                (ValueType)subExprType);

                    if (diagnostic.getSeverity() != Diagnostic.OK)
                    {
                        _reporter.report(diagnostic, offset, length);
                        ((EvaluationTracker) data).setType(null);
                    }
                    else
                    {
                        ((EvaluationTracker) data).setType(bracketOperator.performOperation(symbolType,
                                (ValueType)subExprType));
                        tracker.setCurMemberSymbol(offset, length);
                    }
                }
                // we are finished with the bracketed suffix at this point.
                return data;
            }
        }

        // don't bother to accept children, since if we haven't done
        // something above, there's not much sensible we can do with it
        // clear the type first though
        ((EvaluationTracker) data).setType(null);
        return data; //node.childrenAccept(this, data);
    }

    public Object visit(final SimpleNode node, final Object data) {
        return node.childrenAccept(this, data);
    }

    /**
     * Copies stored messages into the validation reporter
     * @param validator
     * @param reporter
     */
//    public void reportFindings(final IValidator validator, final IReporter reporter)
//    {
//        for (final IMessage message : _messages)
//        {
//            // don't report messages that have no severity.
//            if ((message.getSeverity() & IMessage.ALL_MESSAGES) != 0)
//            {
//                reporter.addMessage(validator, message);
//            }
//        }
//    }

    private void performBinaryEvaluation(final ASTOperatorExpression node, final EvaluationTracker tracker)
    {
        if (node.jjtGetNumChildren() < 2)
        {
            throw new AssertionError("Binary operators should always have at least two sub-expressions"); //$NON-NLS-1$
        }
        else if (node.getOperatorTokens().size() != node.jjtGetNumChildren()-1)
        {
            throw new AssertionError("Binary operators should always have one operator token less than number of sub-expressions"); //$NON-NLS-1$
        }

        // evaluate left-most argument
        node.jjtGetChild(0).jjtAccept(this, tracker);

        ValueType curType = getValueTypeForBinaryOperation(tracker.getType(), (SimpleNode) node.jjtGetChild(0));

        for (int child = 1; child < node.jjtGetNumChildren(); child++)
        {
            // evaluate next argument running left-to-right
            node.jjtGetChild(child).jjtAccept(this, tracker);
            final ValueType secondType =
                getValueTypeForBinaryOperation(tracker.getType(), (SimpleNode) node.jjtGetChild(child));

            if (curType != null && secondType != null)
            {
                final BinaryOperator operator =
                    BinaryOperator.getBinaryOperator((Token)node.getOperatorTokens().get(child-1), _diagnosticFactory, _context);

                final Diagnostic diagnostic = operator.validate(curType, secondType);

                if (diagnostic.getSeverity() != Diagnostic.OK)
                {
                    final Token firstToken = node.getFirstToken();
                    final int offset = _context.getDocumentPosition() + firstToken.beginColumn - 1;
                    final int length = node.getLastToken().endColumn - firstToken.beginColumn+1;
                    _reporter.report(diagnostic, offset, length);
                }

                curType = operator.performOperation(curType, secondType);
            }
        }

        tracker.setType(curType);
    }

    private ValueType getValueTypeForBinaryOperation(final SignatureBasedType type, final SimpleNode node)
    {
        if (type instanceof ValueType)
        {
            return (ValueType) type;
        }
        else if (type instanceof MethodType)
        {
            boolean canCoerce = TypeCoercer.canCoerceToBoolean(
                    TypeTransformer.transformBoxPrimitives(Signature.getReturnType(type.getSignature())));
            if (!canCoerce) {
                final int offset = _context.getDocumentPosition() + node.getFirstToken().beginColumn - 1;
                final int length = node.getLastToken().endColumn - node.getFirstToken().beginColumn+1;
                final Diagnostic diagnostic = _diagnosticFactory.create_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING();
                _reporter.report(diagnostic, offset, length);
            }
        }

        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator#getMessages()
     */
//    public List getMessages()
//    {
//        if (!_validatorHasBeenCalled)
//        {
//            throw new AssertionError("Should not call getMessages before validate has been called");
//        }
//        return _messages;
//    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.validation.internal.el.IExpressionSemanticValidator#getExpressionType()
     */
    public SignatureBasedType getExpressionType()
    {
        return _tracker.getType();
    }

    /**
     * @param args
     * @throws IOException
     * @throws ParseException
     */
    public static void main(final String[] args) throws IOException, ParseException
    {
        String elText = ""; //$NON-NLS-1$
        int nextCharacter;

        while(((nextCharacter = System.in.read()) != -1))
        {
            final char nextChar = (char) nextCharacter;

            if (nextChar == '\n')
            {
                final JSPELParser parser = JSPELParser.createParser(elText);
                final ASTExpression expr = parser.Expression();
                expr.dump(""); //$NON-NLS-1$

                elText = ""; //$NON-NLS-1$
            }
            else
            {
                elText += nextChar;
            }
        }
    }
}
