INSERT INTO cashier (name) VALUES
  ('Pete'),
  ('James'),
  ('Ines');

INSERT INTO user (name, surname, mobile_number, id_card_number) VALUES
  ('Olga'  , 'Deas', 961123145, 753159),
  ('Vera'  , 'Kout', 961789143, 741852) ,
  ('Viktor', 'King', 961456311, 985412);

INSERT INTO operation (user_id, cashier_id, date_time, operation_type,
                       point_balance, cash_spent, cash_discount, delivered_water_packet) VALUES
    (1, 1, '2021-11-01T10:08:49', 'Purchase', 10, 55, 0, 0),
    (1, 2, '2021-11-01T11:08:49', 'Purchase', 200, 1000, 0, 0),
    (2, 2, '2021-11-02T12:08:49', 'Purchase', 10, 55, 0, 0),
    (1, 3, '2021-11-02T13:08:49', 'Purchase', 10, 55, 0, 0),
    (1, 3, '2021-11-02T14:08:49', 'Redeem', -100, 0, 1, 0),
    (3, 2, '2021-11-02T12:08:49', 'Purchase', 10, 55, 0, 0);
