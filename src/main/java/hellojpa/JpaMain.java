package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //회원 저장
            Member2 member = new Member2();
            member.setUsername("member1");
            member.setTeam(team); //jpa가 알아서 team에서 pk 꺼내서 fk로 사용
            em.persist(member);

            em.flush();
            em.clear();

            //조회
            Member2 findMember = em.find(Member2.class, member.getId());

            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());

            //팀 변경 => 외래키 변경 됨
            Team newTeam = em.find(Team.class, 100L); //100L은 DB에 있다고 가정
            findMember.setTeam(newTeam);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
