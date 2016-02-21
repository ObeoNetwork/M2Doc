/*
 * XML Type:  CT_Pane
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Pane(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPane extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPane.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctpaneaab1type");
    
    /**
     * Gets the "xSplit" attribute
     */
    double getXSplit();
    
    /**
     * Gets (as xml) the "xSplit" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetXSplit();
    
    /**
     * True if has "xSplit" attribute
     */
    boolean isSetXSplit();
    
    /**
     * Sets the "xSplit" attribute
     */
    void setXSplit(double xSplit);
    
    /**
     * Sets (as xml) the "xSplit" attribute
     */
    void xsetXSplit(org.apache.xmlbeans.XmlDouble xSplit);
    
    /**
     * Unsets the "xSplit" attribute
     */
    void unsetXSplit();
    
    /**
     * Gets the "ySplit" attribute
     */
    double getYSplit();
    
    /**
     * Gets (as xml) the "ySplit" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetYSplit();
    
    /**
     * True if has "ySplit" attribute
     */
    boolean isSetYSplit();
    
    /**
     * Sets the "ySplit" attribute
     */
    void setYSplit(double ySplit);
    
    /**
     * Sets (as xml) the "ySplit" attribute
     */
    void xsetYSplit(org.apache.xmlbeans.XmlDouble ySplit);
    
    /**
     * Unsets the "ySplit" attribute
     */
    void unsetYSplit();
    
    /**
     * Gets the "topLeftCell" attribute
     */
    java.lang.String getTopLeftCell();
    
    /**
     * Gets (as xml) the "topLeftCell" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef xgetTopLeftCell();
    
    /**
     * True if has "topLeftCell" attribute
     */
    boolean isSetTopLeftCell();
    
    /**
     * Sets the "topLeftCell" attribute
     */
    void setTopLeftCell(java.lang.String topLeftCell);
    
    /**
     * Sets (as xml) the "topLeftCell" attribute
     */
    void xsetTopLeftCell(org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef topLeftCell);
    
    /**
     * Unsets the "topLeftCell" attribute
     */
    void unsetTopLeftCell();
    
    /**
     * Gets the "activePane" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane.Enum getActivePane();
    
    /**
     * Gets (as xml) the "activePane" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane xgetActivePane();
    
    /**
     * True if has "activePane" attribute
     */
    boolean isSetActivePane();
    
    /**
     * Sets the "activePane" attribute
     */
    void setActivePane(org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane.Enum activePane);
    
    /**
     * Sets (as xml) the "activePane" attribute
     */
    void xsetActivePane(org.openxmlformats.schemas.spreadsheetml.x2006.main.STPane activePane);
    
    /**
     * Unsets the "activePane" attribute
     */
    void unsetActivePane();
    
    /**
     * Gets the "state" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState.Enum getState();
    
    /**
     * Gets (as xml) the "state" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState xgetState();
    
    /**
     * True if has "state" attribute
     */
    boolean isSetState();
    
    /**
     * Sets the "state" attribute
     */
    void setState(org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState.Enum state);
    
    /**
     * Sets (as xml) the "state" attribute
     */
    void xsetState(org.openxmlformats.schemas.spreadsheetml.x2006.main.STPaneState state);
    
    /**
     * Unsets the "state" attribute
     */
    void unsetState();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
