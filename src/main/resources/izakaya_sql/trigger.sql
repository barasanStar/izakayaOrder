DELIMITER //

CREATE TRIGGER trg_check_all_served
AFTER UPDATE ON order_items
FOR EACH ROW
BEGIN
  DECLARE item_count INT;
  DECLARE served_count INT;

  IF OLD.status != 'SERVED' AND NEW.status = 'SERVED' THEN
    SELECT COUNT(*) INTO item_count
    FROM order_items
    WHERE order_id = NEW.order_id;

    SELECT COUNT(*) INTO served_count
    FROM order_items
    WHERE order_id = NEW.order_id
      AND status = 'SERVED';

    IF item_count = served_count THEN
      UPDATE orders
      SET status = 'SERVED',
          served_at = CURRENT_TIMESTAMP
      WHERE id = NEW.order_id;
    END IF;
  END IF;
END;
//

DELIMITER ;

