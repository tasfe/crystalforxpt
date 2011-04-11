
DROP TABLE IF EXISTS service_request_assets;

create table service_request_assets(
	service_id int(11) not null,
	asset_id int(11) not null,
	PRIMARY KEY (`service_id`, `asset_id`),
	FOREIGN KEY (`service_id`) REFERENCES `service_request` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	FOREIGN KEY (`asset_id`) REFERENCES `assets_base` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
);