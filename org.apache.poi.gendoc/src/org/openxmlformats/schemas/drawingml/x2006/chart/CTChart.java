/*
 * XML Type:  CT_Chart
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chart
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;


/**
 * An XML CT_Chart(@http://schemas.openxmlformats.org/drawingml/2006/chart).
 *
 * This is a complex type.
 */
public interface CTChart extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTChart.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctchartc108type");
    
    /**
     * Gets the "title" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle getTitle();
    
    /**
     * True if has "title" element
     */
    boolean isSetTitle();
    
    /**
     * Sets the "title" element
     */
    void setTitle(org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle title);
    
    /**
     * Appends and returns a new empty "title" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle addNewTitle();
    
    /**
     * Unsets the "title" element
     */
    void unsetTitle();
    
    /**
     * Gets the "autoTitleDeleted" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getAutoTitleDeleted();
    
    /**
     * True if has "autoTitleDeleted" element
     */
    boolean isSetAutoTitleDeleted();
    
    /**
     * Sets the "autoTitleDeleted" element
     */
    void setAutoTitleDeleted(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean autoTitleDeleted);
    
    /**
     * Appends and returns a new empty "autoTitleDeleted" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewAutoTitleDeleted();
    
    /**
     * Unsets the "autoTitleDeleted" element
     */
    void unsetAutoTitleDeleted();
    
    /**
     * Gets the "pivotFmts" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts getPivotFmts();
    
    /**
     * True if has "pivotFmts" element
     */
    boolean isSetPivotFmts();
    
    /**
     * Sets the "pivotFmts" element
     */
    void setPivotFmts(org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts pivotFmts);
    
    /**
     * Appends and returns a new empty "pivotFmts" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts addNewPivotFmts();
    
    /**
     * Unsets the "pivotFmts" element
     */
    void unsetPivotFmts();
    
    /**
     * Gets the "view3D" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D getView3D();
    
    /**
     * True if has "view3D" element
     */
    boolean isSetView3D();
    
    /**
     * Sets the "view3D" element
     */
    void setView3D(org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D view3D);
    
    /**
     * Appends and returns a new empty "view3D" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D addNewView3D();
    
    /**
     * Unsets the "view3D" element
     */
    void unsetView3D();
    
    /**
     * Gets the "floor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface getFloor();
    
    /**
     * True if has "floor" element
     */
    boolean isSetFloor();
    
    /**
     * Sets the "floor" element
     */
    void setFloor(org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface floor);
    
    /**
     * Appends and returns a new empty "floor" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface addNewFloor();
    
    /**
     * Unsets the "floor" element
     */
    void unsetFloor();
    
    /**
     * Gets the "sideWall" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface getSideWall();
    
    /**
     * True if has "sideWall" element
     */
    boolean isSetSideWall();
    
    /**
     * Sets the "sideWall" element
     */
    void setSideWall(org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface sideWall);
    
    /**
     * Appends and returns a new empty "sideWall" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface addNewSideWall();
    
    /**
     * Unsets the "sideWall" element
     */
    void unsetSideWall();
    
    /**
     * Gets the "backWall" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface getBackWall();
    
    /**
     * True if has "backWall" element
     */
    boolean isSetBackWall();
    
    /**
     * Sets the "backWall" element
     */
    void setBackWall(org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface backWall);
    
    /**
     * Appends and returns a new empty "backWall" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface addNewBackWall();
    
    /**
     * Unsets the "backWall" element
     */
    void unsetBackWall();
    
    /**
     * Gets the "plotArea" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea getPlotArea();
    
    /**
     * Sets the "plotArea" element
     */
    void setPlotArea(org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea plotArea);
    
    /**
     * Appends and returns a new empty "plotArea" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea addNewPlotArea();
    
    /**
     * Gets the "legend" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend getLegend();
    
    /**
     * True if has "legend" element
     */
    boolean isSetLegend();
    
    /**
     * Sets the "legend" element
     */
    void setLegend(org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend legend);
    
    /**
     * Appends and returns a new empty "legend" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend addNewLegend();
    
    /**
     * Unsets the "legend" element
     */
    void unsetLegend();
    
    /**
     * Gets the "plotVisOnly" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getPlotVisOnly();
    
    /**
     * True if has "plotVisOnly" element
     */
    boolean isSetPlotVisOnly();
    
    /**
     * Sets the "plotVisOnly" element
     */
    void setPlotVisOnly(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean plotVisOnly);
    
    /**
     * Appends and returns a new empty "plotVisOnly" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewPlotVisOnly();
    
    /**
     * Unsets the "plotVisOnly" element
     */
    void unsetPlotVisOnly();
    
    /**
     * Gets the "dispBlanksAs" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs getDispBlanksAs();
    
    /**
     * True if has "dispBlanksAs" element
     */
    boolean isSetDispBlanksAs();
    
    /**
     * Sets the "dispBlanksAs" element
     */
    void setDispBlanksAs(org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs dispBlanksAs);
    
    /**
     * Appends and returns a new empty "dispBlanksAs" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs addNewDispBlanksAs();
    
    /**
     * Unsets the "dispBlanksAs" element
     */
    void unsetDispBlanksAs();
    
    /**
     * Gets the "showDLblsOverMax" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getShowDLblsOverMax();
    
    /**
     * True if has "showDLblsOverMax" element
     */
    boolean isSetShowDLblsOverMax();
    
    /**
     * Sets the "showDLblsOverMax" element
     */
    void setShowDLblsOverMax(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean showDLblsOverMax);
    
    /**
     * Appends and returns a new empty "showDLblsOverMax" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewShowDLblsOverMax();
    
    /**
     * Unsets the "showDLblsOverMax" element
     */
    void unsetShowDLblsOverMax();
    
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
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTChart parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTChart) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
