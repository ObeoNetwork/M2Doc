/*
 * XML Type:  CT_DataBinding
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_DataBinding(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTDataBinding extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDataBinding.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctdatabindingc09atype");
    
    /**
     * Gets the "DataBindingName" attribute
     */
    java.lang.String getDataBindingName();
    
    /**
     * Gets (as xml) the "DataBindingName" attribute
     */
    org.apache.xmlbeans.XmlString xgetDataBindingName();
    
    /**
     * True if has "DataBindingName" attribute
     */
    boolean isSetDataBindingName();
    
    /**
     * Sets the "DataBindingName" attribute
     */
    void setDataBindingName(java.lang.String dataBindingName);
    
    /**
     * Sets (as xml) the "DataBindingName" attribute
     */
    void xsetDataBindingName(org.apache.xmlbeans.XmlString dataBindingName);
    
    /**
     * Unsets the "DataBindingName" attribute
     */
    void unsetDataBindingName();
    
    /**
     * Gets the "FileBinding" attribute
     */
    boolean getFileBinding();
    
    /**
     * Gets (as xml) the "FileBinding" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetFileBinding();
    
    /**
     * True if has "FileBinding" attribute
     */
    boolean isSetFileBinding();
    
    /**
     * Sets the "FileBinding" attribute
     */
    void setFileBinding(boolean fileBinding);
    
    /**
     * Sets (as xml) the "FileBinding" attribute
     */
    void xsetFileBinding(org.apache.xmlbeans.XmlBoolean fileBinding);
    
    /**
     * Unsets the "FileBinding" attribute
     */
    void unsetFileBinding();
    
    /**
     * Gets the "ConnectionID" attribute
     */
    long getConnectionID();
    
    /**
     * Gets (as xml) the "ConnectionID" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetConnectionID();
    
    /**
     * True if has "ConnectionID" attribute
     */
    boolean isSetConnectionID();
    
    /**
     * Sets the "ConnectionID" attribute
     */
    void setConnectionID(long connectionID);
    
    /**
     * Sets (as xml) the "ConnectionID" attribute
     */
    void xsetConnectionID(org.apache.xmlbeans.XmlUnsignedInt connectionID);
    
    /**
     * Unsets the "ConnectionID" attribute
     */
    void unsetConnectionID();
    
    /**
     * Gets the "FileBindingName" attribute
     */
    java.lang.String getFileBindingName();
    
    /**
     * Gets (as xml) the "FileBindingName" attribute
     */
    org.apache.xmlbeans.XmlString xgetFileBindingName();
    
    /**
     * True if has "FileBindingName" attribute
     */
    boolean isSetFileBindingName();
    
    /**
     * Sets the "FileBindingName" attribute
     */
    void setFileBindingName(java.lang.String fileBindingName);
    
    /**
     * Sets (as xml) the "FileBindingName" attribute
     */
    void xsetFileBindingName(org.apache.xmlbeans.XmlString fileBindingName);
    
    /**
     * Unsets the "FileBindingName" attribute
     */
    void unsetFileBindingName();
    
    /**
     * Gets the "DataBindingLoadMode" attribute
     */
    long getDataBindingLoadMode();
    
    /**
     * Gets (as xml) the "DataBindingLoadMode" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetDataBindingLoadMode();
    
    /**
     * Sets the "DataBindingLoadMode" attribute
     */
    void setDataBindingLoadMode(long dataBindingLoadMode);
    
    /**
     * Sets (as xml) the "DataBindingLoadMode" attribute
     */
    void xsetDataBindingLoadMode(org.apache.xmlbeans.XmlUnsignedInt dataBindingLoadMode);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBinding) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
