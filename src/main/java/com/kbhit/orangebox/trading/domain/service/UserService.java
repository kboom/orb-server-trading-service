package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient("orb-users")
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/user/{login}")
    Optional<User> findUserByLogin(@PathVariable("login") String login);


}
