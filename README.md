# debug-mafia-demo

To run locally, ensure you have `docker`, and `git` installed.

Next, at the root of the repo, run `docker-compose up --build`.

The API will be running on `localhost:8080`, and the UI will be running on `localhost:3000`.

And that's it! Visit the two above urls (the UI url is more exciting) to see the two deployed apps.

## About

There's two apps in this repo: `api` and `ui` (in those respective directories). 

`api` is a [Spring Boot](http://spring.io/projects/spring-boot) application, currently returning a small JSON response at `/api/test`.

`ui` is a [React](https://reactjs.org/) application, which currently displays a simple UI, and when clicking a button, will make a request to our API to get some test data. The request is proxied to the API.

Both apps are **Dockerized**. There's a `Dockerfile` in each apps directory, and this file is responsible for defining the OS that our app runs on, the application build, and the script/command that gets executed to start or run the app.

In order to run these apps in a single sweeping command, we use `docker-compose`, which is a docker utility that allows us to define how to _orchestrate_ our containers. The `docker-compose.yml` file defines how our two containers (also called services) should be run/deployed/configured. When we run `docker-compose up`, docker will look for that YAML file, find the defined `services`, and try to deploy them.

