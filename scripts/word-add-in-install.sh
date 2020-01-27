#!/bin/sh
sudo add-apt-repository -y -u ppa:ubuntu-mozilla-security/ppa 
sudo apt-get install firefox-geckodriver
cd add-in
npm install
cd -
