1 - Install OD 8 (download bundle here :
	* http://www.obeo.fr/download/release/designer/8.1/community/latest/packages/ObeoDesigner-Community-8.1-win32.win32.x86_64.zip)
2 - Install database meta-model (update site : http://update.obeo.fr/integration/informationsystem/5.0/design/repository/ )
3 - Install delivery version of M2Doc (local build with delivery version of M2Doc github)
4 - Import plug-ins needed to be insalled for test delivery version find in delivery version of M2Doc github. The plug-ins to import are :
	* org.obeonetwork.database.m2doc.services
	* org.obeonetwork.m2doc.sirius.sample.ecore.design
	* org.obeonetwork.m2doc.sirius.tests
5 - Launch runtime instance
7 - Import plug-ins needed for test delivery version find in delivery version of M2Doc github. The plug-ins to import are :
	* org.obeonetwork.m2doc.ecore.wtable.test
	* org.obeonetwork.m2doc.test.regression
6 - After that you can execute test explain in following readme files : 
	* /org.obeonetwork.m2doc.test.regression/README.txt
	* /org.obeonetwork.m2doc.ecore.wtable.test/refreshDiag/README.txt
	* /org.obeonetwork.m2doc.ecore.wtable.test/refreshWTable/README.txt
	* /org.obeonetwork.m2doc.ecore.wtable.test/wtable/README.txt