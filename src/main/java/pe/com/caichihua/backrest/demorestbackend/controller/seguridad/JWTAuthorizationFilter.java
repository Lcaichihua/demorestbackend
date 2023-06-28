package pe.com.caichihua.backrest.demorestbackend.controller.seguridad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  public JWTAuthorizationFilter(AuthenticationManager authManager) {
    super(authManager);
  }

  // Cada peticion- request
  
  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
	  
   //System.out.println("doFilterInternal...x");
   
    String header = req.getHeader(Constants.HEADER_AUTHORIZACION_KEY);
    
    if (header == null || !header.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }
    
    //System.out.println("doFilterInternal...y");
    
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    chain.doFilter(req, res);
  }
  

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
	  
    String token = request.getHeader(Constants.HEADER_AUTHORIZACION_KEY);
    
    if (token != null) {
      
    byte signingKey[] = Constants.SUPER_SECRET_KEY.getBytes();
      
      // Se procesa el token y se recupera el usuario.
      
      String tokenValue=token.replace(Constants.TOKEN_BEARER_PREFIX, "");
      
      String user = Jwts.parser()
            .setSigningKey(signingKey)
            .parseClaimsJws(tokenValue)
            .getBody()
            .getSubject();

      System.out.println("user -> "+user);
      
      Collection<? extends GrantedAuthority> authorities=getAuthorities(tokenValue);
      
      //System.out.println("authorities -> "+authorities);
      
      List<GrantedAuthority> authoritiesItems      = new ArrayList<>();
      
      for (Object authority : authorities.toArray()) {
    	  //System.out.println("authority -> "+authority);
    	  authoritiesItems.add(new SimpleGrantedAuthority(authority.toString()));
      }

      
      //System.out.println("authoritiesItems -> "+authoritiesItems);
      
      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null,authoritiesItems);
      }
      
      return null;
    }
    return null;
  }
  
  public Collection<? extends GrantedAuthority> getAuthorities(String token) {
	  byte signingKey[] = Constants.SUPER_SECRET_KEY.getBytes();
      Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
      return (Collection<? extends GrantedAuthority>) claims.get(Constants.AUTHORITIES);
  }
}