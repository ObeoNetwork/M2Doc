/*
 * XML Type:  CT_RelSizeAnchor
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chartDrawing
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chartDrawing;


/**
 * An XML CT_RelSizeAnchor(@http://schemas.openxmlformats.org/drawingml/2006/chartDrawing).
 *
 * This is a complex type.
 */
public interface CTRelSizeAnchor extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTRelSizeAnchor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctrelsizeanchorf2e7type");
    
    /**
     * Gets the "from" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTMarker getFrom();
    
    /**
     * Sets the "from" element
     */
    void setFrom(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTMarker from);
    
    /**
     * Appends and returns a new empty "from" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTMarker addNewFrom();
    
    /**
     * Gets the "to" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTMarker getTo();
    
    /**
     * Sets the "to" element
     */
    void setTo(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTMarker to);
    
    /**
     * Appends and returns a new empty "to" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTMarker addNewTo();
    
    /**
     * Gets the "sp" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTShape getSp();
    
    /**
     * True if has "sp" element
     */
    boolean isSetSp();
    
    /**
     * Sets the "sp" element
     */
    void setSp(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTShape sp);
    
    /**
     * Appends and returns a new empty "sp" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTShape addNewSp();
    
    /**
     * Unsets the "sp" element
     */
    void unsetSp();
    
    /**
     * Gets the "grpSp" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTGroupShape getGrpSp();
    
    /**
     * True if has "grpSp" element
     */
    boolean isSetGrpSp();
    
    /**
     * Sets the "grpSp" element
     */
    void setGrpSp(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTGroupShape grpSp);
    
    /**
     * Appends and returns a new empty "grpSp" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTGroupShape addNewGrpSp();
    
    /**
     * Unsets the "grpSp" element
     */
    void unsetGrpSp();
    
    /**
     * Gets the "graphicFrame" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTGraphicFrame getGraphicFrame();
    
    /**
     * True if has "graphicFrame" element
     */
    boolean isSetGraphicFrame();
    
    /**
     * Sets the "graphicFrame" element
     */
    void setGraphicFrame(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTGraphicFrame graphicFrame);
    
    /**
     * Appends and returns a new empty "graphicFrame" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTGraphicFrame addNewGraphicFrame();
    
    /**
     * Unsets the "graphicFrame" element
     */
    void unsetGraphicFrame();
    
    /**
     * Gets the "cxnSp" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTConnector getCxnSp();
    
    /**
     * True if has "cxnSp" element
     */
    boolean isSetCxnSp();
    
    /**
     * Sets the "cxnSp" element
     */
    void setCxnSp(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTConnector cxnSp);
    
    /**
     * Appends and returns a new empty "cxnSp" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTConnector addNewCxnSp();
    
    /**
     * Unsets the "cxnSp" element
     */
    void unsetCxnSp();
    
    /**
     * Gets the "pic" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTPicture getPic();
    
    /**
     * True if has "pic" element
     */
    boolean isSetPic();
    
    /**
     * Sets the "pic" element
     */
    void setPic(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTPicture pic);
    
    /**
     * Appends and returns a new empty "pic" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTPicture addNewPic();
    
    /**
     * Unsets the "pic" element
     */
    void unsetPic();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
