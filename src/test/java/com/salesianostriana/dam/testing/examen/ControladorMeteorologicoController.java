package com.salesianostriana.dam.testing.examen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.testing.examen.controller.ControladorMeteorologico;
import com.salesianostriana.dam.testing.examen.dto.GetItemMediaFechaPoblacionDto;
import com.salesianostriana.dam.testing.examen.dto.GetMediaFechaPoblacionDto;
import com.salesianostriana.dam.testing.examen.service.ServicioMeteorologico;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureWebMvc
public class ControladorMeteorologicoController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServicioMeteorologico servicioMeteorologico;

    @InjectMocks
    ControladorMeteorologico controladorMeteorologico;

    @Test
    void mediaMensualPorPoblacion() throws Exception {

        String ciudad = "Sevilla";
        String fecha = "09/02/2024";

        double media = 0;
        GetItemMediaFechaPoblacionDto itemDto = new GetItemMediaFechaPoblacionDto(fecha, media);

        List<GetItemMediaFechaPoblacionDto> itemListDto = new ArrayList<>();
        itemListDto.add(itemDto);

        GetMediaFechaPoblacionDto dto = new GetMediaFechaPoblacionDto(ciudad, itemListDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/meteo/media/mes/{ciudad}", dto)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()
        );
    }

}