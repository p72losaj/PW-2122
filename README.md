# PW-2122
Repositorio para la practica de PW del año 21-22

#SENTENCIAS SQL
###########################################################################################################################
# SENTENCIAS SQL TABLA USUARIO

# 1- CREAR TABLA USUARIO (HECHO)

CREATE TABLE `USUARIO`(
`CORREO` VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`NOMBRE` VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL,
`APELLIDO1` VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL,
`APELLIDO2` VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL,
`NICK` VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL,
`ROL` ENUM( 'administrador', 'espectador' ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL,
PRIMARY KEY (`CORREO`)
) TYPE = MYISAM CHARACTER SET utf8 COLLATE utf8_spanish_ci COMMENT = 'TABLA DE ATRIBUTOS DE UN USUARIO';

##############################################################################################################################
# SENTENCIAS SQL TABLA CRITICA

# 1. CREAR TABLA CRITICA (HECHO)

CREATE TABLE `CRITICA` (
`TITULO` VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`RESENA` TEXT CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL ,
PRIMARY KEY ( `TITULO` ) ,
FULLTEXT (
`RESENA`
)
) TYPE = MYISAM CHARACTER SET utf8 COLLATE utf8_spanish_ci COMMENT = 'TABLA DE ATRIBUTOS DE UNA CRITICA'

###############################################################################################################################

# SENTENCIAS SQL TABLA USUARIO-CRITICA

# 1. CREAR TABLA USUARIO-CRITICA (HECHO)

CREATE TABLE USUARIO_CRITICA(
CORREO VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
TITULO VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
VALORACION_UTILIDAD TEXT CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL ,
PRIMARY KEY (CORREO, TITULO),
FOREIGN KEY ( CORREO ) REFERENCES USUARIO( CORREO ) ,
FOREIGN KEY ( TITULO ) REFERENCES CRITICA( TITULO ) ,
FULLTEXT (
VALORACION_CRITICA
)
) TYPE = MYISAM COMMENT = 'TABLA RELACION USUARIO Y CRITICA';

###################################################################################################################################

# SENTENCIAS SQL TABLA ESPECTACULO

# 1. CREAR TABLA ESPECTACULO (HECHO)

CREATE TABLE `ESPECTACULO` (
`TITULO` VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`DESCRIPCION` TEXT CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL ,
`CATEGORIA` ENUM ('CONCIERTO', 'MONOLOGO', 'OBRA TEATRO') CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL,
`LOCALIDADES` INT NULL DEFAULT '0',
`VENTAS` INT NULL DEFAULT '0',
PRIMARY KEY ( `TITULO` ) ,
FULLTEXT (
`DESCRIPCION`
)
) TYPE = MYISAM CHARACTER SET utf8 COLLATE utf8_spanish_ci COMMENT = 'TABLA CON LOS ATRIBUTOS COMUNES DE LOS DISTINTOS TIPOS DE ESPECTACULOS';

##############################################################################################################################################

# SENTENCIAS SQL TABLA ESPECTACULO-PUNTUAL

# 1. CREAR TABLA ESPECTACULO-PUNTUAL

CREATE TABLE `ESPECTACULO_PUNTUAL` (
`TITULO` VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`TIPO` ENUM( 'PUNTUAL' ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL DEFAULT 'PUNTUAL',
`DIA` INT NULL ,
`MES` INT NULL ,
`ANO` INT NULL ,
`HORA` INT NULL ,
`MINUTOS` INT NULL ,
PRIMARY KEY ( `TITULO` ) ,
FOREIGN KEY ( TITULO ) REFERENCES ESPECTACULO( TITULO )
) TYPE = MYISAM CHARACTER SET utf8 COLLATE utf8_spanish_ci COMMENT = 'TABLA CON LOS ATRIBUTOS DE UN ESPECTACULO PUNTUAL';

##################################################################################################################################################################

# OPERACIONES DE LA TABLA ESPECTACULO-MULTIPLE

# 1. CREACION TABLA ESPECTACULO-MULTIPLE

CREATE TABLE `ESPECTACULO_MULTIPLE` (
`TITULO` VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`TIPO` ENUM('MULTIPLE') CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL DEFAULT 'MULTIPLE',
`DIA_SEMANA1` ENUM( 'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO' ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL ,
`HORA1` INT NULL,
`MINUTOS1` INT NULL,
`DIA_SEMANA2` ENUM( 'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO' ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL ,
`HORA2` INT NULL,
`MINUTOS2` INT NULL ,
PRIMARY KEY ( `TITULO` ) ,
FOREIGN KEY (TITULO) REFERENCES ESPECTACULO(TITULO)
) TYPE = MYISAM CHARACTER SET utf8 COLLATE utf8_spanish_ci COMMENT = 'TABLA CON LOS ATRIBUTOS DE UN ESPECTACULO MULTIPLE';

##################################################################################################################################################################

# OPERACIONES DE LA TABLA ESPECTACULO DE TEMPORADA

# 1. CREACION DE LA TABLA ESPECTACULO DE TEMPORADA

CREAR-TABLA-ESPECTACULO-TEMPORADA: 

CREATE TABLE `ESPECTACULO_TEMPORADA` (
`TITULO` VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`TIPO` ENUM('TEMPORADA' ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL DEFAULT 'TEMPORADA',
`DIA_SEMANA` ENUM( 'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO' ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NULL ,
`HORA_INICIO` INT NULL ,
`MINUTOS_INICIO` INT NULL ,
`HORA_FIN` INT NULL ,
`MINUTOS_FIN` INT NULL ,
PRIMARY KEY ( `TITULO` ) ,
FOREIGN KEY (TITULO) REFERENCES ESPECTACULO(TITULO)
) TYPE = MYISAM CHARACTER SET utf8 COLLATE utf8_spanish_ci COMMENT = 'TABLA CON LOS ATRIBUTOS DE UN ESPECTACULO DE TEMPORADA';

##################################################################################################################################################################

# OPERACIONES DE LA TABLA RELACION ESPECTACULO-CRITICA

CREAR-TABLA-RELACION-ESPECTACULO-CRITICA: 

CREATE TABLE `ESPECTACULO_CRITICA` (
`TITULO_CRITICA` VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`TITULO_ESPECTACULO` VARCHAR( 64 ) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL ,
`PUNTUACION` INT DEFAULT '0' ,
PRIMARY KEY ( `TITULO_ESPECTACULO`, `TITULO_CRITICA` ) ,
FOREIGN KEY ('TITULO_ESPECTACULO') REFERENCES ESPECTACULO ('TITULO'),
FOREIGN KEY ('TITULO_CRITICA) REFERENCES  CRITICA ('TITULO'),
) TYPE = MYISAM CHARACTER SET utf8 COLLATE utf8_spanish_ci COMMENT = 'TABLA RELACION CRITICA Y ESPECTACULO';




