SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mydbproject
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydbproject`;

-- -----------------------------------------------------
-- Schema mydbproject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydbproject` DEFAULT CHARACTER SET utf8mb3;
USE `mydbproject`;

-- -----------------------------------------------------
-- Table `mydbproject`.`brands`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`brands`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`brands`
(
    `id`         INT          NOT NULL,
    `brand_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydbproject`.`car_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`car_status`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`car_status`
(
    `id`              INT          NOT NULL,
    `car_status_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydbproject`.`quality_class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`quality_class`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`quality_class`
(
    `id`                 INT          NOT NULL,
    `quality_class_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydbproject`.`cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`cars`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`cars`
(
    `c_id`             INT           NOT NULL AUTO_INCREMENT,
    `model_name`       VARCHAR(45)   NOT NULL,
    `price`            DECIMAL(8, 2) NOT NULL,
    `brand_id`         INT           NOT NULL,
    `quality_class_id` INT           NOT NULL,
    `car_status_id`    INT           NOT NULL DEFAULT '1',
    `description`      VARCHAR(500)  NULL     DEFAULT NULL,
    PRIMARY KEY (`c_id`),
    INDEX `fk_car_brand1_idx` (`brand_id` ASC) VISIBLE,
    INDEX `fk_car_quality_class1_idx` (`quality_class_id` ASC) VISIBLE,
    INDEX `fk_car_car_status1_idx` (`car_status_id` ASC) VISIBLE,
    CONSTRAINT `fk_car_brand1`
        FOREIGN KEY (`brand_id`)
            REFERENCES `mydbproject`.`brands` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_car_car_status1`
        FOREIGN KEY (`car_status_id`)
            REFERENCES `mydbproject`.`car_status` (`id`),
    CONSTRAINT `fk_car_quality_class1`
        FOREIGN KEY (`quality_class_id`)
            REFERENCES `mydbproject`.`quality_class` (`id`)
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydbproject`.`booking_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`booking_status`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`booking_status`
(
    `id`                  INT         NOT NULL,
    `booking_status_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydbproject`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`roles`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`roles`
(
    `id`        INT         NOT NULL,
    `role_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydbproject`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`users`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`users`
(
    `u_id`       INT          NOT NULL AUTO_INCREMENT,
    `password`   VARCHAR(32)  NOT NULL,
    `email`      VARCHAR(255) NOT NULL,
    `firstname`  VARCHAR(45)  NOT NULL,
    `lastname`   VARCHAR(45)  NOT NULL,
    `is_blocked` INT          NOT NULL DEFAULT '1',
    `roles_id`   INT          NOT NULL,
    PRIMARY KEY (`u_id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `fk_users_roles1_idx` (`roles_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_roles1`
        FOREIGN KEY (`roles_id`)
            REFERENCES `mydbproject`.`roles` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydbproject`.`booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydbproject`.`booking`;

CREATE TABLE IF NOT EXISTS `mydbproject`.`booking`
(
    `b_id`               INT           NOT NULL AUTO_INCREMENT,
    `user_id`            INT           NOT NULL,
    `status_id`          INT           NOT NULL DEFAULT '1',
    `car_id`             INT           NOT NULL,
    `manager_id`         INT           NULL     DEFAULT NULL,
    `creation_date`      TIMESTAMP     NULL     DEFAULT CURRENT_TIMESTAMP,
    `user_details`       VARCHAR(45)   NOT NULL,
    `with_driver`        INT           NOT NULL,
    `booking_start_date` DATE          NOT NULL,
    `booking_end_date`   DATE          NOT NULL,
    `price`              DECIMAL(8, 2) NOT NULL,
    `decline_info`       VARCHAR(100)  NULL     DEFAULT NULL,
    `additional_fee`     DECIMAL(8, 2) NULL     DEFAULT NULL,
    PRIMARY KEY (`b_id`),
    INDEX `fk_booking_user_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_booking_status1_idx` (`status_id` ASC) VISIBLE,
    INDEX `fk_booking_car1_idx` (`car_id` ASC) VISIBLE,
    INDEX `fk_booking_users1_idx` (`manager_id` ASC) VISIBLE,
    CONSTRAINT `fk_booking_car1`
        FOREIGN KEY (`car_id`)
            REFERENCES `mydbproject`.`cars` (`c_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_booking_status1`
        FOREIGN KEY (`status_id`)
            REFERENCES `mydbproject`.`booking_status` (`id`)
            ON UPDATE CASCADE,
    CONSTRAINT `fk_booking_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `mydbproject`.`users` (`u_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `fk_booking_users1`
        FOREIGN KEY (`manager_id`)
            REFERENCES `mydbproject`.`users` (`u_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;


INSERT INTO booking_status (id, booking_status_name) VALUES (1, 'PENDING_REVIEW');
INSERT INTO booking_status (id, booking_status_name) VALUES (2, 'ON_REVIEW');
INSERT INTO booking_status (id, booking_status_name) VALUES (3, 'ACTIVE');
INSERT INTO booking_status (id, booking_status_name) VALUES (4, 'FINISHED');
INSERT INTO booking_status (id, booking_status_name) VALUES (5, 'DECLINED');
INSERT INTO booking_status (id, booking_status_name) VALUES (6, 'TERMINATED');

INSERT INTO brands (id, brand_name) VALUES (1, 'Nissan');
INSERT INTO brands (id, brand_name) VALUES (2, 'Mercedes');
INSERT INTO brands (id, brand_name) VALUES (3, 'Hyundai');
INSERT INTO brands (id, brand_name) VALUES (4, 'Honda');
INSERT INTO brands (id, brand_name) VALUES (5, 'Tesla');
INSERT INTO brands (id, brand_name) VALUES (6, 'BMW');

INSERT INTO car_status (id, car_status_name) VALUES (1, 'AVAILABLE');
INSERT INTO car_status (id, car_status_name) VALUES (2, 'RESERVED');
INSERT INTO car_status (id, car_status_name) VALUES (3, 'UNDER_REPAIR');

INSERT INTO quality_class (id, quality_class_name) VALUES (1, 'NEW');
INSERT INTO quality_class (id, quality_class_name) VALUES (2, 'NORMAL');
INSERT INTO quality_class (id, quality_class_name) VALUES (3, 'OLD');

INSERT INTO roles (id, role_name) VALUES (1, 'CUSTOMER');
INSERT INTO roles (id, role_name) VALUES (2, 'MANAGER');
INSERT INTO roles (id, role_name) VALUES (3, 'ADMIN');

INSERT INTO users (u_id, password, email, firstname, lastname, is_blocked, roles_id)
    VALUES (1, 111, 'adminMail@gmail.com', 'Admin', 'Admin', 1, 3);
INSERT INTO users (u_id, password, email, firstname, lastname, is_blocked, roles_id)
VALUES (2, 222, 'manager1@gmail.com', 'Manager1', 'Manager1', 1, 2);















