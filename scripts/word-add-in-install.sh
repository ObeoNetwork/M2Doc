#!/bin/sh
sudo add-apt-repository ppa:ubuntu-mozilla-security/ppa 
sudo apt-get update
sudo apt-get install firefox-geckodriver
cd add-in
npm install
cd -
