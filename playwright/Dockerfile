FROM mcr.microsoft.com/playwright:v1.32.1

WORKDIR /app

ADD package.json /app/
ADD package-lock.json /app/

RUN npm ci --omit=dev
RUN npx playwright install-deps

ADD tests /app/tests/
ADD playwright.config.js /app/

ENTRYPOINT ["npx", "playwright", "test"]
