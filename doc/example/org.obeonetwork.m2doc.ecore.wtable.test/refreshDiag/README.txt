To execute refresh test : 
1 - generate with no model modification with “testRefreshDiagTagToFalse.genconf” or “testRefreshDiagTagToTrue.genconf” launcher
	* the result file “resultRefreshDiag.docx” should be the same document than 1_resultRefreshDiagNoModelModifExpected.docx
2 - generate document after add class named “Test” in model with “testRefreshDiagTagToFalse.genconf” launcher 
    * the result file “resultRefreshDiag.docx” should be the same document than 2_resultRefreshDiagModelModifRefershTagFalseExpected.docx
3 - generate document after add class named “Test” in model with “testRefreshDiagTagToTrue.genconf” launcher 
    * the result file “resultRefreshDiag.docx” should be the same document than 3_resultRefreshDiagModelModifRefreshTagTrueExpected.docx
4 - generate document after delete class named “Test” in model with “testRefreshDiagTagToTrue.genconf” launcher 
    * the result file “resultRefreshDiag.docx” should be the same document than 4_resultRefreshDiagModelOrigRefreshTagTrueExpected.docx

During those actions should not open the siruis diagram over wise results should be wrong.
To add some element in ecore model, you should use emf tree editor.

To compare expected and generated documents, you can used Compare tool in Word.
