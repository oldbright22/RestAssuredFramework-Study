package com.spotify.oauth2.utility;

import java.io.File;

import org.zeroturnaround.zip.ZipUtil;

import com.spotify.oauth2.utility.UtilityConstants;

public class ZipUtils {

	/* make Zip file of Extent Reports in Project Root folder */
	public static void zip() {

		/*
		 * FrameworkConstants.getExtentReportFolderPath():
		 * D:\Work_In_Local_Machine\
		 * FrameworkConstants.getZipped_ExtentReports_Folder_Name(): ExtentReports.zip
		 */

		ZipUtil.pack(new File(UtilityConstants.getExtentReportFolderPath()),
				new File(UtilityConstants.getZipped_ExtentReports_Folder_Name()));

		System.out.println("Zipped ExtentReports folder successfully");
	}

	public static void main(String[] args) {
		System.out.println(
				"FrameworkConstants.getExtentReportFolderPath(): " + UtilityConstants.getExtentReportFolderPath());
		System.out.println("FrameworkConstants.getZipped_ExtentReports_Folder_Name(): "
				+ UtilityConstants.getZipped_ExtentReports_Folder_Name());
		String reportsLocation = UtilityConstants.getProjectPath() + "/ExtentReports";
		ZipUtil.pack(new File(reportsLocation), new File("ExtentReports.zip"));
	}

}
