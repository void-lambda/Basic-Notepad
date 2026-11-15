#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

PROJECT_NAME="Notepad"
MAIN_CLASS="src.GUI"

echo "[1/4] Cleaning..."
rm -rf out "$PROJECT_NAME.jar"

echo "[2/4] Compiling..."
mkdir -p out
javac -d out $(find src -name "*.java")

echo "[3/4] Copying resourses..."
if [ -f "src/icon.png" ]; then
    cp src/icon.png out/
fi

echo "[4/4] Packaging JAR..."
jar cfe "$PROJECT_NAME.jar" "$MAIN_CLASS" -C out .

echo ""
echo "Done: $PROJECT_NAME.jar ($(du -h "$PROJECT_NAME.jar" | cut -f1))"
echo "Run:  java -jar $PROJECT_NAME.jar"