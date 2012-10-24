package org.urbanizit.jscanner.back.metadata.locator;

import java.util.ArrayList;
import java.util.List;

import org.urbanizit.jscanner.back.metadata.applier.CompanyFlagMetaDataApplier;
import org.urbanizit.jscanner.back.metadata.applier.JaxWSClientMetaDataApplier;
import org.urbanizit.jscanner.back.metadata.applier.MetaDataApplier;
import org.urbanizit.jscanner.back.metadata.applier.OwerGroupMetaDataApplier;
import org.urbanizit.jscanner.back.persistence.bo.ArchiveBo;

public class MetaDataApplierLocator implements Locator<MetaDataApplier<ArchiveBo>> {

	public List<MetaDataApplier<ArchiveBo>> locate(){		
		
		 List<MetaDataApplier<ArchiveBo>> res = new ArrayList<MetaDataApplier<ArchiveBo>>();
		 res.add(new CompanyFlagMetaDataApplier());
		 res.add(new OwerGroupMetaDataApplier());
		 res.add(new JaxWSClientMetaDataApplier());
		 return res;
	}
}
