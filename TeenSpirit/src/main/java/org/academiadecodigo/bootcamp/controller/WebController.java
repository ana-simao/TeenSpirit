package org.academiadecodigo.bootcamp.controller;


import org.academiadecodigo.bootcamp.model.User;
import org.academiadecodigo.bootcamp.services.AuthService;
import org.academiadecodigo.bootcamp.services.FeedService;
import org.academiadecodigo.bootcamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class WebController {

    private AuthService authService;
    private FeedService feedService;
    private UserService userService;



    @RequestMapping(method = RequestMethod.GET, path = {"", "/", "/home"})
    public String getHome() {

        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/dashboard"})
    public String getDashboard() {

        return "dashboard";
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/about"})
    public String getAbout() {

        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/contact"})
    public String getContact() {

        return "contact";
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/mystash"})
    public String getMyStash(Model model) {
        User user = authService.getAccessingUser();
        model.addAttribute("user", user);
        return "gurpastash";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/signUp")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }



    @RequestMapping(method = RequestMethod.POST, path = {"/save", ""})
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "signUp";
        }
        if(authService.signUp(user.getUsername(), user.getPassword(), user.getEmail())){
            return "dashboard";
        }else {
            redirectAttributes.addFlashAttribute("lastAction", "Username already exists.");
            return "redirect:/signUp";
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String login(Model model) {

        model.addAttribute("user", new User());
        return "login";

    }

    @RequestMapping(method = RequestMethod.POST, path = {"/user", ""})
    public String saveLogin(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        if(authService.logIn(user.getUsername(), user.getPassword())){
            return "dashboard";
        }else {
            redirectAttributes.addFlashAttribute("lastAction", "Username or password incorrect");
            return "redirect:/login/";
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""}, params = "action=cancel")
    public String cancel() {
        return "redirect:/home";
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/logout"})
    public String logout(){
        authService.logout();
        return "home";
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setFeedService(FeedService feedService) {
        this.feedService = feedService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
