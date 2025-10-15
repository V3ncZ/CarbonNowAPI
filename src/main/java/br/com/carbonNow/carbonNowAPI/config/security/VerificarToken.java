package br.com.carbonNow.carbonNowAPI.config.security;

import br.com.carbonNow.carbonNowAPI.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class VerificarToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Lista de paths públicos (com barras no final para prefixos)
    private static final List<String> PATHS_PUBLICOS = List.of(
            "/swagger-ui/",
            "/v3/api-docs/",
            "/swagger-resources/",
            "/configuration/",
            "/webjars/",
            "/auth/login",
            "/auth/register"
    );

    public VerificarToken(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        // Log opcional para debug
        // System.out.println("Requisição para path: " + path);

        // Se a requisição começa com algum path público, libera direto
        boolean isPathPublico = PATHS_PUBLICOS.stream()
                .anyMatch(publicPath -> path.startsWith(publicPath));

        if (isPathPublico) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7).trim();
            String login = tokenService.validarToken(token);

            UserDetails usuario = usuarioRepository.findByEmail(login);
            if (usuario != null) {
                var authenticationToken = new UsernamePasswordAuthenticationToken(
                        usuario, null, usuario.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } else {
            // Se não houver token e não for path público, pode bloquear ou permitir seguir
            // Normalmente bloqueia, mas deixamos a autorização por SecurityConfig
        }

        filterChain.doFilter(request, response);
    }
}
