#!/bin/bash
set -euo pipefail

main() {
  ./upload-apps.sh apps
  mvn test
}

main "$@"
