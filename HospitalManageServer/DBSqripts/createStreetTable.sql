CREATE TABLE `hospitalsystem`.`street` (
  `idstreet` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `idregion` INT NOT NULL,
  PRIMARY KEY (`idstreet`),
  INDEX `idregion_idx` (`idregion` ASC) INVISIBLE,
  CONSTRAINT `idregion`
    FOREIGN KEY (`idregion`)
    REFERENCES `hospitalsystem`.`region` (`idregion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
