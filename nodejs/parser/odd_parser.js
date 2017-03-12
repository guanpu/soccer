/**
 *
 * Created by XiaoPu on 2017/3/11.
 */
const interest = require("../config/config.json").resource.odd.leagues;
const _ = require("underscore");

/**
 * Return a promise which can resolve out the matchId list for next steps.
 * @param data the original data fetched form API.
 * @returns a promise from which we can resolve an array of matchIds.
 */
function filter(data) {
    if(!_.isArray(data)) return new Exception("Response from upcoming not an array");
    return _.filter(data,(item)=>{
        return _.contains(interest,item.leagueId);
    });
}

/**
 *
 * @param data
 */
function mergeOdds(data) {
    if(!_.isArray(data)) return new Exception("Response from betlines not an array");
    return _.map(data,(item)=>{
        return _.extend({"bookName": item.bookName},_.pick(item, 'homeLine', 'awayLine', 'drawLine'));
    });
}

module.exports = {
    filter: filter,
    mergeOdds: mergeOdds
};