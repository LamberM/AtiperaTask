# AtiperaTask
This task was implemented using Spring

## To start server:
#### 1) Using Docker
* docker build --tag 'atipera'.
* docker run atipera
#### 2) Using IntelliJ Idea
* go to src/main/java/org/lamberm/AtiperaTask/AtiperaTaskApplication.java
* run 'App'

Server will start at http://localhost:8080/
## Available endpoints:
### /github/users/{userName}/repos
This endpoind will provide repository name,owner login and for each branch it’s name and last commit sha.
* field {userName} - github user name.
* Will return 404 when user does not exist
## Response codes

#### 200 OK - when operation is successful
#### 404 Not Found - when user does not exist
#### 500 Internal Server Error
#### 503 Service Unavailable - if exceed limit of requests
## Examples:
1) userName = LamberM
URL:
GET http://localhost:8080/github/users/LamberM/repos

response code:

200 OK

response body:

[{"repositoryName":"AtiperaTask","ownerLogin":{"login":"LamberM"},"branchDetailsResponses":[{"name":"main","commit":{"sha":"5057d743db4c2df5eb2f4b7603b1151895064dea"}}]},{"repositoryName":"DynatraceIntern","ownerLogin":{"login":"LamberM"},"branchDetailsResponses":[{"name":"master","commit":{"sha":"d01a3bfa4345000bde6917e474ceb963f278cc93"}}]},{"repositoryName":"Horus","ownerLogin":{"login":"LamberM"},"branchDetailsResponses":[{"name":"master","commit":{"sha":"c7f37b8b0b214d8d199be451142dbc43b156a5eb"}}]},{"repositoryName":"RPG","ownerLogin":{"login":"LamberM"},"branchDetailsResponses":[{"name":"development","commit":{"sha":"db4b07bc8fe0dd28c71bca4fc14ac9bc9f362037"}},{"name":"master","commit":{"sha":"b5a151e9283d716faf575dd19f3b530b1b35b868"}},{"name":"refactor","commit":{"sha":"5b698314684aea285c51f251fce215bd5c695593"}}]},{"repositoryName":"School","ownerLogin":{"login":"LamberM"},"branchDetailsResponses":[{"name":"develop","commit":{"sha":"107cb7acfb7b336f951bd5dd69b5b4e38d881fb8"}},{"name":"feature/createClasses","commit":{"sha":"ba13327af340e36833cee9158b9e26c346f3d14b"}},{"name":"feature/createIntegrationTest","commit":{"sha":"2b160dd9cbd1e3b86ccd8698f913a691b38b58a9"}},{"name":"feature/createProfiles","commit":{"sha":"7803f10c3bddf7b1729f1fcdaf2ce3a29267b48d"}},{"name":"feature/createSwaggerAnnotations","commit":{"sha":"d0453cc99820a02512e133740aa77e38513a7c54"}},{"name":"fix/improveValidationsRepositoryAndTest","commit":{"sha":"7de9ee2ef6246ad184456ad7372033d5cd3cf9d8"}},{"name":"master","commit":{"sha":"497200c84c75b8e365ea37612b47ca96b649c4ff"}}]}]

2)userName = test2312312

URL:
GET http://localhost:8080/github/users/test2312312/repos

response code:

404 Not Found

response body:

{"status":404,"message":"Github user does not exist"}