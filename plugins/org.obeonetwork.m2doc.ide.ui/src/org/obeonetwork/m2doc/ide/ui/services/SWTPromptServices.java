/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.services;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.acceleo.query.ast.Call;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.impl.AbstractServiceProvider;
import org.eclipse.acceleo.query.runtime.impl.JavaMethodService;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.obeonetwork.m2doc.ide.ui.dialog.AbstractPromptDialog;
import org.obeonetwork.m2doc.ide.ui.dialog.EObjectSelectionDialog;

/**
 * Services prompting for value.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
//@formatter:off
@ServiceProvider(
value = "Services available for prompting user for values using SWT for graphical user interface."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype", "restriction" })
public class SWTPromptServices extends AbstractServiceProvider {

    private static class EOBjectPromptService extends JavaMethodService {

        /**
         * Constructor.
         * 
         * @param publicMethod
         *            the {@link Method}
         * @param serviceInstance
         *            the instance
         */
        EOBjectPromptService(Method publicMethod, Object serviceInstance) {
            super(publicMethod, serviceInstance);
        }

        @Override
        public Set<IType> getType(Call call, ValidationServices services, IValidationResult validationResult,
                IReadOnlyQueryEnvironment environment, List<IType> argTypes) {
            return new LinkedHashSet<>(argTypes);
        }

        @Override
        public Set<IType> validateAllType(ValidationServices services, IReadOnlyQueryEnvironment queryEnvironment,
                Map<List<IType>, Set<IType>> allTypes) {
            final Set<IType> res = new LinkedHashSet<>();

            for (Set<IType> types : allTypes.values()) {
                res.addAll(types);
            }

            return res;
        }
    }

    /**
     * A {@link Runnable} with a result value.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     * @param <T>
     *            the kind of result
     */
    private abstract static class RunnableWithResult<T> implements Runnable {

        /**
         * The result.
         */
        protected T result;

        /**
         * Gets the result.
         * 
         * @return the result
         */
        public T getResult() {
            return result;
        };
    }

    /**
     * The cancel message.
     */
    private static final String M2DOC_DIALOG_CANCELLED = "M2Doc dialog cancelled.";

    /**
     * The {@link ResourceSet} for model elements.
     */
    private final ResourceSet resourceSetForModels;

    /**
     * The {@link IReadOnlyQueryEnvironment}.
     */
    private final IReadOnlyQueryEnvironment queryEnvironment;

    /**
     * The {@link AdapterFactory}.
     */
    private final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
            ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    /**
     * Constructor.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param resourceSetForModels
     *            the {@link ResourceSet} for model elements
     */
    public SWTPromptServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels) {
        this.resourceSetForModels = resourceSetForModels;
        this.queryEnvironment = queryEnvironment;
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a String value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as a String.",
        examples = {
            @Example(expression = "'Enter your name: '.promptString()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public String promptString(final String message) {
        final RunnableWithResult<String> runnable = new RunnableWithResult<String>() {

            @Override
            public void run() {
                final AbstractPromptDialog dialog = new AbstractPromptDialog(Display.getDefault().getActiveShell(),
                        message) {

                    @Override
                    public boolean validate(String input) {
                        return input != null;
                    }
                };
                if (dialog.open() == Window.OK) {
                    result = dialog.getValue();
                } else {
                    result = null;
                }
            }

        };
        Display.getDefault().syncExec(runnable);
        if (runnable.getResult() != null) {
            return runnable.getResult();
        } else {
            throw new IllegalStateException(M2DOC_DIALOG_CANCELLED);
        }
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Integer value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as an Integer.",
        examples = {
            @Example(expression = "'Enter your age: '.promptInteger()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Integer promptInteger(final String message) {
        final RunnableWithResult<Integer> runnable = new RunnableWithResult<Integer>() {

            @Override
            public void run() {
                final AbstractPromptDialog dialog = new AbstractPromptDialog(Display.getDefault().getActiveShell(),
                        message) {

                    @Override
                    public boolean validate(String input) {
                        return validateInteger(input);
                    }
                };
                if (dialog.open() == Window.OK) {
                    result = Integer.valueOf(dialog.getValue());
                } else {
                    result = null;
                }
            }

        };
        Display.getDefault().syncExec(runnable);
        if (runnable.getResult() != null) {
            return runnable.getResult();
        } else {
            throw new IllegalStateException(M2DOC_DIALOG_CANCELLED);
        }
    }

    private boolean validateInteger(String input) {
        boolean res;

        try {
            Integer.valueOf(input);
            res = true;
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            res = false;
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Long value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as an Long.",
        examples = {
            @Example(expression = "'Enter your age: '.promptLong()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Long promptLong(final String message) {
        final RunnableWithResult<Long> runnable = new RunnableWithResult<Long>() {

            @Override
            public void run() {
                final AbstractPromptDialog dialog = new AbstractPromptDialog(Display.getDefault().getActiveShell(),
                        message) {

                    @Override
                    public boolean validate(String input) {
                        return validateLong(input);
                    }
                };
                if (dialog.open() == Window.OK) {
                    result = Long.valueOf(dialog.getValue());
                } else {
                    result = null;
                }
            }

        };
        Display.getDefault().syncExec(runnable);
        if (runnable.getResult() != null) {
            return runnable.getResult();
        } else {
            throw new IllegalStateException(M2DOC_DIALOG_CANCELLED);
        }
    }

    private boolean validateLong(String input) {
        boolean res;

        try {
            Long.valueOf(input);
            res = true;
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            res = false;
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Float value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as a Float.",
        examples = {
            @Example(expression = "'Enter your weight: '.promptFloat()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Float promptFloat(final String message) {
        final RunnableWithResult<Float> runnable = new RunnableWithResult<Float>() {

            @Override
            public void run() {
                final AbstractPromptDialog dialog = new AbstractPromptDialog(Display.getDefault().getActiveShell(),
                        message) {

                    @Override
                    public boolean validate(String input) {
                        return validateFloat(input);
                    }
                };
                if (dialog.open() == Window.OK) {
                    result = Float.valueOf(dialog.getValue());
                } else {
                    result = null;
                }
            }

        };
        Display.getDefault().syncExec(runnable);
        if (runnable.getResult() != null) {
            return runnable.getResult();
        } else {
            throw new IllegalStateException(M2DOC_DIALOG_CANCELLED);
        }
    }

    private boolean validateFloat(String input) {
        boolean res;

        try {
            Float.valueOf(input);
            res = true;
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            res = false;
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Double value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as a Double.",
        examples = {
            @Example(expression = "'Enter your weight: '.promptDouble()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Double promptDouble(final String message) {
        final RunnableWithResult<Double> runnable = new RunnableWithResult<Double>() {

            @Override
            public void run() {
                final AbstractPromptDialog dialog = new AbstractPromptDialog(Display.getDefault().getActiveShell(),
                        message) {

                    @Override
                    public boolean validate(String input) {
                        return validateDouble(input);
                    }
                };
                if (dialog.open() == Window.OK) {
                    result = Double.valueOf(dialog.getValue());
                } else {
                    result = null;
                }
            }

        };
        Display.getDefault().syncExec(runnable);
        if (runnable.getResult() != null) {
            return runnable.getResult();
        } else {
            throw new IllegalStateException(M2DOC_DIALOG_CANCELLED);
        }
    }

    private boolean validateDouble(String input) {
        boolean res;

        try {
            Double.valueOf(input);
            res = true;
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            res = false;
        }

        return res;
    }

    public EObject promptEObject(String message, EClass eCls) {
        final Set<EClass> acceptedEClasses = new HashSet<>();
        acceptedEClasses.add(eCls);

        return promptEObject(message, acceptedEClasses);
    }

    public EObject promptEObject(final String message, final Set<EClass> acceptedEClasses) {
        final RunnableWithResult<EObject> runnable = new RunnableWithResult<EObject>() {

            @Override
            public void run() {
                final EObjectSelectionDialog dialog = new EObjectSelectionDialog(Display.getDefault().getActiveShell(),
                        adapterFactory, "M2Doc", message, queryEnvironment, resourceSetForModels, acceptedEClasses);
                if (dialog.open() == Window.OK) {
                    result = (EObject) dialog.getValue();
                } else {
                    result = null;
                }
            }

        };
        Display.getDefault().syncExec(runnable);
        if (runnable.getResult() != null) {
            return runnable.getResult();
        } else {
            throw new IllegalStateException(M2DOC_DIALOG_CANCELLED);
        }
    }

    /**
     * Disposes this instance.
     */
    public void dispose() {
        adapterFactory.dispose();
    }

    @Override
    protected IService getService(Method method) {
        final IService result;

        if ("promptEObject".equals(method.getName())) {
            result = new EOBjectPromptService(method, this);
        } else {
            result = new JavaMethodService(method, this);
        }

        return result;
    }

}
