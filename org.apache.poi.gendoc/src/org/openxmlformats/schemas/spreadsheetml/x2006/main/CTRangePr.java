/*
 * XML Type:  CT_RangePr
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_RangePr(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTRangePr extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTRangePr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctrangeprc5betype");
    
    /**
     * Gets the "autoStart" attribute
     */
    boolean getAutoStart();
    
    /**
     * Gets (as xml) the "autoStart" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetAutoStart();
    
    /**
     * True if has "autoStart" attribute
     */
    boolean isSetAutoStart();
    
    /**
     * Sets the "autoStart" attribute
     */
    void setAutoStart(boolean autoStart);
    
    /**
     * Sets (as xml) the "autoStart" attribute
     */
    void xsetAutoStart(org.apache.xmlbeans.XmlBoolean autoStart);
    
    /**
     * Unsets the "autoStart" attribute
     */
    void unsetAutoStart();
    
    /**
     * Gets the "autoEnd" attribute
     */
    boolean getAutoEnd();
    
    /**
     * Gets (as xml) the "autoEnd" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetAutoEnd();
    
    /**
     * True if has "autoEnd" attribute
     */
    boolean isSetAutoEnd();
    
    /**
     * Sets the "autoEnd" attribute
     */
    void setAutoEnd(boolean autoEnd);
    
    /**
     * Sets (as xml) the "autoEnd" attribute
     */
    void xsetAutoEnd(org.apache.xmlbeans.XmlBoolean autoEnd);
    
    /**
     * Unsets the "autoEnd" attribute
     */
    void unsetAutoEnd();
    
    /**
     * Gets the "groupBy" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STGroupBy.Enum getGroupBy();
    
    /**
     * Gets (as xml) the "groupBy" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STGroupBy xgetGroupBy();
    
    /**
     * True if has "groupBy" attribute
     */
    boolean isSetGroupBy();
    
    /**
     * Sets the "groupBy" attribute
     */
    void setGroupBy(org.openxmlformats.schemas.spreadsheetml.x2006.main.STGroupBy.Enum groupBy);
    
    /**
     * Sets (as xml) the "groupBy" attribute
     */
    void xsetGroupBy(org.openxmlformats.schemas.spreadsheetml.x2006.main.STGroupBy groupBy);
    
    /**
     * Unsets the "groupBy" attribute
     */
    void unsetGroupBy();
    
    /**
     * Gets the "startNum" attribute
     */
    double getStartNum();
    
    /**
     * Gets (as xml) the "startNum" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetStartNum();
    
    /**
     * True if has "startNum" attribute
     */
    boolean isSetStartNum();
    
    /**
     * Sets the "startNum" attribute
     */
    void setStartNum(double startNum);
    
    /**
     * Sets (as xml) the "startNum" attribute
     */
    void xsetStartNum(org.apache.xmlbeans.XmlDouble startNum);
    
    /**
     * Unsets the "startNum" attribute
     */
    void unsetStartNum();
    
    /**
     * Gets the "endNum" attribute
     */
    double getEndNum();
    
    /**
     * Gets (as xml) the "endNum" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetEndNum();
    
    /**
     * True if has "endNum" attribute
     */
    boolean isSetEndNum();
    
    /**
     * Sets the "endNum" attribute
     */
    void setEndNum(double endNum);
    
    /**
     * Sets (as xml) the "endNum" attribute
     */
    void xsetEndNum(org.apache.xmlbeans.XmlDouble endNum);
    
    /**
     * Unsets the "endNum" attribute
     */
    void unsetEndNum();
    
    /**
     * Gets the "startDate" attribute
     */
    java.util.Calendar getStartDate();
    
    /**
     * Gets (as xml) the "startDate" attribute
     */
    org.apache.xmlbeans.XmlDateTime xgetStartDate();
    
    /**
     * True if has "startDate" attribute
     */
    boolean isSetStartDate();
    
    /**
     * Sets the "startDate" attribute
     */
    void setStartDate(java.util.Calendar startDate);
    
    /**
     * Sets (as xml) the "startDate" attribute
     */
    void xsetStartDate(org.apache.xmlbeans.XmlDateTime startDate);
    
    /**
     * Unsets the "startDate" attribute
     */
    void unsetStartDate();
    
    /**
     * Gets the "endDate" attribute
     */
    java.util.Calendar getEndDate();
    
    /**
     * Gets (as xml) the "endDate" attribute
     */
    org.apache.xmlbeans.XmlDateTime xgetEndDate();
    
    /**
     * True if has "endDate" attribute
     */
    boolean isSetEndDate();
    
    /**
     * Sets the "endDate" attribute
     */
    void setEndDate(java.util.Calendar endDate);
    
    /**
     * Sets (as xml) the "endDate" attribute
     */
    void xsetEndDate(org.apache.xmlbeans.XmlDateTime endDate);
    
    /**
     * Unsets the "endDate" attribute
     */
    void unsetEndDate();
    
    /**
     * Gets the "groupInterval" attribute
     */
    double getGroupInterval();
    
    /**
     * Gets (as xml) the "groupInterval" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetGroupInterval();
    
    /**
     * True if has "groupInterval" attribute
     */
    boolean isSetGroupInterval();
    
    /**
     * Sets the "groupInterval" attribute
     */
    void setGroupInterval(double groupInterval);
    
    /**
     * Sets (as xml) the "groupInterval" attribute
     */
    void xsetGroupInterval(org.apache.xmlbeans.XmlDouble groupInterval);
    
    /**
     * Unsets the "groupInterval" attribute
     */
    void unsetGroupInterval();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
