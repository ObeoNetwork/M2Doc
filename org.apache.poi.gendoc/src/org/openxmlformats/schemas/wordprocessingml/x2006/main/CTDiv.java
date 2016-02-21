/*
 * XML Type:  CT_Div
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_Div(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTDiv extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTDiv.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctdiv7c21type");
    
    /**
     * Gets the "blockQuote" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff getBlockQuote();
    
    /**
     * True if has "blockQuote" element
     */
    boolean isSetBlockQuote();
    
    /**
     * Sets the "blockQuote" element
     */
    void setBlockQuote(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff blockQuote);
    
    /**
     * Appends and returns a new empty "blockQuote" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff addNewBlockQuote();
    
    /**
     * Unsets the "blockQuote" element
     */
    void unsetBlockQuote();
    
    /**
     * Gets the "bodyDiv" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff getBodyDiv();
    
    /**
     * True if has "bodyDiv" element
     */
    boolean isSetBodyDiv();
    
    /**
     * Sets the "bodyDiv" element
     */
    void setBodyDiv(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff bodyDiv);
    
    /**
     * Appends and returns a new empty "bodyDiv" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff addNewBodyDiv();
    
    /**
     * Unsets the "bodyDiv" element
     */
    void unsetBodyDiv();
    
    /**
     * Gets the "marLeft" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure getMarLeft();
    
    /**
     * Sets the "marLeft" element
     */
    void setMarLeft(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure marLeft);
    
    /**
     * Appends and returns a new empty "marLeft" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure addNewMarLeft();
    
    /**
     * Gets the "marRight" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure getMarRight();
    
    /**
     * Sets the "marRight" element
     */
    void setMarRight(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure marRight);
    
    /**
     * Appends and returns a new empty "marRight" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure addNewMarRight();
    
    /**
     * Gets the "marTop" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure getMarTop();
    
    /**
     * Sets the "marTop" element
     */
    void setMarTop(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure marTop);
    
    /**
     * Appends and returns a new empty "marTop" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure addNewMarTop();
    
    /**
     * Gets the "marBottom" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure getMarBottom();
    
    /**
     * Sets the "marBottom" element
     */
    void setMarBottom(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure marBottom);
    
    /**
     * Appends and returns a new empty "marBottom" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure addNewMarBottom();
    
    /**
     * Gets the "divBdr" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivBdr getDivBdr();
    
    /**
     * True if has "divBdr" element
     */
    boolean isSetDivBdr();
    
    /**
     * Sets the "divBdr" element
     */
    void setDivBdr(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivBdr divBdr);
    
    /**
     * Appends and returns a new empty "divBdr" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivBdr addNewDivBdr();
    
    /**
     * Unsets the "divBdr" element
     */
    void unsetDivBdr();
    
    /**
     * Gets a List of "divsChild" elements
     */
    java.util.List<org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivs> getDivsChildList();
    
    /**
     * Gets array of all "divsChild" elements
     * @deprecated
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivs[] getDivsChildArray();
    
    /**
     * Gets ith "divsChild" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivs getDivsChildArray(int i);
    
    /**
     * Returns number of "divsChild" element
     */
    int sizeOfDivsChildArray();
    
    /**
     * Sets array of all "divsChild" element
     */
    void setDivsChildArray(org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivs[] divsChildArray);
    
    /**
     * Sets ith "divsChild" element
     */
    void setDivsChildArray(int i, org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivs divsChild);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "divsChild" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivs insertNewDivsChild(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "divsChild" element
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDivs addNewDivsChild();
    
    /**
     * Removes the ith "divsChild" element
     */
    void removeDivsChild(int i);
    
    /**
     * Gets the "id" attribute
     */
    java.math.BigInteger getId();
    
    /**
     * Gets (as xml) the "id" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber xgetId();
    
    /**
     * Sets the "id" attribute
     */
    void setId(java.math.BigInteger id);
    
    /**
     * Sets (as xml) the "id" attribute
     */
    void xsetId(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber id);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDiv) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
