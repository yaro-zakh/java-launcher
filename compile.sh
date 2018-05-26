#!/bin/bash
find src/avaj/simulator/*.java src/avaj/simulator/vehicles/*.java src/avaj/weather/*.java > sources.txt
javac -sourcepath ./src -d . @sources.txt
echo "DONE"