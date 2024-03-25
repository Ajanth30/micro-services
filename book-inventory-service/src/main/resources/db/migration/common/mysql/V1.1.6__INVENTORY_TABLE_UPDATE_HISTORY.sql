CREATE TABLE inventory_table_update_histroy(
id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
available_quantity INTEGER,
book_id INTEGER NOT NULL,
last_restocked DATETIME NOT NULL,
price INTEGER,
updated_at DATETIME
);



