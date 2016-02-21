/*
 * XML Type:  CT_TLAnimateColorBehavior
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLAnimateColorBehavior(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLAnimateColorBehavior extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLAnimateColorBehavior.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttlanimatecolorbehavior50f9type");
    
    /**
     * Gets the "cBhvr" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonBehaviorData getCBhvr();
    
    /**
     * Sets the "cBhvr" element
     */
    void setCBhvr(org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonBehaviorData cBhvr);
    
    /**
     * Appends and returns a new empty "cBhvr" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonBehaviorData addNewCBhvr();
    
    /**
     * Gets the "by" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLByAnimateColorTransform getBy();
    
    /**
     * True if has "by" element
     */
    boolean isSetBy();
    
    /**
     * Sets the "by" element
     */
    void setBy(org.openxmlformats.schemas.presentationml.x2006.main.CTTLByAnimateColorTransform by);
    
    /**
     * Appends and returns a new empty "by" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLByAnimateColorTransform addNewBy();
    
    /**
     * Unsets the "by" element
     */
    void unsetBy();
    
    /**
     * Gets the "from" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor getFrom();
    
    /**
     * True if has "from" element
     */
    boolean isSetFrom();
    
    /**
     * Sets the "from" element
     */
    void setFrom(org.openxmlformats.schemas.drawingml.x2006.main.CTColor from);
    
    /**
     * Appends and returns a new empty "from" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor addNewFrom();
    
    /**
     * Unsets the "from" element
     */
    void unsetFrom();
    
    /**
     * Gets the "to" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor getTo();
    
    /**
     * True if has "to" element
     */
    boolean isSetTo();
    
    /**
     * Sets the "to" element
     */
    void setTo(org.openxmlformats.schemas.drawingml.x2006.main.CTColor to);
    
    /**
     * Appends and returns a new empty "to" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor addNewTo();
    
    /**
     * Unsets the "to" element
     */
    void unsetTo();
    
    /**
     * Gets the "clrSpc" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorSpace.Enum getClrSpc();
    
    /**
     * Gets (as xml) the "clrSpc" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorSpace xgetClrSpc();
    
    /**
     * True if has "clrSpc" attribute
     */
    boolean isSetClrSpc();
    
    /**
     * Sets the "clrSpc" attribute
     */
    void setClrSpc(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorSpace.Enum clrSpc);
    
    /**
     * Sets (as xml) the "clrSpc" attribute
     */
    void xsetClrSpc(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorSpace clrSpc);
    
    /**
     * Unsets the "clrSpc" attribute
     */
    void unsetClrSpc();
    
    /**
     * Gets the "dir" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorDirection.Enum getDir();
    
    /**
     * Gets (as xml) the "dir" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorDirection xgetDir();
    
    /**
     * True if has "dir" attribute
     */
    boolean isSetDir();
    
    /**
     * Sets the "dir" attribute
     */
    void setDir(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorDirection.Enum dir);
    
    /**
     * Sets (as xml) the "dir" attribute
     */
    void xsetDir(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateColorDirection dir);
    
    /**
     * Unsets the "dir" attribute
     */
    void unsetDir();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
