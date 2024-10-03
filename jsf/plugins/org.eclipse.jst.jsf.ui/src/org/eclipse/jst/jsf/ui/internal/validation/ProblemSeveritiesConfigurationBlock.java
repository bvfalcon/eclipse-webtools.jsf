/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Oracle - adapted for use in JSF Tooling
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jst.jsf.core.internal.IJSFPreferenceModel;
import org.eclipse.jst.jsf.ui.internal.JSFUIPlugin;
import org.eclipse.jst.jsf.validation.internal.ELValidationPreferences;
import org.eclipse.jst.jsf.validation.internal.JSFTypeComparatorPreferences;
import org.eclipse.jst.jsf.validation.internal.Severity;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


/**
  */
/*package*/ class ProblemSeveritiesConfigurationBlock extends OptionsConfigurationBlock {

    private static final String SETTINGS_SECTION_NAME= "ProblemSeveritiesConfigurationBlock";  //$NON-NLS-1$
    
    /**
     * preference key.  Match to DiagnosticFactory constants
     */
    private final static Key PREF_BINARY_OP_BOTH_OPERANDS_NULL = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_BOTH_OPERANDS_NULL);
    private final static Key PREF_BINARY_OP_POSSIBLE_DIVISION_BY_ZERO = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO);
    private final static Key PREF_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION);
    private final static Key PREF_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME);
    private final static Key PREF_BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME);
    private final static Key PREF_BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN);
    private final static Key PREF_BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS);
    private final static Key PREF_BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME =
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME);
    private final static Key PREF_BINARY_OP_NO_AVAILABLE_TYPE_COERCION = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_NO_AVAILABLE_TYPE_COERCION);
    private final static Key PREF_BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS);
    private final static Key PREF_UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME = 
        getJSFCoreKey(ELValidationPreferences.UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME);
    private final static Key PREF_UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE = 
        getJSFCoreKey(ELValidationPreferences.UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE);
    private final static Key PREF_UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO = 
        getJSFCoreKey(ELValidationPreferences.UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO);
    private final static Key PREF_UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION = 
        getJSFCoreKey(ELValidationPreferences.UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION);
    private final static Key PREF_UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN = 
        getJSFCoreKey(ELValidationPreferences.UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN);
//    private final static Key PREF_TERNARY_OP_CHOICE_IS_ALWAYS_SAME = 
//        getJSFCoreKey(ELValidationPreferences.TERNARY_OP_CHOICE_IS_ALWAYS_SAME);
//    private final static Key PREF_TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN = 
//        getJSFCoreKey(ELValidationPreferences.TERNARY_OP_CANNOT_COERCE_CHOICE_TO_BOOLEAN);
    private final static Key PREF_UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED = 
        getJSFCoreKey(ELValidationPreferences.UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED);
    private final static Key PREF_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING = 
        getJSFCoreKey(ELValidationPreferences.CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING);
    private final static Key PREF_MEMBER_NOT_FOUND = 
        getJSFCoreKey(ELValidationPreferences.MEMBER_NOT_FOUND);
    private final static Key PREF_VARIABLE_NOT_FOUND = 
        getJSFCoreKey(ELValidationPreferences.VARIABLE_NOT_FOUND);
    private final static Key PREF_MISSING_CLOSING_EXPR_BRACKET =
        getJSFCoreKey(ELValidationPreferences.MISSING_CLOSING_EXPR_BRACKET);
    private final static Key PREF_GENERAL_SYNTAX_ERROR = 
        getJSFCoreKey(ELValidationPreferences.GENERAL_SYNTAX_ERROR);
    private final static Key PREF_EMPTY_EL_EXPRESSION = 
        getJSFCoreKey(ELValidationPreferences.EMPTY_EL_EXPRESSION);
    private final static Key PREF_BINARY_OP_DOT_WITH_VALUEB_NULL = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_DOT_WITH_VALUEB_NULL);
    private final static Key PREF_BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY =
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY);
    private final static Key PREF_POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS = 
        getJSFCoreKey(ELValidationPreferences.POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS);
    private final static Key PREF_BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME = 
        getJSFCoreKey(ELValidationPreferences.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME);
    private final static Key PREF_BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE = 
        getJSFCoreKey(ELValidationPreferences.BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE);
    private final static Key PREF_MEMBER_IS_INTERMEDIATE =
        getJSFCoreKey(ELValidationPreferences.MEMBER_IS_INTERMEDIATE);

    //  TypeComparator keys
    private final static Key PREF_INCOMPATIBLE_METHOD_TYPES =
        getJSFCoreKey(JSFTypeComparatorPreferences.INCOMPATIBLE_METHOD_TYPES);
    private final static Key PREF_INCOMPATIBLE_TYPES =
        getJSFCoreKey(JSFTypeComparatorPreferences.INCOMPATIBLE_TYPES);
    private final static Key PREF_METHOD_EXPRESSION_EXPECTED =
        getJSFCoreKey(JSFTypeComparatorPreferences.METHOD_EXPRESSION_EXPECTED);
    private final static Key PREF_PROPERTY_NOT_READABLE =
        getJSFCoreKey(JSFTypeComparatorPreferences.PROPERTY_NOT_READABLE);
    private final static Key PREF_PROPERTY_NOT_WRITABLE =
        getJSFCoreKey(JSFTypeComparatorPreferences.PROPERTY_NOT_WRITABLE);
    private final static Key PREF_VALUE_EXPRESSION_EXPECTED =
        getJSFCoreKey(JSFTypeComparatorPreferences.VALUE_EXPRESSION_EXPECTED);


    private final static int EXPECTED_PREFS = 34;
    
    private PixelConverter fPixelConverter;
    
    /**
     * @param prefs 
     * @param project
     * @param container
     */
    public ProblemSeveritiesConfigurationBlock(/*TODO:IStatusChangeListener context,*/ IJSFPreferenceModel prefs, IProject project, IWorkbenchPreferenceContainer container) {
        super(prefs, project, getKeys(), container);
    }
    
    private static Key[] getKeys() 
    {
        Key[] keys = new Key[] {
                PREF_BINARY_OP_BOTH_OPERANDS_NULL
                , PREF_BINARY_OP_POSSIBLE_DIVISION_BY_ZERO
                , PREF_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION 
                , PREF_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME
                , PREF_BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME
                , PREF_BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN
                , PREF_BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS
                , PREF_BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME
                , PREF_BINARY_OP_NO_AVAILABLE_TYPE_COERCION 
                , PREF_BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS 
                , PREF_UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME
                , PREF_UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE 
                , PREF_UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO 
                , PREF_UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION
                , PREF_UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN
                , PREF_UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED
                , PREF_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING
                , PREF_MEMBER_NOT_FOUND 
                , PREF_VARIABLE_NOT_FOUND
                , PREF_MISSING_CLOSING_EXPR_BRACKET
                , PREF_GENERAL_SYNTAX_ERROR
                , PREF_EMPTY_EL_EXPRESSION
                , PREF_BINARY_OP_DOT_WITH_VALUEB_NULL
                , PREF_BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY
                , PREF_POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS 
                , PREF_BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME
                , PREF_BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE
                , PREF_MEMBER_IS_INTERMEDIATE
                , PREF_INCOMPATIBLE_METHOD_TYPES
                , PREF_INCOMPATIBLE_TYPES
                , PREF_METHOD_EXPRESSION_EXPECTED
                , PREF_PROPERTY_NOT_READABLE
                , PREF_PROPERTY_NOT_WRITABLE
                , PREF_VALUE_EXPRESSION_EXPECTED
          };
        
        if (EXPECTED_PREFS != keys.length)
        {
            JSFUIPlugin.log(IStatus.WARNING, "Expected "+EXPECTED_PREFS+" preferences but was "+keys.length, new Throwable()); //$NON-NLS-1$ //$NON-NLS-2$
        }

        return keys;
    }
    
    /*
     * @see org.eclipse.jface.preference.PreferencePage#createContents(Composite)
     */
    protected Control createContents(Composite parent) {
        fPixelConverter= new PixelConverter(parent);
        setShell(parent.getShell());
        
        Group mainComp= new Group(parent, SWT.NONE);
        mainComp.setFont(parent.getFont());
        GridLayout layout= new GridLayout();
        layout.marginHeight= 0;
        layout.marginWidth= 0;
        mainComp.setLayout(layout);
        mainComp.setText(PreferencesMessages.ProblemSeveritiesConfigurationBlock_common_description);
        
        Composite commonComposite= createStyleTabContent(mainComp);
        GridData gridData= new GridData(GridData.FILL, GridData.FILL, true, true);
        gridData.heightHint= fPixelConverter.convertHeightInCharsToPixels(20);
        commonComposite.setLayoutData(gridData);
        
        validateSettings(null, null, null);
    
        return mainComp;
    }
    
    private Composite createStyleTabContent(Composite folder) {
        String[] errorWarningIgnore= new String[] { Severity.ERROR.toString(), Severity.WARNING.toString(), Severity.IGNORE.toString() };
        
        String[] errorWarningIgnoreLabels= new String[] {
            PreferencesMessages.ProblemSeveritiesConfigurationBlock_error,  
            PreferencesMessages.ProblemSeveritiesConfigurationBlock_warning, 
            PreferencesMessages.ProblemSeveritiesConfigurationBlock_ignore
        };
        
        //String[] enabledDisabled= new String[] { ENABLED, DISABLED };
        
        int nColumns= 3;
        
        final ScrolledPageContent sc1 = new ScrolledPageContent(folder);
        
        Composite composite= sc1.getBody();
        GridLayout layout= new GridLayout(nColumns, false);
        layout.marginHeight= 0;
        layout.marginWidth= 0;
        composite.setLayout(layout);
        
//        Label description= new Label(composite, SWT.LEFT | SWT.WRAP);
//        description.setFont(description.getFont());
//        description.setText(PreferencesMessages.ProblemSeveritiesConfigurationBlock_common_description); 
//        description.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, true, false, nColumns - 1, 1));
                
        int indentStep=  fPixelConverter.convertWidthInCharsToPixels(1);
        
        int defaultIndent= indentStep * 0;
        //int extraIndent= indentStep * 2;
        String label;
        ExpandableComposite excomposite;
        Composite inner;
        
        // -- general errors
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_section_general; 
        excomposite= createStyleSection(composite, label, nColumns);
        
        inner= new Composite(excomposite, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayout(new GridLayout(nColumns, false));
        excomposite.setClient(inner);
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_general_syntax_error; 
        addComboBox(inner, label, PREF_GENERAL_SYNTAX_ERROR, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_empty_el_expression; 
        addComboBox(inner, label, PREF_EMPTY_EL_EXPRESSION, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_missing_closing_expr_bracket; 
        addComboBox(inner, label, PREF_MISSING_CLOSING_EXPR_BRACKET, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_cannot_apply_operator_to_method_binding; 
        addComboBox(inner, label, PREF_CANNOT_APPLY_OPERATOR_TO_METHOD_BINDING, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_dotted_property_key_should_use_array; 
        addComboBox(inner, label, PREF_BINARY_OP_DOT_WITH_DOTTED_KEY_SHOULD_USE_ARRAY, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);
        
        // --- id resolution
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_section_id_resolution; 
        excomposite= createStyleSection(composite, label, nColumns);
        
        inner= new Composite(excomposite, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayout(new GridLayout(nColumns, false));
        excomposite.setClient(inner);
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_variable_not_found; 
        addComboBox(inner, label, PREF_VARIABLE_NOT_FOUND, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_member_not_found; 
        addComboBox(inner, label, PREF_MEMBER_NOT_FOUND, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_member_is_intermediate; 
        addComboBox(inner, label, PREF_MEMBER_IS_INTERMEDIATE, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        // --- type coercion problems
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_section_type_coercion_problems; 
        excomposite= createStyleSection(composite, label, nColumns);
        
        inner= new Composite(excomposite, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayout(new GridLayout(nColumns, false));
        excomposite.setClient(inner);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_binary_op_numeric_coercion_error; 
        addComboBox(inner, label, PREF_BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_binary_op_boolean_coercion_error; 
        addComboBox(inner, label, PREF_BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_binary_op_no_coercion_available; 
        addComboBox(inner, label, PREF_BINARY_OP_NO_AVAILABLE_TYPE_COERCION, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_binary_op_literal_to_number_coercion_error; 
        addComboBox(inner, label, PREF_BINARY_OP_COULD_NOT_COERCE_LITERALS_TO_NUMBERS, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_unary_op_numeric_coercion_error; 
        addComboBox(inner, label, PREF_UNARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_unary_op_boolean_coercion_error; 
        addComboBox(inner, label, PREF_UNARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_unary_op_string_coercion_not_guaranteed; 
        addComboBox(inner, label, PREF_UNARY_OP_STRING_CONVERSION_NOT_GUARANTEED, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        // --- constant folder and unused code
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_section_constant_folding_and_unused_code; 
        excomposite= createStyleSection(composite, label, nColumns);
        
        inner= new Composite(excomposite, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayout(new GridLayout(nColumns, false));
        excomposite.setClient(inner);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_both_binary_operands_null; 
        addComboBox(inner, label, PREF_BINARY_OP_BOTH_OPERANDS_NULL, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_binary_expression_always_evaluates_same; 
        addComboBox(inner, label, PREF_BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_equality_with_null_always_same; 
        addComboBox(inner, label, PREF_BINARY_OP_EQUALITY_COMP_WITH_NULL_ALWAYS_EVAL_SAME, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_enumeration_comparision_always_same; 
        addComboBox(inner, label, PREF_BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_unary_expression_always_evaluates_same; 
        addComboBox(inner, label, PREF_UNARY_OP_CONSTANT_EXPRESSION_EVAL_SAME, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_empty_expression_always_false; 
        addComboBox(inner, label, PREF_UNARY_OP_EMPTY_ALWAYS_FALSE_ON_TYPE, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);          

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_minus_on_null_always_zero; 
        addComboBox(inner, label, PREF_UNARY_OP_MINUS_ON_NULL_ALWAYS_ZERO, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);          

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_first_argument_short_circuits_expression; 
        addComboBox(inner, label, PREF_BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);          

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_second_argument_always_evaluates_same; 
        addComboBox(inner, label, PREF_BINARY_OP_SECOND_ARGUMENT_ALWAYS_EVAL_SAME, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);          

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_apply_dot_operator_with_null; 
        addComboBox(inner, label, PREF_BINARY_OP_DOT_WITH_VALUEB_NULL, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);          

        // --- possible programming errors
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_section_programming_errors; 
        excomposite= createStyleSection(composite, label, nColumns);
        
        inner= new Composite(excomposite, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayout(new GridLayout(nColumns, false));
        excomposite.setClient(inner);
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_possible_division_by_zero; 
        addComboBox(inner, label, PREF_BINARY_OP_POSSIBLE_DIVISION_BY_ZERO, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_possible_array_index_out_of_bounds; 
        addComboBox(inner, label, PREF_POSSIBLE_ARRAY_INDEX_OUT_OF_BOUNDS, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);        
    
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_incompatible_enumeration_comparison; 
        addComboBox(inner, label, PREF_BINARY_OP_COMPARISON_OF_ENUMS_INCOMPATIBLE, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        // --- type comparison with expected
        
        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_section_type_comparison; 
        excomposite= createStyleSection(composite, label, nColumns);
        
        inner= new Composite(excomposite, SWT.NONE);
        inner.setFont(composite.getFont());
        inner.setLayout(new GridLayout(nColumns, false));
        excomposite.setClient(inner);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_method_expression_expected; 
        addComboBox(inner, label, PREF_METHOD_EXPRESSION_EXPECTED, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_incompatible_type_assignment; 
        addComboBox(inner, label, PREF_INCOMPATIBLE_TYPES, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_value_expression_expected; 
        addComboBox(inner, label, PREF_VALUE_EXPRESSION_EXPECTED, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_incompatible_method_types; 
        addComboBox(inner, label, PREF_INCOMPATIBLE_METHOD_TYPES, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_property_not_readable; 
        addComboBox(inner, label, PREF_PROPERTY_NOT_READABLE, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        label= PreferencesMessages.ProblemSeveritiesConfigurationBlock_pb_property_not_writable; 
        addComboBox(inner, label, PREF_PROPERTY_NOT_WRITABLE, errorWarningIgnore, errorWarningIgnoreLabels, defaultIndent);

        new Label(composite, SWT.NONE);
        
        IDialogSettings section= JSFUIPlugin.getDefault().getDialogSettings().getSection(SETTINGS_SECTION_NAME);
        restoreSectionExpansionStates(section);
        
        return sc1;
    }
    
    /* (non-javadoc)
     * Update fields and validate.
     * @param changedKey Key that changed, or null, if all changed.
     */ 
    protected void validateSettings(Key changedKey, String oldValue, String newValue) {
        if (!areSettingsEnabled()) {
            return;
        }
        
//        if (changedKey != null) {
//            if (PREF_PB_UNUSED_PARAMETER.equals(changedKey) ||
//                    PREF_PB_DEPRECATION.equals(changedKey) ||
//                    PREF_PB_LOCAL_VARIABLE_HIDING.equals(changedKey) ||
//                    PREF_PB_UNUSED_DECLARED_THROWN_EXCEPTION.equals(changedKey)) {              
//                updateEnableStates();
//            } else if (PREF_PB_SIGNAL_PARAMETER_IN_OVERRIDING.equals(changedKey)) {
//                // merging the two options
//                setValue(PREF_PB_SIGNAL_PARAMETER_IN_ABSTRACT, newValue);
//            } else {
//                return;
//            }
//        } else {
            updateEnableStates();
//        }       
        // TODO: fContext.statusChanged(new StatusInfo());
    }
    
    private void updateEnableStates() {
//        boolean enableUnusedParams= !checkValue(PREF_PB_UNUSED_PARAMETER, Severity.IGNORE.toString());
//        getCheckBox(PREF_PB_SIGNAL_PARAMETER_IN_OVERRIDING).setEnabled(enableUnusedParams);
//        getCheckBox(PREF_PB_UNUSED_PARAMETER_INCLUDE_DOC_COMMENT_REFERENCE).setEnabled(enableUnusedParams);
//        
//        boolean enableDeprecation= !checkValue(PREF_PB_DEPRECATION, Severity.IGNORE.toString());
//        getCheckBox(PREF_PB_DEPRECATION_IN_DEPRECATED_CODE).setEnabled(enableDeprecation);
//        getCheckBox(PREF_PB_DEPRECATION_WHEN_OVERRIDING).setEnabled(enableDeprecation);
//        
//        boolean enableThrownExceptions= !checkValue(PREF_PB_UNUSED_DECLARED_THROWN_EXCEPTION, Severity.IGNORE.toString());
//        getCheckBox(PREF_PB_UNUSED_DECLARED_THROWN_EXCEPTION_WHEN_OVERRIDING).setEnabled(enableThrownExceptions);
//
//        boolean enableHiding= !checkValue(PREF_PB_LOCAL_VARIABLE_HIDING, Severity.IGNORE.toString());
//        getCheckBox(PREF_PB_SPECIAL_PARAMETER_HIDING_FIELD).setEnabled(enableHiding);
    }

    protected String[] getFullBuildDialogStrings(boolean workspaceSettings) {
        String title= PreferencesMessages.ProblemSeveritiesConfigurationBlock_needsbuild_title; 
        String message;
        if (workspaceSettings) {
            message= PreferencesMessages.ProblemSeveritiesConfigurationBlock_needsfullbuild_message; 
        } else {
            message= PreferencesMessages.ProblemSeveritiesConfigurationBlock_needsprojectbuild_message; 
        }
        return new String[] { title, message };
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jdt.internal.ui.preferences.OptionsConfigurationBlock#dispose()
     */
    public void dispose() {
        IDialogSettings section= JSFUIPlugin.getDefault().getDialogSettings().addNewSection(SETTINGS_SECTION_NAME);
        storeSectionExpansionStates(section);
        super.dispose();
    }
    
}
