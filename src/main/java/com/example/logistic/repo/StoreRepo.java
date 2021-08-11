package com.example.logistic.repo;

import com.example.logistic.entity.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepo extends CrudRepository<Store,Integer> {

}
