#!/bin/sh

# TODO: logic here for running in CI v local (should fail locally - only should run via Travis)
# TODO: script to fail if not logged in, or if heroku apps don't exist


# API and UI app
REPO_ROOT=$(pwd)
API_DIR="$REPO_ROOT/api"
UI_DIR="$REPO_ROOT/ui"
API_APP_NAME="debug-mafia-api"
UI_APP_NAME="debug-mafia-ui"

# Deploy the API
echo "Deploying API..."
cd $API_DIR
heroku container:push web -a $API_APP_NAME
heroku container:release web -a $API_APP_NAME

# Deploy the UI
echo "Deploying UI..."
cd $UI_DIR
heroku container:push web -a $UI_APP_NAME
heroku config:set -a $UI_APP_NAME REACT_APP_API="https://$API_APP_NAME.herokuapp.com"
heroku container:release web -a $UI_APP_NAME

echo "Deploy Script Complete"
