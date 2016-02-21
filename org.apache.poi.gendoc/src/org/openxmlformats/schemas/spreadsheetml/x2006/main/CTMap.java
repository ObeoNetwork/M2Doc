/*
 * XML Type:  CT_Map
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Map(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTMap extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTMap.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctmap023btype");
    
    /**
     * Gets the "DataBinding" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding getDataBinding();
    
    /**
     * True if has "DataBinding" element
     */
    boolean isSetDataBinding();
    
    /**
     * Sets the "DataBinding" element
     */
    void setDataBinding(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding dataBinding);
    
    /**
     * Appends and returns a new empty "DataBinding" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding addNewDataBinding();
    
    /**
     * Unsets the "DataBinding" element
     */
    void unsetDataBinding();
    
    /**
     * Gets the "ID" attribute
     */
    long getID();
    
    /**
     * Gets (as xml) the "ID" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetID();
    
    /**
     * Sets the "ID" attribute
     */
    void setID(long id);
    
    /**
     * Sets (as xml) the "ID" attribute
     */
    void xsetID(org.apache.xmlbeans.XmlUnsignedInt id);
    
    /**
     * Gets the "Name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "Name" attribute
     */
    org.apache.xmlbeans.XmlString xgetName();
    
    /**
     * Sets the "Name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "Name" attribute
     */
    void xsetName(org.apache.xmlbeans.XmlString name);
    
    /**
     * Gets the "RootElement" attribute
     */
    java.lang.String getRootElement();
    
    /**
     * Gets (as xml) the "RootElement" attribute
     */
    org.apache.xmlbeans.XmlString xgetRootElement();
    
    /**
     * Sets the "RootElement" attribute
     */
    void setRootElement(java.lang.String rootElement);
    
    /**
     * Sets (as xml) the "RootElement" attribute
     */
    void xsetRootElement(org.apache.xmlbeans.XmlString rootElement);
    
    /**
     * Gets the "SchemaID" attribute
     */
    java.lang.String getSchemaID();
    
    /**
     * Gets (as xml) the "SchemaID" attribute
     */
    org.apache.xmlbeans.XmlString xgetSchemaID();
    
    /**
     * Sets the "SchemaID" attribute
     */
    void setSchemaID(java.lang.String schemaID);
    
    /**
     * Sets (as xml) the "SchemaID" attribute
     */
    void xsetSchemaID(org.apache.xmlbeans.XmlString schemaID);
    
    /**
     * Gets the "ShowImportExportValidationErrors" attribute
     */
    boolean getShowImportExportValidationErrors();
    
    /**
     * Gets (as xml) the "ShowImportExportValidationErrors" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetShowImportExportValidationErrors();
    
    /**
     * Sets the "ShowImportExportValidationErrors" attribute
     */
    void setShowImportExportValidationErrors(boolean showImportExportValidationErrors);
    
    /**
     * Sets (as xml) the "ShowImportExportValidationErrors" attribute
     */
    void xsetShowImportExportValidationErrors(org.apache.xmlbeans.XmlBoolean showImportExportValidationErrors);
    
    /**
     * Gets the "AutoFit" attribute
     */
    boolean getAutoFit();
    
    /**
     * Gets (as xml) the "AutoFit" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetAutoFit();
    
    /**
     * Sets the "AutoFit" attribute
     */
    void setAutoFit(boolean autoFit);
    
    /**
     * Sets (as xml) the "AutoFit" attribute
     */
    void xsetAutoFit(org.apache.xmlbeans.XmlBoolean autoFit);
    
    /**
     * Gets the "Append" attribute
     */
    boolean getAppend();
    
    /**
     * Gets (as xml) the "Append" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetAppend();
    
    /**
     * Sets the "Append" attribute
     */
    void setAppend(boolean append);
    
    /**
     * Sets (as xml) the "Append" attribute
     */
    void xsetAppend(org.apache.xmlbeans.XmlBoolean append);
    
    /**
     * Gets the "PreserveSortAFLayout" attribute
     */
    boolean getPreserveSortAFLayout();
    
    /**
     * Gets (as xml) the "PreserveSortAFLayout" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetPreserveSortAFLayout();
    
    /**
     * Sets the "PreserveSortAFLayout" attribute
     */
    void setPreserveSortAFLayout(boolean preserveSortAFLayout);
    
    /**
     * Sets (as xml) the "PreserveSortAFLayout" attribute
     */
    void xsetPreserveSortAFLayout(org.apache.xmlbeans.XmlBoolean preserveSortAFLayout);
    
    /**
     * Gets the "PreserveFormat" attribute
     */
    boolean getPreserveFormat();
    
    /**
     * Gets (as xml) the "PreserveFormat" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetPreserveFormat();
    
    /**
     * Sets the "PreserveFormat" attribute
     */
    void setPreserveFormat(boolean preserveFormat);
    
    /**
     * Sets (as xml) the "PreserveFormat" attribute
     */
    void xsetPreserveFormat(org.apache.xmlbeans.XmlBoolean preserveFormat);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
