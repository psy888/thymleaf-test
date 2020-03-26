package com.psy888.thymeleaftest.repository;

import com.psy888.thymeleaftest.model.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity,Long> {

    List<ClientEntity> findByName(String name);

}
