package study.datajpa.repository

import org.springframework.stereotype.Repository
import study.datajpa.entity.Member
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class MemberJpaRepository(

    @PersistenceContext
    private val em: EntityManager
) {

    fun save(member: Member): Member {
        em.persist(member)
        return member
    }

    fun delete(member: Member) {
        em.remove(member)
    }

    fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java)
            .resultList
    }

    fun findById(id: Long): Member? {
        return em.find(Member::class.java, id)
    }

    fun count(): Long {
        return em.createQuery("select count(m) from Member m", Long::class.javaObjectType)
            .singleResult
    }

    fun find(id: Long): Member {
        return em.find(Member::class.java, id)
    }
}