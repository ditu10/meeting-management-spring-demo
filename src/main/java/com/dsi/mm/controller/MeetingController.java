package com.dsi.mm.controller;

import com.dsi.mm.model.Meeting;
import com.dsi.mm.model.User;
import com.dsi.mm.repository.MeetingRepository;
import com.dsi.mm.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class MeetingController {
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    public MeetingController(UserRepository userRepository, MeetingRepository meetingRepository) {
        this.userRepository = userRepository;
        this.meetingRepository = meetingRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        List<Meeting> meetings = meetingRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("meetings", meetings);
        return "index";
    }
    @PostMapping("/addUser")
    public String handleAddUser(@ModelAttribute User user, RedirectAttributes attributes) {
        System.err.println(user);
        if(user.getName().length() < 3) {
            attributes.addFlashAttribute("error", "user name at least contains 3 characters");
            return "redirect:/";
        }
        userRepository.save(user);
        return "redirect:/";
    }
    @GetMapping("/users/search")
    private ResponseEntity<?> getUsers(@RequestParam String query) {
        List<User> users = userRepository.getUsersByNameContaining(query);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/addMeeting")
    public String handleAddMeeting(@ModelAttribute Meeting meeting) {
        System.out.println(meeting);
        meetingRepository.save(meeting);
        return "redirect:/";
    }



}
