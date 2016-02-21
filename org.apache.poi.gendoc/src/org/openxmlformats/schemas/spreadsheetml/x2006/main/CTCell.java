/*
 * XML Type:  CT_Cell
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Cell(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTCell extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTCell.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctcell842btype");
    
    /**
     * Gets the "f" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula getF();
    
    /**
     * True if has "f" element
     */
    boolean isSetF();
    
    /**
     * Sets the "f" element
     */
    void setF(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula f);
    
    /**
     * Appends and returns a new empty "f" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula addNewF();
    
    /**
     * Unsets the "f" element
     */
    void unsetF();
    
    /**
     * Gets the "v" element
     */
    java.lang.String getV();
    
    /**
     * Gets (as xml) the "v" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetV();
    
    /**
     * True if has "v" element
     */
    boolean isSetV();
    
    /**
     * Sets the "v" element
     */
    void setV(java.lang.String v);
    
    /**
     * Sets (as xml) the "v" element
     */
    void xsetV(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring v);
    
    /**
     * Unsets the "v" element
     */
    void unsetV();
    
    /**
     * Gets the "is" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst getIs();
    
    /**
     * True if has "is" element
     */
    boolean isSetIs();
    
    /**
     * Sets the "is" element
     */
    void setIs(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst is);
    
    /**
     * Appends and returns a new empty "is" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst addNewIs();
    
    /**
     * Unsets the "is" element
     */
    void unsetIs();
    
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
     * Gets the "r" attribute
     */
    java.lang.String getR();
    
    /**
     * Gets (as xml) the "r" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef xgetR();
    
    /**
     * True if has "r" attribute
     */
    boolean isSetR();
    
    /**
     * Sets the "r" attribute
     */
    void setR(java.lang.String r);
    
    /**
     * Sets (as xml) the "r" attribute
     */
    void xsetR(org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellRef r);
    
    /**
     * Unsets the "r" attribute
     */
    void unsetR();
    
    /**
     * Gets the "s" attribute
     */
    long getS();
    
    /**
     * Gets (as xml) the "s" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetS();
    
    /**
     * True if has "s" attribute
     */
    boolean isSetS();
    
    /**
     * Sets the "s" attribute
     */
    void setS(long s);
    
    /**
     * Sets (as xml) the "s" attribute
     */
    void xsetS(org.apache.xmlbeans.XmlUnsignedInt s);
    
    /**
     * Unsets the "s" attribute
     */
    void unsetS();
    
    /**
     * Gets the "t" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType.Enum getT();
    
    /**
     * Gets (as xml) the "t" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType xgetT();
    
    /**
     * True if has "t" attribute
     */
    boolean isSetT();
    
    /**
     * Sets the "t" attribute
     */
    void setT(org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType.Enum t);
    
    /**
     * Sets (as xml) the "t" attribute
     */
    void xsetT(org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType t);
    
    /**
     * Unsets the "t" attribute
     */
    void unsetT();
    
    /**
     * Gets the "cm" attribute
     */
    long getCm();
    
    /**
     * Gets (as xml) the "cm" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetCm();
    
    /**
     * True if has "cm" attribute
     */
    boolean isSetCm();
    
    /**
     * Sets the "cm" attribute
     */
    void setCm(long cm);
    
    /**
     * Sets (as xml) the "cm" attribute
     */
    void xsetCm(org.apache.xmlbeans.XmlUnsignedInt cm);
    
    /**
     * Unsets the "cm" attribute
     */
    void unsetCm();
    
    /**
     * Gets the "vm" attribute
     */
    long getVm();
    
    /**
     * Gets (as xml) the "vm" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetVm();
    
    /**
     * True if has "vm" attribute
     */
    boolean isSetVm();
    
    /**
     * Sets the "vm" attribute
     */
    void setVm(long vm);
    
    /**
     * Sets (as xml) the "vm" attribute
     */
    void xsetVm(org.apache.xmlbeans.XmlUnsignedInt vm);
    
    /**
     * Unsets the "vm" attribute
     */
    void unsetVm();
    
    /**
     * Gets the "ph" attribute
     */
    boolean getPh();
    
    /**
     * Gets (as xml) the "ph" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetPh();
    
    /**
     * True if has "ph" attribute
     */
    boolean isSetPh();
    
    /**
     * Sets the "ph" attribute
     */
    void setPh(boolean ph);
    
    /**
     * Sets (as xml) the "ph" attribute
     */
    void xsetPh(org.apache.xmlbeans.XmlBoolean ph);
    
    /**
     * Unsets the "ph" attribute
     */
    void unsetPh();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
