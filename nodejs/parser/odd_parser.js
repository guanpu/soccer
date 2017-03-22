/**
 *
 * Created by XiaoPu on 2017/3/11.
 */
const interest = require("../config/config.json").resource.odd.leagues;
const _ = require("underscore");

/**
 * Resolve out the matchId list for next steps. Currently the filter only does league-picking job.
 * @param data the original data fetched form API.
 * @returns an array of matchIds.
 */
function filter(data) {
    if(!_.isArray(data)) return new Exception("Response from upcoming not an array");
    return _.filter(data,(item)=>{
        return _.contains(interest,item.leagueId);
    });
}

/**
 * Transform the array odd data from its original format, i.e.
 *
 {
   "bookName": "coral",
   "affiliate": 0,
   "link": "http://sports.coral.co.uk/",
   "betlinesId": 3895285,
   "matchId": 43518,
   "bookId": 2,
   "homeLine": 4.2,
   "awayLine": 1.667,
   "drawLine": 4
 }
 to the per-match format, like:
 {
   "bookName": "ladbrokes",
   "homeLine": 4.2,
   "awayLine": 1.7,
   "drawLine": 4.2
 }
 * @param data
 */
function mergeOdds(data) {
    if(!_.isArray(data)) return new Exception("Response from betlines not an array");
    return _.map(data,(item)=>{
        return _.pick(item,'bookName', 'homeLine', 'awayLine', 'drawLine');
    });
}

module.exports = {
    filter: filter,
    mergeOdds: mergeOdds
};