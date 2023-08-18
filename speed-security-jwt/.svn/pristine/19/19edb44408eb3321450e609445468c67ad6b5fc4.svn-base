package com.speedfrwk.security.jwt.helper;

import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.speedfrwk.security.jwt.model.UserSecurity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenHelper {

    @Value("${jwt.appname}")
    private String appName;

    @Value("${jwt.secret}")
    public String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.header}")
    private String authHeader;

    static final String AUDIENCE_WEB = "web";

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            if (claims != null) {
            	username = claims.getSubject();	
            }
        } catch (Exception e) {
            log.error("ERROR: ", e.getMessage());
        }
        return username;
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt = null;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            if (claims != null) {
            	issueAt = claims.getIssuedAt();	
            }
        } catch (Exception e) {
        	log.error("ERROR: ", e.getMessage());
        }
        return issueAt;
    }

    public String getAudienceFromToken(String token) {
        String audience = null;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            if (claims != null) {
            	audience = claims.getAudience();
            }
        } catch (Exception e) {
        	log.error("ERROR: ", e.getMessage());
        }
        return audience;
    }

    public String refreshToken(String token) {
        String refreshedToken = null;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            if (claims != null) {
                claims.setIssuedAt(DateHelper.asDate(LocalDate.now()));
                refreshedToken = Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(generateExpirationDate())
                        .signWith( signatureAlgorithm, secret )
                        .compact();
            }
        } catch (Exception e) {
        	log.error("ERROR: ", e.getMessage());
        }
        return refreshedToken;
    }

    public String generateToken(String username) {
        String audience = AUDIENCE_WEB;
        return Jwts.builder()
                .setIssuer(appName)
                .setSubject(username)
                .setAudience(audience)
                .setIssuedAt(DateHelper.asDate(LocalDate.now()))
                .setExpiration(generateExpirationDate())
                .signWith( signatureAlgorithm, secret )
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(DateHelper.asDate(LocalDate.now()).getTime() + expiration * 1000);
    }

    public long getExpiration() {
        return expiration;
    }

    public boolean validateToken(String token, UserSecurity userDetails) {
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()) && !isCreatedBeforeLastPasswordReset(created, userDetails.getPasswordChangedDate()));
    }

    private boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String getToken( HttpServletRequest request ) {
        /**
         *  Getting the token from Authentication header
         *  e.g Bearer your_token
         */
        String authenticationHeader = getAuthHeaderFromHeader( request );
        if ( authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            return authenticationHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader( HttpServletRequest request ) {
        return request.getHeader(authHeader);
    }

}