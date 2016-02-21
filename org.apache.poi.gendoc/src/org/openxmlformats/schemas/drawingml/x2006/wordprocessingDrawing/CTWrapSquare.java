/*
 * XML Type:  CT_WrapSquare
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing
 * Java type: org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;


/**
 * An XML CT_WrapSquare(@http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing).
 *
 * This is a complex type.
 */
public interface CTWrapSquare extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTWrapSquare.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctwrapsquare2678type");
    
    /**
     * Gets the "effectExtent" element
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent getEffectExtent();
    
    /**
     * True if has "effectExtent" element
     */
    boolean isSetEffectExtent();
    
    /**
     * Sets the "effectExtent" element
     */
    void setEffectExtent(org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent effectExtent);
    
    /**
     * Appends and returns a new empty "effectExtent" element
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTEffectExtent addNewEffectExtent();
    
    /**
     * Unsets the "effectExtent" element
     */
    void unsetEffectExtent();
    
    /**
     * Gets the "wrapText" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapText.Enum getWrapText();
    
    /**
     * Gets (as xml) the "wrapText" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapText xgetWrapText();
    
    /**
     * Sets the "wrapText" attribute
     */
    void setWrapText(org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapText.Enum wrapText);
    
    /**
     * Sets (as xml) the "wrapText" attribute
     */
    void xsetWrapText(org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapText wrapText);
    
    /**
     * Gets the "distT" attribute
     */
    long getDistT();
    
    /**
     * Gets (as xml) the "distT" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance xgetDistT();
    
    /**
     * True if has "distT" attribute
     */
    boolean isSetDistT();
    
    /**
     * Sets the "distT" attribute
     */
    void setDistT(long distT);
    
    /**
     * Sets (as xml) the "distT" attribute
     */
    void xsetDistT(org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance distT);
    
    /**
     * Unsets the "distT" attribute
     */
    void unsetDistT();
    
    /**
     * Gets the "distB" attribute
     */
    long getDistB();
    
    /**
     * Gets (as xml) the "distB" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance xgetDistB();
    
    /**
     * True if has "distB" attribute
     */
    boolean isSetDistB();
    
    /**
     * Sets the "distB" attribute
     */
    void setDistB(long distB);
    
    /**
     * Sets (as xml) the "distB" attribute
     */
    void xsetDistB(org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance distB);
    
    /**
     * Unsets the "distB" attribute
     */
    void unsetDistB();
    
    /**
     * Gets the "distL" attribute
     */
    long getDistL();
    
    /**
     * Gets (as xml) the "distL" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance xgetDistL();
    
    /**
     * True if has "distL" attribute
     */
    boolean isSetDistL();
    
    /**
     * Sets the "distL" attribute
     */
    void setDistL(long distL);
    
    /**
     * Sets (as xml) the "distL" attribute
     */
    void xsetDistL(org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance distL);
    
    /**
     * Unsets the "distL" attribute
     */
    void unsetDistL();
    
    /**
     * Gets the "distR" attribute
     */
    long getDistR();
    
    /**
     * Gets (as xml) the "distR" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance xgetDistR();
    
    /**
     * True if has "distR" attribute
     */
    boolean isSetDistR();
    
    /**
     * Sets the "distR" attribute
     */
    void setDistR(long distR);
    
    /**
     * Sets (as xml) the "distR" attribute
     */
    void xsetDistR(org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.STWrapDistance distR);
    
    /**
     * Unsets the "distR" attribute
     */
    void unsetDistR();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapSquare) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
