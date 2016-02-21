/*
 * XML Type:  CT_Caption
 * Namespace: http://schemas.openxmlformats.org/wordprocessingml/2006/main
 * Java type: org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;


/**
 * An XML CT_Caption(@http://schemas.openxmlformats.org/wordprocessingml/2006/main).
 *
 * This is a complex type.
 */
public interface CTCaption extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTCaption.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctcaption7c6ctype");
    
    /**
     * Gets the "name" attribute
     */
    java.lang.String getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STString xgetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(java.lang.String name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(org.openxmlformats.schemas.wordprocessingml.x2006.main.STString name);
    
    /**
     * Gets the "pos" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STCaptionPos.Enum getPos();
    
    /**
     * Gets (as xml) the "pos" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STCaptionPos xgetPos();
    
    /**
     * True if has "pos" attribute
     */
    boolean isSetPos();
    
    /**
     * Sets the "pos" attribute
     */
    void setPos(org.openxmlformats.schemas.wordprocessingml.x2006.main.STCaptionPos.Enum pos);
    
    /**
     * Sets (as xml) the "pos" attribute
     */
    void xsetPos(org.openxmlformats.schemas.wordprocessingml.x2006.main.STCaptionPos pos);
    
    /**
     * Unsets the "pos" attribute
     */
    void unsetPos();
    
    /**
     * Gets the "chapNum" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getChapNum();
    
    /**
     * Gets (as xml) the "chapNum" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetChapNum();
    
    /**
     * True if has "chapNum" attribute
     */
    boolean isSetChapNum();
    
    /**
     * Sets the "chapNum" attribute
     */
    void setChapNum(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum chapNum);
    
    /**
     * Sets (as xml) the "chapNum" attribute
     */
    void xsetChapNum(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff chapNum);
    
    /**
     * Unsets the "chapNum" attribute
     */
    void unsetChapNum();
    
    /**
     * Gets the "heading" attribute
     */
    java.math.BigInteger getHeading();
    
    /**
     * Gets (as xml) the "heading" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber xgetHeading();
    
    /**
     * True if has "heading" attribute
     */
    boolean isSetHeading();
    
    /**
     * Sets the "heading" attribute
     */
    void setHeading(java.math.BigInteger heading);
    
    /**
     * Sets (as xml) the "heading" attribute
     */
    void xsetHeading(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber heading);
    
    /**
     * Unsets the "heading" attribute
     */
    void unsetHeading();
    
    /**
     * Gets the "noLabel" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum getNoLabel();
    
    /**
     * Gets (as xml) the "noLabel" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff xgetNoLabel();
    
    /**
     * True if has "noLabel" attribute
     */
    boolean isSetNoLabel();
    
    /**
     * Sets the "noLabel" attribute
     */
    void setNoLabel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum noLabel);
    
    /**
     * Sets (as xml) the "noLabel" attribute
     */
    void xsetNoLabel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff noLabel);
    
    /**
     * Unsets the "noLabel" attribute
     */
    void unsetNoLabel();
    
    /**
     * Gets the "numFmt" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat.Enum getNumFmt();
    
    /**
     * Gets (as xml) the "numFmt" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat xgetNumFmt();
    
    /**
     * True if has "numFmt" attribute
     */
    boolean isSetNumFmt();
    
    /**
     * Sets the "numFmt" attribute
     */
    void setNumFmt(org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat.Enum numFmt);
    
    /**
     * Sets (as xml) the "numFmt" attribute
     */
    void xsetNumFmt(org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat numFmt);
    
    /**
     * Unsets the "numFmt" attribute
     */
    void unsetNumFmt();
    
    /**
     * Gets the "sep" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STChapterSep.Enum getSep();
    
    /**
     * Gets (as xml) the "sep" attribute
     */
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STChapterSep xgetSep();
    
    /**
     * True if has "sep" attribute
     */
    boolean isSetSep();
    
    /**
     * Sets the "sep" attribute
     */
    void setSep(org.openxmlformats.schemas.wordprocessingml.x2006.main.STChapterSep.Enum sep);
    
    /**
     * Sets (as xml) the "sep" attribute
     */
    void xsetSep(org.openxmlformats.schemas.wordprocessingml.x2006.main.STChapterSep sep);
    
    /**
     * Unsets the "sep" attribute
     */
    void unsetSep();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption newInstance() {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCaption) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
