package com.example.smit.contacts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository
        extends JpaRepository<Contact, Long>
{
    Optional<Contact> findContactByPhoneNumber(String number);

    List<Contact> findContactByPhoneNumberContains(String number);
}
