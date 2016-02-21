/*
 * XML Type:  CT_StyleDefinition
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/diagram
 * Java type: org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.diagram;


/**
 * An XML CT_StyleDefinition(@http://schemas.openxmlformats.org/drawingml/2006/diagram).
 *
 * This is a complex type.
 */
public interface CTStyleDefinition extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTStyleDefinition.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctstyledefinitiond9f7type");
    
    /**
     * Gets a List of "title" elements
     */
    java.util.List<org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDName> getTitleList();
    
    /**
     * Gets array of all "title" elements
     * @deprecated
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDName[] getTitleArray();
    
    /**
     * Gets ith "title" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDName getTitleArray(int i);
    
    /**
     * Returns number of "title" element
     */
    int sizeOfTitleArray();
    
    /**
     * Sets array of all "title" element
     */
    void setTitleArray(org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDName[] titleArray);
    
    /**
     * Sets ith "title" element
     */
    void setTitleArray(int i, org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDName title);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "title" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDName insertNewTitle(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "title" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDName addNewTitle();
    
    /**
     * Removes the ith "title" element
     */
    void removeTitle(int i);
    
    /**
     * Gets a List of "desc" elements
     */
    java.util.List<org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDDescription> getDescList();
    
    /**
     * Gets array of all "desc" elements
     * @deprecated
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDDescription[] getDescArray();
    
    /**
     * Gets ith "desc" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDDescription getDescArray(int i);
    
    /**
     * Returns number of "desc" element
     */
    int sizeOfDescArray();
    
    /**
     * Sets array of all "desc" element
     */
    void setDescArray(org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDDescription[] descArray);
    
    /**
     * Sets ith "desc" element
     */
    void setDescArray(int i, org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDDescription desc);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "desc" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDDescription insertNewDesc(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "desc" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDDescription addNewDesc();
    
    /**
     * Removes the ith "desc" element
     */
    void removeDesc(int i);
    
    /**
     * Gets the "catLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDCategories getCatLst();
    
    /**
     * True if has "catLst" element
     */
    boolean isSetCatLst();
    
    /**
     * Sets the "catLst" element
     */
    void setCatLst(org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDCategories catLst);
    
    /**
     * Appends and returns a new empty "catLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTSDCategories addNewCatLst();
    
    /**
     * Unsets the "catLst" element
     */
    void unsetCatLst();
    
    /**
     * Gets the "scene3d" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D getScene3D();
    
    /**
     * True if has "scene3d" element
     */
    boolean isSetScene3D();
    
    /**
     * Sets the "scene3d" element
     */
    void setScene3D(org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D scene3D);
    
    /**
     * Appends and returns a new empty "scene3d" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D addNewScene3D();
    
    /**
     * Unsets the "scene3d" element
     */
    void unsetScene3D();
    
    /**
     * Gets a List of "styleLbl" elements
     */
    java.util.List<org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel> getStyleLblList();
    
    /**
     * Gets array of all "styleLbl" elements
     * @deprecated
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel[] getStyleLblArray();
    
    /**
     * Gets ith "styleLbl" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel getStyleLblArray(int i);
    
    /**
     * Returns number of "styleLbl" element
     */
    int sizeOfStyleLblArray();
    
    /**
     * Sets array of all "styleLbl" element
     */
    void setStyleLblArray(org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel[] styleLblArray);
    
    /**
     * Sets ith "styleLbl" element
     */
    void setStyleLblArray(int i, org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel styleLbl);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "styleLbl" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel insertNewStyleLbl(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "styleLbl" element
     */
    org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleLabel addNewStyleLbl();
    
    /**
     * Removes the ith "styleLbl" element
     */
    void removeStyleLbl(int i);
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * Gets the "uniqueId" attribute
     */
    java.lang.String getUniqueId();
    
    /**
     * Gets (as xml) the "uniqueId" attribute
     */
    org.apache.xmlbeans.XmlString xgetUniqueId();
    
    /**
     * True if has "uniqueId" attribute
     */
    boolean isSetUniqueId();
    
    /**
     * Sets the "uniqueId" attribute
     */
    void setUniqueId(java.lang.String uniqueId);
    
    /**
     * Sets (as xml) the "uniqueId" attribute
     */
    void xsetUniqueId(org.apache.xmlbeans.XmlString uniqueId);
    
    /**
     * Unsets the "uniqueId" attribute
     */
    void unsetUniqueId();
    
    /**
     * Gets the "minVer" attribute
     */
    java.lang.String getMinVer();
    
    /**
     * Gets (as xml) the "minVer" attribute
     */
    org.apache.xmlbeans.XmlString xgetMinVer();
    
    /**
     * True if has "minVer" attribute
     */
    boolean isSetMinVer();
    
    /**
     * Sets the "minVer" attribute
     */
    void setMinVer(java.lang.String minVer);
    
    /**
     * Sets (as xml) the "minVer" attribute
     */
    void xsetMinVer(org.apache.xmlbeans.XmlString minVer);
    
    /**
     * Unsets the "minVer" attribute
     */
    void unsetMinVer();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.diagram.CTStyleDefinition) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
