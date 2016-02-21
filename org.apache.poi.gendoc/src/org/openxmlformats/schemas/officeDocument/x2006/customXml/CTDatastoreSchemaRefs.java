/*
 * XML Type:  CT_DatastoreSchemaRefs
 * Namespace: http://schemas.openxmlformats.org/officeDocument/2006/customXml
 * Java type: org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.officeDocument.x2006.customXml;


/**
 * An XML CT_DatastoreSchemaRefs(@http://schemas.openxmlformats.org/officeDocument/2006/customXml).
 *
 * This is a complex type.
 */
public interface CTDatastoreSchemaRefs extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDatastoreSchemaRefs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctdatastoreschemarefsefeatype");
    
    /**
     * Gets a List of "schemaRef" elements
     */
    java.util.List<org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRef> getSchemaRefList();
    
    /**
     * Gets array of all "schemaRef" elements
     * @deprecated
     */
    org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRef[] getSchemaRefArray();
    
    /**
     * Gets ith "schemaRef" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRef getSchemaRefArray(int i);
    
    /**
     * Returns number of "schemaRef" element
     */
    int sizeOfSchemaRefArray();
    
    /**
     * Sets array of all "schemaRef" element
     */
    void setSchemaRefArray(org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRef[] schemaRefArray);
    
    /**
     * Sets ith "schemaRef" element
     */
    void setSchemaRefArray(int i, org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRef schemaRef);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "schemaRef" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRef insertNewSchemaRef(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "schemaRef" element
     */
    org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRef addNewSchemaRef();
    
    /**
     * Removes the ith "schemaRef" element
     */
    void removeSchemaRef(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs newInstance() {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.officeDocument.x2006.customXml.CTDatastoreSchemaRefs) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
