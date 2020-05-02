package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.service.dtos.GithubRepoDto;

import java.util.List;

public interface GithubClient {
    List<GithubRepoDto> getGitHubUserRepos(String owner);

    GithubRepoDto getGitHubUserRepo(String owner, String repo);
}
