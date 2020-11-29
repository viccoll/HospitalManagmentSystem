CREATE TABLE `hospitalsystem`.`address` (
  `idaddress` INT NOT NULL AUTO_INCREMENT,
  `idstreet` INT NOT NULL,
  `flatnumber` INT NOT NULL,
  `housenumber` INT NOT NULL,
  `corpus` INT NOT NULL,
  PRIMARY KEY (`idaddress`),
  INDEX `idstreet_idx` (`idstreet` ASC) VISIBLE,
  CONSTRAINT `idstreet`
    FOREIGN KEY (`idstreet`)
    REFERENCES `hospitalsystem`.`street` (`idstreet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
