package com.ashu.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    List<AddressBook> findByLastName(String lastName);

}
