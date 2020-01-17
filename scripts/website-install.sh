#!/bin/sh
sudo add-apt-repository ppa:brightbox/ruby-ng -y
sudo apt-get update
sudo apt-get install ruby-dev ruby-full
gem install addressable --version '= 2.4.0'
gem install jekyll -v 3.8.6
gem install jekyll-paginate
gem install jekyll-gist
gem install pygments.rb
gem install redcarpet
gem install RedCloth

