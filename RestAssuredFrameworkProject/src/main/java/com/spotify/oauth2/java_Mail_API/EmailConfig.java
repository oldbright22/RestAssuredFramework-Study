package com.spotify.oauth2.java_Mail_API;

import com.spotify.oauth2.utility.UtilityConstants;

/**
 * Data for Sending EMail after execution
 */
public class EmailConfig {

	public static final String SERVER = "smtp.gmail.com";
	public static final String PORT = "587";
	public static final String FROM = "oldbright22@gmail.com";
	public static final String PASSWORD = "*********";
	public static final String[] TO = {"oldbright22@gmail.com"};
	public static final String SUBJECT = UtilityConstants.getProjectName();
}
