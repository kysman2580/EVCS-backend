package com.example.evcs.news.model.service;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.evcs.news.model.dto.NewsMainImageDto;
import com.example.evcs.news.model.dto.NewsMainResponseDto;
import java.net.URI;

@Service
@Slf4j
public class NaverSearchService {

    private final RestTemplate restTemplate;

    @Value("${naver.client.id}")
    private String clientId;

    @Value("${naver.client.secret}")
    private String clientSecret;

    @Value("${naver.api.url}")
    private String apiUrl;

    public NaverSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NewsMainResponseDto searchNews(String query) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", clientId);
            headers.set("X-Naver-Client-Secret", clientSecret);
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

            URI uri = UriComponentsBuilder
                    .fromUriString(apiUrl)
                    .path("/v1/search/news.json")
                    .queryParam("query", query)
                    .queryParam("display", 100)
                    .queryParam("sort", "sim")
                    .build()
                    .encode() // << 꼭 인코딩 추가
                    .toUri();

            log.info("요청 URI: {}", uri.toString()); // ✅ 여기 추가!


            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<NewsMainResponseDto> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    NewsMainResponseDto.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("네이버 뉴스 검색 중 오류 발생: " + e.getMessage());
        }
    }
    
    public NewsMainImageDto searchImage(String title) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", clientId);
            headers.set("X-Naver-Client-Secret", clientSecret);
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

            URI uri = UriComponentsBuilder
                    .fromUriString(apiUrl)
                    .path("/v1/search/image.json")
                    .queryParam("query", title)
                    .queryParam("display", 100)
                    .queryParam("sort", "date")
                    .build()
                    .encode()
                    .toUri();

            log.info("요청 URI: {}", uri.toString()); // 요청 URI 로깅

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<NewsMainImageDto> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    entity,
                    NewsMainImageDto.class // 여기서 반환 타입을 변경!
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("네이버 이미지 검색 중 오류 발생: " + e.getMessage());
        }
    }
}
