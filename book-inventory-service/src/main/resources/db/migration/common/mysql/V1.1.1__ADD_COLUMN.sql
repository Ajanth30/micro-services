alter table inventory
add column is_available bit(1)  not null after id;
