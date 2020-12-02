CREATE TABLE `hospitalsystem`.`appointment` (
  `idappointment` INT NOT NULL AUTO_INCREMENT,
  `idemployee` INT NOT NULL,
  `appointmentdate` DATE NOT NULL,
  `appointmenttime` TIME NOT NULL,
  `epicrisis` LONGTEXT NULL,
  `status` TINYINT NOT NULL,
  `idoutpatientcard` INT NOT NULL,
  PRIMARY KEY (`idappointment`),
  INDEX `idemployee_idx` (`idemployee` ASC) VISIBLE,
  INDEX `idoutpatientcard_idx` (`idoutpatientcard` ASC) VISIBLE,
  CONSTRAINT `idemployee`
    FOREIGN KEY (`idemployee`)
    REFERENCES `hospitalsystem`.`employee` (`idemployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idoutpatientcard`
    FOREIGN KEY (`idoutpatientcard`)
    REFERENCES `hospitalsystem`.`outpatientcard` (`idoutpatientcard`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
ALTER TABLE `hospitalsystem`.`appointment`
DROP FOREIGN KEY `idoutpatientcard`;
ALTER TABLE `hospitalsystem`.`appointment`
    ADD COLUMN `idAppType` INT NOT NULL AFTER `idpatient`,
    CHANGE COLUMN `idoutpatientcard` `idpatient` INT NOT NULL ,
    ADD INDEX `idoutpatientcard_idx` (`idpatient` ASC) VISIBLE,
    ADD INDEX `idAppType_idx` (`idAppType` ASC) VISIBLE,
DROP INDEX `idoutpatientcard_idx` ;
;
ALTER TABLE `hospitalsystem`.`appointment`
    ADD CONSTRAINT `idoutpatientcard`
    FOREIGN KEY (`idpatient`)
    REFERENCES `hospitalsystem`.`patient` (`idpatient`),
    ADD CONSTRAINT `idAppType`
    FOREIGN KEY (`idAppType`)
    REFERENCES `hospitalsystem`.`appointmenttype` (`idappointmentType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;