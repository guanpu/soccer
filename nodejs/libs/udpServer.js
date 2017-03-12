/* 
 * An UDP server to report fetch result to JVM server.
 */

const dgram = require("dgram");
const client = dgram.createSocket('udp4');
const SERVER_PORT = require("../config/config.json").peer.port;
var process = require("process");
var log4js = require('log4js');
var logger;

if(process.env.PRO_SOCCER) {
    log4js.configure("./config/log4js-config");
    logger = log4js.getLogger("production");
} else {
    logger = log4js.getLogger();
}

function reportMatch(string) {
    client.send(string, SERVER_PORT,"localhost", function(){
        logger.info(string + " has being send");
    });
}
function reportResult(string) {
    client.send(string, SERVER_PORT, "localhost",function(){
        logger.info(string + " has being send");
    });    
}
function terminate() {
    client.close();
}
module.exports = {
    reportMatch: reportMatch,
    reportResult: reportResult,
    terminate: terminate
};
