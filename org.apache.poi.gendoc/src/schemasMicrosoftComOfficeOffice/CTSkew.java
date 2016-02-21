/*
 * XML Type:  CT_Skew
 * Namespace: urn:schemas-microsoft-com:office:office
 * Java type: schemasMicrosoftComOfficeOffice.CTSkew
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeOffice;


/**
 * An XML CT_Skew(@urn:schemas-microsoft-com:office:office).
 *
 * This is a complex type.
 */
public interface CTSkew extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTSkew.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctskew006dtype");
    
    /**
     * Gets the "ext" attribute
     */
    schemasMicrosoftComVml.STExt.Enum getExt();
    
    /**
     * Gets (as xml) the "ext" attribute
     */
    schemasMicrosoftComVml.STExt xgetExt();
    
    /**
     * True if has "ext" attribute
     */
    boolean isSetExt();
    
    /**
     * Sets the "ext" attribute
     */
    void setExt(schemasMicrosoftComVml.STExt.Enum ext);
    
    /**
     * Sets (as xml) the "ext" attribute
     */
    void xsetExt(schemasMicrosoftComVml.STExt ext);
    
    /**
     * Unsets the "ext" attribute
     */
    void unsetExt();
    
    /**
     * Gets the "id" attribute
     */
    java.lang.String getId();
    
    /**
     * Gets (as xml) the "id" attribute
     */
    org.apache.xmlbeans.XmlString xgetId();
    
    /**
     * True if has "id" attribute
     */
    boolean isSetId();
    
    /**
     * Sets the "id" attribute
     */
    void setId(java.lang.String id);
    
    /**
     * Sets (as xml) the "id" attribute
     */
    void xsetId(org.apache.xmlbeans.XmlString id);
    
    /**
     * Unsets the "id" attribute
     */
    void unsetId();
    
    /**
     * Gets the "on" attribute
     */
    schemasMicrosoftComOfficeOffice.STTrueFalse.Enum getOn();
    
    /**
     * Gets (as xml) the "on" attribute
     */
    schemasMicrosoftComOfficeOffice.STTrueFalse xgetOn();
    
    /**
     * True if has "on" attribute
     */
    boolean isSetOn();
    
    /**
     * Sets the "on" attribute
     */
    void setOn(schemasMicrosoftComOfficeOffice.STTrueFalse.Enum on);
    
    /**
     * Sets (as xml) the "on" attribute
     */
    void xsetOn(schemasMicrosoftComOfficeOffice.STTrueFalse on);
    
    /**
     * Unsets the "on" attribute
     */
    void unsetOn();
    
    /**
     * Gets the "offset" attribute
     */
    java.lang.String getOffset();
    
    /**
     * Gets (as xml) the "offset" attribute
     */
    org.apache.xmlbeans.XmlString xgetOffset();
    
    /**
     * True if has "offset" attribute
     */
    boolean isSetOffset();
    
    /**
     * Sets the "offset" attribute
     */
    void setOffset(java.lang.String offset);
    
    /**
     * Sets (as xml) the "offset" attribute
     */
    void xsetOffset(org.apache.xmlbeans.XmlString offset);
    
    /**
     * Unsets the "offset" attribute
     */
    void unsetOffset();
    
    /**
     * Gets the "origin" attribute
     */
    java.lang.String getOrigin();
    
    /**
     * Gets (as xml) the "origin" attribute
     */
    org.apache.xmlbeans.XmlString xgetOrigin();
    
    /**
     * True if has "origin" attribute
     */
    boolean isSetOrigin();
    
    /**
     * Sets the "origin" attribute
     */
    void setOrigin(java.lang.String origin);
    
    /**
     * Sets (as xml) the "origin" attribute
     */
    void xsetOrigin(org.apache.xmlbeans.XmlString origin);
    
    /**
     * Unsets the "origin" attribute
     */
    void unsetOrigin();
    
    /**
     * Gets the "matrix" attribute
     */
    java.lang.String getMatrix();
    
    /**
     * Gets (as xml) the "matrix" attribute
     */
    org.apache.xmlbeans.XmlString xgetMatrix();
    
    /**
     * True if has "matrix" attribute
     */
    boolean isSetMatrix();
    
    /**
     * Sets the "matrix" attribute
     */
    void setMatrix(java.lang.String matrix);
    
    /**
     * Sets (as xml) the "matrix" attribute
     */
    void xsetMatrix(org.apache.xmlbeans.XmlString matrix);
    
    /**
     * Unsets the "matrix" attribute
     */
    void unsetMatrix();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static schemasMicrosoftComOfficeOffice.CTSkew newInstance() {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.CTSkew parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.CTSkew) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
