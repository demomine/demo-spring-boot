package com.lance.demo.springboot.controller;

import com.lance.demo.springboot.controller.model.DemoReq;
import com.lance.demo.springboot.controller.model.DemoRsp;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/swagger")
@Api("swagger controller")
public class SwaggerController {

    @PostMapping("/demo")
    public DemoRsp insertDemo(@Valid DemoReq demoReq) {
        return new DemoRsp();
    }

    @GetMapping("/demo")
    public DemoRsp getDemo(@ModelAttribute  DemoReq demoReq) {
        return new DemoRsp();
    }

    @PutMapping("/demo")
    public DemoRsp putDemo(@RequestBody @Valid DemoReq demoReq) {
        return new DemoRsp();
    }
}
