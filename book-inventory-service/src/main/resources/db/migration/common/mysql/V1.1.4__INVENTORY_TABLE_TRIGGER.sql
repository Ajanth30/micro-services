create trigger add_created_time
before insert on inventory
for each row
set new.created_at=NOW();

create trigger add_updated_time
before update on inventory
for each row
set new.updated_at=NOW();