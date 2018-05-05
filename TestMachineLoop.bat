@echo off
title Swanhack Game
:A
echo Checking for code update
git fetch
pause
echo Starting game
call gradle run
pause
GOTO :A