/*
 * XML Type:  CT_Filters
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Filters(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTFilters extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTFilters.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctfiltersb69atype");
    
    /**
     * Gets a List of "filter" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter> getFilterList();
    
    /**
     * Gets array of all "filter" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter[] getFilterArray();
    
    /**
     * Gets ith "filter" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter getFilterArray(int i);
    
    /**
     * Returns number of "filter" element
     */
    int sizeOfFilterArray();
    
    /**
     * Sets array of all "filter" element
     */
    void setFilterArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter[] filterArray);
    
    /**
     * Sets ith "filter" element
     */
    void setFilterArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter filter);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "filter" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter insertNewFilter(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "filter" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter addNewFilter();
    
    /**
     * Removes the ith "filter" element
     */
    void removeFilter(int i);
    
    /**
     * Gets a List of "dateGroupItem" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem> getDateGroupItemList();
    
    /**
     * Gets array of all "dateGroupItem" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem[] getDateGroupItemArray();
    
    /**
     * Gets ith "dateGroupItem" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem getDateGroupItemArray(int i);
    
    /**
     * Returns number of "dateGroupItem" element
     */
    int sizeOfDateGroupItemArray();
    
    /**
     * Sets array of all "dateGroupItem" element
     */
    void setDateGroupItemArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem[] dateGroupItemArray);
    
    /**
     * Sets ith "dateGroupItem" element
     */
    void setDateGroupItemArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem dateGroupItem);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "dateGroupItem" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem insertNewDateGroupItem(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "dateGroupItem" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem addNewDateGroupItem();
    
    /**
     * Removes the ith "dateGroupItem" element
     */
    void removeDateGroupItem(int i);
    
    /**
     * Gets the "blank" attribute
     */
    boolean getBlank();
    
    /**
     * Gets (as xml) the "blank" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetBlank();
    
    /**
     * True if has "blank" attribute
     */
    boolean isSetBlank();
    
    /**
     * Sets the "blank" attribute
     */
    void setBlank(boolean blank);
    
    /**
     * Sets (as xml) the "blank" attribute
     */
    void xsetBlank(org.apache.xmlbeans.XmlBoolean blank);
    
    /**
     * Unsets the "blank" attribute
     */
    void unsetBlank();
    
    /**
     * Gets the "calendarType" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalendarType.Enum getCalendarType();
    
    /**
     * Gets (as xml) the "calendarType" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalendarType xgetCalendarType();
    
    /**
     * True if has "calendarType" attribute
     */
    boolean isSetCalendarType();
    
    /**
     * Sets the "calendarType" attribute
     */
    void setCalendarType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalendarType.Enum calendarType);
    
    /**
     * Sets (as xml) the "calendarType" attribute
     */
    void xsetCalendarType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STCalendarType calendarType);
    
    /**
     * Unsets the "calendarType" attribute
     */
    void unsetCalendarType();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilters) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
