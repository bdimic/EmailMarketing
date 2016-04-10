/* 03Apr2016 version */


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `springtutorial`
--

-- --------------------------------------------------------

--
-- Table structure for table `cm_bounce_codes`
--

CREATE TABLE IF NOT EXISTS `cm_bounce_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(10) NOT NULL,
  `type` varchar(20) NOT NULL,
  `explanation` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=63 ;

--
-- Dumping data for table `cm_bounce_codes`
--

INSERT INTO `cm_bounce_codes` (`id`, `code`, `type`, `explanation`) VALUES
(1, '421', 'traditional', '<domain> Service not available, closing transmission channel'),
(2, '450', 'traditional', 'Requested mail action not taken: mailbox unavailable (e.g., mailbox busy)'),
(3, '451', 'traditional', 'Requested action aborted: error in processing'),
(4, '452', 'traditional', 'Requested action not taken: insufficient system storage'),
(5, '500', 'traditional', 'The server could not recognize the command due to a syntax error.'),
(6, '501', 'traditional', 'A syntax error was encountered in command arguments.'),
(7, '502', 'traditional', 'This command is not implemented.'),
(8, '503', 'traditional', 'The server has encountered a bad sequence of commands.'),
(9, '504', 'traditional', 'A command parameter is not implemented.'),
(10, '550', 'traditional', 'Users mailbox was unavailable (such as not found)'),
(11, '551', 'traditional', 'The recipient is not local to the server.'),
(12, '552', 'traditional', 'The action was aborted due to exceeded storage allocation.'),
(13, '553', 'traditional', 'The command was aborted because the mailbox name is invalid.'),
(14, '554', 'traditional', 'The transaction failed for some unstated reason.'),
(15, '5.0.0', 'enhanced', 'Unknown issue'),
(16, '5.1.0', 'enhanced', 'Other address status'),
(17, '5.1.1', 'enhanced', 'Bad destination mailbox address'),
(18, '5.1.2', 'enhanced', 'Bad destination system address'),
(19, '5.1.3', 'enhanced', 'Bad destination mailbox address syntax'),
(20, '5.1.4', 'enhanced', 'Destination mailbox address ambiguous'),
(21, '5.1.5', 'enhanced', 'Destination mailbox address valid'),
(22, '5.1.6', 'enhanced', 'Mailbox has moved'),
(23, '5.1.7', 'enhanced', 'Bad senders mailbox address syntax'),
(24, '5.1.8', 'enhanced', 'Bad senders system address'),
(25, '5.2.0', 'enhanced', 'Other or undefined mailbox status'),
(26, '5.2.1', 'enhanced', 'Mailbox disabled, not accepting messages'),
(27, '5.2.2', 'enhanced', 'Mailbox full'),
(28, '5.2.3', 'enhanced', 'Message length exceeds administrative limit.'),
(29, '5.2.4', 'enhanced', 'Mailing list expansion problem'),
(30, '5.3.0', 'enhanced', 'Other or undefined mail system status'),
(31, '5.3.1', 'enhanced', 'Mail system full'),
(32, '5.3.2', 'enhanced', 'System not accepting network messages'),
(33, '5.3.3', 'enhanced', 'System not capable of selected features'),
(34, '5.3.4', 'enhanced', 'Message too big for system'),
(35, '5.4.0', 'enhanced', 'Other or undefined network or routing status'),
(36, '5.4.1', 'enhanced', 'No answer from host'),
(37, '5.4.2', 'enhanced', 'Bad connection'),
(38, '5.4.3', 'enhanced', 'Routing server failure'),
(39, '5.4.4', 'enhanced', 'Unable to route'),
(40, '5.4.5', 'enhanced', 'Network congestion'),
(41, '5.4.6', 'enhanced', 'Routing loop detected'),
(42, '5.4.7', 'enhanced', 'Delivery time expired'),
(43, '5.5.0', 'enhanced', 'Other or undefined protocol status'),
(44, '5.5.1', 'enhanced', 'Invalid command'),
(45, '5.5.2', 'enhanced', 'Syntax error'),
(46, '5.5.3', 'enhanced', 'Too many recipients'),
(47, '5.5.4', 'enhanced', 'Invalid command arguments'),
(48, '5.5.5', 'enhanced', 'Wrong protocol version'),
(49, '5.6.0', 'enhanced', 'Other or undefined media error'),
(50, '5.6.1', 'enhanced', 'Media not supported'),
(51, '5.6.2', 'enhanced', 'Conversion required and prohibited'),
(52, '5.6.3', 'enhanced', 'Conversion required but not supported'),
(53, '5.6.4', 'enhanced', 'Conversion with loss performed'),
(54, '5.6.5', 'enhanced', 'Conversion failed'),
(55, '5.7.0', 'enhanced', 'Other or undefined security status'),
(56, '5.7.1', 'enhanced', 'Delivery not authorized, message refused'),
(57, '5.7.2', 'enhanced', 'Mailing list expansion prohibited'),
(58, '5.7.3', 'enhanced', 'Security conversion required but not possible'),
(59, '5.7.4', 'enhanced', 'Security features not supported'),
(60, '5.7.5', 'enhanced', 'Cryptographic failure'),
(61, '5.7.6', 'enhanced', 'Cryptographic algorithm not supported'),
(62, '5.7.7', 'enhanced', 'Message integrity failure');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- --------------------------------------------------------

--
-- Table structure for table `cm_bounce_email`
--

CREATE TABLE IF NOT EXISTS `cm_bounce_email` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email_address` varchar(100) NOT NULL,
  `bounce_reason` varchar(200) NOT NULL,
  `send_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;


-- --------------------------------------------------------

--
-- Table structure for table `cm_broadcast_template`
--

CREATE TABLE IF NOT EXISTS `cm_broadcast_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `b_template_name` varchar(255) NOT NULL,
  `b_template_subject` varchar(255) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `htmlbody` blob,
  `htmlbody_tracking` blob,
  `htmlbody_embed` blob,
  `plaintext` blob,
  `creation_dttm` timestamp NULL DEFAULT NULL,
  `creation_user` varchar(20) DEFAULT NULL,
  `last_change_dttm` timestamp NULL DEFAULT NULL,
  `last_change_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `profile_id` (`profile_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=5 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cm_broadcast_template`
--
ALTER TABLE `cm_broadcast_template`
  ADD CONSTRAINT `profile_id_fk1` FOREIGN KEY (`profile_id`) REFERENCES `cm_email_config` (`profile_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

  
-- --------------------------------------------------------

--
-- Table structure for table `cm_campaign_category`
--

CREATE TABLE IF NOT EXISTS `cm_campaign_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_description` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `cm_config`
--

CREATE TABLE IF NOT EXISTS `cm_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(50) NOT NULL,
  `value` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `cm_email_broadcast`
--

CREATE TABLE IF NOT EXISTS `cm_email_broadcast` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broadcast_id` varchar(20) NOT NULL,
  `broadcast_source` varchar(10) DEFAULT NULL,
  `bcast_template_id` int(11) DEFAULT NULL,
  `broadcast_name` varchar(255) DEFAULT NULL,
  `campaign_id` varchar(20) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `profile_id` int(11) DEFAULT NULL,
  `htmlbody` blob,
  `htmlbody_tracking` blob,
  `htmlbody_embed` blob,
  `plaintext` blob,
  `status` varchar(50) DEFAULT NULL,
  `creation_dttm` timestamp NULL DEFAULT NULL,
  `creation_user` varchar(20) DEFAULT NULL,
  `last_change_dttm` timestamp NULL DEFAULT NULL,
  `last_change_user` varchar(20) DEFAULT NULL,
  `execution_dttm` timestamp NULL DEFAULT NULL,
  `execution_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campaign_id` (`campaign_id`),
  KEY `profile_id` (`profile_id`),
  KEY `broadcast_id` (`broadcast_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=56 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cm_email_broadcast`
--
ALTER TABLE `cm_email_broadcast`
  ADD CONSTRAINT `cm_email_broadcast_ibfk_2` FOREIGN KEY (`profile_id`) REFERENCES `cm_email_config` (`profile_id`) ON DELETE NO ACTION ON UPDATE SET NULL;

-- --------------------------------------------------------

--
-- Table structure for table `cm_email_broadcast_list`
--

CREATE TABLE IF NOT EXISTS `cm_email_broadcast_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `broadcast_id` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `process_dttm` timestamp NULL DEFAULT NULL,
  `name1` varchar(50) DEFAULT NULL,
  `value1` varchar(255) DEFAULT NULL,
  `name2` varchar(50) DEFAULT NULL,
  `value2` varchar(255) DEFAULT NULL,
  `name3` varchar(50) DEFAULT NULL,
  `value3` varchar(255) DEFAULT NULL,
  `name4` varchar(50) DEFAULT NULL,
  `value4` varchar(255) DEFAULT NULL,
  `name5` varchar(50) DEFAULT NULL,
  `value5` varchar(255) DEFAULT NULL,
  `name6` varchar(50) DEFAULT NULL,
  `value6` varchar(255) DEFAULT NULL,
  `name7` varchar(50) DEFAULT NULL,
  `value7` varchar(255) DEFAULT NULL,
  `name8` varchar(50) DEFAULT NULL,
  `value8` varchar(255) DEFAULT NULL,
  `name9` varchar(50) DEFAULT NULL,
  `value9` varchar(255) DEFAULT NULL,
  `name10` varchar(50) DEFAULT NULL,
  `value10` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=131 ;


-- --------------------------------------------------------

--
-- Table structure for table `cm_email_communication`
--

CREATE TABLE IF NOT EXISTS `cm_email_communication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campaign_id` varchar(20) NOT NULL,
  `campaign_source` varchar(10) NOT NULL,
  `campaign_name` varchar(255) DEFAULT NULL,
  `campaign_description` varchar(5000) DEFAULT NULL,
  `campaign_status` varchar(20) DEFAULT NULL,
  `campaign_start_date` date DEFAULT NULL,
  `campaign_end_date` date DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `creation_user` varchar(50) DEFAULT NULL,
  `creation_dttm` timestamp NULL DEFAULT NULL,
  `last_change_user` varchar(50) DEFAULT NULL,
  `last_change_dttm` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `campaign_category` (`category_id`),
  KEY `campaign_id` (`campaign_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=33 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cm_email_communication`
--
ALTER TABLE `cm_email_communication`
  ADD CONSTRAINT `cm_email_communication_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `cm_campaign_category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

  
-- --------------------------------------------------------

--
-- Table structure for table `cm_email_config`
--

CREATE TABLE IF NOT EXISTS `cm_email_config` (
  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_name` varchar(100) DEFAULT NULL,
  `hostname` varchar(100) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `from_address` varchar(100) DEFAULT NULL,
  `debug` tinyint(1) DEFAULT NULL,
  `sslonconnect` tinyint(1) DEFAULT NULL,
  `wait` bigint(20) DEFAULT NULL,
  `reply_to` varchar(100) DEFAULT NULL,
  `bounce_address` varchar(100) DEFAULT NULL,
  `bounce_protocol` varchar(20) DEFAULT NULL,
  `bounce_host` varchar(250) DEFAULT NULL,
  `bounce_port` varchar(20) DEFAULT NULL,
  `bounce_username` varchar(200) DEFAULT NULL,
  `bounce_password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`profile_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2 ;


-- --------------------------------------------------------

--
-- Table structure for table `cm_email_embedded_images`
--

CREATE TABLE IF NOT EXISTS `cm_email_embedded_images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broadcast_id` varchar(20) DEFAULT NULL,
  `bcast_template_id` int(11) DEFAULT NULL,
  `url` varchar(5000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=24 ;


-- --------------------------------------------------------

--
-- Table structure for table `cm_ga_config`
--

CREATE TABLE IF NOT EXISTS `cm_ga_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `application_name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `table_id` varchar(50) CHARACTER SET latin1 NOT NULL,
  `P12_key_file_name` varchar(50) CHARACTER SET latin1 NOT NULL,
  `P12_file` blob,
  `api_email` varchar(100) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;


-- --------------------------------------------------------

--
-- Table structure for table `cm_tracking_config`
--

CREATE TABLE IF NOT EXISTS `cm_tracking_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broadcast_id` varchar(20) DEFAULT NULL,
  `bcast_template_id` int(11) DEFAULT NULL,
  `tracking_flg` int(1) NOT NULL,
  `tracking_type` varchar(20) NOT NULL,
  `utm_campaign` varchar(50) DEFAULT NULL,
  `utm_medium` varchar(50) DEFAULT NULL,
  `utm_source` varchar(50) DEFAULT NULL,
  `utm_content` varchar(50) DEFAULT NULL,
  `open_ga_flg` int(1) NOT NULL,
  `open_pixel_flg` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `broadcast_id` (`broadcast_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=24 ;


-- --------------------------------------------------------

--
-- Table structure for table `cm_tracking_response`
--

CREATE TABLE IF NOT EXISTS `cm_tracking_response` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unique_id` bigint(20) NOT NULL,
  `broadcast_id` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `response_type` varchar(20) NOT NULL,
  `response_source` varchar(50) NOT NULL,
  `response_url` varchar(255) DEFAULT NULL,
  `response_time` timestamp NOT NULL,
  `processed_dttm` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=70 ;


-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(60) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `authority` varchar(45) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
