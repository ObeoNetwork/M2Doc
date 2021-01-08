#!/bin/sh
sudo add-apt-repository ppa:brightbox/ruby-ng -y
sudo apt-get update
sudo apt-get install ruby-dev ruby-full
sudo gem install addressable --version '= 2.4.0'
sudo gem install jekyll -v 3.8.6
sudo gem install jekyll-paginate
sudo gem install jekyll-gist
sudo gem install pygments.rb
sudo gem install redcarpet
sudo gem install RedCloth

