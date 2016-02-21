/*
 * XML Type:  CT_AxDataSource
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chart
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;


/**
 * An XML CT_AxDataSource(@http://schemas.openxmlformats.org/drawingml/2006/chart).
 *
 * This is a complex type.
 */
public interface CTAxDataSource extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTAxDataSource.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctaxdatasource1440type");
    
    /**
     * Gets the "multiLvlStrRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef getMultiLvlStrRef();
    
    /**
     * True if has "multiLvlStrRef" element
     */
    boolean isSetMultiLvlStrRef();
    
    /**
     * Sets the "multiLvlStrRef" element
     */
    void setMultiLvlStrRef(org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef multiLvlStrRef);
    
    /**
     * Appends and returns a new empty "multiLvlStrRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef addNewMultiLvlStrRef();
    
    /**
     * Unsets the "multiLvlStrRef" element
     */
    void unsetMultiLvlStrRef();
    
    /**
     * Gets the "numRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef getNumRef();
    
    /**
     * True if has "numRef" element
     */
    boolean isSetNumRef();
    
    /**
     * Sets the "numRef" element
     */
    void setNumRef(org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef numRef);
    
    /**
     * Appends and returns a new empty "numRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef addNewNumRef();
    
    /**
     * Unsets the "numRef" element
     */
    void unsetNumRef();
    
    /**
     * Gets the "numLit" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData getNumLit();
    
    /**
     * True if has "numLit" element
     */
    boolean isSetNumLit();
    
    /**
     * Sets the "numLit" element
     */
    void setNumLit(org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData numLit);
    
    /**
     * Appends and returns a new empty "numLit" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData addNewNumLit();
    
    /**
     * Unsets the "numLit" element
     */
    void unsetNumLit();
    
    /**
     * Gets the "strRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef getStrRef();
    
    /**
     * True if has "strRef" element
     */
    boolean isSetStrRef();
    
    /**
     * Sets the "strRef" element
     */
    void setStrRef(org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef strRef);
    
    /**
     * Appends and returns a new empty "strRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef addNewStrRef();
    
    /**
     * Unsets the "strRef" element
     */
    void unsetStrRef();
    
    /**
     * Gets the "strLit" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData getStrLit();
    
    /**
     * True if has "strLit" element
     */
    boolean isSetStrLit();
    
    /**
     * Sets the "strLit" element
     */
    void setStrLit(org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData strLit);
    
    /**
     * Appends and returns a new empty "strLit" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData addNewStrLit();
    
    /**
     * Unsets the "strLit" element
     */
    void unsetStrLit();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
