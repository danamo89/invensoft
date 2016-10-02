/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IRespuestaPreguntaDao;
import com.invensoft.model.Cuestionario;
import com.invensoft.model.GrupoPreguntas;
import com.invensoft.model.OpcionRespuesta;
import com.invensoft.model.Persona;
import com.invensoft.model.Pregunta;
import com.invensoft.model.RespuestaPregunta;
import com.invensoft.service.IRespuestaPreguntaService;
import com.invensoft.util.MessageBean;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "respuestaPreguntaService")
public class RespuestaPreguntaServiceImpl implements IRespuestaPreguntaService {
    
    @Autowired
    private IRespuestaPreguntaDao respuestaPreguntaDao;
    @Autowired
    private MessageBean messageBean;

    @Override
    public List<RespuestaPregunta> findBy(Persona persona, Cuestionario cuestionario) {
        try {
            return respuestaPreguntaDao.findBy(persona, cuestionario);
        } catch (Exception e) {
            return new LinkedList<>();
        }
    }

    @Override
    public void save(List<RespuestaPregunta> list) {
        try {
            respuestaPreguntaDao.save(list);
        } catch (Exception e) {
            messageBean.addError("Error", "Ha ocurrido un error al guardar las respuestas. " + e.getLocalizedMessage());
        }
    }
    
    @Override
    public void ordernarCuestionario(Cuestionario cuestionario, Persona persona) {
        List<GrupoPreguntas> listGrupoPreguntas = new LinkedList<>();
        listGrupoPreguntas.addAll(cuestionario.getGrupoPreguntasList());
        Collections.sort(listGrupoPreguntas);
        cuestionario.setGrupoPreguntasList(listGrupoPreguntas);

        for (GrupoPreguntas grupoPreguntas : cuestionario.getGrupoPreguntasList()) {
            List<Pregunta> listPregunta = new LinkedList<>();
            listPregunta.addAll(grupoPreguntas.getPreguntaList());
            Collections.sort(listPregunta);
            grupoPreguntas.setPreguntaList(listPregunta);

            grupoPreguntas.setRespuestaPreguntasList(new LinkedList<RespuestaPregunta>());
            for (Pregunta pregunta : grupoPreguntas.getPreguntaList()) {
                RespuestaPregunta respuestaPregunta = new RespuestaPregunta(pregunta);
                //Buscamos si ya existe una respuesta a la pregunta
                for (RespuestaPregunta respuestaPreguntaItem : this.findBy(persona, cuestionario)) {
                    if (Objects.equals(respuestaPregunta.getPregunta().getIdPregunta(), respuestaPreguntaItem.getPregunta().getIdPregunta())) {
                        respuestaPregunta = respuestaPreguntaItem;
                        break;
                    }
                }
                
                List<OpcionRespuesta> listOpcionRespuesta = new LinkedList<>();
                listOpcionRespuesta.addAll(respuestaPregunta.getPregunta().getOpcioneRespuestaList());
                Collections.sort(listOpcionRespuesta);
                respuestaPregunta.getPregunta().setOpcioneRespuestaList(listOpcionRespuesta);

                grupoPreguntas.getRespuestaPreguntasList().add(respuestaPregunta);
            }
        }
    }
}
