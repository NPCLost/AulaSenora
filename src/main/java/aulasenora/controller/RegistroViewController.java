package aulasenora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aulasenora.model.Usuario;
import aulasenora.service.UsuarioService;

@Controller
@RequestMapping("/registrar")
public class RegistroViewController {

    private final UsuarioService usuarioService;

    public RegistroViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/success")
    public String registrar(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioService.registrar(usuario);
            // después de registrarse, llevar al login con mensaje
            return "redirect:/login?registerSuccess";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}