package com.example.avmed.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface Constant {


    String DEF_DOCUMENT_TYPE= "DNI";
    String SERVICE_TYPE= "Consulta Externa";
    interface AppointmentState {
        String PENDING = "Pendiente";
        String ATTENDED = "Atendido";
        String CANCELED = "Cancelado";
    }

}
