package org.kns.timecard.frontend.common.utility;

import org.kns.timecard.backend.user.exception.MailNotSendException;
import org.kns.timecard.frontend.timecard.organization.dto.OrganizationDto;
import org.kns.timecard.frontend.user.dto.TimecardUserDto;

/**
 * 
 * @author Jeevan -- KNS Technologies Corporation
 *
 *Created on June 25, 2014
 *Utility Interface to send Emails
 */
public interface EmailSender {
	public void sendForgotPasswordMail( final TimecardUserDto user)throws MailNotSendException;
	public void sendOrganizationCreationMail(final OrganizationDto organizationDto)throws MailNotSendException;
}
