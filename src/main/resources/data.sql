DROP TABLE IF EXISTS HOTELS;

CREATE TABLE HOTELS (
  id VARCHAR(250) PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  category VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL
);

INSERT INTO HOTELS (id, name, category, address) VALUES
  ('09cdda32-e239-11ea-87d0-0242ac130003', 'Melia Granada', '4', 'Calle Ángel Ganivet, 7, 18009 Granada'),
  ('1392fc82-e239-11ea-87d0-0242ac130003', 'Hospes Palacio de los Patos', '5', 'Calle Solarillo de Gracia, 1, 18002 Granada'),
  ('1c6f010c-e239-11ea-87d0-0242ac130003', 'Alhambra Palace', '5', 'Plaza Arquitecto García de Paredes, 1, 18009 Granada');