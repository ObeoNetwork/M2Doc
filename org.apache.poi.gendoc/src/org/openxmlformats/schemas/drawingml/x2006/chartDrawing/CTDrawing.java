/*
 * XML Type:  CT_Drawing
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chartDrawing
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chartDrawing;


/**
 * An XML CT_Drawing(@http://schemas.openxmlformats.org/drawingml/2006/chartDrawing).
 *
 * This is a complex type.
 */
public interface CTDrawing extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDrawing.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctdrawingfb76type");
    
    /**
     * Gets a List of "relSizeAnchor" elements
     */
    java.util.List<org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor> getRelSizeAnchorList();
    
    /**
     * Gets array of all "relSizeAnchor" elements
     * @deprecated
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor[] getRelSizeAnchorArray();
    
    /**
     * Gets ith "relSizeAnchor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor getRelSizeAnchorArray(int i);
    
    /**
     * Returns number of "relSizeAnchor" element
     */
    int sizeOfRelSizeAnchorArray();
    
    /**
     * Sets array of all "relSizeAnchor" element
     */
    void setRelSizeAnchorArray(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor[] relSizeAnchorArray);
    
    /**
     * Sets ith "relSizeAnchor" element
     */
    void setRelSizeAnchorArray(int i, org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor relSizeAnchor);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "relSizeAnchor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor insertNewRelSizeAnchor(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "relSizeAnchor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTRelSizeAnchor addNewRelSizeAnchor();
    
    /**
     * Removes the ith "relSizeAnchor" element
     */
    void removeRelSizeAnchor(int i);
    
    /**
     * Gets a List of "absSizeAnchor" elements
     */
    java.util.List<org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTAbsSizeAnchor> getAbsSizeAnchorList();
    
    /**
     * Gets array of all "absSizeAnchor" elements
     * @deprecated
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTAbsSizeAnchor[] getAbsSizeAnchorArray();
    
    /**
     * Gets ith "absSizeAnchor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTAbsSizeAnchor getAbsSizeAnchorArray(int i);
    
    /**
     * Returns number of "absSizeAnchor" element
     */
    int sizeOfAbsSizeAnchorArray();
    
    /**
     * Sets array of all "absSizeAnchor" element
     */
    void setAbsSizeAnchorArray(org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTAbsSizeAnchor[] absSizeAnchorArray);
    
    /**
     * Sets ith "absSizeAnchor" element
     */
    void setAbsSizeAnchorArray(int i, org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTAbsSizeAnchor absSizeAnchor);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "absSizeAnchor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTAbsSizeAnchor insertNewAbsSizeAnchor(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "absSizeAnchor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTAbsSizeAnchor addNewAbsSizeAnchor();
    
    /**
     * Removes the ith "absSizeAnchor" element
     */
    void removeAbsSizeAnchor(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chartDrawing.CTDrawing) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
