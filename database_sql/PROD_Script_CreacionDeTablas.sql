-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CoachConnect_prod
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `CoachConnect_prod` ;

-- -----------------------------------------------------
-- Schema CoachConnect_prod
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CoachConnect_prod` DEFAULT CHARACTER SET utf8 ;
USE `CoachConnect_prod` ;

-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Edad` INT NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `ContactoCelular` VARCHAR(45) NOT NULL,
  `Foto` VARCHAR(200) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Rol` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Estudiante` (
  `idEstudiante` INT NOT NULL AUTO_INCREMENT,
  `NivelEducativo` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idEstudiante`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Estudiante_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect_prod`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Estudiante_Usuario1_idx` ON `CoachConnect_prod`.`Estudiante` (`Usuario_idUsuario` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Profesion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Profesion` (
  `idProfesion` INT NOT NULL AUTO_INCREMENT,
  `NombreProfesion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProfesion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Tutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Tutor` (
  `idTutor` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(200) NOT NULL,
  `Calificacion` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Profesion_idProfesion` INT NOT NULL,
  PRIMARY KEY (`idTutor`, `Usuario_idUsuario`, `Profesion_idProfesion`),
  CONSTRAINT `fk_Tutor_Usuario`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect_prod`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutor_Profesion1`
    FOREIGN KEY (`Profesion_idProfesion`)
    REFERENCES `CoachConnect_prod`.`Profesion` (`idProfesion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutor_Usuario_idx` ON `CoachConnect_prod`.`Tutor` (`Usuario_idUsuario` ASC) VISIBLE;

CREATE INDEX `fk_Tutor_Profesion1_idx` ON `CoachConnect_prod`.`Tutor` (`Profesion_idProfesion` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Admin` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idAdmin`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Admin_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect_prod`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Admin_Usuario1_idx` ON `CoachConnect_prod`.`Admin` (`Usuario_idUsuario` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Detalle_Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Detalle_Reserva` (
  `idDetalle_Reserva` INT NOT NULL AUTO_INCREMENT,
  `FechaInicioReserva` DATETIME NOT NULL,
  `FechaFinReserva` DATETIME NOT NULL,
  `HorasReservadas` INT NOT NULL,
  `Estudiante_idEstudiante` INT NOT NULL,
  `Tutor_idTutor` INT NOT NULL,
  PRIMARY KEY (`idDetalle_Reserva`, `Estudiante_idEstudiante`, `Tutor_idTutor`),
  CONSTRAINT `fk_Detalle_Reserva_Estudiante1`
    FOREIGN KEY (`Estudiante_idEstudiante`)
    REFERENCES `CoachConnect_prod`.`Estudiante` (`idEstudiante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Detalle_Reserva_Tutor1`
    FOREIGN KEY (`Tutor_idTutor`)
    REFERENCES `CoachConnect_prod`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Detalle_Reserva_Estudiante1_idx` ON `CoachConnect_prod`.`Detalle_Reserva` (`Estudiante_idEstudiante` ASC) VISIBLE;

CREATE INDEX `fk_Detalle_Reserva_Tutor1_idx` ON `CoachConnect_prod`.`Detalle_Reserva` (`Tutor_idTutor` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`NivelEducativo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`NivelEducativo` (
  `idNivelEducativo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idNivelEducativo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Categoria` (
  `idCategoria` INT NOT NULL,
  `NombreCategoria` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(200) NOT NULL,
  `Foto` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Tutoria` (
  `idTutoria` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(200) NOT NULL,
  `NivelEducativo_idNivelEducativo` INT NOT NULL,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idTutoria`, `NivelEducativo_idNivelEducativo`, `Categoria_idCategoria`),
  CONSTRAINT `fk_Tutoria_Nivel1`
    FOREIGN KEY (`NivelEducativo_idNivelEducativo`)
    REFERENCES `CoachConnect_prod`.`NivelEducativo` (`idNivelEducativo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutoria_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `CoachConnect_prod`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutoria_Nivel1_idx` ON `CoachConnect_prod`.`Tutoria` (`NivelEducativo_idNivelEducativo` ASC) VISIBLE;

CREATE INDEX `fk_Tutoria_Categoria1_idx` ON `CoachConnect_prod`.`Tutoria` (`Categoria_idCategoria` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Tutor_Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Tutor_Tutoria` (
  `Tutor_idTutor` INT NOT NULL,
  `Tutoria_idTutoria` INT NOT NULL,
  PRIMARY KEY (`Tutor_idTutor`, `Tutoria_idTutoria`),
  CONSTRAINT `fk_Tutor_has_Tutoria_Tutor1`
    FOREIGN KEY (`Tutor_idTutor`)
    REFERENCES `CoachConnect_prod`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutor_has_Tutoria_Tutoria1`
    FOREIGN KEY (`Tutoria_idTutoria`)
    REFERENCES `CoachConnect_prod`.`Tutoria` (`idTutoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutor_has_Tutoria_Tutoria1_idx` ON `CoachConnect_prod`.`Tutor_Tutoria` (`Tutoria_idTutoria` ASC) VISIBLE;

CREATE INDEX `fk_Tutor_has_Tutoria_Tutor1_idx` ON `CoachConnect_prod`.`Tutor_Tutoria` (`Tutor_idTutor` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Caracteristicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Caracteristicas` (
  `idCaracteristicas` INT NOT NULL AUTO_INCREMENT,
  `NombreCaracteristica` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCaracteristicas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_prod`.`Caracteristicas_Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_prod`.`Caracteristicas_Tutoria` (
  `Caracteristicas_idCaracteristicas` INT NOT NULL,
  `Tutoria_idTutoria` INT NOT NULL,
  PRIMARY KEY (`Caracteristicas_idCaracteristicas`, `Tutoria_idTutoria`),
  CONSTRAINT `fk_Caracteristicas_has_Tutoria_Caracteristicas1`
    FOREIGN KEY (`Caracteristicas_idCaracteristicas`)
    REFERENCES `CoachConnect_prod`.`Caracteristicas` (`idCaracteristicas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Caracteristicas_has_Tutoria_Tutoria1`
    FOREIGN KEY (`Tutoria_idTutoria`)
    REFERENCES `CoachConnect_prod`.`Tutoria` (`idTutoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Caracteristicas_has_Tutoria_Tutoria1_idx` ON `CoachConnect_prod`.`Caracteristicas_Tutoria` (`Tutoria_idTutoria` ASC) VISIBLE;

CREATE INDEX `fk_Caracteristicas_has_Tutoria_Caracteristicas1_idx` ON `CoachConnect_prod`.`Caracteristicas_Tutoria` (`Caracteristicas_idCaracteristicas` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
