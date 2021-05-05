# spring-boot
## Technical:

1. Framework: Spring Boot v2.2.7
2. Java 8
3. Docker

## Build app

Do the following commands in each folders (Patient, Note, Report)

```mvn 
mvn clean install
```

## Build image with Docker

After all jar compiled, do the following commands to build docker image for (Patient, Note, Report)

```Powershell commands
cd Patient
docker build -t patient .
cd ../Note 
docker build -t note .
cd ../Report
docker build -t report .

cd ..
docker-compose up
```


## Run app with Docker

Go to the root of the folder, where there is docker-compose.yml
Make sure you are logged in

```Powershell commands
docker login

cd ..
docker-compose up
```


## Access page
Make sure all docker containers are up and running then go to :
http://localhost:9030/patient/list 

## Data example
Copy paste content from "data.txt" in a command windows to add data