/*
 * XML Type:  CT_OdsoFieldMapData
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_OdsoFieldMapData(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTOdsoFieldMapData extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTOdsoFieldMapData.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctodsofieldmapdata3fabtype");
    
    /**
     * Gets the "type" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMailMergeOdsoFMDFieldType getType();
    
    /**
     * True if has "type" element
     */
    boolean isSetType();
    
    /**
     * Sets the "type" element
     */
    void setType(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMailMergeOdsoFMDFieldType type);
    
    /**
     * Appends and returns a new empty "type" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMailMergeOdsoFMDFieldType addNewType();
    
    /**
     * Unsets the "type" element
     */
    void unsetType();
    
    /**
     * Gets the "name" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString getName();
    
    /**
     * True if has "name" element
     */
    boolean isSetName();
    
    /**
     * Sets the "name" element
     */
    void setName(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString name);
    
    /**
     * Appends and returns a new empty "name" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString addNewName();
    
    /**
     * Unsets the "name" element
     */
    void unsetName();
    
    /**
     * Gets the "mappedName" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString getMappedName();
    
    /**
     * True if has "mappedName" element
     */
    boolean isSetMappedName();
    
    /**
     * Sets the "mappedName" element
     */
    void setMappedName(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString mappedName);
    
    /**
     * Appends and returns a new empty "mappedName" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString addNewMappedName();
    
    /**
     * Unsets the "mappedName" element
     */
    void unsetMappedName();
    
    /**
     * Gets the "column" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber getColumn();
    
    /**
     * True if has "column" element
     */
    boolean isSetColumn();
    
    /**
     * Sets the "column" element
     */
    void setColumn(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber column);
    
    /**
     * Appends and returns a new empty "column" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber addNewColumn();
    
    /**
     * Unsets the "column" element
     */
    void unsetColumn();
    
    /**
     * Gets the "lid" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang getLid();
    
    /**
     * True if has "lid" element
     */
    boolean isSetLid();
    
    /**
     * Sets the "lid" element
     */
    void setLid(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang lid);
    
    /**
     * Appends and returns a new empty "lid" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang addNewLid();
    
    /**
     * Unsets the "lid" element
     */
    void unsetLid();
    
    /**
     * Gets the "dynamicAddress" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff getDynamicAddress();
    
    /**
     * True if has "dynamicAddress" element
     */
    boolean isSetDynamicAddress();
    
    /**
     * Sets the "dynamicAddress" element
     */
    void setDynamicAddress(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff dynamicAddress);
    
    /**
     * Appends and returns a new empty "dynamicAddress" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff addNewDynamicAddress();
    
    /**
     * Unsets the "dynamicAddress" element
     */
    void unsetDynamicAddress();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOdsoFieldMapData) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
