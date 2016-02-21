/*
 * XML Type:  CT_TLOleBuildChart
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLOleBuildChart(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLOleBuildChart extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLOleBuildChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttlolebuildchartc2eftype");
    
    /**
     * Gets the "spid" attribute
     */
    java.lang.String getSpid();
    
    /**
     * Gets (as xml) the "spid" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STShapeID xgetSpid();
    
    /**
     * Sets the "spid" attribute
     */
    void setSpid(java.lang.String spid);
    
    /**
     * Sets (as xml) the "spid" attribute
     */
    void xsetSpid(org.openxmlformats.schemas.drawingml.x2006.main.STShapeID spid);
    
    /**
     * Gets the "grpId" attribute
     */
    long getGrpId();
    
    /**
     * Gets (as xml) the "grpId" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetGrpId();
    
    /**
     * Sets the "grpId" attribute
     */
    void setGrpId(long grpId);
    
    /**
     * Sets (as xml) the "grpId" attribute
     */
    void xsetGrpId(org.apache.xmlbeans.XmlUnsignedInt grpId);
    
    /**
     * Gets the "uiExpand" attribute
     */
    boolean getUiExpand();
    
    /**
     * Gets (as xml) the "uiExpand" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetUiExpand();
    
    /**
     * True if has "uiExpand" attribute
     */
    boolean isSetUiExpand();
    
    /**
     * Sets the "uiExpand" attribute
     */
    void setUiExpand(boolean uiExpand);
    
    /**
     * Sets (as xml) the "uiExpand" attribute
     */
    void xsetUiExpand(org.apache.xmlbeans.XmlBoolean uiExpand);
    
    /**
     * Unsets the "uiExpand" attribute
     */
    void unsetUiExpand();
    
    /**
     * Gets the "bld" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLOleChartBuildType.Enum getBld();
    
    /**
     * Gets (as xml) the "bld" attribute
     */
    org.openxmlformats.schemas.presentationml.x2006.main.STTLOleChartBuildType xgetBld();
    
    /**
     * True if has "bld" attribute
     */
    boolean isSetBld();
    
    /**
     * Sets the "bld" attribute
     */
    void setBld(org.openxmlformats.schemas.presentationml.x2006.main.STTLOleChartBuildType.Enum bld);
    
    /**
     * Sets (as xml) the "bld" attribute
     */
    void xsetBld(org.openxmlformats.schemas.presentationml.x2006.main.STTLOleChartBuildType bld);
    
    /**
     * Unsets the "bld" attribute
     */
    void unsetBld();
    
    /**
     * Gets the "animBg" attribute
     */
    boolean getAnimBg();
    
    /**
     * Gets (as xml) the "animBg" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetAnimBg();
    
    /**
     * True if has "animBg" attribute
     */
    boolean isSetAnimBg();
    
    /**
     * Sets the "animBg" attribute
     */
    void setAnimBg(boolean animBg);
    
    /**
     * Sets (as xml) the "animBg" attribute
     */
    void xsetAnimBg(org.apache.xmlbeans.XmlBoolean animBg);
    
    /**
     * Unsets the "animBg" attribute
     */
    void unsetAnimBg();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLOleBuildChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
