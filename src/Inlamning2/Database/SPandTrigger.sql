CREATE DEFINER=`root`@`localhost` PROCEDURE `addToCart`(in p_customerId int, in p_customerOrderId int, in p_shoeId int)
BEGIN

    declare v_customerOrderId int;
    declare v_quantity int;
    declare v_shoeQuantity int;

    declare exit handler for sqlexception
begin
rollback;
select 'Something went wrong, rolling back';
end;

start transaction;

select id into v_customerOrderId from customerOrder where id = p_customerOrderId and customerId = p_customerId and status = 'Aktiv';

if v_customerOrderId is null then

		insert into customerOrder(customerId,orderDate,status) values (p_customerId,NOW(),'Aktiv');
        set v_customerOrderId = last_insert_id();

end if;

select quantity into v_quantity from cart where customerOrderId = v_customerOrderId and shoeId = p_shoeId;
select quantity into v_shoeQuantity from shoe where id = p_shoeId;


if v_quantity is null and v_shoeQuantity > 0 then

        insert into cart(customerOrderId,shoeId,quantity) values(v_customerOrderId,p_shoeId,1);
update shoe set quantity = v_shoeQuantity - 1 where id = p_shoeId;

elseif v_quantity >= 0 and v_shoeQuantity > 0 then

update cart set quantity = v_quantity + 1 where customerOrderId = v_customerOrderId and shoeId = p_shoeId;
update shoe set quantity = v_shoeQuantity - 1 where id = p_shoeId;

end if;
commit;
END

delimiter $$
create trigger outOfStock after update on shoe for each row
begin
    if NEW.quantity = 0 then
		insert into outOfStock(shoeId,outOfStockDate) values (NEW.id,NOW());
end if;
end$$
delimiter ;