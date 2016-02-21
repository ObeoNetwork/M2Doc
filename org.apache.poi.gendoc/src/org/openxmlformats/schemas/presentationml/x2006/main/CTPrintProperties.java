/*
 * XML Type:  CT_PrintProperties
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_PrintProperties(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPrintProperties extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPrintProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctprintproperties6e0ftype");
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * Gets the "prnWhat" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPrintWhat.Enum getPrnWhat();
    
    /**
     * Gets (as xml) the "prnWhat" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPrintWhat xgetPrnWhat();
    
    /**
     * True if has "prnWhat" attribute
     */
    boolean isSetPrnWhat();
    
    /**
     * Sets the "prnWhat" attribute
     */
    void setPrnWhat(org.openxmlformats.schemas.presentationml.x2006.main.STPrintWhat.Enum prnWhat);
    
    /**
     * Sets (as xml) the "prnWhat" attribute
     */
    void xsetPrnWhat(org.openxmlformats.schemas.presentationml.x2006.main.STPrintWhat prnWhat);
    
    /**
     * Unsets the "prnWhat" attribute
     */
    void unsetPrnWhat();
    
    /**
     * Gets the "clrMode" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPrintColorMode.Enum getClrMode();
    
    /**
     * Gets (as xml) the "clrMode" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPrintColorMode xgetClrMode();
    
    /**
     * True if has "clrMode" attribute
     */
    boolean isSetClrMode();
    
    /**
     * Sets the "clrMode" attribute
     */
    void setClrMode(org.openxmlformats.schemas.presentationml.x2006.main.STPrintColorMode.Enum clrMode);
    
    /**
     * Sets (as xml) the "clrMode" attribute
     */
    void xsetClrMode(org.openxmlformats.schemas.presentationml.x2006.main.STPrintColorMode clrMode);
    
    /**
     * Unsets the "clrMode" attribute
     */
    void unsetClrMode();
    
    /**
     * Gets the "hiddenSlides" attribute
     */
    boolean getHiddenSlides();
    
    /**
     * Gets (as xml) the "hiddenSlides" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetHiddenSlides();
    
    /**
     * True if has "hiddenSlides" attribute
     */
    boolean isSetHiddenSlides();
    
    /**
     * Sets the "hiddenSlides" attribute
     */
    void setHiddenSlides(boolean hiddenSlides);
    
    /**
     * Sets (as xml) the "hiddenSlides" attribute
     */
    void xsetHiddenSlides(org.apache.xmlbeans.XmlBoolean hiddenSlides);
    
    /**
     * Unsets the "hiddenSlides" attribute
     */
    void unsetHiddenSlides();
    
    /**
     * Gets the "scaleToFitPaper" attribute
     */
    boolean getScaleToFitPaper();
    
    /**
     * Gets (as xml) the "scaleToFitPaper" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetScaleToFitPaper();
    
    /**
     * True if has "scaleToFitPaper" attribute
     */
    boolean isSetScaleToFitPaper();
    
    /**
     * Sets the "scaleToFitPaper" attribute
     */
    void setScaleToFitPaper(boolean scaleToFitPaper);
    
    /**
     * Sets (as xml) the "scaleToFitPaper" attribute
     */
    void xsetScaleToFitPaper(org.apache.xmlbeans.XmlBoolean scaleToFitPaper);
    
    /**
     * Unsets the "scaleToFitPaper" attribute
     */
    void unsetScaleToFitPaper();
    
    /**
     * Gets the "frameSlides" attribute
     */
    boolean getFrameSlides();
    
    /**
     * Gets (as xml) the "frameSlides" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetFrameSlides();
    
    /**
     * True if has "frameSlides" attribute
     */
    boolean isSetFrameSlides();
    
    /**
     * Sets the "frameSlides" attribute
     */
    void setFrameSlides(boolean frameSlides);
    
    /**
     * Sets (as xml) the "frameSlides" attribute
     */
    void xsetFrameSlides(org.apache.xmlbeans.XmlBoolean frameSlides);
    
    /**
     * Unsets the "frameSlides" attribute
     */
    void unsetFrameSlides();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPrintProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
