#!/bin/bash
# This script will generate necessary file and directories for Project SetMe
# Run this script before doing anything

#Install required packages
sudo apt-get install gksu
#Making script executable.
chmod a+x enable.sh;
chmod a+ disable.sh;
chmod a+x allServices.sh;

# Setting up directories
mkdir /home/$USER/.setMe
cp  enable.sh /home/$USER/.setMe 
cp disable.sh /home/$USER/.setMe
cp allServices.sh /home/$USER/.setMe

echo " ITS DONE."
exit 0;
