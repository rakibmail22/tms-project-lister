# tms-project-lister

## Features
1. Only one config can be saved.
2. The config can be updated
3. There is a validation in place if a user try to save multiple config (bypassing front end)
4. Assumed there's no rate limit of calling tms api for project list, therefore, delegated
request directly from this service without maintaining any queue (for throttling).

## How To Run
##### Run With gradle (Dev Profile) :: Pre Requisite: jdk 17
1. navigate to repository root
2. Run the following command from terminal `./gradlew bootRun  --args='--spring.profiles.active=dev'`


##### Run With gradle (Prod Profile) :: Pre Requisite: Docker
1. navigate to repository root
2. Make the following scripts executable
   `chmod +x dockerBuild.sh`, `chmod +x runProd.sh`
3. Build docker image `./dockerBuild.sh`
4. Run docker image `./runProd.sh`
5. The app can be accessed in the following url http://localhost:8080/

##### Application Architecture
1. Inside `com.tms.project.dashboard` all the view rendering related logics and controllers are located
2. Inside `com.tms.project.api` there are two end points
   1. `ConfigEndpoint` - responsible to save update credential config
   2. `ListProjectEndpoint` - responsible to fetch project list from tms application using the config
3. Inside `com.tms.project.tmsclient` - code for communicating to external endpoints are located
4. The configs are saved and updated with Rest Endpoint, the front end is handled with
jQuery Ajax call.
5. Inside `setup.html` and `project-list.html` corresponding js code can be found
6. For now two sample test classes are added to do unit test for `TmsUserConfigConverter` and `ConfigService`

##### Application Architecture
###### Phase 1 (Time Taken: 1:00 hour)
1. Navigate the api end point for tms server
2. Identify necessary endpoints and perform mock request with Postman

###### Phase 2 (Time Taken: 1:30 hour)
1. Create project setup with spring boot, base project package structure
2. Add dashboard controllers and basic html view
3. Add ui layout libraries with thymeleaf templating (bootstrap, jQuery)

###### Phase 3 (Time Taken: 2:30 hour)
1. Create API end endpoint for configuration save with h2 db
2. Write some unit test for the config service
3. Integrate api with front end page and create user flow for that

###### Phase 4 (Time Taken: 1:30 hour)
1. Define Feign Client to communicate with external api
2. Develop and refactor project packages to well isolate external api calling code

###### Phase 5 (Time Taken: 2:00 hour)
1. Add project listing endpoint
2. Integrate the endpoint with frontend

###### Last Phase (Time Taken: 1:00 hour)
1. Package restructure
2. Naming refactor
3. Add convenient deployment script with docker