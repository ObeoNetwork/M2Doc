/*
 * XML Type:  CT_Dxf
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Dxf(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTDxf extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDxf.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctdxfa3b1type");
    
    /**
     * Gets the "font" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont getFont();
    
    /**
     * True if has "font" element
     */
    boolean isSetFont();
    
    /**
     * Sets the "font" element
     */
    void setFont(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont font);
    
    /**
     * Appends and returns a new empty "font" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont addNewFont();
    
    /**
     * Unsets the "font" element
     */
    void unsetFont();
    
    /**
     * Gets the "numFmt" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt getNumFmt();
    
    /**
     * True if has "numFmt" element
     */
    boolean isSetNumFmt();
    
    /**
     * Sets the "numFmt" element
     */
    void setNumFmt(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt numFmt);
    
    /**
     * Appends and returns a new empty "numFmt" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt addNewNumFmt();
    
    /**
     * Unsets the "numFmt" element
     */
    void unsetNumFmt();
    
    /**
     * Gets the "fill" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill getFill();
    
    /**
     * True if has "fill" element
     */
    boolean isSetFill();
    
    /**
     * Sets the "fill" element
     */
    void setFill(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill fill);
    
    /**
     * Appends and returns a new empty "fill" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill addNewFill();
    
    /**
     * Unsets the "fill" element
     */
    void unsetFill();
    
    /**
     * Gets the "alignment" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment getAlignment();
    
    /**
     * True if has "alignment" element
     */
    boolean isSetAlignment();
    
    /**
     * Sets the "alignment" element
     */
    void setAlignment(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment alignment);
    
    /**
     * Appends and returns a new empty "alignment" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment addNewAlignment();
    
    /**
     * Unsets the "alignment" element
     */
    void unsetAlignment();
    
    /**
     * Gets the "border" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder getBorder();
    
    /**
     * True if has "border" element
     */
    boolean isSetBorder();
    
    /**
     * Sets the "border" element
     */
    void setBorder(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder border);
    
    /**
     * Appends and returns a new empty "border" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder addNewBorder();
    
    /**
     * Unsets the "border" element
     */
    void unsetBorder();
    
    /**
     * Gets the "protection" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection getProtection();
    
    /**
     * True if has "protection" element
     */
    boolean isSetProtection();
    
    /**
     * Sets the "protection" element
     */
    void setProtection(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection protection);
    
    /**
     * Appends and returns a new empty "protection" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection addNewProtection();
    
    /**
     * Unsets the "protection" element
     */
    void unsetProtection();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
