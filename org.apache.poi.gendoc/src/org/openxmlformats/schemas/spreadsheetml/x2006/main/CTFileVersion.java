/*
 * XML Type:  CT_FileVersion
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_FileVersion(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTFileVersion extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTFileVersion.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctfileversion559btype");
    
    /**
     * Gets the "appName" attribute
     */
    java.lang.String getAppName();
    
    /**
     * Gets (as xml) the "appName" attribute
     */
    org.apache.xmlbeans.XmlString xgetAppName();
    
    /**
     * True if has "appName" attribute
     */
    boolean isSetAppName();
    
    /**
     * Sets the "appName" attribute
     */
    void setAppName(java.lang.String appName);
    
    /**
     * Sets (as xml) the "appName" attribute
     */
    void xsetAppName(org.apache.xmlbeans.XmlString appName);
    
    /**
     * Unsets the "appName" attribute
     */
    void unsetAppName();
    
    /**
     * Gets the "lastEdited" attribute
     */
    java.lang.String getLastEdited();
    
    /**
     * Gets (as xml) the "lastEdited" attribute
     */
    org.apache.xmlbeans.XmlString xgetLastEdited();
    
    /**
     * True if has "lastEdited" attribute
     */
    boolean isSetLastEdited();
    
    /**
     * Sets the "lastEdited" attribute
     */
    void setLastEdited(java.lang.String lastEdited);
    
    /**
     * Sets (as xml) the "lastEdited" attribute
     */
    void xsetLastEdited(org.apache.xmlbeans.XmlString lastEdited);
    
    /**
     * Unsets the "lastEdited" attribute
     */
    void unsetLastEdited();
    
    /**
     * Gets the "lowestEdited" attribute
     */
    java.lang.String getLowestEdited();
    
    /**
     * Gets (as xml) the "lowestEdited" attribute
     */
    org.apache.xmlbeans.XmlString xgetLowestEdited();
    
    /**
     * True if has "lowestEdited" attribute
     */
    boolean isSetLowestEdited();
    
    /**
     * Sets the "lowestEdited" attribute
     */
    void setLowestEdited(java.lang.String lowestEdited);
    
    /**
     * Sets (as xml) the "lowestEdited" attribute
     */
    void xsetLowestEdited(org.apache.xmlbeans.XmlString lowestEdited);
    
    /**
     * Unsets the "lowestEdited" attribute
     */
    void unsetLowestEdited();
    
    /**
     * Gets the "rupBuild" attribute
     */
    java.lang.String getRupBuild();
    
    /**
     * Gets (as xml) the "rupBuild" attribute
     */
    org.apache.xmlbeans.XmlString xgetRupBuild();
    
    /**
     * True if has "rupBuild" attribute
     */
    boolean isSetRupBuild();
    
    /**
     * Sets the "rupBuild" attribute
     */
    void setRupBuild(java.lang.String rupBuild);
    
    /**
     * Sets (as xml) the "rupBuild" attribute
     */
    void xsetRupBuild(org.apache.xmlbeans.XmlString rupBuild);
    
    /**
     * Unsets the "rupBuild" attribute
     */
    void unsetRupBuild();
    
    /**
     * Gets the "codeName" attribute
     */
    java.lang.String getCodeName();
    
    /**
     * Gets (as xml) the "codeName" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STGuid xgetCodeName();
    
    /**
     * True if has "codeName" attribute
     */
    boolean isSetCodeName();
    
    /**
     * Sets the "codeName" attribute
     */
    void setCodeName(java.lang.String codeName);
    
    /**
     * Sets (as xml) the "codeName" attribute
     */
    void xsetCodeName(org.openxmlformats.schemas.spreadsheetml.x2006.main.STGuid codeName);
    
    /**
     * Unsets the "codeName" attribute
     */
    void unsetCodeName();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFileVersion) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
