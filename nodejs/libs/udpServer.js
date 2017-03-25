/* 
 * An UDP server to report fetch result to JVM server.
 */

const dgram = require("dgram");
const client = dgram.createSocket('udp4');
const SERVER_PORT = require("../config/config.json").peer.port;
const logger = require("../config/logger").logger;

function report(string) {
    client.send(string, SERVER_PORT,"localhost", function(){
        logger.info("successfully sent");
    });
}

function terminate() {
    logger.info("aboutToClose");
    client.close();
    process.disconnect();
}

process.on("message", (message)=>{
    if (message.eventType) {
        let toBroadcast = JSON.stringify(message);
        logger.info(toBroadcast);
        report(toBroadcast);
    } else {
        terminate();
    }
});
process.on("exit",()=>{
    console.log("Child closeing");
});