package com.management.chestionare.Controller;

import com.management.chestionare.domain.Rol;
import com.management.chestionare.domain.Utilizator;
import com.management.chestionare.service.ServiceChestionarEfectSiIntrebareEfect;
import com.management.chestionare.service.ServiceUtilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class ControllerChestionarEfectuat {
    private final ServiceChestionarEfectSiIntrebareEfect serviceChestionarEfectSiIntrebareEfect;
    private final ServiceUtilizator serviceUtilizator;

    @Autowired
    public ControllerChestionarEfectuat(ServiceChestionarEfectSiIntrebareEfect serviceChestionarEfectSiIntrebareEfect, ServiceUtilizator serviceUtilizator) {
        this.serviceChestionarEfectSiIntrebareEfect = serviceChestionarEfectSiIntrebareEfect;
        this.serviceUtilizator = serviceUtilizator;
    }

    @PostMapping(value = "/rezolva-chestionar/{chestionarId}", consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public String adaugareChestionar(@PathVariable Long chestionarId, @RequestParam MultiValueMap<String, String> paramMap, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            boolean hasUserRole = authentication
                    .getAuthorities()
                    .stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_" + Rol.UTILIZATOR_OBISNUIT.toString()));
            String currentUserName = authentication.getName();
            if (hasUserRole) {
                Optional<Utilizator> utilizatorOptional = serviceUtilizator.findUtilizatorByNumeDeUtilizator(currentUserName);
                if (utilizatorOptional.isPresent()) {
                    Utilizator utilizatorObisnuit = utilizatorOptional.get();

                    serviceChestionarEfectSiIntrebareEfect.save(
                            chestionarId,
                            "varianteIntrebare",
                            paramMap,
                            utilizatorObisnuit
                    );

                    model.addAttribute("succes", true);
                    model.addAttribute("mesaj", "Raspunsurile au fost trimise.");

                    return "htmlfiles/utilizatorObisnuit/chestionarRezolvatPaginaDeConfirmare.html";
                } else {
                    model.addAttribute("titlu", "Eroare nume de utilizator");
                    model.addAttribute("mesaj", "Nume de utilizator inexistent.");
                    return "htmlfiles/general/invalid.html";
                }
            } else {
                model.addAttribute("titlu", "Eroare rol de utilizator obisnuit");
                model.addAttribute("mesaj", "Nu aveti rol de utilizator obisnuit.");
                return "htmlfiles/general/invalid.html";
            }
        } else {
            model.addAttribute("titlu", "Eroare server");
            model.addAttribute("mesaj", "Probleme cu SecurityContext.");
            return "htmlfiles/general/invalid.html";
        }
    }
}
