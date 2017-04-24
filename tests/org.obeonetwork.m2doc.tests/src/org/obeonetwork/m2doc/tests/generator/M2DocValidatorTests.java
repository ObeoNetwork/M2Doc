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
package org.obeonetwork.m2doc.tests.generator;

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
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.M2DocValidator;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.tests.M2DocTestUtils;

import static org.junit.Assert.assertEquals;
import static org.obeonetwork.m2doc.tests.M2DocTestUtils.assertTemplateValidationMessage;

/**
 * Test {@link M2DocValidator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocValidatorTests {

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

        final M2DocValidator validator = new M2DocValidator();

        final Map<String, Set<IType>> types = new HashMap<String, Set<IType>>();
        final Set<IType> selfTypes = new LinkedHashSet<IType>();
        types.put("self", selfTypes);
        selfTypes.add(new EClassifierType(queryEnvironment, EcorePackage.eINSTANCE.getEClassifier()));
        selfTypes.add(new EClassifierType(queryEnvironment, EcorePackage.eINSTANCE.getEPackage()));

        validator.validate(documentTemplate, queryEnvironment);

        assertEquals(0, conditional.getValidationMessages().size());

        assertEquals(1, alternative.getValidationMessages().size());
        assertTemplateValidationMessage(alternative.getValidationMessages().get(0), ValidationMessageLevel.INFO,
                "Always true:\nNothing inferred when self (EClassifier=EPackage) is not kind of EClassifierLiteral=EPackage",
                alternative.getRuns().get(1));
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
        @SuppressWarnings("resource")
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final M2DocValidator validator = new M2DocValidator();
        validator.validate(documentTemplate, queryEnvironment);

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
        image.setFileName("somefile");
        image.getOptionValueMap().put("variable", "value");
        image.getOptionValueMap().put("query", engine.build("self"));

        template.getBody().getStatements().add(image);
        @SuppressWarnings("resource")
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(template);

        final M2DocValidator validator = new M2DocValidator();
        validator.validate(documentTemplate, queryEnvironment);

        assertEquals(3, image.getValidationMessages().size());
        assertTemplateValidationMessage(image.getValidationMessages().get(0), ValidationMessageLevel.WARNING,
                "Providers are deprecated use services instead:\nhttps://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc/src/org/obeonetwork/m2doc/services/ImageServices.java\nhttps://github.com/ObeoNetwork/M2Doc/blob/master/plugins/org.obeonetwork.m2doc.sirius/src/org/obeonetwork/m2doc/sirius/services/M2DocSiriusServices.java",
                image.getStyleRun());
        assertTemplateValidationMessage(image.getValidationMessages().get(1), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", image.getStyleRun());
        assertTemplateValidationMessage(image.getValidationMessages().get(2), ValidationMessageLevel.ERROR,
                "option variable: error with ...", image.getStyleRun());
    }

}
