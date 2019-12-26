package com.garrykevin.payvice.security.impl.jwt;

import com.garrykevin.payvice.security.impl.model.CustomUserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenUtil {

  @Value("${jwt.secret}")
  private String secret;

  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  //retrieve expiration date from jwt token
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  //check if the token has expired
  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }


  public long getUserId(String token) {
    return Long.parseLong(getClaimFromToken(token, Claims::getSubject));
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
  }

  //validate token
  public Boolean validateToken(String token, long userId) {
    final long tokenUserId = getUserId(token);
    return ( tokenUserId == userId && !isTokenExpired(token));
  }

  //create token
  public String generateToken(CustomUserPrincipal customUserPrincipal) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("user_id",customUserPrincipal.getUserDto().getId());
    return doGenerateToken(claims, String.valueOf(customUserPrincipal.getUserDto().getId()));
  }


  private String doGenerateToken(Map<String, Object> claims, String subject) {
    Key key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(key).compact();
  }
}
