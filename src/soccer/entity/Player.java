/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.entity;

import java.io.Serializable;
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
@Table(name = "player")
@XmlRootElement
public class Player implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "player_api_id")
    private Long playerApiId;
    @Column(name = "player_name")
    private String playerName;
    @OneToMany
    @JoinColumn(name = "player_fifa_api_id")
    private List<PlayerAttributes> playerFifaApiId;
    @Column(name = "birthday")
    private String birthday;
    @Column(name = "height")
    private Long height;
    @Column(name = "weight")
    private Long weight;

    public Player() {
    }

    public Player(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerApiId() {
        return playerApiId;
    }

    public void setPlayerApiId(Long playerApiId) {
        this.playerApiId = playerApiId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<PlayerAttributes> getPlayerFifaApiId() {
        return playerFifaApiId;
    }

    public void setPlayerFifaApiId(List<PlayerAttributes> playerFifaApiId) {
        this.playerFifaApiId = playerFifaApiId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
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
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soccer.stage.entity.Player[ id=" + id + " ]";
    }
    
    public PlayerAttributes getAttributes(String date) {
        List<PlayerAttributes> attrs = getPlayerFifaApiId();
        for (Iterator<PlayerAttributes> iterator = attrs.iterator(); iterator.hasNext();) {
            PlayerAttributes next = iterator.next();
            if(next.getDate().equals(date)) {
                return next;
            }
        }
        return null;        
    }    
}
