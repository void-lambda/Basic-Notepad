#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

PROJECT_NAME="Notepad"
MAIN_CLASS="src.GUI"

echo "[1/3] Cleaning..."
rm -rf out "$PROJECT_NAME.jar"

echo "[2/3] Compiling..."
mkdir -p out
javac -d out $(find src -name "*.java")

echo "[3/3] Packaging JAR..."
jar cfe "$PROJECT_NAME.jar" "$MAIN_CLASS" -C out .

echo ""
echo "Done: $PROJECT_NAME.jar ($(du -h "$PROJECT_NAME.jar" | cut -f1))"
echo "Run:  java -jar $PROJECT_NAME.jar"