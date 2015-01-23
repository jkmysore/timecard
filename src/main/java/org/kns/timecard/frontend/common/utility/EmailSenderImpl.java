package org.kns.timecard.frontend.common.utility;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.kns.timecard.exception.MailNotSendException;
import org.kns.timecard.frontend.organization.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.timecarduser.dto.TimecardUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;



/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 * Created by Jeevan on June 25, 2014
 * Utility Class to send all emails
 *
 */
@Service("emailSender")
public class EmailSenderImpl implements EmailSender {
	

	@Autowired
	private JavaMailSender mailSender;
	
	
	@Autowired
	private VelocityEngine velocityEngine=new VelocityEngine();
	
	/*@Resource(name="organizationDao")
	private OrganizationDao organizationDao;
	*/
	
	
	
	
	/**
	 * Created by Jeevan on June 25, 2014
	 * Method to send Forgot Password Mail
	 * 
	 */
	public void sendForgotPasswordMail( final TimecardUserDto user)throws MailNotSendException{		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
		 public void prepare(MimeMessage mimeMessage) throws Exception {     			 
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setTo(user.getTimeCardCredentials().getEmail());             
                message.setSubject("Timecard Reset Password Link");
                Map<String, Object> model=new HashMap<String, Object>();
                model.put("email", user.getTimeCardCredentials().getEmail());
        		model.put("user", user); 
        		String text=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "org/kns/timecard/templates/forgotPassword.vm","UTF-8", model);
        		message.setText(text, true);            
            }
        };
        this.mailSender.send(preparator);		
	}
	
	 /**
	 * Created by Jeevan on June 30, 2014
	 * Method to send OrganizationCreation Mail
	 * 
	 * */
	 
	public void sendOrganizationCreationMail(final OrganizationDto organizationDto)throws MailNotSendException{
		MimeMessagePreparator preparator=new MimeMessagePreparator() {		
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message=new MimeMessageHelper(mimeMessage,true);
				message.setTo(organizationDto.getSiteAdmin().getTimeCardCredentials().getEmail());
				message.setSubject("Organization Created Successfully, Please Login with Credentials Provided");
				 Map<String, Object> model=new HashMap<String, Object>();
	             model.put("username", organizationDto.getSiteAdmin().getTimeCardCredentials().getUsername());
	        	 model.put("organization",organizationDto); 
	        	String text=VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "org/kns/timecard/templates/organization.vm","UTF-8", model);	
	        	message.setText(text,true);
			}
		};
		this.mailSender.send(preparator);
	}
	
}
