/*
 * XML Type:  CT_PageMar
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_PageMar(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPageMar extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPageMar.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctpagemar92a3type");
    
    /**
     * Gets the "top" attribute
     */
    java.math.BigInteger getTop();
    
    /**
     * Gets (as xml) the "top" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure xgetTop();
    
    /**
     * Sets the "top" attribute
     */
    void setTop(java.math.BigInteger top);
    
    /**
     * Sets (as xml) the "top" attribute
     */
    void xsetTop(org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure top);
    
    /**
     * Gets the "right" attribute
     */
    java.math.BigInteger getRight();
    
    /**
     * Gets (as xml) the "right" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure xgetRight();
    
    /**
     * Sets the "right" attribute
     */
    void setRight(java.math.BigInteger right);
    
    /**
     * Sets (as xml) the "right" attribute
     */
    void xsetRight(org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure right);
    
    /**
     * Gets the "bottom" attribute
     */
    java.math.BigInteger getBottom();
    
    /**
     * Gets (as xml) the "bottom" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure xgetBottom();
    
    /**
     * Sets the "bottom" attribute
     */
    void setBottom(java.math.BigInteger bottom);
    
    /**
     * Sets (as xml) the "bottom" attribute
     */
    void xsetBottom(org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure bottom);
    
    /**
     * Gets the "left" attribute
     */
    java.math.BigInteger getLeft();
    
    /**
     * Gets (as xml) the "left" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure xgetLeft();
    
    /**
     * Sets the "left" attribute
     */
    void setLeft(java.math.BigInteger left);
    
    /**
     * Sets (as xml) the "left" attribute
     */
    void xsetLeft(org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure left);
    
    /**
     * Gets the "header" attribute
     */
    java.math.BigInteger getHeader();
    
    /**
     * Gets (as xml) the "header" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure xgetHeader();
    
    /**
     * Sets the "header" attribute
     */
    void setHeader(java.math.BigInteger header);
    
    /**
     * Sets (as xml) the "header" attribute
     */
    void xsetHeader(org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure header);
    
    /**
     * Gets the "footer" attribute
     */
    java.math.BigInteger getFooter();
    
    /**
     * Gets (as xml) the "footer" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure xgetFooter();
    
    /**
     * Sets the "footer" attribute
     */
    void setFooter(java.math.BigInteger footer);
    
    /**
     * Sets (as xml) the "footer" attribute
     */
    void xsetFooter(org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure footer);
    
    /**
     * Gets the "gutter" attribute
     */
    java.math.BigInteger getGutter();
    
    /**
     * Gets (as xml) the "gutter" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure xgetGutter();
    
    /**
     * Sets the "gutter" attribute
     */
    void setGutter(java.math.BigInteger gutter);
    
    /**
     * Sets (as xml) the "gutter" attribute
     */
    void xsetGutter(org.openxmlformats.schemas.wordprocessingml.x2006.main.STTwipsMeasure gutter);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
