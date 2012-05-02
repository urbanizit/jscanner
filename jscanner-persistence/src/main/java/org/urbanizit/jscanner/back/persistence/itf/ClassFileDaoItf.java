package org.urbanizit.jscanner.back.persistence.itf;


import java.util.List;

import org.urbanizit.jscanner.back.persistence.bo.ClassFileBo;


public interface ClassFileDaoItf extends GenericDaoItf<ClassFileBo, Long> {

	List<ClassFileBo> findDependClass(final Long classFileId);
}
