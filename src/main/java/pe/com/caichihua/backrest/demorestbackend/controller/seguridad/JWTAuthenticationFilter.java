package pe.com.caichihua.backrest.demorestbackend.controller.seguridad;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Date;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pe.com.caichihua.backrest.demorestbackend.entity.security.UsuarioEntity;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/* login */

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {

			//System.out.println("usuario json " + request.getInputStream());

			UsuarioEntity usuarioEntity = new ObjectMapper().readValue(request.getInputStream(), UsuarioEntity.class);

			//System.out.println("usuario Entity" + usuarioEntity);

			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(usuarioEntity.getUsuario(), usuarioEntity.getClave());

			//System.out.println("upat" + upat);

			Authentication aut = authenticationManager.authenticate(upat);

			//System.out.println("aut "+aut);

			return aut;

		} catch (IOException e) {
			System.out.println("attemptAuthentication " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		byte signingKey[] = Constants.SUPER_SECRET_KEY.getBytes();

		
		
		UserDetails userDetails=(UserDetails) auth.getPrincipal();
		
		//System.out.println("User -> "+ userDetails.getUsername());.
		
		 Collection<? extends GrantedAuthority> authorities=userDetails.getAuthorities();
		
		//System.out.println("Authorities -> "+ authorities);
		

		Collection<?> authoritiesItems= authorities.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList()); 
		
		String token = Jwts.builder()
				.setIssuedAt(new Date())
				.setIssuer(Constants.ISSUER_INFO)
				.setSubject(userDetails.getUsername())
				.claim(Constants.AUTHORITIES, authoritiesItems)
				.setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, signingKey)
				.compact();

		///System.out.println("token " + token);

		response.addHeader("Access-Control-Expose-Headers", "Authorization");

		response.addHeader(Constants.HEADER_AUTHORIZACION_KEY, Constants.TOKEN_BEARER_PREFIX + " " + token);

	}

}
