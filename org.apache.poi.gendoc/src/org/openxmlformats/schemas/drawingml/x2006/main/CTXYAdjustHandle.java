/*
 * XML Type:  CT_XYAdjustHandle
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/main
 * Java type: org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.main;


/**
 * An XML CT_XYAdjustHandle(@http://schemas.openxmlformats.org/drawingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTXYAdjustHandle extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTXYAdjustHandle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctxyadjusthandlefaf3type");
    
    /**
     * Gets the "pos" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D getPos();
    
    /**
     * Sets the "pos" element
     */
    void setPos(org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D pos);
    
    /**
     * Appends and returns a new empty "pos" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D addNewPos();
    
    /**
     * Gets the "gdRefX" attribute
     */
    java.lang.String getGdRefX();
    
    /**
     * Gets (as xml) the "gdRefX" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName xgetGdRefX();
    
    /**
     * True if has "gdRefX" attribute
     */
    boolean isSetGdRefX();
    
    /**
     * Sets the "gdRefX" attribute
     */
    void setGdRefX(java.lang.String gdRefX);
    
    /**
     * Sets (as xml) the "gdRefX" attribute
     */
    void xsetGdRefX(org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName gdRefX);
    
    /**
     * Unsets the "gdRefX" attribute
     */
    void unsetGdRefX();
    
    /**
     * Gets the "minX" attribute
     */
    java.lang.Object getMinX();
    
    /**
     * Gets (as xml) the "minX" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate xgetMinX();
    
    /**
     * True if has "minX" attribute
     */
    boolean isSetMinX();
    
    /**
     * Sets the "minX" attribute
     */
    void setMinX(java.lang.Object minX);
    
    /**
     * Sets (as xml) the "minX" attribute
     */
    void xsetMinX(org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate minX);
    
    /**
     * Unsets the "minX" attribute
     */
    void unsetMinX();
    
    /**
     * Gets the "maxX" attribute
     */
    java.lang.Object getMaxX();
    
    /**
     * Gets (as xml) the "maxX" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate xgetMaxX();
    
    /**
     * True if has "maxX" attribute
     */
    boolean isSetMaxX();
    
    /**
     * Sets the "maxX" attribute
     */
    void setMaxX(java.lang.Object maxX);
    
    /**
     * Sets (as xml) the "maxX" attribute
     */
    void xsetMaxX(org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate maxX);
    
    /**
     * Unsets the "maxX" attribute
     */
    void unsetMaxX();
    
    /**
     * Gets the "gdRefY" attribute
     */
    java.lang.String getGdRefY();
    
    /**
     * Gets (as xml) the "gdRefY" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName xgetGdRefY();
    
    /**
     * True if has "gdRefY" attribute
     */
    boolean isSetGdRefY();
    
    /**
     * Sets the "gdRefY" attribute
     */
    void setGdRefY(java.lang.String gdRefY);
    
    /**
     * Sets (as xml) the "gdRefY" attribute
     */
    void xsetGdRefY(org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName gdRefY);
    
    /**
     * Unsets the "gdRefY" attribute
     */
    void unsetGdRefY();
    
    /**
     * Gets the "minY" attribute
     */
    java.lang.Object getMinY();
    
    /**
     * Gets (as xml) the "minY" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate xgetMinY();
    
    /**
     * True if has "minY" attribute
     */
    boolean isSetMinY();
    
    /**
     * Sets the "minY" attribute
     */
    void setMinY(java.lang.Object minY);
    
    /**
     * Sets (as xml) the "minY" attribute
     */
    void xsetMinY(org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate minY);
    
    /**
     * Unsets the "minY" attribute
     */
    void unsetMinY();
    
    /**
     * Gets the "maxY" attribute
     */
    java.lang.Object getMaxY();
    
    /**
     * Gets (as xml) the "maxY" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate xgetMaxY();
    
    /**
     * True if has "maxY" attribute
     */
    boolean isSetMaxY();
    
    /**
     * Sets the "maxY" attribute
     */
    void setMaxY(java.lang.Object maxY);
    
    /**
     * Sets (as xml) the "maxY" attribute
     */
    void xsetMaxY(org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate maxY);
    
    /**
     * Unsets the "maxY" attribute
     */
    void unsetMaxY();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
