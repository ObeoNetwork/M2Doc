set -e
if [ $TRAVIS_PULL_REQUEST == 'false' ]
then
echo "Build $TRAVIS_JOB_NUMBER"
echo "Git: $TRAVIS_COMMIT [$TRAVIS_BRANCH]"
echo "Root dir: $TRAVIS_BUILD_DIR"
cd $TRAVIS_BUILD_DIR
echo "Promote: $UPDATE_SITES"
echo "Build promoted."
fi
