module.exports = {
  browsers: ['chromium'],
  src: ['/app/tests/**/*.test.js'],
  reporter: [
    { name: 'saucelabs' },
    { 
      name: 'xunit',
      output: '/app/test-results/report.xml',
    },
  ],
};
