package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member2> query = cb.createQuery(Member2.class);

            Root<Member2> m = query.from(Member2.class);

            CriteriaQuery<Member2> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member2> resultList = em.createQuery(cq).getResultList();

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
