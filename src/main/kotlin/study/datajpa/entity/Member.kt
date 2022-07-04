package study.datajpa.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQuery

@Entity
@NamedQuery( //NamedQuery는 애플리케이션 로딩 시점에 파싱해서 오류 있는지 애플리케이션 로딩(run) 시점에 알 수 있음
    name = "Member.findByUsername",
    query = "select m from Member m where m.username = :username"
)
class Member(
    @Id @GeneratedValue
    @Column(name="member_id")
    val id: Long? = null,
    var username: String?,
    var age: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY)
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