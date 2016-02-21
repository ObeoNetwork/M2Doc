/*
 * XML Type:  CT_NaryPr
 * Namespace: http://schemas.openxmlformats.org/officeDocument/2006/math
 * Java type: org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.officeDocument.x2006.math;


/**
 * An XML CT_NaryPr(@http://schemas.openxmlformats.org/officeDocument/2006/math).
 *
 * This is a complex type.
 */
public interface CTNaryPr extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTNaryPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctnarypr7396type");
    
    /**
     * Gets the "chr" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTChar getChr();
    
    /**
     * True if has "chr" element
     */
    boolean isSetChr();
    
    /**
     * Sets the "chr" element
     */
    void setChr(org.openxmlformats.schemas.officeDocument.x2006.math.CTChar chr);
    
    /**
     * Appends and returns a new empty "chr" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTChar addNewChr();
    
    /**
     * Unsets the "chr" element
     */
    void unsetChr();
    
    /**
     * Gets the "limLoc" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLoc getLimLoc();
    
    /**
     * True if has "limLoc" element
     */
    boolean isSetLimLoc();
    
    /**
     * Sets the "limLoc" element
     */
    void setLimLoc(org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLoc limLoc);
    
    /**
     * Appends and returns a new empty "limLoc" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTLimLoc addNewLimLoc();
    
    /**
     * Unsets the "limLoc" element
     */
    void unsetLimLoc();
    
    /**
     * Gets the "grow" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getGrow();
    
    /**
     * True if has "grow" element
     */
    boolean isSetGrow();
    
    /**
     * Sets the "grow" element
     */
    void setGrow(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff grow);
    
    /**
     * Appends and returns a new empty "grow" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewGrow();
    
    /**
     * Unsets the "grow" element
     */
    void unsetGrow();
    
    /**
     * Gets the "subHide" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getSubHide();
    
    /**
     * True if has "subHide" element
     */
    boolean isSetSubHide();
    
    /**
     * Sets the "subHide" element
     */
    void setSubHide(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff subHide);
    
    /**
     * Appends and returns a new empty "subHide" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewSubHide();
    
    /**
     * Unsets the "subHide" element
     */
    void unsetSubHide();
    
    /**
     * Gets the "supHide" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getSupHide();
    
    /**
     * True if has "supHide" element
     */
    boolean isSetSupHide();
    
    /**
     * Sets the "supHide" element
     */
    void setSupHide(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff supHide);
    
    /**
     * Appends and returns a new empty "supHide" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewSupHide();
    
    /**
     * Unsets the "supHide" element
     */
    void unsetSupHide();
    
    /**
     * Gets the "ctrlPr" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr getCtrlPr();
    
    /**
     * True if has "ctrlPr" element
     */
    boolean isSetCtrlPr();
    
    /**
     * Sets the "ctrlPr" element
     */
    void setCtrlPr(org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr ctrlPr);
    
    /**
     * Appends and returns a new empty "ctrlPr" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTCtrlPr addNewCtrlPr();
    
    /**
     * Unsets the "ctrlPr" element
     */
    void unsetCtrlPr();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr newInstance() {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTNaryPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
