CREATE TABLE `hospitalsystem`.`personal_account` (
  `idpersonal_account` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(30) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`idpersonal_account`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);