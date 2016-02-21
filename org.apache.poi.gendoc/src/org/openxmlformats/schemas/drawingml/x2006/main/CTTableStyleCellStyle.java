/*
 * XML Type:  CT_TableStyleCellStyle
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/main
 * Java type: org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.main;


/**
 * An XML CT_TableStyleCellStyle(@http://schemas.openxmlformats.org/drawingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTableStyleCellStyle extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTableStyleCellStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttablestylecellstyle1fddtype");
    
    /**
     * Gets the "tcBdr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle getTcBdr();
    
    /**
     * True if has "tcBdr" element
     */
    boolean isSetTcBdr();
    
    /**
     * Sets the "tcBdr" element
     */
    void setTcBdr(org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle tcBdr);
    
    /**
     * Appends and returns a new empty "tcBdr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellBorderStyle addNewTcBdr();
    
    /**
     * Unsets the "tcBdr" element
     */
    void unsetTcBdr();
    
    /**
     * Gets the "fill" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties getFill();
    
    /**
     * True if has "fill" element
     */
    boolean isSetFill();
    
    /**
     * Sets the "fill" element
     */
    void setFill(org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties fill);
    
    /**
     * Appends and returns a new empty "fill" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties addNewFill();
    
    /**
     * Unsets the "fill" element
     */
    void unsetFill();
    
    /**
     * Gets the "fillRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference getFillRef();
    
    /**
     * True if has "fillRef" element
     */
    boolean isSetFillRef();
    
    /**
     * Sets the "fillRef" element
     */
    void setFillRef(org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference fillRef);
    
    /**
     * Appends and returns a new empty "fillRef" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTStyleMatrixReference addNewFillRef();
    
    /**
     * Unsets the "fillRef" element
     */
    void unsetFillRef();
    
    /**
     * Gets the "cell3D" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D getCell3D();
    
    /**
     * True if has "cell3D" element
     */
    boolean isSetCell3D();
    
    /**
     * Sets the "cell3D" element
     */
    void setCell3D(org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D cell3D);
    
    /**
     * Appends and returns a new empty "cell3D" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTCell3D addNewCell3D();
    
    /**
     * Unsets the "cell3D" element
     */
    void unsetCell3D();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
