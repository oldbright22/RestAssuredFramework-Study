package com.spotify.oauth2.utility;

import static com.spotify.oauth2.java_Mail_API.EmailConfig.FROM;
import static com.spotify.oauth2.java_Mail_API.EmailConfig.PASSWORD;
import static com.spotify.oauth2.java_Mail_API.EmailConfig.PORT;
import static com.spotify.oauth2.java_Mail_API.EmailConfig.SERVER;
import static com.spotify.oauth2.java_Mail_API.EmailConfig.SUBJECT;
import static com.spotify.oauth2.java_Mail_API.EmailConfig.TO;

import javax.mail.MessagingException;

import com.spotify.oauth2.utility.UtilityConstants;
import com.spotify.oauth2.java_Mail_API.EmailAttachmentsSender;

public class EmailSendUtils {

	/**
	 * sendEmail_WithAttachmentsAndFormattedBodyText_ToManyUsersRajat
	 */
	public static void sendEmail(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs) {

		if (ConfigLoader.getInstance().getSendEmailToUsers().equalsIgnoreCase(UtilityConstants.getYes())) {
			System.out.println("File name: " + UtilityConstants.getExtentReportFilePath());

			String messageBody = getTestCasesCountInFormat(count_totalTCs, count_passedTCs, count_failedTCs,
					count_skippedTCs);
			System.out.println(messageBody);

			/*
			 * String attachmentFile_ExtentReport = Constants.REPORTS_Folder +
			 * "All_Automation_Report_Fri_Sep_10_03_47_17_IST_2021.html";
			 */

			String attachmentFile_ExtentReport = UtilityConstants.getExtentReportFilePath();

			try {
				EmailAttachmentsSender.sendEmailWithAttachments(SERVER, PORT,
						FROM, PASSWORD, TO, SUBJECT, messageBody, attachmentFile_ExtentReport);

				System.out.println("****************************************");
				System.out.println("Email sent successfully.");
				System.out.println("****************************************");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

	}

	private static String getTestCasesCountInFormat(int count_totalTCs, int count_passedTCs, int count_failedTCs,
			int count_skippedTCs) {
		System.out.println("count_totalTCs: " + count_totalTCs);
		System.out.println("count_passedTCs: " + count_passedTCs);
		System.out.println("count_failedTCs: " + count_failedTCs);
		System.out.println("count_skippedTCs: " + count_skippedTCs);

		return "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
				+ "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
				+ UtilityConstants.getProjectName() + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
				+ "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_totalTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_passedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
				+ "                <table style=\"background:#ff5454;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_failedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
				+ "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
				+ count_skippedTCs + "</td></tr>\r\n"
				+ "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
				+ "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
				+ "                </tr>\r\n" + "               \r\n" + "                \r\n"
				+ "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";
	}

}
