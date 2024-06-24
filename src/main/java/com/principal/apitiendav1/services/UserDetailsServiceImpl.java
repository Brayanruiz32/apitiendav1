package com.principal.apitiendav1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.principal.apitiendav1.dto.usuario.ResponseLogin;
import com.principal.apitiendav1.dto.usuario.UsuarioLoginDTO;
import com.principal.apitiendav1.entities.Usuario;
import com.principal.apitiendav1.repositories.UsuarioRepository;
import com.principal.apitiendav1.utils.JwtUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_".concat(usuario.getRol().getNombre())));
        return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), 
        usuario.getContrasenia(), authorities);
    }

    
    public ResponseLogin loginUser(UsuarioLoginDTO usuarioLoginDTO){

        String usuario = usuarioLoginDTO.getUsuario();
        String contrasenia = usuarioLoginDTO.getContrasenia();
        System.out.println(usuarioLoginDTO.toString());
        System.out.println(usuario);
        System.out.println(contrasenia);

        Authentication authentication = this.authenticate(usuario, contrasenia);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication);

        ResponseLogin responseLogin = new ResponseLogin(usuario, token);

        return responseLogin;
    }

    private Authentication authenticate(String usuario, String contrasenia) {
        
        UserDetails userDetails = loadUserByUsername(usuario);
   
        if (userDetails == null) {
            throw new UsernameNotFoundException("No se encuentra el usuario");
        }

        if (!passwordEncoder.matches(contrasenia, userDetails.getPassword())) {
            throw new BadCredentialsException("Las credenciales son incorrectas");
        }
        return new UsernamePasswordAuthenticationToken(usuario, contrasenia, userDetails.getAuthorities());
    }


}
