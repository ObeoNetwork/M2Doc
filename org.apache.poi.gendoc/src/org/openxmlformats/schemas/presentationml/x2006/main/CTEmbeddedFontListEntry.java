/*
 * XML Type:  CT_EmbeddedFontListEntry
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_EmbeddedFontListEntry(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTEmbeddedFontListEntry extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTEmbeddedFontListEntry.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctembeddedfontlistentry48b4type");
    
    /**
     * Gets the "font" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont getFont();
    
    /**
     * Sets the "font" element
     */
    void setFont(org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont font);
    
    /**
     * Appends and returns a new empty "font" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont addNewFont();
    
    /**
     * Gets the "regular" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId getRegular();
    
    /**
     * True if has "regular" element
     */
    boolean isSetRegular();
    
    /**
     * Sets the "regular" element
     */
    void setRegular(org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId regular);
    
    /**
     * Appends and returns a new empty "regular" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId addNewRegular();
    
    /**
     * Unsets the "regular" element
     */
    void unsetRegular();
    
    /**
     * Gets the "bold" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId getBold();
    
    /**
     * True if has "bold" element
     */
    boolean isSetBold();
    
    /**
     * Sets the "bold" element
     */
    void setBold(org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId bold);
    
    /**
     * Appends and returns a new empty "bold" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId addNewBold();
    
    /**
     * Unsets the "bold" element
     */
    void unsetBold();
    
    /**
     * Gets the "italic" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId getItalic();
    
    /**
     * True if has "italic" element
     */
    boolean isSetItalic();
    
    /**
     * Sets the "italic" element
     */
    void setItalic(org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId italic);
    
    /**
     * Appends and returns a new empty "italic" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId addNewItalic();
    
    /**
     * Unsets the "italic" element
     */
    void unsetItalic();
    
    /**
     * Gets the "boldItalic" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId getBoldItalic();
    
    /**
     * True if has "boldItalic" element
     */
    boolean isSetBoldItalic();
    
    /**
     * Sets the "boldItalic" element
     */
    void setBoldItalic(org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId boldItalic);
    
    /**
     * Appends and returns a new empty "boldItalic" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId addNewBoldItalic();
    
    /**
     * Unsets the "boldItalic" element
     */
    void unsetBoldItalic();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
