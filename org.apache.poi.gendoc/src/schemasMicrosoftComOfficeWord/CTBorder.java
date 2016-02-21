/*
 * XML Type:  CT_Border
 * Namespace: urn:schemas-microsoft-com:office:word
 * Java type: schemasMicrosoftComOfficeWord.CTBorder
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeWord;


/**
 * An XML CT_Border(@urn:schemas-microsoft-com:office:word).
 *
 * This is a complex type.
 */
public interface CTBorder extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTBorder.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctborderc15dtype");
    
    /**
     * Gets the "type" attribute
     */
    schemasMicrosoftComOfficeWord.STBorderType.Enum getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    schemasMicrosoftComOfficeWord.STBorderType xgetType();
    
    /**
     * True if has "type" attribute
     */
    boolean isSetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(schemasMicrosoftComOfficeWord.STBorderType.Enum type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(schemasMicrosoftComOfficeWord.STBorderType type);
    
    /**
     * Unsets the "type" attribute
     */
    void unsetType();
    
    /**
     * Gets the "width" attribute
     */
    java.math.BigInteger getWidth();
    
    /**
     * Gets (as xml) the "width" attribute
     */
    org.apache.xmlbeans.XmlPositiveInteger xgetWidth();
    
    /**
     * True if has "width" attribute
     */
    boolean isSetWidth();
    
    /**
     * Sets the "width" attribute
     */
    void setWidth(java.math.BigInteger width);
    
    /**
     * Sets (as xml) the "width" attribute
     */
    void xsetWidth(org.apache.xmlbeans.XmlPositiveInteger width);
    
    /**
     * Unsets the "width" attribute
     */
    void unsetWidth();
    
    /**
     * Gets the "shadow" attribute
     */
    schemasMicrosoftComOfficeWord.STBorderShadow.Enum getShadow();
    
    /**
     * Gets (as xml) the "shadow" attribute
     */
    schemasMicrosoftComOfficeWord.STBorderShadow xgetShadow();
    
    /**
     * True if has "shadow" attribute
     */
    boolean isSetShadow();
    
    /**
     * Sets the "shadow" attribute
     */
    void setShadow(schemasMicrosoftComOfficeWord.STBorderShadow.Enum shadow);
    
    /**
     * Sets (as xml) the "shadow" attribute
     */
    void xsetShadow(schemasMicrosoftComOfficeWord.STBorderShadow shadow);
    
    /**
     * Unsets the "shadow" attribute
     */
    void unsetShadow();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static schemasMicrosoftComOfficeWord.CTBorder newInstance() {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTBorder parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeWord.CTBorder parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeWord.CTBorder parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeWord.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
