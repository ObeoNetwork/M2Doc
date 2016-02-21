/*
 * XML Type:  CT_TLTimeCondition
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLTimeCondition(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLTimeCondition extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLTimeCondition.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttltimecondition1eb9type");
    
    /**
     * Gets the "tgtEl" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement getTgtEl();
    
    /**
     * True if has "tgtEl" element
     */
    boolean isSetTgtEl();
    
    /**
     * Sets the "tgtEl" element
     */
    void setTgtEl(org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement tgtEl);
    
    /**
     * Appends and returns a new empty "tgtEl" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement addNewTgtEl();
    
    /**
     * Unsets the "tgtEl" element
     */
    void unsetTgtEl();
    
    /**
     * Gets the "tn" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerTimeNodeID getTn();
    
    /**
     * True if has "tn" element
     */
    boolean isSetTn();
    
    /**
     * Sets the "tn" element
     */
    void setTn(org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerTimeNodeID tn);
    
    /**
     * Appends and returns a new empty "tn" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerTimeNodeID addNewTn();
    
    /**
     * Unsets the "tn" element
     */
    void unsetTn();
    
    /**
     * Gets the "rtn" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerRuntimeNode getRtn();
    
    /**
     * True if has "rtn" element
     */
    boolean isSetRtn();
    
    /**
     * Sets the "rtn" element
     */
    void setRtn(org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerRuntimeNode rtn);
    
    /**
     * Appends and returns a new empty "rtn" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerRuntimeNode addNewRtn();
    
    /**
     * Unsets the "rtn" element
     */
    void unsetRtn();
    
    /**
     * Gets the "evt" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent.Enum getEvt();
    
    /**
     * Gets (as xml) the "evt" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent xgetEvt();
    
    /**
     * True if has "evt" attribute
     */
    boolean isSetEvt();
    
    /**
     * Sets the "evt" attribute
     */
    void setEvt(org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent.Enum evt);
    
    /**
     * Sets (as xml) the "evt" attribute
     */
    void xsetEvt(org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent evt);
    
    /**
     * Unsets the "evt" attribute
     */
    void unsetEvt();
    
    /**
     * Gets the "delay" attribute
     */
    java.lang.Object getDelay();
    
    /**
     * Gets (as xml) the "delay" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLTime xgetDelay();
    
    /**
     * True if has "delay" attribute
     */
    boolean isSetDelay();
    
    /**
     * Sets the "delay" attribute
     */
    void setDelay(java.lang.Object delay);
    
    /**
     * Sets (as xml) the "delay" attribute
     */
    void xsetDelay(org.openxmlformats.schemas.presentationml.x2006.main.STTLTime delay);
    
    /**
     * Unsets the "delay" attribute
     */
    void unsetDelay();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
