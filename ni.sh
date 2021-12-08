#!/usr/bin/env bash
APP_NAME="$1*jar"
LOG=$HOME/Desktop/log.md
mvn -DskipTests=true -Pnative clean package && \
 mvn -DskipTests=true package  
OUTPUT=$HOME/Desktop/output.log

function reset(){
	pkill -9 batch traditional reactive 
	pkill -9 java
}

echo "---------------------------" >> $LOG 
echo "$1" >> $LOG 

cd target 

reset 
echo "native" >> $LOG
PID=0
./$1 > $OUTPUT  & PID=$!   
sleep 5
cat $OUTPUT | grep "in\s.*\sseconds"   | awk -F 'in ' '{print $2}'  | awk -F ' seconds' '{print $1}' >> $LOG 
$HOME/josh-env/bin/rss.sh $PID >> $LOG 
echo >> $LOG

reset 
echo "jre" >> $LOG
PID=0
java -jar $APP_NAME > $OUTPUT  & PID=$!   
sleep 5
cat $OUTPUT | grep "in\s.*\sseconds"   | awk -F 'in ' '{print $2}'  | awk -F ' seconds' '{print $1}' >> $LOG 
$HOME/josh-env/bin/rss.sh $PID >> $LOG 
echo >> $LOG

reset 
echo "aot" >> $LOG
PID=0
java -DspringAot=true -jar $APP_NAME > $OUTPUT  & PID=$!   
sleep 5
cat $OUTPUT | grep "in\s.*\sseconds"   | awk -F 'in ' '{print $2}'  | awk -F ' seconds' '{print $1}' >> $LOG 
$HOME/josh-env/bin/rss.sh $PID >> $LOG 
echo >> $LOG


