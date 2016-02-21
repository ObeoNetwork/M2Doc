/*
 * XML Type:  ST_FillType
 * Namespace: urn:schemas-microsoft-com:office:office
 * Java type: schemasMicrosoftComOfficeOffice.STFillType
 *
 * Automatically generated - do not modify.
 */
package schemasMicrosoftComOfficeOffice;


/**
 * An XML ST_FillType(@urn:schemas-microsoft-com:office:office).
 *
 * This is an atomic type that is a restriction of schemasMicrosoftComOfficeOffice.STFillType.
 */
public interface STFillType extends org.apache.xmlbeans.XmlString
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(STFillType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("stfilltype0d50type");
    
    org.apache.xmlbeans.StringEnumAbstractBase enumValue();
    void set(org.apache.xmlbeans.StringEnumAbstractBase e);
    
    static final Enum GRADIENT_CENTER = Enum.forString("gradientCenter");
    static final Enum SOLID = Enum.forString("solid");
    static final Enum PATTERN = Enum.forString("pattern");
    static final Enum TILE = Enum.forString("tile");
    static final Enum FRAME = Enum.forString("frame");
    static final Enum GRADIENT_UNSCALED = Enum.forString("gradientUnscaled");
    static final Enum GRADIENT_RADIAL = Enum.forString("gradientRadial");
    static final Enum GRADIENT = Enum.forString("gradient");
    static final Enum BACKGROUND = Enum.forString("background");
    
    static final int INT_GRADIENT_CENTER = Enum.INT_GRADIENT_CENTER;
    static final int INT_SOLID = Enum.INT_SOLID;
    static final int INT_PATTERN = Enum.INT_PATTERN;
    static final int INT_TILE = Enum.INT_TILE;
    static final int INT_FRAME = Enum.INT_FRAME;
    static final int INT_GRADIENT_UNSCALED = Enum.INT_GRADIENT_UNSCALED;
    static final int INT_GRADIENT_RADIAL = Enum.INT_GRADIENT_RADIAL;
    static final int INT_GRADIENT = Enum.INT_GRADIENT;
    static final int INT_BACKGROUND = Enum.INT_BACKGROUND;
    
    /**
     * Enumeration value class for schemasMicrosoftComOfficeOffice.STFillType.
     * These enum values can be used as follows:
     * <pre>
     * enum.toString(); // returns the string value of the enum
     * enum.intValue(); // returns an int value, useful for switches
     * // e.g., case Enum.INT_GRADIENT_CENTER
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
        
        static final int INT_GRADIENT_CENTER = 1;
        static final int INT_SOLID = 2;
        static final int INT_PATTERN = 3;
        static final int INT_TILE = 4;
        static final int INT_FRAME = 5;
        static final int INT_GRADIENT_UNSCALED = 6;
        static final int INT_GRADIENT_RADIAL = 7;
        static final int INT_GRADIENT = 8;
        static final int INT_BACKGROUND = 9;
        
        public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
            new org.apache.xmlbeans.StringEnumAbstractBase.Table
        (
            new Enum[]
            {
                new Enum("gradientCenter", INT_GRADIENT_CENTER),
                new Enum("solid", INT_SOLID),
                new Enum("pattern", INT_PATTERN),
                new Enum("tile", INT_TILE),
                new Enum("frame", INT_FRAME),
                new Enum("gradientUnscaled", INT_GRADIENT_UNSCALED),
                new Enum("gradientRadial", INT_GRADIENT_RADIAL),
                new Enum("gradient", INT_GRADIENT),
                new Enum("background", INT_BACKGROUND),
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
        public static schemasMicrosoftComOfficeOffice.STFillType newValue(java.lang.Object obj) {
          return (schemasMicrosoftComOfficeOffice.STFillType) type.newValue( obj ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType newInstance() {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static schemasMicrosoftComOfficeOffice.STFillType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.STFillType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static schemasMicrosoftComOfficeOffice.STFillType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (schemasMicrosoftComOfficeOffice.STFillType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
