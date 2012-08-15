CREATE INDEX class_file_archive_id_idx ON class_file (archive_id);
CREATE INDEX class_file_checksum_idx ON class_file (checksum);
CREATE INDEX class_file_package_name_idx ON class_file (package_name_id);
CREATE INDEX class_file_canonical_name_idx ON class_file (class_name_id);

CREATE INDEX class_name_class_name_idx ON class_name (class_name);
CREATE INDEX class_name_hashcode_idx ON class_name (hashcode);

CREATE INDEX package_name_package_name_idx ON package_name (package_name);

CREATE INDEX manifest_archive_id_idx ON manifest (archive_archive_id);

CREATE INDEX manifest_entry_manifest_id_idx ON manifest_entry (manifest_id);

CREATE INDEX method_signature_idx ON method (method_signature_id);
CREATE INDEX method_signature_method_signature_idx ON method_signature (method_signature);
CREATE INDEX method_signature_hashcode_idx ON method_signature (hashcode);


CREATE INDEX method_reference_signature_idx ON method_reference (METHOD_SIGNATURE_ID);
CREATE INDEX method_reference_class_name_idx ON method_reference (CLASS_NAME_ID);
CREATE INDEX method_reference_hashcode ON method_reference (HASHCODE);

CREATE INDEX method_method_reference_idx ON method (METHOD_REFERENCE_ID);

CREATE INDEX method_call_method_reference_idx ON method_call(METHOD_REFERENCE_ID);
CREATE INDEX method_call_signature_idx ON method_call (method_signature_id);
CREATE INDEX method_call_class_name_idx ON method_call (CLASS_NAME_ID);
CREATE INDEX method_call_class_file_idx ON method_call (CLASS_FILE_ID);


CREATE INDEX cfcn_class_file_idx ON class_file_class_name (CLASS_FILE_CLASS_FILE_ID);
CREATE INDEX cfcn_class_name_idx ON class_file_class_name (CLASSDEPENDENCIES_CLASS_NAME_ID);