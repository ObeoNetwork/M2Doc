/*
 * XML Type:  CT_PrintOptions
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_PrintOptions(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPrintOptions extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPrintOptions.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctprintoptions943atype");
    
    /**
     * Gets the "horizontalCentered" attribute
     */
    boolean getHorizontalCentered();
    
    /**
     * Gets (as xml) the "horizontalCentered" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetHorizontalCentered();
    
    /**
     * True if has "horizontalCentered" attribute
     */
    boolean isSetHorizontalCentered();
    
    /**
     * Sets the "horizontalCentered" attribute
     */
    void setHorizontalCentered(boolean horizontalCentered);
    
    /**
     * Sets (as xml) the "horizontalCentered" attribute
     */
    void xsetHorizontalCentered(org.apache.xmlbeans.XmlBoolean horizontalCentered);
    
    /**
     * Unsets the "horizontalCentered" attribute
     */
    void unsetHorizontalCentered();
    
    /**
     * Gets the "verticalCentered" attribute
     */
    boolean getVerticalCentered();
    
    /**
     * Gets (as xml) the "verticalCentered" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetVerticalCentered();
    
    /**
     * True if has "verticalCentered" attribute
     */
    boolean isSetVerticalCentered();
    
    /**
     * Sets the "verticalCentered" attribute
     */
    void setVerticalCentered(boolean verticalCentered);
    
    /**
     * Sets (as xml) the "verticalCentered" attribute
     */
    void xsetVerticalCentered(org.apache.xmlbeans.XmlBoolean verticalCentered);
    
    /**
     * Unsets the "verticalCentered" attribute
     */
    void unsetVerticalCentered();
    
    /**
     * Gets the "headings" attribute
     */
    boolean getHeadings();
    
    /**
     * Gets (as xml) the "headings" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetHeadings();
    
    /**
     * True if has "headings" attribute
     */
    boolean isSetHeadings();
    
    /**
     * Sets the "headings" attribute
     */
    void setHeadings(boolean headings);
    
    /**
     * Sets (as xml) the "headings" attribute
     */
    void xsetHeadings(org.apache.xmlbeans.XmlBoolean headings);
    
    /**
     * Unsets the "headings" attribute
     */
    void unsetHeadings();
    
    /**
     * Gets the "gridLines" attribute
     */
    boolean getGridLines();
    
    /**
     * Gets (as xml) the "gridLines" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetGridLines();
    
    /**
     * True if has "gridLines" attribute
     */
    boolean isSetGridLines();
    
    /**
     * Sets the "gridLines" attribute
     */
    void setGridLines(boolean gridLines);
    
    /**
     * Sets (as xml) the "gridLines" attribute
     */
    void xsetGridLines(org.apache.xmlbeans.XmlBoolean gridLines);
    
    /**
     * Unsets the "gridLines" attribute
     */
    void unsetGridLines();
    
    /**
     * Gets the "gridLinesSet" attribute
     */
    boolean getGridLinesSet();
    
    /**
     * Gets (as xml) the "gridLinesSet" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetGridLinesSet();
    
    /**
     * True if has "gridLinesSet" attribute
     */
    boolean isSetGridLinesSet();
    
    /**
     * Sets the "gridLinesSet" attribute
     */
    void setGridLinesSet(boolean gridLinesSet);
    
    /**
     * Sets (as xml) the "gridLinesSet" attribute
     */
    void xsetGridLinesSet(org.apache.xmlbeans.XmlBoolean gridLinesSet);
    
    /**
     * Unsets the "gridLinesSet" attribute
     */
    void unsetGridLinesSet();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPrintOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
