/*
 * XML Type:  CT_Protection
 * Namespace: http://schemas.openxmlformats.org/drawingml/2006/chart
 * Java type: org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.drawingml.x2006.chart;


/**
 * An XML CT_Protection(@http://schemas.openxmlformats.org/drawingml/2006/chart).
 *
 * This is a complex type.
 */
public interface CTProtection extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTProtection.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctprotectione383type");
    
    /**
     * Gets the "chartObject" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getChartObject();
    
    /**
     * True if has "chartObject" element
     */
    boolean isSetChartObject();
    
    /**
     * Sets the "chartObject" element
     */
    void setChartObject(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean chartObject);
    
    /**
     * Appends and returns a new empty "chartObject" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewChartObject();
    
    /**
     * Unsets the "chartObject" element
     */
    void unsetChartObject();
    
    /**
     * Gets the "data" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getData();
    
    /**
     * True if has "data" element
     */
    boolean isSetData();
    
    /**
     * Sets the "data" element
     */
    void setData(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean data);
    
    /**
     * Appends and returns a new empty "data" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewData();
    
    /**
     * Unsets the "data" element
     */
    void unsetData();
    
    /**
     * Gets the "formatting" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getFormatting();
    
    /**
     * True if has "formatting" element
     */
    boolean isSetFormatting();
    
    /**
     * Sets the "formatting" element
     */
    void setFormatting(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean formatting);
    
    /**
     * Appends and returns a new empty "formatting" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewFormatting();
    
    /**
     * Unsets the "formatting" element
     */
    void unsetFormatting();
    
    /**
     * Gets the "selection" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getSelection();
    
    /**
     * True if has "selection" element
     */
    boolean isSetSelection();
    
    /**
     * Sets the "selection" element
     */
    void setSelection(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean selection);
    
    /**
     * Appends and returns a new empty "selection" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewSelection();
    
    /**
     * Unsets the "selection" element
     */
    void unsetSelection();
    
    /**
     * Gets the "userInterface" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean getUserInterface();
    
    /**
     * True if has "userInterface" element
     */
    boolean isSetUserInterface();
    
    /**
     * Sets the "userInterface" element
     */
    void setUserInterface(org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean userInterface);
    
    /**
     * Appends and returns a new empty "userInterface" element
     */
    org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean addNewUserInterface();
    
    /**
     * Unsets the "userInterface" element
     */
    void unsetUserInterface();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection newInstance() {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
