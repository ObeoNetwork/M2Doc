/*
 * XML Type:  CT_BoxPr
 * Namespace: http://schemas.openxmlformats.org/officeDocument/2006/math
 * Java type: org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.officeDocument.x2006.math;


/**
 * An XML CT_BoxPr(@http://schemas.openxmlformats.org/officeDocument/2006/math).
 *
 * This is a complex type.
 */
public interface CTBoxPr extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTBoxPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctboxpr2c4ftype");
    
    /**
     * Gets the "opEmu" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getOpEmu();
    
    /**
     * True if has "opEmu" element
     */
    boolean isSetOpEmu();
    
    /**
     * Sets the "opEmu" element
     */
    void setOpEmu(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff opEmu);
    
    /**
     * Appends and returns a new empty "opEmu" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewOpEmu();
    
    /**
     * Unsets the "opEmu" element
     */
    void unsetOpEmu();
    
    /**
     * Gets the "noBreak" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getNoBreak();
    
    /**
     * True if has "noBreak" element
     */
    boolean isSetNoBreak();
    
    /**
     * Sets the "noBreak" element
     */
    void setNoBreak(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff noBreak);
    
    /**
     * Appends and returns a new empty "noBreak" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewNoBreak();
    
    /**
     * Unsets the "noBreak" element
     */
    void unsetNoBreak();
    
    /**
     * Gets the "diff" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getDiff();
    
    /**
     * True if has "diff" element
     */
    boolean isSetDiff();
    
    /**
     * Sets the "diff" element
     */
    void setDiff(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff diff);
    
    /**
     * Appends and returns a new empty "diff" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewDiff();
    
    /**
     * Unsets the "diff" element
     */
    void unsetDiff();
    
    /**
     * Gets the "brk" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTManualBreak getBrk();
    
    /**
     * True if has "brk" element
     */
    boolean isSetBrk();
    
    /**
     * Sets the "brk" element
     */
    void setBrk(org.openxmlformats.schemas.officeDocument.x2006.math.CTManualBreak brk);
    
    /**
     * Appends and returns a new empty "brk" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTManualBreak addNewBrk();
    
    /**
     * Unsets the "brk" element
     */
    void unsetBrk();
    
    /**
     * Gets the "aln" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff getAln();
    
    /**
     * True if has "aln" element
     */
    boolean isSetAln();
    
    /**
     * Sets the "aln" element
     */
    void setAln(org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff aln);
    
    /**
     * Appends and returns a new empty "aln" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.math.CTOnOff addNewAln();
    
    /**
     * Unsets the "aln" element
     */
    void unsetAln();
    
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
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr newInstance() {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.math.CTBoxPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
