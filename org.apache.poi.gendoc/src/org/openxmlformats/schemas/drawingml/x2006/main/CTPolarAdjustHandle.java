/*
 * XML Type:  CT_PolarAdjustHandle
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/main
 * Java type: org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.main;


/**
 * An XML CT_PolarAdjustHandle(@http://schemas.openxmlformats.org/drawingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPolarAdjustHandle extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPolarAdjustHandle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctpolaradjusthandled0a6type");
    
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
     * Gets the "gdRefR" attribute
     */
    java.lang.String getGdRefR();
    
    /**
     * Gets (as xml) the "gdRefR" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName xgetGdRefR();
    
    /**
     * True if has "gdRefR" attribute
     */
    boolean isSetGdRefR();
    
    /**
     * Sets the "gdRefR" attribute
     */
    void setGdRefR(java.lang.String gdRefR);
    
    /**
     * Sets (as xml) the "gdRefR" attribute
     */
    void xsetGdRefR(org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName gdRefR);
    
    /**
     * Unsets the "gdRefR" attribute
     */
    void unsetGdRefR();
    
    /**
     * Gets the "minR" attribute
     */
    java.lang.Object getMinR();
    
    /**
     * Gets (as xml) the "minR" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate xgetMinR();
    
    /**
     * True if has "minR" attribute
     */
    boolean isSetMinR();
    
    /**
     * Sets the "minR" attribute
     */
    void setMinR(java.lang.Object minR);
    
    /**
     * Sets (as xml) the "minR" attribute
     */
    void xsetMinR(org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate minR);
    
    /**
     * Unsets the "minR" attribute
     */
    void unsetMinR();
    
    /**
     * Gets the "maxR" attribute
     */
    java.lang.Object getMaxR();
    
    /**
     * Gets (as xml) the "maxR" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate xgetMaxR();
    
    /**
     * True if has "maxR" attribute
     */
    boolean isSetMaxR();
    
    /**
     * Sets the "maxR" attribute
     */
    void setMaxR(java.lang.Object maxR);
    
    /**
     * Sets (as xml) the "maxR" attribute
     */
    void xsetMaxR(org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate maxR);
    
    /**
     * Unsets the "maxR" attribute
     */
    void unsetMaxR();
    
    /**
     * Gets the "gdRefAng" attribute
     */
    java.lang.String getGdRefAng();
    
    /**
     * Gets (as xml) the "gdRefAng" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName xgetGdRefAng();
    
    /**
     * True if has "gdRefAng" attribute
     */
    boolean isSetGdRefAng();
    
    /**
     * Sets the "gdRefAng" attribute
     */
    void setGdRefAng(java.lang.String gdRefAng);
    
    /**
     * Sets (as xml) the "gdRefAng" attribute
     */
    void xsetGdRefAng(org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName gdRefAng);
    
    /**
     * Unsets the "gdRefAng" attribute
     */
    void unsetGdRefAng();
    
    /**
     * Gets the "minAng" attribute
     */
    java.lang.Object getMinAng();
    
    /**
     * Gets (as xml) the "minAng" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle xgetMinAng();
    
    /**
     * True if has "minAng" attribute
     */
    boolean isSetMinAng();
    
    /**
     * Sets the "minAng" attribute
     */
    void setMinAng(java.lang.Object minAng);
    
    /**
     * Sets (as xml) the "minAng" attribute
     */
    void xsetMinAng(org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle minAng);
    
    /**
     * Unsets the "minAng" attribute
     */
    void unsetMinAng();
    
    /**
     * Gets the "maxAng" attribute
     */
    java.lang.Object getMaxAng();
    
    /**
     * Gets (as xml) the "maxAng" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle xgetMaxAng();
    
    /**
     * True if has "maxAng" attribute
     */
    boolean isSetMaxAng();
    
    /**
     * Sets the "maxAng" attribute
     */
    void setMaxAng(java.lang.Object maxAng);
    
    /**
     * Sets (as xml) the "maxAng" attribute
     */
    void xsetMaxAng(org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle maxAng);
    
    /**
     * Unsets the "maxAng" attribute
     */
    void unsetMaxAng();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
