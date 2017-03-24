/**
 * Created by XiaoPu on 2017/3/22.
 */
const validName = /[\-\w\s]/g;
const _ = require("underscore");
/**
 * Normalize the special charactors from latin names.
 * @param str
 * @returns {*}
 */
function normalizeName(str) {
    if(!!str) {
        return str.normalize('NFD').match(validName).join("");
    }
    return "";
}
/**
 * teamfeed uses a special format for date, this method aims to return date string like "12. March 2017".
 * @param date A Date Object
 */
function concatDateFormatForLineUp(date) {
    const locale = "en-us";
    const month = {month:"long"};
    var str = date.getDate() + ". " + date.toLocaleString(locale,month) + " " + date.getFullYear();
    return str;
}
module.exports={
    "normalizeName": normalizeName,
    "concatDateFormatForLineUp": concatDateFormatForLineUp
};
