/*
 * XML Type:  ST_TrueFalseBlank
 * Namespace: urn:schemas-microsoft-com:office:excel
 * Java type: schemasMicrosoftComOfficeExcel.STTrueFalseBlank
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeExcel;


/**
 * An XML ST_TrueFalseBlank(@urn:schemas-microsoft-com:office:excel).
 *
 * This is an atomic type that is a restriction of schemasMicrosoftComOfficeExcel.STTrueFalseBlank.
 */
public interface STTrueFalseBlank extends org.apache.xmlbeans.XmlString
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(STTrueFalseBlank.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("sttruefalseblanka061type");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum TRUE = Enum.forString("True");
    static final Enum T = Enum.forString("t");
    static final Enum FALSE = Enum.forString("False");
    static final Enum F = Enum.forString("f");
    static final Enum X = Enum.forString("");
    
    static final int INT_TRUE = Enum.INT_TRUE;
    static final int INT_T = Enum.INT_T;
    static final int INT_FALSE = Enum.INT_FALSE;
    static final int INT_F = Enum.INT_F;
    static final int INT_X = Enum.INT_X;
    
    /**
     * Enumeration value class for schemasMicrosoftComOfficeExcel.STTrueFalseBlank.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_TRUE
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
        
        static final int INT_TRUE = 1;
        static final int INT_T = 2;
        static final int INT_FALSE = 3;
        static final int INT_F = 4;
        static final int INT_X = 5;
        
        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("True", INT_TRUE),
                new Enum("t", INT_T),
                new Enum("False", INT_FALSE),
                new Enum("f", INT_F),
                new Enum("", INT_X),
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
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank newValue(java.lang.Object obj) {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) type.newValue( obj ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank newInstance() {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeExcel.STTrueFalseBlank parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeExcel.STTrueFalseBlank) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
