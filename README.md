# http4k-koin-poc

Run:

`$ ./gradlew run`

Try it out:

`$ curl -X POST --location "http://localhost:8080/pets" -H "Content-Type: application/json" -d "{\"id\":  1, \"name\" :  \"Colin\"}"`

`$ curl http://localhost:8080/pets | jq`

`$ curl -X POST --location "http://localhost:8080/pets" -H "Content-Type: application/json" -d "{\"id\":  2, \"name\" :  \"Fido\"}"`

`$ curl http://localhost:8080/pets | jq`

`$ curl http://localhost:8080/pets/2 | jq`