/*
 * XML Type:  CT_R
 * Namespace: urn:schemas-microsoft-com:office:office
 * Java type: schemasMicrosoftComOfficeOffice.CTR
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeOffice;


/**
 * An XML CT_R(@urn:schemas-microsoft-com:office:office).
 *
 * This is a complex type.
 */
public interface CTR extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTR.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctr0eabtype");
    
    /**
     * Gets a List of "proxy" elements
     */
    java.util.List<schemasMicrosoftComOfficeOffice.CTProxy> getProxyList();
    
    /**
     * Gets array of all "proxy" elements
     * @deprecated
     */
    schemasMicrosoftComOfficeOffice.CTProxy[] getProxyArray();
    
    /**
     * Gets ith "proxy" element
     */
    schemasMicrosoftComOfficeOffice.CTProxy getProxyArray(int i);
    
    /**
     * Returns number of "proxy" element
     */
    int sizeOfProxyArray();
    
    /**
     * Sets array of all "proxy" element
     */
    void setProxyArray(schemasMicrosoftComOfficeOffice.CTProxy[] proxyArray);
    
    /**
     * Sets ith "proxy" element
     */
    void setProxyArray(int i, schemasMicrosoftComOfficeOffice.CTProxy proxy);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "proxy" element
     */
    schemasMicrosoftComOfficeOffice.CTProxy insertNewProxy(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "proxy" element
     */
    schemasMicrosoftComOfficeOffice.CTProxy addNewProxy();
    
    /**
     * Removes the ith "proxy" element
     */
    void removeProxy(int i);
    
    /**
     * Gets the "id" attribute
     */
    java.lang.String getId();
    
    /**
     * Gets (as xml) the "id" attribute
     */
    org.apache.xmlbeans.XmlString xgetId();
    
    /**
     * Sets the "id" attribute
     */
    void setId(java.lang.String id);
    
    /**
     * Sets (as xml) the "id" attribute
     */
    void xsetId(org.apache.xmlbeans.XmlString id);
    
    /**
     * Gets the "type" attribute
     */
    schemasMicrosoftComOfficeOffice.STRType.Enum getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    schemasMicrosoftComOfficeOffice.STRType xgetType();
    
    /**
     * True if has "type" attribute
     */
    boolean isSetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(schemasMicrosoftComOfficeOffice.STRType.Enum type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(schemasMicrosoftComOfficeOffice.STRType type);
    
    /**
     * Unsets the "type" attribute
     */
    void unsetType();
    
    /**
     * Gets the "how" attribute
     */
    schemasMicrosoftComOfficeOffice.STHow.Enum getHow();
    
    /**
     * Gets (as xml) the "how" attribute
     */
    schemasMicrosoftComOfficeOffice.STHow xgetHow();
    
    /**
     * True if has "how" attribute
     */
    boolean isSetHow();
    
    /**
     * Sets the "how" attribute
     */
    void setHow(schemasMicrosoftComOfficeOffice.STHow.Enum how);
    
    /**
     * Sets (as xml) the "how" attribute
     */
    void xsetHow(schemasMicrosoftComOfficeOffice.STHow how);
    
    /**
     * Unsets the "how" attribute
     */
    void unsetHow();
    
    /**
     * Gets the "idref" attribute
     */
    java.lang.String getIdref();
    
    /**
     * Gets (as xml) the "idref" attribute
     */
    org.apache.xmlbeans.XmlString xgetIdref();
    
    /**
     * True if has "idref" attribute
     */
    boolean isSetIdref();
    
    /**
     * Sets the "idref" attribute
     */
    void setIdref(java.lang.String idref);
    
    /**
     * Sets (as xml) the "idref" attribute
     */
    void xsetIdref(org.apache.xmlbeans.XmlString idref);
    
    /**
     * Unsets the "idref" attribute
     */
    void unsetIdref();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static schemasMicrosoftComOfficeOffice.CTR newInstance() {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTR parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.CTR parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.CTR parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.CTR) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
