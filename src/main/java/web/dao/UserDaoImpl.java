package web.dao;

import org.springframework.stereotype.Repository;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManagerFactory emf;

    public UserDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<User> showAll() {
        return emf.createEntityManager().createQuery("SELECT user from User as user", User.class).getResultList();
    }

    @Override
    public void addOrUpdateUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (user.getId() != null) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUser(Long id) {
        return emf.createEntityManager().find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
