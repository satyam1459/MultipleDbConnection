package com.example.MultipleDb.db2.repo;

import com.example.MultipleDb.db2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Qualifier("db2EntityManagerFactory")
public interface UserRepo extends JpaRepository<User, Integer>{
}
