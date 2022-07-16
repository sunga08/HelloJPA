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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //연관관계 추가 후
            em.persist(member);

            em.flush(); //현재 영속성컨텍스트에 있는거를 디비에 쿼릐를 다 날리고
            em.clear(); //영속성 컨텍스트 초기화

            Member findMember = em.find(Member.class, member.getId());
            //양방향 매핑관계 추가 후
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members){
                System.out.println("m.getUsername() = " + m.getUsername());
            }

//            Team findTeam = findMember.getTeam(); //team -> memeber 는 참조 불가
//            System.out.println("findTeam.getName() = " + findTeam.getName());

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
