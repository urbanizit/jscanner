package org.urbanizit.jscanner.back.services.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.urbanizit.jscanner.back.metadata.applier.MetaDataApplier;
import org.urbanizit.jscanner.back.metadata.locator.MetaDataApplierLocator;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;
import org.urbanizit.jscanner.back.persistence.criteria.ArchiveBoCriteria;
import org.urbanizit.jscanner.back.persistence.itf.ArchiveDaoItf;
import org.urbanizit.jscanner.transfert.itf.MetaDataServiceItf;


/**
 * Service dedicated to archives.
 * @author ldassonville
 */
@Service @Named("MetaDataServiceImpl")
@Transactional
public class MetaDataServiceImpl implements MetaDataServiceItf{

	@Inject private ArchiveDaoItf archiveDao;
	
	private final Logger logger = LoggerFactory.getLogger(MetaDataServiceImpl.class);

	@Override
	public void applyArchiveMetaData(List<Long> archivesIds) throws Exception {
		
		ArchiveBoCriteria archiveCriteria = new ArchiveBoCriteria();
		archiveCriteria.setArchiveIds(archivesIds);
		
		List<ArchiveBo> archives = archiveDao.findByCriteria(archiveCriteria);
				
		MetaDataApplierLocator locator = new MetaDataApplierLocator();
		List<MetaDataApplier<ArchiveBo>> metaDataAppliers = locator.locate();

		if(metaDataAppliers != null){
			
			for (MetaDataApplier<ArchiveBo> metaDataApplier : metaDataAppliers) {
				logger.debug("Apply {} metaData" , metaDataApplier.getClass().getSimpleName());
				for (ArchiveBo archive: archives) {
					metaDataApplier.apply(archive);
				}
			}		
		}
		
		archiveDao.save(archives);		
	}	
}
