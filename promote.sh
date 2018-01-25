#!/bin/sh

# add document page to menu
echo "Create entry $1 in the ref-doc menu."
sed -i "s+#---TAG---+#---TAG---\n  - title: $1\n    file: ref-doc/$1/index+g" _data/pages.yml

# reference the current version in the toc
echo "Change nightly to $1 in ref-doc/index-toc.xml."
sed -i "s+nightly+$1+g" ref-doc/index-toc.xml

# initialize documentation
echo "Copy documentation to ref-doc/$1."
cp -Rf ref-doc/nightly/ ref-doc/$1

#update the documentation
echo "Make other replacements in ref-doc/$1/index.md."
sed -i "s+article-with-toc-nightly+article-with-toc+g" ref-doc/$1/index.md
sed -i "s+nightly+$1+g" ref-doc/$1/index.md
sed -i "s+Nightly+$1+g" ref-doc/$1/index.md
sed -i "s+master+$1+g" ref-doc/$1/index.md

#commit
echo "Commit ref-doc/$1."
git config user.email "yvan.lussaud@obeo.fr"
git config user.name "Yvan Lussaud"
git add _data/pages.yml
git add ref-doc/$1
git commit -m "Integration promoting web site for $1. ($TRAVIS_COMMIT)"
git push origin gh-pages
