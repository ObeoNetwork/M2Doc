/*
 * XML Type:  CT_Sources
 * Namespace: http://schemas.openxmlformats.org/officeDocument/2006/bibliography
 * Java type: org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.officeDocument.x2006.bibliography;


/**
 * An XML CT_Sources(@http://schemas.openxmlformats.org/officeDocument/2006/bibliography).
 *
 * This is a complex type.
 */
public interface CTSources extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTSources.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctsourcesa9fetype");
    
    /**
     * Gets a List of "Source" elements
     */
    java.util.List<org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSourceType> getSourceList();
    
    /**
     * Gets array of all "Source" elements
     * @deprecated
     */
    org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSourceType[] getSourceArray();
    
    /**
     * Gets ith "Source" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSourceType getSourceArray(int i);
    
    /**
     * Returns number of "Source" element
     */
    int sizeOfSourceArray();
    
    /**
     * Sets array of all "Source" element
     */
    void setSourceArray(org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSourceType[] sourceArray);
    
    /**
     * Sets ith "Source" element
     */
    void setSourceArray(int i, org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSourceType source);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Source" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSourceType insertNewSource(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Source" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSourceType addNewSource();
    
    /**
     * Removes the ith "Source" element
     */
    void removeSource(int i);
    
    /**
     * Gets the "SelectedStyle" attribute
     */
    java.lang.String getSelectedStyle();
    
    /**
     * Gets (as xml) the "SelectedStyle" attribute
     */
    org.openxmlformats.schemas.officeDocument.x2006.bibliography.STString255 xgetSelectedStyle();
    
    /**
     * True if has "SelectedStyle" attribute
     */
    boolean isSetSelectedStyle();
    
    /**
     * Sets the "SelectedStyle" attribute
     */
    void setSelectedStyle(java.lang.String selectedStyle);
    
    /**
     * Sets (as xml) the "SelectedStyle" attribute
     */
    void xsetSelectedStyle(org.openxmlformats.schemas.officeDocument.x2006.bibliography.STString255 selectedStyle);
    
    /**
     * Unsets the "SelectedStyle" attribute
     */
    void unsetSelectedStyle();
    
    /**
     * Gets the "StyleName" attribute
     */
    java.lang.String getStyleName();
    
    /**
     * Gets (as xml) the "StyleName" attribute
     */
    org.openxmlformats.schemas.officeDocument.x2006.bibliography.STString255 xgetStyleName();
    
    /**
     * True if has "StyleName" attribute
     */
    boolean isSetStyleName();
    
    /**
     * Sets the "StyleName" attribute
     */
    void setStyleName(java.lang.String styleName);
    
    /**
     * Sets (as xml) the "StyleName" attribute
     */
    void xsetStyleName(org.openxmlformats.schemas.officeDocument.x2006.bibliography.STString255 styleName);
    
    /**
     * Unsets the "StyleName" attribute
     */
    void unsetStyleName();
    
    /**
     * Gets the "URI" attribute
     */
    java.lang.String getURI();
    
    /**
     * Gets (as xml) the "URI" attribute
     */
    org.openxmlformats.schemas.officeDocument.x2006.bibliography.STString255 xgetURI();
    
    /**
     * True if has "URI" attribute
     */
    boolean isSetURI();
    
    /**
     * Sets the "URI" attribute
     */
    void setURI(java.lang.String uri);
    
    /**
     * Sets (as xml) the "URI" attribute
     */
    void xsetURI(org.openxmlformats.schemas.officeDocument.x2006.bibliography.STString255 uri);
    
    /**
     * Unsets the "URI" attribute
     */
    void unsetURI();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources newInstance() {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.bibliography.CTSources) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
