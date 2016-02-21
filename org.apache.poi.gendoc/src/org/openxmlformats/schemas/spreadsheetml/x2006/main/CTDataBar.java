/*
 * XML Type:  CT_DataBar
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_DataBar(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTDataBar extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDataBar.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctdatabar4128type");
    
    /**
     * Gets a List of "cfvo" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo> getCfvoList();
    
    /**
     * Gets array of all "cfvo" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo[] getCfvoArray();
    
    /**
     * Gets ith "cfvo" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo getCfvoArray(int i);
    
    /**
     * Returns number of "cfvo" element
     */
    int sizeOfCfvoArray();
    
    /**
     * Sets array of all "cfvo" element
     */
    void setCfvoArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo[] cfvoArray);
    
    /**
     * Sets ith "cfvo" element
     */
    void setCfvoArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo cfvo);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "cfvo" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo insertNewCfvo(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "cfvo" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo addNewCfvo();
    
    /**
     * Removes the ith "cfvo" element
     */
    void removeCfvo(int i);
    
    /**
     * Gets the "color" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor getColor();
    
    /**
     * Sets the "color" element
     */
    void setColor(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor color);
    
    /**
     * Appends and returns a new empty "color" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor addNewColor();
    
    /**
     * Gets the "minLength" attribute
     */
    long getMinLength();
    
    /**
     * Gets (as xml) the "minLength" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetMinLength();
    
    /**
     * True if has "minLength" attribute
     */
    boolean isSetMinLength();
    
    /**
     * Sets the "minLength" attribute
     */
    void setMinLength(long minLength);
    
    /**
     * Sets (as xml) the "minLength" attribute
     */
    void xsetMinLength(org.apache.xmlbeans.XmlUnsignedInt minLength);
    
    /**
     * Unsets the "minLength" attribute
     */
    void unsetMinLength();
    
    /**
     * Gets the "maxLength" attribute
     */
    long getMaxLength();
    
    /**
     * Gets (as xml) the "maxLength" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetMaxLength();
    
    /**
     * True if has "maxLength" attribute
     */
    boolean isSetMaxLength();
    
    /**
     * Sets the "maxLength" attribute
     */
    void setMaxLength(long maxLength);
    
    /**
     * Sets (as xml) the "maxLength" attribute
     */
    void xsetMaxLength(org.apache.xmlbeans.XmlUnsignedInt maxLength);
    
    /**
     * Unsets the "maxLength" attribute
     */
    void unsetMaxLength();
    
    /**
     * Gets the "showValue" attribute
     */
    boolean getShowValue();
    
    /**
     * Gets (as xml) the "showValue" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetShowValue();
    
    /**
     * True if has "showValue" attribute
     */
    boolean isSetShowValue();
    
    /**
     * Sets the "showValue" attribute
     */
    void setShowValue(boolean showValue);
    
    /**
     * Sets (as xml) the "showValue" attribute
     */
    void xsetShowValue(org.apache.xmlbeans.XmlBoolean showValue);
    
    /**
     * Unsets the "showValue" attribute
     */
    void unsetShowValue();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
