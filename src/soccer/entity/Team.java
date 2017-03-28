/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pguan
 */
@Entity
@Table(name = "team")
@XmlRootElement
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "team_api_id")
    private Long teamApiId;
    @OneToMany
    @JoinColumn(name = "team_fifa_api_id")
    private List<TeamAttributes> teamFifaApiId;
    @Column(name = "team_long_name")
    private String teamLongName;
    @Column(name = "team_short_name")
    private String teamShortName;

    public Team() {
    }

    public Team(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeamApiId() {
        return teamApiId;
    }

    public void setTeamApiId(Long teamApiId) {
        this.teamApiId = teamApiId;
    }

    public List<TeamAttributes> getTeamFifaApiId() {
        return teamFifaApiId;
    }

    public void setTeamFifaApiId(List<TeamAttributes> teamFifaApiId) {
        this.teamFifaApiId = teamFifaApiId;
    }

    public String getTeamLongName() {
        return teamLongName;
    }

    public void setTeamLongName(String teamLongName) {
        this.teamLongName = teamLongName;
    }

    public String getTeamShortName() {
        return teamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        this.teamShortName = teamShortName;
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
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soccer.stage.entity.Team[ id=" + id + " ]";
    }
    
    public TeamAttributes getAttributes(String date) {
        List<TeamAttributes> attrs = getTeamFifaApiId();
        for (Iterator<TeamAttributes> iterator = attrs.iterator(); iterator.hasNext();) {
            TeamAttributes next = iterator.next();
            if(next.getMatchdate().equals(date)) {
                return next;
            }            
        }
        return null;
    }
}
