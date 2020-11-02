SET JM_LAUNCH="..\..\jdk-15.0.1\bin\java.exe"
SET JAVAVER="15.0.1"
cd apache-jmeter-5.3\bin\
jmeter.bat -n -t "..\..\05 ConfigurationTest backend listener cli exec.jmx" -l "..\..\reports\report-%date%-%time::=-%.log" -e -o "..\..\reports\report-%date%-%time::=-%"
pause
rem ./jmeter.sh -n -t ELKTest.jmx  -l "..\..\reports\$(date -d "today" +"%%Y%%m%%d_%%H%%M%%S").log"  -e -o reports/$(date -d "today" +"%%Y%%m%%d_%%H%%M%%S")
