#!/usr/bin/env bash
# Builds (if needed) and launches Team Dream Cafe.
set -euo pipefail
cd "$(dirname "$0")"

if [ ! -d out ] || [ -z "$(find out -name '*.class' -print -quit)" ]; then
  ./build.sh
fi

java -cp out com.teamdreamcafe.App
