/*
 * XML Type:  CT_GradientFill
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_GradientFill(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTGradientFill extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTGradientFill.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctgradientfillaf5ctype");
    
    /**
     * Gets a List of "stop" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientStop> getStopList();
    
    /**
     * Gets array of all "stop" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientStop[] getStopArray();
    
    /**
     * Gets ith "stop" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientStop getStopArray(int i);
    
    /**
     * Returns number of "stop" element
     */
    int sizeOfStopArray();
    
    /**
     * Sets array of all "stop" element
     */
    void setStopArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientStop[] stopArray);
    
    /**
     * Sets ith "stop" element
     */
    void setStopArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientStop stop);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "stop" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientStop insertNewStop(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "stop" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientStop addNewStop();
    
    /**
     * Removes the ith "stop" element
     */
    void removeStop(int i);
    
    /**
     * Gets the "type" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STGradientType.Enum getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STGradientType xgetType();
    
    /**
     * True if has "type" attribute
     */
    boolean isSetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STGradientType.Enum type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STGradientType type);
    
    /**
     * Unsets the "type" attribute
     */
    void unsetType();
    
    /**
     * Gets the "degree" attribute
     */
    double getDegree();
    
    /**
     * Gets (as xml) the "degree" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetDegree();
    
    /**
     * True if has "degree" attribute
     */
    boolean isSetDegree();
    
    /**
     * Sets the "degree" attribute
     */
    void setDegree(double degree);
    
    /**
     * Sets (as xml) the "degree" attribute
     */
    void xsetDegree(org.apache.xmlbeans.XmlDouble degree);
    
    /**
     * Unsets the "degree" attribute
     */
    void unsetDegree();
    
    /**
     * Gets the "left" attribute
     */
    double getLeft();
    
    /**
     * Gets (as xml) the "left" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetLeft();
    
    /**
     * True if has "left" attribute
     */
    boolean isSetLeft();
    
    /**
     * Sets the "left" attribute
     */
    void setLeft(double left);
    
    /**
     * Sets (as xml) the "left" attribute
     */
    void xsetLeft(org.apache.xmlbeans.XmlDouble left);
    
    /**
     * Unsets the "left" attribute
     */
    void unsetLeft();
    
    /**
     * Gets the "right" attribute
     */
    double getRight();
    
    /**
     * Gets (as xml) the "right" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetRight();
    
    /**
     * True if has "right" attribute
     */
    boolean isSetRight();
    
    /**
     * Sets the "right" attribute
     */
    void setRight(double right);
    
    /**
     * Sets (as xml) the "right" attribute
     */
    void xsetRight(org.apache.xmlbeans.XmlDouble right);
    
    /**
     * Unsets the "right" attribute
     */
    void unsetRight();
    
    /**
     * Gets the "top" attribute
     */
    double getTop();
    
    /**
     * Gets (as xml) the "top" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetTop();
    
    /**
     * True if has "top" attribute
     */
    boolean isSetTop();
    
    /**
     * Sets the "top" attribute
     */
    void setTop(double top);
    
    /**
     * Sets (as xml) the "top" attribute
     */
    void xsetTop(org.apache.xmlbeans.XmlDouble top);
    
    /**
     * Unsets the "top" attribute
     */
    void unsetTop();
    
    /**
     * Gets the "bottom" attribute
     */
    double getBottom();
    
    /**
     * Gets (as xml) the "bottom" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetBottom();
    
    /**
     * True if has "bottom" attribute
     */
    boolean isSetBottom();
    
    /**
     * Sets the "bottom" attribute
     */
    void setBottom(double bottom);
    
    /**
     * Sets (as xml) the "bottom" attribute
     */
    void xsetBottom(org.apache.xmlbeans.XmlDouble bottom);
    
    /**
     * Unsets the "bottom" attribute
     */
    void unsetBottom();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
