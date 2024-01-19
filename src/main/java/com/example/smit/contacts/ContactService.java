package com.example.smit.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private String numberSample;

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContact()
    {
        return contactRepository.findAll();
    }

    public List<Contact> foundphones() {return contactRepository.findContactByPhoneNumberContains(numberSample);}
    public void setNumberSample(String number)
    {
        // Assuming jsonData is in the format {"searchPhoneNumber":"e"}
        int colonIndex = number.indexOf(':');

        // Extract the value after the colon
        String value = number.substring(colonIndex + 1).trim();

        // Remove trailing "}"
        value = value.replaceAll("\\s*}\\s*$", "");

        // Remove surrounding double quotes if present
        value = value.replaceAll("^\"|\"$", "");

        this.numberSample = value;
        System.out.println(numberSample);
    }
    public void addNewContact(Contact contact) {
        Optional<Contact> contactByName = contactRepository.findContactByPhoneNumber(contact.getPhoneNumber());

        if(contactByName.isPresent())
        {
            throw new IllegalStateException("This contact already exist");
        }
        contactRepository.save(contact);
    }
}
