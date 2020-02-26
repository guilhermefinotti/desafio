package br.com.frwk.desafio.model.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author Guilherme Finotti Carvalho
 *
 */
public class TokenAuthenticationService {

	private static final long EXPIRATION_TIME = 864000000;
	private static final String SECRET = "Framework_Secret"; // palavra secreta para assinar o token
	private static final String TOKEN_PREFIX = "Bearer"; // portador
	private static final String AUTHORIZATION = "Authorization";
	//private static final String USUARIO = "usuario";

	/**
	 * 
	 * @param response
	 * @param usuario
	 */
	public static void addAuthentication(HttpServletResponse response, String usuario) {
		
		String JWT = Jwts.builder().setSubject(usuario)
								   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
								   .signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		if(JWT != null) {

//			String token = TOKEN_PREFIX + " " + JWT;		
			String token = JWT;		
			response.addHeader(AUTHORIZATION, token);
			//response.addHeader(USUARIO, usuario);
	
			response.setContentType("application/json");
			System.err.println("=>response.status=" + response.getStatus());
		}
		
//		Collection<String> headerNames = response.getHeaderNames();
//		for(String headerName : headerNames) {
//			System.out.println("=>" + headerName);
//		}				
//		System.err.println("=>Authorization:" + response.getHeader(AUTHORIZATION).toString());
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public static Authentication getByToken(String token) {
		
		System.err.println("token:" + token);
		
		if(token != null && token.length() > 11) { //TODO: retornando 'Bearer null'
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
			System.err.println("usuario:" + user);
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
		}
		return null;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(AUTHORIZATION);
		if (token != null) {
			return getByToken(token);
		}
		return null;
	}
}