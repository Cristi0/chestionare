package com.management.chestionare.Controller;

import com.management.chestionare.domain.Chestionar;
import com.management.chestionare.domain.Intrebare;
import com.management.chestionare.service.ServiceChestionar;
import com.management.chestionare.service.ServiceIntrebare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpellCheckingInspection")
@Controller
@RequestMapping("/")
public class ControllerChestionar {
    private final ServiceChestionar serviceChestionar;
    private final ServiceIntrebare serviceIntrebare;

    @Autowired
    public ControllerChestionar(ServiceChestionar serviceChestionar, ServiceIntrebare serviceIntrebare) {
        this.serviceChestionar = serviceChestionar;
        this.serviceIntrebare = serviceIntrebare;
    }

    @GetMapping("/chestionar/{chestionarId}")
    public String afisareChestionar(@PathVariable Long chestionarId, Model model) {
        Optional<Chestionar> chestionarOptional = serviceChestionar.findById(chestionarId);
        if (chestionarOptional.isPresent()) {
            Chestionar chestionar = chestionarOptional.get();
            model.addAttribute("chestionar", chestionar);
            List<Intrebare> intrebariChestionar = serviceIntrebare.findAllByChestionar_ChestionarId(chestionarId);
            model.addAttribute("intrebariChestionar", intrebariChestionar);
        }
        return "htmlfiles/administrator/afisareChestionar.html";
    }
}
