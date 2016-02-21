/*
 * XML Type:  CT_Metadata
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Metadata(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTMetadata extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTMetadata.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctmetadata7138type");
    
    /**
     * Gets the "metadataTypes" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataTypes getMetadataTypes();
    
    /**
     * True if has "metadataTypes" element
     */
    boolean isSetMetadataTypes();
    
    /**
     * Sets the "metadataTypes" element
     */
    void setMetadataTypes(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataTypes metadataTypes);
    
    /**
     * Appends and returns a new empty "metadataTypes" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataTypes addNewMetadataTypes();
    
    /**
     * Unsets the "metadataTypes" element
     */
    void unsetMetadataTypes();
    
    /**
     * Gets the "metadataStrings" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataStrings getMetadataStrings();
    
    /**
     * True if has "metadataStrings" element
     */
    boolean isSetMetadataStrings();
    
    /**
     * Sets the "metadataStrings" element
     */
    void setMetadataStrings(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataStrings metadataStrings);
    
    /**
     * Appends and returns a new empty "metadataStrings" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataStrings addNewMetadataStrings();
    
    /**
     * Unsets the "metadataStrings" element
     */
    void unsetMetadataStrings();
    
    /**
     * Gets the "mdxMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxMetadata getMdxMetadata();
    
    /**
     * True if has "mdxMetadata" element
     */
    boolean isSetMdxMetadata();
    
    /**
     * Sets the "mdxMetadata" element
     */
    void setMdxMetadata(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxMetadata mdxMetadata);
    
    /**
     * Appends and returns a new empty "mdxMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxMetadata addNewMdxMetadata();
    
    /**
     * Unsets the "mdxMetadata" element
     */
    void unsetMdxMetadata();
    
    /**
     * Gets a List of "futureMetadata" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFutureMetadata> getFutureMetadataList();
    
    /**
     * Gets array of all "futureMetadata" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFutureMetadata[] getFutureMetadataArray();
    
    /**
     * Gets ith "futureMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFutureMetadata getFutureMetadataArray(int i);
    
    /**
     * Returns number of "futureMetadata" element
     */
    int sizeOfFutureMetadataArray();
    
    /**
     * Sets array of all "futureMetadata" element
     */
    void setFutureMetadataArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFutureMetadata[] futureMetadataArray);
    
    /**
     * Sets ith "futureMetadata" element
     */
    void setFutureMetadataArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFutureMetadata futureMetadata);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "futureMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFutureMetadata insertNewFutureMetadata(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "futureMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFutureMetadata addNewFutureMetadata();
    
    /**
     * Removes the ith "futureMetadata" element
     */
    void removeFutureMetadata(int i);
    
    /**
     * Gets the "cellMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataBlocks getCellMetadata();
    
    /**
     * True if has "cellMetadata" element
     */
    boolean isSetCellMetadata();
    
    /**
     * Sets the "cellMetadata" element
     */
    void setCellMetadata(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataBlocks cellMetadata);
    
    /**
     * Appends and returns a new empty "cellMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataBlocks addNewCellMetadata();
    
    /**
     * Unsets the "cellMetadata" element
     */
    void unsetCellMetadata();
    
    /**
     * Gets the "valueMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataBlocks getValueMetadata();
    
    /**
     * True if has "valueMetadata" element
     */
    boolean isSetValueMetadata();
    
    /**
     * Sets the "valueMetadata" element
     */
    void setValueMetadata(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataBlocks valueMetadata);
    
    /**
     * Appends and returns a new empty "valueMetadata" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadataBlocks addNewValueMetadata();
    
    /**
     * Unsets the "valueMetadata" element
     */
    void unsetValueMetadata();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMetadata) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
