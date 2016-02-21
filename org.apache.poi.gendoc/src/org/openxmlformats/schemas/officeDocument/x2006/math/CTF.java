/*
 * XML Type:  CT_F
 * Namespace: http://schemas.openxmlformats.org/officeDocument/2006/math
 * Java type: org.openxmlformats.schemas.officeDocument.x2006.math.CTF
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.officeDocument.x2006.math;


/**
 * An XML CT_F(@http://schemas.openxmlformats.org/officeDocument/2006/math).
 *
 * This is a complex type.
 */
public interface CTF extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTF.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctfaff6type");
    
    /**
     * Gets the "fPr" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTFPr getFPr();
    
    /**
     * True if has "fPr" element
     */
    boolean isSetFPr();
    
    /**
     * Sets the "fPr" element
     */
    void setFPr(org.openxmlformats.schemas.officeDocument.x2006.math.CTFPr fPr);
    
    /**
     * Appends and returns a new empty "fPr" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTFPr addNewFPr();
    
    /**
     * Unsets the "fPr" element
     */
    void unsetFPr();
    
    /**
     * Gets the "num" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg getNum();
    
    /**
     * Sets the "num" element
     */
    void setNum(org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg num);
    
    /**
     * Appends and returns a new empty "num" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg addNewNum();
    
    /**
     * Gets the "den" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg getDen();
    
    /**
     * Sets the "den" element
     */
    void setDen(org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg den);
    
    /**
     * Appends and returns a new empty "den" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathArg addNewDen();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF newInstance() {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTF parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTF) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
