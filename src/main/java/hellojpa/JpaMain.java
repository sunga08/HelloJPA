package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            //team.getMembers().add(member);
            em.persist(team);

            //회원 저장
            Member2 member = new Member2();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            //team.getMembers().add(member); //양방향 연관관계 -> 양쪽에 값을 모두 셋팅

            //em.flush();
            //em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시
            List<Member2> members = findTeam.getMembers();

            for(Member2 m : members){
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
