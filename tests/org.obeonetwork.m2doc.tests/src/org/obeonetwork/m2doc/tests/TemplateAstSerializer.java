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
package org.obeonetwork.m2doc.tests;

import java.util.List;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.ast.Binding;
import org.eclipse.acceleo.query.ast.BooleanLiteral;
import org.eclipse.acceleo.query.ast.Call;
import org.eclipse.acceleo.query.ast.CallType;
import org.eclipse.acceleo.query.ast.CollectionTypeLiteral;
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
import org.eclipse.emf.ecore.EClassifier;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
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
 * Serialize a {@link Template}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplateAstSerializer extends TemplateSwitch<Void> {

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
        public Void caseConditional(org.eclipse.acceleo.query.ast.Conditional conditional) {
            builder.append("if (");
            doSwitch(conditional.getPredicate());
            builder.append(") then ");
            if (conditional.getTrueBranch() != null) {
                doSwitch(conditional.getTrueBranch());
            }
            builder.append(" else ");
            if (conditional.getFalseBranch() != null) {
                doSwitch(conditional.getFalseBranch());
            }
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
            if (!sequenceInExtensionLiteral.getValues().isEmpty()) {
                final StringBuilder previousBuilder = builder;
                builder = new StringBuilder();
                for (Expression value : sequenceInExtensionLiteral.getValues()) {
                    doSwitch(value);
                    builder.append(", ");
                }
                previousBuilder.append(builder.substring(0, builder.length() - 2));
                builder = previousBuilder;
            }
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
            if (!setInExtensionLiteral.getValues().isEmpty()) {
                final StringBuilder previousBuilder = builder;
                builder = new StringBuilder();
                for (Expression value : setInExtensionLiteral.getValues()) {
                    doSwitch(value);
                    builder.append(", ");
                }
                previousBuilder.append(builder.substring(0, builder.length() - 2));
                builder = previousBuilder;
            }
            builder.append("}");
            return null;
        }

        @Override
        public Void caseStringLiteral(StringLiteral stringLiteral) {
            builder.append("'");
            builder.append(stringLiteral.getValue().replaceAll("\n", "\\\\n").replaceAll("\r", "\\\\r").replaceAll("\t",
                    "\\\\t"));
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

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.acceleo.query.ast.util.AstSwitch#caseTypeLiteral(org.eclipse.acceleo.query.ast.TypeLiteral)
         */
        @Override
        public Void caseTypeLiteral(TypeLiteral object) {
            if (object.getValue() instanceof Class) {
                builder.append(((Class<?>) object.getValue()).getName());
            } else if (object.getValue() instanceof EClassifier) {
                builder.append(((EClassifier) object.getValue()).getName());
            }
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
     * Serializes the given {@link DocumentTemplate}.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @return the serialized {@link DocumentTemplate}
     */
    public String serialize(DocumentTemplate documentTemplate) {
        builder = new StringBuilder();
        indentation = "";

        doSwitch(documentTemplate);

        return builder.toString();
    }

    /**
     * Creates a new line with the right indentation.
     */
    protected void newLine() {
        builder.append("\n" + indentation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.obeonetwork.m2doc.template.util.TemplateSwitch#caseDocumentTemplate(org.obeonetwork.m2doc.template.DocumentTemplate)
     */
    @Override
    public Void caseDocumentTemplate(DocumentTemplate documentTemplate) {
        newLine();
        builder.append("=== HEADER ===");
        newLine();
        for (Template header : documentTemplate.getHeaders()) {
            doSwitch(header);
        }
        newLine();
        builder.append("=== BODY ===");
        newLine();
        doSwitch(documentTemplate.getBody());
        newLine();
        builder.append("=== FOOTER ===");
        newLine();
        for (Template footer : documentTemplate.getFooters()) {
            doSwitch(footer);
        }

        return null;
    }

    @Override
    public Void caseTemplate(Template template) {
        builder.append("template");
        doSwitch(template.getBody());
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
    public Void caseComment(Comment comment) {
        builder.append(String.format("comment: %s", comment.getText()));
        return null;
    }

    @Override
    public Void caseQuery(Query query) {
        builder.append(String.format("[query: %s]", querySerializer.serialize(query.getQuery().getAst())));

        return null;
    }

    @Override
    public Void caseRepetition(Repetition repetition) {
        newLine();
        builder.append("for ");
        builder.append(repetition.getIterationVar());
        builder.append(" | ");
        builder.append(querySerializer.serialize(repetition.getQuery().getAst()));
        builder.append(" do");
        doSwitch(repetition.getBody());
        newLine();
        builder.append("endfor");
        newLine();
        return null;

    }

    @Override
    public Void caseLet(org.obeonetwork.m2doc.template.Let let) {
        newLine();
        builder.append("let ");
        builder.append(let.getName());
        builder.append(" = ");
        builder.append(querySerializer.serialize(let.getValue().getAst()));
        builder.append(" in");
        doSwitch(let.getBody());
        newLine();
        builder.append("endfor");
        newLine();
        return null;
    }

    @Override
    public Void caseUserDoc(UserDoc userDoc) {
        newLine();
        builder.append("userdoc ");
        builder.append(querySerializer.serialize(userDoc.getId().getAst()));
        builder.append(" do");
        indent();
        newLine();
        doSwitch(userDoc.getBody());
        deindent();
        newLine();
        builder.append("enduserdoc");
        newLine();
        return null;
    }

    @Override
    public Void caseConditional(Conditional conditional) {
        newLine();
        builder.append("if ");
        builder.append(querySerializer.serialize(conditional.getCondition().getAst()));
        builder.append(" then");
        doSwitch(conditional.getThen());
        if (conditional.getElse() != null) {
            newLine();
            builder.append("else");
            doSwitch(conditional.getElse());
        }
        newLine();
        builder.append("endif");
        newLine();

        return null;
    }

    @Override
    public Void caseBlock(Block block) {
        indent();
        for (IConstruct construct : block.getStatements()) {
            newLine();
            doSwitch(construct);
        }
        deindent();

        return null;
    }

    @Override
    public Void caseTable(Table table) {
        builder.append("table");
        indent();
        newLine();
        for (Row row : table.getRows()) {
            doSwitch(row);
            newLine();
        }
        deindent();

        return null;
    }

    @Override
    public Void caseRow(Row row) {
        builder.append("row");
        indent();
        newLine();
        for (Cell cell : row.getCells()) {
            doSwitch(cell);
            newLine();
        }
        deindent();

        return null;
    }

    @Override
    public Void caseCell(Cell cell) {
        doSwitch(cell.getTemplate());

        return null;
    }

    @Override
    public Void caseBookmark(Bookmark bookmark) {
        newLine();
        builder.append("bookmark ");
        builder.append(querySerializer.serialize(bookmark.getName().getAst()));
        doSwitch(bookmark.getBody());
        newLine();
        builder.append("endbookmark");
        newLine();
        return null;
    }

    @Override
    public Void caseLink(Link link) {
        newLine();
        builder.append("link ");
        builder.append(querySerializer.serialize(link.getName().getAst()));
        builder.append(" ");
        builder.append(querySerializer.serialize(link.getText().getAst()));
        builder.append(" endlink");
        newLine();
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.obeonetwork.m2doc.template.util.TemplateSwitch#caseContentControl(org.obeonetwork.m2doc.template.ContentControl)
     */
    @Override
    public Void caseContentControl(ContentControl contentControl) {
        newLine();
        builder.append("content control");
        newLine();
        return null;
    }

}
