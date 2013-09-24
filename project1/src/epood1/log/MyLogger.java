package epood1.log;

import org.apache.log4j.*;

/**
 * 
 * @author Jaanus Ojasoo
 *   May 20, 2013
 */
public class MyLogger  {

    static Logger logger = Logger.getLogger(MyLogger.class);

	public  void Log(String method_name,String msg) {
		String log_row="APP_ERROR:" + " method=" + method_name + " error_message=" + msg;
		logger.info(log_row);
	}

	/**
	 * @param string
	 */
	public void LogMessage(String string) {
		logger.info(string);
	}

}
