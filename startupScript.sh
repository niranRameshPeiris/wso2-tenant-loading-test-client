#!/bin/sh

current_date_time="`date +%Y-%m-%d-%H:%M:%S`";

echo "==========================================================="
echo "================== Starting the script ===================="
echo "==========================================================="
echo " "
echo "Please enter the absolute directory path to the tenants directory."
echo "Example: /Users/niran/Documents/wso2am-3.2.0/repository/tenants"
echo "Enter the root directory : "
read root

if [ -z "$root" ]
then
 echo "Root directory cannot be null or empty."
else
 echo " "
 echo " "
 echo "Logs of the client will be saved to the log/client-$current_date_time.log file"
 echo " "
 echo " "
 java -jar lib/JavaClient.jar $root >> logs/client-$current_date_time.log
fi

echo "==========================================================="
echo "======================= Completed ========================="
echo "==========================================================="