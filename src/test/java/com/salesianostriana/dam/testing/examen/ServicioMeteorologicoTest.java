package com.salesianostriana.dam.testing.examen;

import com.salesianostriana.dam.testing.examen.model.DatoMeteorologico;
import com.salesianostriana.dam.testing.examen.model.DatoMeterologicoPK;
import com.salesianostriana.dam.testing.examen.repo.DatoMeteorologicoRepository;
import com.salesianostriana.dam.testing.examen.service.ServicioMeteorologico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ServicioMeteorologicoTest {

    @Mock
    DatoMeteorologicoRepository repository;

    @InjectMocks
    ServicioMeteorologico servicioMeteorologicoService;

    @Test
    void mediaMensualWithSuccess(){

        List<DatoMeteorologico> filtradosPorSevilla = repository.buscarPorPoblacion("Sevilla");

        DatoMeterologicoPK datoMeterologicoPK = new DatoMeterologicoPK("Sevilla", LocalDate.now());
        DatoMeterologicoPK datoMeterologico2PK = new DatoMeterologicoPK("Sevilla", LocalDate.now());

        DatoMeteorologico datoMeteorologico = new DatoMeteorologico();
        datoMeteorologico.setId(datoMeterologicoPK);
        datoMeteorologico.setPrecipitacion(3.5);

        DatoMeteorologico datoMeteorologico2 = new DatoMeteorologico();
        datoMeteorologico2.setId(datoMeterologico2PK);
        datoMeteorologico2.setPrecipitacion(5.0);

        Mockito.when(repository.save(datoMeteorologico)).thenReturn(datoMeteorologico);
        Mockito.when(repository.save(datoMeteorologico2)).thenReturn(datoMeteorologico2);
        Mockito.when(repository.buscarPorPoblacion(datoMeterologicoPK.getCiudad())).thenReturn(filtradosPorSevilla);

        Map<String, Double> result = servicioMeteorologicoService.mediaMensual(datoMeterologicoPK.getCiudad());

        Double precipitacionMedia = datoMeteorologico.getPrecipitacion() + datoMeteorologico2.getPrecipitacion() / 2;

        Map<String, Double> expectedResult = new HashMap<>();
        expectedResult.put("Sevilla", precipitacionMedia);

        Assertions.assertEquals(expectedResult.containsKey("Sevilla"), result.containsKey("Sevilla"));
    }

}