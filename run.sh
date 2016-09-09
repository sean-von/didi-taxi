#!/bin/bash
export MAVEN_OPTS="-Xms2048m -Xmx2048m -XX:PermSize=512M -XX:MaxPermSize=512M -Dfile.encoding=UTF-8"
nohup mvn clean package -Djetty.port=8101 jetty:run &
