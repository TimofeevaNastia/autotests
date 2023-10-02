package ru.mytest.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressServiceRepository extends CrudRepository<Addresses, Long> {
}
