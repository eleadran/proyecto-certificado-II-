package pe.edu.idat.demo_proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    // Lista de correos y contraseñas válidas
    private static final Map<String, String> allowedUsers = new HashMap<>();

    static {
        allowedUsers.put("alonso_tocas@trimotors.com", "alonso123");
        allowedUsers.put("adrian_infantes@trimotors.com", "adrian123");
        allowedUsers.put("daniela_cisneros@trimotors.com", "daniela123");
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }

    @PostMapping("/validateLogin")
    public String validateLogin(@RequestParam String email,
                                @RequestParam String password,
                                Model model) {

        // Si el correo existe en la lista
        if (allowedUsers.containsKey(email)) {
            // Validar contraseña
            if (allowedUsers.get(email).equals(password)) {
                return "redirect:/servidor"; // login correcto
            } else {
                model.addAttribute("error", "Contraseña incorrecta.");
                model.addAttribute("email", email);
                return "login"; // vuelve al login mostrando error
            }
        } else {
            // Correo no está registrado → mostrar error
            model.addAttribute("error", "Correo incorrecto.");
            return "login";
        }
    }
}