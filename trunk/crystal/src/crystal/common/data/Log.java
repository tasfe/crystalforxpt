package crystal.common.data;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Log {
  private  static Logger logger ;
  
  public static Logger getLogger(){
	  return getLogger(Log.class);
  }
  public static Logger getLogger(Class<? extends Object> clazz){
//	  if(logger == null){
		  logger =  Logger.getLogger(clazz);
//	  }
	  logger.setLevel(Level.INFO);
	  return logger;
  }
  public static int test(){
	  return 10 /0;
	  
  }
  public static void main(String[] args){
	Logger logger = Log.getLogger();
  	logger.debug("debug test.");
  	logger.info("Info test");
  	try{test();}
  	catch(Exception t){
  		logger.error(t.getMessage(), t);
  	}
  }
}
