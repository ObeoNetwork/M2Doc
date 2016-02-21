/*
 * XML Type:  CT_TLAnimVariant
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML CT_TLAnimVariant(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is a complex type.
 */
public interface CTTLAnimVariant extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTTLAnimVariant.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("cttlanimvariantc9d3type");
    
    /**
     * Gets the "boolVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantBooleanVal getBoolVal();
    
    /**
     * True if has "boolVal" element
     */
    boolean isSetBoolVal();
    
    /**
     * Sets the "boolVal" element
     */
    void setBoolVal(org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantBooleanVal boolVal);
    
    /**
     * Appends and returns a new empty "boolVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantBooleanVal addNewBoolVal();
    
    /**
     * Unsets the "boolVal" element
     */
    void unsetBoolVal();
    
    /**
     * Gets the "intVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantIntegerVal getIntVal();
    
    /**
     * True if has "intVal" element
     */
    boolean isSetIntVal();
    
    /**
     * Sets the "intVal" element
     */
    void setIntVal(org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantIntegerVal intVal);
    
    /**
     * Appends and returns a new empty "intVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantIntegerVal addNewIntVal();
    
    /**
     * Unsets the "intVal" element
     */
    void unsetIntVal();
    
    /**
     * Gets the "fltVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantFloatVal getFltVal();
    
    /**
     * True if has "fltVal" element
     */
    boolean isSetFltVal();
    
    /**
     * Sets the "fltVal" element
     */
    void setFltVal(org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantFloatVal fltVal);
    
    /**
     * Appends and returns a new empty "fltVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantFloatVal addNewFltVal();
    
    /**
     * Unsets the "fltVal" element
     */
    void unsetFltVal();
    
    /**
     * Gets the "strVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantStringVal getStrVal();
    
    /**
     * True if has "strVal" element
     */
    boolean isSetStrVal();
    
    /**
     * Sets the "strVal" element
     */
    void setStrVal(org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantStringVal strVal);
    
    /**
     * Appends and returns a new empty "strVal" element
     */
    org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariantStringVal addNewStrVal();
    
    /**
     * Unsets the "strVal" element
     */
    void unsetStrVal();
    
    /**
     * Gets the "clrVal" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor getClrVal();
    
    /**
     * True if has "clrVal" element
     */
    boolean isSetClrVal();
    
    /**
     * Sets the "clrVal" element
     */
    void setClrVal(org.openxmlformats.schemas.drawingml.x2006.main.CTColor clrVal);
    
    /**
     * Appends and returns a new empty "clrVal" element
     */
    org.openxmlformats.schemas.drawingml.x2006.main.CTColor addNewClrVal();
    
    /**
     * Unsets the "clrVal" element
     */
    void unsetClrVal();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimVariant) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
