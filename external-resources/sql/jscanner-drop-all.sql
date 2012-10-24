-- get queries
SELECT 'drop table ' || tablename || ' cascade;' 
FROM pg_tables 
WHERE tablename !~ '^pg_' and tablename !~ '^sql';


drop table class_file cascade;
drop table archive_archive cascade;
drop table archive cascade;
drop table manifest cascade;
drop table sign cascade;
drop table class_file_class_name cascade;
drop table class_file_package_name cascade;
drop table class_file_method cascade;
drop table manifest_entry cascade;
drop table package_name cascade;
drop table method cascade;
drop table method_call cascade;
drop table method_signature cascade;
drop table method_reference cascade;
drop table class_name cascade;





SELECT 'truncate ' || tablename || ' cascade;' 
FROM pg_tables 
WHERE tablename !~ '^pg_' and tablename !~ '^sql';

truncate method cascade;
truncate sign cascade;
truncate method_call cascade;
truncate method_reference cascade;
truncate method_signature cascade;
truncate archive_archive cascade;
truncate class_file_class_name cascade;
truncate manifest cascade;
truncate class_file_method cascade;
truncate class_file_package_name cascade;
truncate class_file cascade;
truncate archive cascade;
truncate manifest_entry cascade;
truncate class_name cascade;
truncate package_name cascade;