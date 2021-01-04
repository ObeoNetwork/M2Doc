#!/bin/sh

wget https://github.com/mozilla/geckodriver/releases/download/v0.20.1/geckodriver-v0.20.1-linux64.tar.gz
tar xfz geckodriver-v0.20.1-linux64.tar.gz
sudo mv geckodriver /usr/local/bin/
sudo chmod +x /usr/local/bin/geckodriver

cd add-in
npm i npm@latest -g
npm install
cd -
