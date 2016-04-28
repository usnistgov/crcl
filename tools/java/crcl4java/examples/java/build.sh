#!/bin/sh

set -x;

jarfile="crcl4java-utils-1.3-jar-with-dependencies.jar"
if ! test -f "${jarfile}" ; then
    remotejarurl="https://raw.github.com/usnistgov/crcl/mvn-repo/com/github/wshackle/crcl4java-utils/1.3/crcl4java-utils-1.3-jar-with-dependencies.jar";
    echo "Downloading ${remotejarurl}";
    wget "${remotejarurl}"
fi

CRCL4JAVA_UTILS_JAR=`ls -1t crcl4java-utils*.jar | head -n 1`

javac -cp "${CRCL4JAVA_UTILS_JAR}" CRCLClient.java CRCLServer.java
