#!/bin/bash

# Directory where logs are stored
LOG_DIR="logs"

# Encryption command using OpenSSL
# Replace 'yourpassword' with a strong password or use a more secure key management method
for LOG_FILE in "$LOG_DIR"/*.log
do
  if [ -f "$LOG_FILE" ]; then
    openssl enc -aes-256-cbc -salt -in "$LOG_FILE" -out "${LOG_FILE}.enc" -pass pass:yourpassword
    if [ $? -eq 0 ]; then
      rm "$LOG_FILE"  # Remove the unencrypted log file after successful encryption
    fi
  fi
done
