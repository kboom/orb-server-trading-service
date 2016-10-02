package com.kbhit.orangebox.trading.tests.rest;

import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.filter.FilterContext;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.FilterableRequestSpecification;
import com.jayway.restassured.specification.FilterableResponseSpecification;
import com.kbhit.orangebox.trading.security.AuthoritiesConstants;
import com.kbhit.orangebox.trading.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.google.common.collect.Lists.newArrayList;

public class SecurityFilter implements Filter {

    @Autowired
    TokenProvider tokenProvider;

    private String login;
    private String password;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        String token = tokenProvider.createToken(new TestingAuthenticationToken(login, password, newArrayList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))), false);
        requestSpec.header("Authorization", "Bearer " + token);
        return ctx.next(requestSpec, responseSpec);
    }

    public SecurityFilter setLogin(String login) {
        this.login = login;
        return this;
    }

    public SecurityFilter setPassword(String password) {
        this.password = password;
        return this;
    }

}
