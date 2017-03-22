/**
 * Created by XiaoPu on 2017/3/22.
 */
const validName = /[\-\w\s]/g;

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

module.exports={
    "normalizeName": normalizeName
}
