package com.project.cleanenerg.web.DTO;

import com.project.cleanenerg.Jwt.JwtToken;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthReponse {

    private Long idUsuario;

    private JwtToken token;

}
