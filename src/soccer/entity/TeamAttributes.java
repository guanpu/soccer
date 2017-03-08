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
@Table(name = "team_attributes")
@XmlRootElement
public class TeamAttributes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "team_fifa_api_id")
    private Long teamFifaApiId;
    @Column(name = "team_api_id")
    private Long teamApiId;
    @Column(name = "date")
    private String date;
    @Column(name = "buildupplayspeed")
    private Long buildupplayspeed;
    @Column(name = "buildupplayspeedclass")
    private String buildupplayspeedclass;
    @Column(name = "buildupplaydribbling")
    private Long buildupplaydribbling;
    @Column(name = "buildupplaydribblingclass")
    private String buildupplaydribblingclass;
    @Column(name = "buildupplaypassing")
    private Long buildupplaypassing;
    @Column(name = "buildupplaypassingclass")
    private String buildupplaypassingclass;
    @Column(name = "buildupplaypositioningclass")
    private String buildupplaypositioningclass;
    @Column(name = "chancecreationpassing")
    private Long chancecreationpassing;
    @Column(name = "chancecreationpassingclass")
    private String chancecreationpassingclass;
    @Column(name = "chancecreationcrossing")
    private Long chancecreationcrossing;
    @Column(name = "chancecreationcrossingclass")
    private String chancecreationcrossingclass;
    @Column(name = "chancecreationshooting")
    private Long chancecreationshooting;
    @Column(name = "chancecreationshootingclass")
    private String chancecreationshootingclass;
    @Column(name = "chancecreationpositioningclass")
    private String chancecreationpositioningclass;
    @Column(name = "defencepressure")
    private Long defencepressure;
    @Column(name = "defencepressureclass")
    private String defencepressureclass;
    @Column(name = "defenceaggression")
    private Long defenceaggression;
    @Column(name = "defenceaggressionclass")
    private String defenceaggressionclass;
    @Column(name = "defenceteamwidth")
    private Long defenceteamwidth;
    @Column(name = "defenceteamwidthclass")
    private String defenceteamwidthclass;
    @Column(name = "defencedefenderlineclass")
    private String defencedefenderlineclass;

    public TeamAttributes() {
    }

    public TeamAttributes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamFifaApiId() {
        return teamFifaApiId;
    }

    public void setTeamFifaApiId(Long teamFifaApiId) {
        this.teamFifaApiId = teamFifaApiId;
    }

    public Long getTeamApiId() {
        return teamApiId;
    }

    public void setTeamApiId(Long teamApiId) {
        this.teamApiId = teamApiId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getBuildupplayspeed() {
        return buildupplayspeed;
    }

    public void setBuildupplayspeed(Long buildupplayspeed) {
        this.buildupplayspeed = buildupplayspeed;
    }

    public String getBuildupplayspeedclass() {
        return buildupplayspeedclass;
    }

    public void setBuildupplayspeedclass(String buildupplayspeedclass) {
        this.buildupplayspeedclass = buildupplayspeedclass;
    }

    public Long getBuildupplaydribbling() {
        return buildupplaydribbling;
    }

    public void setBuildupplaydribbling(Long buildupplaydribbling) {
        this.buildupplaydribbling = buildupplaydribbling;
    }

    public String getBuildupplaydribblingclass() {
        return buildupplaydribblingclass;
    }

    public void setBuildupplaydribblingclass(String buildupplaydribblingclass) {
        this.buildupplaydribblingclass = buildupplaydribblingclass;
    }

    public Long getBuildupplaypassing() {
        return buildupplaypassing;
    }

    public void setBuildupplaypassing(Long buildupplaypassing) {
        this.buildupplaypassing = buildupplaypassing;
    }

    public String getBuildupplaypassingclass() {
        return buildupplaypassingclass;
    }

    public void setBuildupplaypassingclass(String buildupplaypassingclass) {
        this.buildupplaypassingclass = buildupplaypassingclass;
    }

    public String getBuildupplaypositioningclass() {
        return buildupplaypositioningclass;
    }

    public void setBuildupplaypositioningclass(String buildupplaypositioningclass) {
        this.buildupplaypositioningclass = buildupplaypositioningclass;
    }

    public Long getChancecreationpassing() {
        return chancecreationpassing;
    }

    public void setChancecreationpassing(Long chancecreationpassing) {
        this.chancecreationpassing = chancecreationpassing;
    }

    public String getChancecreationpassingclass() {
        return chancecreationpassingclass;
    }

    public void setChancecreationpassingclass(String chancecreationpassingclass) {
        this.chancecreationpassingclass = chancecreationpassingclass;
    }

    public Long getChancecreationcrossing() {
        return chancecreationcrossing;
    }

    public void setChancecreationcrossing(Long chancecreationcrossing) {
        this.chancecreationcrossing = chancecreationcrossing;
    }

    public String getChancecreationcrossingclass() {
        return chancecreationcrossingclass;
    }

    public void setChancecreationcrossingclass(String chancecreationcrossingclass) {
        this.chancecreationcrossingclass = chancecreationcrossingclass;
    }

    public Long getChancecreationshooting() {
        return chancecreationshooting;
    }

    public void setChancecreationshooting(Long chancecreationshooting) {
        this.chancecreationshooting = chancecreationshooting;
    }

    public String getChancecreationshootingclass() {
        return chancecreationshootingclass;
    }

    public void setChancecreationshootingclass(String chancecreationshootingclass) {
        this.chancecreationshootingclass = chancecreationshootingclass;
    }

    public String getChancecreationpositioningclass() {
        return chancecreationpositioningclass;
    }

    public void setChancecreationpositioningclass(String chancecreationpositioningclass) {
        this.chancecreationpositioningclass = chancecreationpositioningclass;
    }

    public Long getDefencepressure() {
        return defencepressure;
    }

    public void setDefencepressure(Long defencepressure) {
        this.defencepressure = defencepressure;
    }

    public String getDefencepressureclass() {
        return defencepressureclass;
    }

    public void setDefencepressureclass(String defencepressureclass) {
        this.defencepressureclass = defencepressureclass;
    }

    public Long getDefenceaggression() {
        return defenceaggression;
    }

    public void setDefenceaggression(Long defenceaggression) {
        this.defenceaggression = defenceaggression;
    }

    public String getDefenceaggressionclass() {
        return defenceaggressionclass;
    }

    public void setDefenceaggressionclass(String defenceaggressionclass) {
        this.defenceaggressionclass = defenceaggressionclass;
    }

    public Long getDefenceteamwidth() {
        return defenceteamwidth;
    }

    public void setDefenceteamwidth(Long defenceteamwidth) {
        this.defenceteamwidth = defenceteamwidth;
    }

    public String getDefenceteamwidthclass() {
        return defenceteamwidthclass;
    }

    public void setDefenceteamwidthclass(String defenceteamwidthclass) {
        this.defenceteamwidthclass = defenceteamwidthclass;
    }

    public String getDefencedefenderlineclass() {
        return defencedefenderlineclass;
    }

    public void setDefencedefenderlineclass(String defencedefenderlineclass) {
        this.defencedefenderlineclass = defencedefenderlineclass;
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
        if (!(object instanceof TeamAttributes)) {
            return false;
        }
        TeamAttributes other = (TeamAttributes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soccer.stage.entity.TeamAttributes[ id=" + id + " ]";
    }
    
}
