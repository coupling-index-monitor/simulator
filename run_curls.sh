#!/bin/bash

# Start the timer
echo "Executing curls repeatedly for 5 seconds..."
SECONDS=0  # Built-in variable to track time

# Define an array of curl commands
curl_commands=(
    "curl -X GET http://167.172.83.56:9090/service-a/path1"
    "curl -X GET http://167.172.83.56:9091/service-b/path1"
    "curl -X GET http://167.172.83.56:9091/service-b/path2"
    "curl -X GET http://167.172.83.56:9092/service-c/path1"
    "curl -X GET http://167.172.83.56:9092/service-c/path2"
    "curl -X GET http://167.172.83.56:9093/service-d/path1"
    "curl -X GET http://167.172.83.56:9093/service-d/path2"
    "curl -X GET http://167.172.83.56:9094/service-e/path1"
    "curl -X GET http://167.172.83.56:9094/service-e/path2"
    "curl -X GET http://167.172.83.56:9095/service-f/path1"
    "curl -X GET http://167.172.83.56:9096/service-g/path1"
    "curl -X GET http://167.172.83.56:9097/service-h/path1"
    "curl -X GET http://167.172.83.56:9098/service-i/path1"
    "curl -X GET http://167.172.83.56:9099/service-j/path1"
)

# Repeat execution until 5 seconds have passed
while [[ $SECONDS -lt 5 ]]; do
    for cmd in "${curl_commands[@]}"; do
        echo -e "Executing: $cmd"
        eval "$cmd"
	echo -e "\n\n"

        # Check if 5 seconds have passed after each command
        if [[ $SECONDS -ge 5 ]]; then
            echo -e "\nTime limit reached. Stopping script.\n"
            exit 0
        fi
    done
done

