/*
 * XML Type:  ST_ParameterVal
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/diagram
 * Java type: org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.diagram;


/**
 * An XML ST_ParameterVal(@http://schemas.openxmlformats.org/drawingml/2006/diagram).
 *
 * This is a union type. Instances are of one of the following types:
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STHorizontalAlignment
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STVerticalAlignment
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STChildDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STChildAlignment
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STSecondaryChildAlignment
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STLinearDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STSecondaryLinearDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STStartingElement
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STBendPoint
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STConnectorRouting
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STArrowheadStyle
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STConnectorDimension
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STRotationPath
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STCenterShapeMapping
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STNodeHorizontalAlignment
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STNodeVerticalAlignment
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STFallbackDimension
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STTextDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STPyramidAccentPosition
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STPyramidAccentTextMargin
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STTextBlockDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STTextAnchorHorizontal
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STTextAnchorVertical
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STTextAlignment
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STAutoTextRotation
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STGrowDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STFlowDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STContinueDirection
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STBreakpoint
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STOffset
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STHierarchyAlignment
 *     org.apache.xmlbeans.XmlInt
 *     org.apache.xmlbeans.XmlDouble
 *     org.apache.xmlbeans.XmlBoolean
 *     org.apache.xmlbeans.XmlString
 *     org.openxmlformats.schemas.drawingml.x2006.diagram.STConnectorPoint
 */
public interface STParameterVal extends org.apache.xmlbeans.XmlAnySimpleType
{
    java.lang.Object getObjectValue();
    void setObjectValue(java.lang.Object val);
    /** @deprecated */
    java.lang.Object objectValue();
    /** @deprecated */
    void objectSet(java.lang.Object val);
    org.apache.xmlbeans.SchemaType instanceType();
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(STParameterVal.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("stparametervalc369type");
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal newValue(java.lang.Object obj) {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) type.newValue( obj ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.STParameterVal) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
