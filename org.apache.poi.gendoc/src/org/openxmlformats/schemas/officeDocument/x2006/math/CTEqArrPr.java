/*
 * XML Type:  CT_EqArrPr
 * Namespace: http://schemas.openxmlformats.org/officeDocument/2006/math
 * Java type: org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.officeDocument.x2006.math;


/**
 * An XML CT_EqArrPr(@http://schemas.openxmlformats.org/officeDocument/2006/math).
 *
 * This is a complex type.
 */
public interface CTEqArrPr extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTEqArrPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cteqarrpr1305type");
    
    /**
     * Gets the "baseJc" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTYAlign getBaseJc();
    
    /**
     * True if has "baseJc" element
     */
    boolean isSetBaseJc();
    
    /**
     * Sets the "baseJc" element
     */
    void setBaseJc(org.openxmlformats.schemas.officeDocument.x2006.math.CTYAlign baseJc);
    
    /**
     * Appends and returns a new empty "baseJc" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTYAlign addNewBaseJc();
    
    /**
     * Unsets the "baseJc" element
     */
    void unsetBaseJc();
    
    /**
     * Gets the "maxDist" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getMaxDist();
    
    /**
     * True if has "maxDist" element
     */
    boolean isSetMaxDist();
    
    /**
     * Sets the "maxDist" element
     */
    void setMaxDist(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff maxDist);
    
    /**
     * Appends and returns a new empty "maxDist" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewMaxDist();
    
    /**
     * Unsets the "maxDist" element
     */
    void unsetMaxDist();
    
    /**
     * Gets the "objDist" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getObjDist();
    
    /**
     * True if has "objDist" element
     */
    boolean isSetObjDist();
    
    /**
     * Sets the "objDist" element
     */
    void setObjDist(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff objDist);
    
    /**
     * Appends and returns a new empty "objDist" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewObjDist();
    
    /**
     * Unsets the "objDist" element
     */
    void unsetObjDist();
    
    /**
     * Gets the "rSpRule" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTSpacingRule getRSpRule();
    
    /**
     * True if has "rSpRule" element
     */
    boolean isSetRSpRule();
    
    /**
     * Sets the "rSpRule" element
     */
    void setRSpRule(org.openxmlformats.schemas.officeDocument.x2006.math.CTSpacingRule rSpRule);
    
    /**
     * Appends and returns a new empty "rSpRule" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTSpacingRule addNewRSpRule();
    
    /**
     * Unsets the "rSpRule" element
     */
    void unsetRSpRule();
    
    /**
     * Gets the "rSp" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTUnSignedInteger getRSp();
    
    /**
     * True if has "rSp" element
     */
    boolean isSetRSp();
    
    /**
     * Sets the "rSp" element
     */
    void setRSp(org.openxmlformats.schemas.officeDocument.x2006.math.CTUnSignedInteger rSp);
    
    /**
     * Appends and returns a new empty "rSp" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTUnSignedInteger addNewRSp();
    
    /**
     * Unsets the "rSp" element
     */
    void unsetRSp();
    
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
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr newInstance() {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTEqArrPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
