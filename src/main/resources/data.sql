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
    (1, 1, null, 'Purchase', 10, 55, 0, 0),
    (1, 2, null, 'Purchase', 20, 105, 0, 0),
    (2, 2, null, 'Purchase', 10, 55, 0, 0),
    (1, 3, null, 'Purchase', 10, 55, 0, 0),
    (1, 3, null, 'Redeem', -100, 0, 1, 0);
