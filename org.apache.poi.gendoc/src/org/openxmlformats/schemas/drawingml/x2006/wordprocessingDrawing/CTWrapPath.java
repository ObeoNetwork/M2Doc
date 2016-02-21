/*
 * XML Type:  CT_WrapPath
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing
 * Java type: org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;


/**
 * An XML CT_WrapPath(@http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing).
 *
 * This is a complex type.
 */
public interface CTWrapPath extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTWrapPath.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctwrappath02b0type");
    
    /**
     * Gets the "start" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D getStart();
    
    /**
     * Sets the "start" element
     */
    void setStart(org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D start);
    
    /**
     * Appends and returns a new empty "start" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D addNewStart();
    
    /**
     * Gets a List of "lineTo" elements
     */
    java.util.List<org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D> getLineToList();
    
    /**
     * Gets array of all "lineTo" elements
     * @deprecated
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D[] getLineToArray();
    
    /**
     * Gets ith "lineTo" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D getLineToArray(int i);
    
    /**
     * Returns number of "lineTo" element
     */
    int sizeOfLineToArray();
    
    /**
     * Sets array of all "lineTo" element
     */
    void setLineToArray(org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D[] lineToArray);
    
    /**
     * Sets ith "lineTo" element
     */
    void setLineToArray(int i, org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D lineTo);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "lineTo" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D insertNewLineTo(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "lineTo" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D addNewLineTo();
    
    /**
     * Removes the ith "lineTo" element
     */
    void removeLineTo(int i);
    
    /**
     * Gets the "edited" attribute
     */
    boolean getEdited();
    
    /**
     * Gets (as xml) the "edited" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetEdited();
    
    /**
     * True if has "edited" attribute
     */
    boolean isSetEdited();
    
    /**
     * Sets the "edited" attribute
     */
    void setEdited(boolean edited);
    
    /**
     * Sets (as xml) the "edited" attribute
     */
    void xsetEdited(org.apache.xmlbeans.XmlBoolean edited);
    
    /**
     * Unsets the "edited" attribute
     */
    void unsetEdited();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTWrapPath) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
