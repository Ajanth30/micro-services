CREATE TRIGGER  table_update_history
BEFORE UPDATE ON inventory
FOR EACH ROW
INSERT INTO inventory_table_update_history
(
available_quantity,
book_id,
last_restocked,
price,
updated_at
)
VALUES
(
OLD.available_quantity,
OLD.book_id,
OLD.last_restocked,
OLD.price,
NOW()
);