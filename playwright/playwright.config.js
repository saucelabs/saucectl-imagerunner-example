import { defineConfig } from '@playwright/test';

export default defineConfig({
  outputDir: 'artifacts/test-results',
  use: {
    video: 'on',
  },
  reporter: [
    ['line'],
    ['@saucelabs/playwright-reporter', { upload: true }],
  ],
});