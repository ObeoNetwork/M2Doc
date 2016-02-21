/*
 * XML Type:  CT_IconSet
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_IconSet(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTIconSet extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTIconSet.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cticonset2648type");
    
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
     * Gets the "iconSet" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType.Enum getIconSet();
    
    /**
     * Gets (as xml) the "iconSet" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType xgetIconSet();
    
    /**
     * True if has "iconSet" attribute
     */
    boolean isSetIconSet();
    
    /**
     * Sets the "iconSet" attribute
     */
    void setIconSet(org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType.Enum iconSet);
    
    /**
     * Sets (as xml) the "iconSet" attribute
     */
    void xsetIconSet(org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType iconSet);
    
    /**
     * Unsets the "iconSet" attribute
     */
    void unsetIconSet();
    
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
     * Gets the "percent" attribute
     */
    boolean getPercent();
    
    /**
     * Gets (as xml) the "percent" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetPercent();
    
    /**
     * True if has "percent" attribute
     */
    boolean isSetPercent();
    
    /**
     * Sets the "percent" attribute
     */
    void setPercent(boolean percent);
    
    /**
     * Sets (as xml) the "percent" attribute
     */
    void xsetPercent(org.apache.xmlbeans.XmlBoolean percent);
    
    /**
     * Unsets the "percent" attribute
     */
    void unsetPercent();
    
    /**
     * Gets the "reverse" attribute
     */
    boolean getReverse();
    
    /**
     * Gets (as xml) the "reverse" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetReverse();
    
    /**
     * True if has "reverse" attribute
     */
    boolean isSetReverse();
    
    /**
     * Sets the "reverse" attribute
     */
    void setReverse(boolean reverse);
    
    /**
     * Sets (as xml) the "reverse" attribute
     */
    void xsetReverse(org.apache.xmlbeans.XmlBoolean reverse);
    
    /**
     * Unsets the "reverse" attribute
     */
    void unsetReverse();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
