package com.summer.web.Controller;

import com.summer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consume")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        String url = "http://localhost:8101/user/" + id;
        return this.restTemplate.getForObject(url, User.class);
    }
}
