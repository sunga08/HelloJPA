package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member2 member = new Member2();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homCity", "street", "10000"));
            member.getFavoriteFoods().add("떡볶이");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("곱창");

            member.getAddressHistory().add(new AddressEntity("old1", "street2", "10001"));
            member.getAddressHistory().add(new AddressEntity("old2", "street2", "10002"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==========START===========");
            Member2 findMember = em.find(Member2.class, member.getId()); //지연로딩임

            //Address a = findMember.getHomeAddress();
            //findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            //치킨 -> 한식 변경
            //findMember.getFavoriteFoods().remove("떡볶이");
            //findMember.getFavoriteFoods().add("한식");

            //findMember.getAddressHistory().remove(new Address("old1", "street2", "10001")); //equals를 정확히 넣어줘야 제대로 동작
            //findMember.getAddressHistory().add(new Address("newCity1", "street", "10001"));

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
