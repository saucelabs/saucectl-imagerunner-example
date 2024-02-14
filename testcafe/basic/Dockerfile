FROM testcafe/testcafe:3.5.0

WORKDIR /app

# Add the Sauce Labs reporter to have the test results sent to Sauce for viewing
RUN npm i testcafe-reporter-saucelabs

ADD tests /app/tests/
ADD .testcaferc.js /app/
