/*
 * XML Type:  CT_ShowProperties
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_ShowProperties(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTShowProperties extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTShowProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctshowproperties7555type");
    
    /**
     * Gets the "present" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty getPresent();
    
    /**
     * True if has "present" element
     */
    boolean isSetPresent();
    
    /**
     * Sets the "present" element
     */
    void setPresent(org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty present);
    
    /**
     * Appends and returns a new empty "present" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty addNewPresent();
    
    /**
     * Unsets the "present" element
     */
    void unsetPresent();
    
    /**
     * Gets the "browse" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTShowInfoBrowse getBrowse();
    
    /**
     * True if has "browse" element
     */
    boolean isSetBrowse();
    
    /**
     * Sets the "browse" element
     */
    void setBrowse(org.openxmlformats.schemas.presentationml.x2006.main.CTShowInfoBrowse browse);
    
    /**
     * Appends and returns a new empty "browse" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTShowInfoBrowse addNewBrowse();
    
    /**
     * Unsets the "browse" element
     */
    void unsetBrowse();
    
    /**
     * Gets the "kiosk" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTShowInfoKiosk getKiosk();
    
    /**
     * True if has "kiosk" element
     */
    boolean isSetKiosk();
    
    /**
     * Sets the "kiosk" element
     */
    void setKiosk(org.openxmlformats.schemas.presentationml.x2006.main.CTShowInfoKiosk kiosk);
    
    /**
     * Appends and returns a new empty "kiosk" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTShowInfoKiosk addNewKiosk();
    
    /**
     * Unsets the "kiosk" element
     */
    void unsetKiosk();
    
    /**
     * Gets the "sldAll" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty getSldAll();
    
    /**
     * True if has "sldAll" element
     */
    boolean isSetSldAll();
    
    /**
     * Sets the "sldAll" element
     */
    void setSldAll(org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty sldAll);
    
    /**
     * Appends and returns a new empty "sldAll" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTEmpty addNewSldAll();
    
    /**
     * Unsets the "sldAll" element
     */
    void unsetSldAll();
    
    /**
     * Gets the "sldRg" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTIndexRange getSldRg();
    
    /**
     * True if has "sldRg" element
     */
    boolean isSetSldRg();
    
    /**
     * Sets the "sldRg" element
     */
    void setSldRg(org.openxmlformats.schemas.presentationml.x2006.main.CTIndexRange sldRg);
    
    /**
     * Appends and returns a new empty "sldRg" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTIndexRange addNewSldRg();
    
    /**
     * Unsets the "sldRg" element
     */
    void unsetSldRg();
    
    /**
     * Gets the "custShow" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowId getCustShow();
    
    /**
     * True if has "custShow" element
     */
    boolean isSetCustShow();
    
    /**
     * Sets the "custShow" element
     */
    void setCustShow(org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowId custShow);
    
    /**
     * Appends and returns a new empty "custShow" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowId addNewCustShow();
    
    /**
     * Unsets the "custShow" element
     */
    void unsetCustShow();
    
    /**
     * Gets the "penClr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor getPenClr();
    
    /**
     * True if has "penClr" element
     */
    boolean isSetPenClr();
    
    /**
     * Sets the "penClr" element
     */
    void setPenClr(org.openxmlformats.schemas.drawingml.x2006.main.CTColor penClr);
    
    /**
     * Appends and returns a new empty "penClr" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor addNewPenClr();
    
    /**
     * Unsets the "penClr" element
     */
    void unsetPenClr();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * Gets the "loop" attribute
     */
    boolean getLoop();
    
    /**
     * Gets (as xml) the "loop" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetLoop();
    
    /**
     * True if has "loop" attribute
     */
    boolean isSetLoop();
    
    /**
     * Sets the "loop" attribute
     */
    void setLoop(boolean loop);
    
    /**
     * Sets (as xml) the "loop" attribute
     */
    void xsetLoop(org.apache.xmlbeans.XmlBoolean loop);
    
    /**
     * Unsets the "loop" attribute
     */
    void unsetLoop();
    
    /**
     * Gets the "showNarration" attribute
     */
    boolean getShowNarration();
    
    /**
     * Gets (as xml) the "showNarration" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetShowNarration();
    
    /**
     * True if has "showNarration" attribute
     */
    boolean isSetShowNarration();
    
    /**
     * Sets the "showNarration" attribute
     */
    void setShowNarration(boolean showNarration);
    
    /**
     * Sets (as xml) the "showNarration" attribute
     */
    void xsetShowNarration(org.apache.xmlbeans.XmlBoolean showNarration);
    
    /**
     * Unsets the "showNarration" attribute
     */
    void unsetShowNarration();
    
    /**
     * Gets the "showAnimation" attribute
     */
    boolean getShowAnimation();
    
    /**
     * Gets (as xml) the "showAnimation" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetShowAnimation();
    
    /**
     * True if has "showAnimation" attribute
     */
    boolean isSetShowAnimation();
    
    /**
     * Sets the "showAnimation" attribute
     */
    void setShowAnimation(boolean showAnimation);
    
    /**
     * Sets (as xml) the "showAnimation" attribute
     */
    void xsetShowAnimation(org.apache.xmlbeans.XmlBoolean showAnimation);
    
    /**
     * Unsets the "showAnimation" attribute
     */
    void unsetShowAnimation();
    
    /**
     * Gets the "useTimings" attribute
     */
    boolean getUseTimings();
    
    /**
     * Gets (as xml) the "useTimings" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetUseTimings();
    
    /**
     * True if has "useTimings" attribute
     */
    boolean isSetUseTimings();
    
    /**
     * Sets the "useTimings" attribute
     */
    void setUseTimings(boolean useTimings);
    
    /**
     * Sets (as xml) the "useTimings" attribute
     */
    void xsetUseTimings(org.apache.xmlbeans.XmlBoolean useTimings);
    
    /**
     * Unsets the "useTimings" attribute
     */
    void unsetUseTimings();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTShowProperties) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
