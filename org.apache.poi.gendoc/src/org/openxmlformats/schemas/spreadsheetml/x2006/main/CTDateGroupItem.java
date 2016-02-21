/*
 * XML Type:  CT_DateGroupItem
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_DateGroupItem(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTDateGroupItem extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDateGroupItem.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctdategroupitem9743type");
    
    /**
     * Gets the "year" attribute
     */
    int getYear();
    
    /**
     * Gets (as xml) the "year" attribute
     */
    org.apache.xmlbeans.XmlUnsignedShort xgetYear();
    
    /**
     * Sets the "year" attribute
     */
    void setYear(int year);
    
    /**
     * Sets (as xml) the "year" attribute
     */
    void xsetYear(org.apache.xmlbeans.XmlUnsignedShort year);
    
    /**
     * Gets the "month" attribute
     */
    int getMonth();
    
    /**
     * Gets (as xml) the "month" attribute
     */
    org.apache.xmlbeans.XmlUnsignedShort xgetMonth();
    
    /**
     * True if has "month" attribute
     */
    boolean isSetMonth();
    
    /**
     * Sets the "month" attribute
     */
    void setMonth(int month);
    
    /**
     * Sets (as xml) the "month" attribute
     */
    void xsetMonth(org.apache.xmlbeans.XmlUnsignedShort month);
    
    /**
     * Unsets the "month" attribute
     */
    void unsetMonth();
    
    /**
     * Gets the "day" attribute
     */
    int getDay();
    
    /**
     * Gets (as xml) the "day" attribute
     */
    org.apache.xmlbeans.XmlUnsignedShort xgetDay();
    
    /**
     * True if has "day" attribute
     */
    boolean isSetDay();
    
    /**
     * Sets the "day" attribute
     */
    void setDay(int day);
    
    /**
     * Sets (as xml) the "day" attribute
     */
    void xsetDay(org.apache.xmlbeans.XmlUnsignedShort day);
    
    /**
     * Unsets the "day" attribute
     */
    void unsetDay();
    
    /**
     * Gets the "hour" attribute
     */
    int getHour();
    
    /**
     * Gets (as xml) the "hour" attribute
     */
    org.apache.xmlbeans.XmlUnsignedShort xgetHour();
    
    /**
     * True if has "hour" attribute
     */
    boolean isSetHour();
    
    /**
     * Sets the "hour" attribute
     */
    void setHour(int hour);
    
    /**
     * Sets (as xml) the "hour" attribute
     */
    void xsetHour(org.apache.xmlbeans.XmlUnsignedShort hour);
    
    /**
     * Unsets the "hour" attribute
     */
    void unsetHour();
    
    /**
     * Gets the "minute" attribute
     */
    int getMinute();
    
    /**
     * Gets (as xml) the "minute" attribute
     */
    org.apache.xmlbeans.XmlUnsignedShort xgetMinute();
    
    /**
     * True if has "minute" attribute
     */
    boolean isSetMinute();
    
    /**
     * Sets the "minute" attribute
     */
    void setMinute(int minute);
    
    /**
     * Sets (as xml) the "minute" attribute
     */
    void xsetMinute(org.apache.xmlbeans.XmlUnsignedShort minute);
    
    /**
     * Unsets the "minute" attribute
     */
    void unsetMinute();
    
    /**
     * Gets the "second" attribute
     */
    int getSecond();
    
    /**
     * Gets (as xml) the "second" attribute
     */
    org.apache.xmlbeans.XmlUnsignedShort xgetSecond();
    
    /**
     * True if has "second" attribute
     */
    boolean isSetSecond();
    
    /**
     * Sets the "second" attribute
     */
    void setSecond(int second);
    
    /**
     * Sets (as xml) the "second" attribute
     */
    void xsetSecond(org.apache.xmlbeans.XmlUnsignedShort second);
    
    /**
     * Unsets the "second" attribute
     */
    void unsetSecond();
    
    /**
     * Gets the "dateTimeGrouping" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STDateTimeGrouping.Enum getDateTimeGrouping();
    
    /**
     * Gets (as xml) the "dateTimeGrouping" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STDateTimeGrouping xgetDateTimeGrouping();
    
    /**
     * Sets the "dateTimeGrouping" attribute
     */
    void setDateTimeGrouping(org.openxmlformats.schemas.spreadsheetml.x2006.main.STDateTimeGrouping.Enum dateTimeGrouping);
    
    /**
     * Sets (as xml) the "dateTimeGrouping" attribute
     */
    void xsetDateTimeGrouping(org.openxmlformats.schemas.spreadsheetml.x2006.main.STDateTimeGrouping dateTimeGrouping);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDateGroupItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
