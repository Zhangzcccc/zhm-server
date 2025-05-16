-- 2024年05月14日18:24:40 设备功能
CREATE TABLE `tarmac_device` (
  `id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键',
  `fourth_signal_strength` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '4G信号强度',
  `gps_signal_strength` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'GPS信号强度',
  `last_interact_time` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '末次交互时间',
  `device_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备名称',
  `device_type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备类型',
  `daily_flow` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '日流量',
  `monthly_flow` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '当月流量',
  `online_time` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '当前在线时长',
  `network_type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '网络类型',
  `ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ip',
  `sim_ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'SIMIP',
  `dtu_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'DTUID',
  `last_login_time` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '末次登录时间',
  `last_logout_time` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '末次登出时间',
  `icc_id` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'ICCID',
  `memory_usage` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内存使用率',
  `cpu_usage` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'CPU使用率',
  `network_operator` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '网络运营商',
  `sim_card` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'SIM卡',
  `software_version` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '软件版本',
  `device_id` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '设备id',
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tarmac_device_unique` (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO sys_menu(id, pid, name, url, permissions, menu_type, icon, sort, creator, create_date, updater, update_date)VALUES (1790326573773299713, 1067246875800000035, '监控列表', 'device/device', NULL, 0, 'icon-desktop', 0, 1067246875800000001, now(), 1067246875800000001, now());
INSERT INTO sys_menu(id, pid, name, url, permissions, menu_type, icon, sort, creator, create_date, updater, update_date) VALUES (1790326573773299714, 1790326573773299713, '查看', NULL, 'device:device:page,device:device:info', 1, NULL, 0, 1067246875800000001, now(), 1067246875800000001, now());
INSERT INTO sys_menu(id, pid, name, url, permissions, menu_type, icon, sort, creator, create_date, updater, update_date) VALUES (1790326573773299715, 1790326573773299713, '新增', NULL, 'device:device:save', 1, NULL, 1, 1067246875800000001, now(), 1067246875800000001, now());
INSERT INTO sys_menu(id, pid, name, url, permissions, menu_type, icon, sort, creator, create_date, updater, update_date) VALUES (1790326573773299716, 1790326573773299713, '修改', NULL, 'device:device:update', 1, NULL, 2, 1067246875800000001, now(), 1067246875800000001, now());
INSERT INTO sys_menu(id, pid, name, url, permissions, menu_type, icon, sort, creator, create_date, updater, update_date) VALUES (1790326573773299717, 1790326573773299713, '删除', NULL, 'device:device:delete', 1, NULL, 3, 1067246875800000001, now(), 1067246875800000001, now());
INSERT INTO sys_menu(id, pid, name, url, permissions, menu_type, icon, sort, creator, create_date, updater, update_date) VALUES (1790326573773299718, 1790326573773299713, '导出', NULL, 'device:device:export', 1, NULL, 4, 1067246875800000001, now(), 1067246875800000001, now());
