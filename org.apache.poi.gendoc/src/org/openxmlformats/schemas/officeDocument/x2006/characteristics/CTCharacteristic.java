/*
 * XML Type:  CT_Characteristic
 * Namespace: http://schemas.openxmlformats.org/officeDocument/2006/characteristics
 * Java type: org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.officeDocument.x2006.characteristics;


/**
 * An XML CT_Characteristic(@http://schemas.openxmlformats.org/officeDocument/2006/characteristics).
 *
 * This is a complex type.
 */
public interface CTCharacteristic extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTCharacteristic.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctcharacteristic2b69type");
    
    /**
     * Gets the "name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    org.apache.xmlbeans.XmlString xgetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(org.apache.xmlbeans.XmlString name);
    
    /**
     * Gets the "relation" attribute
     */
    org.openxmlformats.schemas.officeDocument.x2006.characteristics.STRelation.Enum getRelation();
    
    /**
     * Gets (as xml) the "relation" attribute
     */
    org.openxmlformats.schemas.officeDocument.x2006.characteristics.STRelation xgetRelation();
    
    /**
     * Sets the "relation" attribute
     */
    void setRelation(org.openxmlformats.schemas.officeDocument.x2006.characteristics.STRelation.Enum relation);
    
    /**
     * Sets (as xml) the "relation" attribute
     */
    void xsetRelation(org.openxmlformats.schemas.officeDocument.x2006.characteristics.STRelation relation);
    
    /**
     * Gets the "val" attribute
     */
    java.lang.String getVal();
    
    /**
     * Gets (as xml) the "val" attribute
     */
    org.apache.xmlbeans.XmlString xgetVal();
    
    /**
     * Sets the "val" attribute
     */
    void setVal(java.lang.String val);
    
    /**
     * Sets (as xml) the "val" attribute
     */
    void xsetVal(org.apache.xmlbeans.XmlString val);
    
    /**
     * Gets the "vocabulary" attribute
     */
    java.lang.String getVocabulary();
    
    /**
     * Gets (as xml) the "vocabulary" attribute
     */
    org.apache.xmlbeans.XmlAnyURI xgetVocabulary();
    
    /**
     * True if has "vocabulary" attribute
     */
    boolean isSetVocabulary();
    
    /**
     * Sets the "vocabulary" attribute
     */
    void setVocabulary(java.lang.String vocabulary);
    
    /**
     * Sets (as xml) the "vocabulary" attribute
     */
    void xsetVocabulary(org.apache.xmlbeans.XmlAnyURI vocabulary);
    
    /**
     * Unsets the "vocabulary" attribute
     */
    void unsetVocabulary();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic newInstance() {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.characteristics.CTCharacteristic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
