/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const dgram = require("dgram");
const client = dgram.createSocket('udp4');
const SERVER_PORT = 20000;
function reportMatch(string) {
    client.send("1"+string, SERVER_PORT,"localhost", function(){
        console.log(string + " 1has being send");
    });
}
function reportResult(string) {
    client.send("2" + string, SERVER_PORT, "localhost",function(){
        console.log(string + " 2has being send");
    });    
}
function terminate() {
    client.close();
}
module.exports = {
    reportMatch: reportMatch,
    reportResult: reportResult,
    terminate: terminate
}
