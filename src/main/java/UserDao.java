import javax.persistence.*;
import java.util.List;

public class UserDao {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");

    public void addUserDetails(String userName, String password, String email,

                               String phone, String city) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // 4. Starting Transaction
            transaction = manager.getTransaction();
            transaction.begin();
            User user = new User();
            user.setUserName(userName);
            user.setPassword1(password);
            user.setEmail(email);
            user.setCity(city);
            user.setPhone(phone);
            manager.persist(user);

            transaction.commit();
            System.out.println("\n\n Details Added \n");

        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
    }

    public User getUser(int user_id)
    {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        User user = manager.find(User.class, 1);
        return user;
    }

    public List<User> getUsers(){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        TypedQuery<User> query =
                manager.createQuery("SELECT u FROM User u", User.class);
        List<User> results = query.getResultList();
        return results;
    }
}
