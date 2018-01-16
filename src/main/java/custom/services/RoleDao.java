package custom.services;

import custom.bean.Role;

import javax.persistence.*;
import java.util.List;

public class RoleDao {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");

    public static void add(String name) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            Role role = new Role();
            role.setName(name);
            manager.persist(role);
            transaction.commit();
            System.out.println("\n\n Role Added \n");

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

    public static void edit(Role role){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.merge(role);
            transaction.commit();
            System.out.println("\n\n Role Added \n");

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

    /**
     * @return
     */
    public static List<Role> getRoles() {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        TypedQuery<Role> query =
                manager.createQuery("SELECT u FROM Role u", Role.class);
        return query.getResultList();

    }

    public static Role findRoleById(String id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Role role = manager.find(Role.class,  Integer.parseInt(id));
        return role;
    }
}
