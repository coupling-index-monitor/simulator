import subprocess
import time
from datetime import datetime
import threading

curl_commands = [
    "curl -X GET http://localhost:9090/service-a/path1",
    "curl -X GET http://localhost:9091/service-b/path1",
    "curl -X GET http://localhost:9091/service-b/path2",
    "curl -X GET http://localhost:9092/service-c/path1",
    "curl -X GET http://localhost:9092/service-c/path2",
    "curl -X GET http://localhost:9093/service-d/path1",
    "curl -X GET http://localhost:9093/service-d/path2",
    "curl -X GET http://localhost:9094/service-e/path1",
    "curl -X GET http://localhost:9094/service-e/path2",
    "curl -X GET http://localhost:9095/service-f/path1",
    "curl -X GET http://localhost:9096/service-g/path1",
    "curl -X GET http://localhost:9097/service-h/path1",
    "curl -X GET http://localhost:9098/service-i/path1",
    "curl -X GET http://localhost:9099/service-j/path1"
]

intervals = [10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80]


def execute_and_log(command, interval):
    while True:
        timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        try:
            result = subprocess.run(command, shell=True, capture_output=True, text=True)
            output = result.stdout
            error = result.stderr
            print(f"[{timestamp}] Command: {command}")
            print(f"[{timestamp}] Output: {output}")
            if error:
                print(f"[{timestamp}] Error: {error}")
        except Exception as e:
            print(f"[{timestamp}] Failed to execute command: {command}")
            print(f"Error: {str(e)}")
        time.sleep(interval)


print("Sending initial round of all requests...")
initial_threads = []
for command in curl_commands:
    thread = threading.Thread(target=execute_and_log, args=(command, 0))
    initial_threads.append(thread)
    thread.start()

for thread in initial_threads:
    thread.join()

print("Initial round completed. Starting periodic requests at different intervals.")

for command, interval in zip(curl_commands, intervals):
    threading.Thread(target=execute_and_log, args=(command, interval)).start()
