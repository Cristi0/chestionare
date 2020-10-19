package com.management.chestionare.Controller;

import com.management.chestionare.domain.Intrebare;
import com.management.chestionare.domain.Rol;
import com.management.chestionare.domain.Utilizator;
import com.management.chestionare.domain.VariantaDeRaspuns;
import com.management.chestionare.service.ServiceIntrebare;
import com.management.chestionare.service.ServiceUtilizator;
import com.management.chestionare.service.ServiceVariantaDeRaspuns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class ControllerVariantaDeRaspuns {
    private final ServiceVariantaDeRaspuns serviceVariantaDeRaspuns;
    private final ServiceIntrebare serviceIntrebare;
    private final ServiceUtilizator serviceUtilizator;

    @Autowired
    public ControllerVariantaDeRaspuns(ServiceVariantaDeRaspuns serviceVariantaDeRaspuns, ServiceIntrebare serviceIntrebare, ServiceUtilizator serviceUtilizator) {
        this.serviceVariantaDeRaspuns = serviceVariantaDeRaspuns;
        this.serviceIntrebare = serviceIntrebare;
        this.serviceUtilizator = serviceUtilizator;
    }

    @PostMapping(value ="/variantaDeRaspuns", consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public String adaugareIntrebare(@RequestParam("continut") String continut,
                                    @RequestParam("variantaCorecta") Boolean variantaCorecta,
                                    @RequestParam("intrebareId") Long intrebareId,
                                    Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            boolean hasAdministratorRole = authentication
                    .getAuthorities()
                    .stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_" + Rol.ADMINISTRATOR.toString()));
            String currentUserName = authentication.getName();
            if (hasAdministratorRole) {
                Optional<Utilizator> utilizatorOptional = serviceUtilizator.findUtilizatorByNumeDeUtilizator(currentUserName);
                if (utilizatorOptional.isPresent()) {
                    Utilizator administrator = utilizatorOptional.get();
                    Optional<Intrebare> intrebareOptionala = serviceIntrebare.findById(intrebareId);
                    if (intrebareOptionala.isPresent()) {
                        Intrebare intrebare = intrebareOptionala.get();
                        if (administrator.getUtilizatorId().equals(intrebare.getChestionar().getUtilizatorCreator().getUtilizatorId())) {
                            VariantaDeRaspuns variantaDeRaspuns = new VariantaDeRaspuns(
                                    0L,
                                    continut,
                                    variantaCorecta,
                                    intrebare);
                            serviceVariantaDeRaspuns.save(variantaDeRaspuns);
                            model.addAttribute("succes", true);
                            model.addAttribute("intrebareId", intrebareId);
                            return "htmlfiles/administrator/adaugareVariantaDeRaspunsPtIntrebare.html";
                        } else {
                            model.addAttribute("titlu", "Eroare permisiune");
                            model.addAttribute("mesaj", "Nu aveti permisunea de modificare a unui chestionar pentru care nu sunteti autor.");
                            return "htmlfiles/general/invalid.html";
                        }
                    } else {
                        model.addAttribute("titlu", "Eroare chestionar");
                        model.addAttribute("mesaj", "Chestionar inexistent.");
                        return "htmlfiles/general/invalid.html";
                    }
                } else {
                    model.addAttribute("titlu", "Eroare nume de utilizator");
                    model.addAttribute("mesaj", "Nume de utilizator inexistent.");
                    return "htmlfiles/general/invalid.html";
                }
            } else {
                model.addAttribute("titlu", "Eroare rol de administrator");
                model.addAttribute("mesaj", "Nu aveti rol de administrator.");
                return "htmlfiles/general/invalid.html";
            }
        } else {
            model.addAttribute("titlu", "Eroare server");
            model.addAttribute("mesaj", "Probleme cu SecurityContext.");
            return "htmlfiles/general/invalid.html";
        }
    }
}
