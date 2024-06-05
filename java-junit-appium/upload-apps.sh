#!/bin/bash
set -euo pipefail

check_command() {
  if ! command -v "$1" &> /dev/null; then
    echo "Error: $1 is not installed." >&2
    exit 1
  fi
}

check_command "curl"

upload_file() {
  local file="$1"

  echo "Uploading $file ..."
  echo

  curl --silent \
   --output /dev/null \
   --show-error \
   --user "$SAUCE_USERNAME:$SAUCE_ACCESS_KEY" \
   --location \
   --request POST "$SAUCE_REST_ENDPOINT/v1/storage/upload" \
   --form "payload=@\"$file\""
}

upload_all_files() {
  local dir="$1"
  local file_list
  file_list=$(find "$dir" -type f)

  while IFS= read -r file; do
      upload_file "$file"
  done <<< "$file_list"
}

main() {
   if [ "$#" -ne 1 ]; then
      echo "Usage: $0 <apps_dir>" >&2
      exit 1
   fi

  upload_all_files "$1"
}

main "$@"
