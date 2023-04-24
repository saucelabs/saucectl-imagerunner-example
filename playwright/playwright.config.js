const { defineConfig } = require('@playwright/test');

module.exports = defineConfig({
  outputDir: 'artifacts/test-results',
  use: {
    video: 'on',
  },
  reporter: [
    ['line'],
    ['@saucelabs/playwright-reporter', { upload: true }],
  ],
});