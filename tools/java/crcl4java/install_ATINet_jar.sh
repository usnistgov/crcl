#!/bin/bash

set -x;

wget -P /tmp/ https://www.ati-ia.com/Library/software/net_ft/ATINetFT.jar
mvn install:install-file -Dfile=/tmp/ATINetFT.jar -DgroupId=com.ati-ia -DartifactId=ATINetFT -Dversion=1.0 -Dpackaging=jar


