/* 
 * Entrance of soccer node module.
 */
var orc = require("./libs/orchestractor");
var process = require("process");
var log4js = require('log4js');
var logger;

if(process.env.PRO_SOCCER) {
    log4js.configure("./config/log4js-config");
    logger = log4js.getLogger("production");
} else {
    logger = log4js.getLogger();
}
logger.info("started");
orc.fetchOdd();
