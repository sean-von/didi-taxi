#!/bin/bash
nohup mvn clean package -Djetty.port=8101 jetty:run &
