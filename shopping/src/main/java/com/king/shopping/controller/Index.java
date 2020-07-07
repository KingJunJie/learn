package com.king.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/")
@Controller
public class Index {

    @RequestMapping("")
//    @ResponseBody
    public String index(){
        return "index";
    }

    @RequestMapping("/kingList")
    @ResponseBody
    public String getKingList(){
        return null;
    }

}
