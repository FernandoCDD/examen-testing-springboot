package com.salesianostriana.dam.testing.examen.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Embeddable
public class DatoMeterologicoPK {

    private String ciudad;
    private LocalDate fecha;


}
