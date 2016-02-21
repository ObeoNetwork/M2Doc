/*
 * An XML attribute type.
 * Localname: spt
 * Namespace: urn:schemas-microsoft-com:office:office
 * Java type: schemasMicrosoftComOfficeOffice.SptAttribute
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeOffice;


/**
 * A document containing one spt(@urn:schemas-microsoft-com:office:office) attribute.
 *
 * This is a complex type.
 */
public interface SptAttribute extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(SptAttribute.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("sptb96aattrtypetype");
    
    /**
     * Gets the "spt" attribute
     */
    float getSpt();
    
    /**
     * Gets (as xml) the "spt" attribute
     */
    org.apache.xmlbeans.XmlFloat xgetSpt();
    
    /**
     * True if has "spt" attribute
     */
    boolean isSetSpt();
    
    /**
     * Sets the "spt" attribute
     */
    void setSpt(float spt);
    
    /**
     * Sets (as xml) the "spt" attribute
     */
    void xsetSpt(org.apache.xmlbeans.XmlFloat spt);
    
    /**
     * Unsets the "spt" attribute
     */
    void unsetSpt();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static schemasMicrosoftComOfficeOffice.SptAttribute newInstance() {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.SptAttribute parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.SptAttribute) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
