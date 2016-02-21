/*
 * XML Type:  CT_Schema
 * Namespace: http://schemas.openxmlformats.org/schemaLibrary/2006/main
 * Java type: org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.schemaLibrary.x2006.main;


/**
 * An XML CT_Schema(@http://schemas.openxmlformats.org/schemaLibrary/2006/main).
 *
 * This is a complex type.
 */
public interface CTSchema extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTSchema.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctschemac919type");
    
    /**
     * Gets the "uri" attribute
     */
    java.lang.String getUri();
    
    /**
     * Gets (as xml) the "uri" attribute
     */
    org.apache.xmlbeans.XmlString xgetUri();
    
    /**
     * True if has "uri" attribute
     */
    boolean isSetUri();
    
    /**
     * Sets the "uri" attribute
     */
    void setUri(java.lang.String uri);
    
    /**
     * Sets (as xml) the "uri" attribute
     */
    void xsetUri(org.apache.xmlbeans.XmlString uri);
    
    /**
     * Unsets the "uri" attribute
     */
    void unsetUri();
    
    /**
     * Gets the "manifestLocation" attribute
     */
    java.lang.String getManifestLocation();
    
    /**
     * Gets (as xml) the "manifestLocation" attribute
     */
    org.apache.xmlbeans.XmlString xgetManifestLocation();
    
    /**
     * True if has "manifestLocation" attribute
     */
    boolean isSetManifestLocation();
    
    /**
     * Sets the "manifestLocation" attribute
     */
    void setManifestLocation(java.lang.String manifestLocation);
    
    /**
     * Sets (as xml) the "manifestLocation" attribute
     */
    void xsetManifestLocation(org.apache.xmlbeans.XmlString manifestLocation);
    
    /**
     * Unsets the "manifestLocation" attribute
     */
    void unsetManifestLocation();
    
    /**
     * Gets the "schemaLocation" attribute
     */
    java.lang.String getSchemaLocation();
    
    /**
     * Gets (as xml) the "schemaLocation" attribute
     */
    org.apache.xmlbeans.XmlString xgetSchemaLocation();
    
    /**
     * True if has "schemaLocation" attribute
     */
    boolean isSetSchemaLocation();
    
    /**
     * Sets the "schemaLocation" attribute
     */
    void setSchemaLocation(java.lang.String schemaLocation);
    
    /**
     * Sets (as xml) the "schemaLocation" attribute
     */
    void xsetSchemaLocation(org.apache.xmlbeans.XmlString schemaLocation);
    
    /**
     * Unsets the "schemaLocation" attribute
     */
    void unsetSchemaLocation();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema newInstance() {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.schemaLibrary.x2006.main.CTSchema) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
