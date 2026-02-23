package aulasenora.controller;

import aulasenora.model.Usuario;
import aulasenora.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioViewController {

    private final UsuarioService usuarioService;

    public UsuarioViewController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuarioService.registrar(usuario);
            model.addAttribute("exito", "¡Usuario registrado exitosamente!");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "registro";
    }
}