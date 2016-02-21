/*
 * XML Type:  CT_StyleLabel
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/diagram
 * Java type: org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.diagram;


/**
 * An XML CT_StyleLabel(@http://schemas.openxmlformats.org/drawingml/2006/diagram).
 *
 * This is a complex type.
 */
public interface CTStyleLabel extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTStyleLabel.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctstylelabel8d8etype");
    
    /**
     * Gets the "scene3d" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D getScene3D();
    
    /**
     * True if has "scene3d" element
     */
    boolean isSetScene3D();
    
    /**
     * Sets the "scene3d" element
     */
    void setScene3D(org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D scene3D);
    
    /**
     * Appends and returns a new empty "scene3d" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D addNewScene3D();
    
    /**
     * Unsets the "scene3d" element
     */
    void unsetScene3D();
    
    /**
     * Gets the "sp3d" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D getSp3D();
    
    /**
     * True if has "sp3d" element
     */
    boolean isSetSp3D();
    
    /**
     * Sets the "sp3d" element
     */
    void setSp3D(org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D sp3D);
    
    /**
     * Appends and returns a new empty "sp3d" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D addNewSp3D();
    
    /**
     * Unsets the "sp3d" element
     */
    void unsetSp3D();
    
    /**
     * Gets the "txPr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTTextProps getTxPr();
    
    /**
     * True if has "txPr" element
     */
    boolean isSetTxPr();
    
    /**
     * Sets the "txPr" element
     */
    void setTxPr(org.openxmlformats.schemas.drawingml.x2006.diagram.CTTextProps txPr);
    
    /**
     * Appends and returns a new empty "txPr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTTextProps addNewTxPr();
    
    /**
     * Unsets the "txPr" element
     */
    void unsetTxPr();
    
    /**
     * Gets the "style" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle getStyle();
    
    /**
     * True if has "style" element
     */
    boolean isSetStyle();
    
    /**
     * Sets the "style" element
     */
    void setStyle(org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle style);
    
    /**
     * Appends and returns a new empty "style" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle addNewStyle();
    
    /**
     * Unsets the "style" element
     */
    void unsetStyle();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * Gets the "name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    org.apache.xmlbeans.XmlString xgetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(org.apache.xmlbeans.XmlString name);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
