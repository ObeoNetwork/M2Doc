/*
 * XML Type:  CT_Pt
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/diagram
 * Java type: org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.diagram;


/**
 * An XML CT_Pt(@http://schemas.openxmlformats.org/drawingml/2006/diagram).
 *
 * This is a complex type.
 */
public interface CTPt extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPt.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctpt616dtype");
    
    /**
     * Gets the "prSet" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTElemPropSet getPrSet();
    
    /**
     * True if has "prSet" element
     */
    boolean isSetPrSet();
    
    /**
     * Sets the "prSet" element
     */
    void setPrSet(org.openxmlformats.schemas.drawingml.x2006.diagram.CTElemPropSet prSet);
    
    /**
     * Appends and returns a new empty "prSet" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTElemPropSet addNewPrSet();
    
    /**
     * Unsets the "prSet" element
     */
    void unsetPrSet();
    
    /**
     * Gets the "spPr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties getSpPr();
    
    /**
     * True if has "spPr" element
     */
    boolean isSetSpPr();
    
    /**
     * Sets the "spPr" element
     */
    void setSpPr(org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties spPr);
    
    /**
     * Appends and returns a new empty "spPr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties addNewSpPr();
    
    /**
     * Unsets the "spPr" element
     */
    void unsetSpPr();
    
    /**
     * Gets the "t" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody getT();
    
    /**
     * True if has "t" element
     */
    boolean isSetT();
    
    /**
     * Sets the "t" element
     */
    void setT(org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody t);
    
    /**
     * Appends and returns a new empty "t" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody addNewT();
    
    /**
     * Unsets the "t" element
     */
    void unsetT();
    
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
     * Gets the "modelId" attribute
     */
    java.lang.Object getModelId();
    
    /**
     * Gets (as xml) the "modelId" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.STModelId xgetModelId();
    
    /**
     * Sets the "modelId" attribute
     */
    void setModelId(java.lang.Object modelId);
    
    /**
     * Sets (as xml) the "modelId" attribute
     */
    void xsetModelId(org.openxmlformats.schemas.drawingml.x2006.diagram.STModelId modelId);
    
    /**
     * Gets the "type" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.STPtType.Enum getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.STPtType xgetType();
    
    /**
     * True if has "type" attribute
     */
    boolean isSetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(org.openxmlformats.schemas.drawingml.x2006.diagram.STPtType.Enum type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(org.openxmlformats.schemas.drawingml.x2006.diagram.STPtType type);
    
    /**
     * Unsets the "type" attribute
     */
    void unsetType();
    
    /**
     * Gets the "cxnId" attribute
     */
    java.lang.Object getCxnId();
    
    /**
     * Gets (as xml) the "cxnId" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.STModelId xgetCxnId();
    
    /**
     * True if has "cxnId" attribute
     */
    boolean isSetCxnId();
    
    /**
     * Sets the "cxnId" attribute
     */
    void setCxnId(java.lang.Object cxnId);
    
    /**
     * Sets (as xml) the "cxnId" attribute
     */
    void xsetCxnId(org.openxmlformats.schemas.drawingml.x2006.diagram.STModelId cxnId);
    
    /**
     * Unsets the "cxnId" attribute
     */
    void unsetCxnId();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTPt) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
