package com.hcl.elch.freshersuperchargers.trainingworkflow.controller;

import java.util.ArrayList;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestDE;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJavaScript;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestPython;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Task;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDERepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDevopsRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJAVARepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJavaScriptRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestPythonRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.TaskRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.EmailSenderService;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.HackerrankServiceImpl;

@Component
public class TaskAssignmentListener implements TaskListener {

	@Autowired
	RuntimeService rs;

	@Autowired
	private TaskRepo tr;
	
	@Autowired
	private HackerrankServiceImpl hj;
	
	
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
	
	

	long id;

	@Autowired
	EmailSenderService senderService;

	@Value("${camunda.url}")
	String url;

	public static String Assignee = null;

	protected final static Logger log = LogManager.getLogger(TaskAssignmentListener.class.getName());
	
	public void mailSending(DelegateTask delegateTask,String task,String taskName,String username,String[]recipient) {
		try {
			if (taskName.contains("POC")) {
				String branch = (String) delegateTask.getVariable("BranchName");
				String githuburl = (String) delegateTask.getVariable("githuburl");
				senderService.mailSendingForPocApprover("team", recipient, task, url, username, branch,
						githuburl);
			} else {

				senderService.mailSendingToApprover("team", recipient, task, url, username);
			}

		}

		catch (Exception e) {
			log.error("Exception occured. Could not send email to assignees in group ");
		}
	}
	
	
	
	public String score(int gid,Long uid) {
		String s="";
		if(gid==1)
		{
				
				HackerrankTestJAVA j=hj.getByjId(gid);
				System.out.println(j.getPercentage_Score());
				s=j.getPercentage_Score();
		}
		else if(gid==2) {
			HackerrankTestDE j=hj.getBydeId(uid);
			System.out.println(j.getPercentage_Score());
			s=j.getPercentage_Score();
		}
		else if(gid==3)
		{
			HackeerankTestDevops j=hj.getBydId(uid);
			System.out.println(j.getPercentage_Score());
			s=j.getPercentage_Score();
		}
		else if(gid==4)
		{
			HackerrankTestJavaScript j=hj.getByjsId(uid);
			System.out.println(j.getPercentage_Score());
			s=j.getPercentage_Score();
		}
		else if(gid==5){
			
			HackerrankTestPython j=hj.getBypId(uid);
			System.out.println(j.getPercentage_Score());
			s=j.getPercentage_Score();
		}
		
		return s;
		
	}
	
	
	
	
	
	
	
	

	public void notify(DelegateTask delegateTask) {
		try {

			log.info("This is the SME/Approver task");
			log.debug("Variables : {}", delegateTask.getVariable("groupId"));
			String taskId = delegateTask.getId();
			String assignee = delegateTask.getAssignee();
			id = TaskController.id;
			url = url + taskId;

			String task = (String) delegateTask.getVariable("task");

			String taskName = delegateTask.getName();

			String username = (String) delegateTask.getVariable("username");
			
			
			
			String userid=(String) delegateTask.getVariable("userId");
			String groupId=(String) delegateTask.getVariable("groupId");
			
			System.out.println("Group Id"+groupId);
			Long uid=Long.parseLong(userid);
			
			int gid=Integer.parseInt(groupId);
			
			String sc=score(gid,uid);
			System.out.println("score "+sc);
			delegateTask.setVariable("score", sc);
			
			
			List<User> userList = delegateTask.getProcessEngineServices().getIdentityService().createUserQuery()
					.memberOfGroup((String) delegateTask.getVariable("groupId")).list();
			log.info("Group users {}" , userList);
			Assignee = assignee;

			String recipient[] = new String[userList.size()];
			for (int i = 0; i < userList.size(); i++) {
				User user = userList.get(i);
				String email = user.getEmail();
				recipient[i] = email;
				log.info("Assignees :{}", user.getFirstName());
				log.info("Assignees Mail :{}", email);

			}
			log.info("Recipient :{}", (Object)recipient);
			log.info("Approval url :- {}", url);

			if (recipient.length > 0) {
				mailSending(delegateTask,task,taskName,username,recipient);

			} else {
				log.error("Not sending email to group " + "', users has no email address.");
			}
		} catch (Exception e) {
			log.error("Exception in Email Sending for sme in task assignment listener class");
			e.printStackTrace();
			Task t1 = tr.getById(id);
			t1.setStatus("Error");
			tr.save(t1);
		}

	}
}
