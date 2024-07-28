package miu.cs425.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import miu.cs425.configurations.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {

    @Value("${custom.jwt.secret-key}")
    private String secretKey;

    @Value("${custom.jwt.access-token-expired}")
    private Integer accessTokenExpiredTime;

    @Value("${custom.jwt.refresh-token-expired}")
    private Integer refreshTokenExpiredTime;

    public String generateAccessToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails, accessTokenExpiredTime);
    }

    public String generateRefreshToken(UserDetailsImpl userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails, refreshTokenExpiredTime);
    }

    private String createToken(Map<String, Object> claims, UserDetailsImpl userDetails, Integer timeValidity) {
        claims.put("userId", userDetails.getUserId());
        claims.put("username", userDetails.getUsername());
        claims.put("email", userDetails.getEmail());
        claims.put("firstname", userDetails.getFirstname());
        claims.put("lastname", userDetails.getLastname());
        claims.put("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        claims.put("sub", userDetails.getUsername());

        Date expiredTime = new Date(System.currentTimeMillis() + timeValidity * 60 * 1000);
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiredTime)
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
