/*
 * XML Type:  CT_TLTimeTargetElement
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLTimeTargetElement(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLTimeTargetElement extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLTimeTargetElement.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttltimetargetelementdca9type");
    
    /**
     * Gets the "sldTgt" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty getSldTgt();
    
    /**
     * True if has "sldTgt" element
     */
    boolean isSetSldTgt();
    
    /**
     * Sets the "sldTgt" element
     */
    void setSldTgt(org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty sldTgt);
    
    /**
     * Appends and returns a new empty "sldTgt" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty addNewSldTgt();
    
    /**
     * Unsets the "sldTgt" element
     */
    void unsetSldTgt();
    
    /**
     * Gets the "sndTgt" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile getSndTgt();
    
    /**
     * True if has "sndTgt" element
     */
    boolean isSetSndTgt();
    
    /**
     * Sets the "sndTgt" element
     */
    void setSndTgt(org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile sndTgt);
    
    /**
     * Appends and returns a new empty "sndTgt" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTEmbeddedWAVAudioFile addNewSndTgt();
    
    /**
     * Unsets the "sndTgt" element
     */
    void unsetSndTgt();
    
    /**
     * Gets the "spTgt" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement getSpTgt();
    
    /**
     * True if has "spTgt" element
     */
    boolean isSetSpTgt();
    
    /**
     * Sets the "spTgt" element
     */
    void setSpTgt(org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement spTgt);
    
    /**
     * Appends and returns a new empty "spTgt" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement addNewSpTgt();
    
    /**
     * Unsets the "spTgt" element
     */
    void unsetSpTgt();
    
    /**
     * Gets the "inkTgt" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId getInkTgt();
    
    /**
     * True if has "inkTgt" element
     */
    boolean isSetInkTgt();
    
    /**
     * Sets the "inkTgt" element
     */
    void setInkTgt(org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId inkTgt);
    
    /**
     * Appends and returns a new empty "inkTgt" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId addNewInkTgt();
    
    /**
     * Unsets the "inkTgt" element
     */
    void unsetInkTgt();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
