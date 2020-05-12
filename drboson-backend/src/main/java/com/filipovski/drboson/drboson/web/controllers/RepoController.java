package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.service.GithubService;
import com.filipovski.drboson.drboson.service.dtos.GithubRepoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "*"}, allowCredentials = "true")
@RequestMapping("/repo")
public class RepoController {
    private final GithubService githubService;

    public RepoController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping
    public ResponseEntity<List<GithubRepoDto>> searchRepos(String owner) {
        return ResponseEntity.ok(githubService.getUserRepos(owner));
    }
}
