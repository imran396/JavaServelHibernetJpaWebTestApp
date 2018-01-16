package custom.services;

import custom.bean.Permission;
import custom.bean.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

public class AclDao {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");

    public static void setPermission(Role role){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Set<Permission> set = new HashSet<>();

        Permission  permission1 = PermissionDao.findPermissionById("1");
        Permission  permission2 = PermissionDao.findPermissionById("2");

        set.add(permission1);
        set.add(permission2);

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            role.setPermission(set);
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

    public static void test(){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JavaHelps" );
        EntityManager entitymanager = emfactory.createEntityManager( );
        entitymanager.getTransaction( ).begin( );

        Permission permission1 = new Permission();
        permission1.setName("permission1");

        Permission permission2 = new Permission();
        permission2.setName("permission2");

        Permission permission3 = new Permission();
        permission3.setName("permission3");

        entitymanager.persist(permission1);
        entitymanager.persist(permission2);
        entitymanager.persist(permission3);

        Set<Permission> permissionset1 = new HashSet();
        permissionset1.add(permission1);
        permissionset1.add(permission2);
        permissionset1.add(permission3);

        Role role = new Role();
        role.setName("ha ha");
        role.setPermission(permissionset1);
        entitymanager.persist(role);

        entitymanager.getTransaction( ).commit( );
        entitymanager.close( );
        emfactory.close( );
    }
}
