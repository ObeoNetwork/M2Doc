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
package org.obeonetwork.m2doc.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.IServiceProvider;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.ServiceRegistrationResult;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.obeonetwork.m2doc.M2DocPlugin;

/**
 * Compatibility class for AQL 4.0.0 and 4.1.0.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
// TODO remove when AQL 4.0.0 and 4.1.0 will not be supported anymore
@SuppressWarnings({"unchecked", "restriction" })
public final class AQL4Compat {

    /**
     * UNABLE_TO_INVOKE_VALIDATION_METHOD.
     */
    private static final String UNABLE_TO_INVOKE_VALIDATION_METHOD = "unable to invoke validation method";

    /**
     * "unable to instantiate AstValidator" message.
     */
    private static final String UNABLE_TO_INSTANTIATE_AST_VALIDATOR = "unable to instantiate AstValidator";

    /**
     * "unable to get service name" message.
     */
    private static final String UNABLE_TO_GET_SERVICE_NAME = "unable to get service name";

    /**
     * "unable to instantiate IService for method: "message.
     */
    private static final String UNABLE_TO_INSTANTIATE_I_SERVICE_FOR_METHOD = "unable to instantiate IService for method: ";

    /**
     * "unable to register services " message.
     */
    private static final String UNABLE_TO_REGISTER_SERVICES = "unable to register services ";

    /**
     * Tells if we are running with AQL 5.
     */
    private static final boolean IS_AQL_5;

    /**
     * The method to register an {@link IService} (AQL 5) or a {@link Class} (AQL 4).
     */
    private static final Method REGISTER_METHOD;

    /**
     * The Java service {@link Constructor}.
     */
    private static final Constructor<? extends IService> JAVA_SERVICE_CONSTRUCTOR;

    /**
     * {@link AstValidator} constructor.
     */
    private static final Constructor<? extends AstValidator> AST_VALIDATOR_CONSTRUCTOR;

    /**
     * Tells if we should only pass the {@link IQueryEnvironment} to the constructor.
     */
    private static final boolean AST_VALIDATOR_CONSTRUCTOR_ONLY_ENV;

    /**
     * VALIDATION METHOD.
     */
    private static final Method VALIDATION_METHOD;

    static {
        Method methodAQL4 = null;
        Method methodAQL5 = null;
        try {
            methodAQL4 = IQueryEnvironment.class.getMethod("registerServicePackage", Class.class);
            // CHECKSTYLE:OFF
        } catch (Exception e4) {
            // CHECKSTYLE:ON
            try {
                methodAQL5 = IQueryEnvironment.class.getMethod("registerService", IService.class);
                // CHECKSTYLE:OFF
            } catch (Exception e5) {
                // CHECKSTYLE:ON
            }
        }

        assert methodAQL4 != null || methodAQL5 != null;
        IS_AQL_5 = methodAQL4 == null;
        if (IS_AQL_5) {
            REGISTER_METHOD = methodAQL5;
        } else {
            REGISTER_METHOD = methodAQL4;
        }

        Class<? extends IService> javaServiceClass;
        try {
            javaServiceClass = (Class<? extends IService>) AQL4Compat.class.getClassLoader()
                    .loadClass("org.eclipse.acceleo.query.runtime.impl.JavaMethodService");
        } catch (ClassNotFoundException e) {
            try {
                javaServiceClass = (Class<? extends IService>) AQL4Compat.class.getClassLoader()
                        .loadClass("org.eclipse.acceleo.query.runtime.lookup.basic.Service");
            } catch (ClassNotFoundException e1) {
                M2DocPlugin.log(
                        new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, "unable to load Java service class.", e1));
                javaServiceClass = null;
            }
        }

        Constructor<? extends IService> javaServiceConstuctor;
        try {
            javaServiceConstuctor = javaServiceClass.getConstructor(Method.class, Object.class);
        } catch (NoSuchMethodException e) {
            M2DocPlugin.log(
                    new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, "unable to find Java service contructor.", e));
            javaServiceConstuctor = null;
        } catch (SecurityException e) {
            M2DocPlugin.log(
                    new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, "unable to find Java service contructor.", e));
            javaServiceConstuctor = null;
        }
        JAVA_SERVICE_CONSTRUCTOR = javaServiceConstuctor;

        Constructor<? extends AstValidator> astValidatorConstuctor;
        boolean envOnly;
        try {
            astValidatorConstuctor = AstValidator.class.getConstructor(IQueryEnvironment.class, Map.class);
            envOnly = false;
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            try {
                astValidatorConstuctor = AstValidator.class.getConstructor(IReadOnlyQueryEnvironment.class);
                envOnly = true;
            } catch (NoSuchMethodException e1) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        "unable to find AstValidator contructor.", e1));
                astValidatorConstuctor = null;
                envOnly = false;
            } catch (SecurityException e1) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        "unable to find AstValidator contructor.", e1));
                astValidatorConstuctor = null;
                envOnly = false;
            }
        }

        AST_VALIDATOR_CONSTRUCTOR = astValidatorConstuctor;
        AST_VALIDATOR_CONSTRUCTOR_ONLY_ENV = envOnly;

        Method validationMethod;

        try {
            if (AST_VALIDATOR_CONSTRUCTOR_ONLY_ENV) {
                validationMethod = AstValidator.class.getDeclaredMethod("validate", Map.class, AstResult.class);
            } else {
                validationMethod = AstValidator.class.getDeclaredMethod("validate", AstResult.class);
            }
        } catch (NoSuchMethodException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, "unable to find validation method.", e));
            validationMethod = null;
        } catch (SecurityException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, "unable to find validation method.", e));
            validationMethod = null;
        }

        VALIDATION_METHOD = validationMethod;
    }

    /**
     * Constructor.
     */
    private AQL4Compat() {
        // nothing to do here
    }

    /**
     * Registers a {@link Set} of {@link IService} to the given {@link IQueryEnvironment}.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param services
     *            the {@link Set} of {@link IService}
     * @return the {@link ServiceRegistrationResult}
     */
    private static ServiceRegistrationResult registerServices(IQueryEnvironment queryEnvironment,
            Set<IService> services) {
        final ServiceRegistrationResult result = new ServiceRegistrationResult();

        for (IService service : services) {
            try {
                result.merge((ServiceRegistrationResult) REGISTER_METHOD.invoke(queryEnvironment, service));
            } catch (IllegalAccessException e) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        UNABLE_TO_REGISTER_SERVICES + getServiceName(service), e));
            } catch (IllegalArgumentException e) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        UNABLE_TO_REGISTER_SERVICES + getServiceName(service), e));
            } catch (InvocationTargetException e) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        UNABLE_TO_REGISTER_SERVICES + getServiceName(service), e));
            }
        }

        return result;
    }

    /**
     * Gets the given {@link IService} name.
     * 
     * @param service
     *            the {@link IService}
     * @return the given {@link IService} name
     */
    private static String getServiceName(IService service) {
        String result = null;

        try {
            if ("org.eclipse.acceleo.query.runtime.lookup.basic.Service".equals(service.getClass().getName())) {
                result = ((Method) service.getClass().getMethod("getServiceMethod").invoke(service)).getName();
            } else {
                result = (String) service.getClass().getMethod("getName").invoke(service);
            }
        } catch (IllegalAccessException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_GET_SERVICE_NAME, e));
        } catch (IllegalArgumentException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_GET_SERVICE_NAME, e));
        } catch (InvocationTargetException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_GET_SERVICE_NAME, e));
        } catch (NoSuchMethodException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_GET_SERVICE_NAME, e));
        } catch (SecurityException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_GET_SERVICE_NAME, e));
        }

        return result;
    }

    /**
     * Gets the {@link Set} of {@link IService} for the given {@link Class}. If the class can't be
     * instantiated only static {@link Method} will be used to produce {@link IService}.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param cls
     *            the {@link Class}
     * @return the {@link Set} of {@link IService} for the given {@link Class}
     */
    private static Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Class<?> cls) {
        final Set<IService> result = new LinkedHashSet<IService>();

        Object instance = null;
        try {
            Constructor<?> cstr = null;
            cstr = cls.getConstructor(new Class[] {});
            instance = cstr.newInstance(new Object[] {});
        } catch (NoSuchMethodException e) {
            // we will go without instance and register only static methods
        } catch (SecurityException e) {
            // we will go without instance and register only static methods
        } catch (InstantiationException e) {
            // we will go without instance and register only static methods
        } catch (IllegalAccessException e) {
            // we will go without instance and register only static methods
        } catch (IllegalArgumentException e) {
            // we will go without instance and register only static methods
        } catch (InvocationTargetException e) {
            // we will go without instance and register only static methods
        }
        result.addAll(getServicesFromInstance(queryEnvironment, cls, instance));

        return result;
    }

    /**
     * Gets {@link IService} from the given instance and {@link Method} array.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param cls
     *            the services {@link Class}
     * @param instance
     *            the instance
     * @return the {@link ServiceRegistrationResult}
     */
    private static Set<IService> getServicesFromInstance(IReadOnlyQueryEnvironment queryEnvironment, Class<?> cls,
            Object instance) {
        final Set<IService> result = new LinkedHashSet<IService>();

        if (instance instanceof IServiceProvider) {
            try {
                result.addAll(((IServiceProvider) instance).getServices(queryEnvironment));
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        UNABLE_TO_REGISTER_SERVICES + cls.getName(), e));
            }
        } else {
            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                if (isServiceMethod(instance, method)) {
                    try {
                        final IService service = JAVA_SERVICE_CONSTRUCTOR.newInstance(method, instance);
                        result.add(service);
                    } catch (InstantiationException e) {
                        M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                                UNABLE_TO_INSTANTIATE_I_SERVICE_FOR_METHOD + method.getName(), e));
                    } catch (IllegalAccessException e) {
                        M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                                UNABLE_TO_INSTANTIATE_I_SERVICE_FOR_METHOD + method.getName(), e));
                    } catch (IllegalArgumentException e) {
                        M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                                UNABLE_TO_INSTANTIATE_I_SERVICE_FOR_METHOD + method.getName(), e));
                    } catch (InvocationTargetException e) {
                        M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                                UNABLE_TO_INSTANTIATE_I_SERVICE_FOR_METHOD + method.getName(), e));
                    }
                }
            }
        }

        return result;
    }

    /**
     * Tells if a given {@link Method} is considered as a {@link IService}. {@link Object} methods are not
     * considered and only <code>static</code> {@link Method} are considered if the given instance if
     * <code>null</code>.
     * 
     * @param instance
     *            the instance {@link Object} if any, <code>null</code> otherwise
     * @param method
     *            the {@link Method} to check
     * @return <code>true</code> if a given {@link Method} is considered as a {@link IService},
     *         <code>false</code> otherwise
     */
    private static boolean isServiceMethod(Object instance, Method method) {
        final boolean result;

        if (method.getDeclaringClass() == Object.class) {
            // We do not register java.lang.Object method as
            // having an expression calling the 'wait' or the notify service
            // could yield problems that are difficult to track down.
            result = false;
        } else if (instance != null || Modifier.isStatic(method.getModifiers())) {
            // If we have no instance, only consider static methods.
            // Otherwise, any method with at least one parameter can be a service
            result = method.getParameterTypes().length > 0;
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Registers the given {@link Class} into the given {@link IQueryEnvironment}.
     * 
     * @param env
     *            the {@link IQueryEnvironment}
     * @param cls
     *            the {@link Class}
     */
    public static void register(IQueryEnvironment env, Class<?> cls) {
        if (IS_AQL_5) {
            final Set<IService> iServices = getServices(env, cls);
            registerServices(env, iServices);
        } else {
            try {
                REGISTER_METHOD.invoke(env, cls);
            } catch (IllegalAccessException e) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        UNABLE_TO_REGISTER_SERVICES + cls.getName(), e));
            } catch (IllegalArgumentException e) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        UNABLE_TO_REGISTER_SERVICES + cls.getName(), e));
            } catch (InvocationTargetException e) {
                M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID,
                        UNABLE_TO_REGISTER_SERVICES + cls.getName(), e));
            }
        }
    }

    /**
     * Creates an {@link AstValidator}.
     * 
     * @param environment
     *            the {@link IReadOnlyQueryEnvironment} used to validate
     * @param variableTypes
     *            the set of defined variables
     * @return the created {@link AstValidator}
     */
    public static AstValidator getValidator(IReadOnlyQueryEnvironment environment,
            Map<String, Set<IType>> variableTypes) {
        AstValidator result;

        try {
            if (AST_VALIDATOR_CONSTRUCTOR_ONLY_ENV) {
                result = AST_VALIDATOR_CONSTRUCTOR.newInstance(environment);
            } else {
                result = AST_VALIDATOR_CONSTRUCTOR.newInstance(environment, variableTypes);
            }
        } catch (InstantiationException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_INSTANTIATE_AST_VALIDATOR, e));
            result = null;
        } catch (IllegalAccessException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_INSTANTIATE_AST_VALIDATOR, e));
            result = null;
        } catch (IllegalArgumentException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_INSTANTIATE_AST_VALIDATOR, e));
            result = null;
        } catch (InvocationTargetException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_INSTANTIATE_AST_VALIDATOR, e));
            result = null;
        }

        return result;
    }

    /**
     * Validates using the given {@link AstValidator} and {@link AstResult}.
     * 
     * @param astResult
     *            the {@link AstResult}
     * @param variableTypes
     *            the variable types
     * @param environment
     *            the {@link IQueryEnvironment}
     * @return the {@link IValidationResult}
     */
    public static IValidationResult validate(AstResult astResult, Map<String, Set<IType>> variableTypes,
            IQueryEnvironment environment) {
        IValidationResult result;
        AstValidator validator = getValidator(environment, variableTypes);
        if (astResult == null) {
            M2DocPlugin.log(new Status(IStatus.WARNING, M2DocPlugin.PLUGIN_ID,
                    "null ast result passed to the validate method"));
            return null;

        }
        try {
            if (AST_VALIDATOR_CONSTRUCTOR_ONLY_ENV) {
                result = (IValidationResult) VALIDATION_METHOD.invoke(validator, variableTypes, astResult);
            } else {
                result = (IValidationResult) VALIDATION_METHOD.invoke(validator, astResult);
            }
        } catch (IllegalAccessException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_INVOKE_VALIDATION_METHOD, e));
            result = null;
        } catch (IllegalArgumentException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_INVOKE_VALIDATION_METHOD, e));
            result = null;
        } catch (InvocationTargetException e) {
            M2DocPlugin.log(new Status(IStatus.ERROR, M2DocPlugin.PLUGIN_ID, UNABLE_TO_INVOKE_VALIDATION_METHOD, e));
            result = null;
        }

        return result;
    }

}
