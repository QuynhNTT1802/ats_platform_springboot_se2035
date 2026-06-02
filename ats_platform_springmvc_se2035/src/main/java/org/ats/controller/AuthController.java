package org.ats.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auths")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "auths/login";
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam(name = "email") String emailAddress,
                                     @RequestParam(name = "password") String password
    ) {
        System.out.println("Email address : " + emailAddress + "; Password: " + password);

        ModelAndView mv = new ModelAndView();
        if ((!"rec@example.com".equals(emailAddress)) || (!"123456".equals(password))) {
            mv.setViewName("auths/login");
            mv.addObject("errorMessage", "Email or password is invalid!");
        } else {
            mv.setViewName("general_dashboard");
        }
        return mv;
    }

}
