-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CoachConnect
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `CoachConnect` ;

-- -----------------------------------------------------
-- Schema CoachConnect
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CoachConnect` DEFAULT CHARACTER SET utf8 ;
USE `CoachConnect` ;

-- -----------------------------------------------------
-- Table `CoachConnect`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellido` VARCHAR(45) NOT NULL,
  `Edad` INT NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `ContactoCelular` VARCHAR(45) NOT NULL,
  `Foto` VARCHAR(200) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect`.`Estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Estudiante` (
  `idEstudiante` INT NOT NULL AUTO_INCREMENT,
  `NivelEducativo` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idEstudiante`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Estudiante_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Estudiante_Usuario1_idx` ON `CoachConnect`.`Estudiante` (`Usuario_idUsuario` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect`.`Tutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Tutor` (
  `idTutor` INT NOT NULL AUTO_INCREMENT,
  `Profesion` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(200) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idTutor`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Tutor_Usuario`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutor_Usuario_idx` ON `CoachConnect`.`Tutor` (`Usuario_idUsuario` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Admin` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idAdmin`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Admin_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Admin_Usuario1_idx` ON `CoachConnect`.`Admin` (`Usuario_idUsuario` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect`.`Detalle_Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Detalle_Reserva` (
  `idDetalle_Reserva` INT NOT NULL AUTO_INCREMENT,
  `FechaInicioReserva` DATETIME NOT NULL,
  `FechaFinReserva` DATETIME NOT NULL,
  `HorasReservadas` INT NOT NULL,
  `Estudiante_idEstudiante` INT NOT NULL,
  `Tutor_idTutor` INT NOT NULL,
  PRIMARY KEY (`idDetalle_Reserva`, `Estudiante_idEstudiante`, `Tutor_idTutor`),
  CONSTRAINT `fk_Detalle_Reserva_Estudiante1`
    FOREIGN KEY (`Estudiante_idEstudiante`)
    REFERENCES `CoachConnect`.`Estudiante` (`idEstudiante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Detalle_Reserva_Tutor1`
    FOREIGN KEY (`Tutor_idTutor`)
    REFERENCES `CoachConnect`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Detalle_Reserva_Estudiante1_idx` ON `CoachConnect`.`Detalle_Reserva` (`Estudiante_idEstudiante` ASC) VISIBLE;

CREATE INDEX `fk_Detalle_Reserva_Tutor1_idx` ON `CoachConnect`.`Detalle_Reserva` (`Tutor_idTutor` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect`.`Nivel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Nivel` (
  `idNivel` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idNivel`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect`.`Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Tutoria` (
  `idTutoria` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(200) NOT NULL,
  `Nivel_idNivel` INT NOT NULL,
  PRIMARY KEY (`idTutoria`, `Nivel_idNivel`),
  CONSTRAINT `fk_Tutoria_Nivel1`
    FOREIGN KEY (`Nivel_idNivel`)
    REFERENCES `CoachConnect`.`Nivel` (`idNivel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutoria_Nivel1_idx` ON `CoachConnect`.`Tutoria` (`Nivel_idNivel` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect`.`Tutor_Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect`.`Tutor_Tutoria` (
  `Tutor_idTutor` INT NOT NULL,
  `Tutoria_idTutoria` INT NOT NULL,
  PRIMARY KEY (`Tutor_idTutor`, `Tutoria_idTutoria`),
  CONSTRAINT `fk_Tutor_has_Tutoria_Tutor1`
    FOREIGN KEY (`Tutor_idTutor`)
    REFERENCES `CoachConnect`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutor_has_Tutoria_Tutoria1`
    FOREIGN KEY (`Tutoria_idTutoria`)
    REFERENCES `CoachConnect`.`Tutoria` (`idTutoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutor_has_Tutoria_Tutoria1_idx` ON `CoachConnect`.`Tutor_Tutoria` (`Tutoria_idTutoria` ASC) VISIBLE;

CREATE INDEX `fk_Tutor_has_Tutoria_Tutor1_idx` ON `CoachConnect`.`Tutor_Tutoria` (`Tutor_idTutor` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
