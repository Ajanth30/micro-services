alter table inventory
add column created_at datetime after price,
add column updated_at datetime after created_at;