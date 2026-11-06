@echo off
REM Builds and launches Team Dream Cafe on Windows. Requires JDK 17+ on PATH.
cd /d "%~dp0"

if not exist out mkdir out
dir /s /b src\main\java\*.java > build-sources.txt
javac -encoding UTF-8 -d out @build-sources.txt
if errorlevel 1 goto :error

java -cp out com.teamdreamcafe.App
goto :eof

:error
echo.
echo Build failed. Make sure a JDK (17 or newer) is installed and on PATH.
pause
