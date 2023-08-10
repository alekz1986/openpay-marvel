package coderio.open.pay.marvel.filter;

import coderio.open.pay.marvel.services.ValidateJwtTokenService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter /*implements WebFilter*/ {

    private final ValidateJwtTokenService service;


    /*
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {



        String token = getTokenFromRequest(exchange.getRequest());

        Claims claims = this.service.execute(token);

        if (claims != null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(claims, null, null);

            return authenticationManager.authenticate(authenticationToken).

            //SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            return chain.filter(exchange);
        } else {
            return null;
        }
    }
    */


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        Claims claims = this.service.execute(token);
        if (claims != null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(claims, null, null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + message + "\"}");
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}
