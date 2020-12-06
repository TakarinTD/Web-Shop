package com.example.demo.controller;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Product;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController extends BaseController{

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public ModelAndView viewContactPage() {
        Contact contact = new Contact();
        List<Product> listProductTopSale = productService.getTopProductSale(9);
        _mvShare.addObject("productsTopSale", listProductTopSale);
        _mvShare.addObject("contact", contact);
        _mvShare.setViewName("contact_us");
        return _mvShare;
    }

    @PostMapping("/contact")
    public ModelAndView sendContact(@ModelAttribute("contact") Contact contact) {
        if(contact == null) {
            _mvShare.addObject("contact_error", "Bạn chưa nhập đầy đủ thông tin!");
            _mvShare.setViewName("contact_us");
        }
        if(contactService.sendContact(contact)) {
            _mvShare.addObject("contact_message", "success");
            _mvShare.setViewName("contact_success");
        } else {
            _mvShare.setViewName("404");
        }
        return _mvShare;
    }
}
