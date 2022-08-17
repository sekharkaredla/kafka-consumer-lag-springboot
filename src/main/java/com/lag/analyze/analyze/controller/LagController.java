package com.lag.analyze.analyze.controller;

import com.lag.analyze.analyze.service.LagAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/lag")
public class LagController {

    @Autowired
    public LagAnalyzerService lagAnalyzerService;

    @GetMapping("/lag")
    public String showLag() {
        try {
            lagAnalyzerService.analyzeLag("temp_group");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            return "false";
        }
    }
}
