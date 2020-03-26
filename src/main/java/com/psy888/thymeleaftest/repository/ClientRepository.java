package com.psy888.thymeleaftest.repository;

import com.psy888.thymeleaftest.model.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    List<ClientEntity> findByName(String name);
    @Query("SELECT c FROM ClientEntity c ORDER BY c.name ASC")
    List<ClientEntity> findAllAndOrderByName();

    @Query("SELECT c FROM ClientEntity c ORDER BY c.phone ASC")
    List<ClientEntity> findAllAndOrderByPhone();

    @Query("SELECT c FROM ClientEntity c ORDER BY c.email ASC")
    List<ClientEntity> findAllAndOrderByEmail();

    List<ClientEntity> findByPhoneContaining(String query);
}
