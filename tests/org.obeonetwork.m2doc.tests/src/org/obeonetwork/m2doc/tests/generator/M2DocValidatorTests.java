/*******************************************************************************
 *  Copyright (c) 2016, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.QueryParsing;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.M2DocValidator;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Table;
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

    public void conditionalInferedTypeInElse() throws IOException {
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
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        body.getStatements().add(conditional);
        try (final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(body);) {

            final M2DocValidator validator = new M2DocValidator();

            final Map<String, Set<IType>> types = new HashMap<>();
            final Set<IType> selfTypes = new LinkedHashSet<>();
            types.put("self", selfTypes);
            selfTypes.add(new EClassifierType(queryEnvironment, EcorePackage.eINSTANCE.getEClassifier()));
            selfTypes.add(new EClassifierType(queryEnvironment, EcorePackage.eINSTANCE.getEPackage()));

            validator.validate(documentTemplate, queryEnvironment, false, new BasicMonitor());

            assertEquals(0, conditional.getValidationMessages().size());

            assertEquals(1, alternative.getValidationMessages().size());
            assertTemplateValidationMessage(alternative.getValidationMessages().get(0), ValidationMessageLevel.INFO,
                    "Always true:\nNothing inferred when self (EClassifier=EPackage) is not kind of EClassifierLiteral=EPackage",
                    alternative.getRuns().get(1));
        }
    }

    @Test
    public void tableRowCellTemplate() {
        IQueryEnvironment queryEnvironment = Query.newEnvironmentWithDefaultServices(null);
        final IQueryBuilderEngine engine = QueryParsing.newBuilder(queryEnvironment);
        final org.obeonetwork.m2doc.template.Query query = TemplatePackage.eINSTANCE.getTemplateFactory().createQuery();
        query.setQuery(engine.build("self"));
        final Block body = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        final Table table = TemplatePackage.eINSTANCE.getTemplateFactory().createTable();
        body.getStatements().add(table);
        final Row row = TemplatePackage.eINSTANCE.getTemplateFactory().createRow();
        table.getRows().add(row);
        final Cell cell = TemplatePackage.eINSTANCE.getTemplateFactory().createCell();
        row.getCells().add(cell);
        final Block cellBody = TemplatePackage.eINSTANCE.getTemplateFactory().createBlock();
        cellBody.getStatements().add(query);
        cell.setBody(cellBody);
        @SuppressWarnings("resource")
        final DocumentTemplate documentTemplate = M2DocTestUtils.createDocumentTemplate(body);

        final M2DocValidator validator = new M2DocValidator();
        validator.validate(documentTemplate, queryEnvironment, false, new BasicMonitor());

        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Couldn't find the 'self' variable", query.getStyleRun());
    }

}
