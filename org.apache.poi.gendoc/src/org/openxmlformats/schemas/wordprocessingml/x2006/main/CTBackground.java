/*
 * XML Type:  CT_Background
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_Background(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTBackground extends org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPictureBase
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTBackground.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctbackground96batype");
    
    /**
     * Gets the "color" attribute
     */
    java.lang.Object getColor();
    
    /**
     * Gets (as xml) the "color" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor xgetColor();
    
    /**
     * True if has "color" attribute
     */
    boolean isSetColor();
    
    /**
     * Sets the "color" attribute
     */
    void setColor(java.lang.Object color);
    
    /**
     * Sets (as xml) the "color" attribute
     */
    void xsetColor(org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor color);
    
    /**
     * Unsets the "color" attribute
     */
    void unsetColor();
    
    /**
     * Gets the "themeColor" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor.Enum getThemeColor();
    
    /**
     * Gets (as xml) the "themeColor" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor xgetThemeColor();
    
    /**
     * True if has "themeColor" attribute
     */
    boolean isSetThemeColor();
    
    /**
     * Sets the "themeColor" attribute
     */
    void setThemeColor(org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor.Enum themeColor);
    
    /**
     * Sets (as xml) the "themeColor" attribute
     */
    void xsetThemeColor(org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor themeColor);
    
    /**
     * Unsets the "themeColor" attribute
     */
    void unsetThemeColor();
    
    /**
     * Gets the "themeTint" attribute
     */
    byte[] getThemeTint();
    
    /**
     * Gets (as xml) the "themeTint" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber xgetThemeTint();
    
    /**
     * True if has "themeTint" attribute
     */
    boolean isSetThemeTint();
    
    /**
     * Sets the "themeTint" attribute
     */
    void setThemeTint(byte[] themeTint);
    
    /**
     * Sets (as xml) the "themeTint" attribute
     */
    void xsetThemeTint(org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber themeTint);
    
    /**
     * Unsets the "themeTint" attribute
     */
    void unsetThemeTint();
    
    /**
     * Gets the "themeShade" attribute
     */
    byte[] getThemeShade();
    
    /**
     * Gets (as xml) the "themeShade" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber xgetThemeShade();
    
    /**
     * True if has "themeShade" attribute
     */
    boolean isSetThemeShade();
    
    /**
     * Sets the "themeShade" attribute
     */
    void setThemeShade(byte[] themeShade);
    
    /**
     * Sets (as xml) the "themeShade" attribute
     */
    void xsetThemeShade(org.openxmlformats.schemas.wordprocessingml.x2006.main.STUcharHexNumber themeShade);
    
    /**
     * Unsets the "themeShade" attribute
     */
    void unsetThemeShade();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
