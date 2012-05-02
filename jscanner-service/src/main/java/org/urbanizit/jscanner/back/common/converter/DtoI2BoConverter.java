package org.urbanizit.jscanner.back.common.converter;

import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;
import org.urbanizit.jscanner.back.persistence.bo.ClassNameBo;
import org.urbanizit.jscanner.back.persistence.bo.EarArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.JarArchiveBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodCallBo;
import org.urbanizit.jscanner.back.persistence.bo.MethodSignatureBo;
import org.urbanizit.jscanner.back.persistence.bo.PackageNameBo;
import org.urbanizit.jscanner.back.persistence.bo.WarArchiveBo;
import org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria;
import org.urbanizit.jscanner.transfert.Archive;
import org.urbanizit.jscanner.transfert.ArchiveCriteria;
import org.urbanizit.jscanner.transfert.ClassFile;
import org.urbanizit.jscanner.transfert.EarArchive;
import org.urbanizit.jscanner.transfert.JarArchive;
import org.urbanizit.jscanner.transfert.Method;
import org.urbanizit.jscanner.transfert.MethodCall;
import org.urbanizit.jscanner.transfert.WarArchive;


public abstract class DtoI2BoConverter{

	public static ArchiveBo convert(Archive  in){
		if(in == null) return null;	
		
		ArchiveBo out = null;		
		if(in instanceof JarArchive){
			out = new JarArchiveBo();
		}else if(in instanceof WarArchive){
			out = new WarArchiveBo();
		}else if(in instanceof EarArchive){
			out = new EarArchiveBo();
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
		return out;
	}	
		
	public static ClassFileBo convert(ClassFile in){
		if(in == null) return null;
		ClassFileBo out = new ClassFileBo();		
		out.setId(in.getId());	
		out.setChecksum(in.getChecksum());
		out.setClassVersion(in.getClassVersion());
		out.setClassSerialVersionUID(in.getClassSerialVersionUID());
		out.setSimpleClassName(in.getClassName());
		out.setPackageName(new PackageNameBo(in.getPackageName()));
		out.setClassName(new ClassNameBo(in.getCanonicalName()));	
		out.setIsInterface(in.getIsInterface());
		return out;
	}	
	
	public static MethodBo convert(Method in){
		if(in == null) return null;
		MethodBo  out = new MethodBo();		
		out.setId(in.getId());	
		out.setMethodName(in.getMethodName());
		
		MethodSignatureBo methodSignature = new MethodSignatureBo();
		methodSignature.setMethodSignature(in.getMethodSignature());
		methodSignature.setReadableSignature(in.getMethodReadableSignature());
		out.setSignature(methodSignature);
		return out;
	}
	
	public static MethodCallBo convert(MethodCall in){
		if(in == null) return null;
		MethodCallBo  out = new MethodCallBo();		
		out.setId(in.getId());	
		out.setMethodName(in.getMethodName());
		MethodSignatureBo methodSignature = new MethodSignatureBo();
		methodSignature.setMethodSignature(in.getMethodSignature());
		methodSignature.setReadableSignature(in.getMethodReadableSignature());
		out.setSignature(methodSignature);
		return out;
	}	
	
	
	public static ArchiveBoCriteria convert(ArchiveCriteria in){
		if(in == null) return null;
		ArchiveBoCriteria  out = new ArchiveBoCriteria();		
		out.setArchiveIds(in.getArchiveIds());
		out.setCanonicalNamesDependencies(in.getCanonicalNamesDependencies());
		out.setChecksum(in.getChecksum());
		out.setClassNames(in.getCanonicalNames());
		out.setPackageNames(in.getPackageNames());	
		out.setArchiveNames(in.getArchiveNames());		
		out.setMethodCalled(in.getMethodCalled());	
		out.setMethodProvided(in.getMethodProvided());	
		out.setCompagnyFile(in.getCompagnyFile());
		return out;
	}	
}
