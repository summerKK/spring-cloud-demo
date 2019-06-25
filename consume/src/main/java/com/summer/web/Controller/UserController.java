package com.summer.web.Controller;

import com.summer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consume")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public User getUser0(@PathVariable("id") Long id) {
        // 从eureka获取可用服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("user-service");
        ServiceInstance instance = serviceInstances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
        return this.restTemplate.getForObject(url, User.class);
    }

    /**
     * 使用ribbon做负载均衡
     *
     * @param id
     * @return
     */
    @GetMapping("/loadbance/{id}")
    public User getUser1(@PathVariable("id") Long id) {
        String baseUrl = "http://user-service/user/" + id;
        return restTemplate.getForObject(baseUrl, User.class);
    }
}
