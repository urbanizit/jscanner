--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.4
-- Dumped by pg_dump version 9.0.3
-- Started on 2012-08-15 13:28:33

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 338 (class 2612 OID 11574)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1530 (class 1259 OID 38736)
-- Dependencies: 5
-- Name: archive; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE archive (
    dtype character varying(31) NOT NULL,
    archive_id bigint NOT NULL,
    checksum character varying(255),
    is_compagny_file boolean,
    name character varying(255),
    owner_group character varying(255),
    registration_date timestamp without time zone NOT NULL,
    version integer,
    is_ws_artifact boolean
);


ALTER TABLE public.archive OWNER TO jscanner;

--
-- TOC entry 1531 (class 1259 OID 38746)
-- Dependencies: 5
-- Name: archive_archive; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE archive_archive (
    archive_archive_id bigint NOT NULL,
    subarchives_archive_id bigint NOT NULL
);


ALTER TABLE public.archive_archive OWNER TO jscanner;

--
-- TOC entry 1532 (class 1259 OID 38751)
-- Dependencies: 5
-- Name: class_file; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE class_file (
    class_file_id bigint NOT NULL,
    checksum character varying(255),
    class_serial_version_uid bigint,
    class_version integer,
    is_enum boolean,
    is_interface boolean,
    simple_class_name character varying(255) NOT NULL,
    archive_id bigint NOT NULL,
    class_name_id bigint NOT NULL,
    local_ejb_interface_id bigint,
    package_name_id bigint,
    remote_ejb_interface_id bigint
);


ALTER TABLE public.class_file OWNER TO jscanner;

--
-- TOC entry 1533 (class 1259 OID 38759)
-- Dependencies: 5
-- Name: class_file_class_name; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE class_file_class_name (
    class_file_class_file_id bigint NOT NULL,
    classdependencies_class_name_id bigint NOT NULL
);


ALTER TABLE public.class_file_class_name OWNER TO jscanner;

--
-- TOC entry 1534 (class 1259 OID 38764)
-- Dependencies: 5
-- Name: class_file_package_name; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE class_file_package_name (
    class_file_class_file_id bigint NOT NULL,
    packagedependencies_package_id bigint NOT NULL
);


ALTER TABLE public.class_file_package_name OWNER TO jscanner;

--
-- TOC entry 1535 (class 1259 OID 38769)
-- Dependencies: 5
-- Name: class_name; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE class_name (
    class_name_id bigint NOT NULL,
    class_name character varying(255) NOT NULL,
    hashcode integer NOT NULL
);


ALTER TABLE public.class_name OWNER TO jscanner;

--
-- TOC entry 1544 (class 1259 OID 38941)
-- Dependencies: 5
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: jscanner
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO jscanner;

--
-- TOC entry 1536 (class 1259 OID 38776)
-- Dependencies: 5
-- Name: manifest; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE manifest (
    manifest_id bigint NOT NULL,
    archive_archive_id bigint
);


ALTER TABLE public.manifest OWNER TO jscanner;

--
-- TOC entry 1537 (class 1259 OID 38781)
-- Dependencies: 5
-- Name: manifest_entry; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE manifest_entry (
    manifest_entry_id bigint NOT NULL,
    key character varying(255),
    value character varying(255),
    manifest_id bigint NOT NULL
);


ALTER TABLE public.manifest_entry OWNER TO jscanner;

--
-- TOC entry 1538 (class 1259 OID 38789)
-- Dependencies: 5
-- Name: method; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE method (
    method_id bigint NOT NULL,
    is_constructor boolean NOT NULL,
    name character varying(255) NOT NULL,
    class_file_id bigint NOT NULL,
    method_reference_id bigint NOT NULL,
    method_signature_id bigint NOT NULL
);


ALTER TABLE public.method OWNER TO jscanner;

--
-- TOC entry 1539 (class 1259 OID 38794)
-- Dependencies: 5
-- Name: method_call; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE method_call (
    method_call_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    class_file_id bigint NOT NULL,
    class_name_id bigint NOT NULL,
    method_reference_id bigint NOT NULL,
    method_signature_id bigint NOT NULL
);


ALTER TABLE public.method_call OWNER TO jscanner;

--
-- TOC entry 1540 (class 1259 OID 38799)
-- Dependencies: 5
-- Name: method_reference; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE method_reference (
    method_reference_id bigint NOT NULL,
    hashcode character varying(255) NOT NULL,
    class_name_id bigint NOT NULL,
    method_signature_id bigint NOT NULL
);


ALTER TABLE public.method_reference OWNER TO jscanner;

--
-- TOC entry 1541 (class 1259 OID 38804)
-- Dependencies: 5
-- Name: method_signature; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE method_signature (
    method_signature_id bigint NOT NULL,
    hashcode integer NOT NULL,
    method_signature character varying(2048) NOT NULL,
    readable_signature character varying(2048) NOT NULL
);


ALTER TABLE public.method_signature OWNER TO jscanner;

--
-- TOC entry 1542 (class 1259 OID 38814)
-- Dependencies: 5
-- Name: package_name; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE package_name (
    package_id bigint NOT NULL,
    package_name character varying(255) NOT NULL
);


ALTER TABLE public.package_name OWNER TO jscanner;

--
-- TOC entry 1543 (class 1259 OID 38821)
-- Dependencies: 5
-- Name: sign; Type: TABLE; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE TABLE sign (
    sign_entry_id bigint NOT NULL,
    value character varying(255),
    archive_id bigint NOT NULL
);


ALTER TABLE public.sign OWNER TO jscanner;

--
-- TOC entry 1827 (class 2606 OID 38750)
-- Dependencies: 1531 1531 1531
-- Name: archive_archive_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY archive_archive
    ADD CONSTRAINT archive_archive_pkey PRIMARY KEY (archive_archive_id, subarchives_archive_id);


--
-- TOC entry 1823 (class 2606 OID 38745)
-- Dependencies: 1530 1530
-- Name: archive_checksum_key; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY archive
    ADD CONSTRAINT archive_checksum_key UNIQUE (checksum);


--
-- TOC entry 1825 (class 2606 OID 38743)
-- Dependencies: 1530 1530
-- Name: archive_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY archive
    ADD CONSTRAINT archive_pkey PRIMARY KEY (archive_id);


--
-- TOC entry 1837 (class 2606 OID 38763)
-- Dependencies: 1533 1533 1533
-- Name: class_file_class_name_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY class_file_class_name
    ADD CONSTRAINT class_file_class_name_pkey PRIMARY KEY (class_file_class_file_id, classdependencies_class_name_id);


--
-- TOC entry 1839 (class 2606 OID 38768)
-- Dependencies: 1534 1534 1534
-- Name: class_file_package_name_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY class_file_package_name
    ADD CONSTRAINT class_file_package_name_pkey PRIMARY KEY (class_file_class_file_id, packagedependencies_package_id);


--
-- TOC entry 1833 (class 2606 OID 38758)
-- Dependencies: 1532 1532
-- Name: class_file_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY class_file
    ADD CONSTRAINT class_file_pkey PRIMARY KEY (class_file_id);


--
-- TOC entry 1842 (class 2606 OID 38775)
-- Dependencies: 1535 1535
-- Name: class_name_class_name_key; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY class_name
    ADD CONSTRAINT class_name_class_name_key UNIQUE (class_name);


--
-- TOC entry 1845 (class 2606 OID 38773)
-- Dependencies: 1535 1535
-- Name: class_name_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY class_name
    ADD CONSTRAINT class_name_pkey PRIMARY KEY (class_name_id);


--
-- TOC entry 1851 (class 2606 OID 38788)
-- Dependencies: 1537 1537
-- Name: manifest_entry_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY manifest_entry
    ADD CONSTRAINT manifest_entry_pkey PRIMARY KEY (manifest_entry_id);


--
-- TOC entry 1848 (class 2606 OID 38780)
-- Dependencies: 1536 1536
-- Name: manifest_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY manifest
    ADD CONSTRAINT manifest_pkey PRIMARY KEY (manifest_id);


--
-- TOC entry 1860 (class 2606 OID 38798)
-- Dependencies: 1539 1539
-- Name: method_call_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY method_call
    ADD CONSTRAINT method_call_pkey PRIMARY KEY (method_call_id);


--
-- TOC entry 1854 (class 2606 OID 38793)
-- Dependencies: 1538 1538
-- Name: method_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY method
    ADD CONSTRAINT method_pkey PRIMARY KEY (method_id);


--
-- TOC entry 1865 (class 2606 OID 38803)
-- Dependencies: 1540 1540
-- Name: method_reference_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY method_reference
    ADD CONSTRAINT method_reference_pkey PRIMARY KEY (method_reference_id);


--
-- TOC entry 1870 (class 2606 OID 38813)
-- Dependencies: 1541 1541
-- Name: method_signature_method_signature_key; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY method_signature
    ADD CONSTRAINT method_signature_method_signature_key UNIQUE (method_signature);


--
-- TOC entry 1872 (class 2606 OID 38811)
-- Dependencies: 1541 1541
-- Name: method_signature_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY method_signature
    ADD CONSTRAINT method_signature_pkey PRIMARY KEY (method_signature_id);


--
-- TOC entry 1875 (class 2606 OID 38820)
-- Dependencies: 1542 1542
-- Name: package_name_package_name_key; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY package_name
    ADD CONSTRAINT package_name_package_name_key UNIQUE (package_name);


--
-- TOC entry 1877 (class 2606 OID 38818)
-- Dependencies: 1542 1542
-- Name: package_name_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY package_name
    ADD CONSTRAINT package_name_pkey PRIMARY KEY (package_id);


--
-- TOC entry 1879 (class 2606 OID 38825)
-- Dependencies: 1543 1543
-- Name: sign_pkey; Type: CONSTRAINT; Schema: public; Owner: jscanner; Tablespace: 
--

ALTER TABLE ONLY sign
    ADD CONSTRAINT sign_pkey PRIMARY KEY (sign_entry_id);


--
-- TOC entry 1834 (class 1259 OID 38963)
-- Dependencies: 1533
-- Name: cfcn_class_file_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX cfcn_class_file_idx ON class_file_class_name USING btree (class_file_class_file_id);


--
-- TOC entry 1835 (class 1259 OID 38964)
-- Dependencies: 1533
-- Name: cfcn_class_name_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX cfcn_class_name_idx ON class_file_class_name USING btree (classdependencies_class_name_id);


--
-- TOC entry 1828 (class 1259 OID 38943)
-- Dependencies: 1532
-- Name: class_file_archive_id_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX class_file_archive_id_idx ON class_file USING btree (archive_id);


--
-- TOC entry 1829 (class 1259 OID 38946)
-- Dependencies: 1532
-- Name: class_file_canonical_name_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX class_file_canonical_name_idx ON class_file USING btree (class_name_id);


--
-- TOC entry 1830 (class 1259 OID 38944)
-- Dependencies: 1532
-- Name: class_file_checksum_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX class_file_checksum_idx ON class_file USING btree (checksum);


--
-- TOC entry 1831 (class 1259 OID 38945)
-- Dependencies: 1532
-- Name: class_file_package_name_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX class_file_package_name_idx ON class_file USING btree (package_name_id);


--
-- TOC entry 1840 (class 1259 OID 38947)
-- Dependencies: 1535
-- Name: class_name_class_name_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX class_name_class_name_idx ON class_name USING btree (class_name);


--
-- TOC entry 1843 (class 1259 OID 38948)
-- Dependencies: 1535
-- Name: class_name_hashcode_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX class_name_hashcode_idx ON class_name USING btree (hashcode);


--
-- TOC entry 1846 (class 1259 OID 38950)
-- Dependencies: 1536
-- Name: manifest_archive_id_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX manifest_archive_id_idx ON manifest USING btree (archive_archive_id);


--
-- TOC entry 1849 (class 1259 OID 38951)
-- Dependencies: 1537
-- Name: manifest_entry_manifest_id_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX manifest_entry_manifest_id_idx ON manifest_entry USING btree (manifest_id);


--
-- TOC entry 1856 (class 1259 OID 38962)
-- Dependencies: 1539
-- Name: method_call_class_file_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_call_class_file_idx ON method_call USING btree (class_file_id);


--
-- TOC entry 1857 (class 1259 OID 38961)
-- Dependencies: 1539
-- Name: method_call_class_name_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_call_class_name_idx ON method_call USING btree (class_name_id);


--
-- TOC entry 1858 (class 1259 OID 38959)
-- Dependencies: 1539
-- Name: method_call_method_reference_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_call_method_reference_idx ON method_call USING btree (method_reference_id);


--
-- TOC entry 1861 (class 1259 OID 38960)
-- Dependencies: 1539
-- Name: method_call_signature_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_call_signature_idx ON method_call USING btree (method_signature_id);


--
-- TOC entry 1852 (class 1259 OID 38958)
-- Dependencies: 1538
-- Name: method_method_reference_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_method_reference_idx ON method USING btree (method_reference_id);


--
-- TOC entry 1862 (class 1259 OID 38956)
-- Dependencies: 1540
-- Name: method_reference_class_name_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_reference_class_name_idx ON method_reference USING btree (class_name_id);


--
-- TOC entry 1863 (class 1259 OID 38957)
-- Dependencies: 1540
-- Name: method_reference_hashcode; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_reference_hashcode ON method_reference USING btree (hashcode);


--
-- TOC entry 1866 (class 1259 OID 38955)
-- Dependencies: 1540
-- Name: method_reference_signature_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_reference_signature_idx ON method_reference USING btree (method_signature_id);


--
-- TOC entry 1867 (class 1259 OID 38954)
-- Dependencies: 1541
-- Name: method_signature_hashcode_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_signature_hashcode_idx ON method_signature USING btree (hashcode);


--
-- TOC entry 1855 (class 1259 OID 38952)
-- Dependencies: 1538
-- Name: method_signature_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_signature_idx ON method USING btree (method_signature_id);


--
-- TOC entry 1868 (class 1259 OID 38953)
-- Dependencies: 1541
-- Name: method_signature_method_signature_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX method_signature_method_signature_idx ON method_signature USING btree (method_signature);


--
-- TOC entry 1873 (class 1259 OID 38949)
-- Dependencies: 1542
-- Name: package_name_package_name_idx; Type: INDEX; Schema: public; Owner: jscanner; Tablespace: 
--

CREATE INDEX package_name_package_name_idx ON package_name USING btree (package_name);


--
-- TOC entry 1902 (class 2606 OID 38936)
-- Dependencies: 1824 1530 1543
-- Name: fk26d5bdc7ebec2c; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY sign
    ADD CONSTRAINT fk26d5bdc7ebec2c FOREIGN KEY (archive_id) REFERENCES archive(archive_id);


--
-- TOC entry 1891 (class 2606 OID 38881)
-- Dependencies: 1530 1536 1824
-- Name: fk366f1e2fc9b4b3c9; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY manifest
    ADD CONSTRAINT fk366f1e2fc9b4b3c9 FOREIGN KEY (archive_archive_id) REFERENCES archive(archive_id);


--
-- TOC entry 1901 (class 2606 OID 38931)
-- Dependencies: 1844 1540 1535
-- Name: fk4569294d9d05f3dd; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method_reference
    ADD CONSTRAINT fk4569294d9d05f3dd FOREIGN KEY (class_name_id) REFERENCES class_name(class_name_id);


--
-- TOC entry 1900 (class 2606 OID 38926)
-- Dependencies: 1540 1541 1871
-- Name: fk4569294dbaaca4e9; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method_reference
    ADD CONSTRAINT fk4569294dbaaca4e9 FOREIGN KEY (method_signature_id) REFERENCES method_signature(method_signature_id);


--
-- TOC entry 1899 (class 2606 OID 38921)
-- Dependencies: 1535 1844 1539
-- Name: fk4ace4afc9d05f3dd; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method_call
    ADD CONSTRAINT fk4ace4afc9d05f3dd FOREIGN KEY (class_name_id) REFERENCES class_name(class_name_id);


--
-- TOC entry 1896 (class 2606 OID 38906)
-- Dependencies: 1539 1871 1541
-- Name: fk4ace4afcbaaca4e9; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method_call
    ADD CONSTRAINT fk4ace4afcbaaca4e9 FOREIGN KEY (method_signature_id) REFERENCES method_signature(method_signature_id);


--
-- TOC entry 1898 (class 2606 OID 38916)
-- Dependencies: 1539 1864 1540
-- Name: fk4ace4afcbc765f49; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method_call
    ADD CONSTRAINT fk4ace4afcbc765f49 FOREIGN KEY (method_reference_id) REFERENCES method_reference(method_reference_id);


--
-- TOC entry 1897 (class 2606 OID 38911)
-- Dependencies: 1832 1532 1539
-- Name: fk4ace4afcf63649fd; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method_call
    ADD CONSTRAINT fk4ace4afcf63649fd FOREIGN KEY (class_file_id) REFERENCES class_file(class_file_id);


--
-- TOC entry 1893 (class 2606 OID 38891)
-- Dependencies: 1541 1538 1871
-- Name: fk8758c4e1baaca4e9; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method
    ADD CONSTRAINT fk8758c4e1baaca4e9 FOREIGN KEY (method_signature_id) REFERENCES method_signature(method_signature_id);


--
-- TOC entry 1895 (class 2606 OID 38901)
-- Dependencies: 1538 1540 1864
-- Name: fk8758c4e1bc765f49; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method
    ADD CONSTRAINT fk8758c4e1bc765f49 FOREIGN KEY (method_reference_id) REFERENCES method_reference(method_reference_id);


--
-- TOC entry 1894 (class 2606 OID 38896)
-- Dependencies: 1538 1832 1532
-- Name: fk8758c4e1f63649fd; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY method
    ADD CONSTRAINT fk8758c4e1f63649fd FOREIGN KEY (class_file_id) REFERENCES class_file(class_file_id);


--
-- TOC entry 1887 (class 2606 OID 38861)
-- Dependencies: 1832 1533 1532
-- Name: fka174ace8f4142a1; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file_class_name
    ADD CONSTRAINT fka174ace8f4142a1 FOREIGN KEY (class_file_class_file_id) REFERENCES class_file(class_file_id);


--
-- TOC entry 1888 (class 2606 OID 38866)
-- Dependencies: 1844 1535 1533
-- Name: fka174aceb9744abf; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file_class_name
    ADD CONSTRAINT fka174aceb9744abf FOREIGN KEY (classdependencies_class_name_id) REFERENCES class_name(class_name_id);


--
-- TOC entry 1892 (class 2606 OID 38886)
-- Dependencies: 1537 1847 1536
-- Name: fkbe72f24243c716e2; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY manifest_entry
    ADD CONSTRAINT fkbe72f24243c716e2 FOREIGN KEY (manifest_id) REFERENCES manifest(manifest_id);


--
-- TOC entry 1881 (class 2606 OID 38831)
-- Dependencies: 1531 1824 1530
-- Name: fkc71abe05290d1337; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY archive_archive
    ADD CONSTRAINT fkc71abe05290d1337 FOREIGN KEY (archive_archive_id) REFERENCES archive(archive_id);


--
-- TOC entry 1880 (class 2606 OID 38826)
-- Dependencies: 1531 1530 1824
-- Name: fkc71abe05b5ea72fa; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY archive_archive
    ADD CONSTRAINT fkc71abe05b5ea72fa FOREIGN KEY (subarchives_archive_id) REFERENCES archive(archive_id);


--
-- TOC entry 1889 (class 2606 OID 38871)
-- Dependencies: 1832 1534 1532
-- Name: fkd4f1cdc08f4142a1; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file_package_name
    ADD CONSTRAINT fkd4f1cdc08f4142a1 FOREIGN KEY (class_file_class_file_id) REFERENCES class_file(class_file_id);


--
-- TOC entry 1890 (class 2606 OID 38876)
-- Dependencies: 1876 1542 1534
-- Name: fkd4f1cdc0d0dd00e7; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file_package_name
    ADD CONSTRAINT fkd4f1cdc0d0dd00e7 FOREIGN KEY (packagedependencies_package_id) REFERENCES package_name(package_id);


--
-- TOC entry 1884 (class 2606 OID 38846)
-- Dependencies: 1844 1532 1535
-- Name: fkd590c0380c9892c; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file
    ADD CONSTRAINT fkd590c0380c9892c FOREIGN KEY (local_ejb_interface_id) REFERENCES class_name(class_name_id);


--
-- TOC entry 1885 (class 2606 OID 38851)
-- Dependencies: 1535 1844 1532
-- Name: fkd590c038343e1b1; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file
    ADD CONSTRAINT fkd590c038343e1b1 FOREIGN KEY (remote_ejb_interface_id) REFERENCES class_name(class_name_id);


--
-- TOC entry 1886 (class 2606 OID 38856)
-- Dependencies: 1535 1532 1844
-- Name: fkd590c039d05f3dd; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file
    ADD CONSTRAINT fkd590c039d05f3dd FOREIGN KEY (class_name_id) REFERENCES class_name(class_name_id);


--
-- TOC entry 1883 (class 2606 OID 38841)
-- Dependencies: 1530 1824 1532
-- Name: fkd590c03c7ebec2c; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file
    ADD CONSTRAINT fkd590c03c7ebec2c FOREIGN KEY (archive_id) REFERENCES archive(archive_id);


--
-- TOC entry 1882 (class 2606 OID 38836)
-- Dependencies: 1876 1532 1542
-- Name: fkd590c03e78f7bb9; Type: FK CONSTRAINT; Schema: public; Owner: jscanner
--

ALTER TABLE ONLY class_file
    ADD CONSTRAINT fkd590c03e78f7bb9 FOREIGN KEY (package_name_id) REFERENCES package_name(package_id);


--
-- TOC entry 1907 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2012-08-15 13:28:33

--
-- PostgreSQL database dump complete
--

