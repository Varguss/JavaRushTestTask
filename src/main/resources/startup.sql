USE TEST;

DROP TABLE IF EXISTS `computerpart`;

CREATE TABLE computerpart (
    id BIGINT(20) PRIMARY KEY,
    count BIGINT(20) NOT NULL,
    isImportant BIT(1) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE hibernate_sequence (
    next_val BIGINT(20)
) ENGINE = InnoDB;

INSERT INTO hibernate_sequence VALUES (1);

INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (1, 4, 1, 'Материнские платы');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (2, 8, 0, 'Мышки');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (3, 5, 0, 'Клавиатуры');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (4, 4, 0, 'Динамики');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (5, 7, 0, 'Наушники');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (6, 13, 1, 'Оперативки');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (7, 21, 1, 'Жесткие диски');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (8, 3, 1, 'Дисководы');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (9, 9, 0, 'Веб-камеры');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (10, 13, 1, 'USB-порты');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (11, 24, 1, 'Видеокарты');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (12, 5, 1, 'Блоки питания');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (13, 9, 1, 'Комплекты проводов');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (14, 6, 1, 'Центральные процессоры');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (15, 5, 1, 'Корпусы');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (16, 7, 0, 'Геймпады');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (17, 3, 1, 'Мониторы');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (18, 4, 1, 'Кулеры');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (19, 8, 1, 'Порты и разъемы');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (20, 6, 1, 'Сетевые карты');
INSERT INTO computerpart(id, count, isImportant, name)
      VALUES (21, 9, 1, 'Аудиокарты');