package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleRepositoryIml implements RoleRepository{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role getRole(String name) {
        TypedQuery<Role> typedQuery = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name",
                Role.class);
        typedQuery.setParameter("name", name);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return  entityManager.createQuery("select e from Role e", Role.class).getResultList();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        entityManager.createQuery("DELETE FROM Role u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();

    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
