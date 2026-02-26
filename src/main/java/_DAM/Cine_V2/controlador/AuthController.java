package _DAM.Cine_V2.controlador;

import _DAM.Cine_V2.dto.login.LoginRequestDTO;
import _DAM.Cine_V2.dto.login.LoginResponseDTO;
import _DAM.Cine_V2.servicio.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(usuarioService.login(loginRequestDTO));
    }

}
