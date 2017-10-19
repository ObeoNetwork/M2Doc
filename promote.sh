#!/bin/sh
cp -Rf ref-doc/nightly/ ref-doc/$1
# add document page to menu
sed -i "s+#---TAG---+#---TAG---\n  - title: $1\n    file: ref-doc/$1/index+g" _data/pages.yml
# reference the current version in the toc
sed -i "s+nightly+$1+g" ref-doc/index-toc.xml
#update the documentation
sed -i "s+article-with-toc-nightly+article-with-toc+g" ref-doc/$1/index.md
sed -i "s+nightly+$1+g" ref-doc/$1/index.md
sed -i "s+Nightly+$1+g" ref-doc/$1/index.md
sed -i "s+master+$1+g" ref-doc/$1/index.md


