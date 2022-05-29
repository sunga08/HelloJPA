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
            /*Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            em.persist(member); //멤버 저장*/

            Member member = new Member(200L, "member200");//데이터 찾고
            em.persist(member);

            em.flush();

            System.out.println("==============");

            //findMember.setName("HelloJPA"); //멤버 수정

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
