package com.example.se.qlsv.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findByMssv(String mssv);
	
	@Query(value = "SELECT * FROM user WHERE mssv = ?1 AND role_id = ?2", nativeQuery = true)
	public User findByMssvAndRole(String mssv, long roleId); 
}
