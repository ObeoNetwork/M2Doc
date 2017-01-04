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
package org.obeonetwork.m2doc.generator.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.QueryParsing;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.acceleo.query.validation.type.SequenceType;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.api.QueryServices;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.TemplateValidator;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.TableMerge;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.test.M2DocTestUtils;

import static org.junit.Assert.assertEquals;
import static org.obeonetwork.m2doc.test.M2DocTestUtils.assertTemplateValidationMessage;

/**
 * Test {@link TemplateValidator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplateValidatorTests {

    @Test
    public void documentTemplateHeader() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Template header = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        header.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        header.getBody().getStatements().add(query);
        final Template bodyTemplate = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        bodyTemplate.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(bodyTemplate);
        documentTemplate.getHeaders().add(header);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

    @Test
    public void documentTemplateBody() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(query);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        Generation generation = GenconfFactory.eINSTANCE.createGeneration();
        validator.validate(documentTemplate, queryEnvironment,
                QueryServices.getInstance().getTypes(queryEnvironment, generation));

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

    @Test
    public void documentTemplateFooter() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Template footer = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        footer.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        footer.getBody().getStatements().add(query);
        final Template bodyTemplate = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        bodyTemplate.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(bodyTemplate);
        documentTemplate.getFooters().add(footer);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

    @Test
    public void templateSubConstruct() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(query);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

    @Test
    public void conditionalConditionNotBoolean() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Conditional conditional = TemplatePackage.eINSTANCE.getTemplateFactory().createConditional();
        conditional.setCondition(engine.build("self"));
        final Block thenCompound = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        conditional.setThen(thenCompound);
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(conditional);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new ClassType(queryEnvironment, String.class));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, conditional.getValidationMessages().size());
        assertTemplateValidationMessage(conditional.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The predicate never evaluates to a boolean type ([java.lang.String]).", conditional.getRuns().get(1));
    }

    @Test
    public void conditionalSelectorNotOnlyBoolean() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Conditional conditional = TemplatePackage.eINSTANCE.getTemplateFactory().createConditional();
        conditional.setCondition(engine.build("self"));
        final Block thenCompound = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        conditional.setThen(thenCompound);
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(conditional);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new ClassType(queryEnvironment, String.class));
        selfTypes.add(new ClassType(queryEnvironment, Boolean.class));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, conditional.getValidationMessages().size());
        assertTemplateValidationMessage(conditional.getValidationMessages().get(0), ValidationMessageLevel.WARNING,
                "The predicate may evaluate to a value that is not a boolean type ([java.lang.String, java.lang.Boolean]).",
                conditional.getRuns().get(1));
    }

    @Test
    public void conditionalSelectorAlwaysTrue() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Conditional conditional = TemplatePackage.eINSTANCE.getTemplateFactory().createConditional();
        conditional.setCondition(engine.build("self.oclIsKindOf(String)"));
        final Block thenCompound = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        conditional.setThen(thenCompound);
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(conditional);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new ClassType(queryEnvironment, String.class));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, conditional.getValidationMessages().size());
        assertTemplateValidationMessage(conditional.getValidationMessages().get(0), ValidationMessageLevel.INFO,
                "Always true:\nNothing inferred when self (java.lang.String) is not kind of java.lang.String",
                conditional.getRuns().get(1));
    }

    @Test
    public void conditionalInferedTypeInThen() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Conditional conditional = TemplatePackage.eINSTANCE.getTemplateFactory().createConditional();
        conditional.setCondition(engine.build("self.oclIsKindOf(ecore::EClass)"));
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self.oclIsKindOf(ecore::EClass)"));
        final Block thenCompound = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        thenCompound.getStatements().add(query);
        conditional.setThen(thenCompound);
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(conditional);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new EClassifierType(queryEnvironment, EcorePackage.eINSTANCE.getEClassifier()));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(0, conditional.getValidationMessages().size());

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.INFO,
                "Always true:\nNothing inferred when self (EClassifier=EClass) is not kind of EClassifierLiteral=EClass",
                query.getStyleRun());
    }

    @Test
    public void conditionalInferedTypeInElse() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Conditional conditional = TemplatePackage.eINSTANCE.getTemplateFactory().createConditional();
        conditional.setCondition(engine.build("self.oclIsKindOf(ecore::EClassifier)"));
        final Block thenCompound = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        conditional.setThen(thenCompound);
        final Conditional alternative = TemplatePackage.eINSTANCE.getTemplateFactory().createConditional();
        alternative.setCondition(engine.build("self.oclIsKindOf(ecore::EPackage)"));
        final Block alternativeThenCompound = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        alternative.setThen(alternativeThenCompound);
        final Block elseCompound = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        elseCompound.getStatements().add(alternative);
        conditional.setElse(elseCompound);
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(conditional);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new EClassifierType(queryEnvironment, EcorePackage.eINSTANCE.getEClassifier()));
        selfTypes.add(new EClassifierType(queryEnvironment, EcorePackage.eINSTANCE.getEPackage()));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(0, conditional.getValidationMessages().size());

        assertEquals(1, alternative.getValidationMessages().size());
        assertTemplateValidationMessage(alternative.getValidationMessages().get(0), ValidationMessageLevel.INFO,
                "Always true:\nNothing inferred when self (EClassifier=EPackage) is not kind of EClassifierLiteral=EPackage",
                alternative.getRuns().get(1));
    }

    @Test
    public void repetitionMaskVariable() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Repetition repetition = TemplatePackage.eINSTANCE.getTemplateFactory().createRepetition();
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        repetition.setBody(body);
        repetition.setIterationVar("self");
        repetition.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(repetition);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new SequenceType(queryEnvironment, new ClassType(queryEnvironment, String.class)));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, repetition.getValidationMessages().size());
        assertTemplateValidationMessage(repetition.getValidationMessages().get(0), ValidationMessageLevel.WARNING,
                "The iteration variable mask an existing variable (self).", repetition.getRuns().get(1));
    }

    @Test
    public void repetitionNotCollection() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Repetition repetition = TemplatePackage.eINSTANCE.getTemplateFactory().createRepetition();
        repetition.setIterationVar("i");
        repetition.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        repetition.setBody(body);
        template.getBody().getStatements().add(repetition);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new ClassType(queryEnvironment, String.class));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, repetition.getValidationMessages().size());
        assertTemplateValidationMessage(repetition.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The iteration variable types must be collections ([java.lang.String]).", repetition.getRuns().get(1));
    }

    @Test
    public void repetition() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Repetition repetition = TemplatePackage.eINSTANCE.getTemplateFactory().createRepetition();
        repetition.setIterationVar("i");
        repetition.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(repetition);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("i.oclIsKindOf(String)"));
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        body.getStatements().add(query);
        repetition.setBody(body);

        final TemplateValidator validator = new TemplateValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new SequenceType(queryEnvironment, new ClassType(queryEnvironment, String.class)));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(0, repetition.getValidationMessages().size());

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.INFO,
                "Always true:\nNothing inferred when i (java.lang.String) is not kind of java.lang.String",
                query.getStyleRun());
    }

    @Test
    public void tableMergeSubConstruct() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        final TableMerge tableMerge = TemplatePackage.eINSTANCE.getTemplateFactory().createTableMerge();
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        tableMerge.setBody(body);
        tableMerge.getBody().getStatements().add(query);
        template.getBody().getStatements().add(query);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

    @Test
    public void query() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(query);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

    @Test
    public void tableRowCellTemplate() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        final Table table = TemplatePackage.eINSTANCE.getTemplateFactory().createTable();
        template.getBody().getStatements().add(table);
        final Row row = TemplatePackage.eINSTANCE.getTemplateFactory().createRow();
        table.getRows().add(row);
        final Cell cell = TemplatePackage.eINSTANCE.getTemplateFactory().createCell();
        row.getCells().add(cell);
        final Template cellTemplate = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        cellTemplate.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        cellTemplate.getBody().getStatements().add(query);
        cell.setTemplate(cellTemplate);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

    @Test
    public void abstractProviderClient() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        final Image image = TemplatePackage.eINSTANCE.getTemplateFactory().createImage();
        image.setProvider(new IProvider() {

            @Override
            public List<ProviderValidationMessage> validate(Map<String, Object> options) {
                final List<ProviderValidationMessage> res = new ArrayList<ProviderValidationMessage>();

                res.add(new ProviderValidationMessage("variable", ValidationMessageLevel.ERROR, "error with ..."));

                return res;
            }

            @Override
            public Map<String, OptionType> getOptionTypes() {
                Map<String, OptionType> res = new LinkedHashMap<String, OptionType>();

                res.put("variable", OptionType.STRING);
                res.put("query", OptionType.AQL_EXPRESSION);

                return res;
            }
        });
        image.getOptionValueMap().put("variable", "value");
        image.getOptionValueMap().put("query", engine.build("self"));

        template.getBody().getStatements().add(image);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(2, image.getValidationMessages().size());
        assertTemplateValidationMessage(image.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", image.getStyleRun());
        assertTemplateValidationMessage(image.getValidationMessages().get(1), ValidationMessageLevel.ERROR,
                "option variable: error with ...", image.getStyleRun());
    }

    @Test
    public void userDocWrongVariable() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.UserDoc userDoc = TemplatePackage.eINSTANCE.getTemplateFactory()
                .createUserDoc();
        userDoc.setId(engine.build("self"));
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        userDoc.setBody(body);

        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(userDoc);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, userDoc.getValidationMessages().size());
        assertTemplateValidationMessage(userDoc.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", userDoc.getRuns().get(1));
    }

    @Test
    public void userDocWithAQLResultAsCollectionType() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.UserDoc userDoc = TemplatePackage.eINSTANCE.getTemplateFactory()
                .createUserDoc();
        userDoc.setId(engine.build("collection"));
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        userDoc.setBody(body);

        final Template template = TemplatePackage.eINSTANCE.getTemplateFactory().createTemplate();
        template.setBody(TemplatePackage.eINSTANCE.getTemplateFactory().createBlock());
        template.getBody().getStatements().add(userDoc);
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final TemplateValidator validator = new TemplateValidator();
        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("collection", selfTypes);
        selfTypes.add(new SequenceType(queryEnvironment, new ClassType(queryEnvironment, Object.class)));

        validator.validate(documentTemplate, queryEnvironment, types);

        assertEquals(1, userDoc.getValidationMessages().size());
        assertTemplateValidationMessage(userDoc.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The id type must not be a collection (Sequence(java.lang.Object)).", userDoc.getRuns().get(1));
    }

}
