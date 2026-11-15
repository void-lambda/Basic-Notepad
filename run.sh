#!/usr/bin/env bash
cd "$(dirname "$0")"
if ! command -v java >/dev/null 2>&1; then
    echo "Java 17+ is required: https://adoptium.net/temurin/releases/?version=17"
    exit 1
fi
java -jar Notepad.jar