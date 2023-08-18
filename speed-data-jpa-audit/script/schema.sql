CREATE TABLE `audit_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `modified_by` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;