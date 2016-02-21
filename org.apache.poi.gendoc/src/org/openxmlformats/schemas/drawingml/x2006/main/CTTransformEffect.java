/*
 * XML Type:  CT_TransformEffect
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/main
 * Java type: org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.main;


/**
 * An XML CT_TransformEffect(@http://schemas.openxmlformats.org/drawingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTransformEffect extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTransformEffect.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttransformeffect948etype");
    
    /**
     * Gets the "sx" attribute
     */
    int getSx();
    
    /**
     * Gets (as xml) the "sx" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STPercentage xgetSx();
    
    /**
     * True if has "sx" attribute
     */
    boolean isSetSx();
    
    /**
     * Sets the "sx" attribute
     */
    void setSx(int sx);
    
    /**
     * Sets (as xml) the "sx" attribute
     */
    void xsetSx(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage sx);
    
    /**
     * Unsets the "sx" attribute
     */
    void unsetSx();
    
    /**
     * Gets the "sy" attribute
     */
    int getSy();
    
    /**
     * Gets (as xml) the "sy" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STPercentage xgetSy();
    
    /**
     * True if has "sy" attribute
     */
    boolean isSetSy();
    
    /**
     * Sets the "sy" attribute
     */
    void setSy(int sy);
    
    /**
     * Sets (as xml) the "sy" attribute
     */
    void xsetSy(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage sy);
    
    /**
     * Unsets the "sy" attribute
     */
    void unsetSy();
    
    /**
     * Gets the "kx" attribute
     */
    int getKx();
    
    /**
     * Gets (as xml) the "kx" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle xgetKx();
    
    /**
     * True if has "kx" attribute
     */
    boolean isSetKx();
    
    /**
     * Sets the "kx" attribute
     */
    void setKx(int kx);
    
    /**
     * Sets (as xml) the "kx" attribute
     */
    void xsetKx(org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle kx);
    
    /**
     * Unsets the "kx" attribute
     */
    void unsetKx();
    
    /**
     * Gets the "ky" attribute
     */
    int getKy();
    
    /**
     * Gets (as xml) the "ky" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle xgetKy();
    
    /**
     * True if has "ky" attribute
     */
    boolean isSetKy();
    
    /**
     * Sets the "ky" attribute
     */
    void setKy(int ky);
    
    /**
     * Sets (as xml) the "ky" attribute
     */
    void xsetKy(org.openxmlformats.schemas.drawingml.x2006.main.STFixedAngle ky);
    
    /**
     * Unsets the "ky" attribute
     */
    void unsetKy();
    
    /**
     * Gets the "tx" attribute
     */
    long getTx();
    
    /**
     * Gets (as xml) the "tx" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate xgetTx();
    
    /**
     * True if has "tx" attribute
     */
    boolean isSetTx();
    
    /**
     * Sets the "tx" attribute
     */
    void setTx(long tx);
    
    /**
     * Sets (as xml) the "tx" attribute
     */
    void xsetTx(org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate tx);
    
    /**
     * Unsets the "tx" attribute
     */
    void unsetTx();
    
    /**
     * Gets the "ty" attribute
     */
    long getTy();
    
    /**
     * Gets (as xml) the "ty" attribute
     */
    org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate xgetTy();
    
    /**
     * True if has "ty" attribute
     */
    boolean isSetTy();
    
    /**
     * Sets the "ty" attribute
     */
    void setTy(long ty);
    
    /**
     * Sets (as xml) the "ty" attribute
     */
    void xsetTy(org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate ty);
    
    /**
     * Unsets the "ty" attribute
     */
    void unsetTy();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
