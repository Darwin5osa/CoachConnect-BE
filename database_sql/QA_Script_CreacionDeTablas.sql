-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema CoachConnect_qa
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `CoachConnect_qa` ;

-- -----------------------------------------------------
-- Schema CoachConnect_qa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CoachConnect_qa` DEFAULT CHARACTER SET utf8 ;
USE `CoachConnect_qa` ;

-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Usuario` (
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
-- Table `CoachConnect_qa`.`Estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Estudiante` (
  `idEstudiante` INT NOT NULL AUTO_INCREMENT,
  `NivelEducativo` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idEstudiante`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Estudiante_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect_qa`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Estudiante_Usuario1_idx` ON `CoachConnect_qa`.`Estudiante` (`Usuario_idUsuario` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Profesion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Profesion` (
  `idProfesion` INT NOT NULL AUTO_INCREMENT,
  `NombreProfesion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProfesion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Tutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Tutor` (
  `idTutor` INT NOT NULL AUTO_INCREMENT,
  `Descripcion` VARCHAR(200) NOT NULL,
  `Calificacion` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Profesion_idProfesion` INT NOT NULL,
  PRIMARY KEY (`idTutor`, `Usuario_idUsuario`, `Profesion_idProfesion`),
  CONSTRAINT `fk_Tutor_Usuario`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect_qa`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutor_Profesion1`
    FOREIGN KEY (`Profesion_idProfesion`)
    REFERENCES `CoachConnect_qa`.`Profesion` (`idProfesion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutor_Usuario_idx` ON `CoachConnect_qa`.`Tutor` (`Usuario_idUsuario` ASC) VISIBLE;

CREATE INDEX `fk_Tutor_Profesion1_idx` ON `CoachConnect_qa`.`Tutor` (`Profesion_idProfesion` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Admin` (
  `idAdmin` INT NOT NULL AUTO_INCREMENT,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idAdmin`, `Usuario_idUsuario`),
  CONSTRAINT `fk_Admin_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `CoachConnect_qa`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Admin_Usuario1_idx` ON `CoachConnect_qa`.`Admin` (`Usuario_idUsuario` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Detalle_Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Detalle_Reserva` (
  `idDetalle_Reserva` INT NOT NULL AUTO_INCREMENT,
  `FechaInicioReserva` DATETIME NOT NULL,
  `FechaFinReserva` DATETIME NOT NULL,
  `HorasReservadas` INT NOT NULL,
  `Estudiante_idEstudiante` INT NOT NULL,
  `Tutor_idTutor` INT NOT NULL,
  PRIMARY KEY (`idDetalle_Reserva`, `Estudiante_idEstudiante`, `Tutor_idTutor`),
  CONSTRAINT `fk_Detalle_Reserva_Estudiante1`
    FOREIGN KEY (`Estudiante_idEstudiante`)
    REFERENCES `CoachConnect_qa`.`Estudiante` (`idEstudiante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Detalle_Reserva_Tutor1`
    FOREIGN KEY (`Tutor_idTutor`)
    REFERENCES `CoachConnect_qa`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Detalle_Reserva_Estudiante1_idx` ON `CoachConnect_qa`.`Detalle_Reserva` (`Estudiante_idEstudiante` ASC) VISIBLE;

CREATE INDEX `fk_Detalle_Reserva_Tutor1_idx` ON `CoachConnect_qa`.`Detalle_Reserva` (`Tutor_idTutor` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`NivelEducativo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`NivelEducativo` (
  `idNivelEducativo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idNivelEducativo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Categoria` (
  `idCategoria` INT NOT NULL,
  `NombreCategoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Tutoria` (
  `idTutoria` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(200) NOT NULL,
  `NivelEducativo_idNivelEducativo` INT NOT NULL,
  `Categoria_idCategoria` INT NOT NULL,
  PRIMARY KEY (`idTutoria`, `NivelEducativo_idNivelEducativo`, `Categoria_idCategoria`),
  CONSTRAINT `fk_Tutoria_Nivel1`
    FOREIGN KEY (`NivelEducativo_idNivelEducativo`)
    REFERENCES `CoachConnect_qa`.`NivelEducativo` (`idNivelEducativo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutoria_Categoria1`
    FOREIGN KEY (`Categoria_idCategoria`)
    REFERENCES `CoachConnect_qa`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutoria_Nivel1_idx` ON `CoachConnect_qa`.`Tutoria` (`NivelEducativo_idNivelEducativo` ASC) VISIBLE;

CREATE INDEX `fk_Tutoria_Categoria1_idx` ON `CoachConnect_qa`.`Tutoria` (`Categoria_idCategoria` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Tutor_Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Tutor_Tutoria` (
  `Tutor_idTutor` INT NOT NULL,
  `Tutoria_idTutoria` INT NOT NULL,
  PRIMARY KEY (`Tutor_idTutor`, `Tutoria_idTutoria`),
  CONSTRAINT `fk_Tutor_has_Tutoria_Tutor1`
    FOREIGN KEY (`Tutor_idTutor`)
    REFERENCES `CoachConnect_qa`.`Tutor` (`idTutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tutor_has_Tutoria_Tutoria1`
    FOREIGN KEY (`Tutoria_idTutoria`)
    REFERENCES `CoachConnect_qa`.`Tutoria` (`idTutoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Tutor_has_Tutoria_Tutoria1_idx` ON `CoachConnect_qa`.`Tutor_Tutoria` (`Tutoria_idTutoria` ASC) VISIBLE;

CREATE INDEX `fk_Tutor_has_Tutoria_Tutor1_idx` ON `CoachConnect_qa`.`Tutor_Tutoria` (`Tutor_idTutor` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Caracteristicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Caracteristicas` (
  `idCaracteristicas` INT NOT NULL AUTO_INCREMENT,
  `NombreCaracteristica` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCaracteristicas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CoachConnect_qa`.`Caracteristicas_Tutoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CoachConnect_qa`.`Caracteristicas_Tutoria` (
  `Caracteristicas_idCaracteristicas` INT NOT NULL,
  `Tutoria_idTutoria` INT NOT NULL,
  PRIMARY KEY (`Caracteristicas_idCaracteristicas`, `Tutoria_idTutoria`),
  CONSTRAINT `fk_Caracteristicas_has_Tutoria_Caracteristicas1`
    FOREIGN KEY (`Caracteristicas_idCaracteristicas`)
    REFERENCES `CoachConnect_qa`.`Caracteristicas` (`idCaracteristicas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Caracteristicas_has_Tutoria_Tutoria1`
    FOREIGN KEY (`Tutoria_idTutoria`)
    REFERENCES `CoachConnect_qa`.`Tutoria` (`idTutoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Caracteristicas_has_Tutoria_Tutoria1_idx` ON `CoachConnect_qa`.`Caracteristicas_Tutoria` (`Tutoria_idTutoria` ASC) VISIBLE;

CREATE INDEX `fk_Caracteristicas_has_Tutoria_Caracteristicas1_idx` ON `CoachConnect_qa`.`Caracteristicas_Tutoria` (`Caracteristicas_idCaracteristicas` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
