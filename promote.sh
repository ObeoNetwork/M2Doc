#!/bin/sh

# $1 the version
# $2 the commit on master

# add document page to menu
echo "Create entry $1 in the ref-doc menu."
sed -i "s+#---TAG---+#---TAG---\n  - title: $1\n    file: ref-doc/$1/index+g" _data/pages.yml


# reference the current version in the toc
echo "Change nightly to $1 in ref-doc/index-toc.xml."
sed -i "s+nightly+$1+g" ref-doc/index-toc.xml
echo $?

# initialize documentation
echo "Copy documentation to ref-doc/$1."
cp -Rf ref-doc/nightly/ ref-doc/$1
echo $?

#update the documentation
echo "Make other replacements in ref-doc/$1/index.md."
sed -i "s+nightly+$1+g" ref-doc/$1/*.md
echo $?
sed -i "s+Nightly+$1+g" ref-doc/$1/*.md
echo $?
sed -i "s+master+$1+g" ref-doc/$1/*.md
echo $?

if [ ! -z $2 ]; then
  #commit
  echo "Commit ref-doc/$1."
  git config user.email "yvan.lussaud@obeo.fr"
  echo $?
  git config user.name "Yvan Lussaud"
  echo $?
  git add _data/pages.yml
  echo $?
  git add ref-doc/$1
  echo $?
  git commit -m "Integration promoting web site for $1. ($2)"
  echo $?
  git push origin gh-pages
  echo $?
fi
