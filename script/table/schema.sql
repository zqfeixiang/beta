CREATE DATABASE IF NOT EXISTS `pomp_test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
grant all PRIVILEGES on pomp_test.* to root@'%' identified by '123456';
flush privileges;

use pomp_test;