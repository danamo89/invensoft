/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.controller.helper.CuestionarioPainter;
import com.invensoft.model.EducacionFormal;
import com.invensoft.model.EducacionNoFormal;
import com.invensoft.model.EstadoCivil;
import com.invensoft.model.Familiar;
import com.invensoft.model.Pais;
import com.invensoft.model.Persona;
import com.invensoft.model.Documento;
import com.invensoft.model.DocumentoPersona;
import com.invensoft.model.FotoPersona;
import com.invensoft.model.InformacionLaboral;
import com.invensoft.model.Puesto;
import com.invensoft.model.RespuestaPregunta;
import com.invensoft.model.Sector;
import com.invensoft.model.TipoDocumento;
import com.invensoft.model.TipoIdentificacion;
import com.invensoft.service.ICuestionarioService;
import com.invensoft.service.IDocumentoService;
import com.invensoft.service.IEstadoCivilService;
import com.invensoft.service.IPaisService;
import com.invensoft.service.IPersonaService;
import com.invensoft.service.IPuestoService;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
    private Puesto puesto;
    
    private List<Persona> personasList;
    private List<TipoIdentificacion> tiposIdentificacionList;
    private List<Pais> paisesList;
    private List<EstadoCivil> estadosCivilesList;
    private List<Integer> rangoAniosPermitidos;
    private List<Sector> sectoresList;
    private List<Puesto> puestosList;

    private String[] selectedTiposDocumentos;
    private List<SelectItem> selectTiposDocumentos;
    private List<TipoDocumento> tiposDocumentosList;
    
    private CuestionarioPainter cuestionarioPainter;
    
    private HtmlPanelGroup saludOcupacionalRootPanelGroup;
    private List<RespuestaPregunta> listRespuestasPreguntasCuestionarioSaludOcupacional;
    private HtmlPanelGroup desarrolloProfesionalRootPanelGroup;
    private List<RespuestaPregunta> listRespuestasPreguntasCuestionarioDesarrolloProfesional;
    private HtmlPanelGroup cuestionarioRootPanelGroup;
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

    /**
     * Creates a new instance of PersonasController
     */
    public PersonasController() {
    }

    @PostConstruct
    public void initController() {
        showPersonasTable = true;
        personasList = personasService.findAll();
        cuestionarioPainter = new CuestionarioPainter();
        
        saludOcupacionalRootPanelGroup = new HtmlPanelGroup();
        desarrolloProfesionalRootPanelGroup = new HtmlPanelGroup();
        
        listRespuestasPreguntasCuestionarioSaludOcupacional = cuestionarioPainter.paint(saludOcupacionalRootPanelGroup, cuestionarioService.findCuestionarioSaludOcupacional());
        listRespuestasPreguntasCuestionarioDesarrolloProfesional = cuestionarioPainter.paint(desarrolloProfesionalRootPanelGroup, cuestionarioService.findCuestionarioDesarrolloProfesional());

        familiar = new Familiar();
        educacionFormal = new EducacionFormal();
        educacionNoFormal = new EducacionNoFormal();
        informacionLaboral = new InformacionLaboral();

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
        
        if (this.puestosList == null) {
            this.puestosList = puestoService.findAll();
            
            if (puestosList == null) {
                puestosList = new LinkedList<>();
            }
        }
    }

    public void onViewPersonaDetailedInfo(Persona persona) {
        this.persona = persona;
        this.showPersonasTable = false;
        FacesContext context = FacesContext.getCurrentInstance();
        
        initDocumentosPersona();
                
        if (this.persona.getFotoPersona() != null) {
            try {
                String relativePath = "/resources/images";
                String absolutePath = context.getExternalContext().getRealPath(relativePath);
                String fileExtension = this.persona.getFotoPersona().getExtension();
                String fileName = this.persona.getLegajo()+"."+fileExtension;

                FileUtils.writeByteArrayToFile(new File(absolutePath+"/"+fileName), this.persona.getFotoPersona().getFoto());

                this.rutaFoto = relativePath + "/" + fileName;
            }catch(IOException e) {
                this.rutaFoto = "/resources/images/broken.png";
            }
        }else {          
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
        this.showPersonasTable = false;
        this.rutaFoto = "/resources/images/broken.png";
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
                //this.persona.getEducacionesNoFormalesList().remove(educacionNoFormalItem);
                break;
            }
        }
        this.educacionNoFormal = educacionNoFormal;
    }
    
    public void onRemoveEducacionNoFormal(EducacionNoFormal educacionNoFormal) {
        this.persona.getEducacionesNoFormalesList().remove(educacionNoFormal);
    }
    
    public void onIdentificacionChange() {
        this.persona.setLegajo(this.persona.getNumeroIdentificacion());
    }
    
    public void onSectorChange(ValueChangeEvent changeEvent) {
        Sector sector = (Sector)changeEvent.getNewValue();
        cuestionarioPainter.paint(cuestionarioRootPanelGroup, sector.getCuestionario());
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
                //this.persona.getInformacionLaboralList().remove(informacionLaboralItem);
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
            
            for (Puesto p : puestosList) {
                if (p.getIdPuesto().equals(persona.getPuesto().getIdPuesto())) {
                    persona.setPuesto(p);
                }
            }
            
            updateDocumentosPersona();

//            persona.setFoto(IOUtils.toByteArray(this.fotoPersonaView.getStream()));
            /*
            System.out.println("Salud Ocupacional");
            System.out.println("------------------");
            for (RespuestaPregunta respuestaPregunta : listRespuestasPreguntasCuestionarioSaludOcupacional) {
                System.out.println("Pregunta: " + respuestaPregunta.getPregunta().getTexto());
                
                if (respuestaPregunta.getValue()!= null) {
                    System.out.println("Respuesta: " + respuestaPregunta.getValue());
                } 
                
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("Desarrollo profesional");
            System.out.println("-----------------------");
            for (RespuestaPregunta respuestaPregunta : listRespuestasPreguntasCuestionarioSaludOcupacional) {
                System.out.println("Pregunta: " + respuestaPregunta.getPregunta().getTexto());
                
                if (respuestaPregunta.getValue()!= null) {
                    System.out.println("Respuesta: " + respuestaPregunta.getValue());
                } 
            }
            */
            
            //Si la persona que vamos a guardar es nueva, la sumamos a la lista. 
            boolean nuevaPersona = false;
            if (persona.getIdPersona() == null || persona.getIdPersona() == 0) {
                nuevaPersona = true;
            }

            personasService.save(persona);
            
            //La agregacion la hacemos despues de guardar por si ocurre un error
            if (nuevaPersona) {
                personasList = personasService.findAll();
            }
//            
            this.showPersonasTable = true;

        } catch (Exception e) {
            System.out.println("Error:  " + e.getLocalizedMessage());
        }
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
            
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            
            byte[] content = IOUtils.toByteArray(uploadedFile.getInputstream());
            String extension = uploadedFile.getFileName().split("\\.")[1];
            
            FotoPersona foto = new FotoPersona();
            foto.setFoto(content);
            foto.setExtension(extension);
            
            this.persona.setFotoPersona(foto);
            
            String relativeWebPath = "/resources/images/";
            String absoluteDiskPath = externalContext.getRealPath(relativeWebPath);
            String tempFileName = this.persona.getLegajo()+extension;
            
            File tempFile = new File(absoluteDiskPath+"/"+tempFileName);
            
            IOUtils.write(content, new FileOutputStream(tempFile));
            this.rutaFoto = relativeWebPath+tempFileName;
            
        } catch (IOException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validarFoto(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();

        UploadedFile file = (UploadedFile) value;
        int bytes = file.getContents().length;
        
        if(bytes > 15360) {
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

    public HtmlPanelGroup getSaludOcupacionalRootPanelGroup() {
        return saludOcupacionalRootPanelGroup;
    }

    public void setSaludOcupacionalRootPanelGroup(HtmlPanelGroup saludOcupacionalRootPanelGroup) {
        this.saludOcupacionalRootPanelGroup = saludOcupacionalRootPanelGroup;
    }

    public HtmlPanelGroup getDesarrolloProfesionalRootPanelGroup() {
        return desarrolloProfesionalRootPanelGroup;
    }

    public void setDesarrolloProfesionalRootPanelGroup(HtmlPanelGroup desarrolloProfesionalRootPanelGroup) {
        this.desarrolloProfesionalRootPanelGroup = desarrolloProfesionalRootPanelGroup;
    }

    public HtmlPanelGroup getCuestionarioRootPanelGroup() {
        return cuestionarioRootPanelGroup;
    }

    public void setCuestionarioRootPanelGroup(HtmlPanelGroup cuestionarioRootPanelGroup) {
        this.cuestionarioRootPanelGroup = cuestionarioRootPanelGroup;
    }

    public CuestionarioPainter getCuestionarioPainter() {
        return cuestionarioPainter;
    }

    public void setCuestionarioPainter(CuestionarioPainter cuestionarioPainter) {
        this.cuestionarioPainter = cuestionarioPainter;
    }

    public ICuestionarioService getCuestionarioService() {
        return cuestionarioService;
    }

    public void setCuestionarioService(ICuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
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
    
    //</editor-fold>
}
