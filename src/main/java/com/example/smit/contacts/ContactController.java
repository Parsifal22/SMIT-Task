package com.example.smit.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@CrossOrigin
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/getAllData")
    public List<Contact> getContact()
    {
        return contactService.getContact();
    }

    @PostMapping("getFiltredData")
    public List<Contact> addSampleNumber(@RequestBody String number){
        contactService.setNumberSample(number);
        return contactService.foundphones();
    }


    @PostMapping("/add")
    public List<Contact> registerNewContact(@RequestBody Contact contact)
    {
        contactService.addNewContact(contact);
        return contactService.getContact();
    }

}
