/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.dto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * DTO object that wraps values of a team to form a classifier
 * compliant "instance".
 * @author pguan
 */
public class TeamDto {
    @PersistenceUnit(unitName = "soccerPU")
    private EntityManagerFactory emf;
    private EntityManager em;
    private String query;

    public TeamDto(String query) {
        this.em = emf.createEntityManager();
        this.query = query;
    }
    
}
