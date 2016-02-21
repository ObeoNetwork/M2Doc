/*
 * XML Type:  CT_TLTimeNodeSequence
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLTimeNodeSequence(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLTimeNodeSequence extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLTimeNodeSequence.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttltimenodesequenced31dtype");
    
    /**
     * Gets the "cTn" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData getCTn();
    
    /**
     * Sets the "cTn" element
     */
    void setCTn(org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData cTn);
    
    /**
     * Appends and returns a new empty "cTn" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommonTimeNodeData addNewCTn();
    
    /**
     * Gets the "prevCondLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList getPrevCondLst();
    
    /**
     * True if has "prevCondLst" element
     */
    boolean isSetPrevCondLst();
    
    /**
     * Sets the "prevCondLst" element
     */
    void setPrevCondLst(org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList prevCondLst);
    
    /**
     * Appends and returns a new empty "prevCondLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList addNewPrevCondLst();
    
    /**
     * Unsets the "prevCondLst" element
     */
    void unsetPrevCondLst();
    
    /**
     * Gets the "nextCondLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList getNextCondLst();
    
    /**
     * True if has "nextCondLst" element
     */
    boolean isSetNextCondLst();
    
    /**
     * Sets the "nextCondLst" element
     */
    void setNextCondLst(org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList nextCondLst);
    
    /**
     * Appends and returns a new empty "nextCondLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList addNewNextCondLst();
    
    /**
     * Unsets the "nextCondLst" element
     */
    void unsetNextCondLst();
    
    /**
     * Gets the "concurrent" attribute
     */
    boolean getConcurrent();
    
    /**
     * Gets (as xml) the "concurrent" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetConcurrent();
    
    /**
     * True if has "concurrent" attribute
     */
    boolean isSetConcurrent();
    
    /**
     * Sets the "concurrent" attribute
     */
    void setConcurrent(boolean concurrent);
    
    /**
     * Sets (as xml) the "concurrent" attribute
     */
    void xsetConcurrent(org.apache.xmlbeans.XmlBoolean concurrent);
    
    /**
     * Unsets the "concurrent" attribute
     */
    void unsetConcurrent();
    
    /**
     * Gets the "prevAc" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLPreviousActionType.Enum getPrevAc();
    
    /**
     * Gets (as xml) the "prevAc" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLPreviousActionType xgetPrevAc();
    
    /**
     * True if has "prevAc" attribute
     */
    boolean isSetPrevAc();
    
    /**
     * Sets the "prevAc" attribute
     */
    void setPrevAc(org.openxmlformats.schemas.presentationml.x2006.main.STTLPreviousActionType.Enum prevAc);
    
    /**
     * Sets (as xml) the "prevAc" attribute
     */
    void xsetPrevAc(org.openxmlformats.schemas.presentationml.x2006.main.STTLPreviousActionType prevAc);
    
    /**
     * Unsets the "prevAc" attribute
     */
    void unsetPrevAc();
    
    /**
     * Gets the "nextAc" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLNextActionType.Enum getNextAc();
    
    /**
     * Gets (as xml) the "nextAc" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLNextActionType xgetNextAc();
    
    /**
     * True if has "nextAc" attribute
     */
    boolean isSetNextAc();
    
    /**
     * Sets the "nextAc" attribute
     */
    void setNextAc(org.openxmlformats.schemas.presentationml.x2006.main.STTLNextActionType.Enum nextAc);
    
    /**
     * Sets (as xml) the "nextAc" attribute
     */
    void xsetNextAc(org.openxmlformats.schemas.presentationml.x2006.main.STTLNextActionType nextAc);
    
    /**
     * Unsets the "nextAc" attribute
     */
    void unsetNextAc();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
