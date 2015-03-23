#!/bin/sh

./gradlew clean || exit 1
./gradlew build || exit 1
./gradlew generatePomFileForMavenJavaPublication || exit 1
./gradlew preparePublication publish || exit 1
