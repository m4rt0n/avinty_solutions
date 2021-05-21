-- korhaz.kapcs_min definition

-- Drop table

-- DROP TABLE kapcs_min;

CREATE TABLE kapcs_min (
	kapcs_min_id int4 NOT NULL,
	kapcs_min varchar(10) NOT NULL,
	CONSTRAINT kapcs_min_pkey PRIMARY KEY (kapcs_min_id)
);


-- korhaz.kapcs_tip definition

-- Drop table

-- DROP TABLE kapcs_tip;

CREATE TABLE kapcs_tip (
	kapcs_tip_id int4 NOT NULL,
	kapcs_tip varchar(10) NOT NULL,
	CONSTRAINT kapcs_tip_pkey PRIMARY KEY (kapcs_tip_id)
);


-- korhaz.nem definition

-- Drop table

-- DROP TABLE nem;

CREATE TABLE nem (
	nem_id int4 NOT NULL,
	nem varchar(20) NOT NULL,
	CONSTRAINT nem_pkey PRIMARY KEY (nem_id)
);


-- korhaz.telep definition

-- Drop table

-- DROP TABLE telep;

CREATE TABLE telep (
	telep_id int4 NOT NULL,
	telep varchar(20) NULL DEFAULT NULL::character varying,
	CONSTRAINT telep_pkey PRIMARY KEY (telep_id)
);


-- korhaz.beteg definition

-- Drop table

-- DROP TABLE beteg;

CREATE TABLE beteg (
	beteg_id int4 NOT NULL,
	lakcim_telep_id int4 NOT NULL,
	lakcim_adat varchar(50) NOT NULL,
	ker_nev varchar(20) NOT NULL,
	vez_nev varchar(20) NOT NULL,
	szul_ido date NOT NULL,
	szul_hely_id int4 NOT NULL,
	nem_id int4 NULL,
	a_nev varchar(20) NULL DEFAULT NULL::character varying,
	hal_ido date NULL,
	telsz int4 NULL,
	email varchar(20) NULL DEFAULT NULL::character varying,
	CONSTRAINT beteg_pkey PRIMARY KEY (beteg_id),
	CONSTRAINT beteg_lakcim_telep_id_fkey FOREIGN KEY (lakcim_telep_id) REFERENCES telep(telep_id),
	CONSTRAINT beteg_nem_id_fkey FOREIGN KEY (nem_id) REFERENCES nem(nem_id),
	CONSTRAINT beteg_szul_hely_id_fkey FOREIGN KEY (szul_hely_id) REFERENCES telep(telep_id)
);


-- korhaz.beteg_kapcs definition

-- Drop table

-- DROP TABLE beteg_kapcs;

CREATE TABLE beteg_kapcs (
	beteg1_id int4 NOT NULL,
	beteg2_id int4 NOT NULL,
	kapcs_tip_id int4 NOT NULL,
	kapcs_min_id int4 NOT NULL,
	kapcs_koz int4 NULL CHECK (kapcs_koz BETWEEN 1 AND 10),
	kapcs_kezd date NOT NULL,
	kapcs_veg date NULL,
	CONSTRAINT beteg_kapcs_pkey PRIMARY KEY (beteg1_id, beteg2_id, kapcs_tip_id),
	CONSTRAINT beteg_kapcs_beteg1_id_fkey FOREIGN KEY (beteg1_id) REFERENCES beteg(beteg_id),
	CONSTRAINT beteg_kapcs_beteg2_id_fkey FOREIGN KEY (beteg2_id) REFERENCES beteg(beteg_id),
	CONSTRAINT beteg_kapcs_kapcs_tip_id_fkey FOREIGN KEY (kapcs_tip_id) REFERENCES kapcs_tip(kapcs_tip_id),
	CONSTRAINT beteg_kapcs_kapcs_min_id_fkey FOREIGN KEY (kapcs_min_id) REFERENCES kapcs_min(kapcs_min_id)
);

CREATE OR REPLACE FUNCTION after_death_funct()
 RETURNS trigger
 LANGUAGE plpgsql
AS '
DECLARE 
beteg_id INTEGER := NEW.beteg_id;
uj_hal_ido DATE := NEW.hal_ido;
BEGIN 
UPDATE beteg_kapcs
SET kapcs_veg = uj_hal_ido
WHERE   beteg_kapcs.beteg1_id = beteg_id  
OR beteg_kapcs.beteg2_id = beteg_id 
AND beteg_kapcs.kapcs_veg > uj_hal_ido;
RETURN NULL;
END;
'
;

CREATE OR REPLACE FUNCTION before_birth_funct()
 RETURNS trigger
 LANGUAGE plpgsql
AS '
DECLARE 
beteg1_id INTEGER := NEW.beteg1_id;
beteg2_id INTEGER := NEW.beteg2_id;
kesobbi_szul_ido DATE :=
(
SELECT  MAX(szul_ido)
FROM beteg
WHERE beteg_id = beteg1_id OR beteg_id = beteg2_id
);
BEGIN 
IF NEW.kapcs_kezd < kesobbi_szul_ido THEN
NEW.kapcs_kezd := kesobbi_szul_ido;
END IF;
RETURN NEW;
END;
'
;

INSERT INTO korhaz.kapcs_tip
VALUES 
(1, 'hazastars'),
(2, 'kollega'),
(3, 'szomszed'),
(4, 'testver');

INSERT INTO korhaz.kapcs_min
VALUES
(1, 'pozitiv'),
(2, 'semleges'),
(3, 'negativ');

INSERT INTO korhaz.telep
VALUES
(1, 'budapest'),
(2, 'pecs'),
(3, 'debrecen');

INSERT INTO korhaz.nem
VALUES
(1, 'ferfi'),
(2, 'no'),
(3, 'egyeb');

INSERT INTO korhaz.beteg
VALUES 
(1, 1, '111 piros utca 1', 'jozsi', 'kovacs', '2001-01-01', 1, 1,  'jozsianyjaneve',  '2010-10-10', 123, 'jozsi@mail.com'),
(2, 2, '222 zold utca 2','julcsi', 'nagy', '2002-02-02', 2, 2,  'julcsianyjaneve',  '2011-11-11', 456,'julcsi@mail.com'),
(3, 3, '333 kek utca 3', 'pisti', 'kis', '2003-03-03', 3, 3,  'pistianyjaneve',  '2012-12-12', 789, 'pisti@mail.com');

INSERT INTO korhaz.beteg_kapcs
VALUES (1, 2, 1, 3, 7, '2000-10-10', '2001-11-11');



