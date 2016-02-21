/*
 * XML Type:  CT_PictureOptions
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chart
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;


/**
 * An XML CT_PictureOptions(@http://schemas.openxmlformats.org/drawingml/2006/chart).
 *
 * This is a complex type.
 */
public interface CTPictureOptions extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPictureOptions.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctpictureoptions493ctype");
    
    /**
     * Gets the "applyToFront" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getApplyToFront();
    
    /**
     * True if has "applyToFront" element
     */
    boolean isSetApplyToFront();
    
    /**
     * Sets the "applyToFront" element
     */
    void setApplyToFront(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean applyToFront);
    
    /**
     * Appends and returns a new empty "applyToFront" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewApplyToFront();
    
    /**
     * Unsets the "applyToFront" element
     */
    void unsetApplyToFront();
    
    /**
     * Gets the "applyToSides" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getApplyToSides();
    
    /**
     * True if has "applyToSides" element
     */
    boolean isSetApplyToSides();
    
    /**
     * Sets the "applyToSides" element
     */
    void setApplyToSides(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean applyToSides);
    
    /**
     * Appends and returns a new empty "applyToSides" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewApplyToSides();
    
    /**
     * Unsets the "applyToSides" element
     */
    void unsetApplyToSides();
    
    /**
     * Gets the "applyToEnd" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getApplyToEnd();
    
    /**
     * True if has "applyToEnd" element
     */
    boolean isSetApplyToEnd();
    
    /**
     * Sets the "applyToEnd" element
     */
    void setApplyToEnd(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean applyToEnd);
    
    /**
     * Appends and returns a new empty "applyToEnd" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewApplyToEnd();
    
    /**
     * Unsets the "applyToEnd" element
     */
    void unsetApplyToEnd();
    
    /**
     * Gets the "pictureFormat" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureFormat getPictureFormat();
    
    /**
     * True if has "pictureFormat" element
     */
    boolean isSetPictureFormat();
    
    /**
     * Sets the "pictureFormat" element
     */
    void setPictureFormat(org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureFormat pictureFormat);
    
    /**
     * Appends and returns a new empty "pictureFormat" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureFormat addNewPictureFormat();
    
    /**
     * Unsets the "pictureFormat" element
     */
    void unsetPictureFormat();
    
    /**
     * Gets the "pictureStackUnit" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureStackUnit getPictureStackUnit();
    
    /**
     * True if has "pictureStackUnit" element
     */
    boolean isSetPictureStackUnit();
    
    /**
     * Sets the "pictureStackUnit" element
     */
    void setPictureStackUnit(org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureStackUnit pictureStackUnit);
    
    /**
     * Appends and returns a new empty "pictureStackUnit" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureStackUnit addNewPictureStackUnit();
    
    /**
     * Unsets the "pictureStackUnit" element
     */
    void unsetPictureStackUnit();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTPictureOptions) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
