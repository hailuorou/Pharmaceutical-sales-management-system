/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.62 : Database - psms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`psms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `psms`;

/*Table structure for table `buy_medicine` */

DROP TABLE IF EXISTS `buy_medicine`;

CREATE TABLE `buy_medicine` (
  `rkid` varchar(32) NOT NULL DEFAULT '',
  `medicineId` varchar(32) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `rktime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`rkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `buy_medicine` */

insert  into `buy_medicine`(`rkid`,`medicineId`,`number`,`money`,`rktime`) values ('RK2020122716','yp0',10,100,'2020年12月27日 16时53分40秒'),('RK2020122915','yp3',1000,1000,'2020年12月29日 15时24分21秒'),('RK20201229152935','yp1',200,3000,'2020年12月29日 15时29分35秒'),('RK20201229153715','yp5',300,9000,'2020年12月29日 15时37分15秒'),('RK20201229153727','yp4',1000,10000,'2020年12月29日 15时37分15秒'),('RK20201229161150','yp6',5000,30000,'2020年12月29日 16时11分50秒'),('RK20201231142349','yp2',1,8,'2020年12月31日 14时23分49秒');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values ('lb0','处方药'),('lb1','非处方药');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `addr` varchar(100) NOT NULL,
  `people` varchar(20) NOT NULL,
  `tel` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `medicineId` varchar(32) NOT NULL DEFAULT '',
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`medicineId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inventory` */

insert  into `inventory`(`medicineId`,`number`) values ('yp0',6),('yp1',394),('yp2',357),('yp3',700),('yp4',950),('yp5',237),('yp6',4770);

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `addr` varchar(100) NOT NULL,
  `proBatch` varchar(50) NOT NULL,
  `purcharPrice` double NOT NULL,
  `sellPrice` double NOT NULL,
  `packingSpecification` varchar(50) NOT NULL,
  `productionDate` varchar(50) NOT NULL,
  `validity` varchar(20) NOT NULL,
  `category` varchar(15) NOT NULL,
  `supplierName` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `medicine` */

insert  into `medicine`(`id`,`name`,`addr`,`proBatch`,`purcharPrice`,`sellPrice`,`packingSpecification`,`productionDate`,`validity`,`category`,`supplierName`) values ('yp0','复方氨酚烷胺片','山西大同市经济开发区（湖东）','200301',10,20,'7片/板×2板/盒','2020.08.18','2022.02','非处方药','大同市利群药业有限责任公司'),('yp1','999感冒灵颗粒','广东省深圳市','国药标准Z44021940',15,30,'10g×9袋/盒','2020.10,20','24个月','非处方药','华润三九医药股份有限公司'),('yp2','众生丸','广东省东莞市石龙镇西湖工业区信息产业园','国药标准Z44023769',8,23,'100丸/盒','2020.11.1','24个月','非处方药','广东众生药业股份有限公司'),('yp3','六味地黄丸(浓缩丸)','北京市北京经济技术开发区东环北路5号','国药准字Z19993068',1,5,'每8丸重1.44g(每8丸相当于饮片3g)','2020.5','24个月','非处方药','北京同仁堂科技发展股份有限公司制药厂'),('yp4','小儿清肺止咳片','北京市北京经济技术开发区同济北路16号','国药准字Z11020403',10,20,'素片:每片重0.2g;薄膜衣片:每片重0.21g','2020.10.08','24个月','非处方药','北京同仁堂科技发展股份有限公司制药厂'),('yp5','三七片','北京市北京经济技术开发区同济北路16号','国药准字Z11020297',30,60,'每片含三七0.5g(每片重0.6g);每片含三七0.5g(每片重0.62g)','2020.9.30','24个月','非处方药','北京同仁堂科技发展股份有限公司制药厂');

/*Table structure for table `sell_medicine` */

DROP TABLE IF EXISTS `sell_medicine`;

CREATE TABLE `sell_medicine` (
  `xsid` varchar(32) NOT NULL DEFAULT '',
  `medicineid` varchar(32) DEFAULT NULL,
  `cid` varchar(32) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `xstime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`xsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sell_medicine` */

insert  into `sell_medicine`(`xsid`,`medicineid`,`cid`,`number`,`money`,`xstime`) values ('XS202012271656','yp0','kh0',4,40,'2020年12月27日 16时56分34秒'),('XS202012291617','yp6','kh0',230,4600,'2020年12月29日 16时17分21秒'),('XS20201229161906','yp4','kh0',50,1000,'2020年12月29日 16时19分06秒'),('XS20201229161926','yp5','kh0',63,3780,'2020年12月29日 16时19分06秒'),('XS20201229161951','yp3','kh0',300,1500,'2020年12月29日 16时19分51秒'),('XS20201229162016','yp1','kh0',6,180,'2020年12月29日 16时20分16秒');

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` varchar(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `postcode` varchar(15) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `fax` varchar(30) NOT NULL,
  `people` varchar(15) NOT NULL,
  `people_tel` varchar(30) NOT NULL,
  `mail` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `supplier` */

insert  into `supplier`(`id`,`name`,`city`,`postcode`,`tel`,`fax`,`people`,`people_tel`,`mail`) values ('gys1','大同市利群药业有限责任公司','山西省大同市经济技术开发区（湖东）','037000','0352-6116806','0352-5355784','宋俊青','0352-5375807','dtlqyy@163.com'),('gys2','华润三九医药股份有限公司','深圳市龙华区观湖街道观澜高新园区观清路1号','518110','86-755-83360999-393042','86-755-83360999-396006','邱华伟','无','000999@999.com.cn'),('gys3','广东众生药业股份有限公司','广东省东莞市石龙镇西湖工业区信息产业园','523325','86-769-86188130','86-769-86188082','陈永红','11111111','zqb@zspcl.com'),('gys4','北京同仁堂科技发展股份有限公司制药厂','北京市丰台区南三环中路20号','100000','154654545','12155455455','王煜炜','无','bangongshi@tong.com');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `tel` varchar(30) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`staff_id`,`username`,`tel`,`password`) values (1,'超级管理员','1008611','OQXvThGuGqlPHR9GA/i1tg==');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
