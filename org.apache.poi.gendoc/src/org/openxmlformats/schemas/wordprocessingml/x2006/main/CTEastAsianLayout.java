/*
 * XML Type:  CT_EastAsianLayout
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_EastAsianLayout(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTEastAsianLayout extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTEastAsianLayout.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cteastasianlayout0841type");
    
    /**
     * Gets the "id" attribute
     */
    java.math.BigInteger getId();
    
    /**
     * Gets (as xml) the "id" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber xgetId();
    
    /**
     * True if has "id" attribute
     */
    boolean isSetId();
    
    /**
     * Sets the "id" attribute
     */
    void setId(java.math.BigInteger id);
    
    /**
     * Sets (as xml) the "id" attribute
     */
    void xsetId(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber id);
    
    /**
     * Unsets the "id" attribute
     */
    void unsetId();
    
    /**
     * Gets the "combine" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getCombine();
    
    /**
     * Gets (as xml) the "combine" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetCombine();
    
    /**
     * True if has "combine" attribute
     */
    boolean isSetCombine();
    
    /**
     * Sets the "combine" attribute
     */
    void setCombine(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum combine);
    
    /**
     * Sets (as xml) the "combine" attribute
     */
    void xsetCombine(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff combine);
    
    /**
     * Unsets the "combine" attribute
     */
    void unsetCombine();
    
    /**
     * Gets the "combineBrackets" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STCombineBrackets.Enum getCombineBrackets();
    
    /**
     * Gets (as xml) the "combineBrackets" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STCombineBrackets xgetCombineBrackets();
    
    /**
     * True if has "combineBrackets" attribute
     */
    boolean isSetCombineBrackets();
    
    /**
     * Sets the "combineBrackets" attribute
     */
    void setCombineBrackets(org.openxmlformats.schemas.wordprocessingml.x2006.main.STCombineBrackets.Enum combineBrackets);
    
    /**
     * Sets (as xml) the "combineBrackets" attribute
     */
    void xsetCombineBrackets(org.openxmlformats.schemas.wordprocessingml.x2006.main.STCombineBrackets combineBrackets);
    
    /**
     * Unsets the "combineBrackets" attribute
     */
    void unsetCombineBrackets();
    
    /**
     * Gets the "vert" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getVert();
    
    /**
     * Gets (as xml) the "vert" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetVert();
    
    /**
     * True if has "vert" attribute
     */
    boolean isSetVert();
    
    /**
     * Sets the "vert" attribute
     */
    void setVert(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum vert);
    
    /**
     * Sets (as xml) the "vert" attribute
     */
    void xsetVert(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff vert);
    
    /**
     * Unsets the "vert" attribute
     */
    void unsetVert();
    
    /**
     * Gets the "vertCompress" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getVertCompress();
    
    /**
     * Gets (as xml) the "vertCompress" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetVertCompress();
    
    /**
     * True if has "vertCompress" attribute
     */
    boolean isSetVertCompress();
    
    /**
     * Sets the "vertCompress" attribute
     */
    void setVertCompress(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum vertCompress);
    
    /**
     * Sets (as xml) the "vertCompress" attribute
     */
    void xsetVertCompress(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff vertCompress);
    
    /**
     * Unsets the "vertCompress" attribute
     */
    void unsetVertCompress();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
