package com.examen.domain.data;

import lombok.*;
import javax.validation.constraints.*;
import java.lang.Long;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto{

      private Long id;
      private String nombre;
      private String apellido;
      private String telefono;
      private String correoElectronico;

}