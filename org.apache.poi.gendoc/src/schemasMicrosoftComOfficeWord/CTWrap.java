/*
 * XML Type:  CT_Wrap
 * Namespace: urn:schemas-microsoft-com:office:word
 * Java type: schemasMicrosoftComOfficeWord.CTWrap
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeWord;


/**
 * An XML CT_Wrap(@urn:schemas-microsoft-com:office:word).
 *
 * This is a complex type.
 */
public interface CTWrap extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTWrap.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctwrapbc3btype");
    
    /**
     * Gets the "type" attribute
     */
    schemasMicrosoftComOfficeWord.STWrapType.Enum getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    schemasMicrosoftComOfficeWord.STWrapType xgetType();
    
    /**
     * True if has "type" attribute
     */
    boolean isSetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(schemasMicrosoftComOfficeWord.STWrapType.Enum type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(schemasMicrosoftComOfficeWord.STWrapType type);
    
    /**
     * Unsets the "type" attribute
     */
    void unsetType();
    
    /**
     * Gets the "side" attribute
     */
    schemasMicrosoftComOfficeWord.STWrapSide.Enum getSide();
    
    /**
     * Gets (as xml) the "side" attribute
     */
    schemasMicrosoftComOfficeWord.STWrapSide xgetSide();
    
    /**
     * True if has "side" attribute
     */
    boolean isSetSide();
    
    /**
     * Sets the "side" attribute
     */
    void setSide(schemasMicrosoftComOfficeWord.STWrapSide.Enum side);
    
    /**
     * Sets (as xml) the "side" attribute
     */
    void xsetSide(schemasMicrosoftComOfficeWord.STWrapSide side);
    
    /**
     * Unsets the "side" attribute
     */
    void unsetSide();
    
    /**
     * Gets the "anchorx" attribute
     */
    schemasMicrosoftComOfficeWord.STHorizontalAnchor.Enum getAnchorx();
    
    /**
     * Gets (as xml) the "anchorx" attribute
     */
    schemasMicrosoftComOfficeWord.STHorizontalAnchor xgetAnchorx();
    
    /**
     * True if has "anchorx" attribute
     */
    boolean isSetAnchorx();
    
    /**
     * Sets the "anchorx" attribute
     */
    void setAnchorx(schemasMicrosoftComOfficeWord.STHorizontalAnchor.Enum anchorx);
    
    /**
     * Sets (as xml) the "anchorx" attribute
     */
    void xsetAnchorx(schemasMicrosoftComOfficeWord.STHorizontalAnchor anchorx);
    
    /**
     * Unsets the "anchorx" attribute
     */
    void unsetAnchorx();
    
    /**
     * Gets the "anchory" attribute
     */
    schemasMicrosoftComOfficeWord.STVerticalAnchor.Enum getAnchory();
    
    /**
     * Gets (as xml) the "anchory" attribute
     */
    schemasMicrosoftComOfficeWord.STVerticalAnchor xgetAnchory();
    
    /**
     * True if has "anchory" attribute
     */
    boolean isSetAnchory();
    
    /**
     * Sets the "anchory" attribute
     */
    void setAnchory(schemasMicrosoftComOfficeWord.STVerticalAnchor.Enum anchory);
    
    /**
     * Sets (as xml) the "anchory" attribute
     */
    void xsetAnchory(schemasMicrosoftComOfficeWord.STVerticalAnchor anchory);
    
    /**
     * Unsets the "anchory" attribute
     */
    void unsetAnchory();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static schemasMicrosoftComOfficeWord.CTWrap newInstance() {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeWord.CTWrap parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeWord.CTWrap parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeWord.CTWrap parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeWord.CTWrap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
