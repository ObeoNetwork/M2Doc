/*
 * XML Type:  CT_Border
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_Border(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTBorder extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTBorder.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctbordercdfctype");
    
    /**
     * Gets the "val" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder.Enum getVal();
    
    /**
     * Gets (as xml) the "val" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder xgetVal();
    
    /**
     * Sets the "val" attribute
     */
    void setVal(org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder.Enum val);
    
    /**
     * Sets (as xml) the "val" attribute
     */
    void xsetVal(org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder val);
    
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
     * Gets the "sz" attribute
     */
    java.math.BigInteger getSz();
    
    /**
     * Gets (as xml) the "sz" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STEighthPointMeasure xgetSz();
    
    /**
     * True if has "sz" attribute
     */
    boolean isSetSz();
    
    /**
     * Sets the "sz" attribute
     */
    void setSz(java.math.BigInteger sz);
    
    /**
     * Sets (as xml) the "sz" attribute
     */
    void xsetSz(org.openxmlformats.schemas.wordprocessingml.x2006.main.STEighthPointMeasure sz);
    
    /**
     * Unsets the "sz" attribute
     */
    void unsetSz();
    
    /**
     * Gets the "space" attribute
     */
    java.math.BigInteger getSpace();
    
    /**
     * Gets (as xml) the "space" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STPointMeasure xgetSpace();
    
    /**
     * True if has "space" attribute
     */
    boolean isSetSpace();
    
    /**
     * Sets the "space" attribute
     */
    void setSpace(java.math.BigInteger space);
    
    /**
     * Sets (as xml) the "space" attribute
     */
    void xsetSpace(org.openxmlformats.schemas.wordprocessingml.x2006.main.STPointMeasure space);
    
    /**
     * Unsets the "space" attribute
     */
    void unsetSpace();
    
    /**
     * Gets the "shadow" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getShadow();
    
    /**
     * Gets (as xml) the "shadow" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetShadow();
    
    /**
     * True if has "shadow" attribute
     */
    boolean isSetShadow();
    
    /**
     * Sets the "shadow" attribute
     */
    void setShadow(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum shadow);
    
    /**
     * Sets (as xml) the "shadow" attribute
     */
    void xsetShadow(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff shadow);
    
    /**
     * Unsets the "shadow" attribute
     */
    void unsetShadow();
    
    /**
     * Gets the "frame" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getFrame();
    
    /**
     * Gets (as xml) the "frame" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetFrame();
    
    /**
     * True if has "frame" attribute
     */
    boolean isSetFrame();
    
    /**
     * Sets the "frame" attribute
     */
    void setFrame(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum frame);
    
    /**
     * Sets (as xml) the "frame" attribute
     */
    void xsetFrame(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff frame);
    
    /**
     * Unsets the "frame" attribute
     */
    void unsetFrame();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
