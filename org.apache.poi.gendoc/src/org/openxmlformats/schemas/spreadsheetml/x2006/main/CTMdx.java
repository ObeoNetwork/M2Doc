/*
 * XML Type:  CT_Mdx
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_Mdx(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTMdx extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTMdx.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctmdxca60type");
    
    /**
     * Gets the "t" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxTuple getT();
    
    /**
     * True if has "t" element
     */
    boolean isSetT();
    
    /**
     * Sets the "t" element
     */
    void setT(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxTuple t);
    
    /**
     * Appends and returns a new empty "t" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxTuple addNewT();
    
    /**
     * Unsets the "t" element
     */
    void unsetT();
    
    /**
     * Gets the "ms" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxSet getMs();
    
    /**
     * True if has "ms" element
     */
    boolean isSetMs();
    
    /**
     * Sets the "ms" element
     */
    void setMs(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxSet ms);
    
    /**
     * Appends and returns a new empty "ms" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxSet addNewMs();
    
    /**
     * Unsets the "ms" element
     */
    void unsetMs();
    
    /**
     * Gets the "p" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxMemeberProp getP();
    
    /**
     * True if has "p" element
     */
    boolean isSetP();
    
    /**
     * Sets the "p" element
     */
    void setP(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxMemeberProp p);
    
    /**
     * Appends and returns a new empty "p" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxMemeberProp addNewP();
    
    /**
     * Unsets the "p" element
     */
    void unsetP();
    
    /**
     * Gets the "k" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxKPI getK();
    
    /**
     * True if has "k" element
     */
    boolean isSetK();
    
    /**
     * Sets the "k" element
     */
    void setK(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxKPI k);
    
    /**
     * Appends and returns a new empty "k" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdxKPI addNewK();
    
    /**
     * Unsets the "k" element
     */
    void unsetK();
    
    /**
     * Gets the "n" attribute
     */
    long getN();
    
    /**
     * Gets (as xml) the "n" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetN();
    
    /**
     * Sets the "n" attribute
     */
    void setN(long n);
    
    /**
     * Sets (as xml) the "n" attribute
     */
    void xsetN(org.apache.xmlbeans.XmlUnsignedInt n);
    
    /**
     * Gets the "f" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STMdxFunctionType.Enum getF();
    
    /**
     * Gets (as xml) the "f" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STMdxFunctionType xgetF();
    
    /**
     * Sets the "f" attribute
     */
    void setF(org.openxmlformats.schemas.spreadsheetml.x2006.main.STMdxFunctionType.Enum f);
    
    /**
     * Sets (as xml) the "f" attribute
     */
    void xsetF(org.openxmlformats.schemas.spreadsheetml.x2006.main.STMdxFunctionType f);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMdx) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
