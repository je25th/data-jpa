package study.datajpa.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Member(
    @Id @GeneratedValue
    @Column(name="member_id")
    val id: Long? = null,
    var username: String?,
    var age: Int? = null,

    @ManyToOne
    @JoinColumn(name = "team_id") // fk
    var team: Team? = null
) {

    fun changeTema(team: Team) {
        this.team = team
        team.member.add(this)
    }

    override fun toString(): String {
        //무한루프에 빠질 위험성이 있기 때문에 연관관계 필드는 toString에 안넣는 것을 권장
        return "Member(id=$id, username='$username', age=$age)"
    }
}