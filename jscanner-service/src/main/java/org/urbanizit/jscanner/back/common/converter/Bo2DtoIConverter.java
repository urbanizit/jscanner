package org.urbanizit.jscanner.back.common.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.bo.EarArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.JarArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodCallBo;
import org.urbanizit.jscanner.back.persistence.bo.NestableArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.WarArchiveBo;
import org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.EarArchive;
import org.urbanizit.jscanner.transfert.JarArchive;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.MethodCall;
import org.urbanizit.jscanner.transfert.NestableArchive;
import org.urbanizit.jscanner.transfert.WarArchive;




public abstract class Bo2DtoIConverter {

	public static Archive convert(ArchiveBo in){
		return convert( in, false);
	}	
	
	
	public static ArchiveCriteria convert(ArchiveBoCriteria in){
		if(in == null) return null;	
		
		ArchiveCriteria out = new ArchiveCriteria();

		out.setChecksum(in.getChecksum());
		out.setArchiveIds(in.getArchiveIds());
		out.setArchiveNames(in.getArchiveNames());
		out.setCanonicalNamesDependencies(in.getCanonicalNamesDependencies());
		out.setClassNames(in.getClassNames());
		out.setMethodCalled(in.getMethodCalled());
		out.setMethodProvided(in.getMethodProvided());
		out.setPackageNames(in.getPackageNames());
		out.setCompagnyFile(in.getCompagnyFile());
		return out;
	}
	
	
	public static Archive convert(ArchiveBo  in, boolean cascade){
		if(in == null) return null;	
		
		Archive out = null;		
		if(in instanceof JarArchiveBo){
			out = new JarArchive();
		}else if(in instanceof WarArchiveBo){
			out = new WarArchive();
		}else if(in instanceof EarArchiveBo){
			out = new EarArchive();
		}else{
			throw new RuntimeException("Unsupported type");
		}		
		out.setChecksum(in.getChecksum());
		out.setName(in.getName());
		out.setId(in.getId());
		out.setRegistrationDate(in.getRegistrationDate());
		out.setOwnerGroup(in.getOwnerGroup());
		out.setCompagnyFile(in.getCompagnyFile());
		out.setWsArtifact(in.isWsArtifact());
		
		if(cascade){
			if(in instanceof NestableArchiveBo){
				NestableArchiveBo nestableArchive = (NestableArchiveBo) in;
				
				if(nestableArchive.getSubArchives() != null){
					List<Archive> subArchives = new ArrayList<Archive>(); 
					for (ArchiveBo nestedArchive : nestableArchive.getSubArchives()) {
						subArchives.add(convert(nestedArchive, true));
					}
					((NestableArchive)out).setSubArchives(subArchives);			
				}
			}
		}
		
		return out;
	}	
		
	public static ClassFile  convert(ClassFileBo in){
		if(in == null) return null;
		ClassFile  out = new ClassFile ();		
		out.setId(in.getId());	
		out.setChecksum(in.getChecksum());
		out.setClassVersion(in.getClassVersion());
		out.setClassSerialVersionUID(in.getClassSerialVersionUID());
		out.setClassName(in.getSimpleClassName());
		if(in.getPackageName()!= null) out.setPackageName(in.getPackageName().getPackageName());
		if(in.getClassName()!= null)out.setCanonicalName(in.getClassName().getClassName());
		out.setIsInterface(in.getIsInterface());
		return out;
	}	
	
	
	public static Method convert(MethodBo in){
		if(in == null) return null;
		Method  out = new Method();		
		out.setId(in.getId());	
		out.setMethodName(in.getMethodName());
		out.setMethodSignature(in.getSignature().getMethodSignature());
		out.setMethodReadableSignature(in.getSignature().getReadableSignature());
		out.setClassName(in.getClassFile().getClassName().getClassName());
		return out;
	}

	public static List<Archive> convertArchives(Collection<ArchiveBo> in) {
		if(in == null) return null;
		
		List<Archive> res = new ArrayList<Archive>();
		for (ArchiveBo archive : in) {
			res.add(convert(archive));
		}
		return res;
	}
	
	public static List<ClassFile> convertClassFiles(Collection<ClassFileBo> in) {
		if(in == null) return null;
		
		List<ClassFile> res = new ArrayList<ClassFile>();
		for (ClassFileBo classFile : in) {
			res.add(convert(classFile));
		}
		return res;
	}
	
//	public static ArchiveCriteria convert(ArchiveCriteria in) {
//		if(in == null) return null;
//		
//		ArchiveCriteria res = new ArchiveCriteria();
//		res.setChecksum(in.getChecksum());
//		return res;
//	}


	public static List<Method> convertMethods(Collection<MethodBo> in) {
		if(in == null) return null;
		
		List<Method> res = new ArrayList<Method>();
		for (MethodBo method : in) {
			res.add(convert(method));
		}
		return res;
	}
	

	public static List<MethodCall> convertMethodCalls(Collection<MethodCallBo> in) {
		if(in == null) return null;
		
		List<MethodCall> res = new ArrayList<MethodCall>();
		for (MethodCallBo methodCall : in) {
			res.add(convert(methodCall));
		}
		return res;
	}


	private static MethodCall convert(MethodCallBo in) {
		if(in == null) return null;
		MethodCall  out = new MethodCall();		
		out.setId(in.getId());	
		out.setMethodSignature(in.getSignature().getMethodSignature());
		out.setMethodReadableSignature(in.getSignature().getReadableSignature());
		return out;
	}
}
