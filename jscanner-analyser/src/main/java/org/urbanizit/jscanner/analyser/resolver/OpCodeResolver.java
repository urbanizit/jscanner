package org.urbanizit.jscanner.analyser.resolver;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.asm.Opcodes;

public class OpCodeResolver {

    private static Map<Integer, String> codeCache;
    
    static {
    	try{
    		init();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }

    private static void init()throws IllegalAccessException{
    	codeCache = new HashMap<Integer, String>();
    	
    	Field[] fields = Opcodes.class.getFields();
    	
    	if(fields != null){
    		for (Field field : fields) {

    			Class<?> classType = field.getType();
    			if( Integer.TYPE.equals(classType)){
    				codeCache.put(field.getInt(null), field.getName());
    			}else if(classType.equals(Integer.class)){
    				codeCache.put((Integer)field.get(null), field.getName());
    			}
			}
    	}
    }
 
   public static String resolve(Integer integer){
	   return codeCache.get(integer);
   }
}