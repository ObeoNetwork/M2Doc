/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.doc;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Other;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.ecore.EClass;

/**
 * Utility class used to compute the html of the documentation.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
// CHECKSTYLE:OFF
public final class M2DocHelpContentUtils {

    public static final Function<Method, StringBuffer> METHOD_SIGNATURE_GENERATOR_2016 = new Function<Method, StringBuffer>() {

        @Override
        public StringBuffer apply(Method method) {
            Map<String, String> methodnamesToOperator = new LinkedHashMap<String, String>();
            methodnamesToOperator.put("add", " + ");
            methodnamesToOperator.put("sub", " - ");
            methodnamesToOperator.put("equals", " = ");
            methodnamesToOperator.put("differs", " <> ");

            methodnamesToOperator.put("greaterThan", " > ");
            methodnamesToOperator.put("greaterThanEqual", " >= ");
            methodnamesToOperator.put("lessThan", " < ");
            methodnamesToOperator.put("lessThanEqual", " <= ");

            StringBuffer result = new StringBuffer();
            boolean isOperator = false;

            boolean first = true;

            List<String> parameterNames = new ArrayList<String>();
            Documentation documentation = method.getAnnotation(Documentation.class);
            Param[] params = documentation.params();
            if (params != null) {
                for (Param param : params) {
                    parameterNames.add(param.name());
                }
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            for (int i = 0; i < parameterTypes.length; i = i + 1) {
                Object argType = parameterTypes[i];

                String typeName = "";
                if (argType instanceof Class<?>) {
                    typeName = getPrettyGenericTypename(genericParameterTypes[i], (Class<?>) argType);

                } else if (argType instanceof EClass) {
                    typeName = "EClass=" + ((EClass) argType).getName();
                } else {
                    typeName = "Object=" + argType.toString();
                }

                if (first) {
                    String methodName = method.getName();
                    /*
                     * check for operator names
                     */
                    if (methodnamesToOperator.get(methodName) != null) {
                        isOperator = true;
                        methodName = methodnamesToOperator.get(methodName);
                    }

                    result.append(typeName);

                    if (!isOperator) {
                        if (isCollection((Class<?>) argType)) {
                            result.append("->");
                        } else {
                            result.append(".");
                        }
                    }

                    result.append(methodName);
                    if (!isOperator) {
                        result.append('(');
                    }
                }

                if (!first) {
                    if (i > 1) {
                        result.append(", ");
                    }
                    result.append(typeName);
                } else {
                    first = false;
                }

            }
            if (!isOperator) {
                result.append(')');
            }

            Type returnType = method.getGenericReturnType();
            if (Void.class.equals(returnType)) {
                result.append(" : void");
            } else {
                result.append(" : ");
                result.append(getPrettyGenericTypename(returnType, method.getReturnType()));
            }
            return result;
        }
    };

    /**
     * The prefix to use to create internal links.
     */
    public static final String M2DOC_HREF_PREFIX = "m2doc_service_";

    /**
     * The line separator.
     */
    private static final String LS = System.getProperty("line.separator");

    private static final Function<Method, StringBuffer> METHOD_SIGNATURE_OLD_GENERATOR = new Function<Method, StringBuffer>() {

        @Override
        public StringBuffer apply(Method method) {
            StringBuffer result = new StringBuffer();
            result.append(method.getName()).append('(');
            boolean first = true;

            List<String> parameterNames = new ArrayList<String>();
            Documentation documentation = method.getAnnotation(Documentation.class);
            Param[] params = documentation.params();
            if (params != null) {
                for (Param param : params) {
                    parameterNames.add(param.name());
                }
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i = i + 1) {
                Object argType = parameterTypes[i];
                if (!first) {
                    result.append(", ");
                } else {
                    first = false;
                }

                if (parameterNames.size() >= i + 1) {
                    String paramName = parameterNames.get(i);
                    if (paramName.trim().length() > 0) {
                        result.append(paramName);
                        result.append(": ");
                    }
                }

                if (argType instanceof Class<?>) {
                    result.append(((Class<?>) argType).getCanonicalName());
                } else if (argType instanceof EClass) {
                    result.append("EClass=" + ((EClass) argType).getName());
                } else {
                    result.append("Object=" + argType.toString());
                }
            }
            result.append(')');

            Class<?> returnType = method.getReturnType();
            if (Void.class.equals(returnType)) {
                result.append(" = void");
            } else {
                result.append(" = ");
                result.append(returnType.getSimpleName());
            }
            return result;
        }
    };

    /**
     * The constructor.
     */
    private M2DocHelpContentUtils() {
        // Prevent instantiation
    }

    /**
     * Produces the HTML page.
     * 
     * @param head
     *            The content of the head
     * @param body
     *            The content of the body
     * @return The HTML content
     */
    public static StringBuffer html(StringBuffer head, StringBuffer body) {
        // @formatter:off
        StringBuffer buffer = new StringBuffer();
        buffer.append("<!DOCTYPE html>").append(LS);
        buffer.append("<html lang=\"en\">").append(LS);
        buffer.append("<!--").append(LS);
        buffer.append("/********************************************************************************").append(LS);
        buffer.append("** Copyright (c) 2015 Obeo.").append(LS);
        buffer.append("** All rights reserved. This program and the accompanying materials").append(LS);
        buffer.append("** are made available under the terms of the Eclipse Public License v1.0").append(LS);
        buffer.append("** which accompanies this distribution, and is available at").append(LS);
        buffer.append("** http://www.eclipse.org/legal/epl-v10.html").append(LS);
        buffer.append("**").append(LS);
        buffer.append("** Contributors:").append(LS);
        buffer.append("**    Stephane Begaudeau (Obeo) - initial API and implementation").append(LS);
        buffer.append("*********************************************************************************/").append(LS);
        buffer.append("-->").append(LS);

        buffer.append(head).append(LS);
        buffer.append(body).append(LS);

        buffer.append("</html>").append(LS);
        // @formatter:on
        return buffer;
    }

    /**
     * Produces the HTML page.
     * 
     * @param head
     *            The content of the head
     * @param body
     *            The content of the body
     * @return The markdown content
     */
    public static StringBuffer md(StringBuffer head, StringBuffer body) {
        StringBuffer buffer = new StringBuffer();

        // @formatter:off
        buffer.append(head);
        buffer.append(body).append(LS);
        // @formatter:on

        return buffer;
    }

    /**
     * Produces the head element.
     * 
     * @return The content of the head element
     */
    public static StringBuffer htmlHead() {
        // @formatter:off
        StringBuffer buffer = new StringBuffer();
        buffer.append("  <head>").append(LS);
        buffer.append("    <meta charset=\"utf-8\">").append(LS);
        buffer.append("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">").append(LS);
        buffer.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">").append(LS);
        buffer.append("    <meta name=\"description\" content=\"\">").append(LS);
        buffer.append("    <meta name=\"author\" content=\"St&eacute;phane B&eacute;gaudeau\">").append(LS);

        buffer.append("    <!-- IE6-8 support of HTML elements -->").append(LS);
        buffer.append("    <!--[if lt IE 9]>").append(LS);
        buffer.append("      <script src=\"http://html5shim.googlecode.com/svn/trunk/html5.js\"></script>").append(LS);
        buffer.append("    <![endif]-->").append(LS);

        buffer.append("    <link href=\"../assets/css/bootstrap.css\" rel=\"stylesheet\">").append(LS);
        buffer.append("    <link href=\"../assets/css/docs.css\" rel=\"stylesheet\">").append(LS);

        buffer.append("    <title>M2Doc</title>").append(LS);
        buffer.append("  </head>").append(LS);
        // @formatter:on
        return buffer;
    }

    /**
     * Produces the head element.
     * 
     * @return The content of the head element
     */
    public static StringBuffer mdHead() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("");

        return buffer;
    }

    /**
     * Produces the content of the body.
     * 
     * @param header
     *            The header
     * @param sections
     *            The sections to display
     * @return The content of the body
     */
    public static StringBuffer htmlBody(StringBuffer header, List<StringBuffer> sections) {
        // @formatter:off
        StringBuffer buffer = new StringBuffer();
        buffer.append("  <body>").append(LS);
        buffer.append("    <div class=\"container\">").append(LS);

        buffer.append(header).append(LS);

        for (StringBuffer section : sections) {
            buffer.append(section).append(LS);
        }

        buffer.append("    </div>").append(LS);
        buffer.append("  </body>").append(LS);
        // @formatter:on
        return buffer;
    }

    /**
     * Produces the content of the body.
     * 
     * @param header
     *            The header
     * @param sections
     *            The sections to display
     * @return The content of the body
     */
    public static StringBuffer mdBody(StringBuffer header, List<StringBuffer> sections) {
        StringBuffer buffer = new StringBuffer();

        buffer.append(header);

        for (StringBuffer section : sections) {
            buffer.append(section).append(LS);
        }

        return buffer;
    }

    /**
     * Produces the content of the header.
     * 
     * @param isMainPage
     *            Indicates if we are computing the header of the main page
     * @return The content of the header element
     */
    public static StringBuffer htmlHeader(boolean isMainPage) {
        StringBuffer buffer = new StringBuffer();
        // @formatter:off
        buffer.append("   <header class=\"jumbotron subhead\" id=\"overview\">").append(LS);
        if (!isMainPage) {          
            buffer.append("    <h1>M2Doc Documentation</h1>").append(LS);
        }
        buffer.append("    <!--<div class=\"subnav\">").append(LS);
        buffer.append("       <ul class=\"nav nav-pills\">").append(LS);
        buffer.append("        <li><a href=\"#introduction\">Introduction</a></li>").append(LS);
        buffer.append("        <li><a href=\"#language\">Language</a></li>").append(LS);
        buffer.append("        <li><a href=\"#operations\">Operations</a></li>").append(LS);
        buffer.append("        <li><a href=\"#standalone\">Stand Alone</a></li>").append(LS);
        buffer.append("        <li><a href=\"#migration\">Migration</a></li>").append(LS);
        buffer.append("        <li><a href=\"#textproductionrules\">Text Production Rules</a></li>").append(LS);
        buffer.append("        <li><a href=\"#onlineresources\">Online Resources</a></li>").append(LS);
        buffer.append("      </ul>").append(LS);
        buffer.append("    </div>-->").append(LS);
        buffer.append("  </header>").append(LS);
        // @formatter:on
        return buffer;
    }

    /**
     * Produces the content of the header.
     * 
     * @param isMainPage
     *            Indicates if we are computing the header of the main page
     * @return The content of the header element
     */
    public static StringBuffer mdHeader(boolean isMainPage) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("");

        return buffer;
    }

    /**
     * Computes the content of the toc.xml file.
     * 
     * @param serviceProviders
     *            The list of Java classes providing services to M2Doc.
     * @return The content of the toc.xml
     */
    public static StringBuffer computeToc(Class<?>... serviceProviders) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(LS);
        buffer.append("<?NLS TYPE=\"org.eclipse.help.toc\"?>").append(LS);
        buffer.append("<toc label=\"M2Doc Documentation\" topic=\"doc/index.html\">").append(LS);
        for (Class<?> serviceProviderClass : serviceProviders) {
            if (serviceProviderClass.isAnnotationPresent(ServiceProvider.class)) {
                ServiceProvider serviceProvider = serviceProviderClass.getAnnotation(ServiceProvider.class);
                buffer.append(
                        "<topic href=\"doc/" + M2DOC_HREF_PREFIX + serviceProviderClass.getSimpleName().toLowerCase()
                            + ".html\" label=\"" + serviceProvider.value() + "\"></topic>")
                        .append(LS);
            }
        }
        buffer.append("</toc>").append(LS);
        return buffer;
    }

    /**
     * Computes the sections for a service provider.
     * 
     * @param serviceProviderClass
     *            The service provider
     * @return The sections to display in the HTML page
     */
    public static List<StringBuffer> htmlServiceSections(Class<?> serviceProviderClass) {
        return htmlServiceSections(serviceProviderClass, 1, METHOD_SIGNATURE_OLD_GENERATOR);
    }

    /**
     * Computes the sections for a service provider.
     * 
     * @param serviceProviderClass
     *            The service provider
     * @return The sections to display in the markdown page
     */
    public static List<StringBuffer> mdServiceSections(Class<?> serviceProviderClass) {
        return mdServiceSections(serviceProviderClass, 1, METHOD_SIGNATURE_GENERATOR_2016);
    }

    /**
     * Computes the sections for a service provider.
     * 
     * @param serviceProviderClass
     *            The service provider
     * @param titleLevel
     *            the title level
     * @param signatureGenerator
     *            the signature generator
     * @return The sections to display in the HTML page
     */
    public static List<StringBuffer> htmlServiceSections(Class<?> serviceProviderClass, int titleLevel,
            Function<Method, StringBuffer> signatureGenerator) {
        List<StringBuffer> buffers = new ArrayList<StringBuffer>();

        ServiceProvider serviceProvider = serviceProviderClass.getAnnotation(ServiceProvider.class);
        if (serviceProvider == null) {
            return buffers;
        }

        // @formatter:off
        StringBuffer servicesSection = new StringBuffer();
        servicesSection.append("  <section id=\"services\">").append(LS);
        servicesSection.append("    <div class=\"page-header\">").append(LS);
        servicesSection.append("      <h" + titleLevel + ">").append(serviceProvider.value()).append("</h" + titleLevel + ">").append(LS);
        servicesSection.append("    </div>").append(LS);
        // @formatter:on

        Method[] methods = serviceProviderClass.getMethods();

        Method[] sortedMethods = Arrays.copyOf(methods, methods.length);
        Comparator<Method> comparator = new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Arrays.sort(sortedMethods, 0, sortedMethods.length, comparator);

        for (Method method : sortedMethods) {
            if (method.isAnnotationPresent(Documentation.class)) {
                Documentation serviceDocumentation = method.getAnnotation(Documentation.class);

                // @formatter:off
                servicesSection.append(LS);

                servicesSection.append("        <h3>").append(signatureGenerator.apply(method)).append("</h3>").append(LS);
                servicesSection.append("        <p>").append(LS);
                servicesSection.append("          ").append(serviceDocumentation.value()).append(LS);
                servicesSection.append("        </p>").append(LS);
                
                if (serviceDocumentation.examples().length > 0) {                   
                    servicesSection.append("        <table class=\"table table-striped table-bordered table-condensed\">").append(LS);
                    servicesSection.append("          <thead>").append(LS);
                    servicesSection.append("            <tr>").append(LS);
                    servicesSection.append("              <th>Expression</th>").append(LS);
                    servicesSection.append("              <th>Result</th>").append(LS);
                    servicesSection.append("            </tr>").append(LS);
                    servicesSection.append("          </thead><colgroup><col width=\"60%\" /><col width=\"40%\" /></colgroup>").append(LS);
                    servicesSection.append("          <tbody>").append(LS);
                    
                    List<Other> others = new ArrayList<Other>();
                    
                    Example[] examples = serviceDocumentation.examples();
                    for (Example example : examples) {                      
                        servicesSection.append("            <tr>").append(LS);
                        servicesSection.append("              <td>").append(example.expression()).append("</td>").append(LS);
                        servicesSection.append("              <td>").append(example.result()).append("</td>").append(LS);
                        servicesSection.append("            </tr>").append(LS);
                        
                        Other[] otherExamples = example.others();
                        for (Other otherExample : otherExamples) {
                            others.add(otherExample);
                        }
                    }
                    
                    servicesSection.append("          </tbody>").append(LS);
                    servicesSection.append("        </table>").append(LS);
                    
                    servicesSection.append("        <p>").append(LS);
                    servicesSection.append("          ").append(serviceDocumentation.comment()).append(LS);
                    servicesSection.append("        </p>").append(LS);
                    
                    if (others.size() > 0) {
                        servicesSection.append("        <h4>In other languages</h4>").append(LS);
                        servicesSection.append("        <table class=\"table table-striped table-bordered table-condensed\">").append(LS);
                        servicesSection.append("          <thead>").append(LS);
                        servicesSection.append("            <tr>").append(LS);
                        servicesSection.append("              <th>Language</th>").append(LS);
                        servicesSection.append("              <th>Expression</th>").append(LS);
                        servicesSection.append("              <th>Result</th>").append(LS);
                        servicesSection.append("            </tr>").append(LS);
                        servicesSection.append("          </thead><colgroup><col width=\"60%\" /><col width=\"40%\" /></colgroup>").append(LS);
                        servicesSection.append("          <tbody>").append(LS);
                        for (Other alternate : others) {
                            servicesSection.append("            <tr>").append(LS);
                            servicesSection.append("              <td>").append(alternate.language()).append("</td>").append(LS);
                            servicesSection.append("              <td>").append(alternate.expression()).append("</td>").append(LS);
                            servicesSection.append("              <td>").append(alternate.result()).append("</td>").append(LS);
                            servicesSection.append("            </tr>").append(LS);
                        }
                        servicesSection.append("          </tbody>").append(LS);
                        servicesSection.append("        </table>").append(LS);
                    }
                }
                servicesSection.append("        <hr />").append(LS);
                // @formatter:on
            }
        }

        // @formatter:off
        servicesSection.append("  </section>").append(LS);
        // @formatter:on

        buffers.add(servicesSection);

        return buffers;
    }

    /**
     * Computes the sections for a service provider.
     * 
     * @param serviceProviderClass
     *            The service provider
     * @param titleLevel
     *            the title level
     * @param signatureGenerator
     *            the signature generator
     * @return The sections to display in the markdown page
     */
    public static List<StringBuffer> mdServiceSections(Class<?> serviceProviderClass, int titleLevel,
            Function<Method, StringBuffer> signatureGenerator) {
        List<StringBuffer> buffers = new ArrayList<StringBuffer>();

        ServiceProvider serviceProvider = serviceProviderClass.getAnnotation(ServiceProvider.class);
        if (serviceProvider == null) {
            return buffers;
        }

        StringBuffer servicesSection = new StringBuffer();
        // @formatter:off
        servicesSection.append("---").append(LS);
        servicesSection.append("layout: article").append(LS);
        servicesSection.append("title: ").append(serviceProviderClass.getSimpleName()).append(LS);
        servicesSection.append("subtitle: Nightly").append(LS);
        servicesSection.append("relativePath: ../..").append(LS);
        servicesSection.append("---").append(LS);
        servicesSection.append(LS);
        servicesSection.append("<!--").append(LS);
        servicesSection.append("/********************************************************************************").append(LS);
        servicesSection.append("** Copyright (c) 2015 Obeo.").append(LS);
        servicesSection.append("** All rights reserved. This program and the accompanying materials").append(LS);
        servicesSection.append("** are made available under the terms of the Eclipse Public License v1.0").append(LS);
        servicesSection.append("** which accompanies this distribution, and is available at").append(LS);
        servicesSection.append("** http://www.eclipse.org/legal/epl-v10.html").append(LS);
        servicesSection.append("**").append(LS);
        servicesSection.append("** Contributors:").append(LS);
        servicesSection.append("**    Stephane Begaudeau (Obeo) - initial API and implementation").append(LS);
        servicesSection.append("*********************************************************************************/").append(LS);
        servicesSection.append("-->").append(LS);
        servicesSection.append(LS);
        servicesSection.append("# ").append(serviceProviderClass.getSimpleName()).append(LS);
        servicesSection.append(LS);
        servicesSection.append(serviceProvider.value()).append(LS);
        servicesSection.append(LS);
        servicesSection.append("* TOC").append(LS);
        servicesSection.append("{:toc}").append(LS);
        servicesSection.append(LS);
        // @formatter:on

        Method[] methods = serviceProviderClass.getMethods();

        Method[] sortedMethods = Arrays.copyOf(methods, methods.length);
        Comparator<Method> comparator = new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Arrays.sort(sortedMethods, 0, sortedMethods.length, comparator);

        for (Method method : sortedMethods) {
            if (method.isAnnotationPresent(Documentation.class)) {
                Documentation serviceDocumentation = method.getAnnotation(Documentation.class);

                // @formatter:off
                servicesSection.append("## ").append(signatureGenerator.apply(method)).append(LS);
                servicesSection.append(LS);
                servicesSection.append(serviceDocumentation.value()).append(LS);
                servicesSection.append(LS);
                
                servicesSection.append("### Parameter").append(LS);
                servicesSection.append(LS);
                for (Param param : serviceDocumentation.params()) {
                    servicesSection.append("* **").append(param.name()).append("**: ").append(param.value()).append(LS);
                }
                servicesSection.append(LS);
                
                
                if (serviceDocumentation.examples().length > 0) {                   
                    servicesSection.append("### Example").append(LS);
                    servicesSection.append(LS);
                    
                    List<Other> others = new ArrayList<Other>();
                    
                    Example[] examples = serviceDocumentation.examples();
                    for (Example example : examples) {                      
                        servicesSection.append("* ").append(example.expression()).append(LS);
                        servicesSection.append("  * ").append(example.result()).append(LS);
                        
                        Other[] otherExamples = example.others();
                        for (Other otherExample : otherExamples) {
                            others.add(otherExample);
                        }
                    }
                    servicesSection.append(LS);

                    if (others.size() > 0) {
                        servicesSection.append("| Language | Expression | Result |").append(LS);
                        servicesSection.append("| -------- | ---------- | ------ |").append(LS);
                        for (Other alternate : others) {
                            servicesSection.append("| ").append(alternate.language()).append(" | ").append(alternate.expression()).append(" | ").append(alternate.result()).append(" |").append(LS);
                        }
                        servicesSection.append(LS);
                   }
                }
                // @formatter:on
            }
        }

        buffers.add(servicesSection);

        return buffers;
    }

    /**
     * Tells if the given Class is a {@link Collection}.
     * 
     * @param argType
     *            the {@link Class}
     * @return <code>true</code> if the given Class is a {@link Collection}, <code>false</code>
     *         otherwise
     */
    private static boolean isCollection(Class<?> argType) {
        final boolean res;

        String typeName = ((Class<?>) argType).getCanonicalName();
        if ("java.util.Set".equals(typeName)) {
            res = true;
        } else if ("java.util.List".equals(typeName) || "java.util.Collection".equals(typeName)) {
            res = true;
        } else {
            res = false;
        }

        return res;

    }

    public static String prettySimpleName(Class<?> argType) {
        String typeName = argType.getCanonicalName();
        if ("org.eclipse.acceleo.query.runtime.impl.LambdaValue".equals(typeName)) {
            typeName = " x | ... ";
        }
        if (typeName.startsWith("java.lang") || typeName.startsWith("java.util")) {
            typeName = argType.getSimpleName();
        }
        if (typeName.startsWith("org.eclipse.emf")) {
            typeName = argType.getSimpleName();
        }
        if ("List".equals(typeName)) {
            typeName = "Sequence";
        }
        if ("Set".equals(typeName)) {
            typeName = "OrderedSet";
        }
        return typeName;
    }

    public static String getPrettyGenericTypename(Type type, Class<?> argType) {
        String typename = prettySimpleName(argType);
        if (type instanceof Class<?>) {
            typename = prettySimpleName((Class<?>) type);
        } else if (type instanceof ParameterizedType) {
            String canonical = ((Class<?>) argType).getCanonicalName();
            Type t = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (t instanceof Class<?>) {
                if ("java.util.Set".equals(canonical)) {
                    typename = "OrderedSet{" + prettySimpleName((Class<?>) t) + "}";
                } else if ("java.util.List".equals(canonical) || "java.util.Collection".equals(canonical)) {
                    typename = "Sequence{" + prettySimpleName((Class<?>) t) + "}";
                } else {
                    typename = "{" + prettySimpleName((Class<?>) t) + "}";
                }

            }
        }
        return typename;

    }
}
// CHECKSTYLE:ON
