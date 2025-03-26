package org.example.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            logger.warn("Token não encontrado no header");
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Ignorar as rotas de login e registro (não requerem autenticação)
        if (request.getRequestURI().equals("/users/login") || request.getRequestURI().equals("/users/register")) {
            filterChain.doFilter(request, response);
            return;  // Continua o fluxo sem adicionar autenticação
        }

        var token = this.recoverToken(request);
        if (token != null) {
            var login = tokenService.validateToken(token);
            if (login != null) {
                // Se o token for válido, configura a autenticação
                UserDetails user = userRepository.findByLogin(login);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Token inválido, retorna 401 Unauthorized
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token inválido ou ausente");
                return;  // Não prossegue com a requisição
            }
        }

        filterChain.doFilter(request, response);  // Continua o fluxo
    }
}