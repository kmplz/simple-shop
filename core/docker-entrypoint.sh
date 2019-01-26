#! /bin/sh

java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:+UseG1GC -XX:+ExitOnOutOfMemoryError \
    -Dspring.profiles.active=$PROFILES \
    -jar app.jar