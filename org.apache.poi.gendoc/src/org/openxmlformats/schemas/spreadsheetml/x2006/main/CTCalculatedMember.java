/*
 * XML Type:  CT_CalculatedMember
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_CalculatedMember(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTCalculatedMember extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTCalculatedMember.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctcalculatedmember9dc1type");
    
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
     * Gets the "name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring name);
    
    /**
     * Gets the "mdx" attribute
     */
    java.lang.String getMdx();
    
    /**
     * Gets (as xml) the "mdx" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetMdx();
    
    /**
     * Sets the "mdx" attribute
     */
    void setMdx(java.lang.String mdx);
    
    /**
     * Sets (as xml) the "mdx" attribute
     */
    void xsetMdx(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring mdx);
    
    /**
     * Gets the "memberName" attribute
     */
    java.lang.String getMemberName();
    
    /**
     * Gets (as xml) the "memberName" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetMemberName();
    
    /**
     * True if has "memberName" attribute
     */
    boolean isSetMemberName();
    
    /**
     * Sets the "memberName" attribute
     */
    void setMemberName(java.lang.String memberName);
    
    /**
     * Sets (as xml) the "memberName" attribute
     */
    void xsetMemberName(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring memberName);
    
    /**
     * Unsets the "memberName" attribute
     */
    void unsetMemberName();
    
    /**
     * Gets the "hierarchy" attribute
     */
    java.lang.String getHierarchy();
    
    /**
     * Gets (as xml) the "hierarchy" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetHierarchy();
    
    /**
     * True if has "hierarchy" attribute
     */
    boolean isSetHierarchy();
    
    /**
     * Sets the "hierarchy" attribute
     */
    void setHierarchy(java.lang.String hierarchy);
    
    /**
     * Sets (as xml) the "hierarchy" attribute
     */
    void xsetHierarchy(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring hierarchy);
    
    /**
     * Unsets the "hierarchy" attribute
     */
    void unsetHierarchy();
    
    /**
     * Gets the "parent" attribute
     */
    java.lang.String getParent();
    
    /**
     * Gets (as xml) the "parent" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring xgetParent();
    
    /**
     * True if has "parent" attribute
     */
    boolean isSetParent();
    
    /**
     * Sets the "parent" attribute
     */
    void setParent(java.lang.String parent);
    
    /**
     * Sets (as xml) the "parent" attribute
     */
    void xsetParent(org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring parent);
    
    /**
     * Unsets the "parent" attribute
     */
    void unsetParent();
    
    /**
     * Gets the "solveOrder" attribute
     */
    int getSolveOrder();
    
    /**
     * Gets (as xml) the "solveOrder" attribute
     */
    org.apache.xmlbeans.XmlInt xgetSolveOrder();
    
    /**
     * True if has "solveOrder" attribute
     */
    boolean isSetSolveOrder();
    
    /**
     * Sets the "solveOrder" attribute
     */
    void setSolveOrder(int solveOrder);
    
    /**
     * Sets (as xml) the "solveOrder" attribute
     */
    void xsetSolveOrder(org.apache.xmlbeans.XmlInt solveOrder);
    
    /**
     * Unsets the "solveOrder" attribute
     */
    void unsetSolveOrder();
    
    /**
     * Gets the "set" attribute
     */
    boolean getSet();
    
    /**
     * Gets (as xml) the "set" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetSet();
    
    /**
     * True if has "set" attribute
     */
    boolean isSetSet();
    
    /**
     * Sets the "set" attribute
     */
    void setSet(boolean set);
    
    /**
     * Sets (as xml) the "set" attribute
     */
    void xsetSet(org.apache.xmlbeans.XmlBoolean set);
    
    /**
     * Unsets the "set" attribute
     */
    void unsetSet();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalculatedMember) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
