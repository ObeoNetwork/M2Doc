/*
 * XML Type:  CT_CellSmartTag
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_CellSmartTag(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTCellSmartTag extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTCellSmartTag.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctcellsmarttag931ctype");
    
    /**
     * Gets a List of "cellSmartTagPr" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTagPr> getCellSmartTagPrList();
    
    /**
     * Gets array of all "cellSmartTagPr" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTagPr[] getCellSmartTagPrArray();
    
    /**
     * Gets ith "cellSmartTagPr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTagPr getCellSmartTagPrArray(int i);
    
    /**
     * Returns number of "cellSmartTagPr" element
     */
    int sizeOfCellSmartTagPrArray();
    
    /**
     * Sets array of all "cellSmartTagPr" element
     */
    void setCellSmartTagPrArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTagPr[] cellSmartTagPrArray);
    
    /**
     * Sets ith "cellSmartTagPr" element
     */
    void setCellSmartTagPrArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTagPr cellSmartTagPr);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "cellSmartTagPr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTagPr insertNewCellSmartTagPr(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "cellSmartTagPr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTagPr addNewCellSmartTagPr();
    
    /**
     * Removes the ith "cellSmartTagPr" element
     */
    void removeCellSmartTagPr(int i);
    
    /**
     * Gets the "type" attribute
     */
    long getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(long type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(org.apache.xmlbeans.XmlUnsignedInt type);
    
    /**
     * Gets the "deleted" attribute
     */
    boolean getDeleted();
    
    /**
     * Gets (as xml) the "deleted" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetDeleted();
    
    /**
     * True if has "deleted" attribute
     */
    boolean isSetDeleted();
    
    /**
     * Sets the "deleted" attribute
     */
    void setDeleted(boolean deleted);
    
    /**
     * Sets (as xml) the "deleted" attribute
     */
    void xsetDeleted(org.apache.xmlbeans.XmlBoolean deleted);
    
    /**
     * Unsets the "deleted" attribute
     */
    void unsetDeleted();
    
    /**
     * Gets the "xmlBased" attribute
     */
    boolean getXmlBased();
    
    /**
     * Gets (as xml) the "xmlBased" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetXmlBased();
    
    /**
     * True if has "xmlBased" attribute
     */
    boolean isSetXmlBased();
    
    /**
     * Sets the "xmlBased" attribute
     */
    void setXmlBased(boolean xmlBased);
    
    /**
     * Sets (as xml) the "xmlBased" attribute
     */
    void xsetXmlBased(org.apache.xmlbeans.XmlBoolean xmlBased);
    
    /**
     * Unsets the "xmlBased" attribute
     */
    void unsetXmlBased();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellSmartTag) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
