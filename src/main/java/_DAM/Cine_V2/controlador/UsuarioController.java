package _DAM.Cine_V2.controlador;

import _DAM.Cine_V2.dto.login.LoginRequestDTO;
import _DAM.Cine_V2.dto.login.LoginResponseDTO;
import _DAM.Cine_V2.dto.request.UsuarioRequestDTO;
import _DAM.Cine_V2.dto.response.UsuarioResponseDTO;
import _DAM.Cine_V2.modelo.Usuario;
import _DAM.Cine_V2.servicio.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return new ResponseEntity<>(usuarioService.save(usuarioRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(usuarioService.update(id, usuarioRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @RestController
    @RequestMapping("/api/v1/auth")
    public class AuthController {

        private final UsuarioService usuarioService;

        public AuthController(UsuarioService usuarioService) {
            this.usuarioService = usuarioService;
        }

        @PostMapping("/login")
        public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
            LoginResponseDTO usuario = usuarioService.login(loginRequest);

            return ResponseEntity.ok(usuario);
        }
    }

}
