package com.filipovski.drboson.drboson.repository.impl;

import com.filipovski.drboson.drboson.repository.GithubClient;
import com.filipovski.drboson.drboson.service.dtos.GithubRepoDto;
import com.filipovski.drboson.drboson.service.exceptions.GithubNotAvailableException;
import com.filipovski.drboson.drboson.service.exceptions.GithubRepoNotFoundException;
import org.apache.http.HttpHeaders;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GithubClientImpl implements GithubClient {

    private final WebClient.Builder webClientBuilder;

    public GithubClientImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public List<GithubRepoDto> getGitHubUserRepos(String owner) {
        String requestUri = String.format("https://api.github.com/users/%s/repos", owner);

        List<GithubRepoDto> repos = webClientBuilder.build()
                .get()
                .uri(requestUri)
                .header(HttpHeaders.ACCEPT, "Accept: application/vnd.github.v3+json")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new GithubRepoNotFoundException(clientResponse.toString()))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new GithubNotAvailableException(clientResponse.toString()))
                )
                .bodyToMono(new ParameterizedTypeReference<List<GithubRepoDto>>() {})
                .block();

        return repos;
    }

    @Override
    public GithubRepoDto getGitHubUserRepo(String owner, String repo) {
        String requestUri = String.format("https://api.github.com/repos/%s/%s", owner, repo);

        GithubRepoDto githubRepo = webClientBuilder.build()
                .get()
                .uri(requestUri)
                .header(HttpHeaders.ACCEPT, "Accept: application/vnd.github.v3+json")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new GithubRepoNotFoundException(clientResponse.toString()))
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new GithubNotAvailableException(clientResponse.toString()))
                )
                .bodyToMono(GithubRepoDto.class)
                .block();

        return githubRepo;
    }
}
