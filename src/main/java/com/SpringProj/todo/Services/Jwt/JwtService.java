package com.SpringProj.todo.Services.Jwt;

import com.SpringProj.todo.Model.RefreshToken;
import com.SpringProj.todo.Model.User;
import com.SpringProj.todo.Repository.RefreshTokenRepository;
import com.SpringProj.todo.Responses.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    final private RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.secret}")
    private String secret;



    public RefreshToken generateRefreshToken(Long userId){

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(UUID.randomUUID().toString() + "_" + UUID.randomUUID().toString());
        refreshToken.setRevoked(false);
        refreshToken.setCreatedAt(new Date(System.currentTimeMillis()));
        refreshToken.setExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15));

        return refreshToken;
    }

    public boolean validateRefreshToken(String refreshToken) {

        Optional<RefreshToken> refTok =  refreshTokenRepository.findByToken(refreshToken);

        if(refTok.isEmpty())
            return false;

        if(refTok.get().isRevoked())
            return false;

        if(refTok.get().getExpiresAt().before(new Date(System.currentTimeMillis())))
            return false;

        return true;

    }

    public TokenResponse getTokens(User user)
    {
        TokenResponse tokenResponse = new TokenResponse();

        tokenResponse.setAccessToken(generateToken(user,new HashMap<>()));
        tokenResponse.setRefreshToken(generateRefreshToken(user.getId()).getToken());

        return tokenResponse;
    }


    public String generateToken(UserDetails userDetails, HashMap<String, Object> claims) {

        String token =  Jwts.builder()
                .subject(userDetails.getUsername())
                .claims()
                .add(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(getExpiry())
                .and()
                .signWith(getKey())
                .compact();

        return token;

    }

    public boolean validateToken(String token, UserDetails userDetails)
    {
        return !isExpired(token) && extractUserName(token).equals(userDetails.getUsername());
    }

    // just to implement DRY
    public Key getKey(){

        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // Verify with takes SecretKey not Key :(
    public SecretKey getSecretKey(){

        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }



    public Date getExpiry(){
        return new Date(System.currentTimeMillis() + 1000 * 60 * 15);
    }

    public boolean isExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
