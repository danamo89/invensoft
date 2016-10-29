/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IPersonaDao;
import com.invensoft.model.Persona;
import com.invensoft.service.IPersonaService;
import com.invensoft.util.MessageBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "personaService")
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaDao personaDao;
    @Autowired
    private MessageBean messageBean;
    
    public Persona findById(Integer id) {
        try {
            return personaDao.find(id);
        } catch (Exception ex) {
            Logger.getLogger(PersonaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            messageBean.addError("Ha ocurrido un error al tratar de encontrar a una persona", ex.getMessage());
            return null;
        }
    }
    
    public List<Object[]> findPersonasBasicData() {
        try {
            return personaDao.findPersonasBasicData();
        } catch (Exception ex) {
            Logger.getLogger(PersonaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            messageBean.addError("Ha ocurrido un error al consultar el listado de personas", ex.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Persona> findAll() {
        try {
            return personaDao.findAllOrderBy("apellidos");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar el listado de personas", e.getMessage());
            return null;
        }
    }

    @Override 
    public Persona save(Persona persona) {
        try {
            messageBean.addInfo("Datos guardados!", "Se han guardado los datos de forma correcta.");
            return personaDao.save(persona);
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al guardar los datos de la persona seleccionada", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public XSSFWorkbook exportarPersonaExcel(Persona persona) throws IOException {
        String tempFile = FileUtils.getTempDirectoryPath()+persona.getLegajo()+".xlsx";
        FileOutputStream out = new FileOutputStream(tempFile);
        
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        
        /*
        XSSFSheet spreadsheet = workbook.createSheet("cellstyle");
        XSSFRow row = spreadsheet.createRow((short) 1);
        row.setHeight((short) 800);
        XSSFCell cell = (XSSFCell) row.createCell((short) 1);
        cell.setCellValue("test of merging");
        //MEARGING CELLS 
        //this statement for merging cells
        spreadsheet.addMergedRegion(new CellRangeAddress(
        1, //first row (0-based)
        1, //last row (0-based)
        1, //first column (0-based)
        4 //last column (0-based)
        ));
        //CELL Alignment
        row = spreadsheet.createRow(5); 
        cell = (XSSFCell) row.createCell(0);
        row.setHeight((short) 800);
        // Top Left alignment 
        XSSFCellStyle style1 = workbook.createCellStyle();
        spreadsheet.setColumnWidth(0, 8000);
        style1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
        cell.setCellValue("Top Left");
        cell.setCellStyle(style1);
        row = spreadsheet.createRow(6); 
        cell = (XSSFCell) row.createCell(1);
        row.setHeight((short) 800);
        // Center Align Cell Contents 
        XSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment( 
        XSSFCellStyle.VERTICAL_CENTER);
        cell.setCellValue("Center Aligned"); 
        cell.setCellStyle(style2);
        row = spreadsheet.createRow(7); 
        cell = (XSSFCell) row.createCell(2);
        row.setHeight((short) 800);
        // Bottom Right alignment 
        XSSFCellStyle style3 = workbook.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style3.setVerticalAlignment( 
        XSSFCellStyle.VERTICAL_BOTTOM);
        cell.setCellValue("Bottom Right");
        cell.setCellStyle(style3);
        row = spreadsheet.createRow(8);
        cell = (XSSFCell) row.createCell(3);
        // Justified Alignment 
        XSSFCellStyle style4 = workbook.createCellStyle();
        style4.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
        style4.setVerticalAlignment(
        XSSFCellStyle.VERTICAL_JUSTIFY);
        cell.setCellValue("Contents are Justified in Alignment"); 
        cell.setCellStyle(style4);
        //CELL BORDER
        row = spreadsheet.createRow((short) 10);
        row.setHeight((short) 800);
        cell = (XSSFCell) row.createCell((short) 1);
        cell.setCellValue("BORDER");
        XSSFCellStyle style5 = workbook.createCellStyle();
        style5.setBorderBottom(XSSFCellStyle.BORDER_THICK);
        style5.setBottomBorderColor(
        IndexedColors.BLUE.getIndex());
        style5.setBorderLeft(XSSFCellStyle.BORDER_DOUBLE);
        style5.setLeftBorderColor( 
        IndexedColors.GREEN.getIndex());
        style5.setBorderRight(XSSFCellStyle.BORDER_HAIR);
        style5.setRightBorderColor( 
        IndexedColors.RED.getIndex());
        style5.setBorderTop(XSSFCellStyle.BIG_SPOTS);
        style5.setTopBorderColor( 
        IndexedColors.CORAL.getIndex());
        cell.setCellStyle(style5);
        //Fill Colors
        //background color
        row = spreadsheet.createRow((short) 10 );
        cell = (XSSFCell) row.createCell((short) 1);
        XSSFCellStyle style6 = workbook.createCellStyle();
        style6.setFillBackgroundColor(
        HSSFColor.LEMON_CHIFFON.index );
        style6.setFillPattern(XSSFCellStyle.LESS_DOTS);
        style6.setAlignment(XSSFCellStyle.ALIGN_FILL);
        spreadsheet.setColumnWidth(1,8000);
        cell.setCellValue("FILL BACKGROUNG/FILL PATTERN");
        cell.setCellStyle(style6);
        //Foreground color
        
        row = spreadsheet.createRow((short) 12);
        cell = (XSSFCell) row.createCell((short) 1);
        
        XSSFCellStyle style7=workbook.createCellStyle();
        style7.setFillForegroundColor(HSSFColor.BLUE.index);
        style7.setFillPattern( XSSFCellStyle.LESS_DOTS);
        style7.setAlignment(XSSFCellStyle.ALIGN_FILL);
        
        cell.setCellValue("FILL FOREGROUND/FILL PATTERN");
        cell.setCellStyle(style7);
        */
        return workbook;
    }

    @Override
    public XSSFWorkbook exportarPersonasAllExcel() throws IOException {
        List<Persona> personas = findAll();
        XSSFWorkbook workbook = null;
        
        if(personas.size() > 0) {
            workbook = new XSSFWorkbook();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            XSSFSheet spreadsheet = workbook.createSheet("Empleados");
            
            /* Title */
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

            Font titleFont = workbook.createFont();
            titleFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            titleFont.setFontHeightInPoints((short)26);
            titleStyle.setFont(titleFont);

            /* Header */
            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            headerStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

            headerStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
            headerStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
            headerStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
            headerStyle.setBorderTop(CellStyle.BORDER_MEDIUM);

            headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());        
            headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

            headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            headerStyle.setFillPattern( XSSFCellStyle.SOLID_FOREGROUND);

            Font headerFont = workbook.createFont();
            headerFont.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);

            headerStyle.setFont(headerFont);

            /* Values */
            XSSFCellStyle valueStyle = workbook.createCellStyle();
            valueStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
            valueStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

            valueStyle.setBorderBottom(CellStyle.BORDER_THIN);
            valueStyle.setBorderLeft(CellStyle.BORDER_THIN);
            valueStyle.setBorderRight(CellStyle.BORDER_THIN);
            valueStyle.setBorderTop(CellStyle.BORDER_THIN);

            valueStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            valueStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            valueStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            valueStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

            /* Titulo */
            XSSFRow row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 800);
            XSSFCell cell = (XSSFCell) row.createCell((short) 1);
            cell.setCellValue("Listado de Personas");
            cell.setCellStyle(titleStyle);
            
            spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 7));

            /* Informacion Personal - Headers */
            row = spreadsheet.createRow((short) 3);
            cell = (XSSFCell) row.createCell((short) 0);
            cell.setCellValue("Legajo");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 1);
            cell.setCellValue("Nombre");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 2);
            cell.setCellValue("Apellido");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 3);
            cell.setCellValue("Cuil");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 4);
            cell.setCellValue("Tipo Identificacion");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 5);
            cell.setCellValue("Numero de Identificacion");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 6);
            cell.setCellValue("Fecha Nacimiento");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 7);
            cell.setCellValue("Lugar de Nacimiento");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 8);
            cell.setCellValue("Domicilio");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 9);
            cell.setCellValue("Numero");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 10);
            cell.setCellValue("Piso");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 11);
            cell.setCellValue("Departamento");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 12);
            cell.setCellValue("Localidad");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 13);
            cell.setCellValue("Cod. Postal");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 14);
            cell.setCellValue("Email");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 15);
            cell.setCellValue("Telefono");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 16);
            cell.setCellValue("Celular");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 17);
            cell.setCellValue("Estado Civil");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 18);
            cell.setCellValue("Pais de Origen");
            cell.setCellStyle(headerStyle);

            /* Informacion Laboral - Headers */
            cell = (XSSFCell) row.createCell((short) 19);
            cell.setCellValue("Puesto");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 20);
            cell.setCellValue("Categoria Laboral");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 21);
            cell.setCellValue("Gremio");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 22);
            cell.setCellValue("Horario");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 23);
            cell.setCellValue("Lugar de Trabajo");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 24);
            cell.setCellValue("Jefe Inmediato");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 25);
            cell.setCellValue("Linea");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 26);
            cell.setCellValue("Fecha de Ingreso");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 27);
            cell.setCellValue("Antiguedad");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 28);
            cell.setCellValue("Obra Social");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 29);
            cell.setCellValue("Banco de Cobro");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 30);
            cell.setCellValue("Tiene Credencial ART");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 31);
            cell.setCellValue("Carnet de Conductor");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 32);
            cell.setCellValue("Carnet de Conductor desde");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 33);
            cell.setCellValue("Carnet de Conductor desde");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 34);
            cell.setCellValue("Buzo");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 35);
            cell.setCellValue("Pantalon");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 36);
            cell.setCellValue("Botines");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 37);
            cell.setCellValue("Campera");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 38);
            cell.setCellValue("Eq. de Lluvia");
            cell.setCellStyle(headerStyle);
            cell = (XSSFCell) row.createCell((short) 39);
            cell.setCellValue("Camisa");
            cell.setCellStyle(headerStyle);
            Integer indx = row.getRowNum() + 1;

            for(Persona persona : personas) {            
                row = spreadsheet.createRow(indx);
                cell = (XSSFCell) row.createCell((short) 0);
                cell.setCellValue(persona.getLegajo() != null ? persona.getLegajo() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 1);
                cell.setCellValue(persona.getNombres() != null ? persona.getNombres() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 2);
                cell.setCellValue(persona.getApellidos() != null ? persona.getApellidos() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 3);
                cell.setCellValue(persona.getCuil() != null ? persona.getCuil() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 4);
                cell.setCellValue(persona.getTipoIdentificacion().getDescripcion() != null ? persona.getTipoIdentificacion().getDescripcion() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 5);
                cell.setCellValue(persona.getNumeroIdentificacion() != null ? persona.getNumeroIdentificacion() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 6);
                cell.setCellValue(persona.getFechaNacimiento() != null ? dateFormat.format(persona.getFechaNacimiento()) : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 7);
                cell.setCellValue(persona.getLugarNacimiento() != null ? persona.getLugarNacimiento() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 8);
                cell.setCellValue(persona.getDomicilio() != null ? persona.getDomicilio() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 9);
                cell.setCellValue(persona.getNumeroDomicilio() != null ? persona.getNumeroDomicilio() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 10);
                cell.setCellValue(persona.getPiso() != null ? persona.getPiso() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 11);
                cell.setCellValue(persona.getDepartamento() != null ? persona.getDepartamento() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 12);
                cell.setCellValue(persona.getLocalidad().getNombre() != null ? persona.getLocalidad().getNombre() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 13);
                cell.setCellValue(persona.getCodigoPostal() != null ? persona.getCodigoPostal() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 14);
                cell.setCellValue(persona.getEmail() != null ? persona.getEmail() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 15);
                cell.setCellValue(persona.getTelefonoParticular() != null ? persona.getTelefonoParticular() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 16);
                cell.setCellValue(persona.getCelular() != null ? persona.getCelular() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 17);
                cell.setCellValue(persona.getEstadoCivil().getDescripcion() != null ? persona.getEstadoCivil().getDescripcion() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 18);
                cell.setCellValue(persona.getPaisOrigen().getNombre() != null ? persona.getPaisOrigen().getNombre() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 19);
                cell.setCellValue(persona.getPuesto().getDescripcion() != null ? persona.getPuesto().getDescripcion() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 20);
                cell.setCellValue(persona.getIdCategoriaLaboral().getDescripcion() != null ? persona.getIdCategoriaLaboral().getDescripcion() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 21);
                cell.setCellValue(persona.getGremio().getDescripcion() != null ? persona.getGremio().getDescripcion() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 22);
                cell.setCellValue(persona.getHorario() != null ? persona.getHorario() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 23);
                cell.setCellValue(persona.getLugarDeTrabajo() != null ? persona.getLugarDeTrabajo() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 24);
                cell.setCellValue(persona.getJefeInmediato() != null ? persona.getJefeInmediato() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 25);
                cell.setCellValue(persona.getLinea() != null ? persona.getLinea() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 26);
                cell.setCellValue(persona.getFechaIngreso() != null ? persona.getFechaIngreso() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 27);
                cell.setCellValue(persona.getAntiguedad() != null ? persona.getAntiguedad().toString() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 28);
                cell.setCellValue(persona.getObraSocial() != null ? persona.getObraSocial() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 29);
                cell.setCellValue(persona.getBanco() != null ? persona.getBanco() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 30);
                cell.setCellValue(persona.getTieneCredencialArt() != null ? persona.getTieneCredencialArt() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 31);
                cell.setCellValue(persona.getTieneCarnetConductor() != null ? persona.getTieneCarnetConductor() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 32);
                cell.setCellValue(persona.getCarnetConductorDesde() != null ? dateFormat.format(persona.getCarnetConductorDesde()) : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 33);
                cell.setCellValue(persona.getCarnetConductorHasta() != null ? dateFormat.format(persona.getCarnetConductorHasta()) : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 34);
                cell.setCellValue(persona.getBuzo() != null ? persona.getBuzo() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 35);
                cell.setCellValue(persona.getPantalon() != null ? persona.getPantalon().toString() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 36);
                cell.setCellValue(persona.getBotines() != null ? persona.getBotines().toString() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 37);
                cell.setCellValue(persona.getCampera() != null ? persona.getCampera() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 38);
                cell.setCellValue(persona.getEqLluvia() != null ? persona.getEqLluvia().toString() : "");
                cell.setCellStyle(valueStyle);
                cell = (XSSFCell) row.createCell((short) 39);
                cell.setCellValue(persona.getCamisa() != null ? persona.getCamisa().toString() : "");
                cell.setCellStyle(valueStyle);

                indx ++;
            }

            for(int i = 0; i < 40; i ++) {
                spreadsheet.autoSizeColumn(i);
            }
        }
        
        return workbook;
    }
    
    public void test() {
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("Empleados");        
        XSSFRow row = spreadsheet.createRow((short) 1);
        row.createCell(1);
        //CELL Alignment
        row = spreadsheet.createRow(5); 
        XSSFCell cell = (XSSFCell) row.createCell((short) 1);
        
        row.setHeight((short) 800);
        // Top Left alignment 
        XSSFCellStyle style1 = workbook.createCellStyle();
        spreadsheet.setColumnWidth(0, 8000);
        style1.setAlignment(XSSFCellStyle.ALIGN_LEFT);
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
        cell.setCellValue("Top Left");
        cell.setCellStyle(style1);
        
        row = spreadsheet.createRow(6); 
        cell = (XSSFCell) row.createCell(1);
        row.setHeight((short) 800);
        // Center Align Cell Contents 
        XSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment( 
        XSSFCellStyle.VERTICAL_CENTER);
        cell.setCellValue("Center Aligned"); 
        cell.setCellStyle(style2);
        
        row = spreadsheet.createRow(7); 
        cell = (XSSFCell) row.createCell(2);
        row.setHeight((short) 800);
        // Bottom Right alignment 
        XSSFCellStyle style3 = workbook.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        style3.setVerticalAlignment( 
        XSSFCellStyle.VERTICAL_BOTTOM);
        cell.setCellValue("Bottom Right");
        cell.setCellStyle(style3);
        
        row = spreadsheet.createRow(8);
        cell = (XSSFCell) row.createCell(3);
        // Justified Alignment 
        XSSFCellStyle style4 = workbook.createCellStyle();
        style4.setAlignment(XSSFCellStyle.ALIGN_JUSTIFY);
        style4.setVerticalAlignment(
        XSSFCellStyle.VERTICAL_JUSTIFY);
        cell.setCellValue("Contents are Justified in Alignment"); 
        cell.setCellStyle(style4);
        
        //CELL BORDER
        row = spreadsheet.createRow((short) 10);
        row.setHeight((short) 800);
        cell = (XSSFCell) row.createCell((short) 1);
        cell.setCellValue("BORDER");
        XSSFCellStyle style5 = workbook.createCellStyle();
        style5.setBorderBottom(XSSFCellStyle.BORDER_THICK);
        style5.setBottomBorderColor(
        IndexedColors.BLUE.getIndex());
        style5.setBorderLeft(XSSFCellStyle.BORDER_DOUBLE);
        style5.setLeftBorderColor( 
        IndexedColors.GREEN.getIndex());
        style5.setBorderRight(XSSFCellStyle.BORDER_HAIR);
        style5.setRightBorderColor( 
        IndexedColors.RED.getIndex());
        style5.setBorderTop(XSSFCellStyle.BIG_SPOTS);
        style5.setTopBorderColor( 
        IndexedColors.CORAL.getIndex());
        cell.setCellStyle(style5);
        
        //Fill Colors
        //background color
        row = spreadsheet.createRow((short) 10 );
        cell = (XSSFCell) row.createCell((short) 1);
        XSSFCellStyle style6 = workbook.createCellStyle();
        style6.setFillBackgroundColor(
        HSSFColor.LEMON_CHIFFON.index );
        style6.setFillPattern(XSSFCellStyle.LESS_DOTS);
        style6.setAlignment(XSSFCellStyle.ALIGN_FILL);
        spreadsheet.setColumnWidth(1,8000);
        cell.setCellValue("FILL BACKGROUNG/FILL PATTERN");
        cell.setCellStyle(style6);
        
        //Foreground color
        row = spreadsheet.createRow((short) 12);
        cell = (XSSFCell) row.createCell((short) 1);
        XSSFCellStyle style7=workbook.createCellStyle();
        style7.setFillForegroundColor(HSSFColor.BLUE.index);
        style7.setFillPattern( XSSFCellStyle.LESS_DOTS);
        style7.setAlignment(XSSFCellStyle.ALIGN_FILL);
        cell.setCellValue("FILL FOREGROUND/FILL PATTERN");
        cell.setCellStyle(style7);
    }

    @Override
    public List<Object[]> findTopPersonasXCategoriaLaboral(Integer top) {
        try {    
            return personaDao.findTopPersonasXCategoriaLaboral(top);
        } catch (Exception e) {
            messageBean.addError("Error", "Ha ocurrido un error al consultar los datos. " + e.getMessage());
            return new LinkedList<>();
        }
    }

    @Override
    public List<Object[]> findTopPersonasXLocalidad(Integer top) {
        try {    
            return personaDao.findTopPersonasXLocalidad(top);
        } catch (Exception e) {
            messageBean.addError("Error", "Ha ocurrido un error al consultar los datos. " + e.getMessage());
            return new LinkedList<>();
        }
    }

    @Override
    public void delete(Persona persona) {
        try {
            personaDao.delete(persona.getIdPersona());
            messageBean.addInfo("Datos eliminados", "Se han eliminado los registros de forma exitosa");
        } catch (Exception e) {
            messageBean.addError("Error", "Ha ocurrido un error al eliminar los datos. " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
