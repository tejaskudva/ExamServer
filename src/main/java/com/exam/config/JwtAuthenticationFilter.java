package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
    	
    	
    	if ("OPTIONS".equalsIgnoreCase(request.getMethod()) && !request.getRequestURL().toString().equals("http://localhost:8080/auth/login")) {
    		
    		HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest) request;
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            res.setHeader("Access-Control-Max-Age", "3600");
            res.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type");
            
    		res.setStatus(HttpServletResponse.SC_OK);
            System.out.println("caught with options: " + req.getRequestURL());
            
        } else {
        	System.out.println("caught with GET: " + request.getRequestURL());
        	String requestHeader = request.getHeader("Authorization");
            
            System.out.println("@@123--origin: " + request.getHeader("origin"));
            System.out.println("@@456--access-control-request-headers: " + request.getHeader("authorization"));
            
            //Bearer 2352345235sdfrsfgsdfsdf
            System.out.println(" Header :  {}" + requestHeader);
            String username = null;
            String token = null;
            if (requestHeader != null && requestHeader.startsWith("Bearer")) {
                //looking good
                token = requestHeader.substring(7);
                try {

                    username = this.jwtHelper.getUsernameFromToken(token);

                } catch (IllegalArgumentException e) {
                    System.out.println("Illegal Argument while fetching the username !!");
                    e.printStackTrace();
                } catch (ExpiredJwtException e) {
                    System.out.println("Given jwt token is expired !!");
                    e.printStackTrace();
                } catch (MalformedJwtException e) {
                    System.out.println("Some changed has done in token !! Invalid Token");
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();

                }


            } else {
                System.out.println("Invalid Header Value !! ");
            }


            //
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {


                //fetch user detail from username
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
                if (validateToken) {

                    //set the authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);


                } else {
                    System.out.println("Validation fails !!");
                }


            }

            filterChain.doFilter(request, response);
        }
    	
        


    }

}
