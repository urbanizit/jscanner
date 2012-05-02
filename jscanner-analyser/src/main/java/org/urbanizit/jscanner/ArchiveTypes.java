package org.urbanizit.jscanner;

public class ArchiveTypes
{
   public static final int JAR = 0;
   public static final int WAR = 1;
   public static final int EAR = 2;
   
   /**
    * Constructor
    */
   private ArchiveTypes(){
   }
   
   public static Integer getArchiveType(String filename){
	   if(filename == null)
		   return null;
	  
	   filename = filename.trim().toLowerCase();
	   if(filename.endsWith(".jar")){
		   return JAR;
	   }else if(filename.endsWith(".war")){
		   return WAR;
	   }else if(filename.endsWith(".ear")){
		   return EAR;
	   }
	   return null;
   }   
}
