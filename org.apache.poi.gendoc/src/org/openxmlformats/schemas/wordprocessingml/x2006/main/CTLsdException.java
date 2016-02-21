/*
 * XML Type:  CT_LsdException
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_LsdException(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTLsdException extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTLsdException.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctlsdexceptiona296type");
    
    /**
     * Gets the "name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STString xgetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(org.openxmlformats.schemas.wordprocessingml.x2006.main.STString name);
    
    /**
     * Gets the "locked" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getLocked();
    
    /**
     * Gets (as xml) the "locked" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetLocked();
    
    /**
     * True if has "locked" attribute
     */
    boolean isSetLocked();
    
    /**
     * Sets the "locked" attribute
     */
    void setLocked(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum locked);
    
    /**
     * Sets (as xml) the "locked" attribute
     */
    void xsetLocked(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff locked);
    
    /**
     * Unsets the "locked" attribute
     */
    void unsetLocked();
    
    /**
     * Gets the "uiPriority" attribute
     */
    java.math.BigInteger getUiPriority();
    
    /**
     * Gets (as xml) the "uiPriority" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber xgetUiPriority();
    
    /**
     * True if has "uiPriority" attribute
     */
    boolean isSetUiPriority();
    
    /**
     * Sets the "uiPriority" attribute
     */
    void setUiPriority(java.math.BigInteger uiPriority);
    
    /**
     * Sets (as xml) the "uiPriority" attribute
     */
    void xsetUiPriority(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber uiPriority);
    
    /**
     * Unsets the "uiPriority" attribute
     */
    void unsetUiPriority();
    
    /**
     * Gets the "semiHidden" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getSemiHidden();
    
    /**
     * Gets (as xml) the "semiHidden" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetSemiHidden();
    
    /**
     * True if has "semiHidden" attribute
     */
    boolean isSetSemiHidden();
    
    /**
     * Sets the "semiHidden" attribute
     */
    void setSemiHidden(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum semiHidden);
    
    /**
     * Sets (as xml) the "semiHidden" attribute
     */
    void xsetSemiHidden(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff semiHidden);
    
    /**
     * Unsets the "semiHidden" attribute
     */
    void unsetSemiHidden();
    
    /**
     * Gets the "unhideWhenUsed" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getUnhideWhenUsed();
    
    /**
     * Gets (as xml) the "unhideWhenUsed" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetUnhideWhenUsed();
    
    /**
     * True if has "unhideWhenUsed" attribute
     */
    boolean isSetUnhideWhenUsed();
    
    /**
     * Sets the "unhideWhenUsed" attribute
     */
    void setUnhideWhenUsed(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum unhideWhenUsed);
    
    /**
     * Sets (as xml) the "unhideWhenUsed" attribute
     */
    void xsetUnhideWhenUsed(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff unhideWhenUsed);
    
    /**
     * Unsets the "unhideWhenUsed" attribute
     */
    void unsetUnhideWhenUsed();
    
    /**
     * Gets the "qFormat" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getQFormat();
    
    /**
     * Gets (as xml) the "qFormat" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetQFormat();
    
    /**
     * True if has "qFormat" attribute
     */
    boolean isSetQFormat();
    
    /**
     * Sets the "qFormat" attribute
     */
    void setQFormat(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum qFormat);
    
    /**
     * Sets (as xml) the "qFormat" attribute
     */
    void xsetQFormat(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff qFormat);
    
    /**
     * Unsets the "qFormat" attribute
     */
    void unsetQFormat();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
