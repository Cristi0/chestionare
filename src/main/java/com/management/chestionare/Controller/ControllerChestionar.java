package com.management.chestionare.Controller;

import com.management.chestionare.domain.Chestionar;
import com.management.chestionare.domain.Intrebare;
import com.management.chestionare.domain.Rol;
import com.management.chestionare.domain.Utilizator;
import com.management.chestionare.dtodomain.AdaugareChestionarDTO;
import com.management.chestionare.dtodomain.IntrebareDTO;
import com.management.chestionare.mapper.MapperIntrebare;
import com.management.chestionare.service.ServiceChestionar;
import com.management.chestionare.service.ServiceIntrebare;
import com.management.chestionare.service.ServiceUtilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ControllerChestionar {
    private final ServiceChestionar serviceChestionar;
    private final ServiceIntrebare serviceIntrebare;
    private final ServiceUtilizator serviceUtilizator;
    private final MapperIntrebare mapperIntrebare;

    @Autowired
    public ControllerChestionar(ServiceChestionar serviceChestionar, ServiceIntrebare serviceIntrebare, ServiceUtilizator serviceUtilizator, MapperIntrebare mapperIntrebare) {
        this.serviceChestionar = serviceChestionar;
        this.serviceIntrebare = serviceIntrebare;
        this.serviceUtilizator = serviceUtilizator;
        this.mapperIntrebare = mapperIntrebare;
    }

    @GetMapping("/chestionar/{chestionarId}")
    public String afisareChestionar(@PathVariable Long chestionarId, Model model) {
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
                    Optional<Chestionar> chestionarOptional = serviceChestionar.findById(chestionarId);
                    if (chestionarOptional.isPresent()) {
                        Chestionar chestionar = chestionarOptional.get();
                        if (administrator.getUtilizatorId().equals(chestionar.getUtilizatorCreator().getUtilizatorId())) {
                            model.addAttribute("autor", true);
                        }
                        model.addAttribute("chestionar", chestionar);
                        List<Intrebare> intrebariChestionar = serviceIntrebare.findAllByChestionar_ChestionarId(chestionarId);
                        List<IntrebareDTO> intrebariChestionarDTO = mapperIntrebare.intrebariToIntrebariDTO(intrebariChestionar);
                        model.addAttribute("intrebariChestionar", intrebariChestionarDTO);
                        return "htmlfiles/administrator/afisareChestionar.html";
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

    @PostMapping(value = "/chestionar/format-json")
    public String adaugareChestionar(@RequestBody AdaugareChestionarDTO adaugareChestionarDTO, Model model) {
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
                    Chestionar chestionar = new Chestionar(
                            0L,
                            adaugareChestionarDTO.descriere,
                            0,
                            administrator);
                    serviceChestionar.save(chestionar);
                    model.addAttribute("succes", true);
                    return "htmlfiles/administrator/adaugareChestionar.html";
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

    @PostMapping(value = "/chestionar", consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public String adaugareChestionar(@RequestParam("descriere") String descriere, Model model) {
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
                    Chestionar chestionar = new Chestionar(
                            0L,
                            descriere,
                            0,
                            administrator);
                    serviceChestionar.save(chestionar);
                    model.addAttribute("succes", true);
                    return "htmlfiles/administrator/adaugareChestionar.html";
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

    @PostMapping("/sterge-chestionar/{chestionarId}")
    public String stergereChestionar(@PathVariable Long chestionarId, Model model) {
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
                    Optional<Chestionar> chestionarOptional = serviceChestionar.findById(chestionarId);
                    if (chestionarOptional.isPresent()) {
                        Chestionar chestionar = chestionarOptional.get();
                        if (administrator.getUtilizatorId().equals(chestionar.getUtilizatorCreator().getUtilizatorId())) {
                            serviceChestionar.delete(chestionar);
                            model.addAttribute("listaDeChestionare", serviceChestionar.findAll());
                            model.addAttribute("listaDeChestionareAdministratorCurent", serviceChestionar
                                    .findAllByUtilizatorCreator_NumeDeUtilizator(currentUserName));
                            return "htmlfiles/administrator/administrator.html";
                        } else {
                            model.addAttribute("titlu", "Eroare permisiune");
                            model.addAttribute("mesaj", "Nu aveti permisunea de stergere a unui chestionar pentru care nu sunteti autor.");
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