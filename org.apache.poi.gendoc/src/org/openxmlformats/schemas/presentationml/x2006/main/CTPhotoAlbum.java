/*
 * XML Type:  CT_PhotoAlbum
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_PhotoAlbum(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPhotoAlbum extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPhotoAlbum.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctphotoalbum4368type");
    
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
     * Gets the "bw" attribute
     */
    boolean getBw();
    
    /**
     * Gets (as xml) the "bw" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetBw();
    
    /**
     * True if has "bw" attribute
     */
    boolean isSetBw();
    
    /**
     * Sets the "bw" attribute
     */
    void setBw(boolean bw);
    
    /**
     * Sets (as xml) the "bw" attribute
     */
    void xsetBw(org.apache.xmlbeans.XmlBoolean bw);
    
    /**
     * Unsets the "bw" attribute
     */
    void unsetBw();
    
    /**
     * Gets the "showCaptions" attribute
     */
    boolean getShowCaptions();
    
    /**
     * Gets (as xml) the "showCaptions" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetShowCaptions();
    
    /**
     * True if has "showCaptions" attribute
     */
    boolean isSetShowCaptions();
    
    /**
     * Sets the "showCaptions" attribute
     */
    void setShowCaptions(boolean showCaptions);
    
    /**
     * Sets (as xml) the "showCaptions" attribute
     */
    void xsetShowCaptions(org.apache.xmlbeans.XmlBoolean showCaptions);
    
    /**
     * Unsets the "showCaptions" attribute
     */
    void unsetShowCaptions();
    
    /**
     * Gets the "layout" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumLayout.Enum getLayout();
    
    /**
     * Gets (as xml) the "layout" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumLayout xgetLayout();
    
    /**
     * True if has "layout" attribute
     */
    boolean isSetLayout();
    
    /**
     * Sets the "layout" attribute
     */
    void setLayout(org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumLayout.Enum layout);
    
    /**
     * Sets (as xml) the "layout" attribute
     */
    void xsetLayout(org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumLayout layout);
    
    /**
     * Unsets the "layout" attribute
     */
    void unsetLayout();
    
    /**
     * Gets the "frame" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumFrameShape.Enum getFrame();
    
    /**
     * Gets (as xml) the "frame" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumFrameShape xgetFrame();
    
    /**
     * True if has "frame" attribute
     */
    boolean isSetFrame();
    
    /**
     * Sets the "frame" attribute
     */
    void setFrame(org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumFrameShape.Enum frame);
    
    /**
     * Sets (as xml) the "frame" attribute
     */
    void xsetFrame(org.openxmlformats.schemas.presentationml.x2006.main.STPhotoAlbumFrameShape frame);
    
    /**
     * Unsets the "frame" attribute
     */
    void unsetFrame();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
