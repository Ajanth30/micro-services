DROP TRIGGER IF EXISTS add_created_time;

CREATE TRIGGER add_creation_time
BEFORE INSERT ON inventory
FOR EACH ROW
SET NEW.created_at = NOW(),
    NEW.updated_at = NOW();

