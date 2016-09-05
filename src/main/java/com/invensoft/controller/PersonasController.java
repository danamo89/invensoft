/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.EducacionFormal;
import com.invensoft.model.EducacionNoFormal;
import com.invensoft.model.EstadoCivil;
import com.invensoft.model.Familiar;
import com.invensoft.model.Pais;
import com.invensoft.model.Persona;
import com.invensoft.model.Cargo;
import com.invensoft.model.Documento;
import com.invensoft.model.DocumentoPersona;
import com.invensoft.model.InformacionLaboral;
import com.invensoft.model.TipoDocumento;
import com.invensoft.model.TipoIdentificacion;
import com.invensoft.service.ICargoService;
import com.invensoft.service.IDocumentoService;
import com.invensoft.service.IEstadoCivilService;
import com.invensoft.service.IPaisService;
import com.invensoft.service.IPersonaService;
import com.invensoft.service.ITipoDocumentoService;
import com.invensoft.service.ITipoIdentificacionService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import org.apache.commons.io.FileUtils;
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
    private Cargo cargo;
    private EducacionFormal educacionFormal;
    private EducacionNoFormal educacionNoFormal;

    private List<Persona> personasList;
    private List<TipoIdentificacion> tiposIdentificacionList;
    private List<Pais> paisesList;
    private List<EstadoCivil> estadosCivilesList;
    private List<Integer> rangoAniosPermitidos;
    private List<InformacionLaboral> informacionLaboralList;
    private List<Cargo> tiposCargoList;

    private String[] selectedTiposDocumentos;
    private List<SelectItem> selectTiposDocumentos;
    private List<TipoDocumento> tiposDocumentosList;

    private StreamedContent fotoPersonaView = null;

    private boolean showPersonasTable;

    //Services
    @ManagedProperty(value = "#{personaService}")
    private IPersonaService personasService;
    @ManagedProperty(value = "#{tipoIdentificacionService}")
    private ITipoIdentificacionService tipoIdentificacionService;
    @ManagedProperty(value = "#{paisService}")
    private IPaisService paisService;
    @ManagedProperty(value = "#{estadoCivilService}")
    private IEstadoCivilService estadoCivilService;
    @ManagedProperty(value = "#{cargoService}")
    private ICargoService cargoService;
    @ManagedProperty(value = "#{tipoDocumentoService}")
    private ITipoDocumentoService tipoDocumentoService;
    @ManagedProperty(value = "#{documentoService}")
    private IDocumentoService documentoService;

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
        cargo = new Cargo();

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

        if (tiposCargoList == null) {
            tiposCargoList = cargoService.findAll();

            // Por si no existen registros en la base de datos
            if (tiposCargoList == null) {
                tiposCargoList = new LinkedList<>();
            }
        }

        if (this.tiposDocumentosList == null) {
            this.tiposDocumentosList = tipoDocumentoService.findAll();
            
            // Por si no existen registros en la base de datos
            if (tiposDocumentosList == null) {
                tiposDocumentosList = new LinkedList<>();
            }
        }
        
//        if (selectTiposDocumentos == null) {
//            this.tiposDocumentosList = tipoDocumentoService.findAll();
//
//            // Por si no existen registros en la base de datos
//            if (tiposDocumentosList == null) {
//                tiposDocumentosList = new LinkedList<>();
//            }
//
//            selectTiposDocumentos = new LinkedList<>();
//            for (TipoDocumento tipoDocumento : tiposDocumentosList) {
//                SelectItemGroup selectItemGroup = new SelectItemGroup(tipoDocumento.getDescripcion());
//                
//                SelectItem[] selectedItems = new SelectItem[tipoDocumento.getDocumentosList().size()];
//                for (int i = 0; i < tipoDocumento.getDocumentosList().size(); i++) {
//                    Documento documento = tipoDocumento.getDocumentosList().get(i);
//                    SelectItem item = new SelectItem(documento.getIdDocumento(), documento.getDescripcion());
//                    selectedItems[i] = item;
//                }
//                
//                selectItemGroup.setSelectItems(selectedItems);
//                selectTiposDocumentos.add(selectItemGroup);
//            }
//        }

//        Integer idTipo = -1;
//        List<SelectItem> items = new ArrayList<>();
//        SelectItemGroup g1 = new SelectItemGroup();
//        if (this.tiposDocumentosList == null) {
//            this.tiposDocumentosList = new LinkedList<>();
//        }
//
//        for (Documento doc : this.tiposDocumentosList) {
//            if (idTipo.equals(-1)) {
//                g1 = new SelectItemGroup(doc.getDocumentoTipo().getDescripcion());
//                idTipo = doc.getDocumentoTipo().getIdDocumentoTipo();
//            } else if (!idTipo.equals(doc.getDocumentoTipo().getIdDocumentoTipo())) {
//                if (items.size() > 0) {
//                    SelectItem[] selectedItems = items.toArray(new SelectItem[items.size()]);
//                    g1.setSelectItems(selectedItems);
//                    selectTiposDocumentos.add(g1);
//
//                    items = new ArrayList<>();
//                    g1 = new SelectItemGroup(doc.getDocumentoTipo().getDescripcion());
//                    idTipo = doc.getDocumentoTipo().getIdDocumentoTipo();
//                }
//            }
//
//            SelectItem item = new SelectItem(doc.getIdDocumento(), doc.getDescripcion());
//            items.add(item);
//        }
//        SelectItem[] selectedItems = items.toArray(new SelectItem[items.size()]);
//        g1.setSelectItems(selectedItems);
//        selectTiposDocumentos.add(g1);
    }

    public void onViewPersonaDetailedInfo(Persona persona) {
        this.persona = persona;
        this.showPersonasTable = false;

        initDocumentosPersona();

//        if (this.persona.getFoto() != null) {
//            this.fotoPersonaView = new DefaultStreamedContent(new ByteArrayInputStream(persona.getFoto()), "image/jpg");
//        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            ServletContext servletContext = (ServletContext) externalContext.getContext();
            String absoluteDiskPath = servletContext.getRealPath("resources/images/broken.png");

            File file = new File(absoluteDiskPath);

            try {
                this.fotoPersonaView = new DefaultStreamedContent(new FileInputStream(file), "image/png");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
                this.fotoPersonaView = new DefaultStreamedContent();
            }
//        }
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
        
//        int idx = 0;
//        selectedTiposDocumentos = new String[this.tiposDocumentosList.size()];
//
//        for (Documento tipo : this.tiposDocumentosList) {
//            for (DocumentoPersona documento : this.persona.getDocumentosPersonaList()) {
//                if (tipo.getIdDocumento().equals(documento.getDocumento().getIdDocumento())) {
//                    selectedTiposDocumentos[idx] = tipo.getIdDocumento().toString();
//                    break;
//                }
//            }
//            idx++;
//        }
    }

    public void onCreatePersona() {
        this.persona = new Persona();
        this.showPersonasTable = false;
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

    public void onAddCargo() {
        boolean add = true;

        if (this.persona.getInformacionLaboralList() == null) {
            this.persona.setInformacionLaboralList(new LinkedList<InformacionLaboral>());
        }

        for (InformacionLaboral info : this.persona.getInformacionLaboralList()) {
            if (info.getCargo().getIdCargo().equals(this.cargo.getIdCargo())) {
                add = false;
            }
        }

        if (add) {
            this.informacionLaboral = new InformacionLaboral();

            for (Cargo c : tiposCargoList) {
                if (c.getIdCargo().equals(this.cargo.getIdCargo())) {
                    this.informacionLaboral.setCargo(c);
                    this.informacionLaboral.setPersona(this.persona);
                }
            }

            this.persona.getInformacionLaboralList().add(this.informacionLaboral);
        }

        this.informacionLaboral = new InformacionLaboral();
        this.cargo = new Cargo();
    }

    public void onRemoveInformacionLaboral(InformacionLaboral informacionLaboral) {
        this.persona.getInformacionLaboralList().remove(informacionLaboral);
    }

    public void onRemoveFamiliar(Familiar familiar) {
        this.persona.getFamiliaresList().remove(familiar);
    }

    public void onRemoveEducacionFormal(EducacionFormal educacionFormal) {
        this.persona.getEducacionesFormalesList().remove(educacionFormal);
    }

    public void onRemoveEducacionNoFormal(EducacionNoFormal educacionNoFormal) {
        this.persona.getEducacionesNoFormalesList().remove(educacionNoFormal);
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

    public void onViewFamiliarDetailedInfo(Familiar familiar) {
        for (Familiar familiarItem : this.persona.getFamiliaresList()) {
            if (Objects.equals(familiarItem.getIdFamiliar(), familiar.getIdFamiliar())) {
                this.persona.getFamiliaresList().remove(familiarItem);
                break;
            }
        }
        this.familiar = familiar;
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

            updateDocumentosPersona();

//            persona.setFoto(IOUtils.toByteArray(this.fotoPersonaView.getStream()));

            personasService.save(persona);
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

            File tempFile = new File(FileUtils.getTempDirectory().getAbsoluteFile() + "/" + this.persona.getLegajo() + ".jpg");
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(uploadedFile.getContents()), tempFile);
        } catch (IOException ex) {
            Logger.getLogger(PersonasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validarFoto(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<>();

        UploadedFile file = (UploadedFile) value;
        int bytes = file.getContents().length;
        /*
         if(bytes > 15360) {
         msgs.add(new FacesMessage("Archivo demasiado grande"));
         }
         */
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

    public List<InformacionLaboral> getInformacionLaboralList() {
        return informacionLaboralList;
    }

    public void setInformacionLaboralList(List<InformacionLaboral> informacionLaboralList) {
        this.informacionLaboralList = informacionLaboralList;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Cargo> getTiposCargoList() {
        return tiposCargoList;
    }

    public void setTiposCargoList(List<Cargo> tiposCargoList) {
        this.tiposCargoList = tiposCargoList;
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

    public ICargoService getCargoService() {
        return cargoService;
    }

    public void setCargoService(ICargoService cargoService) {
        this.cargoService = cargoService;
    }

    public IDocumentoService getDocumentoService() {
        return documentoService;
    }

    public void setDocumentoService(IDocumentoService documentoService) {
        this.documentoService = documentoService;
    }
    //</editor-fold>
}
