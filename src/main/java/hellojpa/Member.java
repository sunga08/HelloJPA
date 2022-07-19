package hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id //PK가 무엇인지를 알려줌
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    /**
     * 객체 지향 모델링 (객체 연관관계 사용)
     */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID") //many to one 관계로 조인하는 컬럼
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team){
        this.team = team;
    }
    /*public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this); //연관관계 편의 메소드
    }*/


}
