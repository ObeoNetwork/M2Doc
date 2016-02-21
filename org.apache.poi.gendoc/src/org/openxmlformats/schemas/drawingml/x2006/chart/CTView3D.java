/*
 * XML Type:  CT_View3D
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chart
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;


/**
 * An XML CT_View3D(@http://schemas.openxmlformats.org/drawingml/2006/chart).
 *
 * This is a complex type.
 */
public interface CTView3D extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTView3D.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctview3daf66type");
    
    /**
     * Gets the "rotX" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTRotX getRotX();
    
    /**
     * True if has "rotX" element
     */
    boolean isSetRotX();
    
    /**
     * Sets the "rotX" element
     */
    void setRotX(org.openxmlformats.schemas.drawingml.x2006.chart.CTRotX rotX);
    
    /**
     * Appends and returns a new empty "rotX" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTRotX addNewRotX();
    
    /**
     * Unsets the "rotX" element
     */
    void unsetRotX();
    
    /**
     * Gets the "hPercent" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTHPercent getHPercent();
    
    /**
     * True if has "hPercent" element
     */
    boolean isSetHPercent();
    
    /**
     * Sets the "hPercent" element
     */
    void setHPercent(org.openxmlformats.schemas.drawingml.x2006.chart.CTHPercent hPercent);
    
    /**
     * Appends and returns a new empty "hPercent" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTHPercent addNewHPercent();
    
    /**
     * Unsets the "hPercent" element
     */
    void unsetHPercent();
    
    /**
     * Gets the "rotY" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY getRotY();
    
    /**
     * True if has "rotY" element
     */
    boolean isSetRotY();
    
    /**
     * Sets the "rotY" element
     */
    void setRotY(org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY rotY);
    
    /**
     * Appends and returns a new empty "rotY" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY addNewRotY();
    
    /**
     * Unsets the "rotY" element
     */
    void unsetRotY();
    
    /**
     * Gets the "depthPercent" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDepthPercent getDepthPercent();
    
    /**
     * True if has "depthPercent" element
     */
    boolean isSetDepthPercent();
    
    /**
     * Sets the "depthPercent" element
     */
    void setDepthPercent(org.openxmlformats.schemas.drawingml.x2006.chart.CTDepthPercent depthPercent);
    
    /**
     * Appends and returns a new empty "depthPercent" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDepthPercent addNewDepthPercent();
    
    /**
     * Unsets the "depthPercent" element
     */
    void unsetDepthPercent();
    
    /**
     * Gets the "rAngAx" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getRAngAx();
    
    /**
     * True if has "rAngAx" element
     */
    boolean isSetRAngAx();
    
    /**
     * Sets the "rAngAx" element
     */
    void setRAngAx(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean rAngAx);
    
    /**
     * Appends and returns a new empty "rAngAx" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewRAngAx();
    
    /**
     * Unsets the "rAngAx" element
     */
    void unsetRAngAx();
    
    /**
     * Gets the "perspective" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPerspective getPerspective();
    
    /**
     * True if has "perspective" element
     */
    boolean isSetPerspective();
    
    /**
     * Sets the "perspective" element
     */
    void setPerspective(org.openxmlformats.schemas.drawingml.x2006.chart.CTPerspective perspective);
    
    /**
     * Appends and returns a new empty "perspective" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPerspective addNewPerspective();
    
    /**
     * Unsets the "perspective" element
     */
    void unsetPerspective();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList addNewExtLst();
    
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
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
