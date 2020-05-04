package com.filipovski.drboson.drboson.service.impl;

import com.filipovski.drboson.drboson.repository.GithubClient;
import com.filipovski.drboson.drboson.service.GithubService;
import com.filipovski.drboson.drboson.service.dtos.GithubRepoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GithubRepoImpl implements GithubService {
    private final GithubClient gitHubClient;

    public GithubRepoImpl(GithubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @Override
    public List<GithubRepoDto> getUserRepos(String owner) {
        return gitHubClient.getGitHubUserRepos(owner);
    }

    @Override
    public GithubRepoDto getUserRepo(String owner, String repo) {
        return null;
    }
}
