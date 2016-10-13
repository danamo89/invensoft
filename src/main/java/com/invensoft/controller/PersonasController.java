/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.CategoriaLaboral;
import com.invensoft.model.Cuestionario;
import com.invensoft.model.CuestionarioSector;
import com.invensoft.model.EducacionFormal;
import com.invensoft.model.EducacionNoFormal;
import com.invensoft.model.EstadoCivil;
import com.invensoft.model.Familiar;
import com.invensoft.model.Pais;
import com.invensoft.model.Persona;
import com.invensoft.model.Documento;
import com.invensoft.model.DocumentoPersona;
import com.invensoft.model.FotoPersona;
import com.invensoft.model.Gremio;
import com.invensoft.model.GrupoPreguntas;
import com.invensoft.model.InformacionLaboral;
import com.invensoft.model.Localidad;
import com.invensoft.model.OpcionRespuesta;
import com.invensoft.model.Provincia;
import com.invensoft.model.Puesto;
import com.invensoft.model.RespuestaPregunta;
import com.invensoft.model.Sector;
import com.invensoft.model.TipoDocumento;
import com.invensoft.model.TipoIdentificacion;
import com.invensoft.service.ICategoriaLaboralService;
import com.invensoft.service.ICuestionarioService;
import com.invensoft.service.IDocumentoService;
import com.invensoft.service.IEstadoCivilService;
import com.invensoft.service.IGremioService;
import com.invensoft.service.ILocalidadService;
import com.invensoft.service.IPaisService;
import com.invensoft.service.IPersonaService;
import com.invensoft.service.IProvinciaService;
import com.invensoft.service.IPuestoService;
import com.invensoft.service.IRespuestaPreguntaService;
import com.invensoft.service.ISectorService;
import com.invensoft.service.ITipoDocumentoService;
import com.invensoft.service.ITipoIdentificacionService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author David
 */
@ManagedBean(name = "personasController")
@ViewScoped
public class PersonasController implements Serializable {
    
    private Persona persona;
    private Familiar familiar;
    private InformacionLaboral informacionLaboral;
    private EducacionFormal educacionFormal;
    private EducacionNoFormal educacionNoFormal;
    private Integer idProvinciaSelected;
    private Puesto puesto;
    private CategoriaLaboral categoriaLaboral;
    
    private List<Persona> personasList;
    private List<TipoIdentificacion> tiposIdentificacionList;
    private List<Pais> paisesList;
    private List<EstadoCivil> estadosCivilesList;
    private List<Integer> rangoAniosPermitidos;
    private List<Sector> sectoresList;
    private List<Puesto> puestosList;
    private List<Provincia> provinciasList;
    private List<Localidad> localidadesList;
    private Map<Integer, List<Localidad>> mapProvinciasLocalidades;
    private List<CategoriaLaboral> categoriasLaboralesList;
    private String[] selectedTiposDocumentos;
    private List<SelectItem> selectTiposDocumentos;
    private List<TipoDocumento> tiposDocumentosList;
    private List<Gremio> gremiosList;

    private Cuestionario cuestionarioSaludOcupacional;
    private Cuestionario cuestionarioDesarrolloProfesional;
    private boolean showPersonasTable;
    private String rutaFoto;

    //Services
    @ManagedProperty(value = "#{personaService}")
    private IPersonaService personasService;
    @ManagedProperty(value = "#{tipoIdentificacionService}")
    private ITipoIdentificacionService tipoIdentificacionService;
    @ManagedProperty(value = "#{paisService}")
    private IPaisService paisService;
    @ManagedProperty(value = "#{estadoCivilService}")
    private IEstadoCivilService estadoCivilService;
    @ManagedProperty(value = "#{tipoDocumentoService}")
    private ITipoDocumentoService tipoDocumentoService;
    @ManagedProperty(value = "#{documentoService}")
    private IDocumentoService documentoService;
    @ManagedProperty(value = "#{sectorService}")
    private ISectorService sectorService;
    @ManagedProperty(value = "#{puestoService}")
    private IPuestoService puestoService;
    @ManagedProperty(value = "#{cuestionarioService}")
    private ICuestionarioService cuestionarioService;
    @ManagedProperty(value = "#{respuestaPreguntaService}")
    private IRespuestaPreguntaService respuestaPreguntaService;
    @ManagedProperty(value = "#{provinciaService}")
    private IProvinciaService provinciaService;
    @ManagedProperty(value = "#{localidadService}")
    private ILocalidadService localidadService;
    @ManagedProperty(value = "#{categoriaLaboralService}")
    private ICategoriaLaboralService categoriaLaboralService;
    @ManagedProperty(value = "#{gremioService}")
    private IGremioService gremioService;

    /**
     * Creates a new instance of PersonasController
     */
    public PersonasController() {
    }

    @PostConstruct
    public void initController() {
        showPersonasTable = true;
        personasList = personasService.findAll();

        familiar = new Familiar();
        educacionFormal = new EducacionFormal();
        educacionNoFormal = new EducacionNoFormal();
        informacionLaboral = new InformacionLaboral();
        categoriaLaboral = new CategoriaLaboral();
        this.loadLists();
    }

    private void loadLists() {
        if (tiposIdentificacionList == null) {
            tiposIdentificacionList = tipoIdentificacionService.findAll();

            // Por si no existen registros en la base de datos
            if (tiposIdentificacionList == null) {
                tiposIdentificacionList = new LinkedList<>();
            }
        }

        if (paisesList == null) {
            paisesList = paisService.findAll();

            // Por si no existen registros en la base de datos
            if (paisesList == null) {
                paisesList = new LinkedList<>();
            }
        }

        if (estadosCivilesList == null) {
            estadosCivilesList = estadoCivilService.findAll();

            // Por si no existen registros en la base de datos
            if (estadosCivilesList == null) {
                estadosCivilesList = new LinkedList<>();
            }
        }

        if (rangoAniosPermitidos == null) {
            rangoAniosPermitidos = new LinkedList<>();
            int anioMaximo = Calendar.getInstance().get(Calendar.YEAR);

            for (int anio = anioMaximo; anio >= anioMaximo - 100; anio--) {
                rangoAniosPermitidos.add(anio);
            }
        }

        if (this.tiposDocumentosList == null) {
            this.tiposDocumentosList = tipoDocumentoService.findAll();

            // Por si no existen registros en la base de datos
            if (tiposDocumentosList == null) {
                tiposDocumentosList = new LinkedList<>();
            }
        }

        if (this.sectoresList == null) {
            this.sectoresList = sectorService.findAll();

            if (sectoresList == null) {
                sectoresList = new LinkedList<>();
            }
        }

        if (this.provinciasList == null) {
            this.provinciasList = provinciaService.findAllOrderBy("orden");
            mapProvinciasLocalidades = new HashMap<>();

            for (Provincia provincia : provinciasList) {
                Map<String, Object> fieldsAndValues = new HashMap<>();
                fieldsAndValues.put("provincia.idProvincia", provincia.getIdProvincia());
                mapProvinciasLocalidades.put(provincia.getIdProvincia(), localidadService.findByOrderBy(fieldsAndValues, new String[]{"orden"}));
            }

            idProvinciaSelected = provinciasList.get(0).getIdProvincia();
            onProvinciaChange();
        }

        if (this.puestosList == null) {
            this.puestosList = puestoService.findAll();

            if (puestosList == null) {
                puestosList = new LinkedList<>();
            }
        }
        
        if(this.categoriasLaboralesList == null) {
            this.categoriasLaboralesList = this.categoriaLaboralService.findAll();
            
            if (categoriasLaboralesList == null) {
                categoriasLaboralesList = new LinkedList<>();
            }
        }
        
        if (this.gremiosList == null) {
            this.gremiosList = this.gremioService.findAll();
            
            if (gremiosList == null) {
                gremiosList = new LinkedList<>();
            }
        }
    }

    private void loadCuestionarios() {
        try {
            cuestionarioSaludOcupacional = cuestionarioService.findCuestionarioSaludOcupacional();
            respuestaPreguntaService.ordernarCuestionario(cuestionarioSaludOcupacional, persona);

            cuestionarioDesarrolloProfesional = cuestionarioService.findCuestionarioDesarrolloProfesional();
            respuestaPreguntaService.ordernarCuestionario(cuestionarioDesarrolloProfesional, persona);

            for (CuestionarioSector cuestionarioSector : persona.getSector().getCuestionariosSectorList()) {
                respuestaPreguntaService.ordernarCuestionario(cuestionarioSector.getCuestionario(), persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onDeletePersona(Persona persona) {
        personasService.delete(persona);
        
        //Recargamos la lista de personas
        this.personasList.clear();
        this.personasList.addAll(personasService.findAll());
    }

    public void onViewPersonaDetailedInfo(Persona persona) {
        this.persona = persona;
        
        this.showPersonasTable = false;
        FacesContext context = FacesContext.getCurrentInstance();

        initDocumentosPersona();
        loadCuestionarios();
        onSectorChange();

        idProvinciaSelected = this.persona.getLocalidad().getProvincia().getIdProvincia();
        onProvinciaChange();

        if (this.persona.getFotoPersona() != null) {
            try {
                String relativePath = "/resources/images";
                String absolutePath = context.getExternalContext().getRealPath(relativePath);
                String fileExtension = this.persona.getFotoPersona().getExtension();
                String fileName = this.persona.getLegajo() + "." + fileExtension;

                FileUtils.writeByteArrayToFile(new File(absolutePath + "/" + fileName), this.persona.getFotoPersona().getFoto());

                this.rutaFoto = relativePath + "/" + fileName;
            } catch (IOException e) {
                this.rutaFoto = "/resources/images/broken.png";
            }
        } else {
            this.rutaFoto = "/resources/images/broken.png";
        }
    }

    public void initDocumentosPersona() {
        for (TipoDocumento tipoDocumento : tiposDocumentosList) {
            for (Documento documento : tipoDocumento.getDocumentosList()) {
                documento.setSelected(false);
                for (DocumentoPersona documentoPersona : persona.getDocumentosPersonaList()) {
                    if (documento.getIdDocumento().equals(documentoPersona.getDocumento().getIdDocumento())) {
                        documento.setSelected(true);
                    }
                }
            }
        }
    }

    public void onCreatePersona() {
        this.persona = new Persona();
        persona.setSector(sectorService.find(sectoresList.get(0).getIdSector()));
        loadCuestionarios();
        this.showPersonasTable = false;
        this.rutaFoto = "/resources/images/broken.png";
    }

    public void onProvinciaChange() {
        if (idProvinciaSelected != null) {
            localidadesList = mapProvinciasLocalidades.get(idProvinciaSelected);
        } else {
            localidadesList = new LinkedList<>();
        }
    }

    public void onAddFamiliar() {
        if (this.persona.getFamiliaresList() == null) {
            this.persona.setFamiliaresList(new LinkedList<Familiar>());
        }

        for (TipoIdentificacion tipoIdentificacion : tiposIdentificacionList) {
            if (tipoIdentificacion.getIdTipoIdentificacion().equals(familiar.getTipoIdentificacion().getIdTipoIdentificacion())) {
                familiar.setTipoIdentificacion(tipoIdentificacion);
            }
        }

        this.familiar.setPersona(this.persona);
        this.persona.getFamiliaresList().add(this.familiar);
        this.familiar = new Familiar();
    }

    public void onViewFamiliarDetailedInfo(Familiar familiar) {
        for (Familiar familiarItem : this.persona.getFamiliaresList()) {
            if (Objects.equals(familiarItem.getIdFamiliar(), familiar.getIdFamiliar())) {
                this.persona.getFamiliaresList().remove(familiarItem);
                break;
            }
        }
        this.familiar = familiar;
    }

    public void onRemoveFamiliar(Familiar familiar) {
        this.persona.getFamiliaresList().remove(familiar);
    }

    public void onAddEducacionFormal() {
        if (this.persona.getEducacionesFormalesList() == null) {
            this.persona.setEducacionesFormalesList(new LinkedList<EducacionFormal>());
        }

        this.educacionFormal.setPersona(this.persona);
        this.persona.getEducacionesFormalesList().add(this.educacionFormal);
        this.educacionFormal = new EducacionFormal();
    }

    public void onViewEducacionFormalDetail(EducacionFormal educacionFormal) {
        for (EducacionFormal educacionFormalItem : this.persona.getEducacionesFormalesList()) {
            if (Objects.equals(educacionFormalItem.getIdEducacionFormal(), educacionFormal.getIdEducacionFormal())) {
                this.persona.getEducacionesFormalesList().remove(educacionFormalItem);
                break;
            }
        }
        this.educacionFormal = educacionFormal;
    }

    public void onRemoveEducacionFormal(EducacionFormal educacionFormal) {
        this.persona.getEducacionesFormalesList().remove(educacionFormal);
    }

    public void onAddEducacionNoFormal() {
        if (this.persona.getEducacionesNoFormalesList() == null) {
            this.persona.setEducacionesNoFormalesList(new LinkedList<EducacionNoFormal>());
        }

        this.educacionNoFormal.setPersona(this.persona);
        this.persona.getEducacionesNoFormalesList().add(this.educacionNoFormal);
        this.educacionNoFormal = new EducacionNoFormal();
    }

    public void onViewEducacionNoFormalDetail(EducacionNoFormal educacionNoFormal) {
        for (EducacionNoFormal educacionNoFormalItem : this.persona.getEducacionesNoFormalesList()) {
            if (Objects.equals(educacionNoFormalItem.getIdEducacionNoFormal(), educacionNoFormal.getIdEducacionNoFormal())) {
                this.persona.getEducacionesNoFormalesList().remove(educacionNoFormalItem);
                break;
            }
        }
        this.educacionNoFormal = educacionNoFormal;
    }

    public void onRemoveEducacionNoFormal(EducacionNoFormal educacionNoFormal) {
        this.persona.getEducacionesNoFormalesList().remove(educacionNoFormal);
    }

    public void onLegajoChange() {
        this.persona.setNumeroIdentificacion(this.persona.getLegajo());
    }

    public void onSectorChange() {
        for (Sector sector : sectoresList) {
            if (sector.getIdSector().equals(persona.getSector().getIdSector())) {
                persona.setSector(sectorService.find(sector.getIdSector()));

                for (CuestionarioSector cuestionarioSector : persona.getSector().getCuestionariosSectorList()) {
                    respuestaPreguntaService.ordernarCuestionario(cuestionarioSector.getCuestionario(), persona);
                }
                break;
            }
        }
    }

    public void onAddInformacionLaboral() {
        if (this.persona.getInformacionLaboralList() == null) {
            this.persona.setInformacionLaboralList(new LinkedList<InformacionLaboral>());
        }

        this.informacionLaboral.setPersona(this.persona);
        this.persona.getInformacionLaboralList().add(this.informacionLaboral);
        this.informacionLaboral = new InformacionLaboral();
    }

    public void onViewInformacionLaboralDetail(InformacionLaboral informacionLaboral) {
        for (InformacionLaboral informacionLaboralItem : this.persona.getInformacionLaboralList()) {
            if (Objects.equals(informacionLaboralItem.getIdInformacionLaboral(), informacionLaboral.getIdInformacionLaboral())) {
                this.persona.getInformacionLaboralList().remove(informacionLaboralItem);
                break;
            }
        }
        this.informacionLaboral = informacionLaboral;
    }

    public void onRemoveInformacionLaboral(InformacionLaboral informacionLaboral) {
        this.persona.getInformacionLaboralList().remove(informacionLaboral);
    }

    public void updateDocumentosPersona() {
        this.persona.getDocumentosPersonaList().clear();

        for (TipoDocumento tipoDocumento : tiposDocumentosList) {
            for (Documento documento : tipoDocumento.getDocumentosList()) {
                if (documento.isSelected()) {
                    this.persona.getDocumentosPersonaList().add(new DocumentoPersona(documento, persona));
                }
            }
        }

//        for (String selectedDocument : selectedTiposDocumentos) {
//            Documento newDoc = null;
//            for (Documento documento : this.tiposDocumentosList) {
//                if (Integer.valueOf(selectedDocument).equals(documento.getIdDocumento())) {
//                    newDoc = documento;
//                    break;
//                }
//            }
//            DocumentoPersona newDocPersona = new DocumentoPersona();
//            newDocPersona.setDocumento(newDoc);
//            newDocPersona.setPersona(this.persona);
//            this.persona.getDocumentosPersonaList().add(newDocPersona);
//        }
    }

    public void onSave() {
        try {
            //Actualizamos el tipo de identificacion
            for (TipoIdentificacion tipoIdentificacion : tiposIdentificacionList) {
                if (tipoIdentificacion.getIdTipoIdentificacion().equals(persona.getTipoIdentificacion().getIdTipoIdentificacion())) {
                    persona.setTipoIdentificacion(tipoIdentificacion);
                }
            }

            //Actualizamos el Pais de origen
            for (Pais pais : paisesList) {
                if (pais.getIdPais().equals(persona.getPaisOrigen().getIdPais())) {
                    persona.setPaisOrigen(pais);
                }
            }

            //Actualizamos el estado civil
            for (EstadoCivil estadoCivil : estadosCivilesList) {
                if (estadoCivil.getIdEstadoCivil().equals(persona.getEstadoCivil().getIdEstadoCivil())) {
                    persona.setEstadoCivil(estadoCivil);
                }
            }

            //Actualizamos la localidad
            for (Localidad localidad : localidadesList) {
                if (localidad.getIdLocalidad().equals(persona.getLocalidad().getIdLocalidad())) {
                    persona.setLocalidad(localidad);
                }
            }

            //Actualizamos el puesto
            for (Puesto puestoItem : puestosList) {
                if (puestoItem.getIdPuesto().equals(persona.getPuesto().getIdPuesto())) {
                    persona.setPuesto(puestoItem);
                }
            }
            
            //Actualizamos la categoria laboral
            for (CategoriaLaboral categoriaLaboralItem : categoriasLaboralesList) {
                if (categoriaLaboralItem.getIdCategoriaLaboral().equals(persona.getIdCategoriaLaboral().getIdCategoriaLaboral())) {
                    persona.setIdCategoriaLaboral(categoriaLaboralItem);
                }
            }
            
            //Actualizamos el gremio
            for (Gremio gremio : gremiosList) {
                if (gremio.getIdGremio().equals(persona.getGremio().getIdGremio())) {
                    persona.setGremio(gremio);
                }
            }

            updateDocumentosPersona();
            
            personasService.save(persona);
            respuestaPreguntaService.save(createListToMergeForCuestionario(cuestionarioSaludOcupacional));
            respuestaPreguntaService.save(createListToMergeForCuestionario(cuestionarioDesarrolloProfesional));
            for (CuestionarioSector cuestionarioSector : persona.getSector().getCuestionariosSectorList()) {
                respuestaPreguntaService.save(createListToMergeForCuestionario(cuestionarioSector.getCuestionario()));
            }
            
            //Recargamos la lista de personas
            this.personasList.clear();
            this.personasList = personasService.findAll();

            this.showPersonasTable = true;

        } catch (Exception e) {
            System.out.println("Error:  " + e.getLocalizedMessage());
        }
    }
    
    private List<RespuestaPregunta> createListToMergeForCuestionario(Cuestionario cuestionario) {
        List<RespuestaPregunta> listRespuestasPreguntas = new LinkedList<>();
        
        try {    
            for (GrupoPreguntas grupoPreguntas : cuestionario.getGrupoPreguntasList()) {
                for (RespuestaPregunta respuestaPreguntaItem : grupoPreguntas.getRespuestaPreguntasList()) {
                    if (respuestaPreguntaItem.getValue() != null && !respuestaPreguntaItem.getValue().isEmpty()) {
                        RespuestaPregunta respuestaPregunta = new RespuestaPregunta(respuestaPreguntaItem.getIdRespuestaPregunta());
                        respuestaPregunta.setPersona(persona);
                        respuestaPregunta.setTextoRespuesta(respuestaPreguntaItem.getTextoRespuesta());
                        respuestaPregunta.setPregunta(respuestaPreguntaItem.getPregunta());

                        if (respuestaPreguntaItem.getOpcionRespuesta() != null && respuestaPreguntaItem.getOpcionRespuesta().getIdOpcionRespuesta() != null) {
                            respuestaPregunta.setOpcionRespuesta(new OpcionRespuesta(respuestaPreguntaItem.getOpcionRespuesta().getIdOpcionRespuesta(), respuestaPreguntaItem.getPregunta()));
                        }

                        listRespuestasPreguntas.add(respuestaPregunta);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listRespuestasPreguntas;
    }

    public void imprimir() {
        try {
            //Generamos el archivo PDF
            String directorioArchivos;
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            directorioArchivos = ctx.getRealPath("/") + "reports";

            String name = directorioArchivos + "/document-report.pdf";

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(name));
            document.open();
            document.add(new Paragraph("Visita http://rolandopalermo.blogspot.com"));
            document.add(new Paragraph("Nombre: " + "Hernan"));
            document.add(new Paragraph("Tipo: " + "Galan"));
            document.add(new Paragraph("Precio neto: " + "100"));
            document.add(new Paragraph("Valor neto: " + "200"));
            document.add(new Paragraph("Síguenos en http://facebook.com/blog.rolandopalermo"));
            document.close();

            //----------------------------
            //Abrimos el archivo PDF
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline=filename=" + name);

            try {
                //response.getOutputStream().write(Util.getBytesFromFile(new java.io.File(name))); 
                response.getOutputStream().flush();
                response.getOutputStream().close();
                context.responseComplete();
            } catch (IOException e) {
                System.out.println("Error:  " + e.getLocalizedMessage());
            }

            this.showPersonasTable = true;
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("Error:  " + e.getLocalizedMessage());
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            final UploadedFile uploadedFile = event.getFile();
            String tempFileName;
            
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            
            if(externalContext.getUserPrincipal() != null) {
                String user = externalContext.getUserPrincipal().getName();
                tempFileName = "tmp_"+user;
            }else {
                tempFileName = new Date().toString();
            }
            
            byte[] content = IOUtils.toByteArray(uploadedFile.getInputstream());
            String extension = uploadedFile.getFileName().split("\\.")[1].toLowerCase();
            
            FotoPersona foto = new FotoPersona();
            foto.setFoto(content);
            foto.setExtension(extension);

            this.persona.setFotoPersona(foto);

            String relativeWebPath = "/resources/images/";
            String absoluteDiskPath = externalContext.getRealPath(relativeWebPath);
            String completeTempFileName = tempFileName+"."+extension;
            
             File tempFile = new File(absoluteDiskPath+"/"+completeTempFileName);
            
            IOUtils.write(content, new FileOutputStream(tempFile));
            this.rutaFoto = relativeWebPath+completeTempFileName;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validarFoto(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();

        UploadedFile file = (UploadedFile) value;
        int bytes = file.getContents().length;

        if (bytes > 15360) {
            msgs.add(new FacesMessage("Archivo demasiado grande"));
        }

        if (!file.getContentType().startsWith("image")) {
            msgs.add(new FacesMessage("El archivo debe ser de tipo Imagen"));
        }

        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public StreamedContent getFotoPersonaView() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            try {
                String image_id = context.getExternalContext().getRequestParameterMap().get("image_id");

                File imgTmp = FileUtils.getFile(FileUtils.getTempDirectory() + "/" + image_id + ".jpg");

                if (imgTmp.exists()) {
                    return new DefaultStreamedContent(new FileInputStream(imgTmp.getAbsoluteFile()), "image/jpg");
                } else {
                    return new DefaultStreamedContent();
                }
            } catch (Exception e) {
                return new DefaultStreamedContent();
            }
        }
    }
    
    public void onExportarPersonaExcel() {
        
        try {
            String fileName = FileUtils.getTempDirectoryPath()+"/"+this.persona.getLegajo()+".xlsx";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.ms-excel");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
            
            XSSFWorkbook workbook = personasService.exportarPersonaExcel(persona);
            
            workbook.write(externalContext.getResponseOutputStream());
            facesContext.responseComplete();
        } catch (IOException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onExportarPersonasAllExcel() {
        
        try {
            String fileName = new Date().toString()+".xlsx";
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseContentType("application/vnd.ms-excel");
            externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
            
            XSSFWorkbook workbook = personasService.exportarPersonasAllExcel();
            
            if(workbook != null) {
                workbook.write(externalContext.getResponseOutputStream());
                facesContext.responseComplete();
            }
        } catch (IOException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }

    public InformacionLaboral getInformacionLaboral() {
        return informacionLaboral;
    }

    public void setInformacionLaboral(InformacionLaboral informacionLaboral) {
        this.informacionLaboral = informacionLaboral;
    }

    public EducacionFormal getEducacionFormal() {
        return educacionFormal;
    }

    public void setEducacionFormal(EducacionFormal educacionFormal) {
        this.educacionFormal = educacionFormal;
    }

    public EducacionNoFormal getEducacionNoFormal() {
        return educacionNoFormal;
    }

    public void setEducacionNoFormal(EducacionNoFormal educacionNoFormal) {
        this.educacionNoFormal = educacionNoFormal;
    }

    public List<Persona> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Persona> personasList) {
        this.personasList = personasList;
    }

    public List<TipoIdentificacion> getTiposIdentificacionList() {
        return tiposIdentificacionList;
    }

    public void setTiposIdentificacionList(List<TipoIdentificacion> tiposIdentificacionList) {
        this.tiposIdentificacionList = tiposIdentificacionList;
    }

    public List<Pais> getPaisesList() {
        return paisesList;
    }

    public void setPaisesList(List<Pais> paisesList) {
        this.paisesList = paisesList;
    }

    public List<EstadoCivil> getEstadosCivilesList() {
        return estadosCivilesList;
    }

    public void setEstadosCivilesList(List<EstadoCivil> estadosCivilesList) {
        this.estadosCivilesList = estadosCivilesList;
    }

    public List<Integer> getRangoAniosPermitidos() {
        return rangoAniosPermitidos;
    }

    public void setRangoAniosPermitidos(List<Integer> rangoAniosPermitidos) {
        this.rangoAniosPermitidos = rangoAniosPermitidos;
    }

    public String[] getSelectedTiposDocumentos() {
        return selectedTiposDocumentos;
    }

    public void setSelectedTiposDocumentos(String[] selectedTiposDocumentos) {
        this.selectedTiposDocumentos = selectedTiposDocumentos;
    }

    public List<SelectItem> getSelectTiposDocumentos() {
        return selectTiposDocumentos;
    }

    public void setSelectTiposDocumentos(List<SelectItem> selectTiposDocumentos) {
        this.selectTiposDocumentos = selectTiposDocumentos;
    }

    public List<TipoDocumento> getTiposDocumentosList() {
        return tiposDocumentosList;
    }

    public void setTiposDocumentosList(List<TipoDocumento> tiposDocumentosList) {
        this.tiposDocumentosList = tiposDocumentosList;
    }

    public ITipoDocumentoService getTipoDocumentoService() {
        return tipoDocumentoService;
    }

    public CategoriaLaboral getCategoriaLaboral() {
        return categoriaLaboral;
    }

    public void setCategoriaLaboral(CategoriaLaboral categoriaLaboral) {
        this.categoriaLaboral = categoriaLaboral;
    }

    public ICategoriaLaboralService getCategoriaLaboralService() {
        return categoriaLaboralService;
    }

    public void setCategoriaLaboralService(ICategoriaLaboralService categoriaLaboralService) {
        this.categoriaLaboralService = categoriaLaboralService;
    }
    
    public void setTipoDocumentoService(ITipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    public boolean isShowPersonasTable() {
        return showPersonasTable;
    }

    public void setShowPersonasTable(boolean showPersonasTable) {
        this.showPersonasTable = showPersonasTable;
    }

    public IPersonaService getPersonasService() {
        return personasService;
    }

    public void setPersonasService(IPersonaService personasService) {
        this.personasService = personasService;
    }

    public ITipoIdentificacionService getTipoIdentificacionService() {
        return tipoIdentificacionService;
    }

    public void setTipoIdentificacionService(ITipoIdentificacionService tipoIdentificacionService) {
        this.tipoIdentificacionService = tipoIdentificacionService;
    }

    public IPaisService getPaisService() {
        return paisService;
    }

    public void setPaisService(IPaisService paisService) {
        this.paisService = paisService;
    }

    public IEstadoCivilService getEstadoCivilService() {
        return estadoCivilService;
    }

    public void setEstadoCivilService(IEstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    public IDocumentoService getDocumentoService() {
        return documentoService;
    }

    public void setDocumentoService(IDocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    public ISectorService getSectorService() {
        return sectorService;
    }

    public void setSectorService(ISectorService sectorService) {
        this.sectorService = sectorService;
    }

    public List<Sector> getSectoresList() {
        return sectoresList;
    }

    public IPuestoService getPuestoService() {
        return puestoService;
    }

    public void setPuestoService(IPuestoService puestoService) {
        this.puestoService = puestoService;
    }

    public void setSectoresList(List<Sector> sectoresList) {
        this.sectoresList = sectoresList;
    }

    public ICuestionarioService getCuestionarioService() {
        return cuestionarioService;
    }

    public void setCuestionarioService(ICuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
    }

    public IRespuestaPreguntaService getRespuestaPreguntaService() {
        return respuestaPreguntaService;
    }

    public void setRespuestaPreguntaService(IRespuestaPreguntaService respuestaPreguntaService) {
        this.respuestaPreguntaService = respuestaPreguntaService;
    }

    public Integer getIdProvinciaSelected() {
        return idProvinciaSelected;
    }

    public void setIdProvinciaSelected(Integer idProvinciaSelected) {
        this.idProvinciaSelected = idProvinciaSelected;
    }

    public IProvinciaService getProvinciaService() {
        return provinciaService;
    }

    public void setProvinciaService(IProvinciaService provinciaService) {
        this.provinciaService = provinciaService;
    }

    public ILocalidadService getLocalidadService() {
        return localidadService;
    }

    public void setLocalidadService(ILocalidadService localidadService) {
        this.localidadService = localidadService;
    }

    public List<Provincia> getProvinciasList() {
        return provinciasList;
    }

    public void setProvinciasList(List<Provincia> provinciasList) {
        this.provinciasList = provinciasList;
    }

    public List<Localidad> getLocalidadesList() {
        return localidadesList;
    }

    public void setLocalidadesList(List<Localidad> localidadesList) {
        this.localidadesList = localidadesList;
    }

    public Map<Integer, List<Localidad>> getMapProvinciasLocalidades() {
        return mapProvinciasLocalidades;
    }

    public void setMapProvinciasLocalidades(Map<Integer, List<Localidad>> mapProvinciasLocalidades) {
        this.mapProvinciasLocalidades = mapProvinciasLocalidades;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public List<Puesto> getPuestosList() {
        return puestosList;
    }

    public void setPuestosList(List<Puesto> puestosList) {
        this.puestosList = puestosList;
    }
    
    public List<CategoriaLaboral> getCategoriasLaboralesList() {
        return categoriasLaboralesList;
    }

    public void setCategoriasLaboralesList(List<CategoriaLaboral> categoriasLaboralesList) {
        this.categoriasLaboralesList = categoriasLaboralesList;
    }
    

    public Cuestionario getCuestionarioSaludOcupacional() {
        return cuestionarioSaludOcupacional;
    }

    public void setCuestionarioSaludOcupacional(Cuestionario cuestionarioSaludOcupacional) {
        this.cuestionarioSaludOcupacional = cuestionarioSaludOcupacional;
    }

    public Cuestionario getCuestionarioDesarrolloProfesional() {
        return cuestionarioDesarrolloProfesional;
    }

    public void setCuestionarioDesarrolloProfesional(Cuestionario cuestionarioDesarrolloProfesional) {
        this.cuestionarioDesarrolloProfesional = cuestionarioDesarrolloProfesional;
    }

    public List<Gremio> getGremiosList() {
        return gremiosList;
    }

    public void setGremiosList(List<Gremio> gremiosList) {
        this.gremiosList = gremiosList;
    }

    public IGremioService getGremioService() {
        return gremioService;
    }

    public void setGremioService(IGremioService gremioService) {
        this.gremioService = gremioService;
    }
    //</editor-fold>
}
