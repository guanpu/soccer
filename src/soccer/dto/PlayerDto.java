/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.dto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import soccer.entity.PlayerAttributes;

/**
 * DTO object that wraps values of a match to form a classifier
 * compliant "instance".
 * @author pguan
 */
public class PlayerDto {
    @PersistenceUnit(unitName = "soccerPU")
    private EntityManagerFactory emf;
    private final EntityManager em;
    private final String query;

    public PlayerDto(String query) {
        this.em = emf.createEntityManager();
        this.query = query;
    }
    public double getRating() {
        PlayerAttributes player = (PlayerAttributes) em.createQuery(query).getSingleResult();
        long rating = player.getOverallRating();
        return rating * 1.0;
    }
}
