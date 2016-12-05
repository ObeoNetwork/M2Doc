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
package org.obeonetwork.m2doc.test;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.ast.Binding;
import org.eclipse.acceleo.query.ast.BooleanLiteral;
import org.eclipse.acceleo.query.ast.Call;
import org.eclipse.acceleo.query.ast.CallType;
import org.eclipse.acceleo.query.ast.CollectionTypeLiteral;
import org.eclipse.acceleo.query.ast.Conditional;
import org.eclipse.acceleo.query.ast.EnumLiteral;
import org.eclipse.acceleo.query.ast.Error;
import org.eclipse.acceleo.query.ast.Expression;
import org.eclipse.acceleo.query.ast.IntegerLiteral;
import org.eclipse.acceleo.query.ast.Lambda;
import org.eclipse.acceleo.query.ast.Let;
import org.eclipse.acceleo.query.ast.NullLiteral;
import org.eclipse.acceleo.query.ast.RealLiteral;
import org.eclipse.acceleo.query.ast.SequenceInExtensionLiteral;
import org.eclipse.acceleo.query.ast.SetInExtensionLiteral;
import org.eclipse.acceleo.query.ast.StringLiteral;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.ast.TypeSetLiteral;
import org.eclipse.acceleo.query.ast.VarRef;
import org.eclipse.acceleo.query.ast.VariableDeclaration;
import org.eclipse.acceleo.query.ast.util.AstSwitch;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.TableClient;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;

/**
 * Serialize a {@link Template}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplateAstSerializer extends TemplateSwitch<Void> {

    /**
     * Expression error message.
     */
    private static final String EXPRESSION_ERROR = "*** expression error ****";

    /**
     * Serialize a {@link Expression}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class QueryAstSerializer extends AstSwitch<Void> {

        /**
         * The {@link StringBuilder} used to serialize.
         */
        private StringBuilder builder;

        /**
         * Serializes the given {@link Expression}.
         * 
         * @param expression
         *            the {@link Expression}
         * @return the serialized {@link Expression}
         */
        public String serialize(Expression expression) {
            builder = new StringBuilder();

            doSwitch(expression);

            return builder.toString();
        }

        @Override
        public Void caseBinding(Binding binding) {
            builder.append(binding.getName());
            if (binding.getType() != null) {
                builder.append(" : ");
                builder.append(doSwitch(binding.getType()));
            }
            builder.append(" = ");
            builder.append(doSwitch(binding.getValue()));
            return null;
        }

        @Override
        public Void caseBooleanLiteral(BooleanLiteral booleanLiteral) {
            builder.append(booleanLiteral.isValue());
            return null;
        }

        @Override
        public Void caseCall(Call call) {
            if (call.getType() == CallType.COLLECTIONCALL) {
                builder.append("->");
            } else {
                builder.append(".");
            }
            builder.append(call.getServiceName());
            builder.append("(");
            final StringBuilder previousBuilder = builder;
            builder = new StringBuilder();
            for (Expression argument : call.getArguments()) {
                doSwitch(argument);
                builder.append(", ");
            }
            if (builder.length() > 0) {
                previousBuilder.append(builder.substring(0, builder.length() - 2));
            }
            builder = previousBuilder;
            builder.append(")");
            return null;
        }

        @Override
        public Void caseCollectionTypeLiteral(CollectionTypeLiteral collectionTypeLiteral) {
            if (collectionTypeLiteral.getValue() == List.class) {
                builder.append("Sequence(");
            } else if (collectionTypeLiteral.getValue() == Set.class) {
                builder.append("OrderedSet(");
            } else {
                builder.append("***invalid type of collection ***(");
            }
            doSwitch(collectionTypeLiteral.getElementType());
            builder.append("");
            return null;
        }

        @Override
        public Void caseConditional(Conditional conditional) {
            builder.append("if (");
            doSwitch(conditional.getPredicate());
            builder.append(") then ");
            doSwitch(conditional.getTrueBranch());
            builder.append(" else ");
            doSwitch(conditional.getFalseBranch());
            builder.append(" endif ");
            return null;
        }

        @Override
        public Void caseEnumLiteral(EnumLiteral enumLiteral) {
            builder.append(enumLiteral.getLiteral().getEEnum().getEPackage().getName());
            builder.append("::");
            builder.append(enumLiteral.getLiteral().getEEnum().getName());
            builder.append("::");
            builder.append(enumLiteral.getLiteral().getName());
            return null;
        }

        @Override
        public Void caseError(Error error) {
            builder.append("***ERROR***");
            return null;
        }

        @Override
        public Void caseIntegerLiteral(IntegerLiteral object) {
            builder.append(object.getValue());
            return null;
        }

        @Override
        public Void caseLambda(Lambda lambda) {
            doSwitch(lambda.getParameters().get(0));
            builder.append(" | ");
            doSwitch(lambda.getExpression());
            return null;
        }

        @Override
        public Void caseLet(Let let) {
            builder.append("let ");
            final StringBuilder previousBuilder = builder;
            builder = new StringBuilder();
            for (Binding binding : let.getBindings()) {
                doSwitch(binding);
                builder.append(", ");
            }
            previousBuilder.append(builder.substring(0, builder.length() - 2));
            builder.append(" in ");
            doSwitch(let.getBody());
            return super.caseLet(let);
        }

        @Override
        public Void caseNullLiteral(NullLiteral nullLiteral) {
            builder.append("null");
            return null;
        }

        @Override
        public Void caseSequenceInExtensionLiteral(SequenceInExtensionLiteral sequenceInExtensionLiteral) {
            builder.append("Sequence{");
            final StringBuilder previousBuilder = builder;
            builder = new StringBuilder();
            for (Expression value : sequenceInExtensionLiteral.getValues()) {
                doSwitch(value);
                builder.append(", ");
            }
            previousBuilder.append(builder.substring(0, builder.length() - 2));
            builder.append("}");
            return null;
        }

        @Override
        public Void caseRealLiteral(RealLiteral realLiteral) {
            builder.append(realLiteral.getValue());
            return null;
        }

        @Override
        public Void caseSetInExtensionLiteral(SetInExtensionLiteral setInExtensionLiteral) {
            builder.append("OrderedSet{");
            final StringBuilder previousBuilder = builder;
            builder = new StringBuilder();
            for (Expression value : setInExtensionLiteral.getValues()) {
                doSwitch(value);
                builder.append(", ");
            }
            previousBuilder.append(builder.substring(0, builder.length() - 2));
            builder.append("}");
            return null;
        }

        @Override
        public Void caseStringLiteral(StringLiteral stringLiteral) {
            builder.append("'");
            builder.append(stringLiteral.getValue());
            builder.append("'");
            return null;
        }

        @Override
        public Void caseTypeSetLiteral(TypeSetLiteral typeSetLiteral) {
            builder.append("{");
            final StringBuilder previousBuilder = builder;
            builder = new StringBuilder();
            for (TypeLiteral type : typeSetLiteral.getTypes()) {
                doSwitch(type);
                builder.append(" | ");
            }
            previousBuilder.append(builder.substring(0, builder.length() - 3));
            builder.append("}");
            return null;
        }

        @Override
        public Void caseVariableDeclaration(VariableDeclaration variableDeclaration) {
            builder.append(variableDeclaration.getName());
            if (variableDeclaration.getType() != null) {
                builder.append(" : ");
                doSwitch(variableDeclaration.getType());
            }
            builder.append(" = ");
            doSwitch(variableDeclaration.getExpression());
            return null;
        }

        @Override
        public Void caseVarRef(VarRef varRef) {
            builder.append(varRef.getVariableName());
            return null;
        }

    }

    /**
     * The {@link StringBuilder} used to serialize.
     */
    private StringBuilder builder;

    /**
     * The {@link QueryAstSerializer}.
     */
    private QueryAstSerializer querySerializer = new QueryAstSerializer();

    /**
     * Current indentation.
     */
    private String indentation;

    /**
     * Increases indentation.
     */
    protected void indent() {
        indentation = indentation + "  ";
    }

    /**
     * Decreases indentation.
     */
    protected void deindent() {
        indentation = indentation.substring(0, indentation.length() - 2);
    }

    /**
     * Serializes the given {@link Template}.
     * 
     * @param template
     *            the {@link Template}
     * @return the serialized {@link Template}
     */
    public String serialize(Template template) {
        builder = new StringBuilder();
        indentation = "";

        doSwitch(template);

        return builder.toString();
    }

    /**
     * Creates a new line with the right indentation.
     */
    protected void newLine() {
        builder.append("\n" + indentation);
    }

    @Override
    public Void caseTemplate(Template template) {
        builder.append(String.format("template %s", template.getTemplateName()));
        indent();
        newLine();
        for (AbstractConstruct construct : template.getSubConstructs()) {
            doSwitch(construct);
        }
        deindent();
        return null;
    }

    @Override
    public Void caseStaticFragment(StaticFragment staticFragment) {
        for (XWPFRun run : staticFragment.getRuns()) {
            builder.append(run.text().replaceAll("\r\n", "\n" + indentation).replaceAll("\r", "\n" + indentation));
        }
        return null;
    }

    @Override
    public Void caseQuery(Query query) {
        if (query.getQuery() != null) {
            builder.append(String.format("[query: %s | %s]", querySerializer.serialize(query.getQuery().getAst()),
                    query.getBehavior()));
        } else {
            builder.append(String.format("[query: %s | %s]", EXPRESSION_ERROR, query.getBehavior().values()));
        }

        return null;
    }

    @Override
    public Void caseRepetition(Repetition repetition) {
        newLine();
        builder.append("for ");
        builder.append(repetition.getIterationVar());
        builder.append(" | ");
        if (repetition.getQuery() != null) {
            builder.append(querySerializer.serialize(repetition.getQuery().getAst()));
        } else {
            builder.append(EXPRESSION_ERROR);
        }
        builder.append(" do");
        indent();
        newLine();
        for (AbstractConstruct subConstruct : repetition.getSubConstructs()) {
            doSwitch(subConstruct);
        }
        deindent();
        newLine();
        builder.append("endfor");
        newLine();
        return null;

    }

    @Override
    public Void caseUserDoc(UserDoc userDoc) {
        newLine();
        builder.append("userdoc ");
        if (userDoc.getId() != null) {
            builder.append(querySerializer.serialize(userDoc.getId().getAst()));
        } else {
            builder.append(EXPRESSION_ERROR);
        }
        builder.append(" do");
        indent();
        newLine();
        for (AbstractConstruct subConstruct : userDoc.getSubConstructs()) {
            doSwitch(subConstruct);
        }
        deindent();
        newLine();
        builder.append("enduserdoc");
        newLine();
        return null;
    }

    @Override
    public Void caseConditionnal(Conditionnal conditionnal) {
        newLine();
        builder.append("if ");
        if (conditionnal.getQuery() != null) {
            builder.append(querySerializer.serialize(conditionnal.getQuery().getAst()));
        } else {
            builder.append(EXPRESSION_ERROR);
        }
        builder.append(" then");
        indent();
        newLine();
        for (AbstractConstruct subConstruct : conditionnal.getSubConstructs()) {
            doSwitch(subConstruct);
        }
        deindent();
        if (conditionnal.getAlternative() != null) {
            newLine();
            builder.append("else");
            indent();
            newLine();
            doSwitch(conditionnal.getAlternative());
            deindent();
        }
        if (conditionnal.getElse() != null) {
            newLine();
            builder.append("else");
            indent();
            newLine();
            doSwitch(conditionnal.getElse());
            deindent();
        }
        newLine();
        builder.append("endif");
        newLine();
        return null;
    }

    @Override
    public Void caseTable(Table object) {
        // TODO
        return null;
    }

    @Override
    public Void caseImage(Image image) {
        newLine();
        builder.append("image ");
        builder.append(image.getFileName());
        builder.append(" provider ");
        builder.append(image.getProvider().getClass().getName());
        builder.append(" height ");
        builder.append(image.getHeight());
        builder.append(" width ");
        builder.append(image.getWidth());
        builder.append(" legend ");
        builder.append(image.getLegend());
        builder.append(" legendpos ");
        builder.append(image.getLegendPOS().getName());
        for (Entry<String, Object> entry : image.getOptionValueMap().entrySet()) {
            // TODO the value will not be consistent between serializations...
            builder.append(String.format(" %s %s ", entry.getKey(), entry.getValue()));
        }
        newLine();
        return null;
    }

    @Override
    public Void caseRepresentation(Representation representation) {
        newLine();
        builder.append("representation ");
        builder.append(representation.getRepresentationId());
        builder.append(" title ");
        builder.append(representation.getRepresentationTitle());
        builder.append(" provider ");
        builder.append(representation.getProvider().getClass().getName());
        builder.append(" height ");
        builder.append(representation.getHeight());
        builder.append(" width ");
        builder.append(representation.getWidth());
        builder.append(" legend ");
        builder.append(representation.getLegend());
        builder.append(" legendpos ");
        builder.append(representation.getLegendPOS().getName());
        for (Entry<String, Object> entry : representation.getOptionValueMap().entrySet()) {
            // TODO the value will not be consistent between serializations...
            builder.append(String.format(" %s %s ", entry.getKey(), entry.getValue()));
        }
        newLine();
        return null;
    }

    @Override
    public Void caseTableClient(TableClient tableClient) {
        newLine();
        builder.append("table client ");
        builder.append(tableClient.getProvider().getClass().getName());
        for (Entry<String, Object> entry : tableClient.getOptionValueMap().entrySet()) {
            // TODO the value will not be consistent between serializations...
            builder.append(String.format(" %s %s ", entry.getKey(), entry.getValue()));
        }
        newLine();
        return null;
    }

    @Override
    public Void caseBookmark(Bookmark bookmark) {
        newLine();
        builder.append("bookmark ");
        if (bookmark.getName() != null) {
            builder.append(querySerializer.serialize(bookmark.getName().getAst()));
        } else {
            builder.append(EXPRESSION_ERROR);
        }
        indent();
        newLine();
        for (AbstractConstruct subConstruct : bookmark.getSubConstructs()) {
            doSwitch(subConstruct);
        }
        deindent();
        newLine();
        builder.append("endbookmark");
        newLine();
        return null;
    }

    @Override
    public Void caseLink(Link link) {
        newLine();
        builder.append("link ");
        if (link.getName() != null) {
            builder.append(querySerializer.serialize(link.getName().getAst()));
        } else {
            builder.append(EXPRESSION_ERROR);
        }
        builder.append(" ");
        if (link.getText() != null) {
            builder.append(querySerializer.serialize(link.getText().getAst()));
        } else {
            builder.append(EXPRESSION_ERROR);
        }
        builder.append(" endlink");
        newLine();
        return null;
    }

}
