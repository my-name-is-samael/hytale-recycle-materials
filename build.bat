@echo off
setlocal
set "PATH=C:\Program Files\Java\jdk-25\bin;%PATH%"
cd /d "%~dp0"
set OUT=build\classes
if exist build rmdir /s /q build
mkdir %OUT%
dir /s /b src\main\java\*.java > build\sources.txt
javac -encoding UTF-8 --release 25 -cp ..\HytaleServer.jar -d %OUT% @build\sources.txt || exit /b 1
xcopy /e /i /q /y src\main\resources %OUT% >nul
jar --create --file build\RecycleMaterials.jar -C %OUT% . || exit /b 1
if /i "%1"=="deploy" (
  del /q ..\Server\mods\RecycleMaterials-*.jar 2>nul
  copy /y build\RecycleMaterials.jar ..\Server\mods\RecycleMaterials.jar >nul
  echo Deployed to ..\Server\mods\RecycleMaterials.jar
)
