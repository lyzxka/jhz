package com.zzxka.jhz.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zzxka
 * @date: 2020-07-20
 * @description:
 */
@RestController
public class BaseController {
    @GetMapping("/testApi")
    public void test(){

    }
}
