package com.emidio.restapiauthtest.security.filters;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.emidio.restapiauthtest.security.dto.AuthDtoRequest;
import com.emidio.restapiauthtest.security.dto.ResponseTokenSimples;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

/**
 * CustomAuthenticationFilter
 */

public class CustomAuthenticationFilter extends GenericFilterBean {

    private Logger log = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    // @Override
    // protected void doFilterInternal(HttpServletRequest request,
    // HttpServletResponse response, FilterChain filterChain)
    // throws ServletException, IOException {

    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // auth.setAuthenticated(true);

    // int x = 0;

    // if (x == 0) {
    // filterChain.doFilter(request, response);
    // }

    // // UsernamePasswordAuthenticationToken authentication = new
    // UsernamePasswordAuthenticationToken(userDetails, null,
    // userDetails.getAuthorities());
    // // authentication.setDetails(new
    // WebAuthenticationDetailsSource().buildDetails(request));
    // // SecurityContextHolder.getContext().setAuthentication(authentication);

    // }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        String email = jsonObject.get("user").getAsString();
        String pass = jsonObject.get("pass").getAsString();

        AuthDtoRequest dtoReq = new AuthDtoRequest();
        dtoReq.setEmail(email);
        dtoReq.setSenha(pass);

        RestTemplate restTemplate = new RestTemplate();
        ResponseTokenSimples result = restTemplate.postForObject("http://localhost:8080/auth", dtoReq,
                (ResponseTokenSimples.class));

        log.info(result.toString());

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        chain.doFilter(request, response);
    }



    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    //         throws IOException, ServletException {

    //     String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

    //     JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

    //     String email = jsonObject.get("user").getAsString();
    //     String pass = jsonObject.get("pass").getAsString();

    //     AuthDtoRequest dtoReq = new AuthDtoRequest();
    //     dtoReq.setEmail(email);
    //     dtoReq.setSenha(pass);

    //     RestTemplate restTemplate = new RestTemplate();
    //     ResponseTokenSimples result = restTemplate.postForObject("http://localhost:8080/auth", dtoReq,
    //             (ResponseTokenSimples.class));

    //     log.info(result.toString());

    //     // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    //     chain.doFilter(request, response);
    // }

}