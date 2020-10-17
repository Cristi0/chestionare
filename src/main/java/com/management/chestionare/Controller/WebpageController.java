package com.management.chestionare.Controller;

import com.management.chestionare.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("SpellCheckingInspection")
@Controller
@RequestMapping("/")
public class WebpageController {
    private final RestTemplate restTemplate;

    @Autowired
    public WebpageController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String principal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            boolean hasAdministratorRole = authentication
                    .getAuthorities()
                    .stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_"+Rol.ADMINISTRATOR.toString()));
            String currentUserName = authentication.getName();
            System.out.println(currentUserName);
            System.out.println(hasAdministratorRole);
            if (hasAdministratorRole) {
                return "htmlfiles/administrator.html";
            } else {
                boolean hasUserRole = authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(r -> r.getAuthority().equals("ROLE_"+Rol.UTILIZATOR_OBISNUIT.toString()));
                System.out.println(hasUserRole);
                if (hasUserRole) {
                    return "htmlfiles/utilizatorObisnuit.html";
                } else {
                    return "htmlfiles/invalid.html";
                }
            }
        } else {
            return "htmlfiles/invalid.html";
        }
    }

    @GetMapping("/administrator")
    public String administrator() {
        return "htmlfiles/administrator.html";
    }

    @GetMapping("/utilizator")
    public String utilizatorObisnuit() {
        return "htmlfiles/utilizatorObisnuit.html";
    }
}
