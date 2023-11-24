#!/bin/bash

AAPT2_SDK=$(find /opt/android_sdk -name aapt2 -type f | grep 33.0.0)
AAPT2_GRADLE=$(find /root/.gradle -name aapt2 -type f)
#AAPT2_GRADLE=/root/.gradle/caches/transforms-3/4b6f1aa1bbff9735fae3035b9fdf410d/transformed/aapt2-7.3.1-8691043-linux/aapt2

echo $AAPT2_SDK
echo $AAPT2_GRADLE

rm -f $AAPT2_GRADLE
ln -s $AAPT2_SDK $AAPT2_GRADLE

ls -l $AAPT2_GRADLE
