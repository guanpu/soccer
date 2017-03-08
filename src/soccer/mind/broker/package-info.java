/**
 * This package acts as message broker between nodejs module, email module and the core module(the training system).
 * and thus is responsible to communicate with nodejs module to:
 * 1. Get the real-world match data in time and then trigger matching in the trained model.
 * 2. Get the real-world match results to refine trained model.
 * 3. Get the nodejs module error and notify the Admin via email when necessary.
 * 
 */
package soccer.mind.broker;
