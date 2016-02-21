/*
 * XML Type:  CT_TLAnimateMotionBehavior
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLAnimateMotionBehavior(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLAnimateMotionBehavior extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLAnimateMotionBehavior.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttlanimatemotionbehavior043etype");
    
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
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint getBy();
    
    /**
     * True if has "by" element
     */
    boolean isSetBy();
    
    /**
     * Sets the "by" element
     */
    void setBy(org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint by);
    
    /**
     * Appends and returns a new empty "by" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint addNewBy();
    
    /**
     * Unsets the "by" element
     */
    void unsetBy();
    
    /**
     * Gets the "from" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint getFrom();
    
    /**
     * True if has "from" element
     */
    boolean isSetFrom();
    
    /**
     * Sets the "from" element
     */
    void setFrom(org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint from);
    
    /**
     * Appends and returns a new empty "from" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint addNewFrom();
    
    /**
     * Unsets the "from" element
     */
    void unsetFrom();
    
    /**
     * Gets the "to" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint getTo();
    
    /**
     * True if has "to" element
     */
    boolean isSetTo();
    
    /**
     * Sets the "to" element
     */
    void setTo(org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint to);
    
    /**
     * Appends and returns a new empty "to" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint addNewTo();
    
    /**
     * Unsets the "to" element
     */
    void unsetTo();
    
    /**
     * Gets the "rCtr" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint getRCtr();
    
    /**
     * True if has "rCtr" element
     */
    boolean isSetRCtr();
    
    /**
     * Sets the "rCtr" element
     */
    void setRCtr(org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint rCtr);
    
    /**
     * Appends and returns a new empty "rCtr" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLPoint addNewRCtr();
    
    /**
     * Unsets the "rCtr" element
     */
    void unsetRCtr();
    
    /**
     * Gets the "origin" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionBehaviorOrigin.Enum getOrigin();
    
    /**
     * Gets (as xml) the "origin" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionBehaviorOrigin xgetOrigin();
    
    /**
     * True if has "origin" attribute
     */
    boolean isSetOrigin();
    
    /**
     * Sets the "origin" attribute
     */
    void setOrigin(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionBehaviorOrigin.Enum origin);
    
    /**
     * Sets (as xml) the "origin" attribute
     */
    void xsetOrigin(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionBehaviorOrigin origin);
    
    /**
     * Unsets the "origin" attribute
     */
    void unsetOrigin();
    
    /**
     * Gets the "path" attribute
     */
    java.lang.String getPath();
    
    /**
     * Gets (as xml) the "path" attribute
     */
    org.apache.xmlbeans.XmlString xgetPath();
    
    /**
     * True if has "path" attribute
     */
    boolean isSetPath();
    
    /**
     * Sets the "path" attribute
     */
    void setPath(java.lang.String path);
    
    /**
     * Sets (as xml) the "path" attribute
     */
    void xsetPath(org.apache.xmlbeans.XmlString path);
    
    /**
     * Unsets the "path" attribute
     */
    void unsetPath();
    
    /**
     * Gets the "pathEditMode" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionPathEditMode.Enum getPathEditMode();
    
    /**
     * Gets (as xml) the "pathEditMode" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionPathEditMode xgetPathEditMode();
    
    /**
     * True if has "pathEditMode" attribute
     */
    boolean isSetPathEditMode();
    
    /**
     * Sets the "pathEditMode" attribute
     */
    void setPathEditMode(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionPathEditMode.Enum pathEditMode);
    
    /**
     * Sets (as xml) the "pathEditMode" attribute
     */
    void xsetPathEditMode(org.openxmlformats.schemas.presentationml.x2006.main.STTLAnimateMotionPathEditMode pathEditMode);
    
    /**
     * Unsets the "pathEditMode" attribute
     */
    void unsetPathEditMode();
    
    /**
     * Gets the "rAng" attribute
     */
    int getRAng();
    
    /**
     * Gets (as xml) the "rAng" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STAngle xgetRAng();
    
    /**
     * True if has "rAng" attribute
     */
    boolean isSetRAng();
    
    /**
     * Sets the "rAng" attribute
     */
    void setRAng(int rAng);
    
    /**
     * Sets (as xml) the "rAng" attribute
     */
    void xsetRAng(org.openxmlformats.schemas.drawingml.x2006.main.STAngle rAng);
    
    /**
     * Unsets the "rAng" attribute
     */
    void unsetRAng();
    
    /**
     * Gets the "ptsTypes" attribute
     */
    java.lang.String getPtsTypes();
    
    /**
     * Gets (as xml) the "ptsTypes" attribute
     */
    org.apache.xmlbeans.XmlString xgetPtsTypes();
    
    /**
     * True if has "ptsTypes" attribute
     */
    boolean isSetPtsTypes();
    
    /**
     * Sets the "ptsTypes" attribute
     */
    void setPtsTypes(java.lang.String ptsTypes);
    
    /**
     * Sets (as xml) the "ptsTypes" attribute
     */
    void xsetPtsTypes(org.apache.xmlbeans.XmlString ptsTypes);
    
    /**
     * Unsets the "ptsTypes" attribute
     */
    void unsetPtsTypes();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
