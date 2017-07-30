/**
 * Created by XiaoPu on 2017/3/22.
 */
const log4js = require('log4js');
var logger;


if(process.env.PRDO == 'on') {
    log4js.configure(`${__dirname}/log4js-config.json`);
    logger = log4js.getLogger("production");
} else {
    logger = log4js.getLogger();
}
exports.logger = logger;
