CREATE TABLE `hospitalsystem`.`medical_examination` (
  `idmedical_examination` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `eventdate` DATE NOT NULL,
  `appointmentdate` DATE NOT NULL,
  `eventtime` TIME NOT NULL,
  `idemployee` INT NOT NULL,
  `status` TINYINT NOT NULL,
  `idoutpatietcard` INT NOT NULL,
  PRIMARY KEY (`idmedical_examination`),
  INDEX `FK2idemployee_idx` (`idemployee` ASC) VISIBLE,
  INDEX `FK2idoutpatietcard_idx` (`idoutpatietcard` ASC) VISIBLE,
  CONSTRAINT `FK2idemployee`
    FOREIGN KEY (`idemployee`)
    REFERENCES `hospitalsystem`.`employee` (`idemployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK2idoutpatietcard`
    FOREIGN KEY (`idoutpatietcard`)
    REFERENCES `hospitalsystem`.`outpatientcard` (`idoutpatientcard`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);