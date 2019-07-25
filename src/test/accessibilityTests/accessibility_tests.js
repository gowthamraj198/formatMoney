var selenium = require('selenium-webdriver'),
    AxeBuilder = require('axe-webdriverjs');

jasmine.DEFAULT_TIMEOUT_INTERVAL = 60000;

describe('Accessibility', function() {
    var driver;

    beforeEach(function(done) {
        driver = new selenium.Builder()
            .forBrowser('chrome')
            .build();

        driver.get('https://localhost:7338/home.html')
            .then(function () {
                done();
            });
    });

    // Close website after each test is run (so it is opened fresh each time)
    afterEach(function(done) {
        driver.quit().then(function () {
            done();
        });
    });

    it('should analyze the home page with aXe', function (done) {
        AxeBuilder(driver)
            .analyze(function(results) {
                console.log('Accessibility Violations: ', results.violations.length);
                if (results.violations.length > 0) {
                    console.log(results.violations);
                }
                expect(results.violations.length).toBe(0);
                done();
            })
    });

    it('should analyze the output page with aXe', function (done) {

        var money = 'input[name="money"]';
        var submit = '#submit';

            driver.findElement(selenium.By.css(money))
                .then(function (element) {
                    element.sendKeys("123");
                });
        driver.findElement(selenium.By.css(submit))
            .then(function (element) {
                element.click();
            });

        AxeBuilder(driver)
            .analyze(function(results) {
                console.log('Accessibility Violations: ', results.violations.length);
                if (results.violations.length > 0) {
                    console.log(results.violations);
                }
                expect(results.violations.length).toBe(0);
                done();
            })
    });

    it('should analyze the error page with aXe', function (done) {

        var submit = '#submit';

        driver.findElement(selenium.By.css(submit))
            .then(function (element) {
                element.click();
            });

        AxeBuilder(driver)
            .analyze(function(results) {
                console.log('Accessibility Violations: ', results.violations.length);
                if (results.violations.length > 0) {
                    console.log(results.violations);
                }
                expect(results.violations.length).toBe(0);
                done();
            })
    });
});