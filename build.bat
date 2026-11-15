@echo off
setlocal
cd /d "%~dp0"

set PROJECT_NAME=Notepad
set MAIN_CLASS=src.GUI

echo [1/4] Cleaning...
if exist out rmdir /s /q out
if exist %PROJECT_NAME%.jar del %PROJECT_NAME%.jar

echo [2/4] Compiling...
mkdir out
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
if errorlevel 1 (
    del sources.txt
    echo.
    echo Compilation failed!
    pause
    exit /b 1
)
del sources.txt

echo [3/4] Copying resources...
if exist src\icon.png copy /y src\icon.png out\icon.png >nul

echo [4/4] Packaging JAR...
jar cfe %PROJECT_NAME%.jar %MAIN_CLASS% -C out .
if errorlevel 1 (
    echo.
    echo JAR creation failed!
    pause
    exit /b 1
)

echo.
echo Done: %PROJECT_NAME%.jar
echo Run:  java -jar %PROJECT_NAME%.jar
pause