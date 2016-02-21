/*
 * XML Type:  CT_TLShapeTargetElement
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLShapeTargetElement(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLShapeTargetElement extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLShapeTargetElement.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttlshapetargetelement2763type");
    
    /**
     * Gets the "bg" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty getBg();
    
    /**
     * True if has "bg" element
     */
    boolean isSetBg();
    
    /**
     * Sets the "bg" element
     */
    void setBg(org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty bg);
    
    /**
     * Appends and returns a new empty "bg" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty addNewBg();
    
    /**
     * Unsets the "bg" element
     */
    void unsetBg();
    
    /**
     * Gets the "subSp" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId getSubSp();
    
    /**
     * True if has "subSp" element
     */
    boolean isSetSubSp();
    
    /**
     * Sets the "subSp" element
     */
    void setSubSp(org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId subSp);
    
    /**
     * Appends and returns a new empty "subSp" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLSubShapeId addNewSubSp();
    
    /**
     * Unsets the "subSp" element
     */
    void unsetSubSp();
    
    /**
     * Gets the "oleChartEl" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleChartTargetElement getOleChartEl();
    
    /**
     * True if has "oleChartEl" element
     */
    boolean isSetOleChartEl();
    
    /**
     * Sets the "oleChartEl" element
     */
    void setOleChartEl(org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleChartTargetElement oleChartEl);
    
    /**
     * Appends and returns a new empty "oleChartEl" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleChartTargetElement addNewOleChartEl();
    
    /**
     * Unsets the "oleChartEl" element
     */
    void unsetOleChartEl();
    
    /**
     * Gets the "txEl" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTextTargetElement getTxEl();
    
    /**
     * True if has "txEl" element
     */
    boolean isSetTxEl();
    
    /**
     * Sets the "txEl" element
     */
    void setTxEl(org.openxmlformats.schemas.presentationml.x2006.main.CTTLTextTargetElement txEl);
    
    /**
     * Appends and returns a new empty "txEl" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTextTargetElement addNewTxEl();
    
    /**
     * Unsets the "txEl" element
     */
    void unsetTxEl();
    
    /**
     * Gets the "graphicEl" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTAnimationElementChoice getGraphicEl();
    
    /**
     * True if has "graphicEl" element
     */
    boolean isSetGraphicEl();
    
    /**
     * Sets the "graphicEl" element
     */
    void setGraphicEl(org.openxmlformats.schemas.drawingml.x2006.main.CTAnimationElementChoice graphicEl);
    
    /**
     * Appends and returns a new empty "graphicEl" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTAnimationElementChoice addNewGraphicEl();
    
    /**
     * Unsets the "graphicEl" element
     */
    void unsetGraphicEl();
    
    /**
     * Gets the "spid" attribute
     */
    java.lang.String getSpid();
    
    /**
     * Gets (as xml) the "spid" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STShapeID xgetSpid();
    
    /**
     * Sets the "spid" attribute
     */
    void setSpid(java.lang.String spid);
    
    /**
     * Sets (as xml) the "spid" attribute
     */
    void xsetSpid(org.openxmlformats.schemas.drawingml.x2006.main.STShapeID spid);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLShapeTargetElement) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
