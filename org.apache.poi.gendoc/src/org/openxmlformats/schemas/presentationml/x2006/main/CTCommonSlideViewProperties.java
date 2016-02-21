/*
 * XML Type:  CT_CommonSlideViewProperties
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_CommonSlideViewProperties(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTCommonSlideViewProperties extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTCommonSlideViewProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctcommonslideviewproperties6ef1type");
    
    /**
     * Gets the "cViewPr" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTCommonViewProperties getCViewPr();
    
    /**
     * Sets the "cViewPr" element
     */
    void setCViewPr(org.openxmlformats.schemas.presentationml.x2006.main.CTCommonViewProperties cViewPr);
    
    /**
     * Appends and returns a new empty "cViewPr" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTCommonViewProperties addNewCViewPr();
    
    /**
     * Gets the "guideLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTGuideList getGuideLst();
    
    /**
     * True if has "guideLst" element
     */
    boolean isSetGuideLst();
    
    /**
     * Sets the "guideLst" element
     */
    void setGuideLst(org.openxmlformats.schemas.presentationml.x2006.main.CTGuideList guideLst);
    
    /**
     * Appends and returns a new empty "guideLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTGuideList addNewGuideLst();
    
    /**
     * Unsets the "guideLst" element
     */
    void unsetGuideLst();
    
    /**
     * Gets the "snapToGrid" attribute
     */
    boolean getSnapToGrid();
    
    /**
     * Gets (as xml) the "snapToGrid" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetSnapToGrid();
    
    /**
     * True if has "snapToGrid" attribute
     */
    boolean isSetSnapToGrid();
    
    /**
     * Sets the "snapToGrid" attribute
     */
    void setSnapToGrid(boolean snapToGrid);
    
    /**
     * Sets (as xml) the "snapToGrid" attribute
     */
    void xsetSnapToGrid(org.apache.xmlbeans.XmlBoolean snapToGrid);
    
    /**
     * Unsets the "snapToGrid" attribute
     */
    void unsetSnapToGrid();
    
    /**
     * Gets the "snapToObjects" attribute
     */
    boolean getSnapToObjects();
    
    /**
     * Gets (as xml) the "snapToObjects" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetSnapToObjects();
    
    /**
     * True if has "snapToObjects" attribute
     */
    boolean isSetSnapToObjects();
    
    /**
     * Sets the "snapToObjects" attribute
     */
    void setSnapToObjects(boolean snapToObjects);
    
    /**
     * Sets (as xml) the "snapToObjects" attribute
     */
    void xsetSnapToObjects(org.apache.xmlbeans.XmlBoolean snapToObjects);
    
    /**
     * Unsets the "snapToObjects" attribute
     */
    void unsetSnapToObjects();
    
    /**
     * Gets the "showGuides" attribute
     */
    boolean getShowGuides();
    
    /**
     * Gets (as xml) the "showGuides" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetShowGuides();
    
    /**
     * True if has "showGuides" attribute
     */
    boolean isSetShowGuides();
    
    /**
     * Sets the "showGuides" attribute
     */
    void setShowGuides(boolean showGuides);
    
    /**
     * Sets (as xml) the "showGuides" attribute
     */
    void xsetShowGuides(org.apache.xmlbeans.XmlBoolean showGuides);
    
    /**
     * Unsets the "showGuides" attribute
     */
    void unsetShowGuides();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideViewProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
