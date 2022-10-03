DROP TABLE agendamento;
DROP TABLE pet;
DROP TABLE tutor;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `db_clinicawszd` DEFAULT CHARACTER SET utf8 ;
USE `db_clinicawszd` ;

-- -----------------------------------------------------
-- Table `db_clinicawszd`.`tutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_clinicawszd`.`tutor` (
  `id_tutor` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `cpf` VARCHAR(15) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `endereco` VARCHAR(150) NULL,
  PRIMARY KEY (`id_tutor`),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE,
  UNIQUE INDEX `telefone_UNIQUE` (`telefone` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_clinicawszd`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_clinicawszd`.`pet` (
  `id_pet` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NOT NULL,
  `idade` INT NULL,
  `porte` VARCHAR(1) NOT NULL,
  `tipo` VARCHAR(20) NOT NULL,
  `id_tutor` INT NOT NULL,
  PRIMARY KEY (`id_pet`),
  INDEX `fk_pet_tutor_idx` (`id_tutor` ASC) VISIBLE,
  CONSTRAINT `fk_pet_tutor`
    FOREIGN KEY (`id_tutor`)
    REFERENCES `db_clinicawszd`.`tutor` (`id_tutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_clinicawszd`.`agendamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_clinicawszd`.`agendamento` (
  `id_agendamento` INT NOT NULL AUTO_INCREMENT,
  `id_pet` INT NOT NULL,
  `dt_criacao` DATETIME NOT NULL,
  `dt_agendamento` DATETIME NOT NULL,
  `procedimento` VARCHAR(15) NOT NULL,
  `ag_status` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_agendamento`),
  INDEX `fk_agendamento_pet1_idx` (`id_pet` ASC) VISIBLE,
  CONSTRAINT `fk_agendamento_pet1`
    FOREIGN KEY (`id_pet`)
    REFERENCES `db_clinicawszd`.`pet` (`id_pet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- Valores dos Tutores para teste
INSERT INTO tutor (nome, cpf, telefone, email, endereco)
    VALUES
	("MANU LORENA FARIAS", "96998572899", "18704294270","NICOLELFIAS@GMAIL.COM" ,"68908515 AV DEODORO FONSECA 777 SAO LAZARO MACAPA AP"),
    ("JESSICA COSTA", "82988417478", "04006552769","JESSICACOSTA@COSTAJ.COM" ,"57052140 RUA MINISTRO SALGADO 739 PITANGUINHA MACEIO AL"),
    ("JOAQUIM MOREIRA", "98989006545", "48543475384", "JOAQUIMMOREIRA@MOREIRA.COM","65058369 RUA SAO FRANCISCO 802 VILA RIOD SAO LUIS MA"),
    ("BENTO LIMA", "68988839096", "48390764482","BENTOLIMA@LIMAB.COM" ,"69918703 RUA NELSON MESQUITA 216 SANTA QUITERIA RIO BRANCO AC");

-- Valores dos Pets para teste
INSERT INTO pet (nome, idade, porte, tipo, id_tutor)
    VALUES
	("BOLINHA", 3, "P", "CACHORRO", 3),
    ("HANIBAL", 1, "G", "CACHORRO", 3),
    ("MIAU", 1, "P", "GATO", 2),
    ("SWARZA", 3, "P", "GATO", 2),
    ("REPETE", 5, "P", "PAPAGAIO", 1),
    ("LARGATIXA", 1, "P", "IGUANA", 4);

-- Valores dos Agendamentospettutor para teste
INSERT INTO agendamento (id_pet, dt_criacao, dt_agendamento, procedimento, ag_status)
    VALUES
	(4 , '2021-12-10 13:00:13', '2021-12-20 13:00:13', "CASTRACAO", "AGENDADO"),
	(2 , '2021-12-10 13:00:13', '2021-12-21 14:00:13' , "CIRURGIA",  "FALTOU"),
	(1 , '2021-11-20 13:00:13', '2021-11-29 14:00:13' , "CASTRACAO", "FINALIZADO"),
	(6 , '2021-11-20 13:00:13', '2021-11-30 08:00:13' , "CIRURGIA", "CANCELADO");