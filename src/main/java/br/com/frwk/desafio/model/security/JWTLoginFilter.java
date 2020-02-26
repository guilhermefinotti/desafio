package br.com.frwk.desafio.model.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.frwk.desafio.model.Usuario;

/**
 * 
 * @author Guilherme Finotti Carvalho
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	
	/**
	 * 
	 * @param url
	 * @param authManager
	 */
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }
    


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, 
    											HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        UserCredentials credenciais = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
        
        Authentication auth = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credenciais.getUsuario(),
                        credenciais.getSenha(),
                        Collections.emptyList())); 
        
        return auth;
    }
    


    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            								HttpServletResponse response, 
            								FilterChain chain,
            								Authentication auth) throws IOException, ServletException {
    	
    	String usuario = ((Usuario)auth.getPrincipal()).getUsername();
    	
        TokenAuthenticationService.addAuthentication(response, usuario);        
        
    }
    
    
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, 
    										  HttpServletResponse response,
    										  AuthenticationException failed) throws IOException, ServletException {
    	super.unsuccessfulAuthentication(request, response, failed);
    }
}
