package com.lance.demo.springboot;

import com.lance.demo.springboot.thymeleaf.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by perdonare on 2017/7/4.
 */
@Controller
@RequestMapping("/")
public class ThemeLeafController {
    @GetMapping("/index")
    public ModelAndView hello() {
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name", "hello world !");
        System.out.println("return");
        return modelAndView;
    }

    @GetMapping("/user")
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView("/user.html");
        User user = new User("lance", 27);
        modelAndView.addObject("user", user);
        System.out.println("return");
        return modelAndView;
    }

    @GetMapping("/user2")
    public ModelAndView getUser(String name) {
        ModelAndView modelAndView = new ModelAndView("/user.html");
        User user = new User(name, 27);
        modelAndView.addObject("user", user);
        System.out.println("return");
        return modelAndView;
    }
}
