/*
 * XML Type:  CT_RadarSer
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chart
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;


/**
 * An XML CT_RadarSer(@http://schemas.openxmlformats.org/drawingml/2006/chart).
 *
 * This is a complex type.
 */
public interface CTRadarSer extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTRadarSer.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctradarser3482type");
    
    /**
     * Gets the "idx" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt getIdx();
    
    /**
     * Sets the "idx" element
     */
    void setIdx(org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt idx);
    
    /**
     * Appends and returns a new empty "idx" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt addNewIdx();
    
    /**
     * Gets the "order" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt getOrder();
    
    /**
     * Sets the "order" element
     */
    void setOrder(org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt order);
    
    /**
     * Appends and returns a new empty "order" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt addNewOrder();
    
    /**
     * Gets the "tx" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx getTx();
    
    /**
     * True if has "tx" element
     */
    boolean isSetTx();
    
    /**
     * Sets the "tx" element
     */
    void setTx(org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx tx);
    
    /**
     * Appends and returns a new empty "tx" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx addNewTx();
    
    /**
     * Unsets the "tx" element
     */
    void unsetTx();
    
    /**
     * Gets the "spPr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties getSpPr();
    
    /**
     * True if has "spPr" element
     */
    boolean isSetSpPr();
    
    /**
     * Sets the "spPr" element
     */
    void setSpPr(org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties spPr);
    
    /**
     * Appends and returns a new empty "spPr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties addNewSpPr();
    
    /**
     * Unsets the "spPr" element
     */
    void unsetSpPr();
    
    /**
     * Gets the "marker" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker getMarker();
    
    /**
     * True if has "marker" element
     */
    boolean isSetMarker();
    
    /**
     * Sets the "marker" element
     */
    void setMarker(org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker marker);
    
    /**
     * Appends and returns a new empty "marker" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker addNewMarker();
    
    /**
     * Unsets the "marker" element
     */
    void unsetMarker();
    
    /**
     * Gets a List of "dPt" elements
     */
    java.util.List<org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt> getDPtList();
    
    /**
     * Gets array of all "dPt" elements
     * @deprecated
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt[] getDPtArray();
    
    /**
     * Gets ith "dPt" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt getDPtArray(int i);
    
    /**
     * Returns number of "dPt" element
     */
    int sizeOfDPtArray();
    
    /**
     * Sets array of all "dPt" element
     */
    void setDPtArray(org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt[] dPtArray);
    
    /**
     * Sets ith "dPt" element
     */
    void setDPtArray(int i, org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt dPt);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "dPt" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt insertNewDPt(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "dPt" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt addNewDPt();
    
    /**
     * Removes the ith "dPt" element
     */
    void removeDPt(int i);
    
    /**
     * Gets the "dLbls" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls getDLbls();
    
    /**
     * True if has "dLbls" element
     */
    boolean isSetDLbls();
    
    /**
     * Sets the "dLbls" element
     */
    void setDLbls(org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls dLbls);
    
    /**
     * Appends and returns a new empty "dLbls" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls addNewDLbls();
    
    /**
     * Unsets the "dLbls" element
     */
    void unsetDLbls();
    
    /**
     * Gets the "cat" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource getCat();
    
    /**
     * True if has "cat" element
     */
    boolean isSetCat();
    
    /**
     * Sets the "cat" element
     */
    void setCat(org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource cat);
    
    /**
     * Appends and returns a new empty "cat" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource addNewCat();
    
    /**
     * Unsets the "cat" element
     */
    void unsetCat();
    
    /**
     * Gets the "val" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource getVal();
    
    /**
     * True if has "val" element
     */
    boolean isSetVal();
    
    /**
     * Sets the "val" element
     */
    void setVal(org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource val);
    
    /**
     * Appends and returns a new empty "val" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource addNewVal();
    
    /**
     * Unsets the "val" element
     */
    void unsetVal();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList addNewExtLst();
    
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
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
