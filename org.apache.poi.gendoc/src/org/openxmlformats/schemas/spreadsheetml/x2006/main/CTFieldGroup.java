/*
 * XML Type:  CT_FieldGroup
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_FieldGroup(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTFieldGroup extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTFieldGroup.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctfieldgroupe2eetype");
    
    /**
     * Gets the "rangePr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr getRangePr();
    
    /**
     * True if has "rangePr" element
     */
    boolean isSetRangePr();
    
    /**
     * Sets the "rangePr" element
     */
    void setRangePr(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr rangePr);
    
    /**
     * Appends and returns a new empty "rangePr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRangePr addNewRangePr();
    
    /**
     * Unsets the "rangePr" element
     */
    void unsetRangePr();
    
    /**
     * Gets the "discretePr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDiscretePr getDiscretePr();
    
    /**
     * True if has "discretePr" element
     */
    boolean isSetDiscretePr();
    
    /**
     * Sets the "discretePr" element
     */
    void setDiscretePr(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDiscretePr discretePr);
    
    /**
     * Appends and returns a new empty "discretePr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDiscretePr addNewDiscretePr();
    
    /**
     * Unsets the "discretePr" element
     */
    void unsetDiscretePr();
    
    /**
     * Gets the "groupItems" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGroupItems getGroupItems();
    
    /**
     * True if has "groupItems" element
     */
    boolean isSetGroupItems();
    
    /**
     * Sets the "groupItems" element
     */
    void setGroupItems(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGroupItems groupItems);
    
    /**
     * Appends and returns a new empty "groupItems" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGroupItems addNewGroupItems();
    
    /**
     * Unsets the "groupItems" element
     */
    void unsetGroupItems();
    
    /**
     * Gets the "par" attribute
     */
    long getPar();
    
    /**
     * Gets (as xml) the "par" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetPar();
    
    /**
     * True if has "par" attribute
     */
    boolean isSetPar();
    
    /**
     * Sets the "par" attribute
     */
    void setPar(long par);
    
    /**
     * Sets (as xml) the "par" attribute
     */
    void xsetPar(org.apache.xmlbeans.XmlUnsignedInt par);
    
    /**
     * Unsets the "par" attribute
     */
    void unsetPar();
    
    /**
     * Gets the "base" attribute
     */
    long getBase();
    
    /**
     * Gets (as xml) the "base" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetBase();
    
    /**
     * True if has "base" attribute
     */
    boolean isSetBase();
    
    /**
     * Sets the "base" attribute
     */
    void setBase(long base);
    
    /**
     * Sets (as xml) the "base" attribute
     */
    void xsetBase(org.apache.xmlbeans.XmlUnsignedInt base);
    
    /**
     * Unsets the "base" attribute
     */
    void unsetBase();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFieldGroup) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
