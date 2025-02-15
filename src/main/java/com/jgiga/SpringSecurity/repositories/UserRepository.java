package com.jgiga.SpringSecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jgiga.SpringSecurity.models.User;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {

}