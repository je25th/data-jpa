package study.datajpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import study.datajpa.entity.Member

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByUsernameAndAgeGreaterThan(username: String, age: Int): List<Member>

//    @Query(name = "Member.findByUsername")
    fun findByUsername(@Param("username") username: String): List<Member>

    //실무에서 많이 씀!
    //쿼리문에 오타나면 애플리케이션 로딩 시점에 오류남
    @Query("select m from Member m where m.username = :username and m.age = :age")
    fun findUser(@Param("username") username: String, @Param("age") age: Int): List<Member>
}