package com.example.sbb.security;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.FileReader;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.*;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.keyPath}")
    private String keyPath; // PEM 파일 경로

    private PrivateKey privateKey;
    private PublicKey publicKey;

    // 토큰의 유효 시간 설정 (예: 1시간)
    private long validityInMilliseconds = 3600000;

    @PostConstruct
    protected void init() throws Exception {
        // PEM 파일로부터 키를 로드합니다.
        this.privateKey = loadPrivateKey(keyPath);
        this.publicKey = loadPublicKey(privateKey);
    }

    // 토큰 생성
    public String createToken(String username) {

        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    // 토큰에서 사용자 이름 추출
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // PEM 형식의 개인 키 로드 (PKCS#1 지원)
    private PrivateKey loadPrivateKey(String filepath) throws Exception {
        PemReader pemReader = new PemReader(new FileReader(filepath));
        PemObject pemObject = pemReader.readPemObject();
        pemReader.close();

        byte[] content = pemObject.getContent();

        PKCS8EncodedKeySpec keySpec;
        try {
            keySpec = new PKCS8EncodedKeySpec(content);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            // PKCS#1 형식인 경우
            RSAPrivateCrtKeySpec rsaKeySpec = decodePKCS1(content);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(rsaKeySpec);
        }
    }

    // PKCS#1 형식을 PKCS#8로 변환하는 메서드
    private RSAPrivateCrtKeySpec decodePKCS1(byte[] keyBytes) throws IOException {
        // BouncyCastle을 사용하여 PKCS#1 형식을 파싱
        try {
            org.bouncycastle.asn1.pkcs.RSAPrivateKey rsaPrivateKey = org.bouncycastle.asn1.pkcs.RSAPrivateKey.getInstance(keyBytes);
            return new RSAPrivateCrtKeySpec(
                    rsaPrivateKey.getModulus(),
                    rsaPrivateKey.getPublicExponent(),
                    rsaPrivateKey.getPrivateExponent(),
                    rsaPrivateKey.getPrime1(),
                    rsaPrivateKey.getPrime2(),
                    rsaPrivateKey.getExponent1(),
                    rsaPrivateKey.getExponent2(),
                    rsaPrivateKey.getCoefficient()
            );
        } catch (Exception e) {
            throw new IOException("Failed to parse PKCS#1 private key", e);
        }
    }

    // 공개 키 로드 (개인 키로부터 추출)
    private PublicKey loadPublicKey(PrivateKey privateKey) throws Exception {
        RSAPrivateCrtKey privk = (RSAPrivateCrtKey) privateKey;
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(
                privk.getModulus(), privk.getPublicExponent());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicKeySpec);
    }
}
