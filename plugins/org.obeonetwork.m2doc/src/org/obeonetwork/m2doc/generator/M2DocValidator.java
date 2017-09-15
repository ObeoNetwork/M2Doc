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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationMessage;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.ICollectionType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.acceleo.query.validation.type.NothingType;
import org.eclipse.emf.common.util.Diagnostic;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;

/**
 * Validates {@link DocumentTemplate}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public class M2DocValidator extends TemplateSwitch<ValidationMessageLevel> {

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
    private final Stack<Map<String, Set<IType>>> stack = new Stack<Map<String, Set<IType>>>();

    /**
     * AQL {@link AstValidator}.
     */
    private AstValidator aqlValidator;

    /**
     * Validates the given {@link DocumentTemplate} against the given {@link IQueryEnvironment} and variables types.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @return the {@link ValidationMessageLevel}
     */
    public ValidationMessageLevel validate(DocumentTemplate documentTemplate,
            IReadOnlyQueryEnvironment queryEnvironment) {
        aqlValidator = new AstValidator(new ValidationServices(queryEnvironment));
        final TemplateCustomProperties templateProperties = new TemplateCustomProperties(
                documentTemplate.getDocument());
        Map<String, Set<IType>> types = new LinkedHashMap<String, Set<IType>>();
        for (Entry<String, String> entry : templateProperties.getVariables().entrySet()) {
            final Set<IType> variableTypes = templateProperties.getVariableTypes(aqlValidator, queryEnvironment,
                    entry.getValue());
            types.put(entry.getKey(), variableTypes);
        }
        booleanObjectType = new ClassType(queryEnvironment, Boolean.class);
        booleanType = new ClassType(queryEnvironment, boolean.class);
        stack.clear();
        stack.push(types);

        final ValidationMessageLevel result;
        try {
            result = doSwitch(documentTemplate);
        } finally {
            stack.pop();
        }

        return result;
    }

    @Override
    public ValidationMessageLevel caseDocumentTemplate(DocumentTemplate documentTemplate) {
        ValidationMessageLevel headerLevel = ValidationMessageLevel.OK;
        for (Template header : documentTemplate.getHeaders()) {
            headerLevel = ValidationMessageLevel.updateLevel(headerLevel, doSwitch(header));
        }
        final ValidationMessageLevel bodyLevel = doSwitch(documentTemplate.getBody());
        ValidationMessageLevel footerLevel = ValidationMessageLevel.OK;
        for (Template footer : documentTemplate.getFooters()) {
            footerLevel = ValidationMessageLevel.updateLevel(footerLevel, doSwitch(footer));
        }

        return ValidationMessageLevel.updateLevel(headerLevel, bodyLevel, footerLevel);
    }

    @Override
    public ValidationMessageLevel caseTemplate(Template template) {
        final ValidationMessageLevel parsingLevel = getHighestMessageLevel(template);
        final ValidationMessageLevel bodyLevel = doSwitch(template.getBody());

        return ValidationMessageLevel.updateLevel(parsingLevel, bodyLevel);
    }

    @Override
    public ValidationMessageLevel caseBookmark(Bookmark bookmark) {
        if (bookmark.getName().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            final IValidationResult validationResult = aqlValidator.validate(stack.peek(), bookmark.getName());
            final XWPFRun run = bookmark.getRuns().get(1);
            addValidationMessages(bookmark, run, validationResult);
        }

        final ValidationMessageLevel bodyLevel = doSwitch(bookmark.getBody());

        return ValidationMessageLevel.updateLevel(getHighestMessageLevel(bookmark), bodyLevel);
    }

    @Override
    public ValidationMessageLevel caseLink(Link link) {
        if (link.getName().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            final IValidationResult nameValidationResult = aqlValidator.validate(stack.peek(), link.getName());
            final XWPFRun run = link.getRuns().get(1);
            addValidationMessages(link, run, nameValidationResult);
        }

        if (link.getText().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            final IValidationResult textValidationResult = aqlValidator.validate(stack.peek(), link.getText());
            final XWPFRun run = link.getRuns().get(1);
            addValidationMessages(link, run, textValidationResult);
        }

        return getHighestMessageLevel(link);
    }

    @Override
    public ValidationMessageLevel caseUserDoc(UserDoc userDoc) {
        final ValidationMessageLevel idLevel;
        if (userDoc.getId().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            final IValidationResult validationResult = aqlValidator.validate(stack.peek(), userDoc.getId());
            final XWPFRun run = userDoc.getRuns().get(1);
            addValidationMessages(userDoc, run, validationResult);
            idLevel = checkUserDocIdTypes(userDoc, run, validationResult);
        } else {
            idLevel = ValidationMessageLevel.ERROR;
        }

        return ValidationMessageLevel.updateLevel(getHighestMessageLevel(userDoc), idLevel,
                doSwitch(userDoc.getBody()));
    }

    @Override
    public ValidationMessageLevel caseBlock(Block block) {
        ValidationMessageLevel res = getHighestMessageLevel(block);

        for (IConstruct construct : block.getStatements()) {
            res = ValidationMessageLevel.updateLevel(res, doSwitch(construct));
        }

        return res;
    }

    @Override
    public ValidationMessageLevel caseStaticFragment(StaticFragment staticFragment) {
        return ValidationMessageLevel.OK;
    }

    @Override
    public ValidationMessageLevel caseConditional(Conditional conditional) {
        final IValidationResult validationResult = aqlValidator.validate(stack.peek(), conditional.getCondition());
        final Set<IType> types = validationResult.getPossibleTypes(conditional.getCondition().getAst());
        final ValidationMessageLevel conditionLevel;
        if (conditional.getCondition().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            final XWPFRun run = conditional.getRuns().get(1);
            addValidationMessages(conditional, run, validationResult);
            conditionLevel = checkConditionalConditionTypes(conditional, run, types);
        } else {
            conditionLevel = ValidationMessageLevel.ERROR;
        }

        final Map<String, Set<IType>> thenVariables = new HashMap<String, Set<IType>>(stack.peek());
        thenVariables
                .putAll(validationResult.getInferredVariableTypes(conditional.getCondition().getAst(), Boolean.TRUE));
        stack.push(thenVariables);
        final ValidationMessageLevel thenLevel;
        try {
            thenLevel = doSwitch(conditional.getThen());
        } finally {
            stack.pop();
        }
        final ValidationMessageLevel elseLevel;
        if (conditional.getElse() != null) {
            final Map<String, Set<IType>> elseVariables = new HashMap<String, Set<IType>>(stack.peek());
            elseVariables.putAll(
                    validationResult.getInferredVariableTypes(conditional.getCondition().getAst(), Boolean.FALSE));
            stack.push(elseVariables);
            try {
                elseLevel = doSwitch(conditional.getElse());
            } finally {
                stack.pop();
            }
        } else {
            elseLevel = ValidationMessageLevel.OK;
        }

        return ValidationMessageLevel.updateLevel(getHighestMessageLevel(conditional), conditionLevel, thenLevel,
                elseLevel);
    }

    /**
     * Checks if the given types are assignable to {@link Boolean}.
     * 
     * @param conditional
     *            the {@link Conditional}
     * @param run
     *            the {@link XWPFRun}
     * @param conditionTypes
     *            the {@link Set} of {@link IType} for the {@link Conditional#getCondition() condition}
     * @return the {@link ValidationMessageLevel}
     */
    private ValidationMessageLevel checkConditionalConditionTypes(Conditional conditional, XWPFRun run,
            final Set<IType> conditionTypes) {
        final ValidationMessageLevel res;

        if (!conditionTypes.isEmpty()) {
            boolean onlyBoolean = true;
            boolean onlyNotBoolean = true;
            for (IType type : conditionTypes) {
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
                    res = ValidationMessageLevel.ERROR;
                    conditional.getValidationMessages().add(new TemplateValidationMessage(res,
                            String.format("The predicate never evaluates to a boolean type (%s).", conditionTypes),
                            run));
                } else {
                    res = ValidationMessageLevel.WARNING;
                    conditional.getValidationMessages()
                            .add(new TemplateValidationMessage(res,
                                    String.format(
                                            "The predicate may evaluate to a value that is not a boolean type (%s).",
                                            conditionTypes),
                                    run));
                }
            } else {
                res = ValidationMessageLevel.OK;
            }
        } else {
            res = ValidationMessageLevel.ERROR;
            conditional.getValidationMessages().add(new TemplateValidationMessage(res,
                    String.format("The predicate never evaluates to a boolean type (%s).", conditionTypes), run));
        }

        return res;
    }

    /**
     * Checks if the given types are assignable to no a {@link ICollectionType}.
     * 
     * @param userDoc
     *            the {@link UserDoc}
     * @param run
     *            the {@link XWPFRun}
     * @param validationResult
     *            validation Result for {@link UserDoc#getId() id}
     * @return the {@link ValidationMessageLevel}
     */
    private ValidationMessageLevel checkUserDocIdTypes(UserDoc userDoc, XWPFRun run,
            IValidationResult validationResult) {
        ValidationMessageLevel res = ValidationMessageLevel.OK;

        final Set<IType> types = validationResult.getPossibleTypes(userDoc.getId().getAst());
        for (IType type : types) {
            if (type instanceof ICollectionType) {
                userDoc.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        String.format("The id type must not be a collection (%s).", type), run));
                res = ValidationMessageLevel.ERROR;
                break;
            }
        }

        return res;
    }

    @Override
    public ValidationMessageLevel caseRepetition(Repetition repetition) {

        final IValidationResult validationResult = aqlValidator.validate(stack.peek(), repetition.getQuery());
        final Set<IType> types = validationResult.getPossibleTypes(repetition.getQuery().getAst());
        final XWPFRun run = repetition.getRuns().get(1);
        ValidationMessageLevel iteratorLevel;
        if (repetition.getQuery().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            addValidationMessages(repetition, run, validationResult);
            iteratorLevel = validateRepetitionQueryType(repetition, run, types);
        } else {
            iteratorLevel = ValidationMessageLevel.ERROR;
        }
        if (stack.peek().containsKey(repetition.getIterationVar())) {
            iteratorLevel = ValidationMessageLevel.updateLevel(iteratorLevel, ValidationMessageLevel.WARNING);
            repetition.getValidationMessages()
                    .add(new TemplateValidationMessage(ValidationMessageLevel.WARNING,
                            String.format("The iteration variable mask an existing variable (%s).",
                                    repetition.getIterationVar()),
                            run));
        }

        final Set<IType> iteratorTypes = new LinkedHashSet<IType>();
        for (IType type : types) {
            if (type instanceof ICollectionType) {
                iteratorTypes.add(((ICollectionType) type).getCollectionType());
            }
        }
        if (iteratorTypes.isEmpty()) {
            iteratorTypes.add(new NothingType("No collection type for the iterator " + repetition.getIterationVar()));
        }
        final Map<String, Set<IType>> iterationVariables = new HashMap<String, Set<IType>>(stack.peek());
        iterationVariables.put(repetition.getIterationVar(), iteratorTypes);
        stack.push(iterationVariables);
        final ValidationMessageLevel bodyLevel;
        try {
            bodyLevel = doSwitch(repetition.getBody());
        } finally {
            stack.pop();
        }

        return ValidationMessageLevel.updateLevel(getHighestMessageLevel(repetition), iteratorLevel, bodyLevel);
    }

    @Override
    public ValidationMessageLevel caseLet(Let let) {
        final IValidationResult validationResult = aqlValidator.validate(stack.peek(), let.getValue());
        final Set<IType> types = validationResult.getPossibleTypes(let.getValue().getAst());

        final XWPFRun run = let.getRuns().get(1);
        ValidationMessageLevel variableLevel;
        if (let.getValue().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            addValidationMessages(let, run, validationResult);
            variableLevel = ValidationMessageLevel.OK;
        } else {
            variableLevel = ValidationMessageLevel.ERROR;
        }
        if (stack.peek().containsKey(let.getName())) {
            variableLevel = ValidationMessageLevel.updateLevel(variableLevel, ValidationMessageLevel.WARNING);
            let.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.WARNING,
                    String.format("The variable mask an existing variable (%s).", let.getName()), run));
        }

        final Map<String, Set<IType>> iterationVariables = new HashMap<String, Set<IType>>(stack.peek());
        iterationVariables.put(let.getName(), types);
        stack.push(iterationVariables);
        final ValidationMessageLevel bodyLevel;
        try {
            bodyLevel = doSwitch(let.getBody());
        } finally {
            stack.pop();
        }

        return ValidationMessageLevel.updateLevel(getHighestMessageLevel(let), variableLevel, bodyLevel);
    }

    /**
     * Validates the {@link Repetition#getQuery() query}.
     * 
     * @param repetition
     *            the {@link Repetition}
     * @param run
     *            the {@link XWPFRun}
     * @param types
     *            the {@link Set} of {@link IType} for the {@link Repetition#getQuery() query}
     * @return the {@link ValidationMessageLevel}
     */
    private ValidationMessageLevel validateRepetitionQueryType(Repetition repetition, final XWPFRun run,
            final Set<IType> types) {
        ValidationMessageLevel res = ValidationMessageLevel.OK;

        for (IType type : types) {
            if (!(type instanceof ICollectionType)) {
                res = ValidationMessageLevel.ERROR;
                repetition.getValidationMessages().add(new TemplateValidationMessage(res,
                        String.format("The iteration variable types must be collections (%s).", types), run));
                break;
            }
        }

        return res;
    }

    @Override
    public ValidationMessageLevel caseQuery(Query query) {
        if (query.getQuery().getDiagnostic().getSeverity() != Diagnostic.ERROR) {
            final IValidationResult validationResult = aqlValidator.validate(stack.peek(), query.getQuery());
            final XWPFRun run = query.getStyleRun();
            addValidationMessages(query, run, validationResult);
        }

        return getHighestMessageLevel(query);
    }

    @Override
    public ValidationMessageLevel caseComment(Comment comment) {
        // noting to do here
        return ValidationMessageLevel.OK;
    }

    @Override
    public ValidationMessageLevel caseTable(Table table) {
        ValidationMessageLevel res = ValidationMessageLevel.OK;

        for (Row row : table.getRows()) {
            res = ValidationMessageLevel.updateLevel(res, doSwitch(row));
        }

        return res;
    }

    @Override
    public ValidationMessageLevel caseRow(Row row) {
        ValidationMessageLevel res = ValidationMessageLevel.OK;

        for (Cell cell : row.getCells()) {
            res = ValidationMessageLevel.updateLevel(res, doSwitch(cell));
        }

        return res;
    }

    @Override
    public ValidationMessageLevel caseCell(Cell cell) {
        return doSwitch(cell.getTemplate());
    }

    @Override
    public ValidationMessageLevel caseContentControl(ContentControl contentControl) {
        // noting to do here
        return ValidationMessageLevel.OK;
    }

    /**
     * Adds {@link IValidationMessage} from the given {@link IValidationResult} to the given {@link IConstruct}.
     * 
     * @param construct
     *            the {@link IConstruct}
     * @param run
     *            the {@link XWPFRun}
     * @param validationResult
     *            the {@link IValidationResult}
     */
    private void addValidationMessages(IConstruct construct, XWPFRun run, IValidationResult validationResult) {
        for (IValidationMessage message : validationResult.getMessages()) {
            final ValidationMessageLevel level = getLevel(message);
            construct.getValidationMessages().add(new TemplateValidationMessage(level, message.getMessage(), run));
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
                res = ValidationMessageLevel.OK;
                break;
        }

        return res;
    }

    /**
     * Gets the highest {@link ValidationMessageLevel} for the given {@link IConstruct}.
     * 
     * @param construct
     *            the {@link IConstruct}
     * @return the highest {@link ValidationMessageLevel} for the given {@link IConstruct}
     */
    protected ValidationMessageLevel getHighestMessageLevel(IConstruct construct) {
        ValidationMessageLevel res = ValidationMessageLevel.OK;

        for (TemplateValidationMessage message : construct.getValidationMessages()) {
            res = ValidationMessageLevel.updateLevel(res, message.getLevel());
        }

        return res;
    }

}
