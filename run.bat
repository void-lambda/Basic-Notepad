@echo off
cd /d "%~dp0"
where java >nul 2>nul
if errorlevel 1 (
    echo Java 17+ is required.
    echo Download: https://adoptium.net/temurin/releases/?version=17
    pause
    exit /b 1
)
java -jar Notepad.jar
pause