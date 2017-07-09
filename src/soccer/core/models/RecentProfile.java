/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import soccer.entity.Matches;
import soccer.entity.Model1;

/**
 *
 * @author pguan
 */
public class RecentProfile {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em = emf.createEntityManager ();
    private List<Model1> list;
    private Map<Integer, Matches> map = new HashMap<>();
    private Map<League, List<Matches>> matchesInStage= new HashMap<>();
    private static final Integer DEPRESSED = 1;
    private static final Integer LOST = 2;
    private static final Integer NORMAL = 3;
    private static final Integer DEDICATED = 4;
    private static final Integer DESPERATED = 5;
    
    public RecentProfile() {
        Query attrQuery = em.createQuery("SELECT t FROM Model1 t where t.homeExpRank>0 AND t.awayExpRank>0");
        this.list = attrQuery.getResultList();
        Query matchQuery = em.createQuery("SELECT t FROM Matches t where t.leagueId=1729 OR t.leagueId=21518 OR t.leagueId=10257 ORDER BY t.season,t.stage");
        List<Matches> matches = matchQuery.getResultList();
        for(Matches m : matches) {
            map.put(m.getMatchApiId(), m);
            League league = new League(m.getSeason(),m.getStage(),m.getLeagueId());
            if(matchesInStage.containsKey(league)) {
              List<Matches> ms = matchesInStage.get(league);
              ms.add(m);
            } else {
              List<Matches> ms = new ArrayList<>();
              ms.add(m);
              matchesInStage.put(league, ms);                
            }
        }
    }
    
    /**
     * For every model1 item, add the morale to the row, and also added stage,season,league into every item BTW.
     */
    public void persistProfile() {
        int total  =list.size();
        em.getTransaction().begin();
        for (int ii = 0; ii < total; ii++) {
            Model1 item = list.get(ii);
            Integer id = item.getId().intValue();
            Matches match = map.get(id);
            if(match == null) {
                continue;
            }
            int stage = match.getStage();
            String season = match.getSeason();
            Long league = match.getLeagueId();
            item.setStage(stage);
            item.setSeason(match.getSeason());
            item.setLeague(match.getLeagueId().intValue());
            if( stage < 5 || stage > 36) {
                item.setHomeMorale(null);
                item.setAwayMorale(null);                
            } else {
                int[] forHome = new int[4];
                int[] forAway = new int[4];
                Long homeId = item.getHomeTeamId();
                Long awayId = item.getHomeTeamId();
                for(int i=0;i<4;i++) {
                    List<Matches> mlist = matchesInStage.get(new League(season,--stage,league));
                    for (int j = 0; j < mlist.size(); j++) {
                        Matches m = mlist.get(j);
                        if(m.getHomeTeamApiId().equals(homeId)) {
                            int homeGoal = m.getHomeTeamGoal();
                            int awayGoal = m.getAwayTeamGoal();
                            if (homeGoal - awayGoal > 0) {
                                forHome[i] = 3;
                            } else if (homeGoal - awayGoal < 0) {
                                forHome[i] = 0;
                            } else {
                                forHome[i] = 1;
                            }
                        } else if(m.getAwayTeamApiId().equals(homeId)) {
                            int homeGoal = m.getHomeTeamGoal();
                            int awayGoal = m.getAwayTeamGoal();
                            if (homeGoal - awayGoal > 0) {
                                forHome[i] = 0;
                            } else if (homeGoal - awayGoal < 0) {
                                forHome[i] = 3;
                            } else {
                                forHome[i] = 1;
                            }
                        }
                        if (m.getHomeTeamApiId().equals(awayId)) {
                            int homeGoal = m.getHomeTeamGoal();
                            int awayGoal = m.getAwayTeamGoal();
                            if (homeGoal - awayGoal > 0) {
                                forAway[i] = 3;
                            } else if (homeGoal - awayGoal < 0) {
                                forAway[i] = 0;
                            } else {
                                forAway[i] = 1;
                            }
                        } else if (m.getAwayTeamApiId().equals(awayId)) {
                            int homeGoal = m.getHomeTeamGoal();
                            int awayGoal = m.getAwayTeamGoal();
                            if (homeGoal - awayGoal > 0) {
                                forAway[i] = 0;
                            } else if (homeGoal - awayGoal < 0) {
                                forAway[i] = 3;
                            } else {
                                forAway[i] = 1;
                            }
                        }
                    }
                }
                item.setHomeMorale(getMorale(homeId, item.getHomeExpRank(), forHome));
                item.setAwayMorale(getMorale(awayId, item.getAwayExpRank(), forAway));
            }
            em.merge(item);   
            System.out.printf("Finished %d One Match, with total being %d \n",ii, total);
        }
        em.getTransaction().commit();
    }
    
    /**
     * Calculate the team's morale according to it's last season rank as well as recent 4 match results.
     * @param teamId
     * @param exp
     * @param result
     * @return 
     */
    public Integer getMorale(Long teamId,int exp, int[] result) {
        if (exp == 0) {
            exp = 8;            
        }
        int recentPoint = 0;
        for (int i = 0; i < result.length; i++) {
            recentPoint += result[i];
        }
        if(exp<4) {//For those who are targeting champion
            if(recentPoint>9) {
                return DEDICATED;
            }
            if(recentPoint<6) {
                return LOST;
            }
            return NORMAL;
        }
        
        if (exp < 8) {//For those who are targeting european battle
            if (recentPoint > 7) {
                return DEDICATED;
            }
            if (recentPoint < 5) {
                return LOST;
            }
            return NORMAL;
        }
        
        if (exp < 13) {//For those who are considered to be mid-class team
            if (recentPoint > 6) {
                return DEDICATED;
            }
            if (recentPoint < 4) {
                return LOST;
            }
            return NORMAL;
        }
        
        if (exp > 12) {//For those who are strieving to stay
            if (recentPoint > 5) {
                return DEDICATED;
            }
            if (recentPoint < 3) {
                return LOST;
            }
            return NORMAL;
        }
        return NORMAL;        
    }
    
    public static void main(String[] args) {
        RecentProfile rp = new RecentProfile();
        rp.persistProfile();
    }
    
    class League implements Serializable{
        private final String season;
        private final Integer stage;
        private final Long league;

        public League(String season, Integer stage, Long league) {
            this.season = season;
            this.stage = stage;
            this.league = league;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 97 * hash + Objects.hashCode(this.season);
            hash = 97 * hash + Objects.hashCode(this.stage);
            hash = 97 * hash + Objects.hashCode(this.league);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final League other = (League) obj;
            if (!Objects.equals(this.season, other.season)) {
                return false;
            }
            if (!Objects.equals(this.stage, other.stage)) {
                return false;
            }
            if (!Objects.equals(this.league, other.league)) {
                return false;
            }
            return true;
        }
        
        
    }
}
