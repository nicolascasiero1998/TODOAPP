package com.casierni.todo.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casierni.todo.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	@Cacheable("roles")
	Role findOneByName(String name);
}
