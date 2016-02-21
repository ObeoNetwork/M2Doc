/*
 * XML Type:  ST_TransitionCornerDirectionType
 * Namespace: http://schemas.openxmlformats.org/presentationml/2006/main
 * Java type: org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.presentationml.x2006.main;


/**
 * An XML ST_TransitionCornerDirectionType(@http://schemas.openxmlformats.org/presentationml/2006/main).
 *
 * This is an atomic type that is a restriction of org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType.
 */
public interface STTransitionCornerDirectionType extends org.apache.xmlbeans.XmlToken
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(STTransitionCornerDirectionType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("sttransitioncornerdirectiontype0850type");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum LU = Enum.forString("lu");
    static final Enum RU = Enum.forString("ru");
    static final Enum LD = Enum.forString("ld");
    static final Enum RD = Enum.forString("rd");
    
    static final int INT_LU = Enum.INT_LU;
    static final int INT_RU = Enum.INT_RU;
    static final int INT_LD = Enum.INT_LD;
    static final int INT_RD = Enum.INT_RD;
    
    /**
     * Enumeration value class for org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_LU
     * Enum.forString(s); // returns the enum value for a string
     * Enum.forInt(i); // returns the enum value for an int
     * </pre>
     * Enumeration objects are immutable singleton objects that
     * can be compared using == object equality. They have no
     * public constructor. See the constants defined within this
     * class for all the valid values.
     */
    static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
    {
        /**
         * Returns the enum value for a string, or null if none.
         */
        public static Enum forString(java.lang.String s)
            { return (Enum)table.forString(s); }
        /**
         * Returns the enum value corresponding to an int, or null if none.
         */
        public static Enum forInt(int i)
            { return (Enum)table.forInt(i); }
        
        private Enum(java.lang.String s, int i)
            { super(s, i); }
        
        static final int INT_LU = 1;
        static final int INT_RU = 2;
        static final int INT_LD = 3;
        static final int INT_RD = 4;
        
        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("lu", INT_LU),
                new Enum("ru", INT_RU),
                new Enum("ld", INT_LD),
                new Enum("rd", INT_RD),
            }
        );
        private static final long serialVersionUID = 1L;
        private java.lang.Object readResolve() { return forInt(intValue()); } 
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType newValue(java.lang.Object obj) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) type.newValue( obj ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType newInstance() {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.presentationml.x2006.main.STTransitionCornerDirectionType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
