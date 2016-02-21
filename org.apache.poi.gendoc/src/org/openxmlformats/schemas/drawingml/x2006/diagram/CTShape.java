/*
 * XML Type:  CT_Shape
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/diagram
 * Java type: org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.diagram;


/**
 * An XML CT_Shape(@http://schemas.openxmlformats.org/drawingml/2006/diagram).
 *
 * This is a complex type.
 */
public interface CTShape extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTShape.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctshapebd1atype");
    
    /**
     * Gets the "adjLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTAdjLst getAdjLst();
    
    /**
     * True if has "adjLst" element
     */
    boolean isSetAdjLst();
    
    /**
     * Sets the "adjLst" element
     */
    void setAdjLst(org.openxmlformats.schemas.drawingml.x2006.diagram.CTAdjLst adjLst);
    
    /**
     * Appends and returns a new empty "adjLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTAdjLst addNewAdjLst();
    
    /**
     * Unsets the "adjLst" element
     */
    void unsetAdjLst();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * Gets the "rot" attribute
     */
    double getRot();
    
    /**
     * Gets (as xml) the "rot" attribute
     */
    org.apache.xmlbeans.XmlDouble xgetRot();
    
    /**
     * True if has "rot" attribute
     */
    boolean isSetRot();
    
    /**
     * Sets the "rot" attribute
     */
    void setRot(double rot);
    
    /**
     * Sets (as xml) the "rot" attribute
     */
    void xsetRot(org.apache.xmlbeans.XmlDouble rot);
    
    /**
     * Unsets the "rot" attribute
     */
    void unsetRot();
    
    /**
     * Gets the "type" attribute
     */
    java.lang.String getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.STLayoutShapeType xgetType();
    
    /**
     * True if has "type" attribute
     */
    boolean isSetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(java.lang.String type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(org.openxmlformats.schemas.drawingml.x2006.diagram.STLayoutShapeType type);
    
    /**
     * Unsets the "type" attribute
     */
    void unsetType();
    
    /**
     * Gets the "blip" attribute
     */
    java.lang.String getBlip();
    
    /**
     * Gets (as xml) the "blip" attribute
     */
    org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId xgetBlip();
    
    /**
     * True if has "blip" attribute
     */
    boolean isSetBlip();
    
    /**
     * Sets the "blip" attribute
     */
    void setBlip(java.lang.String blip);
    
    /**
     * Sets (as xml) the "blip" attribute
     */
    void xsetBlip(org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId blip);
    
    /**
     * Unsets the "blip" attribute
     */
    void unsetBlip();
    
    /**
     * Gets the "zOrderOff" attribute
     */
    int getZOrderOff();
    
    /**
     * Gets (as xml) the "zOrderOff" attribute
     */
    org.apache.xmlbeans.XmlInt xgetZOrderOff();
    
    /**
     * True if has "zOrderOff" attribute
     */
    boolean isSetZOrderOff();
    
    /**
     * Sets the "zOrderOff" attribute
     */
    void setZOrderOff(int zOrderOff);
    
    /**
     * Sets (as xml) the "zOrderOff" attribute
     */
    void xsetZOrderOff(org.apache.xmlbeans.XmlInt zOrderOff);
    
    /**
     * Unsets the "zOrderOff" attribute
     */
    void unsetZOrderOff();
    
    /**
     * Gets the "hideGeom" attribute
     */
    boolean getHideGeom();
    
    /**
     * Gets (as xml) the "hideGeom" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetHideGeom();
    
    /**
     * True if has "hideGeom" attribute
     */
    boolean isSetHideGeom();
    
    /**
     * Sets the "hideGeom" attribute
     */
    void setHideGeom(boolean hideGeom);
    
    /**
     * Sets (as xml) the "hideGeom" attribute
     */
    void xsetHideGeom(org.apache.xmlbeans.XmlBoolean hideGeom);
    
    /**
     * Unsets the "hideGeom" attribute
     */
    void unsetHideGeom();
    
    /**
     * Gets the "lkTxEntry" attribute
     */
    boolean getLkTxEntry();
    
    /**
     * Gets (as xml) the "lkTxEntry" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetLkTxEntry();
    
    /**
     * True if has "lkTxEntry" attribute
     */
    boolean isSetLkTxEntry();
    
    /**
     * Sets the "lkTxEntry" attribute
     */
    void setLkTxEntry(boolean lkTxEntry);
    
    /**
     * Sets (as xml) the "lkTxEntry" attribute
     */
    void xsetLkTxEntry(org.apache.xmlbeans.XmlBoolean lkTxEntry);
    
    /**
     * Unsets the "lkTxEntry" attribute
     */
    void unsetLkTxEntry();
    
    /**
     * Gets the "blipPhldr" attribute
     */
    boolean getBlipPhldr();
    
    /**
     * Gets (as xml) the "blipPhldr" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetBlipPhldr();
    
    /**
     * True if has "blipPhldr" attribute
     */
    boolean isSetBlipPhldr();
    
    /**
     * Sets the "blipPhldr" attribute
     */
    void setBlipPhldr(boolean blipPhldr);
    
    /**
     * Sets (as xml) the "blipPhldr" attribute
     */
    void xsetBlipPhldr(org.apache.xmlbeans.XmlBoolean blipPhldr);
    
    /**
     * Unsets the "blipPhldr" attribute
     */
    void unsetBlipPhldr();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTShape) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
