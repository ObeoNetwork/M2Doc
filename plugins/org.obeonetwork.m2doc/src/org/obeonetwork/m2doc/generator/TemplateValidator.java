/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.generator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationMessage;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.ICollectionType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.acceleo.query.validation.type.NothingType;
import org.obeonetwork.m2doc.api.AQL4Compat;
import org.obeonetwork.m2doc.api.QueryServices;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.TableMerge;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;

/**
 * Validates {@link DocumentTemplate}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplateValidator extends TemplateSwitch<Void> {

    /**
     * {@link Boolean} {@link IType}.
     */
    private IType booleanObjectType;

    /**
     * boolean {@link IType}.
     */
    private IType booleanType;

    /**
     * The {@link Stack} of variables types.
     */
    private final Stack<Map<String, Set<IType>>> stack = new Stack<>();

    /**
     * AQL environment used to validate queries.
     */
    private IQueryEnvironment environment;

    /**
     * Validates the given {@link DocumentTemplate} against the given {@link IQueryEnvironment} and variables types.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param generation
     *            Generation
     */
    public void validate(DocumentTemplate documentTemplate, Generation generation) {
        environment = QueryServices.getInstance().initAcceleoEnvironment(generation);
        internalValidate(documentTemplate, generation);
    }

    /**
     * Validates the given {@link DocumentTemplate} against the given {@link IQueryEnvironment} and variables types.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param generation
     *            Generation
     */
    public void internalValidate(DocumentTemplate documentTemplate, Generation generation) {
        Map<String, Set<IType>> types = QueryServices.getInstance().getTypes(environment, generation);
        validate(documentTemplate, generation, environment, types);
    }

    /**
     * Validates the given {@link DocumentTemplate} against the given {@link IQueryEnvironment} and variables types.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param types
     *            the variables types
     * @param generation
     *            Generation
     */
    public void validate(DocumentTemplate documentTemplate, Generation generation,
            IReadOnlyQueryEnvironment queryEnvironment, Map<String, Set<IType>> types) {
        environment = QueryServices.getInstance().initAcceleoEnvironment(generation);
        booleanObjectType = new ClassType(queryEnvironment, Boolean.class);
        booleanType = new ClassType(queryEnvironment, boolean.class);
        stack.clear();
        stack.push(types);
        doSwitch(documentTemplate);
    }

    @Override
    public Void caseDocumentTemplate(DocumentTemplate documentTemplate) {
        for (Template template : documentTemplate.getHeaders()) {
            doSwitch(template);
        }
        if (documentTemplate.getBody() != null) {
            doSwitch(documentTemplate.getBody());
        }
        for (Template template : documentTemplate.getFooters()) {
            doSwitch(template);
        }

        return null;
    }

    @Override
    public Void caseTemplate(Template template) {
        for (AbstractConstruct construct : template.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseUserDoc(UserDoc userDoc) {
        final IValidationResult validationResult = AQL4Compat.validate(userDoc.getId(), stack.peek(), environment);
        final XWPFRun run = userDoc.getRuns().get(1);
        addValidationMessages(userDoc, run, validationResult);
        if (validationResult != null) { // FIXME : we might check why we may have a null validation result in AQL.
            checkUserDocSelectorTypes(userDoc, run, validationResult);
            for (AbstractConstruct construct : userDoc.getSubConstructs()) {
                doSwitch(construct);
            }
        }

        return null;
    }

    @Override
    public Void caseConditionnal(Conditionnal conditional) {
        final IValidationResult validationResult = AQL4Compat.validate(conditional.getQuery(), stack.peek(),
                environment);
        final XWPFRun run = conditional.getRuns().get(1);
        addValidationMessages(conditional, run, validationResult);
        if (validationResult != null) { // FIXME : we might check why we may have a null validation result in AQL.
            final Set<IType> types = validationResult.getPossibleTypes(conditional.getQuery().getAst());
            checkConditionalSelectorTypes(conditional, run, types);

            final Map<String, Set<IType>> thenVariables = new HashMap<>(stack.peek());
            thenVariables
                    .putAll(validationResult.getInferredVariableTypes(conditional.getQuery().getAst(), Boolean.TRUE));
            stack.push(thenVariables);
            try {
                for (AbstractConstruct construct : conditional.getSubConstructs()) {
                    doSwitch(construct);
                }
            } finally {
                stack.pop();
            }

            try {
                final Map<String, Set<IType>> elseVariables = new HashMap<>(stack.peek());
                elseVariables.putAll(
                        validationResult.getInferredVariableTypes(conditional.getQuery().getAst(), Boolean.FALSE));
                stack.push(elseVariables);
                if (conditional.getAlternative() != null) {
                    doSwitch(conditional.getAlternative());
                    // TODO remove this when the AST for Conditional will be fixed
                    final IValidationResult alternativeValidationResult = AQL4Compat
                            .validate(conditional.getAlternative().getQuery(), stack.peek(), environment);
                    elseVariables.putAll(alternativeValidationResult
                            .getInferredVariableTypes(conditional.getAlternative().getQuery().getAst(), Boolean.FALSE));
                }
                if (conditional.getElse() != null) {
                    doSwitch(conditional.getElse());
                }
            } finally {
                stack.pop();
            }
        }
        return null;
    }

    /**
     * Checks if the given types are assignable to {@link Boolean}.
     * 
     * @param conditional
     *            the {@link Conditionnal}
     * @param run
     *            the {@link XWPFRun}
     * @param selectorTypes
     *            the {@link Set} of {@link IType} for the selector
     */
    private void checkConditionalSelectorTypes(Conditionnal conditional, XWPFRun run, final Set<IType> selectorTypes) {
        if (!selectorTypes.isEmpty()) {
            boolean onlyBoolean = true;
            boolean onlyNotBoolean = true;
            for (IType type : selectorTypes) {
                final boolean assignableFrom = booleanObjectType.isAssignableFrom(type)
                    || booleanType.isAssignableFrom(type);
                onlyBoolean = onlyBoolean && assignableFrom;
                onlyNotBoolean = onlyNotBoolean && !assignableFrom;
                if (!onlyBoolean && !onlyNotBoolean) {
                    break;
                }
            }
            if (!onlyBoolean) {
                if (onlyNotBoolean) {
                    conditional.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                            String.format("The predicate never evaluates to a boolean type (%s).", selectorTypes),
                            run));
                } else {
                    conditional.getValidationMessages()
                            .add(new TemplateValidationMessage(ValidationMessageLevel.WARNING,
                                    String.format(
                                            "The predicate may evaluate to a value that is not a boolean type (%s).",
                                            selectorTypes),
                                    run));
                }
            }
        } else {
            conditional.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                    String.format("The predicate never evaluates to a boolean type (%s).", selectorTypes), run));
        }
    }

    /**
     * Checks if the given types are assignable to no a {@link ICollectionType}.
     * 
     * @param userDoc
     *            the {@link UserDoc}
     * @param run
     *            the {@link XWPFRun}
     * @param validationResult
     *            validation Result
     */
    private void checkUserDocSelectorTypes(UserDoc userDoc, XWPFRun run, IValidationResult validationResult) {
        final Set<IType> types = validationResult.getPossibleTypes(userDoc.getId().getAst());
        for (IType type : types) {
            if (type instanceof ICollectionType) {
                userDoc.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        String.format("The id type must not be a collection (%s).", type), run));
                break;
            }
        }
    }

    @Override
    public Void caseDefault(Default object) {
        for (AbstractConstruct construct : object.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseRepetition(Repetition repetition) {
        final IValidationResult validationResult = AQL4Compat.validate(repetition.getQuery(), stack.peek(),
                environment);
        final XWPFRun run = repetition.getRuns().get(1);
        addValidationMessages(repetition, run, validationResult);

        if (stack.peek().containsKey(repetition.getIterationVar())) {
            repetition.getValidationMessages()
                    .add(new TemplateValidationMessage(ValidationMessageLevel.WARNING,
                            String.format("The iteration variable mask an existing variable (%s).",
                                    repetition.getIterationVar()),
                            run));
        }
        if (validationResult != null) {
            final Set<IType> types = validationResult.getPossibleTypes(repetition.getQuery().getAst());
            for (IType type : types) {
                if (!(type instanceof ICollectionType)) {
                    repetition.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                            String.format("The iteration variable types must be collections (%s).", types), run));
                    break;
                }
            }
            final Set<IType> iteratorTypes = new LinkedHashSet<>();
            for (IType type : types) {
                if (type instanceof ICollectionType) {
                    iteratorTypes.add(((ICollectionType) type).getCollectionType());
                }
            }
            if (iteratorTypes.isEmpty()) {
                iteratorTypes
                        .add(new NothingType("No collection type for the iterator " + repetition.getIterationVar()));
            }
            final Map<String, Set<IType>> iterationVariables = new HashMap<>(stack.peek());
            iterationVariables.put(repetition.getIterationVar(), iteratorTypes);
            stack.push(iterationVariables);
            try {
                for (AbstractConstruct construct : repetition.getSubConstructs()) {
                    doSwitch(construct);
                }
            } finally {
                stack.pop();
            }
        }
        return null;
    }

    @Override
    public Void caseTableMerge(TableMerge tableMerge) {
        for (AbstractConstruct construct : tableMerge.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseQuery(Query query) {
        final IValidationResult validationResult = AQL4Compat.validate(query.getQuery(), stack.peek(), environment);
        final XWPFRun run = query.getStyleRun();
        addValidationMessages(query, run, validationResult);

        return null;
    }

    @Override
    public Void caseTable(Table table) {
        for (Row row : table.getRows()) {
            doSwitch(row);
        }

        return null;
    }

    @Override
    public Void caseRow(Row row) {
        for (Cell cell : row.getCells()) {
            doSwitch(cell);
        }

        return null;
    }

    @Override
    public Void caseCell(Cell cell) {
        doSwitch(cell.getTemplate());

        return null;
    }

    @Override
    public Void caseAbstractProviderClient(AbstractProviderClient providerClient) {
        final XWPFRun run = providerClient.getStyleRun();
        if (providerClient.getProvider() != null) {
            Map<String, Object> options = new LinkedHashMap<>(providerClient.getOptionValueMap().size());
            for (Entry<String, Object> entry : providerClient.getOptionValueMap()) {
                if (providerClient.getProvider().getOptionTypes().get(entry.getKey()) == OptionType.AQL_EXPRESSION) {
                    final AstResult astResult = (AstResult) entry.getValue();
                    final IValidationResult validationResult = AQL4Compat.validate(astResult, stack.peek(),
                            environment);
                    addValidationMessages(providerClient, run, validationResult);
                    if (validationResult != null) {
                        options.put(entry.getKey(), validationResult.getPossibleTypes(astResult.getAst()));
                    }
                } else {
                    options.put(entry.getKey(), entry.getValue());
                }
            }

            final List<ProviderValidationMessage> messages = providerClient.getProvider().validate(options);
            for (ProviderValidationMessage message : messages) {
                providerClient.getValidationMessages().add(new TemplateValidationMessage(message.getLevel(),
                        String.format("option %s: %s", message.getOptionName(), message.getMessage()), run));
            }
        } else {
            // This case seems to be checked at parsing time
        }
        return null;
    }

    /**
     * Adds {@link IValidationMessage} from the given {@link IValidationResult} to the given {@link AbstractConstruct}.
     * 
     * @param construct
     *            the {@link AbstractConstruct}
     * @param run
     *            the {@link XWPFRun}
     * @param validationResult
     *            the {@link IValidationResult}
     */
    private void addValidationMessages(AbstractConstruct construct, XWPFRun run, IValidationResult validationResult) {
        if (validationResult != null) {
            for (IValidationMessage message : validationResult.getMessages()) {
                construct.getValidationMessages()
                        .add(new TemplateValidationMessage(getLevel(message), message.getMessage(), run));
            }
        } else {
            construct.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.WARNING,
                    "Couldn't validate the expression", run));

        }
    }

    /**
     * Gets the {@link ValidationMessageLevel} to use for the given {@link IValidationMessage}.
     * 
     * @param message
     *            the {@link TemplateValidationMessage}
     * @return the {@link ValidationMessageLevel} to use for the given {@link IValidationMessage}
     */
    private ValidationMessageLevel getLevel(IValidationMessage message) {
        final ValidationMessageLevel res;

        switch (message.getLevel()) {
            case INFO:
                res = ValidationMessageLevel.INFO;
                break;

            case WARNING:
                res = ValidationMessageLevel.WARNING;
                break;

            case ERROR:
                res = ValidationMessageLevel.ERROR;
                break;

            default:
                res = ValidationMessageLevel.INFO;
                break;
        }

        return res;
    }

}
