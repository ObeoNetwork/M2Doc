/*
 * XML Type:  CT_ReadingModeInkLockDown
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_ReadingModeInkLockDown(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTReadingModeInkLockDown extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTReadingModeInkLockDown.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctreadingmodeinklockdown1e84type");
    
    /**
     * Gets the "actualPg" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getActualPg();
    
    /**
     * Gets (as xml) the "actualPg" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetActualPg();
    
    /**
     * Sets the "actualPg" attribute
     */
    void setActualPg(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum actualPg);
    
    /**
     * Sets (as xml) the "actualPg" attribute
     */
    void xsetActualPg(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff actualPg);
    
    /**
     * Gets the "w" attribute
     */
    java.math.BigInteger getW();
    
    /**
     * Gets (as xml) the "w" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STPixelsMeasure xgetW();
    
    /**
     * Sets the "w" attribute
     */
    void setW(java.math.BigInteger w);
    
    /**
     * Sets (as xml) the "w" attribute
     */
    void xsetW(org.openxmlformats.schemas.wordprocessingml.x2006.main.STPixelsMeasure w);
    
    /**
     * Gets the "h" attribute
     */
    java.math.BigInteger getH();
    
    /**
     * Gets (as xml) the "h" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STPixelsMeasure xgetH();
    
    /**
     * Sets the "h" attribute
     */
    void setH(java.math.BigInteger h);
    
    /**
     * Sets (as xml) the "h" attribute
     */
    void xsetH(org.openxmlformats.schemas.wordprocessingml.x2006.main.STPixelsMeasure h);
    
    /**
     * Gets the "fontSz" attribute
     */
    java.math.BigInteger getFontSz();
    
    /**
     * Gets (as xml) the "fontSz" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber xgetFontSz();
    
    /**
     * Sets the "fontSz" attribute
     */
    void setFontSz(java.math.BigInteger fontSz);
    
    /**
     * Sets (as xml) the "fontSz" attribute
     */
    void xsetFontSz(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber fontSz);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTReadingModeInkLockDown) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
