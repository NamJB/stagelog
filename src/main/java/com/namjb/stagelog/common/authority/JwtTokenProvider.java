package com.namjb.stagelog.common.authority;

import com.namjb.stagelog.common.dto.TokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long expirationMillis;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-millis}") long expirationMillis) {

        // 설정의 Base64 문자열을 원래 키 바이트로 복원하고,
        // JWT 서명에 사용할 SecretKey 객체로 변환
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        // 설정에 작성한 토큰 유효시간을 보관
        this.expirationMillis = expirationMillis;
    }

    public TokenInfo createToken(Authentication authentication){

        Date now = new Date();
        //현재시각에 설정한 유효시간 더해 만료 시각 계싼
        Date expiresAt = new Date(now.getTime() + expirationMillis);

        String authorities = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));

        String token = Jwts.builder()
                .subject(authentication.getName()) //인증된 회원의 로그인 아이디
                .claim("auth",authorities) //회원 권한
                .issuedAt(now) //발급시각
                .expiration(expiresAt) //만료시각
                .signWith(secretKey, Jwts.SIG.HS256) //비밀키로 서명
                .compact(); //최종 jwt 문자열 생성

        return new TokenInfo("Bearer",token);
    }

    public Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(secretKey) // 서버의 비밀키로 서명검증
                .build()  //검사 도구 생성
                .parseSignedClaims(token) //토큰 해석 및 만료 여부 검사
                .getPayload();  //검증된 토큰 내용반환
    }

    public Authentication getAuthentication(String token) {

        Claims claims  = getClaims(token);

        var authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
                claims.get("auth",String.class)
        );

        var principal =  new User(claims.getSubject(),"", authorities);

        //사용자 정보와 권한을 담아서 인증 완료 객체를 반환
        return new UsernamePasswordAuthenticationToken(
                principal, // 누가 요청했는지
                "",  // 토큰으로 확인후 비번 필요 x
                authorities //어떤권한이 있ㄴ느지
        );

    }

}
