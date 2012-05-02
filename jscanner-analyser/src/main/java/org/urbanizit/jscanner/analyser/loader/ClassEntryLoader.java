package org.urbanizit.jscanner.analyser.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;


import org.urbanizit.jscanner.analyser.resolver.MethodSignatureResolver;
import org.urbanizit.jscanner.analyser.scanner.utils.ClassUtils;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.MethodCall;


public class ClassEntryLoader extends AbstractArchiveEntryLoader {
	
	private static final String CLASS_EXTENSION = ".class";
	
	private static final String SERIAL_VERSION_UID = "serialVersionUID";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSupported(final ZipEntry entry) {
		if(entry == null || entry.isDirectory()){
			return false;
		}		
		String entryName = entry.getName().toLowerCase();		
		
		return entryName.endsWith(CLASS_EXTENSION);
       		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void processLoading(final String fileName, final InputStream is, final Archive archive)throws Exception {
		
		if(archive.getClassFiles() == null){
			archive.setClassFiles(new ArrayList<ClassFile>());
		}
		archive.getClassFiles().add(readClass(is));		
	}
	   
	/**
	 * Analyse .class file.
	 * @param inputStream InputStream datas
	 * @return {@link ClassFileBo} loaded
	 */
	@SuppressWarnings("unchecked")
	private ClassFile readClass(InputStream inputStream)throws Exception {

		ClassPool CLASS_POOL = new ClassPool();;
		ClassFile res = new ClassFile();			

		CtClass ctClz = CLASS_POOL.makeClass(inputStream);		
		res.setClassVersion(Integer.valueOf(ctClz.getClassFile2().getMajorVersion()));
		res.setClassName(ctClz.getSimpleName());
		res.setCanonicalName(ctClz.getName());
		res.setIsInterface(ctClz.isInterface());
		res.setIsEnum(ctClz.isEnum());
				
				
		//Resolve serialVersionUID
		try {
			CtField field = ctClz.getField(SERIAL_VERSION_UID);
			res.setClassSerialVersionUID((Long) field.getConstantValue());
		} catch (NotFoundException nfe) {
			// Ignore - not serializable
		}
		
		//Resolve package name
		res.setPackageName(ClassUtils.extractPackage(ctClz.getName()));
		res.setPackageDependencies(new HashSet<String>());
		res.setClassDependencies(new HashSet<String>());
		
		//Search class dependencies
		for (String refClasses : (Collection<String>) ctClz.getRefClasses()) {
			res.getPackageDependencies().add(ClassUtils.extractPackage(refClasses));
			res.getClassDependencies().add(refClasses);
		}	
			
		
		//Read methods
		List<Method> methods = new ArrayList<Method>(); 
		res.setMethods(methods);
		
		//Finding methods references
		MethodCallEditor methodCallEditor = new MethodCallEditor();
		if(ctClz.getMethods() != null){
			for (CtBehavior method : ctClz.getDeclaredBehaviors()) {
				methods.add(readBeahavior(method));
				method.instrument(methodCallEditor);
			}			
		}
		res.setMethodCalls(new ArrayList<MethodCall>(methodCallEditor.getMethodCalls()));
		
		return res;
	}
		
	/**
	 * Read the method definition
	 * @param method
	 * @return
	 * @throws Exception
	 */
	private Method readBeahavior(final CtBehavior method)throws Exception {
		if(method == null)return null;
		
		Method res = new Method();
		res.setMethodName(method.getName());
		res.setMethodSignature(method.getName()+method.getSignature());
		//res.setMethodReadableSignature(MethodSignatureResolver.resolveNameSignature(method.getName()+" "+method.getSignature()));
		return res;
	}	
	
	
	/**
	 * Method call loader.
	 * @author Loic Dassonville
	 */
	private class MethodCallEditor extends ExprEditor{
		 
		private Map<String, MethodCall> map;
		
		public MethodCallEditor(){
			this.map = new HashMap<String, MethodCall>();
		}

		public void edit(final javassist.expr.MethodCall m) throws CannotCompileException {

		   	MethodCall methodCallDto = new MethodCall();		    		    	
		   	methodCallDto.setMethodName(m.getMethodName());
		   	methodCallDto.setMethodSignature(m.getMethodName()+m.getSignature());
		 	methodCallDto.setMethodReadableSignature(MethodSignatureResolver.resolveNameSignature(m.getMethodName()+" "+m.getSignature()));
		   	methodCallDto.setClassName(m.getClassName());
		   	map.put(m.getMethodName()+m.getSignature(), methodCallDto);	
		}
		
		public Collection<MethodCall> getMethodCalls(){
			if(map == null) return new ArrayList<MethodCall>();			
			return map.values();
		}
	}	
}
