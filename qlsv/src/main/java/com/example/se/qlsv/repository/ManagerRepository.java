package com.example.se.qlsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.se.qlsv.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
	public Manager findByUserId(long userId);
}
