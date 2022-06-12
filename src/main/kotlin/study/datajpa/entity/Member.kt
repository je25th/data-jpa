package study.datajpa.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(
    @Id @GeneratedValue
    val id: Long? = null,
    var username: String
)