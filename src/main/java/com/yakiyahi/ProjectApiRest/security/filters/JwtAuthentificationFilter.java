package com.yakiyahi.ProjectApiRest.security.filters;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yakiyahi.ProjectApiRest.entities.LoginRequet;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.algorithms.Algorithm;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthentificationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequet loginRequet = this.getLogin(request);


        String username = loginRequet.getUsername();
        String password = loginRequet.getPassword();

        System.out.println(password);
        System.out.println(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successAuthentification");
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm1 = Algorithm.HMAC256("secret".getBytes());

        String jwtaccesstoken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+15*60*1000))
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles",user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm1);

        String refreshtoken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000))
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm1);
       /*
        response.setHeader("access_token",jwtaccesstoken);
        response.setHeader("refresh_token",refreshtoken);*/

        HashMap<String,String> tokens = new HashMap<>();
        tokens.put("access_token",jwtaccesstoken);
        tokens.put("refresh_token",refreshtoken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }
    public LoginRequet getLogin(HttpServletRequest request) {
        BufferedReader reader=null;
        LoginRequet loginRequet=null;
        try {
            reader = request.getReader();
            Gson gson = new Gson();
            loginRequet = gson.fromJson(reader,LoginRequet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(loginRequet==null){
            loginRequet = new LoginRequet();
        }
        return loginRequet;
    }
}
