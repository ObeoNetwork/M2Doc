To execute refresh test on WTable : 
1 - generate with no model modification with testRefreshWTableTagToFalse.genconf or testRefreshWTableTagToTrue.genconf launcher
	* the result file resultWTable.docx should be the same document than 1_resultWTableNoModelModifExpected.docx
2 - generate document after add class named Test in model with testRefreshWTableTagToFalse.genconf launcher 
    * the result file resultWTable.docx should be the same document than 2_resultWTableModelModifRefreshTagFalseExpected.docx
3 - generate document after add class named Test in model with testRefreshWTableTagToTrue.genconf launcher 
    * the result file resultWTable.docx should be the same document than 3_resultWTableModelModifRefreshTagTrueExpected.docx
4 - generate document after delete class named Test in model with testRefreshWTableTagToTrue.genconf launcher 
    * the result file resultWTable.docx should be the same document than 4_resultWTableDiagModelOrigRefreshTagTrueExpected.docx

During those actions should not open the siruis diagram over wise results should be wrong.
To add some element in ecore model, you should use emf tree editor.

To compare expected and generated documents, you can used Compare tool in Word.

