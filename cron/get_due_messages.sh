#/bin/bash
TOKEN_URL=${KEYCLOAK_AUTH_URL}/realms/${KEYCLOAK_REALM}/protocol/openid-connect/token
TOKEN=$(node -pe 'JSON.parse(process.argv[1]).access_token' "$(curl --insecure --location --request POST $TOKEN_URL --header 'Content-Type: application/x-www-form-urlencoded' --data-urlencode 'client_id=bocraportal-cron' --data-urlencode 'client_secret=bd7M5xC4SQbJf6pvHzEe5mFzzAWapmmk' --data-urlencode 'scope=email' --data-urlencode 'grant_type=client_credentials')")

DUE=${COMM_URL}/messages/due
HEADER="Authorization: Bearer $TOKEN"
curl --location --request GET "${DUE}" --header "$HEADER"