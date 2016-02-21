/*
 * XML Type:  CT_Relation
 * Namespace: urn:schemas-microsoft-com:office:office
 * Java type: schemasMicrosoftComOfficeOffice.CTRelation
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeOffice;


/**
 * An XML CT_Relation(@urn:schemas-microsoft-com:office:office).
 *
 * This is a complex type.
 */
public interface CTRelation extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTRelation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctrelation60dftype");
    
    /**
     * Gets the "ext" attribute
     */
    schemasMicrosoftComVml.STExt.Enum getExt();
    
    /**
     * Gets (as xml) the "ext" attribute
     */
    schemasMicrosoftComVml.STExt xgetExt();
    
    /**
     * True if has "ext" attribute
     */
    boolean isSetExt();
    
    /**
     * Sets the "ext" attribute
     */
    void setExt(schemasMicrosoftComVml.STExt.Enum ext);
    
    /**
     * Sets (as xml) the "ext" attribute
     */
    void xsetExt(schemasMicrosoftComVml.STExt ext);
    
    /**
     * Unsets the "ext" attribute
     */
    void unsetExt();
    
    /**
     * Gets the "idsrc" attribute
     */
    java.lang.String getIdsrc();
    
    /**
     * Gets (as xml) the "idsrc" attribute
     */
    org.apache.xmlbeans.XmlString xgetIdsrc();
    
    /**
     * True if has "idsrc" attribute
     */
    boolean isSetIdsrc();
    
    /**
     * Sets the "idsrc" attribute
     */
    void setIdsrc(java.lang.String idsrc);
    
    /**
     * Sets (as xml) the "idsrc" attribute
     */
    void xsetIdsrc(org.apache.xmlbeans.XmlString idsrc);
    
    /**
     * Unsets the "idsrc" attribute
     */
    void unsetIdsrc();
    
    /**
     * Gets the "iddest" attribute
     */
    java.lang.String getIddest();
    
    /**
     * Gets (as xml) the "iddest" attribute
     */
    org.apache.xmlbeans.XmlString xgetIddest();
    
    /**
     * True if has "iddest" attribute
     */
    boolean isSetIddest();
    
    /**
     * Sets the "iddest" attribute
     */
    void setIddest(java.lang.String iddest);
    
    /**
     * Sets (as xml) the "iddest" attribute
     */
    void xsetIddest(org.apache.xmlbeans.XmlString iddest);
    
    /**
     * Unsets the "iddest" attribute
     */
    void unsetIddest();
    
    /**
     * Gets the "idcntr" attribute
     */
    java.lang.String getIdcntr();
    
    /**
     * Gets (as xml) the "idcntr" attribute
     */
    org.apache.xmlbeans.XmlString xgetIdcntr();
    
    /**
     * True if has "idcntr" attribute
     */
    boolean isSetIdcntr();
    
    /**
     * Sets the "idcntr" attribute
     */
    void setIdcntr(java.lang.String idcntr);
    
    /**
     * Sets (as xml) the "idcntr" attribute
     */
    void xsetIdcntr(org.apache.xmlbeans.XmlString idcntr);
    
    /**
     * Unsets the "idcntr" attribute
     */
    void unsetIdcntr();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static schemasMicrosoftComOfficeOffice.CTRelation newInstance() {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.CTRelation parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.CTRelation) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
