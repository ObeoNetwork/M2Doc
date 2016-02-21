/*
 * XML Type:  CT_AnchorClientData
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing
 * Java type: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;


/**
 * An XML CT_AnchorClientData(@http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing).
 *
 * This is a complex type.
 */
public interface CTAnchorClientData extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTAnchorClientData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctanchorclientdata02betype");
    
    /**
     * Gets the "fLocksWithSheet" attribute
     */
    boolean getFLocksWithSheet();
    
    /**
     * Gets (as xml) the "fLocksWithSheet" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetFLocksWithSheet();
    
    /**
     * True if has "fLocksWithSheet" attribute
     */
    boolean isSetFLocksWithSheet();
    
    /**
     * Sets the "fLocksWithSheet" attribute
     */
    void setFLocksWithSheet(boolean fLocksWithSheet);
    
    /**
     * Sets (as xml) the "fLocksWithSheet" attribute
     */
    void xsetFLocksWithSheet(org.apache.xmlbeans.XmlBoolean fLocksWithSheet);
    
    /**
     * Unsets the "fLocksWithSheet" attribute
     */
    void unsetFLocksWithSheet();
    
    /**
     * Gets the "fPrintsWithSheet" attribute
     */
    boolean getFPrintsWithSheet();
    
    /**
     * Gets (as xml) the "fPrintsWithSheet" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetFPrintsWithSheet();
    
    /**
     * True if has "fPrintsWithSheet" attribute
     */
    boolean isSetFPrintsWithSheet();
    
    /**
     * Sets the "fPrintsWithSheet" attribute
     */
    void setFPrintsWithSheet(boolean fPrintsWithSheet);
    
    /**
     * Sets (as xml) the "fPrintsWithSheet" attribute
     */
    void xsetFPrintsWithSheet(org.apache.xmlbeans.XmlBoolean fPrintsWithSheet);
    
    /**
     * Unsets the "fPrintsWithSheet" attribute
     */
    void unsetFPrintsWithSheet();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAnchorClientData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
