package study.datajpa.entity

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootTest
@Transactional
@Rollback(false)
internal class MemberTest {

    @PersistenceContext
    lateinit var em: EntityManager

    @Test
    fun testEntity() {
        val teamA = Team(name = "teamA")
        val teamB = Team(name = "teamB")
        em.persist(teamA)
        em.persist(teamB)

        val member1 = Member(username = "member1", age = 10, team = teamA)
        val member2 = Member(username = "member2", age = 20, team = teamA)
        val member3 = Member(username = "member3", age = 30, team = teamB)
        val member4 = Member(username = "member4", age = 40, team = teamB)
        em.persist(member1)
        em.persist(member2)
        em.persist(member3)
        em.persist(member4)

        //초기화
        em.flush() //DB에 반영
        em.clear() //영속성 컨텍스트 캐시 날림

        //확인
        val members = em.createQuery("select m from Member m", Member::class.java).resultList
        members.forEach{ member ->
            println("member = $member")
            println(" -> member.team = ${member.team}")
        }
    }
}