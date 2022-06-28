package study.datajpa.repository

import org.springframework.stereotype.Repository
import study.datajpa.entity.Team
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class TeamRepository(

    @PersistenceContext
    private val em: EntityManager
) {
    fun save(team: Team): Team {
        em.persist(team)
        return team
    }

    fun delete(team: Team) {
        em.remove(team)
    }

    fun findAll(): List<Team> {
        return em.createQuery("select t Team t", Team::class.java)
            .resultList
    }

    fun findById(id: Long): Team? {
        return em.find(Team::class.java, id)
    }

    fun count(): Long {
        return em.createQuery("select count(t) from Team t", Long::class.java)
            .singleResult
    }

}