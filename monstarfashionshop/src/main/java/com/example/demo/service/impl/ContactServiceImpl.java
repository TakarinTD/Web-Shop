package com.example.demo.service.impl;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public boolean sendContact(Contact contact) {
        Contact dbContact = contactRepository.save(contact);
        if (dbContact != null) {
            return true;
        }
        return false;
    }
}
