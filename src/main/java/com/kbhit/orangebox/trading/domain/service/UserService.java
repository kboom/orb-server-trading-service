package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(serviceId = "orb-users", url = "http://localhost:5000")
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    User findUserByLogin(@PathVariable("login") String login);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{loginList}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> findUserByLogin(@PathVariable("loginList") List<String> loginList);

}
