package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.utilities;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service(value = "utilitiesService")
public class UtilitiesService {

    public ZonedDateTime textDateToZonedDateTime(String date) throws BasicException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    .withZone(ZoneId.of("America/Bogota"));
            return ZonedDateTime.parse(date, dtf);
        } catch (Exception e) {
            throw new BasicException( 400, "la fecha no cumple con el formato dd/MM/yyyy, por favor valide la informacion");
        }
    }
}
