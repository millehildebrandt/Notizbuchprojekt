@echo off
echo Starte H2-Webkonsole...

REM >>> H2-JAR Pfad anpassen, falls du eine andere Version oder Speicherort nutzt
set H2JAR="C:\Users\Win11 Pro\.m2\repository\com\h2database\h2\2.2.224\h2-2.2.224.jar"

REM >>> Starte die Web-Konsole
java -cp %H2JAR% org.h2.tools.Server

pause
