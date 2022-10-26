package com.springboot.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get-api")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET) //컨트롤러에 어떤 메소드를 할당해줄지 정해줌
    public String hello(){
        return "Hello World";
    }

    @GetMapping(value = "/name")
    public String getName(){
        return "suin";
    }

    //주소의 경로를 통해 variable을 넘기고 싶을때
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }


}
