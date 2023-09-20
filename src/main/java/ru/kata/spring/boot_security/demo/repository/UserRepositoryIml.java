package ru.kata.spring.boot_security.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

@Repository
public class UserRepositoryIml implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private final RoleRepository roleRepository;

    public UserRepositoryIml(EntityManager entityManager, RoleRepository roleRepository) {
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
    }

    public User loadUserByUsername(String email) {
            TypedQuery<User> query = entityManager
                    .createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email);
            User user = query.getSingleResult();
            entityManager.close();
            return user;
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select e from User e", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public void update(Long id, User user){
        user.setId(id);
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void setUserRoles (User user, String roleAdmin){
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getRole("USER"));
        if (roleAdmin != null && roleAdmin.equals("ADMIN")) {
            roles.add(roleRepository.getRole("ADMIN"));
        }
        user.setRoles(roles);
    }


}
