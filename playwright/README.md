# saucectl imagerunner example

Example running Playwright on ImageRunner with `saucectl`.

## What You'll Need

The steps below illustrate one of the quickest ways to get set up. If you'd like a more in-depth guide, please check out
our [documentation](https://docs.saucelabs.com/dev/cli/saucectl/#installing-saucectl).

*If you're using VS Code, you can use [Runme](https://marketplace.visualstudio.com/items?itemName=stateful.runme) to run the following commands directly from VS Code.*

### Prerequisites

If you want to be able to modify and rebuild the docker image, you need to have [Docker](https://docs.docker.com/engine/install/) installed.

### Install `saucectl`

```shell
npm install -g saucectl
```

### Set Your Sauce Labs Credentials

```shell
saucectl configure
```

### Running The Examples

```shell
saucectl run
``` 
![running example](../assets/playwright-example.gif)

Running saucectl in US and EU

```bash
saucectl run --region us-west-1
```

```bash
saucectl run --region eu-central-1
```

## The Config

[Follow me](.sauce/config.yml) if you'd like to see how `saucectl` is configured for this repository.

Our IDE Integrations (e.g. [Visual Studio Code](https://docs.saucelabs.com/dev/cli/saucectl/usage/ide/vscode)) can help you out by validating the YAML files and provide handy suggestions, so make sure to check them out!

## Customization

If you want to modify the way the docker image is built, the [Dockerfile is here](Dockerfile).

After modifying the image, you will have to publish it to your own registry, and modify [.sauce/config.yml](.sauce/config.yml) accordingly, to use your own image.

The image to be used is defined with the [`image:` field](.sauce/config.yml#L7), under the `suites` category.
