/*
 * XML Type:  CT_CustomChartsheetView
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_CustomChartsheetView(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTCustomChartsheetView extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTCustomChartsheetView.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctcustomchartsheetview68e0type");
    
    /**
     * Gets the "pageMargins" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins getPageMargins();
    
    /**
     * True if has "pageMargins" element
     */
    boolean isSetPageMargins();
    
    /**
     * Sets the "pageMargins" element
     */
    void setPageMargins(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins pageMargins);
    
    /**
     * Appends and returns a new empty "pageMargins" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins addNewPageMargins();
    
    /**
     * Unsets the "pageMargins" element
     */
    void unsetPageMargins();
    
    /**
     * Gets the "pageSetup" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup getPageSetup();
    
    /**
     * True if has "pageSetup" element
     */
    boolean isSetPageSetup();
    
    /**
     * Sets the "pageSetup" element
     */
    void setPageSetup(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup pageSetup);
    
    /**
     * Appends and returns a new empty "pageSetup" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCsPageSetup addNewPageSetup();
    
    /**
     * Unsets the "pageSetup" element
     */
    void unsetPageSetup();
    
    /**
     * Gets the "headerFooter" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter getHeaderFooter();
    
    /**
     * True if has "headerFooter" element
     */
    boolean isSetHeaderFooter();
    
    /**
     * Sets the "headerFooter" element
     */
    void setHeaderFooter(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter headerFooter);
    
    /**
     * Appends and returns a new empty "headerFooter" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter addNewHeaderFooter();
    
    /**
     * Unsets the "headerFooter" element
     */
    void unsetHeaderFooter();
    
    /**
     * Gets the "guid" attribute
     */
    java.lang.String getGuid();
    
    /**
     * Gets (as xml) the "guid" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STGuid xgetGuid();
    
    /**
     * Sets the "guid" attribute
     */
    void setGuid(java.lang.String guid);
    
    /**
     * Sets (as xml) the "guid" attribute
     */
    void xsetGuid(org.openxmlformats.schemas.spreadsheetml.x2006.main.STGuid guid);
    
    /**
     * Gets the "scale" attribute
     */
    long getScale();
    
    /**
     * Gets (as xml) the "scale" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetScale();
    
    /**
     * True if has "scale" attribute
     */
    boolean isSetScale();
    
    /**
     * Sets the "scale" attribute
     */
    void setScale(long scale);
    
    /**
     * Sets (as xml) the "scale" attribute
     */
    void xsetScale(org.apache.xmlbeans.XmlUnsignedInt scale);
    
    /**
     * Unsets the "scale" attribute
     */
    void unsetScale();
    
    /**
     * Gets the "state" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState.Enum getState();
    
    /**
     * Gets (as xml) the "state" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState xgetState();
    
    /**
     * True if has "state" attribute
     */
    boolean isSetState();
    
    /**
     * Sets the "state" attribute
     */
    void setState(org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState.Enum state);
    
    /**
     * Sets (as xml) the "state" attribute
     */
    void xsetState(org.openxmlformats.schemas.spreadsheetml.x2006.main.STSheetState state);
    
    /**
     * Unsets the "state" attribute
     */
    void unsetState();
    
    /**
     * Gets the "zoomToFit" attribute
     */
    boolean getZoomToFit();
    
    /**
     * Gets (as xml) the "zoomToFit" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetZoomToFit();
    
    /**
     * True if has "zoomToFit" attribute
     */
    boolean isSetZoomToFit();
    
    /**
     * Sets the "zoomToFit" attribute
     */
    void setZoomToFit(boolean zoomToFit);
    
    /**
     * Sets (as xml) the "zoomToFit" attribute
     */
    void xsetZoomToFit(org.apache.xmlbeans.XmlBoolean zoomToFit);
    
    /**
     * Unsets the "zoomToFit" attribute
     */
    void unsetZoomToFit();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomChartsheetView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
