package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Jwt;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.repositories.JwtRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.signing-key}")
    private String signingKey;

    @Value("${jwt.expiration.access-token}")
    private Long accessTokenExpiration;

    @Value("${jwt.expiration.refresh-token}")
    private Long refreshTokenExpiration;

    private final JwtRepository jwtRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
   /* public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    */

    public JwsHeader extractHeader(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getHeader();
    }

    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, accessTokenExpiration);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshTokenExpiration);
    }

    /* public String generateToken(
             Map<String, Objects> extraClaims,
             UserDetails userDetails
             ) {
                 return Jwts
                         .builder()
                         .setClaims(extraClaims)
                         .setSubject(userDetails.getUsername())
                         .setIssuedAt(new Date(System.currentTimeMillis()))
                         .setExpiration(new Date(System.currentTimeMillis() + 10000 *6 *24))
                         .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                         .compact();
     }
     */
    private String buildToken(Map<String, Object> extraClaims,
                              UserDetails userDetails,
                              Long expiration) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(signingKey));
    }

    public void saveUserToken(String token, Users user) {
        jwtRepository.save(
                Jwt.builder()
                        .token(token)
                        .user(user)
                        .build()
        );
    }

    public boolean isTokenValid(String token, UserDetails user) {
        return (extractUsername(token).equals(user.getUsername())
                && !extractExpiration(token).before(new Date()));
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public void revokeAllUserToken(Users user) {
        jwtRepository.findAllValidTokensByUser(user.getId())
                .filter(tokens -> !tokens.isEmpty())
                .ifPresent(
                        tokenList -> tokenList.forEach(
                                t -> t.setRevoked(true)
                        )
                );
    }

    @Transactional(readOnly = true, transactionManager = "transactionManager")
    public boolean isTokenRevoked(String token) {
        return jwtRepository.findByToken(token).filter(Jwt::isRevoked).isPresent();
    }
}
