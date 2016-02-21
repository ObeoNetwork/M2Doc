/*
 * XML Type:  CT_Textbox
 * Namespace: urn:schemas-microsoft-com:vml
 * Java type: schemasMicrosoftComVml.CTTextbox
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComVml;


/**
 * An XML CT_Textbox(@urn:schemas-microsoft-com:vml).
 *
 * This is a complex type.
 */
public interface CTTextbox extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTextbox.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttextboxf712type");
    
    /**
     * Gets the "txbxContent" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent getTxbxContent();
    
    /**
     * True if has "txbxContent" element
     */
    boolean isSetTxbxContent();
    
    /**
     * Sets the "txbxContent" element
     */
    void setTxbxContent(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent txbxContent);
    
    /**
     * Appends and returns a new empty "txbxContent" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTxbxContent addNewTxbxContent();
    
    /**
     * Unsets the "txbxContent" element
     */
    void unsetTxbxContent();
    
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
     * Gets the "style" attribute
     */
    java.lang.String getStyle();
    
    /**
     * Gets (as xml) the "style" attribute
     */
    org.apache.xmlbeans.XmlString xgetStyle();
    
    /**
     * True if has "style" attribute
     */
    boolean isSetStyle();
    
    /**
     * Sets the "style" attribute
     */
    void setStyle(java.lang.String style);
    
    /**
     * Sets (as xml) the "style" attribute
     */
    void xsetStyle(org.apache.xmlbeans.XmlString style);
    
    /**
     * Unsets the "style" attribute
     */
    void unsetStyle();
    
    /**
     * Gets the "inset" attribute
     */
    java.lang.String getInset();
    
    /**
     * Gets (as xml) the "inset" attribute
     */
    org.apache.xmlbeans.XmlString xgetInset();
    
    /**
     * True if has "inset" attribute
     */
    boolean isSetInset();
    
    /**
     * Sets the "inset" attribute
     */
    void setInset(java.lang.String inset);
    
    /**
     * Sets (as xml) the "inset" attribute
     */
    void xsetInset(org.apache.xmlbeans.XmlString inset);
    
    /**
     * Unsets the "inset" attribute
     */
    void unsetInset();
    
    /**
     * Gets the "singleclick" attribute
     */
    schemasMicrosoftComOfficeOffice.STTrueFalse.Enum getSingleclick();
    
    /**
     * Gets (as xml) the "singleclick" attribute
     */
    schemasMicrosoftComOfficeOffice.STTrueFalse xgetSingleclick();
    
    /**
     * True if has "singleclick" attribute
     */
    boolean isSetSingleclick();
    
    /**
     * Sets the "singleclick" attribute
     */
    void setSingleclick(schemasMicrosoftComOfficeOffice.STTrueFalse.Enum singleclick);
    
    /**
     * Sets (as xml) the "singleclick" attribute
     */
    void xsetSingleclick(schemasMicrosoftComOfficeOffice.STTrueFalse singleclick);
    
    /**
     * Unsets the "singleclick" attribute
     */
    void unsetSingleclick();
    
    /**
     * Gets the "insetmode" attribute
     */
    schemasMicrosoftComOfficeOffice.STInsetMode.Enum getInsetmode();
    
    /**
     * Gets (as xml) the "insetmode" attribute
     */
    schemasMicrosoftComOfficeOffice.STInsetMode xgetInsetmode();
    
    /**
     * True if has "insetmode" attribute
     */
    boolean isSetInsetmode();
    
    /**
     * Sets the "insetmode" attribute
     */
    void setInsetmode(schemasMicrosoftComOfficeOffice.STInsetMode.Enum insetmode);
    
    /**
     * Sets (as xml) the "insetmode" attribute
     */
    void xsetInsetmode(schemasMicrosoftComOfficeOffice.STInsetMode insetmode);
    
    /**
     * Unsets the "insetmode" attribute
     */
    void unsetInsetmode();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static schemasMicrosoftComVml.CTTextbox newInstance() {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComVml.CTTextbox parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComVml.CTTextbox parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComVml.CTTextbox parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComVml.CTTextbox parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComVml.CTTextbox parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComVml.CTTextbox) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
