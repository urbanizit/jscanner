package org.urbanizit.jscanner.back.common.metadata.locator;

import java.util.ArrayList;
import java.util.List;

import org.urbanizit.jscanner.back.common.metadata.applier.CompanyFlagMetaDataApplier;
import org.urbanizit.jscanner.back.common.metadata.applier.MetaDataApplier;
import org.urbanizit.jscanner.back.common.metadata.applier.OwerGroupMetaDataApplier;
import org.urbanizit.jscanner.transfert.Archive;

public class MetaDataApplierLocator implements Locator<MetaDataApplier<Archive>> {

	public List<MetaDataApplier<Archive>> locate(){		
		
		 List<MetaDataApplier<Archive>> res = new ArrayList<MetaDataApplier<Archive>>();
		 res.add(new CompanyFlagMetaDataApplier());
		 res.add(new OwerGroupMetaDataApplier());
		 return res;
	}
}
