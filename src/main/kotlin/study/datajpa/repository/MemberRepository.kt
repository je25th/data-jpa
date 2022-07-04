package study.datajpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import study.datajpa.entity.Member

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>

//    @Query(name = "Member.findByUsername")
    fun findByUsername(@Param("username") username: String): List<Member>
}