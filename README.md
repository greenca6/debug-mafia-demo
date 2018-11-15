# Getting Started

To run locally, ensure you have `docker`, and `git` installed.

Next, at the root of the repo, run `docker-compose up --build`.

The API will be running on `localhost:8080`, and the UI will be running on `localhost:3000`.

And that's it! Visit the two above urls (the UI url is more exciting) to see the two deployed apps.

## Live/Hot Reloading
The application is about 95% of the way configured to allow for file changes to be automatically reloaded in the respected applications. 

A change to any file within the UI app (`ui/`) will trigger a rebuild of that app, and also will refresh the browser when the rebuild is complete. You make a change, and your browser refreshes. No clicks! Pretty amazing huh?

The API is only slightly less easy. The Spring Boot/Gradle app is configured to restart the server when anything on the `classpath` changes, but nothing will actually rebuild the code when you make a change to a file. To get around this small hiccup, run the following commands:

```bash
# Run the app as usual
$ docker-compose up --build
```

**Wait for the app to get up and running before continuing.** You have to run this step first before continuing. It won't work if you do the next command first.

Then, in a **separate** terminal, run:
```bash
# Run the continuous gradle build locally, outside of docker
$ cd ./api && ./gradlew build --continuous --quiet
```

This essentially tells Gradle to watch for file changes, and do a rebuild whenever there is a file change. If you change a file, then Gradle will rebuild, then the running `bootRun` process in the docker container will then detect that there's been a change to a file in the `classpath`, thus restarting the application. It's a not-so-perfect workaround. [Here's](https://stackoverflow.com/a/41878387/9257823) where I found this out if you're curious what's going on at a deeper level.

# About
See the [About](https://github.com/greenca6/debug-mafia-demo/blob/master/ABOUT.md) page for more information on this application.
