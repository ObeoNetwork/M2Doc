/*
 * XML Type:  CT_Rst
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Rst(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTRst extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTRst.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctrsta472type");
    
    /**
     * Gets the "t" element
     */
    java.lang.String getT();
    
    /**
     * Gets (as xml) the "t" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetT();
    
    /**
     * True if has "t" element
     */
    boolean isSetT();
    
    /**
     * Sets the "t" element
     */
    void setT(java.lang.String t);
    
    /**
     * Sets (as xml) the "t" element
     */
    void xsetT(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring t);
    
    /**
     * Unsets the "t" element
     */
    void unsetT();
    
    /**
     * Gets a List of "r" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt> getRList();
    
    /**
     * Gets array of all "r" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt[] getRArray();
    
    /**
     * Gets ith "r" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt getRArray(int i);
    
    /**
     * Returns number of "r" element
     */
    int sizeOfRArray();
    
    /**
     * Sets array of all "r" element
     */
    void setRArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt[] rArray);
    
    /**
     * Sets ith "r" element
     */
    void setRArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt r);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "r" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt insertNewR(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "r" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt addNewR();
    
    /**
     * Removes the ith "r" element
     */
    void removeR(int i);
    
    /**
     * Gets a List of "rPh" elements
     */
    java.util.List<org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun> getRPhList();
    
    /**
     * Gets array of all "rPh" elements
     * @deprecated
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun[] getRPhArray();
    
    /**
     * Gets ith "rPh" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun getRPhArray(int i);
    
    /**
     * Returns number of "rPh" element
     */
    int sizeOfRPhArray();
    
    /**
     * Sets array of all "rPh" element
     */
    void setRPhArray(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun[] rPhArray);
    
    /**
     * Sets ith "rPh" element
     */
    void setRPhArray(int i, org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun rPh);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "rPh" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun insertNewRPh(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "rPh" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun addNewRPh();
    
    /**
     * Removes the ith "rPh" element
     */
    void removeRPh(int i);
    
    /**
     * Gets the "phoneticPr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr getPhoneticPr();
    
    /**
     * True if has "phoneticPr" element
     */
    boolean isSetPhoneticPr();
    
    /**
     * Sets the "phoneticPr" element
     */
    void setPhoneticPr(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr phoneticPr);
    
    /**
     * Appends and returns a new empty "phoneticPr" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr addNewPhoneticPr();
    
    /**
     * Unsets the "phoneticPr" element
     */
    void unsetPhoneticPr();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
