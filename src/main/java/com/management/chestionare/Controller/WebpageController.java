package com.management.chestionare.Controller;

import com.management.chestionare.domain.Rol;
import com.management.chestionare.domain.Utilizator;
import com.management.chestionare.service.ServiceChestionar;
import com.management.chestionare.service.ServiceUtilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@SuppressWarnings("SpellCheckingInspection")
@Controller
@RequestMapping("/")
public class WebpageController {
    private final RestTemplate restTemplate;
    private final ServiceUtilizator serviceUtilizator;
    private final ServiceChestionar serviceChestionar;

    @Autowired
    public WebpageController(RestTemplate restTemplate, ServiceUtilizator serviceUtilizator, ServiceChestionar serviceChestionar) {
        this.restTemplate = restTemplate;
        this.serviceUtilizator = serviceUtilizator;
        this.serviceChestionar = serviceChestionar;
    }

    @GetMapping("/")
    public String principal(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            boolean hasAdministratorRole = authentication
                    .getAuthorities()
                    .stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_" + Rol.ADMINISTRATOR.toString()));
            String currentUserName = authentication.getName();
            System.out.println(currentUserName);
            System.out.println(hasAdministratorRole);
            if (hasAdministratorRole) {
                Optional<Utilizator> utilizatorOptional = serviceUtilizator.findUtilizatorByNumeDeUtilizator(currentUserName);
                if (utilizatorOptional.isPresent()) {
                    Utilizator administrator = utilizatorOptional.get();
                    model.addAttribute("numePrenume", administrator.getNumePrenume());
                    System.out.println(model.getAttribute("numePrenume"));
                    model.addAttribute("listaDeChestionare", serviceChestionar.findAll());
                    model.addAttribute("listaDeChestionareAdministratorCurent", serviceChestionar
                            .findAllByUtilizatorCreator_NumeDeUtilizator(currentUserName));
                }
                return "htmlfiles/administrator/administrator.html";
            } else {
                boolean hasUserRole = authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(r -> r.getAuthority().equals("ROLE_" + Rol.UTILIZATOR_OBISNUIT.toString()));
                System.out.println(hasUserRole);
                if (hasUserRole) {
                    return "htmlfiles/utilizatorObisnuit/utilizatorObisnuit.html";
                } else {
                    return "htmlfiles/invalid.html";
                }
            }
        } else {
            return "htmlfiles/general/invalid.html";
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
