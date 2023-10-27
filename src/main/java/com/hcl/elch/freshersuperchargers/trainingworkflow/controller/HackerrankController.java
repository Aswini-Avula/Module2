package com.hcl.elch.freshersuperchargers.trainingworkflow.controller;

import java.io.IOException;


//import java.net.http.HttpHeaders;
//import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.output.ByteArrayOutputStream;
//import org.apache.http.HttpHeaders;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestDE;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJavaScript;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestPython;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.User;

import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDERepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDevopsRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJAVARepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJavaScriptRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestPythonRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.UserRepo;
//import com.hcl.elch.freshersuperchargers.trainingworkflow.service.ImportServiceimpl;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.ImportServiceimpl;


@RestController
public class HackerrankController {
	
	
	
	@Autowired
	private ImportServiceimpl Dser;
	
	@Autowired
	private UserRepo ur;
	
	//@Autowired
    //private GenderRepo gr;
	
	//@Autowired 
	//private ExportServiceImpl es;
	
	//@Autowired
	//private EmployeeprojectDetailsRepo er;
	
	@Autowired
	private  HackerranktestJAVARepo HJ;
	
	@Autowired
	private  HackerranktestJavaScriptRepo Hjs;
	
	@Autowired
	private  HackerranktestDERepo HD;
	 
	@Autowired
	private  HackerranktestDevopsRepo HDev;
	
	@Autowired
	private  HackerranktestPythonRepo HPY;
	
    public static int max;
	
	@PostMapping("/import/excel")
	public void importdata(@RequestParam("file") MultipartFile reapExcelDataFile,@RequestParam("filename") String excelname) throws IOException 
	{
		
		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		

		
		int len=workbook.getNumberOfSheets();
		for(int i=0;i<len;i++) 
		{
			XSSFSheet worksheet = workbook.getSheetAt(i);
	        
	        String name=worksheet.getSheetName();
	        
	        System.out.println(name+"  "+len);
	        
	        if(name.contains("Java_Backend"))
	        {
	        	Dser.importjavadata(worksheet,excelname);
	        }
	        else if(name.contains("JavaScript"))
	        {
	        	Dser.importjavascriptdata(worksheet,excelname);
	        }
	        else if(name.contains("Data"))
	        {
	        	Dser.importdedata(worksheet,excelname);
	        }
	        else if(name.contains("Devops"))
	        {
	        	Dser.importdevopsdata(worksheet,excelname);
	        }
	        else if(name.contains("Python"))
	        {
	        	Dser.importpythondata(worksheet,excelname);
	        }
			
		}
	}
	
	
	
	/*@GetMapping("/export/excel")
	public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
	
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=_Hackerrank_Test"+currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
       //List<User> listUsers = service.listAll();
        
        String month="August";
        
        List<HackerrankTestJAVA> jlist=HJ.findAll();
    	List<HackerrankTestJavaScript> jslist=Hjs.findAll();
    	List<HackerrankTestDE> dlist=HD.findAll();
    	List<HackeerankTestDevops> devlist=HDev.findAll();
    	List<HackerrankTestPython> pylist=HPY.findAll();
    	List<HackerrankTestJAVA> fja=new ArrayList<HackerrankTestJAVA>();
    	List<HackerrankTestJavaScript> fjs=new ArrayList<HackerrankTestJavaScript>();
    	List<HackerrankTestDE> fde=new ArrayList<HackerrankTestDE>();
    	List<HackeerankTestDevops> fdev=new ArrayList<HackeerankTestDevops>();
    	List<HackerrankTestPython> fpy=new ArrayList<HackerrankTestPython>();
    	
    	for(HackerrankTestJAVA J:jlist)
    	{
    		if(J.getExcel_Name().contains(month)) {
    			fja.add(J);
    		}
    	}
    	for(HackerrankTestJavaScript J:jslist)
    	{
    		if(J.getExcel_Name().contains(month)) {
    			fjs.add(J);
    		}
    	}
    	for(HackerrankTestDE J:dlist)
    	{
    		if(J.getExcel_Name().contains(month)) {
    			fde.add(J);
    		}
    	}
    	for(HackeerankTestDevops J:devlist)
    	{
    		if(J.getExcel_Name().contains(month)) {
    			fdev.add(J);
    		}
    	}
    	for(HackerrankTestPython J:pylist)
    	{
    		if(J.getExcel_Name().contains(month)) {
    			fpy.add(J);
    		}
    	}
    	
    	System.out.println(fja);
    	
    	 List<ExcelPojo> exc=es.getdetails(fja,fjs,fde,fdev,fpy);
    	 System.out.println(exc);
    	 
    	 //List<ExcelPojo> e=es.gete();
    	 
    	 
    	 List<ExcelPojo> exc2=es.pojodata(exc, fja, fjs, fde, fdev, fpy);
    	 
    	 //Map<String,List<ExcelPojo>>exc2=es.pojodata(exc, fja, fjs, fde, fdev, fpy);
    	 
    	 List<String> dates=es.get();
    	 
    	System.out.println(dates);
    	 
       //ExcelExporter excelExporter = new ExcelExporter(exc2,dates,month,fja, fjs, fde, fdev, fpy);
    	
       //excelExporter.export(response);  
		

	}
	
	
	
	/*@GetMapping("/gender/excel")
	public void exportIntoExcelFileGender(HttpServletResponse response) throws IOException {
	
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=_GenderSheet"+currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
        List<Gender> g=gr.findAll();
        
        GenderExporter excelExporter = new GenderExporter(g);
        
       excelExporter.export(response);  
		

	}*/
	
	 

}
