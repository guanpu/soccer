/**
 * Orchestrating functions to work together
 * @type {exports}
 */
const schedule = require("node-schedule");
const oddFetcher = require("./oddFetcher");
function fetchOdd() {
	oddFetcher.runOnce();
}

module.exports = {
	fetchOdd: fetchOdd
};