#! /usr/bin/env sh
UPDATE_SITE_DIR=releng/org.obeonetwork.m2doc.update/target/repository
PRODUCT_DIR=releng/org.obeonetwork.m2doc.update/target/products
DEPLOY_LOCAL_DIR=$1/repository
echo "Prepare deploy local dir = ${DEPLOY_LOCAL_DIR}"
# Create deploy local dir
mkdir -p $DEPLOY_LOCAL_DIR 
# Copy update-site to deploy local dir
cp -r $UPDATE_SITE_DIR/* $DEPLOY_LOCAL_DIR
# Copy product to deploy local dir
cp $PRODUCT_DIR/m2doc*.z* $DEPLOY_LOCAL_DIR
echo "find ${DEPLOY_LOCAL_DIR}"
find $DEPLOY_LOCAL_DIR
