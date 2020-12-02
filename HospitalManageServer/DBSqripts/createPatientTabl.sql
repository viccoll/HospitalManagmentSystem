CREATE TABLE `hospitalsystem`.`patient` (
                                            `idpatient` INT NOT NULL AUTO_INCREMENT,
                                            `name` VARCHAR(45) NOT NULL,
    `surname` VARCHAR(45) NOT NULL,
    `patronymic` VARCHAR(45) NOT NULL,
    `birthdayDate` DATE NOT NULL,
    `idaddress` INT NOT NULL,
    `phoneNumber` VARCHAR(13) NOT NULL,
    `gender` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`idpatient`),
    INDEX `idaddress_idx` (`idaddress` ASC) VISIBLE,
    CONSTRAINT `FKidaddress`
    FOREIGN KEY (`idaddress`)
    REFERENCES `hospitalsystem`.`address` (`idaddress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
