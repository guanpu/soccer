/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pguan
 */
@Entity
@Table(name = "player_attributes")
@XmlRootElement
public class PlayerAttributes implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "player_fifa_api_id")
    private Long playerFifaApiId;
    @Column(name = "player_api_id")
    private Long playerApiId;
    @Column(name = "player_date")
    private String playerDate;
    @Column(name = "overall_rating")
    private Long overallRating;
    @Column(name = "potential")
    private Long potential;
    @Column(name = "preferred_foot")
    private String preferredFoot;
    @Column(name = "attacking_work_rate")
    private String attackingWorkRate;
    @Column(name = "defensive_work_rate")
    private String defensiveWorkRate;
    @Column(name = "crossing")
    private Long crossing;
    @Column(name = "finishing")
    private Long finishing;
    @Column(name = "heading_accuracy")
    private Long headingAccuracy;
    @Column(name = "short_passing")
    private Long shortPassing;
    @Column(name = "volleys")
    private Long volleys;
    @Column(name = "dribbling")
    private Long dribbling;
    @Column(name = "curve")
    private Long curve;
    @Column(name = "free_kick_accuracy")
    private Long freeKickAccuracy;
    @Column(name = "long_passing")
    private Long longPassing;
    @Column(name = "ball_control")
    private Long ballControl;
    @Column(name = "acceleration")
    private Long acceleration;
    @Column(name = "sprint_speed")
    private Long sprintSpeed;
    @Column(name = "agility")
    private Long agility;
    @Column(name = "reactions")
    private Long reactions;
    @Column(name = "balance")
    private Long balance;
    @Column(name = "shot_power")
    private Long shotPower;
    @Column(name = "jumping")
    private Long jumping;
    @Column(name = "stamina")
    private Long stamina;
    @Column(name = "strength")
    private Long strength;
    @Column(name = "long_shots")
    private Long longShots;
    @Column(name = "aggression")
    private Long aggression;
    @Column(name = "interceptions")
    private Long interceptions;
    @Column(name = "positioning")
    private Long positioning;
    @Column(name = "vision")
    private Long vision;
    @Column(name = "penalties")
    private Long penalties;
    @Column(name = "marking")
    private Long marking;
    @Column(name = "standing_tackle")
    private Long standingTackle;
    @Column(name = "sliding_tackle")
    private Long slidingTackle;
    @Column(name = "gk_diving")
    private Long gkDiving;
    @Column(name = "gk_handling")
    private Long gkHandling;
    @Column(name = "gk_kicking")
    private Long gkKicking;
    @Column(name = "gk_positioning")
    private Long gkPositioning;
    @Column(name = "gk_reflexes")
    private Long gkReflexes;

    public PlayerAttributes() {
    }

    public PlayerAttributes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerFifaApiId() {
        return playerFifaApiId;
    }

    public void setPlayerFifaApiId(Long playerFifaApiId) {
        this.playerFifaApiId = playerFifaApiId;
    }

    public Long getPlayerApiId() {
        return playerApiId;
    }

    public void setPlayerApiId(Long playerApiId) {
        this.playerApiId = playerApiId;
    }

    public String getPlayerDate() {
        return playerDate;
    }

    public void setPlayerDate(String playerDate) {
        this.playerDate = playerDate;
    }
    
    public Long getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Long overallRating) {
        this.overallRating = overallRating;
    }

    public Long getPotential() {
        return potential;
    }

    public void setPotential(Long potential) {
        this.potential = potential;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(String preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public String getAttackingWorkRate() {
        return attackingWorkRate;
    }

    public void setAttackingWorkRate(String attackingWorkRate) {
        this.attackingWorkRate = attackingWorkRate;
    }

    public String getDefensiveWorkRate() {
        return defensiveWorkRate;
    }

    public void setDefensiveWorkRate(String defensiveWorkRate) {
        this.defensiveWorkRate = defensiveWorkRate;
    }

    public Long getCrossing() {
        return crossing;
    }

    public void setCrossing(Long crossing) {
        this.crossing = crossing;
    }

    public Long getFinishing() {
        return finishing;
    }

    public void setFinishing(Long finishing) {
        this.finishing = finishing;
    }

    public Long getHeadingAccuracy() {
        return headingAccuracy;
    }

    public void setHeadingAccuracy(Long headingAccuracy) {
        this.headingAccuracy = headingAccuracy;
    }

    public Long getShortPassing() {
        return shortPassing;
    }

    public void setShortPassing(Long shortPassing) {
        this.shortPassing = shortPassing;
    }

    public Long getVolleys() {
        return volleys;
    }

    public void setVolleys(Long volleys) {
        this.volleys = volleys;
    }

    public Long getDribbling() {
        return dribbling;
    }

    public void setDribbling(Long dribbling) {
        this.dribbling = dribbling;
    }

    public Long getCurve() {
        return curve;
    }

    public void setCurve(Long curve) {
        this.curve = curve;
    }

    public Long getFreeKickAccuracy() {
        return freeKickAccuracy;
    }

    public void setFreeKickAccuracy(Long freeKickAccuracy) {
        this.freeKickAccuracy = freeKickAccuracy;
    }

    public Long getLongPassing() {
        return longPassing;
    }

    public void setLongPassing(Long longPassing) {
        this.longPassing = longPassing;
    }

    public Long getBallControl() {
        return ballControl;
    }

    public void setBallControl(Long ballControl) {
        this.ballControl = ballControl;
    }

    public Long getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Long acceleration) {
        this.acceleration = acceleration;
    }

    public Long getSprintSpeed() {
        return sprintSpeed;
    }

    public void setSprintSpeed(Long sprintSpeed) {
        this.sprintSpeed = sprintSpeed;
    }

    public Long getAgility() {
        return agility;
    }

    public void setAgility(Long agility) {
        this.agility = agility;
    }

    public Long getReactions() {
        return reactions;
    }

    public void setReactions(Long reactions) {
        this.reactions = reactions;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getShotPower() {
        return shotPower;
    }

    public void setShotPower(Long shotPower) {
        this.shotPower = shotPower;
    }

    public Long getJumping() {
        return jumping;
    }

    public void setJumping(Long jumping) {
        this.jumping = jumping;
    }

    public Long getStamina() {
        return stamina;
    }

    public void setStamina(Long stamina) {
        this.stamina = stamina;
    }

    public Long getStrength() {
        return strength;
    }

    public void setStrength(Long strength) {
        this.strength = strength;
    }

    public Long getLongShots() {
        return longShots;
    }

    public void setLongShots(Long longShots) {
        this.longShots = longShots;
    }

    public Long getAggression() {
        return aggression;
    }

    public void setAggression(Long aggression) {
        this.aggression = aggression;
    }

    public Long getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(Long interceptions) {
        this.interceptions = interceptions;
    }

    public Long getPositioning() {
        return positioning;
    }

    public void setPositioning(Long positioning) {
        this.positioning = positioning;
    }

    public Long getVision() {
        return vision;
    }

    public void setVision(Long vision) {
        this.vision = vision;
    }

    public Long getPenalties() {
        return penalties;
    }

    public void setPenalties(Long penalties) {
        this.penalties = penalties;
    }

    public Long getMarking() {
        return marking;
    }

    public void setMarking(Long marking) {
        this.marking = marking;
    }

    public Long getStandingTackle() {
        return standingTackle;
    }

    public void setStandingTackle(Long standingTackle) {
        this.standingTackle = standingTackle;
    }

    public Long getSlidingTackle() {
        return slidingTackle;
    }

    public void setSlidingTackle(Long slidingTackle) {
        this.slidingTackle = slidingTackle;
    }

    public Long getGkDiving() {
        return gkDiving;
    }

    public void setGkDiving(Long gkDiving) {
        this.gkDiving = gkDiving;
    }

    public Long getGkHandling() {
        return gkHandling;
    }

    public void setGkHandling(Long gkHandling) {
        this.gkHandling = gkHandling;
    }

    public Long getGkKicking() {
        return gkKicking;
    }

    public void setGkKicking(Long gkKicking) {
        this.gkKicking = gkKicking;
    }

    public Long getGkPositioning() {
        return gkPositioning;
    }

    public void setGkPositioning(Long gkPositioning) {
        this.gkPositioning = gkPositioning;
    }

    public Long getGkReflexes() {
        return gkReflexes;
    }

    public void setGkReflexes(Long gkReflexes) {
        this.gkReflexes = gkReflexes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlayerAttributes)) {
            return false;
        }
        PlayerAttributes other = (PlayerAttributes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soccer.stage.entity.PlayerAttributes[ id=" + id + " ]";
    }
    
}
