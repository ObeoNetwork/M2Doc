#!/bin/sh

wget https://github.com/mozilla/geckodriver/releases/download/v0.28.0/geckodriver-v0.28.0-linux64.tar.gz
tar xfz geckodriver-v0.28.0-linux64.tar.gz
sudo mv geckodriver /usr/local/bin/
sudo chmod +x /usr/local/bin/geckodriver

cd add-in
sudo npm i npm@latest -g
sudo npm install
cd -
