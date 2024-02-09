FROM testcafe/testcafe

WORKDIR /app

ADD tests /app/tests/
ADD .testcaferc.js /app/

RUN npm i testcafe-reporter-saucelabs
