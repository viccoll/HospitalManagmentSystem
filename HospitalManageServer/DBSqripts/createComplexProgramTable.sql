CREATE TABLE `hospitalsystem`.`complex_program` (
  `idcomplex_program` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `eventdate` DATE NOT NULL,
  `eventtime` TIME NOT NULL,
  `appointmentdate` DATE NOT NULL,
  `idemployee` INT NOT NULL,
  `status` TINYINT NOT NULL,
  `idoutpatientcard` INT NOT NULL,
  PRIMARY KEY (`idcomplex_program`),
  INDEX `FK3idemployee_idx` (`idemployee` ASC) VISIBLE,
  INDEX `FK3idoutpatientcard_idx` (`idoutpatientcard` ASC) VISIBLE,
  CONSTRAINT `FK3idemployee`
    FOREIGN KEY (`idemployee`)
    REFERENCES `hospitalsystem`.`employee` (`idemployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK3idoutpatientcard`
    FOREIGN KEY (`idoutpatientcard`)
    REFERENCES `hospitalsystem`.`outpatientcard` (`idoutpatientcard`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);