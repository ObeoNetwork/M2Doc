/*
 * XML Type:  CT_SdtDate
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_SdtDate(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTSdtDate extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTSdtDate.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctsdtdatedfa1type");
    
    /**
     * Gets the "dateFormat" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString getDateFormat();
    
    /**
     * True if has "dateFormat" element
     */
    boolean isSetDateFormat();
    
    /**
     * Sets the "dateFormat" element
     */
    void setDateFormat(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString dateFormat);
    
    /**
     * Appends and returns a new empty "dateFormat" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString addNewDateFormat();
    
    /**
     * Unsets the "dateFormat" element
     */
    void unsetDateFormat();
    
    /**
     * Gets the "lid" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang getLid();
    
    /**
     * True if has "lid" element
     */
    boolean isSetLid();
    
    /**
     * Sets the "lid" element
     */
    void setLid(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang lid);
    
    /**
     * Appends and returns a new empty "lid" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang addNewLid();
    
    /**
     * Unsets the "lid" element
     */
    void unsetLid();
    
    /**
     * Gets the "storeMappedDataAs" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDateMappingType getStoreMappedDataAs();
    
    /**
     * True if has "storeMappedDataAs" element
     */
    boolean isSetStoreMappedDataAs();
    
    /**
     * Sets the "storeMappedDataAs" element
     */
    void setStoreMappedDataAs(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDateMappingType storeMappedDataAs);
    
    /**
     * Appends and returns a new empty "storeMappedDataAs" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDateMappingType addNewStoreMappedDataAs();
    
    /**
     * Unsets the "storeMappedDataAs" element
     */
    void unsetStoreMappedDataAs();
    
    /**
     * Gets the "calendar" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCalendarType getCalendar();
    
    /**
     * True if has "calendar" element
     */
    boolean isSetCalendar();
    
    /**
     * Sets the "calendar" element
     */
    void setCalendar(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCalendarType calendar);
    
    /**
     * Appends and returns a new empty "calendar" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCalendarType addNewCalendar();
    
    /**
     * Unsets the "calendar" element
     */
    void unsetCalendar();
    
    /**
     * Gets the "fullDate" attribute
     */
    java.util.Calendar getFullDate();
    
    /**
     * Gets (as xml) the "fullDate" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime xgetFullDate();
    
    /**
     * True if has "fullDate" attribute
     */
    boolean isSetFullDate();
    
    /**
     * Sets the "fullDate" attribute
     */
    void setFullDate(java.util.Calendar fullDate);
    
    /**
     * Sets (as xml) the "fullDate" attribute
     */
    void xsetFullDate(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime fullDate);
    
    /**
     * Unsets the "fullDate" attribute
     */
    void unsetFullDate();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
