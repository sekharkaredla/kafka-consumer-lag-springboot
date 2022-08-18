package com.lag.analyze.analyze.controller;

import com.lag.analyze.analyze.service.LagAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lag")
public class LagController {

    @Autowired
    public LagAnalyzerService lagAnalyzerService;

    @GetMapping("/{groupName}")
    public ResponseEntity showLag(@PathVariable String groupName) {
        try {
            return ResponseEntity.ok().body(lagAnalyzerService.analyzeLag(groupName));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }
}
