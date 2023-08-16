package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void getUserByModelAndSeries(String model, int series) {
        Session session = sessionFactory.openSession();
       try (session) {
        String HQL = "from Car c left join fetch c.user where c.model=:model and c.series=:series";
        Car car = session.createQuery(HQL, Car.class)
                .setParameter("model", model).setParameter("series", series).getSingleResult();
        User user = car.getUser();
        System.out.println(user);
    } catch (RuntimeException e) {
           System.out.println("Warning! User with car " + model + " " + series + " is not found");
       }
    }

}
