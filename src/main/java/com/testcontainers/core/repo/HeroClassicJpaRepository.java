package com.testcontainers.core.repo;


import com.testcontainers.core.entity.Shinobi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class HeroClassicJpaRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addHero(Shinobi shinobi) {
        em.persist(shinobi);
    }

    public Collection<Shinobi> allHeros() {
        return em.createQuery("Select hero FROM Hero hero", Shinobi.class).getResultList();
    }

    public Collection<Shinobi> findHerosBySearchCriteria(String searchCriteria) {
        return em.createQuery("SELECT hero FROM Hero hero " +
                                "where hero.city LIKE :searchCriteria OR " +
                                "hero.name LIKE :searchCriteria OR " +
                                "hero.universum = :searchCriteria",
                        Shinobi.class)
                .setParameter("searchCriteria", searchCriteria).getResultList();
    }
}
