/*
 * XML Type:  CT_WebPublishItem
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_WebPublishItem(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTWebPublishItem extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTWebPublishItem.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctwebpublishitemce57type");
    
    /**
     * Gets the "id" attribute
     */
    long getId();
    
    /**
     * Gets (as xml) the "id" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetId();
    
    /**
     * Sets the "id" attribute
     */
    void setId(long id);
    
    /**
     * Sets (as xml) the "id" attribute
     */
    void xsetId(org.apache.xmlbeans.XmlUnsignedInt id);
    
    /**
     * Gets the "divId" attribute
     */
    java.lang.String getDivId();
    
    /**
     * Gets (as xml) the "divId" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetDivId();
    
    /**
     * Sets the "divId" attribute
     */
    void setDivId(java.lang.String divId);
    
    /**
     * Sets (as xml) the "divId" attribute
     */
    void xsetDivId(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring divId);
    
    /**
     * Gets the "sourceType" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STWebSourceType.Enum getSourceType();
    
    /**
     * Gets (as xml) the "sourceType" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STWebSourceType xgetSourceType();
    
    /**
     * Sets the "sourceType" attribute
     */
    void setSourceType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STWebSourceType.Enum sourceType);
    
    /**
     * Sets (as xml) the "sourceType" attribute
     */
    void xsetSourceType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STWebSourceType sourceType);
    
    /**
     * Gets the "sourceRef" attribute
     */
    java.lang.String getSourceRef();
    
    /**
     * Gets (as xml) the "sourceRef" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef xgetSourceRef();
    
    /**
     * True if has "sourceRef" attribute
     */
    boolean isSetSourceRef();
    
    /**
     * Sets the "sourceRef" attribute
     */
    void setSourceRef(java.lang.String sourceRef);
    
    /**
     * Sets (as xml) the "sourceRef" attribute
     */
    void xsetSourceRef(org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef sourceRef);
    
    /**
     * Unsets the "sourceRef" attribute
     */
    void unsetSourceRef();
    
    /**
     * Gets the "sourceObject" attribute
     */
    java.lang.String getSourceObject();
    
    /**
     * Gets (as xml) the "sourceObject" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetSourceObject();
    
    /**
     * True if has "sourceObject" attribute
     */
    boolean isSetSourceObject();
    
    /**
     * Sets the "sourceObject" attribute
     */
    void setSourceObject(java.lang.String sourceObject);
    
    /**
     * Sets (as xml) the "sourceObject" attribute
     */
    void xsetSourceObject(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring sourceObject);
    
    /**
     * Unsets the "sourceObject" attribute
     */
    void unsetSourceObject();
    
    /**
     * Gets the "destinationFile" attribute
     */
    java.lang.String getDestinationFile();
    
    /**
     * Gets (as xml) the "destinationFile" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetDestinationFile();
    
    /**
     * Sets the "destinationFile" attribute
     */
    void setDestinationFile(java.lang.String destinationFile);
    
    /**
     * Sets (as xml) the "destinationFile" attribute
     */
    void xsetDestinationFile(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring destinationFile);
    
    /**
     * Gets the "title" attribute
     */
    java.lang.String getTitle();
    
    /**
     * Gets (as xml) the "title" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetTitle();
    
    /**
     * True if has "title" attribute
     */
    boolean isSetTitle();
    
    /**
     * Sets the "title" attribute
     */
    void setTitle(java.lang.String title);
    
    /**
     * Sets (as xml) the "title" attribute
     */
    void xsetTitle(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring title);
    
    /**
     * Unsets the "title" attribute
     */
    void unsetTitle();
    
    /**
     * Gets the "autoRepublish" attribute
     */
    boolean getAutoRepublish();
    
    /**
     * Gets (as xml) the "autoRepublish" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetAutoRepublish();
    
    /**
     * True if has "autoRepublish" attribute
     */
    boolean isSetAutoRepublish();
    
    /**
     * Sets the "autoRepublish" attribute
     */
    void setAutoRepublish(boolean autoRepublish);
    
    /**
     * Sets (as xml) the "autoRepublish" attribute
     */
    void xsetAutoRepublish(org.apache.xmlbeans.XmlBoolean autoRepublish);
    
    /**
     * Unsets the "autoRepublish" attribute
     */
    void unsetAutoRepublish();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWebPublishItem) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
