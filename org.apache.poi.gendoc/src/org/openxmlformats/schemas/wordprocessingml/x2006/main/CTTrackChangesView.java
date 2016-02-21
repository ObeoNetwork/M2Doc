/*
 * XML Type:  CT_TrackChangesView
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_TrackChangesView(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTrackChangesView extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTrackChangesView.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttrackchangesview2c4btype");
    
    /**
     * Gets the "markup" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getMarkup();
    
    /**
     * Gets (as xml) the "markup" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetMarkup();
    
    /**
     * True if has "markup" attribute
     */
    boolean isSetMarkup();
    
    /**
     * Sets the "markup" attribute
     */
    void setMarkup(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum markup);
    
    /**
     * Sets (as xml) the "markup" attribute
     */
    void xsetMarkup(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff markup);
    
    /**
     * Unsets the "markup" attribute
     */
    void unsetMarkup();
    
    /**
     * Gets the "comments" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getComments();
    
    /**
     * Gets (as xml) the "comments" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetComments();
    
    /**
     * True if has "comments" attribute
     */
    boolean isSetComments();
    
    /**
     * Sets the "comments" attribute
     */
    void setComments(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum comments);
    
    /**
     * Sets (as xml) the "comments" attribute
     */
    void xsetComments(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff comments);
    
    /**
     * Unsets the "comments" attribute
     */
    void unsetComments();
    
    /**
     * Gets the "insDel" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getInsDel();
    
    /**
     * Gets (as xml) the "insDel" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetInsDel();
    
    /**
     * True if has "insDel" attribute
     */
    boolean isSetInsDel();
    
    /**
     * Sets the "insDel" attribute
     */
    void setInsDel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum insDel);
    
    /**
     * Sets (as xml) the "insDel" attribute
     */
    void xsetInsDel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff insDel);
    
    /**
     * Unsets the "insDel" attribute
     */
    void unsetInsDel();
    
    /**
     * Gets the "formatting" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getFormatting();
    
    /**
     * Gets (as xml) the "formatting" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetFormatting();
    
    /**
     * True if has "formatting" attribute
     */
    boolean isSetFormatting();
    
    /**
     * Sets the "formatting" attribute
     */
    void setFormatting(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum formatting);
    
    /**
     * Sets (as xml) the "formatting" attribute
     */
    void xsetFormatting(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff formatting);
    
    /**
     * Unsets the "formatting" attribute
     */
    void unsetFormatting();
    
    /**
     * Gets the "inkAnnotations" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getInkAnnotations();
    
    /**
     * Gets (as xml) the "inkAnnotations" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetInkAnnotations();
    
    /**
     * True if has "inkAnnotations" attribute
     */
    boolean isSetInkAnnotations();
    
    /**
     * Sets the "inkAnnotations" attribute
     */
    void setInkAnnotations(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum inkAnnotations);
    
    /**
     * Sets (as xml) the "inkAnnotations" attribute
     */
    void xsetInkAnnotations(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff inkAnnotations);
    
    /**
     * Unsets the "inkAnnotations" attribute
     */
    void unsetInkAnnotations();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangesView) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
