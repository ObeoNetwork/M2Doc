/*
 * XML Type:  ST_FillMethod
 * Namespace: urn:schemas-microsoft-com:vml
 * Java type: schemasMicrosoftComVml.STFillMethod
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComVml;


/**
 * An XML ST_FillMethod(@urn:schemas-microsoft-com:vml).
 *
 * This is an atomic type that is a restriction of schemasMicrosoftComVml.STFillMethod.
 */
public interface STFillMethod extends org.apache.xmlbeans.XmlString
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(STFillMethod.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("stfillmethoda592type");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum NONE = Enum.forString("none");
    static final Enum LINEAR = Enum.forString("linear");
    static final Enum SIGMA = Enum.forString("sigma");
    static final Enum ANY = Enum.forString("any");
    static final Enum LINEAR_SIGMA = Enum.forString("linear sigma");
    
    static final int INT_NONE = Enum.INT_NONE;
    static final int INT_LINEAR = Enum.INT_LINEAR;
    static final int INT_SIGMA = Enum.INT_SIGMA;
    static final int INT_ANY = Enum.INT_ANY;
    static final int INT_LINEAR_SIGMA = Enum.INT_LINEAR_SIGMA;
    
    /**
     * Enumeration value class for schemasMicrosoftComVml.STFillMethod.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_NONE
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
        
        static final int INT_NONE = 1;
        static final int INT_LINEAR = 2;
        static final int INT_SIGMA = 3;
        static final int INT_ANY = 4;
        static final int INT_LINEAR_SIGMA = 5;
        
        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("none", INT_NONE),
                new Enum("linear", INT_LINEAR),
                new Enum("sigma", INT_SIGMA),
                new Enum("any", INT_ANY),
                new Enum("linear sigma", INT_LINEAR_SIGMA),
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
        public static schemasMicrosoftComVml.STFillMethod newValue(java.lang.Object obj) {
          return (schemasMicrosoftComVml.STFillMethod) type.newValue( obj ); }
        
        public static schemasMicrosoftComVml.STFillMethod newInstance() {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComVml.STFillMethod parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComVml.STFillMethod parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComVml.STFillMethod parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComVml.STFillMethod parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComVml.STFillMethod parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComVml.STFillMethod) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
