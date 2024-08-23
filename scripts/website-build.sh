#!/bin/sh
TAG=$1

mkdir web-site
cd web-site
echo "Clone gh-pages"
git clone https://$GH_TOKEN@github.com/ObeoNetwork/M2Doc.git -b gh-pages --quiet .
echo "Build gh-pages"
# create documentation folder
mkdir ../plugins/org.obeonetwork.m2doc.doc/doc
# download the nighlty documentation
echo " - Download nightly documentation"
wget -r --no-parent --no-host-directories --no-verbose -P ../plugins/org.obeonetwork.m2doc.doc/doc/ https://www.m2doc.org/ref-doc/nightly/
# replace nigthly by current tag
if [ -n "$TAG" ]; then
    echo " - Replace Nightly to" $TAG
    find ../plugins/org.obeonetwork.m2doc.doc/doc/ -name "*.html" -exec sed -i 's#Nightly#'$TAG'#g' {} \;
    find ../plugins/org.obeonetwork.m2doc.doc/doc/ -name "*.html" -exec sed -i 's#nightly#'$TAG'#g' {} \;
fi
# remove the navigation bar
echo " - Remove navigation bar"
find ../plugins/org.obeonetwork.m2doc.doc/doc/ -name "*.html" -exec sed -i '/<nav/,/nav>/d' {} \;
# replace all download versions link
echo " - Replace all versions download link"
find ../plugins/org.obeonetwork.m2doc.doc/doc/ -name "*.html" -exec sed -i 's_../../download/#all-versions_https://github.com/ObeoNetwork/M2Doc/releases_g' {} \;
# copy static resources
echo " - Copy static resources"
cp -R css ../plugins/org.obeonetwork.m2doc.doc/doc/
cp -R images ../plugins/org.obeonetwork.m2doc.doc/doc/
cp -R capella ../plugins/org.obeonetwork.m2doc.doc/doc/
cp -R font-awesome ../plugins/org.obeonetwork.m2doc.doc/doc/
cp ref-doc/index-toc.xml ../plugins/org.obeonetwork.m2doc.doc/doc/ref-doc/index-toc.xml
cd -

