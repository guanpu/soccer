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
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pguan
 */
@Entity
@Table(name = "matches")
@XmlRootElement
public class Matches implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "league_id")
    private Long leagueId;
    @Column(name = "season")
    private String season;
    @Column(name = "stage")
    private Integer stage;
    @Column(name = "match_date")
    private String matchDate;
    @Column(name = "match_api_id")
    private Integer matchApiId;
    @Column(name = "home_team_api_id")
    private Long homeTeamApiId;
    @Column(name = "away_team_api_id")
    private Long awayTeamApiId;
    @Column(name = "home_team_goal")
    private Integer homeTeamGoal;
    @Column(name = "away_team_goal")
    private Integer awayTeamGoal;
    @Column(name = "home_player_X1")
    private Integer homeplayerX1;
    @Column(name = "home_player_X2")
    private Integer homeplayerX2;
    @Column(name = "home_player_X3")
    private Integer homeplayerX3;
    @Column(name = "home_player_X4")
    private Integer homeplayerX4;
    @Column(name = "home_player_X5")
    private Integer homeplayerX5;
    @Column(name = "home_player_X6")
    private Integer homeplayerX6;
    @Column(name = "home_player_X7")
    private Integer homeplayerX7;
    @Column(name = "home_player_X8")
    private Integer homeplayerX8;
    @Column(name = "home_player_X9")
    private Integer homeplayerX9;
    @Column(name = "home_player_X10")
    private Integer homeplayerX10;
    @Column(name = "home_player_X11")
    private Integer homeplayerX11;
    @Column(name = "away_player_X1")
    private Integer awayplayerX1;
    @Column(name = "away_player_X2")
    private Integer awayplayerX2;
    @Column(name = "away_player_X3")
    private Integer awayplayerX3;
    @Column(name = "away_player_X4")
    private Integer awayplayerX4;
    @Column(name = "away_player_X5")
    private Integer awayplayerX5;
    @Column(name = "away_player_X6")
    private Integer awayplayerX6;
    @Column(name = "away_player_X7")
    private Integer awayplayerX7;
    @Column(name = "away_player_X8")
    private Integer awayplayerX8;
    @Column(name = "away_player_X9")
    private Integer awayplayerX9;
    @Column(name = "away_player_X10")
    private Integer awayplayerX10;
    @Column(name = "away_player_X11")
    private Integer awayplayerX11;
    @Column(name = "home_player_Y1")
    private Integer homeplayerY1;
    @Column(name = "home_player_Y2")
    private Integer homeplayerY2;
    @Column(name = "home_player_Y3")
    private Integer homeplayerY3;
    @Column(name = "home_player_Y4")
    private Integer homeplayerY4;
    @Column(name = "home_player_Y5")
    private Integer homeplayerY5;
    @Column(name = "home_player_Y6")
    private Integer homeplayerY6;
    @Column(name = "home_player_Y7")
    private Integer homeplayerY7;
    @Column(name = "home_player_Y8")
    private Integer homeplayerY8;
    @Column(name = "home_player_Y9")
    private Integer homeplayerY9;
    @Column(name = "home_player_Y10")
    private Integer homeplayerY10;
    @Column(name = "home_player_Y11")
    private Integer homeplayerY11;
    @Column(name = "away_player_Y1")
    private Integer awayplayerY1;
    @Column(name = "away_player_Y2")
    private Integer awayplayerY2;
    @Column(name = "away_player_Y3")
    private Integer awayplayerY3;
    @Column(name = "away_player_Y4")
    private Integer awayplayerY4;
    @Column(name = "away_player_Y5")
    private Integer awayplayerY5;
    @Column(name = "away_player_Y6")
    private Integer awayplayerY6;
    @Column(name = "away_player_Y7")
    private Integer awayplayerY7;
    @Column(name = "away_player_Y8")
    private Integer awayplayerY8;
    @Column(name = "away_player_Y9")
    private Integer awayplayerY9;
    @Column(name = "away_player_Y10")
    private Integer awayplayerY10;
    @Column(name = "away_player_Y11")
    private Integer awayplayerY11;
    @Column(name = "home_player_1")
    private Long homePlayer1;
    @Column(name = "home_player_2")
    private Long homePlayer2;
    @Column(name = "home_player_3")
    private Long homePlayer3;
    @Column(name = "home_player_4")
    private Long homePlayer4;
    @Column(name = "home_player_5")
    private Long homePlayer5;
    @Column(name = "home_player_6")
    private Long homePlayer6;
    @Column(name = "home_player_7")
    private Long homePlayer7;
    @Column(name = "home_player_8")
    private Long homePlayer8;
    @Column(name = "home_player_9")
    private Long homePlayer9;
    @Column(name = "home_player_10")
    private Long homePlayer10;
    @Column(name = "home_player_11")
    private Long homePlayer11;
    @Column(name = "away_player_1")
    private Long awayPlayer1;
    @Column(name = "away_player_2")
    private Long awayPlayer2;
    @Column(name = "away_player_3")
    private Long awayPlayer3;
    @Column(name = "away_player_4")
    private Long awayPlayer4;
    @Column(name = "away_player_5")
    private Long awayPlayer5;
    @Column(name = "away_player_6")
    private Long awayPlayer6;
    @Column(name = "away_player_7")
    private Long awayPlayer7;
    @Column(name = "away_player_8")
    private Long awayPlayer8;
    @Column(name = "away_player_9")
    private Long awayPlayer9;
    @Column(name = "away_player_10")
    private Long awayPlayer10;
    @Column(name = "away_player_11")
    private Long awayPlayer11;
    @Lob
    @Column(name = "goal")
    private String goal;
    @Lob
    @Column(name = "shoton")
    private String shoton;
    @Lob
    @Column(name = "shotoff")
    private String shotoff;
    @Lob
    @Column(name = "foulcommit")
    private String foulcommit;
    @Lob
    @Column(name = "card")
    private String card;
    @Lob
    @Column(name = "crosspass")
    private String crosspass;
    @Lob
    @Column(name = "corner")
    private String corner;
    @Lob
    @Column(name = "possession")
    private String possession;
    @Column(name = "B365H")
    private Long b365h;
    @Column(name = "B365D")
    private Long b365d;
    @Column(name = "B365A")
    private Long b365a;
    @Column(name = "BWH")
    private Long bwh;
    @Column(name = "BWD")
    private Long bwd;
    @Column(name = "BWA")
    private Long bwa;
    @Column(name = "IWH")
    private Long iwh;
    @Column(name = "IWD")
    private Long iwd;
    @Column(name = "IWA")
    private Long iwa;
    @Column(name = "LBH")
    private Long lbh;
    @Column(name = "LBD")
    private Long lbd;
    @Column(name = "LBA")
    private Long lba;
    @Column(name = "PSH")
    private Long psh;
    @Column(name = "PSD")
    private Long psd;
    @Column(name = "PSA")
    private Long psa;
    @Column(name = "WHH")
    private Long whh;
    @Column(name = "WHD")
    private Long whd;
    @Column(name = "WHA")
    private Long wha;
    @Column(name = "SJH")
    private Long sjh;
    @Column(name = "SJD")
    private Long sjd;
    @Column(name = "SJA")
    private Long sja;
    @Column(name = "VCH")
    private Long vch;
    @Column(name = "VCD")
    private Long vcd;
    @Column(name = "VCA")
    private Long vca;
    @Column(name = "GBH")
    private Long gbh;
    @Column(name = "GBD")
    private Long gbd;
    @Column(name = "GBA")
    private Long gba;
    @Column(name = "BSH")
    private Long bsh;
    @Column(name = "BSD")
    private Long bsd;
    @Column(name = "BSA")
    private Long bsa;

    public Matches() {
    }

    public Matches(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getMatchApiId() {
        return matchApiId;
    }

    public void setMatchApiId(Integer matchApiId) {
        this.matchApiId = matchApiId;
    }  

    public Integer getHomeTeamGoal() {
        return homeTeamGoal;
    }

    public void setHomeTeamGoal(Integer homeTeamGoal) {
        this.homeTeamGoal = homeTeamGoal;
    }

    public Integer getAwayTeamGoal() {
        return awayTeamGoal;
    }

    public void setAwayTeamGoal(Integer awayTeamGoal) {
        this.awayTeamGoal = awayTeamGoal;
    }

    public Integer getHomeplayerX1() {
        return homeplayerX1;
    }

    public void setHomeplayerX1(Integer homeplayerX1) {
        this.homeplayerX1 = homeplayerX1;
    }

    public Integer getHomeplayerX2() {
        return homeplayerX2;
    }

    public void setHomeplayerX2(Integer homeplayerX2) {
        this.homeplayerX2 = homeplayerX2;
    }

    public Integer getHomeplayerX3() {
        return homeplayerX3;
    }

    public void setHomeplayerX3(Integer homeplayerX3) {
        this.homeplayerX3 = homeplayerX3;
    }

    public Integer getHomeplayerX4() {
        return homeplayerX4;
    }

    public void setHomeplayerX4(Integer homeplayerX4) {
        this.homeplayerX4 = homeplayerX4;
    }

    public Integer getHomeplayerX5() {
        return homeplayerX5;
    }

    public void setHomeplayerX5(Integer homeplayerX5) {
        this.homeplayerX5 = homeplayerX5;
    }

    public Integer getHomeplayerX6() {
        return homeplayerX6;
    }

    public void setHomeplayerX6(Integer homeplayerX6) {
        this.homeplayerX6 = homeplayerX6;
    }

    public Integer getHomeplayerX7() {
        return homeplayerX7;
    }

    public void setHomeplayerX7(Integer homeplayerX7) {
        this.homeplayerX7 = homeplayerX7;
    }

    public Integer getHomeplayerX8() {
        return homeplayerX8;
    }

    public void setHomeplayerX8(Integer homeplayerX8) {
        this.homeplayerX8 = homeplayerX8;
    }

    public Integer getHomeplayerX9() {
        return homeplayerX9;
    }

    public void setHomeplayerX9(Integer homeplayerX9) {
        this.homeplayerX9 = homeplayerX9;
    }

    public Integer getHomeplayerX10() {
        return homeplayerX10;
    }

    public void setHomeplayerX10(Integer homeplayerX10) {
        this.homeplayerX10 = homeplayerX10;
    }

    public Integer getHomeplayerX11() {
        return homeplayerX11;
    }

    public void setHomeplayerX11(Integer homeplayerX11) {
        this.homeplayerX11 = homeplayerX11;
    }

    public Integer getAwayplayerX1() {
        return awayplayerX1;
    }

    public void setAwayplayerX1(Integer awayplayerX1) {
        this.awayplayerX1 = awayplayerX1;
    }

    public Integer getAwayplayerX2() {
        return awayplayerX2;
    }

    public void setAwayplayerX2(Integer awayplayerX2) {
        this.awayplayerX2 = awayplayerX2;
    }

    public Integer getAwayplayerX3() {
        return awayplayerX3;
    }

    public void setAwayplayerX3(Integer awayplayerX3) {
        this.awayplayerX3 = awayplayerX3;
    }

    public Integer getAwayplayerX4() {
        return awayplayerX4;
    }

    public void setAwayplayerX4(Integer awayplayerX4) {
        this.awayplayerX4 = awayplayerX4;
    }

    public Integer getAwayplayerX5() {
        return awayplayerX5;
    }

    public void setAwayplayerX5(Integer awayplayerX5) {
        this.awayplayerX5 = awayplayerX5;
    }

    public Integer getAwayplayerX6() {
        return awayplayerX6;
    }

    public void setAwayplayerX6(Integer awayplayerX6) {
        this.awayplayerX6 = awayplayerX6;
    }

    public Integer getAwayplayerX7() {
        return awayplayerX7;
    }

    public void setAwayplayerX7(Integer awayplayerX7) {
        this.awayplayerX7 = awayplayerX7;
    }

    public Integer getAwayplayerX8() {
        return awayplayerX8;
    }

    public void setAwayplayerX8(Integer awayplayerX8) {
        this.awayplayerX8 = awayplayerX8;
    }

    public Integer getAwayplayerX9() {
        return awayplayerX9;
    }

    public void setAwayplayerX9(Integer awayplayerX9) {
        this.awayplayerX9 = awayplayerX9;
    }

    public Integer getAwayplayerX10() {
        return awayplayerX10;
    }

    public void setAwayplayerX10(Integer awayplayerX10) {
        this.awayplayerX10 = awayplayerX10;
    }

    public Integer getAwayplayerX11() {
        return awayplayerX11;
    }

    public void setAwayplayerX11(Integer awayplayerX11) {
        this.awayplayerX11 = awayplayerX11;
    }

    public Integer getHomeplayerY1() {
        return homeplayerY1;
    }

    public void setHomeplayerY1(Integer homeplayerY1) {
        this.homeplayerY1 = homeplayerY1;
    }

    public Integer getHomeplayerY2() {
        return homeplayerY2;
    }

    public void setHomeplayerY2(Integer homeplayerY2) {
        this.homeplayerY2 = homeplayerY2;
    }

    public Integer getHomeplayerY3() {
        return homeplayerY3;
    }

    public void setHomeplayerY3(Integer homeplayerY3) {
        this.homeplayerY3 = homeplayerY3;
    }

    public Integer getHomeplayerY4() {
        return homeplayerY4;
    }

    public void setHomeplayerY4(Integer homeplayerY4) {
        this.homeplayerY4 = homeplayerY4;
    }

    public Integer getHomeplayerY5() {
        return homeplayerY5;
    }

    public void setHomeplayerY5(Integer homeplayerY5) {
        this.homeplayerY5 = homeplayerY5;
    }

    public Integer getHomeplayerY6() {
        return homeplayerY6;
    }

    public void setHomeplayerY6(Integer homeplayerY6) {
        this.homeplayerY6 = homeplayerY6;
    }

    public Integer getHomeplayerY7() {
        return homeplayerY7;
    }

    public void setHomeplayerY7(Integer homeplayerY7) {
        this.homeplayerY7 = homeplayerY7;
    }

    public Integer getHomeplayerY8() {
        return homeplayerY8;
    }

    public void setHomeplayerY8(Integer homeplayerY8) {
        this.homeplayerY8 = homeplayerY8;
    }

    public Integer getHomeplayerY9() {
        return homeplayerY9;
    }

    public void setHomeplayerY9(Integer homeplayerY9) {
        this.homeplayerY9 = homeplayerY9;
    }

    public Integer getHomeplayerY10() {
        return homeplayerY10;
    }

    public void setHomeplayerY10(Integer homeplayerY10) {
        this.homeplayerY10 = homeplayerY10;
    }

    public Integer getHomeplayerY11() {
        return homeplayerY11;
    }

    public void setHomeplayerY11(Integer homeplayerY11) {
        this.homeplayerY11 = homeplayerY11;
    }

    public Integer getAwayplayerY1() {
        return awayplayerY1;
    }

    public void setAwayplayerY1(Integer awayplayerY1) {
        this.awayplayerY1 = awayplayerY1;
    }

    public Integer getAwayplayerY2() {
        return awayplayerY2;
    }

    public void setAwayplayerY2(Integer awayplayerY2) {
        this.awayplayerY2 = awayplayerY2;
    }

    public Integer getAwayplayerY3() {
        return awayplayerY3;
    }

    public void setAwayplayerY3(Integer awayplayerY3) {
        this.awayplayerY3 = awayplayerY3;
    }

    public Integer getAwayplayerY4() {
        return awayplayerY4;
    }

    public void setAwayplayerY4(Integer awayplayerY4) {
        this.awayplayerY4 = awayplayerY4;
    }

    public Integer getAwayplayerY5() {
        return awayplayerY5;
    }

    public void setAwayplayerY5(Integer awayplayerY5) {
        this.awayplayerY5 = awayplayerY5;
    }

    public Integer getAwayplayerY6() {
        return awayplayerY6;
    }

    public void setAwayplayerY6(Integer awayplayerY6) {
        this.awayplayerY6 = awayplayerY6;
    }

    public Integer getAwayplayerY7() {
        return awayplayerY7;
    }

    public void setAwayplayerY7(Integer awayplayerY7) {
        this.awayplayerY7 = awayplayerY7;
    }

    public Integer getAwayplayerY8() {
        return awayplayerY8;
    }

    public void setAwayplayerY8(Integer awayplayerY8) {
        this.awayplayerY8 = awayplayerY8;
    }

    public Integer getAwayplayerY9() {
        return awayplayerY9;
    }

    public void setAwayplayerY9(Integer awayplayerY9) {
        this.awayplayerY9 = awayplayerY9;
    }

    public Integer getAwayplayerY10() {
        return awayplayerY10;
    }

    public void setAwayplayerY10(Integer awayplayerY10) {
        this.awayplayerY10 = awayplayerY10;
    }

    public Integer getAwayplayerY11() {
        return awayplayerY11;
    }

    public void setAwayplayerY11(Integer awayplayerY11) {
        this.awayplayerY11 = awayplayerY11;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getShoton() {
        return shoton;
    }

    public void setShoton(String shoton) {
        this.shoton = shoton;
    }

    public String getShotoff() {
        return shotoff;
    }

    public void setShotoff(String shotoff) {
        this.shotoff = shotoff;
    }

    public String getFoulcommit() {
        return foulcommit;
    }

    public void setFoulcommit(String foulcommit) {
        this.foulcommit = foulcommit;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getCrosspass() {
        return crosspass;
    }

    public void setCrosspass(String crosspass) {
        this.crosspass = crosspass;
    }

    public String getCorner() {
        return corner;
    }

    public void setCorner(String corner) {
        this.corner = corner;
    }

    public String getPossession() {
        return possession;
    }

    public void setPossession(String possession) {
        this.possession = possession;
    }

    public Long getB365h() {
        return b365h;
    }

    public void setB365h(Long b365h) {
        this.b365h = b365h;
    }

    public Long getB365d() {
        return b365d;
    }

    public void setB365d(Long b365d) {
        this.b365d = b365d;
    }

    public Long getB365a() {
        return b365a;
    }

    public void setB365a(Long b365a) {
        this.b365a = b365a;
    }

    public Long getBwh() {
        return bwh;
    }

    public void setBwh(Long bwh) {
        this.bwh = bwh;
    }

    public Long getBwd() {
        return bwd;
    }

    public void setBwd(Long bwd) {
        this.bwd = bwd;
    }

    public Long getBwa() {
        return bwa;
    }

    public void setBwa(Long bwa) {
        this.bwa = bwa;
    }

    public Long getIwh() {
        return iwh;
    }

    public void setIwh(Long iwh) {
        this.iwh = iwh;
    }

    public Long getIwd() {
        return iwd;
    }

    public void setIwd(Long iwd) {
        this.iwd = iwd;
    }

    public Long getIwa() {
        return iwa;
    }

    public void setIwa(Long iwa) {
        this.iwa = iwa;
    }

    public Long getLbh() {
        return lbh;
    }

    public void setLbh(Long lbh) {
        this.lbh = lbh;
    }

    public Long getLbd() {
        return lbd;
    }

    public void setLbd(Long lbd) {
        this.lbd = lbd;
    }

    public Long getLba() {
        return lba;
    }

    public void setLba(Long lba) {
        this.lba = lba;
    }

    public Long getPsh() {
        return psh;
    }

    public void setPsh(Long psh) {
        this.psh = psh;
    }

    public Long getPsd() {
        return psd;
    }

    public void setPsd(Long psd) {
        this.psd = psd;
    }

    public Long getPsa() {
        return psa;
    }

    public void setPsa(Long psa) {
        this.psa = psa;
    }

    public Long getWhh() {
        return whh;
    }

    public void setWhh(Long whh) {
        this.whh = whh;
    }

    public Long getWhd() {
        return whd;
    }

    public void setWhd(Long whd) {
        this.whd = whd;
    }

    public Long getWha() {
        return wha;
    }

    public void setWha(Long wha) {
        this.wha = wha;
    }

    public Long getSjh() {
        return sjh;
    }

    public void setSjh(Long sjh) {
        this.sjh = sjh;
    }

    public Long getSjd() {
        return sjd;
    }

    public void setSjd(Long sjd) {
        this.sjd = sjd;
    }

    public Long getSja() {
        return sja;
    }

    public void setSja(Long sja) {
        this.sja = sja;
    }

    public Long getVch() {
        return vch;
    }

    public void setVch(Long vch) {
        this.vch = vch;
    }

    public Long getVcd() {
        return vcd;
    }

    public void setVcd(Long vcd) {
        this.vcd = vcd;
    }

    public Long getVca() {
        return vca;
    }

    public void setVca(Long vca) {
        this.vca = vca;
    }

    public Long getGbh() {
        return gbh;
    }

    public void setGbh(Long gbh) {
        this.gbh = gbh;
    }

    public Long getGbd() {
        return gbd;
    }

    public void setGbd(Long gbd) {
        this.gbd = gbd;
    }

    public Long getGba() {
        return gba;
    }

    public void setGba(Long gba) {
        this.gba = gba;
    }

    public Long getBsh() {
        return bsh;
    }

    public void setBsh(Long bsh) {
        this.bsh = bsh;
    }

    public Long getBsd() {
        return bsd;
    }

    public void setBsd(Long bsd) {
        this.bsd = bsd;
    }

    public Long getBsa() {
        return bsa;
    }

    public void setBsa(Long bsa) {
        this.bsa = bsa;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public Long getHomeTeamApiId() {
        return homeTeamApiId;
    }

    public void setHomeTeamApiId(Long homeTeamApiId) {
        this.homeTeamApiId = homeTeamApiId;
    }

    public Long getAwayTeamApiId() {
        return awayTeamApiId;
    }

    public void setAwayTeamApiId(Long awayTeamApiId) {
        this.awayTeamApiId = awayTeamApiId;
    }

    public Long getHomePlayer1() {
        return homePlayer1;
    }

    public void setHomePlayer1(Long homePlayer1) {
        this.homePlayer1 = homePlayer1;
    }

    public Long getHomePlayer2() {
        return homePlayer2;
    }

    public void setHomePlayer2(Long homePlayer2) {
        this.homePlayer2 = homePlayer2;
    }

    public Long getHomePlayer3() {
        return homePlayer3;
    }

    public void setHomePlayer3(Long homePlayer3) {
        this.homePlayer3 = homePlayer3;
    }

    public Long getHomePlayer4() {
        return homePlayer4;
    }

    public void setHomePlayer4(Long homePlayer4) {
        this.homePlayer4 = homePlayer4;
    }

    public Long getHomePlayer5() {
        return homePlayer5;
    }

    public void setHomePlayer5(Long homePlayer5) {
        this.homePlayer5 = homePlayer5;
    }

    public Long getHomePlayer6() {
        return homePlayer6;
    }

    public void setHomePlayer6(Long homePlayer6) {
        this.homePlayer6 = homePlayer6;
    }

    public Long getHomePlayer7() {
        return homePlayer7;
    }

    public void setHomePlayer7(Long homePlayer7) {
        this.homePlayer7 = homePlayer7;
    }

    public Long getHomePlayer8() {
        return homePlayer8;
    }

    public void setHomePlayer8(Long homePlayer8) {
        this.homePlayer8 = homePlayer8;
    }

    public Long getHomePlayer9() {
        return homePlayer9;
    }

    public void setHomePlayer9(Long homePlayer9) {
        this.homePlayer9 = homePlayer9;
    }

    public Long getHomePlayer10() {
        return homePlayer10;
    }

    public void setHomePlayer10(Long homePlayer10) {
        this.homePlayer10 = homePlayer10;
    }

    public Long getHomePlayer11() {
        return homePlayer11;
    }

    public void setHomePlayer11(Long homePlayer11) {
        this.homePlayer11 = homePlayer11;
    }

    public Long getAwayPlayer1() {
        return awayPlayer1;
    }

    public void setAwayPlayer1(Long awayPlayer1) {
        this.awayPlayer1 = awayPlayer1;
    }

    public Long getAwayPlayer2() {
        return awayPlayer2;
    }

    public void setAwayPlayer2(Long awayPlayer2) {
        this.awayPlayer2 = awayPlayer2;
    }

    public Long getAwayPlayer3() {
        return awayPlayer3;
    }

    public void setAwayPlayer3(Long awayPlayer3) {
        this.awayPlayer3 = awayPlayer3;
    }

    public Long getAwayPlayer4() {
        return awayPlayer4;
    }

    public void setAwayPlayer4(Long awayPlayer4) {
        this.awayPlayer4 = awayPlayer4;
    }

    public Long getAwayPlayer5() {
        return awayPlayer5;
    }

    public void setAwayPlayer5(Long awayPlayer5) {
        this.awayPlayer5 = awayPlayer5;
    }

    public Long getAwayPlayer6() {
        return awayPlayer6;
    }

    public void setAwayPlayer6(Long awayPlayer6) {
        this.awayPlayer6 = awayPlayer6;
    }

    public Long getAwayPlayer7() {
        return awayPlayer7;
    }

    public void setAwayPlayer7(Long awayPlayer7) {
        this.awayPlayer7 = awayPlayer7;
    }

    public Long getAwayPlayer8() {
        return awayPlayer8;
    }

    public void setAwayPlayer8(Long awayPlayer8) {
        this.awayPlayer8 = awayPlayer8;
    }

    public Long getAwayPlayer9() {
        return awayPlayer9;
    }

    public void setAwayPlayer9(Long awayPlayer9) {
        this.awayPlayer9 = awayPlayer9;
    }

    public Long getAwayPlayer10() {
        return awayPlayer10;
    }

    public void setAwayPlayer10(Long awayPlayer10) {
        this.awayPlayer10 = awayPlayer10;
    }

    public Long getAwayPlayer11() {
        return awayPlayer11;
    }

    public void setAwayPlayer11(Long awayPlayer11) {
        this.awayPlayer11 = awayPlayer11;
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
        if (!(object instanceof Matches)) {
            return false;
        }
        Matches other = (Matches) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "soccer.stage.entity.Matches[ id=" + id + " ]";
    }

}
