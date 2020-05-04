package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.service.dtos.GithubRepoDto;

import java.util.List;

public interface GithubService {
    List<GithubRepoDto> getUserRepos(String owner);

    GithubRepoDto getUserRepo(String owner, String repo);
}
