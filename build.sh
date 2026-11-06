#!/usr/bin/env bash
# Compiles Team Dream Cafe from source into ./out using only the JDK.
set -euo pipefail
cd "$(dirname "$0")"

mkdir -p out
find src/main/java -name "*.java" > build-sources.txt

javac -encoding UTF-8 -Xlint:all,-serial -d out @build-sources.txt
echo "Build OK -> out/"
