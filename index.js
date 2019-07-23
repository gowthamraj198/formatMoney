var phantomcss = require('phantomcss');
var x = require("casper").selectXPath;

casper.test.begin('Tags', function(test) {

    phantomcss.init({
        rebase: casper.cli.get('rebase')
    });

    casper.start('http://localhost:7238/home.html');

    casper.viewport(800, 1024);

    casper.then(function() {
        phantomcss.screenshot('body', 'body');
    });

    casper.then(function now_check_the_screenshots() {
        phantomcss.compareAll();
    });

    // run tests
    casper.run(function() {
        console.log('\nTHE END.');
        casper.test.done();
    });
});

casper.test.begin('Tags', function(test) {

    phantomcss.init({
        rebase: casper.cli.get('rebase')
    });

    casper.start('http://localhost:7238/getmoney');
    casper.viewport(1000, 1000);

    casper.then(function() {
        phantomcss.screenshot('body', 'body');
    });

    casper.then(function now_check_the_screenshots() {
        phantomcss.compareAll();
    });

    // run tests
    casper.run(function() {
        console.log('\nTHE END.');
        casper.test.done();
    });
});

