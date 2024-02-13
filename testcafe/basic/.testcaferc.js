module.exports = {
  browsers: ['chromium'],
  src: ['/app/tests/**/*.test.js'],
  reporter: [
    { name: 'saucelabs' },
    { 
      name: 'xunit',
      output: '/test-results/report.xml',
    },
  ],
};
