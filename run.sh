#!/bin/bash

./gradlew build   -Dorg.gradle.java.home="/Users/RishiVeerepalli/frc2019/jdk"
./gradlew deploy  -PteamNumber=1128 --offline  -Dorg.gradle.java.home="/Users/RishiVeerepalli/frc2019/jdk"
