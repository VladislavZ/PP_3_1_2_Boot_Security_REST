package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Repository
public interface RoleRepository {
    @Query("FROM Role WHERE name=:name")
    public Role getRole(@Param("name") String name);

    public List<Role> getAllRoles();

    public void saveRole(Role role);

    public void deleteRoleById(Long id);

    public Role getRoleById(Long id);

}
