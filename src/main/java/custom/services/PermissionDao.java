package custom.services;

import custom.bean.Permission;
import custom.bean.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PermissionDao {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");


    public static Permission findPermissionById(String id) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Permission permission = manager.find(Permission.class,  Integer.parseInt(id));
        return permission;
    }


}
