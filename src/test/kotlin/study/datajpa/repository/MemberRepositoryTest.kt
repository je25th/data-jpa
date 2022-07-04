package study.datajpa.repository

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import study.datajpa.entity.Member

@SpringBootTest
@Transactional
@Rollback(false)
internal class MemberRepositoryTest {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Test
    fun testMember() {
        var member = Member(username = "memberA")
        val save = memberRepository.save(member)
        val find: Member? = save.id?.let { memberRepository.findById(it) }?.get()

        assertThat(find?.id).isEqualTo(member.id)
        assertThat(find?.username).isEqualTo(member.username)
        assertThat(find).isEqualTo(member)
    }

    @Test
    fun basicCRUD() {
        val member1 = Member(username = "member1")
        val member2 = Member(username = "member2")
        memberRepository.save(member1)
        memberRepository.save(member2)

        //단건 조회 검증
        val findMember1 = memberRepository.findById(member1.id!!)
        val findMember2 = memberRepository.findById(member2.id!!)
        assertThat(findMember1.get()).isEqualTo(member1)
        assertThat(findMember2.get()).isEqualTo(member2)

        //리스트 조회 검증
        val all = memberRepository.findAll()
        assertThat(all.size).isEqualTo(2)

        //카운트 검증
        val count = memberRepository.count()
        assertThat(count).isEqualTo(2)

        //삭제 검증
        memberRepository.delete(member1)
        memberRepository.delete(member2)
        assertThat(memberRepository.count()).isEqualTo(0)
    }

    @Test
    fun findByUsernameAndAgeGreaterThan() {
        val m1 = Member(username = "AAA", age = 10)
        val m2 = Member(username = "AAA", age = 20)
        memberRepository.save(m1)
        memberRepository.save(m2)

        val result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15)

        assertThat(result?.size).isEqualTo(1)
        assertThat(result?.get(0)?.username).isEqualTo("AAA")
        assertThat(result?.get(0)?.age).isEqualTo(20)

    }

    @Test
    fun testNamedQuery() {
        val m1 = Member(username = "AAA", age = 10)
        val m2 = Member(username = "BBB", age = 20)
        memberRepository.save(m1)
        memberRepository.save(m2)

        val result = memberRepository.findByUsername("AAA").get(0)
        assertThat(result).isEqualTo(m1)
    }

}