package com.example.logistic.repo;

import com.example.logistic.entity.Usr;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsrRepo extends CrudRepository<Usr,Integer> {
    @Query("SELECT user FROM Usr user where user.login = ?1")
    public Usr getUsrByLogin(String login);
}
