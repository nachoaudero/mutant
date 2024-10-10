package com.example.mutant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
// Formato de respuesta si lo ingresado por POST no es valido, lo recibe desde el Controller
public class ResponseError {
    @JsonProperty("error_message") // Esto sirve para respetar la convencion de snake_case en los formatos JSON
    private String errorMessage;
    @JsonProperty("error_class") // Esto sirve para respetar la convencion de snake_case en los formatos JSON
    private String errorClass;
}
