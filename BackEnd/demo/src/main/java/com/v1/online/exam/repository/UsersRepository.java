package com.v1.online.exam.repository;

import com.v1.online.exam.entity.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{
	Optional<Users> findByEmail(String email);
	Optional<Users> findByName(String username);

}
