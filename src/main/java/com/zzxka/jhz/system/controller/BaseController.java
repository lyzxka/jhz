package com.zzxka.jhz.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zzxka
 * @date: 2020-07-20
 * @description:
 */
@RestController
public class BaseController {
    @ResponseBody
    @GetMapping("/testApi")
    public void test(HttpServletResponse response) throws Exception{
        System.out.println("saiodhjiashi");
        response.getWriter().println("asdasd");
    }
}
