/*
 * XML Type:  CT_FldChar
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_FldChar(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTFldChar extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTFldChar.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctfldchare83etype");
    
    /**
     * Gets the "fldData" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText getFldData();
    
    /**
     * True if has "fldData" element
     */
    boolean isSetFldData();
    
    /**
     * Sets the "fldData" element
     */
    void setFldData(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText fldData);
    
    /**
     * Appends and returns a new empty "fldData" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText addNewFldData();
    
    /**
     * Unsets the "fldData" element
     */
    void unsetFldData();
    
    /**
     * Gets the "ffData" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData getFfData();
    
    /**
     * True if has "ffData" element
     */
    boolean isSetFfData();
    
    /**
     * Sets the "ffData" element
     */
    void setFfData(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData ffData);
    
    /**
     * Appends and returns a new empty "ffData" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFData addNewFfData();
    
    /**
     * Unsets the "ffData" element
     */
    void unsetFfData();
    
    /**
     * Gets the "numberingChange" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering getNumberingChange();
    
    /**
     * True if has "numberingChange" element
     */
    boolean isSetNumberingChange();
    
    /**
     * Sets the "numberingChange" element
     */
    void setNumberingChange(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering numberingChange);
    
    /**
     * Appends and returns a new empty "numberingChange" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChangeNumbering addNewNumberingChange();
    
    /**
     * Unsets the "numberingChange" element
     */
    void unsetNumberingChange();
    
    /**
     * Gets the "fldCharType" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType.Enum getFldCharType();
    
    /**
     * Gets (as xml) the "fldCharType" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType xgetFldCharType();
    
    /**
     * Sets the "fldCharType" attribute
     */
    void setFldCharType(org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType.Enum fldCharType);
    
    /**
     * Sets (as xml) the "fldCharType" attribute
     */
    void xsetFldCharType(org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType fldCharType);
    
    /**
     * Gets the "fldLock" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getFldLock();
    
    /**
     * Gets (as xml) the "fldLock" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetFldLock();
    
    /**
     * True if has "fldLock" attribute
     */
    boolean isSetFldLock();
    
    /**
     * Sets the "fldLock" attribute
     */
    void setFldLock(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum fldLock);
    
    /**
     * Sets (as xml) the "fldLock" attribute
     */
    void xsetFldLock(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff fldLock);
    
    /**
     * Unsets the "fldLock" attribute
     */
    void unsetFldLock();
    
    /**
     * Gets the "dirty" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getDirty();
    
    /**
     * Gets (as xml) the "dirty" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetDirty();
    
    /**
     * True if has "dirty" attribute
     */
    boolean isSetDirty();
    
    /**
     * Sets the "dirty" attribute
     */
    void setDirty(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum dirty);
    
    /**
     * Sets (as xml) the "dirty" attribute
     */
    void xsetDirty(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff dirty);
    
    /**
     * Unsets the "dirty" attribute
     */
    void unsetDirty();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
