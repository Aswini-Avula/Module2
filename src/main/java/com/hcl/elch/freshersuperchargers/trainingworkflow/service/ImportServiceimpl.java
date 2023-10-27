package com.hcl.elch.freshersuperchargers.trainingworkflow.service;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestDE;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJavaScript;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestPython;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDERepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDevopsRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJAVARepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJavaScriptRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestPythonRepo;


@Service
public class ImportServiceimpl {
	
	@Autowired
	private HackerranktestDevopsRepo Drepo;
	
	@Autowired
	private HackerranktestJAVARepo Jrepo;
	
	@Autowired
	private HackerranktestPythonRepo Prepo;
	
	@Autowired
	private HackerranktestJavaScriptRepo Jsrepo;
	
	@Autowired
	private HackerranktestDERepo Derepo;
	
	
	
    public void importpythondata(XSSFSheet worksheet,String excelname) {
		
		for(int i=1;i<=worksheet.getLastRowNum();i++)
		{
			XSSFRow row = worksheet.getRow(i);
			HackerrankTestPython py=new HackerrankTestPython();
			py.setFull_Name(row.getCell(2).toString());
			py.setDate_Taken(row.getCell(3).toString());
			py.setLogin_Id(row.getCell(4).toString());
			String sap=row.getCell(5).toString();
			int number = (int) Double.parseDouble(sap);
			Long l=Long.valueOf(number);
			py.setSapId(l);
			py.setATS_State(row.getCell(6).toString());
			py.setTags(row.getCell(7).toString());
			py.setInvited_By(row.getCell(8).toString());
			py.setIp_address(row.getCell(9).toString());
			py.setPercentage_Score(row.getCell(10).toString());
			py.setTotal_Score(row.getCell(11).toString());
			py.setMax_Score(row.getCell(12).toString());
			py.setTime_Taken(row.getCell(13).toString());
			py.setReport_Link(row.getCell(14).toString());
			py.setCoding(row.getCell(15).toString());
			py.setMCQ(row.getCell(16).toString());
			py.setPlagarism(row.getCell(17).toString());
			py.setAttempt_Count(row.getCell(18).toString());
            py.setNetwork_Disconnect_Duration(row.getCell(19).toString());
            py.setOutof_Window_Duration(row.getCell(20).toString());
            py.setNumber_Of_Window_Exists(row.getCell(21).toString());
            py.setYears_experience(row.getCell(22).toString());
            py.setProgramming_Score(row.getCell(23).toString());
            py.setMcq_Score(row.getCell(24).toString());
            py.setExcel_Name(excelname);
            py.setSkill("5");;
            Prepo.save(py);
		}
		
		System.out.println(worksheet);
	}

	
   
   
	public void importdevopsdata(XSSFSheet worksheet,String excelname) {
		
		for(int i=1;i<=worksheet.getLastRowNum();i++)
		{
			XSSFRow row = worksheet.getRow(i);
			HackeerankTestDevops dev=new HackeerankTestDevops();
			dev.setFull_Name(row.getCell(2).toString());
			dev.setDate_Taken(row.getCell(3).toString());
			dev.setLogin_Id(row.getCell(4).toString());
			String sap=row.getCell(5).toString();
			int number = (int) Double.parseDouble(sap);
			Long l=Long.valueOf(number);
			dev.setSapId(l);
			dev.setATS_State(row.getCell(6).toString());
			dev.setTags(row.getCell(7).toString());
			dev.setInvited_By(row.getCell(8).toString());
			dev.setIp_address(row.getCell(9).toString());
			dev.setPercentage_Score(row.getCell(10).toString());
			dev.setTotal_Score(row.getCell(11).toString());
			dev.setMax_Score(row.getCell(12).toString());
			dev.setTime_Taken(row.getCell(13).toString());
			dev.setReport_Link(row.getCell(14).toString());
			dev.setSudo_Rank(row.getCell(15).toString());

			dev.setPlagarism(row.getCell(16).toString());
			dev.setAttempt_Count(row.getCell(17).toString());
            dev.setNetwork_Disconnect_Duration(row.getCell(18).toString());
            dev.setYears_experience(row.getCell(19).toString());
            dev.setBizops_coding_score(row.getCell(20).toString());
            dev.setExcel_Name(excelname);
            dev.setSkill("3");
            Drepo.save(dev);
		}
		
		System.out.println(worksheet);
	}
	
	
	
	public void importjavadata(XSSFSheet worksheet,String excelname) {
		
		//HackerrankTestJAVA java=new HackerrankTestJAVA();
		for(int i=1;i<=worksheet.getLastRowNum();i++)
		{
			XSSFRow row = worksheet.getRow(i);
			HackerrankTestJAVA java=new HackerrankTestJAVA();
			java.setFull_Name(row.getCell(2).toString());
			java.setDate_Taken(row.getCell(3).toString());
			java.setLogin_Id(row.getCell(4).toString());
			String sap=row.getCell(5).toString();
			int number = (int) Double.parseDouble(sap);
			Long l=Long.valueOf(number);
			java.setSapId(l);
			java.setATS_State(row.getCell(6).toString());
			java.setTags(row.getCell(7).toString());
			java.setInvited_By(row.getCell(8).toString());
			java.setIp_address(row.getCell(9).toString());
			java.setPercentage_Score(row.getCell(10).toString());
			java.setTotal_Score(row.getCell(11).toString());
			java.setMax_Score(row.getCell(12).toString());
			java.setTime_Taken(row.getCell(13).toString());
			java.setReport_Link(row.getCell(14).toString());
			java.setCoding(row.getCell(15).toString());
			java.setMCQ(row.getCell(16).toString());
			java.setPlagarism(row.getCell(17).toString());
			java.setAttempt_Count(row.getCell(18).toString());
            java.setNetwork_Disconnect_Duration(row.getCell(19).toString());
            java.setYears_experience(row.getCell(20).toString());
            java.setJava_Score(row.getCell(21).toString());
            java.setSql_Score(row.getCell(22).toString());
            java.setRest_api_score(row.getCell(23).toString());
            java.setSpringboot_Score(row.getCell(24).toString());
            java.setJunit_Score(row.getCell(25).toString());
            java.setExcel_Name(excelname);
            java.setSkill("1");
            Jrepo.save(java);
		}

		System.out.println("Entered Service "+worksheet);
	}
	
	

    public void importjavascriptdata(XSSFSheet worksheet,String excelname) {
		
		for(int i=1;i<=worksheet.getLastRowNum();i++)
		{
			XSSFRow row = worksheet.getRow(i);
			HackerrankTestJavaScript js=new HackerrankTestJavaScript();
			js.setFull_Name(row.getCell(2).toString());
			js.setDate_Taken(row.getCell(3).toString());
			js.setLogin_Id(row.getCell(4).toString());
			String sap=row.getCell(5).toString();
			int number = (int) Double.parseDouble(sap);
			Long l=Long.valueOf(number);
			js.setSapId(l);
			js.setATS_State(row.getCell(6).toString());
			js.setTags(row.getCell(7).toString());
			js.setInvited_By(row.getCell(8).toString());
			js.setIp_address(row.getCell(9).toString());
			js.setPercentage_Score(row.getCell(10).toString());
			js.setTotal_Score(row.getCell(11).toString());
			js.setMax_Score(row.getCell(12).toString());
			js.setTime_Taken(row.getCell(13).toString());
			js.setReport_Link(row.getCell(14).toString());
			
			js.setCoding(row.getCell(15).toString());
			//js.setMCQ(row.getCell(16).toString());
			js.setPlagarism(row.getCell(16).toString());
			js.setAttempt_Count(row.getCell(17).toString());
            js.setNetwork_Disconnect_Duration(row.getCell(18).toString());
            js.setYears_experience(row.getCell(19).toString());
            js.setJavascript_Score(row.getCell(20).toString());
            js.setExcel_Name(excelname);
            js.setSkill("4");
          
            Jsrepo.save(js);
           
		}
		
		System.out.println(worksheet);
	}
  
  
  
    
    public void importdedata(XSSFSheet worksheet,String excelname) {
		
		for(int i=1;i<=worksheet.getLastRowNum();i++)
		{
			XSSFRow row = worksheet.getRow(i);
			HackerrankTestDE de=new HackerrankTestDE();
			de.setFull_Name(row.getCell(2).toString());
			de.setDate_Taken(row.getCell(3).toString());
			de.setLogin_Id(row.getCell(4).toString());
			String sap=row.getCell(5).toString();
			int number = (int) Double.parseDouble(sap);
			Long l=Long.valueOf(number);
			de.setSapId(l);
			de.setATS_State(row.getCell(6).toString());
			de.setTags(row.getCell(7).toString());
			de.setInvited_By(row.getCell(8).toString());
			de.setIp_address(row.getCell(9).toString());
			de.setPercentage_Score(row.getCell(10).toString());
			de.setTotal_Score(row.getCell(11).toString());
			de.setMax_Score(row.getCell(12).toString());
			de.setTime_Taken(row.getCell(13).toString());
			de.setReport_Link(row.getCell(14).toString());
			de.setCoding(row.getCell(15).toString());
			
			de.setDatabase_Score(row.getCell(16).toString());
			de.setMCQ(row.getCell(17).toString());
		
			de.setPlagarism(row.getCell(18).toString());
			de.setAttempt_Count(row.getCell(19).toString());
            de.setNetwork_Disconnection_Duration(row.getCell(20).toString());
            de.setYears_experience(row.getCell(21).toString());
            
          de.setDe_mcqs_Score(row.getCell(22).toString());
          de.setDatabase_sql_Score(row.getCell(23).toString());
          de.setProgramming_language_Score(row.getCell(24).toString());
          de.setExcel_Name(excelname);
          de.setSkill("2");
          Derepo.save(de);
		}
		
		System.out.println(worksheet);
	}

}