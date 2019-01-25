package com.nanourl.helpers;

/*
 * Helper interface to store all the necessary constants
 * 
 * Author: Harsh Vasoya
 */

public interface Constants {

	final String	DB_URL						= "jdbc:mysql://localhost:3306";
	final String	DB_USERNAME					= "root";
	final String	DB_PASSWORD					= "";
	final String	DB_SCHEMA_NAME				= "nanourl";
	
	final String	REDIS_HOSTNAME				= "127.0.0.1";
	final int		REDIS_PORT					= 6379;
	final long		CACHE_EXPIRY_TIME_SEC		= 120L;
	
	final int 		BASE_NUM 					= 62;
	final String 	KEYS 						= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	final long		MOD							= 3521614606199L;
	final double	URL_EXPIRY_TIME_HOURS		= 72;
	final int		SHORT_URL_LENGTH			= 21;
	
	final String[]	DOMAIN_NAMES				= {	"http://pro.pt/",
													"http://hou.zz/",
													"http://ma.kan/"};
	
	final String	MSG_URL_NOT_FOUND			= "4XX: Not Found.\nEither this page doesn't exist OR your short-url had been reduced beyond nanoscale...";
	final String	MSG_URL_TOO_SHORT			= "4XX: URL too short.\nSorry to say, but your URL is not worth reducing.";
	final String 	MSG_SAVE_ERROR				= "5XX: Unable to save.\nSometimes, bad things happen. Try again";
	final String	MSG_REPORT_NOT_FOUND		= "4XX: Not Found.\nEither you went too much back in past OR too much ahead in future.";
	
}
