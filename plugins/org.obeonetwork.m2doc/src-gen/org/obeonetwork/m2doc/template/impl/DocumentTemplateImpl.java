/**
 *  Copyright (c) 2016, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 */
package org.obeonetwork.m2doc.template.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getHeaders <em>Headers</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getFooters <em>Footers</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getBody <em>Body</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getInputStream <em>Input Stream</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getOpcPackage <em>Opc Package</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getDocument <em>Document</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getTemplates <em>Templates</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getM2DocVersion <em>M2 Doc Version</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getMetamodels <em>Metamodels</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getImports <em>Imports</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getExtend <em>Extend</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getQualifiedName <em>Qualified Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentTemplateImpl extends MinimalEObjectImpl.Container implements DocumentTemplate {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016, 2025 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The cached value of the '{@link #getHeaders() <em>Headers</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHeaders()
     * @generated
     * @ordered
     */
    protected EList<Block> headers;

    /**
     * The cached value of the '{@link #getFooters() <em>Footers</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getFooters()
     * @generated
     * @ordered
     */
    protected EList<Block> footers;

    /**
     * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getBody()
     * @generated
     * @ordered
     */
    protected Block body;

    /**
     * The default value of the '{@link #getInputStream() <em>Input Stream</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getInputStream()
     * @generated
     * @ordered
     */
    protected static final InputStream INPUT_STREAM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInputStream() <em>Input Stream</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getInputStream()
     * @generated
     * @ordered
     */
    protected InputStream inputStream = INPUT_STREAM_EDEFAULT;

    /**
     * The default value of the '{@link #getOpcPackage() <em>Opc Package</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOpcPackage()
     * @generated
     * @ordered
     */
    protected static final OPCPackage OPC_PACKAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOpcPackage() <em>Opc Package</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getOpcPackage()
     * @generated
     * @ordered
     */
    protected OPCPackage opcPackage = OPC_PACKAGE_EDEFAULT;

    /**
     * The default value of the '{@link #getDocument() <em>Document</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDocument()
     * @generated
     * @ordered
     */
    protected static final XWPFDocument DOCUMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDocument() <em>Document</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getDocument()
     * @generated
     * @ordered
     */
    protected XWPFDocument document = DOCUMENT_EDEFAULT;

    /**
     * The cached value of the '{@link #getTemplates() <em>Templates</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTemplates()
     * @generated
     * @ordered
     */
    protected EList<Template> templates;

    /**
     * The default value of the '{@link #getProperties() <em>Properties</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected static final TemplateCustomProperties PROPERTIES_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getProperties()
     * @generated
     * @ordered
     */
    protected TemplateCustomProperties properties = PROPERTIES_EDEFAULT;

    /**
     * The default value of the '{@link #getM2DocVersion() <em>M2 Doc Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getM2DocVersion()
     * @generated
     * @ordered
     */
    protected static final String M2_DOC_VERSION_EDEFAULT = null;

    /**
     * The default value of the '{@link #getExtend() <em>Extend</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getExtend()
     * @generated
     * @ordered
     */
    protected static final String EXTEND_EDEFAULT = null;

    /**
     * The default value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getQualifiedName()
     * @generated
     * @ordered
     */
    protected static final String QUALIFIED_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQualifiedName() <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getQualifiedName()
     * @generated
     * @ordered
     */
    protected String qualifiedName = QUALIFIED_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DocumentTemplateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.DOCUMENT_TEMPLATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Block> getHeaders() {
        if (headers == null) {
            headers = new EObjectContainmentEList<Block>(Block.class, this, TemplatePackage.DOCUMENT_TEMPLATE__HEADERS);
        }
        return headers;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Block> getFooters() {
        if (footers == null) {
            footers = new EObjectContainmentEList<Block>(Block.class, this, TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS);
        }
        return footers;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Block getBody() {
        return body;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBody(Block newBody, NotificationChain msgs) {
        Block oldBody = body;
        body = newBody;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    TemplatePackage.DOCUMENT_TEMPLATE__BODY, oldBody, newBody);
            if (msgs == null)
                msgs = notification;
            else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBody(Block newBody) {
        if (newBody != body) {
            NotificationChain msgs = null;
            if (body != null)
                msgs = ((InternalEObject) body).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - TemplatePackage.DOCUMENT_TEMPLATE__BODY, null, msgs);
            if (newBody != null)
                msgs = ((InternalEObject) newBody).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - TemplatePackage.DOCUMENT_TEMPLATE__BODY, null, msgs);
            msgs = basicSetBody(newBody, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__BODY, newBody,
                    newBody));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInputStream(InputStream newInputStream) {
        InputStream oldInputStream = inputStream;
        inputStream = newInputStream;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__INPUT_STREAM,
                    oldInputStream, inputStream));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public OPCPackage getOpcPackage() {
        return opcPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setOpcPackage(OPCPackage newOpcPackage) {
        OPCPackage oldOpcPackage = opcPackage;
        opcPackage = newOpcPackage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__OPC_PACKAGE,
                    oldOpcPackage, opcPackage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public XWPFDocument getDocument() {
        return document;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDocument(XWPFDocument newDocument) {
        XWPFDocument oldDocument = document;
        document = newDocument;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__DOCUMENT,
                    oldDocument, document));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Template> getTemplates() {
        if (templates == null) {
            templates = new EObjectContainmentWithInverseEList<Template>(Template.class, this,
                    TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES, TemplatePackage.TEMPLATE__DOCUMENT_TEMPLATE);
        }
        return templates;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TemplateCustomProperties getProperties() {
        return properties;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setProperties(TemplateCustomProperties newProperties) {
        TemplateCustomProperties oldProperties = properties;
        properties = newProperties;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__PROPERTIES,
                    oldProperties, properties));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public String getM2DocVersion() {
        final String res;

        final TemplateCustomProperties properties = getProperties();
        if (properties != null) {
            res = properties.getM2DocVersion();
        } else {
            res = null;
        }

        return res;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public EList<String> getMetamodels() {
        final EList<String> res;

        final TemplateCustomProperties properties = getProperties();
        if (properties != null) {
            final List<String> list = new ArrayList<>(properties.getPackagesURIs());
            res = ECollections.unmodifiableEList(ECollections.asEList(list));
        } else {
            res = ECollections.emptyEList();
        }

        return res;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public EList<String> getImports() {
        final EList<String> res;

        final TemplateCustomProperties properties = getProperties();
        if (properties != null) {
            final List<String> list = new ArrayList<>(properties.getImports());
            res = ECollections.unmodifiableEList(ECollections.asEList(list));
        } else {
            res = ECollections.emptyEList();
        }

        return res;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated NOT
     */
    @Override
    public String getExtend() {
        final String res;

        final TemplateCustomProperties properties = getProperties();
        if (properties != null) {
            res = properties.getExtend();
        } else {
            res = null;
        }

        return res;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getQualifiedName() {
        return qualifiedName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setQualifiedName(String newQualifiedName) {
        String oldQualifiedName = qualifiedName;
        qualifiedName = newQualifiedName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__QUALIFIED_NAME,
                    oldQualifiedName, qualifiedName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES:
                return ((InternalEList<InternalEObject>) (InternalEList<?>) getTemplates()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                return ((InternalEList<?>) getHeaders()).basicRemove(otherEnd, msgs);
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                return ((InternalEList<?>) getFooters()).basicRemove(otherEnd, msgs);
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                return basicSetBody(null, msgs);
            case TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES:
                return ((InternalEList<?>) getTemplates()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                return getHeaders();
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                return getFooters();
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                return getBody();
            case TemplatePackage.DOCUMENT_TEMPLATE__INPUT_STREAM:
                return getInputStream();
            case TemplatePackage.DOCUMENT_TEMPLATE__OPC_PACKAGE:
                return getOpcPackage();
            case TemplatePackage.DOCUMENT_TEMPLATE__DOCUMENT:
                return getDocument();
            case TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES:
                return getTemplates();
            case TemplatePackage.DOCUMENT_TEMPLATE__PROPERTIES:
                return getProperties();
            case TemplatePackage.DOCUMENT_TEMPLATE__M2_DOC_VERSION:
                return getM2DocVersion();
            case TemplatePackage.DOCUMENT_TEMPLATE__METAMODELS:
                return getMetamodels();
            case TemplatePackage.DOCUMENT_TEMPLATE__IMPORTS:
                return getImports();
            case TemplatePackage.DOCUMENT_TEMPLATE__EXTEND:
                return getExtend();
            case TemplatePackage.DOCUMENT_TEMPLATE__QUALIFIED_NAME:
                return getQualifiedName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                getHeaders().clear();
                getHeaders().addAll((Collection<? extends Block>) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                getFooters().clear();
                getFooters().addAll((Collection<? extends Block>) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                setBody((Block) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__INPUT_STREAM:
                setInputStream((InputStream) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__OPC_PACKAGE:
                setOpcPackage((OPCPackage) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__DOCUMENT:
                setDocument((XWPFDocument) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES:
                getTemplates().clear();
                getTemplates().addAll((Collection<? extends Template>) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__PROPERTIES:
                setProperties((TemplateCustomProperties) newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__QUALIFIED_NAME:
                setQualifiedName((String) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                getHeaders().clear();
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                getFooters().clear();
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                setBody((Block) null);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__INPUT_STREAM:
                setInputStream(INPUT_STREAM_EDEFAULT);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__OPC_PACKAGE:
                setOpcPackage(OPC_PACKAGE_EDEFAULT);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__DOCUMENT:
                setDocument(DOCUMENT_EDEFAULT);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES:
                getTemplates().clear();
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__PROPERTIES:
                setProperties(PROPERTIES_EDEFAULT);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__QUALIFIED_NAME:
                setQualifiedName(QUALIFIED_NAME_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                return headers != null && !headers.isEmpty();
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                return footers != null && !footers.isEmpty();
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                return body != null;
            case TemplatePackage.DOCUMENT_TEMPLATE__INPUT_STREAM:
                return INPUT_STREAM_EDEFAULT == null ? inputStream != null : !INPUT_STREAM_EDEFAULT.equals(inputStream);
            case TemplatePackage.DOCUMENT_TEMPLATE__OPC_PACKAGE:
                return OPC_PACKAGE_EDEFAULT == null ? opcPackage != null : !OPC_PACKAGE_EDEFAULT.equals(opcPackage);
            case TemplatePackage.DOCUMENT_TEMPLATE__DOCUMENT:
                return DOCUMENT_EDEFAULT == null ? document != null : !DOCUMENT_EDEFAULT.equals(document);
            case TemplatePackage.DOCUMENT_TEMPLATE__TEMPLATES:
                return templates != null && !templates.isEmpty();
            case TemplatePackage.DOCUMENT_TEMPLATE__PROPERTIES:
                return PROPERTIES_EDEFAULT == null ? properties != null : !PROPERTIES_EDEFAULT.equals(properties);
            case TemplatePackage.DOCUMENT_TEMPLATE__M2_DOC_VERSION:
                return M2_DOC_VERSION_EDEFAULT == null ? getM2DocVersion() != null
                        : !M2_DOC_VERSION_EDEFAULT.equals(getM2DocVersion());
            case TemplatePackage.DOCUMENT_TEMPLATE__METAMODELS:
                return !getMetamodels().isEmpty();
            case TemplatePackage.DOCUMENT_TEMPLATE__IMPORTS:
                return !getImports().isEmpty();
            case TemplatePackage.DOCUMENT_TEMPLATE__EXTEND:
                return EXTEND_EDEFAULT == null ? getExtend() != null : !EXTEND_EDEFAULT.equals(getExtend());
            case TemplatePackage.DOCUMENT_TEMPLATE__QUALIFIED_NAME:
                return QUALIFIED_NAME_EDEFAULT == null ? qualifiedName != null
                        : !QUALIFIED_NAME_EDEFAULT.equals(qualifiedName);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (inputStream: ");
        result.append(inputStream);
        result.append(", opcPackage: ");
        result.append(opcPackage);
        result.append(", document: ");
        result.append(document);
        result.append(", properties: ");
        result.append(properties);
        result.append(", qualifiedName: ");
        result.append(qualifiedName);
        result.append(')');
        return result.toString();
    }

    /**
     * @see java.io.Closeable#close()
     * @generated NOT
     */
    @Override
    public void close() throws IOException {
        getDocument().close();
        getOpcPackage().close();
        getInputStream().close();
    }

} // DocumentTemplateImpl
