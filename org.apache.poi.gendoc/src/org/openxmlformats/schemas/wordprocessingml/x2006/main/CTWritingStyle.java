/*
 * XML Type:  CT_WritingStyle
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_WritingStyle(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTWritingStyle extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTWritingStyle.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctwritingstyled853type");
    
    /**
     * Gets the "lang" attribute
     */
    java.lang.Object getLang();
    
    /**
     * Gets (as xml) the "lang" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STLang xgetLang();
    
    /**
     * Sets the "lang" attribute
     */
    void setLang(java.lang.Object lang);
    
    /**
     * Sets (as xml) the "lang" attribute
     */
    void xsetLang(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLang lang);
    
    /**
     * Gets the "vendorID" attribute
     */
    java.math.BigInteger getVendorID();
    
    /**
     * Gets (as xml) the "vendorID" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber xgetVendorID();
    
    /**
     * Sets the "vendorID" attribute
     */
    void setVendorID(java.math.BigInteger vendorID);
    
    /**
     * Sets (as xml) the "vendorID" attribute
     */
    void xsetVendorID(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber vendorID);
    
    /**
     * Gets the "dllVersion" attribute
     */
    java.math.BigInteger getDllVersion();
    
    /**
     * Gets (as xml) the "dllVersion" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber xgetDllVersion();
    
    /**
     * Sets the "dllVersion" attribute
     */
    void setDllVersion(java.math.BigInteger dllVersion);
    
    /**
     * Sets (as xml) the "dllVersion" attribute
     */
    void xsetDllVersion(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber dllVersion);
    
    /**
     * Gets the "nlCheck" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getNlCheck();
    
    /**
     * Gets (as xml) the "nlCheck" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetNlCheck();
    
    /**
     * True if has "nlCheck" attribute
     */
    boolean isSetNlCheck();
    
    /**
     * Sets the "nlCheck" attribute
     */
    void setNlCheck(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum nlCheck);
    
    /**
     * Sets (as xml) the "nlCheck" attribute
     */
    void xsetNlCheck(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff nlCheck);
    
    /**
     * Unsets the "nlCheck" attribute
     */
    void unsetNlCheck();
    
    /**
     * Gets the "checkStyle" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getCheckStyle();
    
    /**
     * Gets (as xml) the "checkStyle" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetCheckStyle();
    
    /**
     * Sets the "checkStyle" attribute
     */
    void setCheckStyle(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum checkStyle);
    
    /**
     * Sets (as xml) the "checkStyle" attribute
     */
    void xsetCheckStyle(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff checkStyle);
    
    /**
     * Gets the "appName" attribute
     */
    java.lang.String getAppName();
    
    /**
     * Gets (as xml) the "appName" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STString xgetAppName();
    
    /**
     * Sets the "appName" attribute
     */
    void setAppName(java.lang.String appName);
    
    /**
     * Sets (as xml) the "appName" attribute
     */
    void xsetAppName(org.openxmlformats.schemas.wordprocessingml.x2006.main.STString appName);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTWritingStyle) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
