/*
 * XML Type:  CT_PBdr
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_PBdr(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPBdr extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPBdr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctpbdre388type");
    
    /**
     * Gets the "top" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder getTop();
    
    /**
     * True if has "top" element
     */
    boolean isSetTop();
    
    /**
     * Sets the "top" element
     */
    void setTop(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder top);
    
    /**
     * Appends and returns a new empty "top" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder addNewTop();
    
    /**
     * Unsets the "top" element
     */
    void unsetTop();
    
    /**
     * Gets the "left" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder getLeft();
    
    /**
     * True if has "left" element
     */
    boolean isSetLeft();
    
    /**
     * Sets the "left" element
     */
    void setLeft(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder left);
    
    /**
     * Appends and returns a new empty "left" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder addNewLeft();
    
    /**
     * Unsets the "left" element
     */
    void unsetLeft();
    
    /**
     * Gets the "bottom" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder getBottom();
    
    /**
     * True if has "bottom" element
     */
    boolean isSetBottom();
    
    /**
     * Sets the "bottom" element
     */
    void setBottom(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder bottom);
    
    /**
     * Appends and returns a new empty "bottom" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder addNewBottom();
    
    /**
     * Unsets the "bottom" element
     */
    void unsetBottom();
    
    /**
     * Gets the "right" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder getRight();
    
    /**
     * True if has "right" element
     */
    boolean isSetRight();
    
    /**
     * Sets the "right" element
     */
    void setRight(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder right);
    
    /**
     * Appends and returns a new empty "right" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder addNewRight();
    
    /**
     * Unsets the "right" element
     */
    void unsetRight();
    
    /**
     * Gets the "between" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder getBetween();
    
    /**
     * True if has "between" element
     */
    boolean isSetBetween();
    
    /**
     * Sets the "between" element
     */
    void setBetween(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder between);
    
    /**
     * Appends and returns a new empty "between" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder addNewBetween();
    
    /**
     * Unsets the "between" element
     */
    void unsetBetween();
    
    /**
     * Gets the "bar" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder getBar();
    
    /**
     * True if has "bar" element
     */
    boolean isSetBar();
    
    /**
     * Sets the "bar" element
     */
    void setBar(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder bar);
    
    /**
     * Appends and returns a new empty "bar" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder addNewBar();
    
    /**
     * Unsets the "bar" element
     */
    void unsetBar();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPBdr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
