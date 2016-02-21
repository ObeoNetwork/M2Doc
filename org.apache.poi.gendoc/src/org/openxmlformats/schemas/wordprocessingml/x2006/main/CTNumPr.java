/*
 * XML Type:  CT_NumPr
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_NumPr(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTNumPr extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTNumPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctnumpr16aatype");
    
    /**
     * Gets the "ilvl" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber getIlvl();
    
    /**
     * True if has "ilvl" element
     */
    boolean isSetIlvl();
    
    /**
     * Sets the "ilvl" element
     */
    void setIlvl(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber ilvl);
    
    /**
     * Appends and returns a new empty "ilvl" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber addNewIlvl();
    
    /**
     * Unsets the "ilvl" element
     */
    void unsetIlvl();
    
    /**
     * Gets the "numId" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber getNumId();
    
    /**
     * True if has "numId" element
     */
    boolean isSetNumId();
    
    /**
     * Sets the "numId" element
     */
    void setNumId(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber numId);
    
    /**
     * Appends and returns a new empty "numId" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber addNewNumId();
    
    /**
     * Unsets the "numId" element
     */
    void unsetNumId();
    
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
     * Gets the "ins" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange getIns();
    
    /**
     * True if has "ins" element
     */
    boolean isSetIns();
    
    /**
     * Sets the "ins" element
     */
    void setIns(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange ins);
    
    /**
     * Appends and returns a new empty "ins" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange addNewIns();
    
    /**
     * Unsets the "ins" element
     */
    void unsetIns();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumPr) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
