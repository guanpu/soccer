/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.core.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import soccer.entity.Model1;
import java.util.*;
import javax.persistence.Query;
import java.io.*;
import javax.persistence.EntityTransaction;
import soccer.entity.Matches;
/**
 * Based on BuildModel1, adding teamFeature into the database.
 * @author pguan
 */
public class TeamFeatureModel {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("soccerPU");
    private EntityManager em;
    private List<Model1> list;
    private List<Matches> matchlist;
    private Map<TeamYear, Integer> rankMap;
    /**
     * A map holding the match id(correspond to Model1.id) and it's date
     */
    private Map<Long, String> map;
    private static final String PATH = "F:\\data\\football-data\\ranking\\total.csv";
            

    public TeamFeatureModel() throws FileNotFoundException {
        this.em = emf.createEntityManager();
        
        Query attrQuery = em.createQuery("SELECT t FROM Model1 t");
        this.list = attrQuery.getResultList();
        Query matchQuery = em.createQuery("SELECT t FROM Matches t where t.matchDate>'2013-07-01 00:00:00'");
        this.matchlist = matchQuery.getResultList();
        this.map = matchlist.stream().reduce(new HashMap<Long,String>(), (Map<Long, String> mid, Matches m)->{
            mid.put(m.getMatchApiId().longValue(), m.getMatchDate());
            return mid;
        }, (Map<Long, String> map1,Map<Long, String> map2)->{
            map1.putAll(map2);
            return map1;
        });
        
        this.rankMap = new HashMap<>();
        Scanner scanner = new Scanner(new File(PATH));
        while(scanner.hasNext()){
            String s = scanner.nextLine();
            String[] result = s.split(",");
            TeamYear ty = new TeamYear(result[1], Long.parseLong(result[0]));
            rankMap.put(ty, Integer.parseInt(result[2].trim()));                    
        }
        
    }
    
    /**
     * Add home/away team expected rank(last season) and match data.
     */
    public void persistRank() {
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (int i = 0; i < list.size(); i++) {
            Model1 item = list.get(i);
            String dateStirng = map.get(item.getId());
            if(null == dateStirng) {
                continue;
            }
            item.setMatchdate(dateStirng);
            String shortDate = getSeason(dateStirng);
            if(null == shortDate) {
                em.merge(item);
                return;
            } else {
                TeamYear ty = new TeamYear(shortDate, item.getHomeTeamId().longValue());
                Integer hRankInteger = rankMap.get(ty);
                item.setHomeExpRank(hRankInteger);
                TeamYear ty2 = new TeamYear(shortDate, item.getAwayTeamId().longValue());
                Integer aRankInteger = rankMap.get(ty2);
                item.setAwayExpRank(aRankInteger);
                em.merge(item);
            }            
        }
        et.commit();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        TeamFeatureModel tfm = new TeamFeatureModel();
        tfm.persistRank();
    }
    
    public String getSeason(String date) {
        if(date.compareTo("2013-07-11 00:00:00")<0) {
            return null;            
        }else if (date.compareTo("2013-07-11 00:00:00") > 0 && date.compareTo("2014-07-11 00:00:00") < 0) {
            return "2013";
        }else if (date.compareTo("2014-07-11 00:00:00") > 0 && date.compareTo("2015-07-11 00:00:00") < 0) {
            return "2014";
        }else if (date.compareTo("2015-07-11 00:00:00") > 0 && date.compareTo("2016-07-11 00:00:00") < 0) {
            return "2015";
        }
        return null;        
    }
    
    class TeamYear {
        /**
         * League season, like 2013 correspond to season 2013-2014.
         */
        private String date;
        /**
         * Team id
         */
        private Long id;

        public TeamYear(String date, Long id) {
            this.date = date;
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        
        /**
         * Get the value of date
         *
         * @return the value of date
         */
        public String getDate() {
            return date;
        }

        /**
         * Set the value of date
         *
         * @param date new value of date
         */
        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 59 * hash + Objects.hashCode(this.date);
            hash = 59 * hash + Objects.hashCode(this.id);
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
            final TeamYear other = (TeamYear) obj;
            if (!Objects.equals(this.date, other.date)) {
                return false;
            }
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            return true;
        }

    }
    
}
