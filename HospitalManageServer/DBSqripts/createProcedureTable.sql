CREATE TABLE `hospitalsystem`.`procedure` (
  `idprocedure` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `eventdate` DATE NOT NULL,
  `appointmentdate` DATE NOT NULL,
  `eventtime` TIME NOT NULL,
  `idemployee` INT NOT NULL,
  `status` TINYINT NOT NULL,
  `idoutpatientcard` INT NOT NULL,
  PRIMARY KEY (`idprocedure`),
  INDEX `idemployee_idx` (`idemployee` ASC) VISIBLE,
  INDEX `FKidoutpatientcard_idx` (`idoutpatientcard` ASC) VISIBLE,
  CONSTRAINT `FK1idemployee`
    FOREIGN KEY (`idemployee`)
    REFERENCES `hospitalsystem`.`employee` (`idemployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK1idoutpatientcard`
    FOREIGN KEY (`idoutpatientcard`)
    REFERENCES `hospitalsystem`.`outpatientcard` (`idoutpatientcard`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);