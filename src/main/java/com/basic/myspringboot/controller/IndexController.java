package com.basic.myspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "redirect:/userspage/index";  // redirect: 는 겟맵핑형태로 가는거고
                                            // 그걸안쓰면 페이지자체를 찾는거라 에러남
    }
}
