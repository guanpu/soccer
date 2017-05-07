/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pguan
 */
@Entity
@Table(name = "model1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Model1.findAll", query = "SELECT m FROM Model1 m")})
public class Model1 implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "P1")
    private Integer p1;
    @Column(name = "P2")
    private Integer p2;
    @Column(name = "P3")
    private Integer p3;
    @Column(name = "P4")
    private Integer p4;
    @Column(name = "P5")
    private Integer p5;
    @Column(name = "P6")
    private Integer p6;
    @Column(name = "P7")
    private Integer p7;
    @Column(name = "P8")
    private Integer p8;
    @Column(name = "P9")
    private Integer p9;
    @Column(name = "P10")
    private Integer p10;
    @Column(name = "P11")
    private Integer p11;
    @Column(name = "P12")
    private Integer p12;
    @Column(name = "P13")
    private Integer p13;
    @Column(name = "P14")
    private Integer p14;
    @Column(name = "P15")
    private Integer p15;
    @Column(name = "P16")
    private Integer p16;
    @Column(name = "P17")
    private Integer p17;
    @Column(name = "P18")
    private Integer p18;
    @Column(name = "P19")
    private Integer p19;
    @Column(name = "P20")
    private Integer p20;
    @Column(name = "P21")
    private Integer p21;
    @Column(name = "P22")
    private Integer p22;
    @Column(name = "HTEAM_ATT1")
    private Integer hteamAtt1;
    @Column(name = "ATEAM_ATT1")
    private Integer ateamAtt1;
    @Column(name = "HTEAM_ATT2")
    private Integer hteamAtt2;
    @Column(name = "ATEAM_ATT2")
    private Integer ateamAtt2;
    @Column(name = "HG")
    private Integer hg;
    @Column(name = "AG")
    private Integer ag;
    @Column(name = "final_result")
    private Integer finalResult;
    @Column(name = "home_team_id")
    private BigInteger homeTeamId;
    @Column(name = "away_team_id")
    private BigInteger awayTeamId;
    @Column(name = "home_curr_rank")
    private Integer homeCurrRank;
    @Column(name = "away_curr_rank")
    private Integer awayCurrRank;
    @Column(name = "home_exp_rank")
    private Integer homeExpRank;
    @Column(name = "away_exp_rank")
    private Integer awayExpRank;
    @Column(name = "home_last_profile")
    private String homeLastProfile;
    @Column(name = "away_last_profile")
    private String awayLastProfile;
    @Column(name = "matchdate")
    private String matchdate;

    /**
     * Get the value of matchdate
     *
     * @return the value of matchdate
     */
    public String getMatchdate() {
        return matchdate;
    }

    /**
     * Set the value of matchdate
     *
     * @param matchdate new value of matchdate
     */
    public void setMatchdate(String matchdate) {
        this.matchdate = matchdate;
    }


    public Model1() {
    }

    public Model1(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getP1() {
        return p1;
    }

    public void setP1(Integer p1) {
        this.p1 = p1;
    }

    public Integer getP2() {
        return p2;
    }

    public void setP2(Integer p2) {
        this.p2 = p2;
    }

    public Integer getP3() {
        return p3;
    }

    public void setP3(Integer p3) {
        this.p3 = p3;
    }

    public Integer getP4() {
        return p4;
    }

    public void setP4(Integer p4) {
        this.p4 = p4;
    }

    public Integer getP5() {
        return p5;
    }

    public void setP5(Integer p5) {
        this.p5 = p5;
    }

    public Integer getP6() {
        return p6;
    }

    public void setP6(Integer p6) {
        this.p6 = p6;
    }

    public Integer getP7() {
        return p7;
    }

    public void setP7(Integer p7) {
        this.p7 = p7;
    }

    public Integer getP8() {
        return p8;
    }

    public void setP8(Integer p8) {
        this.p8 = p8;
    }

    public Integer getP9() {
        return p9;
    }

    public void setP9(Integer p9) {
        this.p9 = p9;
    }

    public Integer getP10() {
        return p10;
    }

    public void setP10(Integer p10) {
        this.p10 = p10;
    }

    public Integer getP11() {
        return p11;
    }

    public void setP11(Integer p11) {
        this.p11 = p11;
    }

    public Integer getP12() {
        return p12;
    }

    public void setP12(Integer p12) {
        this.p12 = p12;
    }

    public Integer getP13() {
        return p13;
    }

    public void setP13(Integer p13) {
        this.p13 = p13;
    }

    public Integer getP14() {
        return p14;
    }

    public void setP14(Integer p14) {
        this.p14 = p14;
    }

    public Integer getP15() {
        return p15;
    }

    public void setP15(Integer p15) {
        this.p15 = p15;
    }

    public Integer getP16() {
        return p16;
    }

    public void setP16(Integer p16) {
        this.p16 = p16;
    }

    public Integer getP17() {
        return p17;
    }

    public void setP17(Integer p17) {
        this.p17 = p17;
    }

    public Integer getP18() {
        return p18;
    }

    public void setP18(Integer p18) {
        this.p18 = p18;
    }

    public Integer getP19() {
        return p19;
    }

    public void setP19(Integer p19) {
        this.p19 = p19;
    }

    public Integer getP20() {
        return p20;
    }

    public void setP20(Integer p20) {
        this.p20 = p20;
    }

    public Integer getP21() {
        return p21;
    }

    public void setP21(Integer p21) {
        this.p21 = p21;
    }

    public Integer getP22() {
        return p22;
    }

    public void setP22(Integer p22) {
        this.p22 = p22;
    }

    public Integer getHteamAtt1() {
        return hteamAtt1;
    }

    public void setHteamAtt1(Integer hteamAtt1) {
        this.hteamAtt1 = hteamAtt1;
    }

    public Integer getAteamAtt1() {
        return ateamAtt1;
    }

    public void setAteamAtt1(Integer ateamAtt1) {
        this.ateamAtt1 = ateamAtt1;
    }

    public Integer getHteamAtt2() {
        return hteamAtt2;
    }

    public void setHteamAtt2(Integer hteamAtt2) {
        this.hteamAtt2 = hteamAtt2;
    }

    public Integer getAteamAtt2() {
        return ateamAtt2;
    }

    public void setAteamAtt2(Integer ateamAtt2) {
        this.ateamAtt2 = ateamAtt2;
    }

    public Integer getHg() {
        return hg;
    }

    public void setHg(Integer hg) {
        this.hg = hg;
    }

    public Integer getAg() {
        return ag;
    }

    public void setAg(Integer ag) {
        this.ag = ag;
    }

    public Integer getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(Integer finalResult) {
        this.finalResult = finalResult;
    }

    public BigInteger getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(BigInteger homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public BigInteger getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(BigInteger awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getHomeCurrRank() {
        return homeCurrRank;
    }

    public void setHomeCurrRank(Integer homeCurrRank) {
        this.homeCurrRank = homeCurrRank;
    }

    public Integer getAwayCurrRank() {
        return awayCurrRank;
    }

    public void setAwayCurrRank(Integer awayCurrRank) {
        this.awayCurrRank = awayCurrRank;
    }

    public Integer getHomeExpRank() {
        return homeExpRank;
    }

    public void setHomeExpRank(Integer homeExpRank) {
        this.homeExpRank = homeExpRank;
    }

    public Integer getAwayExpRank() {
        return awayExpRank;
    }

    public void setAwayExpRank(Integer awayExpRank) {
        this.awayExpRank = awayExpRank;
    }

    public String getHomeLastProfile() {
        return homeLastProfile;
    }

    public void setHomeLastProfile(String homeLastProfile) {
        this.homeLastProfile = homeLastProfile;
    }

    public String getAwayLastProfile() {
        return awayLastProfile;
    }

    public void setAwayLastProfile(String awayLastProfile) {
        this.awayLastProfile = awayLastProfile;
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
        if (!(object instanceof Model1)) {
            return false;
        }
        Model1 other = (Model1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soccer.entity.Model1[ id=" + id + " ]";
    }
    
}
