/*
 * XML Type:  CT_PivotArea
 * Namespace: http://schemas.openxmlformats.org/spreadsheetml/2006/main
 * Java type: org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea
 *
 * Automatically generated - do not modify.
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;


/**
 * An XML CT_PivotArea(@http://schemas.openxmlformats.org/spreadsheetml/2006/main).
 *
 * This is a complex type.
 */
public interface CTPivotArea extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CTPivotArea.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sE130CAA0A01A7CDE5A2B4FEB8B311707").resolveHandle("ctpivotarea26cetype");
    
    /**
     * Gets the "references" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotAreaReferences getReferences();
    
    /**
     * True if has "references" element
     */
    boolean isSetReferences();
    
    /**
     * Sets the "references" element
     */
    void setReferences(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotAreaReferences references);
    
    /**
     * Appends and returns a new empty "references" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotAreaReferences addNewReferences();
    
    /**
     * Unsets the "references" element
     */
    void unsetReferences();
    
    /**
     * Gets the "extLst" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList getExtLst();
    
    /**
     * True if has "extLst" element
     */
    boolean isSetExtLst();
    
    /**
     * Sets the "extLst" element
     */
    void setExtLst(org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList extLst);
    
    /**
     * Appends and returns a new empty "extLst" element
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList addNewExtLst();
    
    /**
     * Unsets the "extLst" element
     */
    void unsetExtLst();
    
    /**
     * Gets the "field" attribute
     */
    int getField();
    
    /**
     * Gets (as xml) the "field" attribute
     */
    org.apache.xmlbeans.XmlInt xgetField();
    
    /**
     * True if has "field" attribute
     */
    boolean isSetField();
    
    /**
     * Sets the "field" attribute
     */
    void setField(int field);
    
    /**
     * Sets (as xml) the "field" attribute
     */
    void xsetField(org.apache.xmlbeans.XmlInt field);
    
    /**
     * Unsets the "field" attribute
     */
    void unsetField();
    
    /**
     * Gets the "type" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STPivotAreaType.Enum getType();
    
    /**
     * Gets (as xml) the "type" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STPivotAreaType xgetType();
    
    /**
     * True if has "type" attribute
     */
    boolean isSetType();
    
    /**
     * Sets the "type" attribute
     */
    void setType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STPivotAreaType.Enum type);
    
    /**
     * Sets (as xml) the "type" attribute
     */
    void xsetType(org.openxmlformats.schemas.spreadsheetml.x2006.main.STPivotAreaType type);
    
    /**
     * Unsets the "type" attribute
     */
    void unsetType();
    
    /**
     * Gets the "dataOnly" attribute
     */
    boolean getDataOnly();
    
    /**
     * Gets (as xml) the "dataOnly" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetDataOnly();
    
    /**
     * True if has "dataOnly" attribute
     */
    boolean isSetDataOnly();
    
    /**
     * Sets the "dataOnly" attribute
     */
    void setDataOnly(boolean dataOnly);
    
    /**
     * Sets (as xml) the "dataOnly" attribute
     */
    void xsetDataOnly(org.apache.xmlbeans.XmlBoolean dataOnly);
    
    /**
     * Unsets the "dataOnly" attribute
     */
    void unsetDataOnly();
    
    /**
     * Gets the "labelOnly" attribute
     */
    boolean getLabelOnly();
    
    /**
     * Gets (as xml) the "labelOnly" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetLabelOnly();
    
    /**
     * True if has "labelOnly" attribute
     */
    boolean isSetLabelOnly();
    
    /**
     * Sets the "labelOnly" attribute
     */
    void setLabelOnly(boolean labelOnly);
    
    /**
     * Sets (as xml) the "labelOnly" attribute
     */
    void xsetLabelOnly(org.apache.xmlbeans.XmlBoolean labelOnly);
    
    /**
     * Unsets the "labelOnly" attribute
     */
    void unsetLabelOnly();
    
    /**
     * Gets the "grandRow" attribute
     */
    boolean getGrandRow();
    
    /**
     * Gets (as xml) the "grandRow" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetGrandRow();
    
    /**
     * True if has "grandRow" attribute
     */
    boolean isSetGrandRow();
    
    /**
     * Sets the "grandRow" attribute
     */
    void setGrandRow(boolean grandRow);
    
    /**
     * Sets (as xml) the "grandRow" attribute
     */
    void xsetGrandRow(org.apache.xmlbeans.XmlBoolean grandRow);
    
    /**
     * Unsets the "grandRow" attribute
     */
    void unsetGrandRow();
    
    /**
     * Gets the "grandCol" attribute
     */
    boolean getGrandCol();
    
    /**
     * Gets (as xml) the "grandCol" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetGrandCol();
    
    /**
     * True if has "grandCol" attribute
     */
    boolean isSetGrandCol();
    
    /**
     * Sets the "grandCol" attribute
     */
    void setGrandCol(boolean grandCol);
    
    /**
     * Sets (as xml) the "grandCol" attribute
     */
    void xsetGrandCol(org.apache.xmlbeans.XmlBoolean grandCol);
    
    /**
     * Unsets the "grandCol" attribute
     */
    void unsetGrandCol();
    
    /**
     * Gets the "cacheIndex" attribute
     */
    boolean getCacheIndex();
    
    /**
     * Gets (as xml) the "cacheIndex" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetCacheIndex();
    
    /**
     * True if has "cacheIndex" attribute
     */
    boolean isSetCacheIndex();
    
    /**
     * Sets the "cacheIndex" attribute
     */
    void setCacheIndex(boolean cacheIndex);
    
    /**
     * Sets (as xml) the "cacheIndex" attribute
     */
    void xsetCacheIndex(org.apache.xmlbeans.XmlBoolean cacheIndex);
    
    /**
     * Unsets the "cacheIndex" attribute
     */
    void unsetCacheIndex();
    
    /**
     * Gets the "outline" attribute
     */
    boolean getOutline();
    
    /**
     * Gets (as xml) the "outline" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetOutline();
    
    /**
     * True if has "outline" attribute
     */
    boolean isSetOutline();
    
    /**
     * Sets the "outline" attribute
     */
    void setOutline(boolean outline);
    
    /**
     * Sets (as xml) the "outline" attribute
     */
    void xsetOutline(org.apache.xmlbeans.XmlBoolean outline);
    
    /**
     * Unsets the "outline" attribute
     */
    void unsetOutline();
    
    /**
     * Gets the "offset" attribute
     */
    java.lang.String getOffset();
    
    /**
     * Gets (as xml) the "offset" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef xgetOffset();
    
    /**
     * True if has "offset" attribute
     */
    boolean isSetOffset();
    
    /**
     * Sets the "offset" attribute
     */
    void setOffset(java.lang.String offset);
    
    /**
     * Sets (as xml) the "offset" attribute
     */
    void xsetOffset(org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef offset);
    
    /**
     * Unsets the "offset" attribute
     */
    void unsetOffset();
    
    /**
     * Gets the "collapsedLevelsAreSubtotals" attribute
     */
    boolean getCollapsedLevelsAreSubtotals();
    
    /**
     * Gets (as xml) the "collapsedLevelsAreSubtotals" attribute
     */
    org.apache.xmlbeans.XmlBoolean xgetCollapsedLevelsAreSubtotals();
    
    /**
     * True if has "collapsedLevelsAreSubtotals" attribute
     */
    boolean isSetCollapsedLevelsAreSubtotals();
    
    /**
     * Sets the "collapsedLevelsAreSubtotals" attribute
     */
    void setCollapsedLevelsAreSubtotals(boolean collapsedLevelsAreSubtotals);
    
    /**
     * Sets (as xml) the "collapsedLevelsAreSubtotals" attribute
     */
    void xsetCollapsedLevelsAreSubtotals(org.apache.xmlbeans.XmlBoolean collapsedLevelsAreSubtotals);
    
    /**
     * Unsets the "collapsedLevelsAreSubtotals" attribute
     */
    void unsetCollapsedLevelsAreSubtotals();
    
    /**
     * Gets the "axis" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis.Enum getAxis();
    
    /**
     * Gets (as xml) the "axis" attribute
     */
    org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis xgetAxis();
    
    /**
     * True if has "axis" attribute
     */
    boolean isSetAxis();
    
    /**
     * Sets the "axis" attribute
     */
    void setAxis(org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis.Enum axis);
    
    /**
     * Sets (as xml) the "axis" attribute
     */
    void xsetAxis(org.openxmlformats.schemas.spreadsheetml.x2006.main.STAxis axis);
    
    /**
     * Unsets the "axis" attribute
     */
    void unsetAxis();
    
    /**
     * Gets the "fieldPosition" attribute
     */
    long getFieldPosition();
    
    /**
     * Gets (as xml) the "fieldPosition" attribute
     */
    org.apache.xmlbeans.XmlUnsignedInt xgetFieldPosition();
    
    /**
     * True if has "fieldPosition" attribute
     */
    boolean isSetFieldPosition();
    
    /**
     * Sets the "fieldPosition" attribute
     */
    void setFieldPosition(long fieldPosition);
    
    /**
     * Sets (as xml) the "fieldPosition" attribute
     */
    void xsetFieldPosition(org.apache.xmlbeans.XmlUnsignedInt fieldPosition);
    
    /**
     * Unsets the "fieldPosition" attribute
     */
    void unsetFieldPosition();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea newInstance() {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPivotArea) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
