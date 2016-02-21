/*
 * XML Type:  CT_IconFilter
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_IconFilter(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTIconFilter extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTIconFilter.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cticonfilterb79atype");
    
    /**
     * Gets the "iconSet" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType.Enum getIconSet();
    
    /**
     * Gets (as xml) the "iconSet" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType xgetIconSet();
    
    /**
     * Sets the "iconSet" attribute
     */
    void setIconSet(org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType.Enum iconSet);
    
    /**
     * Sets (as xml) the "iconSet" attribute
     */
    void xsetIconSet(org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType iconSet);
    
    /**
     * Gets the "iconId" attribute
     */
    long getIconId();
    
    /**
     * Gets (as xml) the "iconId" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetIconId();
    
    /**
     * True if has "iconId" attribute
     */
    boolean isSetIconId();
    
    /**
     * Sets the "iconId" attribute
     */
    void setIconId(long iconId);
    
    /**
     * Sets (as xml) the "iconId" attribute
     */
    void xsetIconId(org.apache.xmlbeans.XmlUnsignedInt iconId);
    
    /**
     * Unsets the "iconId" attribute
     */
    void unsetIconId();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconFilter) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
