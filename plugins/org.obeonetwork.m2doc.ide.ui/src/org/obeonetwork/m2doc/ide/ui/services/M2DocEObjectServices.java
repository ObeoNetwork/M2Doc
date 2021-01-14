/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.services;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.ide.ui.element.impl.MImageSWTImpl;

/**
 * M2Doc services for {@link EObject}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
//@formatter:off
@ServiceProvider(
value = "Services available for EObject."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class M2DocEObjectServices {

    /**
     * The {@link ComposedAdapterFactory}.
     */
    private final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
            ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    /**
     * The {@link AdapterFactoryLabelProvider}.
     */
    private final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(
            adapterFactory);

    // @formatter:off
    @Documentation(
        value = "Gets the text of the ILabelProvider for the given EObject.",
        params = {
            @Param(name = "eObj", value = "The EObject"),
        },
        result = "Inserts the text from the ILabelProvider of the given EObject.",
        examples = {
            @Example(expression = "myEClass.getText()", result = "MyEClass -> MySuperClass"),
        }
    )
    // @formatter:on
    public String getText(EObject eObj) {
        return adapterFactoryLabelProvider.getText(eObj);
    }

    // @formatter:off
    @Documentation(
        value = "Gets the image of the ILabelProvider for the given EObject.",
        params = {
            @Param(name = "eObj", value = "The EObject"),
        },
        result = "Inserts the image from the ILabelProvider of the given EObject.",
        examples = {
            @Example(expression = "myEClass.getImage()", result = "the MImage for the given EObject"),
        }
    )
    // @formatter:on
    public MImage getImage(EObject eObj) {
        final Image image = adapterFactoryLabelProvider.getImage(eObj);

        return new MImageSWTImpl(image.getImageData());
    }

    /**
     * Disposes the service class.
     */
    public void dispose() {
        adapterFactory.dispose();
    }

}
