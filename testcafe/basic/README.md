# Basic TestCafe Example

This example demonstrates how to run TestCafe tests with saucectl and Sauce Orchestrate.

⚠️ **The provider plugin is not maintained by Sauce Labs and is currently
experiencing a variety of issues. However, we'd love to [hear from you](devx@saucelabs.com)
if you'd like us to provide a first party plugin instead.**

## Setup

### Install saucectl

`saucectl` is the command line tool that we'll use to run our tests on Sauce Orchestrate.

The simplest way to install `saucectl` is via npm:

```shell
npm install -g saucectl
```
Alternative installation methods are described [here](https://docs.saucelabs.com/dev/cli/saucectl/#installing-saucectl).

### Set your Sauce Labs credentials

```shell
saucectl configure
```

## Usage 

```shell
saucectl run --config .sauce/config.yml
```

The `saucectl run` command will execute the suites defined in the config file specified by the `--config` flag.

### In-depth

The [config file](./.sauce/config.yml) defines the docker image to run, in this case it is an image already published to DockerHub called `saucelabs/imagerunner-testcafe-basic-example:latest`. That image is defined by the Dockerfile [here](./Dockerfile) and contains our [test code](./tests), our [testcafe config](./.testcaferc.js), the latest version of testcafe, and the browsers we can test against (chromium and firefox).

When the `saucectl run` command is executed, the docker image defined in the saucectl config will be run in the Sauce Labs cloud which will execute the tests and report the results to Sauce Labs using the [Sauce Labs TestCafe Reporter](https://www.github.com/saucelabs/testcafe-reporter).

## Next steps

### Run the example locally

Since Sauce Orchestrate utilizes containers for execution, you can build and run the example image locally.

```shell
docker build . --tag testcafe-basic
docker run testcafe-basic
```

### Run your own tests

To run your own tests, you can modify the Dockerfile used to build the image to include your own tests instead of the example tests:

```Dockerfile
ADD /local/directory/to/your/tests /app/tests/
ADD /local/directory/to/your/.testcaferc.js /app/
```

Build your image:

```shell
docker build . --tag testcafe-local
```

And run it locally:

```shell
docker run testcafe-local
```

To run your own tests on Sauce Orchestrate, you'll have to publish your new image to a Docker registry, like [DockerHub](https://hub.docker.com), your company provided registry, or to the [Sauce Labs Container Registry](https://docs.saucelabs.com/orchestrate/saucelabs-private-registry/).

Once published, you need to update the image defined in the saucectl config file to your new image.

### saucectl configuration

Additional functionality can be included in the saucectl config file. Detailed configuration options are described in our [docs](https://docs.saucelabs.com/orchestrate/saucectl-configuration/).
