package com.example.logistic.repo;

import com.example.logistic.entity.Good;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoodRepo extends CrudRepository<Good,Integer> {
    @Query("SELECT g FROM Good g WHERE g.name LIKE %?1%")
    public List<Good> search(String keyword);

    @Query("SELECT g FROM Good g WHERE g.store = ?1")
    public List<Good> selectGoodsByStore(int store);
}
