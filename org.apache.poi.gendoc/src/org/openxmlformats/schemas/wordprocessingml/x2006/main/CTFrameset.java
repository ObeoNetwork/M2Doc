/*
 * XML Type:  CT_Frameset
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_Frameset(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTFrameset extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTFrameset.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctframeset1033type");
    
    /**
     * Gets the "sz" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString getSz();
    
    /**
     * True if has "sz" element
     */
    boolean isSetSz();
    
    /**
     * Sets the "sz" element
     */
    void setSz(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString sz);
    
    /**
     * Appends and returns a new empty "sz" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString addNewSz();
    
    /**
     * Unsets the "sz" element
     */
    void unsetSz();
    
    /**
     * Gets the "framesetSplitbar" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramesetSplitbar getFramesetSplitbar();
    
    /**
     * True if has "framesetSplitbar" element
     */
    boolean isSetFramesetSplitbar();
    
    /**
     * Sets the "framesetSplitbar" element
     */
    void setFramesetSplitbar(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramesetSplitbar framesetSplitbar);
    
    /**
     * Appends and returns a new empty "framesetSplitbar" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFramesetSplitbar addNewFramesetSplitbar();
    
    /**
     * Unsets the "framesetSplitbar" element
     */
    void unsetFramesetSplitbar();
    
    /**
     * Gets the "frameLayout" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameLayout getFrameLayout();
    
    /**
     * True if has "frameLayout" element
     */
    boolean isSetFrameLayout();
    
    /**
     * Sets the "frameLayout" element
     */
    void setFrameLayout(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameLayout frameLayout);
    
    /**
     * Appends and returns a new empty "frameLayout" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameLayout addNewFrameLayout();
    
    /**
     * Unsets the "frameLayout" element
     */
    void unsetFrameLayout();
    
    /**
     * Gets a List of "frameset" elements
     */
    java.util.List<org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset> getFramesetList();
    
    /**
     * Gets array of all "frameset" elements
     * @deprecated
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset[] getFramesetArray();
    
    /**
     * Gets ith "frameset" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset getFramesetArray(int i);
    
    /**
     * Returns number of "frameset" element
     */
    int sizeOfFramesetArray();
    
    /**
     * Sets array of all "frameset" element
     */
    void setFramesetArray(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset[] framesetArray);
    
    /**
     * Sets ith "frameset" element
     */
    void setFramesetArray(int i, org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset frameset);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "frameset" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset insertNewFrameset(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "frameset" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset addNewFrameset();
    
    /**
     * Removes the ith "frameset" element
     */
    void removeFrameset(int i);
    
    /**
     * Gets a List of "frame" elements
     */
    java.util.List<org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrame> getFrameList();
    
    /**
     * Gets array of all "frame" elements
     * @deprecated
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrame[] getFrameArray();
    
    /**
     * Gets ith "frame" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrame getFrameArray(int i);
    
    /**
     * Returns number of "frame" element
     */
    int sizeOfFrameArray();
    
    /**
     * Sets array of all "frame" element
     */
    void setFrameArray(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrame[] frameArray);
    
    /**
     * Sets ith "frame" element
     */
    void setFrameArray(int i, org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrame frame);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "frame" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrame insertNewFrame(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "frame" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrame addNewFrame();
    
    /**
     * Removes the ith "frame" element
     */
    void removeFrame(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFrameset) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
