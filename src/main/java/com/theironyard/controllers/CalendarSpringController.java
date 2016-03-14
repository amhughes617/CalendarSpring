package com.theironyard.controllers;

import com.theironyard.entities.Event;
import com.theironyard.entities.Favorite;
import com.theironyard.entities.User;
import com.theironyard.services.EventRepository;
import com.theironyard.services.FavoriteRepository;
import com.theironyard.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by alexanderhughes on 3/14/16.
 */
@Controller
public class CalendarSpringController {
    @Autowired
    EventRepository events;
    @Autowired
    UserRepository users;
    @Autowired
    FavoriteRepository favorites;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            model.addAttribute("user", users.findFirstByName(userName));
            model.addAttribute("userExists", true);
            model.addAttribute("now", LocalDateTime.now());
        }
        model.addAttribute("events", events.findAllByOrderByDateTimeDesc());

        return "home";
    }

    @RequestMapping(path = "/create-event", method = RequestMethod.POST)
    public String createEvent(String description, String dateTime, HttpSession session) {
        User user = users.findFirstByName((String)session.getAttribute("userName"));
        if (user != null) {
            Event event = new Event(description, LocalDateTime.parse(dateTime), user);
            events.save(event);
        }
        return "redirect:/";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String name) {
        User user =  users.findFirstByName(name);
        if (user == null) {
            user = new User(name);
            users.save(user);
        }
        session.setAttribute("userName", name);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/fav", method = RequestMethod.POST)
    public String fav(int id, HttpSession session) {
        User user = users.findFirstByName((String)session.getAttribute("userName"));
        if (user != null) {
            favorites.save(new Favorite(events.findOne(id), user));
        }
        return "redirect:/";
    }
}
