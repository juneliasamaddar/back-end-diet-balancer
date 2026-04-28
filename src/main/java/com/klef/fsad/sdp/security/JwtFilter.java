package com.klef.fsad.sdp.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter 
{
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException 
    {
        String path = request.getServletPath();

        if (
            path.startsWith("/userapi/loginUser") ||
            path.startsWith("/userapi/registerUser") ||
            path.startsWith("/adminapi/login") ||   // ✅ FIXED
            path.startsWith("/adminapi/register") ||// ✅ FIXED
            path.startsWith("/swagger-ui") ||
            path.startsWith("/v3/api-docs") ||
            path.equals("/swagger-ui.html")
        ) 
        {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) 
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization token missing");
            return;
        }

        String token = authHeader.substring(7);

        try 
        {
            String email = jwtUtil.extractUsername(token);

            if (email != null && jwtUtil.validateToken(token, email)) 
            {
                request.setAttribute("userEmail", email);
                filterChain.doFilter(request, response);
            } 
            else 
            {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
            }
        } 
        catch (Exception e) 
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token validation failed");
        }
    }
}