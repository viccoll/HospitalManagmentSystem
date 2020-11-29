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