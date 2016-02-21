/*
 * XML Type:  CT_VolTopic
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_VolTopic(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTVolTopic extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTVolTopic.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctvoltopic23e5type");
    
    /**
     * Gets the "v" element
     */
    java.lang.String getV();
    
    /**
     * Gets (as xml) the "v" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetV();
    
    /**
     * Sets the "v" element
     */
    void setV(java.lang.String v);
    
    /**
     * Sets (as xml) the "v" element
     */
    void xsetV(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring v);
    
    /**
     * Gets a List of "stp" elements
     */
    java.util.List<java.lang.String> getStpList();
    
    /**
     * Gets array of all "stp" elements
     * @deprecated
     */
    java.lang.String[] getStpArray();
    
    /**
     * Gets ith "stp" element
     */
    java.lang.String getStpArray(int i);
    
    /**
     * Gets (as xml) a List of "stp" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring> xgetStpList();
    
    /**
     * Gets (as xml) array of all "stp" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring[] xgetStpArray();
    
    /**
     * Gets (as xml) ith "stp" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetStpArray(int i);
    
    /**
     * Returns number of "stp" element
     */
    int sizeOfStpArray();
    
    /**
     * Sets array of all "stp" element
     */
    void setStpArray(java.lang.String[] stpArray);
    
    /**
     * Sets ith "stp" element
     */
    void setStpArray(int i, java.lang.String stp);
    
    /**
     * Sets (as xml) array of all "stp" element
     */
    void xsetStpArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring[] stpArray);
    
    /**
     * Sets (as xml) ith "stp" element
     */
    void xsetStpArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring stp);
    
    /**
     * Inserts the value as the ith "stp" element
     */
    void insertStp(int i, java.lang.String stp);
    
    /**
     * Appends the value as the last "stp" element
     */
    void addStp(java.lang.String stp);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "stp" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring insertNewStp(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "stp" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring addNewStp();
    
    /**
     * Removes the ith "stp" element
     */
    void removeStp(int i);
    
    /**
     * Gets a List of "tr" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopicRef> getTrList();
    
    /**
     * Gets array of all "tr" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopicRef[] getTrArray();
    
    /**
     * Gets ith "tr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopicRef getTrArray(int i);
    
    /**
     * Returns number of "tr" element
     */
    int sizeOfTrArray();
    
    /**
     * Sets array of all "tr" element
     */
    void setTrArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopicRef[] trArray);
    
    /**
     * Sets ith "tr" element
     */
    void setTrArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopicRef tr);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "tr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopicRef insertNewTr(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "tr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopicRef addNewTr();
    
    /**
     * Removes the ith "tr" element
     */
    void removeTr(int i);
    
    /**
     * Gets the "t" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STVolValueType.Enum getT();
    
    /**
     * Gets (as xml) the "t" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STVolValueType xgetT();
    
    /**
     * True if has "t" attribute
     */
    boolean isSetT();
    
    /**
     * Sets the "t" attribute
     */
    void setT(org.openxmlformats.schemas.spreadsheetml.x2006.main.STVolValueType.Enum t);
    
    /**
     * Sets (as xml) the "t" attribute
     */
    void xsetT(org.openxmlformats.schemas.spreadsheetml.x2006.main.STVolValueType t);
    
    /**
     * Unsets the "t" attribute
     */
    void unsetT();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVolTopic) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
