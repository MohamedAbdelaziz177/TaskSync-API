package com.SpringProj.todo.Filters;

import com.SpringProj.todo.AppConfigurations.AppUserDetailService;
import com.SpringProj.todo.Services.Jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AppUserDetailService userDetailService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {



        if("/api/auth/login".equals(request.getRequestURI()) ||
                "/api/auth/register".equals(request.getRequestURI()) ||
                "/api/auth/resend-confirmation-code".equals(request.getRequestURI()) ||
                "/api/auth/confirm-email".equals(request.getRequestURI())

        )
        {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        if(token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);

        String username = jwtService.extractUserName(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailService.loadUserByUsername(username);

            if(userDetails == null){

                System.out.println("User not found");

                filterChain.doFilter(request, response);
                return;
            }

            boolean valid =  jwtService.validateToken(token, userDetails);

           // System.out.println("Filter issssss " + valid);

            if(!valid){
                filterChain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authTok =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authTok.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authTok);

        }

        filterChain.doFilter(request, response);

    }
}
