@echo off
title Swanhack Game
:A
echo Checking for code update
git pull
pause
echo Starting game
call gradlew run
pause
GOTO :A