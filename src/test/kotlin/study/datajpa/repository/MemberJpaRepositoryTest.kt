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
internal class MemberJpaRepositoryTest {

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @Test
    fun testMember() {
        val member = Member(username = "memberA")
        val save = memberJpaRepository.save(member)
        val find = save.id?.let { memberJpaRepository.find(it) }

        assertThat(find?.id).isEqualTo(member.id)
        assertThat(find?.username).isEqualTo(member.username)
        assertThat(find).isEqualTo(member)
    }

    @Test
    fun basicCRUD() {
        val member1 = Member(username = "member1")
        val member2 = Member(username = "member2")
        memberJpaRepository.save(member1)
        memberJpaRepository.save(member2)

        //단건 조회 검증
        val findMember1 = memberJpaRepository.findById(member1.id!!)
        val findMember2 = memberJpaRepository.findById(member2.id!!)
        assertThat(findMember1).isEqualTo(member1)
        assertThat(findMember2).isEqualTo(member2)

        //리스트 조회 검증
        val all = memberJpaRepository.findAll()
        assertThat(all.size).isEqualTo(2)

        //카운트 검증
        val count = memberJpaRepository.count()
        assertThat(count).isEqualTo(2)

        //삭제 검증
        memberJpaRepository.delete(member1)
        memberJpaRepository.delete(member2)
        assertThat(memberJpaRepository.count()).isEqualTo(0)
    }

}