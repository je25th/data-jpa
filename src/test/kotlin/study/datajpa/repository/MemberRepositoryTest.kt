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

}