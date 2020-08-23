INSERT INTO HOTELS (id, name, category, address) VALUES
  ('09cdda32-e239-11ea-87d0-0242ac130003', 'Melia Granada', '4', 'Calle Ángel Ganivet, 7, 18009 Granada'),
  ('1392fc82-e239-11ea-87d0-0242ac130003', 'Hospes Palacio de los Patos', '5', 'Calle Solarillo de Gracia, 1, 18002 Granada'),
  ('1c6f010c-e239-11ea-87d0-0242ac130003', 'Alhambra Palace', '5', 'Plaza Arquitecto García de Paredes, 1, 18009 Granada');
INSERT INTO ROOMS (id, hotel_id, name, occupation, price) VALUES
  ('bcb9a530-e32a-11ea-87d0-0242ac130003', '09cdda32-e239-11ea-87d0-0242ac130003', 'Habitación Melià triple', 3, 120.25),
  ('c406734a-e32a-11ea-87d0-0242ac130003', '09cdda32-e239-11ea-87d0-0242ac130003', 'Habitación clásica con terraza', 2, 100.50),
  ('c9fcba0c-e32a-11ea-87d0-0242ac130003', '09cdda32-e239-11ea-87d0-0242ac130003', 'Habitación clásica', 2, 90.75),
  ('d140ee14-e32a-11ea-87d0-0242ac130003', '1392fc82-e239-11ea-87d0-0242ac130003', 'Dreamer''s', 2, 100.95),
  ('d141ee14-e32a-11ea-87d0-0242ac130003', '1392fc82-e239-11ea-87d0-0242ac130003', 'Dreamer''s Palacio', 2, 180.35),
  ('d9af1fda-e32a-11ea-87d0-0242ac130003', '1c6f010c-e239-11ea-87d0-0242ac130003', 'Junior Suite', 2, 200.50),
  ('df683024-e32a-11ea-87d0-0242ac130003', '1c6f010c-e239-11ea-87d0-0242ac130003', 'Suite', 3, 290.85);
INSERT INTO SERVICES (id, name) VALUES
  ('bf247dca-e32c-11ea-87d0-0242ac130003', 'Swimming Pool'),
  ('c6b80f48-e32c-11ea-87d0-0242ac130003', 'Spa'),
  ('cb91fcc2-e32c-11ea-87d0-0242ac130003', 'Gym');
INSERT INTO HOTELS_SERVICES (hotel_id, service_id) VALUES
  ('09cdda32-e239-11ea-87d0-0242ac130003', 'c6b80f48-e32c-11ea-87d0-0242ac130003'),
  ('09cdda32-e239-11ea-87d0-0242ac130003', 'cb91fcc2-e32c-11ea-87d0-0242ac130003'),
  ('1392fc82-e239-11ea-87d0-0242ac130003', 'bf247dca-e32c-11ea-87d0-0242ac130003'),
  ('1392fc82-e239-11ea-87d0-0242ac130003', 'cb91fcc2-e32c-11ea-87d0-0242ac130003'),
  ('1c6f010c-e239-11ea-87d0-0242ac130003', 'bf247dca-e32c-11ea-87d0-0242ac130003');

  COMMIT;