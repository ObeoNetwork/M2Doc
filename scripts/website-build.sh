#!/bin/sh
TAG=$1

mkdir web-site
cd web-site
echo "Clone gh-pages"
git clone https://$GH_TOKEN@github.com/ObeoNetwork/M2Doc.git -b gh-pages --quiet .
if [[ "$TAG" != "" ]]; then chmod a+x ./promote.sh; ./promote.sh ${TAG}; fi
echo "Build gh-pages"
jekyll build
ls
# copy to documentation plugin
echo "Copy to documentation plugin"
mkdir ../plugins/org.obeonetwork.m2doc.doc/doc
cp -Rf _site/* ../plugins/org.obeonetwork.m2doc.doc/doc/
cd -

