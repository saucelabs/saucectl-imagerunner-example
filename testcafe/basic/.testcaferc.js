module.exports = {
  browsers: ['chromium', 'firefox'],
  src: ['/app/tests/**/*.test.js'],
  reporter: [
    { name: 'saucelabs' },
    { 
      name: 'xunit',
      output: '/app/report.xml',
    },
  ],
};
