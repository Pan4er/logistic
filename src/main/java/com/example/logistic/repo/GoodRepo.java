package com.example.logistic.repo;

import com.example.logistic.entity.Good;
import org.springframework.data.repository.CrudRepository;

public interface GoodRepo extends CrudRepository<Good,Integer> {
}
