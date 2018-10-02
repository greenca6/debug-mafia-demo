# Getting Started

To run locally, ensure you have `docker`, and `git` installed.

Next, at the root of the repo, run `docker-compose up --build`.

The API will be running on `localhost:8080`, and the UI will be running on `localhost:3000`.

And that's it! Visit the two above urls (the UI url is more exciting) to see the two deployed apps.

# About

There's two apps in this repo: `api` and `ui` (in those respective directories). 

`api` is a [Spring Boot](http://spring.io/projects/spring-boot) application, currently returning a small JSON response at `/api/test`.

`ui` is a [React](https://reactjs.org/) application, which currently displays a simple UI, and when clicking a button, will make a request to our API to get some test data. The request is proxied to the API.

Both apps are **Dockerized**. There's a `Dockerfile` in each apps directory, and this file is responsible for defining the OS that our app runs on, the application build, and the script/command that gets executed to start or run the app.

In order to run these apps in a single sweeping command, we use `docker-compose`, which is a docker utility that allows us to define how to _orchestrate_ our containers. The `docker-compose.yml` file defines how our two containers (also called services) should be run/deployed/configured. When we run `docker-compose up`, docker will look for that YAML file, find the defined `services`, and try to deploy them.

## CI & CD
This repo has been integrated with a few services to allow for continuous integration and continuous deployment. With these services, the only thing you need to worry about as a developer is writing your code and checking it in. The process of getting your changes deployed is entirely automated.

### Master Branch Is Special & Protected
The current configuration is set up so that when a merge to the `master` branch is made, it is automatically deployed to [Heroku](https://www.heroku.com/). However, merges to `master` are [protected](https://help.github.com/articles/about-protected-branches/). At the moment, they are only allowed if the following conditions are met:
- API and UI tests are both passing
- There is at least 1 approved review from a collaborator

If both of these conditions aren't met, then merges to master are not allowed. Read more below on the CI and CD process.

### Continuous Integration
"Continuous Integration" for this repo means that for every commit made, tests are run on a server automatically, without developer intervention. [Travis CI](https://travis-ci.org/) is the integration server that is being used to facilitate the execution of these tests.

The typical workflow is:
- You make your changes locally
- You push it to a new branch (for example, `feature/my-new-shiny-feature`)
- You create a [Pull Request](https://help.github.com/articles/about-pull-requests/) when you think your branch is ready to be merged into `master`
- You tag other developers that you want to have review your changes
- They comment/Approve/Request for Changes
- Travis CI runs tests automatically when opening the PR (and on any subsequent commits), and reports to GitHub the passing/failing status
- Assuming that you have

**On every check-in to any branch**, Travis will run tests on your commit.

### Continuous Deployment
There are technically _two_ applications that are deployed - the Dockerized application inside of the `api/` directory, and the Dockerized application inside of the `ui/` directory.

Here are the deployed URLs for the respective applications:
**API**: https://debug-mafia-api.herokuapp.com
**UI**: https://debug-mafia-ui.herokuapp.com

## A Note About Docker
Everything is facilitated and run through docker.

When you develop locally, you use docker to spin up the application, and facilitate the communication between the API and the UI.

When our tests run within Travis CI, they all run within docker containers (using the same images that we develop locally with!).

When our application gets deployed, it uses docker to build and run our application.

#### Sounds neat, but _why_ use Docker?
A good question. Why introduce this new shiny tool? Why not just do everything as we used to do as software devs - have our source code run straight on an operating system for local development and for deployment? What does docker give us?

Here is a _short_ list of benefits (and the list could probably be longer):
- It allows us to easily control the deployment platform/OS. The `Dockerfile`'s allow us to define what OS/environment our application runs on - and those files can be changed by us inside the repo!
- It ensures that local development is _as close as possible_ to what our deployed code environment looks like. If it works locally, then it's _highly highly_ likely to work in a deployed environment. You can feel confident as a dev (or devops person) that if it works locally or in our tests, then **it's going to work when it gets deployed to a different environment**
- "It works on my machine" syndrome is _very_ unlikely to happen. If we all use the same Docker images, and the same `docker-compose.yml` files - the application environments are going to be identical, no matter what operating system we all use to develop on
- You can get to developing in seconds. No need to install 100's of tools/languages/frameworks to get started developing. You just install `git` and `docker`. The `Dockerfile` contains all the stuff that's required to run the app, which lives in the repo itself.
