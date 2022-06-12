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
        var member = Member(username = "memberA")
        val save = memberJpaRepository.save(member)
        val find = memberJpaRepository.find(save.id)

        assertThat(find.id).isEqualTo(member.id)
        assertThat(find.username).isEqualTo(member.username)
        assertThat(find).isEqualTo(member)
    }

}