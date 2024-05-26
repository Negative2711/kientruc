-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 25, 2024 at 06:33 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlsv`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `department_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `department_name`) VALUES
(1001, 'Khoa Công Nghệ Thông Tin'),
(1002, 'Khoa Công nghệ Cơ khí'),
(1003, 'Khoa Kỹ thuật Hóa Học'),
(1004, 'Khoa Quản trị Kinh doanh'),
(1005, 'Khoa Khoa học Máy tính'),
(1006, 'Khoa Khoa học Môi trường'),
(1007, 'Khoa Y Dược'),
(1008, 'Khoa Kiến trúc'),
(1009, 'Khoa Vật lý'),
(1010, 'Khoa Điện tử Viễn thông'),
(1011, 'Khoa Toán học'),
(1012, 'Khoa Tiếng Anh'),
(1013, 'Khoa Lịch sử'),
(1014, 'Khoa Văn học'),
(1015, 'Khoa Ngôn ngữ học'),
(1016, 'Khoa Triết học'),
(1017, 'Khoa Hóa học'),
(1018, 'Khoa Sinh học'),
(1019, 'Khoa Địa lý');

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `id` bigint(20) NOT NULL,
  `final_grade` float NOT NULL,
  `gradeck` float NOT NULL,
  `gradegk` float NOT NULL,
  `gradelt1` float NOT NULL,
  `gradelt2` float NOT NULL,
  `gradelt3` float NOT NULL,
  `grade_ranking` enum('KEM','YEU','TRUNG_BINH_YEU','TRUNG_BINH','TRUNG_BINH_KHA','KHA','KHA_GIOI','GIOI') DEFAULT NULL,
  `gradeth1` float NOT NULL,
  `gradeth2` float NOT NULL,
  `gradeth3` float NOT NULL,
  `is_pass` bit(1) NOT NULL,
  `letter_grade` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `registration_section_class_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `final_grade`, `gradeck`, `gradegk`, `gradelt1`, `gradelt2`, `gradelt3`, `grade_ranking`, `gradeth1`, `gradeth2`, `gradeth3`, `is_pass`, `letter_grade`, `note`, `registration_section_class_id`) VALUES
(1, 8.5, 9.5, 8.5, 7, 8, 9, 'KHA_GIOI', 7, 8, 9, b'1', 'A-', 'Em học rất giỏi! Chăm phát biểu.', 4);

-- --------------------------------------------------------

--
-- Table structure for table `major`
--

CREATE TABLE `major` (
  `id` bigint(20) NOT NULL,
  `major_name` varchar(255) DEFAULT NULL,
  `required_credits` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `major`
--

INSERT INTO `major` (`id`, `major_name`, `required_credits`) VALUES
(1001, 'Kỹ Thuật Phần Mềm', 158),
(1002, 'Cơ Khí Chế Tạo Máy', 160),
(1003, 'Công nghệ thông tin', 160),
(1004, 'An toàn thông tin', 155),
(1005, 'Kỹ thuật phần mềm', 158),
(1006, 'Kỹ thuật phần cứng', 162),
(1007, 'Kỹ thuật máy tính', 156),
(1008, 'Kỹ thuật robot', 164),
(1009, 'Kỹ thuật ô tô', 160),
(1010, 'Kỹ thuật cơ điện tử', 162),
(1011, 'Kỹ thuật chế tạo máy', 160),
(1012, 'Kỹ thuật điện', 155),
(1013, 'Kỹ thuật điều khiển và tự động hóa', 158),
(1014, 'Kỹ thuật vật liệu', 157),
(1015, 'Kinh doanh quốc tế', 160),
(1016, 'Quản trị doanh nghiệp', 155),
(1017, 'Marketing', 158),
(1018, 'Kỹ thuật môi trường', 160),
(1019, 'Dược phẩm', 162),
(1020, 'Y sĩ', 155);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `join_school_date` datetime(6) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `place_born` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`id`, `address`, `avatar`, `date_of_birth`, `email`, `full_name`, `gender`, `join_school_date`, `phone_number`, `place_born`, `department_id`, `user_id`) VALUES
(1, '56/327, Vịnh Quang Trung, Thủ Đức Hà Nam Hà Nội', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSjIrv5kjGg-DjcX0NBYYUb_ktxyGC8YpTWu3YWWfHUVw&s', '2001-05-25 13:30:51.000000', 'hongkimbao@gmail.com', 'Hồng Kim Bảo', 'NAM', '2015-05-01 22:29:42.000000', '0338188506', 'HÀ NỘI', 1001, 16);

-- --------------------------------------------------------

--
-- Table structure for table `prime_class`
--

CREATE TABLE `prime_class` (
  `id` bigint(20) NOT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `number_of_student` int(11) NOT NULL,
  `semester_school_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `prime_class`
--

INSERT INTO `prime_class` (`id`, `class_name`, `number_of_student`, `semester_school_id`, `teacher_id`) VALUES
(1, 'DHKTPM24ATT', 50, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `registration_section_class`
--

CREATE TABLE `registration_section_class` (
  `id` bigint(20) NOT NULL,
  `class_group_name` enum('KHONG_PHAN_NHOM','NHOM_1','NHOM_2','NHOM_3') DEFAULT NULL COMMENT 'Da co san trong section_class_group_id nhung van ton tai cho de hinh dung',
  `paid` double NOT NULL,
  `register_date` datetime(6) DEFAULT NULL,
  `section_class_id` bigint(20) DEFAULT NULL,
  `section_class_group_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `registration_section_class`
--

INSERT INTO `registration_section_class` (`id`, `class_group_name`, `paid`, `register_date`, `section_class_id`, `section_class_group_id`, `student_id`) VALUES
(1, 'KHONG_PHAN_NHOM', 0, '2024-04-01 16:38:05.000000', 1, 1, 1),
(2, 'KHONG_PHAN_NHOM', 0, '2024-04-01 16:38:05.000000', 2, 2, 1),
(3, 'KHONG_PHAN_NHOM', 0, '2024-05-09 15:28:53.000000', 3, 3, 2),
(4, 'KHONG_PHAN_NHOM', 0, '2024-05-09 15:30:10.000000', 3, 3, 3),
(6, 'KHONG_PHAN_NHOM', 0, '2024-05-09 15:31:22.000000', 3, 3, 4),
(7, 'KHONG_PHAN_NHOM', 0, '2024-05-25 06:48:20.000000', 4, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role_name` enum('STUDENT','TEACHER','MANAGER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'STUDENT'),
(2, 'TEACHER'),
(3, 'MANAGER');

-- --------------------------------------------------------

--
-- Table structure for table `section_class`
--

CREATE TABLE `section_class` (
  `id` bigint(20) NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `lock_status` enum('DANG_CHO_SINH_VIEN_DANG_KY','CHAP_NHAN_MO_LOP','DA_KHOA') DEFAULT NULL,
  `max_student` int(11) NOT NULL,
  `section_class_name_present` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `semester_school_id` bigint(20) DEFAULT NULL,
  `registered_number` int(11) NOT NULL,
  `section_class_type` enum('HOC_MOI','HOC_LAI','HOC_CAI_THIEN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `section_class`
--

INSERT INTO `section_class` (`id`, `end_date`, `lock_status`, `max_student`, `section_class_name_present`, `start_date`, `subject_id`, `teacher_id`, `created_at`, `semester_school_id`, `registered_number`, `section_class_type`) VALUES
(1, '2024-04-30 16:36:03.000000', 'CHAP_NHAN_MO_LOP', 50, 'DHKTPM24ATT', '2024-04-01 16:36:03.000000', 1, 1, '2024-04-24 17:00:00', 1, 0, 'HOC_MOI'),
(2, '2024-12-31 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24BTT', '2024-06-01 00:00:00.000000', 2, 1, '2024-04-24 17:00:00', 1, 0, 'HOC_MOI'),
(3, '2024-04-30 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24CTT', '2024-04-01 00:00:00.000000', 3, 1, '2024-04-01 05:29:51', 1, 3, 'HOC_MOI'),
(4, '2024-04-30 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24DTT', '2024-04-01 00:00:00.000000', 4, 1, '2024-04-24 17:00:00', 1, 1, 'HOC_MOI'),
(5, '2024-05-31 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24ETT', '2024-05-01 00:00:00.000000', 5, 1, '2024-04-24 17:00:00', 1, 0, 'HOC_MOI'),
(6, '2024-12-31 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24ETT', '2024-06-01 00:00:00.000000', 6, 1, '2024-05-08 05:22:18', 1, 0, 'HOC_MOI'),
(7, '2024-12-31 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24ETT', '2024-06-01 00:00:00.000000', 7, 1, '2024-05-08 05:22:18', 1, 0, 'HOC_MOI'),
(8, '2024-12-31 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24ATT', '2024-06-01 00:00:00.000000', 8, 1, '2024-05-08 22:36:27', 1, 0, 'HOC_MOI'),
(9, '2025-12-31 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24FTT', '2024-06-01 00:00:00.000000', 9, 1, '2024-05-09 07:18:03', 1, 0, 'HOC_MOI'),
(10, '2025-12-31 00:00:00.000000', 'DANG_CHO_SINH_VIEN_DANG_KY', 50, 'DHKTPM24ATT', '2024-06-01 00:00:00.000000', 10, 1, '2024-05-09 07:18:03', 1, 0, 'HOC_MOI');

-- --------------------------------------------------------

--
-- Table structure for table `section_class_group`
--

CREATE TABLE `section_class_group` (
  `id` bigint(20) NOT NULL,
  `class_group_name` enum('KHONG_PHAN_NHOM','NHOM_1','NHOM_2','NHOM_3') DEFAULT NULL COMMENT 'Da co san trong section_class_group_id nhung van ton tai cho de hinh dung',
  `max_student` int(11) NOT NULL,
  `section_class_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `section_class_group`
--

INSERT INTO `section_class_group` (`id`, `class_group_name`, `max_student`, `section_class_id`) VALUES
(1, 'KHONG_PHAN_NHOM', 50, 1),
(2, 'KHONG_PHAN_NHOM', 50, 2),
(3, 'KHONG_PHAN_NHOM', 50, 3),
(4, 'KHONG_PHAN_NHOM', 50, 4),
(5, 'KHONG_PHAN_NHOM', 50, 5),
(6, 'KHONG_PHAN_NHOM', 50, 6),
(7, 'KHONG_PHAN_NHOM', 50, 7),
(8, 'KHONG_PHAN_NHOM', 50, 8),
(9, 'KHONG_PHAN_NHOM', 50, 9),
(10, 'KHONG_PHAN_NHOM', 50, 10);

-- --------------------------------------------------------

--
-- Table structure for table `semester_pattern`
--

CREATE TABLE `semester_pattern` (
  `id` bigint(20) NOT NULL,
  `stage_semester` enum('HOC_KY_1','HOC_KY_2','HOC_KY_3','HOC_KY_4','HOC_KY_5','HOC_KY_6','HOC_KY_7','HOC_KY_8','HOC_KY_9','HOC_KY_10') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `semester_pattern`
--

INSERT INTO `semester_pattern` (`id`, `stage_semester`) VALUES
(1, 'HOC_KY_1'),
(2, 'HOC_KY_2'),
(3, 'HOC_KY_3'),
(4, 'HOC_KY_4'),
(5, 'HOC_KY_5'),
(6, 'HOC_KY_6'),
(7, 'HOC_KY_7'),
(8, 'HOC_KY_8'),
(9, 'HOC_KY_9'),
(10, 'HOC_KY_10');

-- --------------------------------------------------------

--
-- Table structure for table `semester_school`
--

CREATE TABLE `semester_school` (
  `id` bigint(20) NOT NULL,
  `stage_semester` enum('HOC_KY_1','HOC_KY_2','HOC_KY_3') DEFAULT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `semester_school`
--

INSERT INTO `semester_school` (`id`, `stage_semester`, `year`) VALUES
(1, 'HOC_KY_1', 2024);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `join_school_date` datetime(6) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `place_born` varchar(255) DEFAULT NULL,
  `quanlity_of_traning` varchar(255) DEFAULT NULL,
  `status_of_straning` varchar(255) DEFAULT NULL,
  `type_of_traning` varchar(255) DEFAULT NULL,
  `year_end` int(11) NOT NULL,
  `year_of_study` double NOT NULL,
  `year_start` int(11) NOT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `major_id` bigint(20) DEFAULT NULL,
  `prime_class_id` bigint(20) DEFAULT NULL,
  `status_of_traning` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `citizen_card` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `address`, `avatar`, `date_of_birth`, `email`, `full_name`, `gender`, `join_school_date`, `phone_number`, `place_born`, `quanlity_of_traning`, `status_of_straning`, `type_of_traning`, `year_end`, `year_of_study`, `year_start`, `department_id`, `major_id`, `prime_class_id`, `status_of_traning`, `user_id`, `citizen_card`) VALUES
(1, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://res.cloudinary.com/dopzctbyo/image/upload/v1672644566/sv_dkhp/sv-iuh-avatar-pattern_oyubmc.jpg', '2001-05-25 13:30:51.000000', 'taito1doraemon@gmail.com', 'Phan Tấn Tài', 'NAM', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'CLC', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 1, '079201030774'),
(2, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://res.cloudinary.com/dopzctbyo/image/upload/v1715250593/sv_dkhp/liikcqaihdjnvgurqimc.jpg', '2001-12-29 13:30:51.000000', 'doanthelong.com', 'Đoàn Thế Long', 'NAM', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 2, '079201030775'),
(3, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://tuyensinh.uit.edu.vn/sites/default/files/uploads/images/202205/248e92eb-a323-4cf8-9149-eceff326647f.png', '2001-12-29 13:30:51.000000', 'LEHOANGMINH@GMAIL.COM', 'Lê Hoàng Minh', 'NAM', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 3, '079201030775'),
(4, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://jobsgo.vn/blog/wp-content/uploads/2023/05/Anh-ho-so.jpg', '2001-12-29 13:30:51.000000', 'lethianhtuyet@gmail.com', 'Lê Thị Ánh Tuyết', 'NU', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 4, '079201030775'),
(5, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://toigingiuvedep.vn/wp-content/uploads/2021/07/mau-anh-the-dep-chat-luong.jpg', '2001-12-29 13:30:51.000000', 'nguyenthingoc@gmail.com', 'Nguyễn Thị Ngọc', 'NU', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 5, '079201030775'),
(6, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://inanh.net/wp-content/uploads/2020/07/in_anh_the_2.jpg', '2001-12-29 13:30:51.000000', 'lethanhtam@gmail.com', 'Lê Thanh Tâm', 'NU', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 6, '079201030775'),
(7, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://sohanews.sohacdn.com/2019/6/3/photo-1-1559224812706370448730-15595332219951389022692-15595341291552010624314.png', '2001-12-29 13:30:51.000000', 'buitrinhnu@gmail.com', 'Bùi Trinh Nữ', 'NU', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 7, '079201030775'),
(8, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://duyendangstudio.com/wp-content/uploads/2017/10/chup-anh-the-nhanh.jpg', '2001-12-29 13:30:51.000000', 'nguyenthinga@gmail.com', 'Nguyễn Thị Nga', 'NU', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 8, '079201030775'),
(9, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://inanhdepphanthiet.com/Uploads/images/ThietKeNha/2022/3/%E1%BA%A2nh%20th%E1%BA%BB%20%C4%91%E1%BA%B9p%20(2)_1115.jpg', '2001-12-29 13:30:51.000000', 'chauthibaongoc@gmail.com', 'Châu Thị Bảo Ngọc', 'NU', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 9, '079201030775'),
(10, '60/122 Tổ 10 KP8 Q12 TPHCM', 'https://anhdephd.vn/wp-content/uploads/2022/05/mau-anh-the.jpg', '2001-12-29 13:30:51.000000', 'letuankiet@gmail.com', 'Lê Tuấn Kiệt', 'Nam', '2019-04-01 13:30:51.000000', '0338188506', 'HỒ CHÍ MINH', 'DAI_TRA', 'Đang học', 'UNIVERSITY', 2023, 4, 2019, 1001, 1001, 1, 'Đang Học', 10, '079201030775');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL,
  `credits` int(11) NOT NULL,
  `group_number` int(11) NOT NULL,
  `number_of_excerise` int(11) NOT NULL,
  `number_of_theory` int(11) NOT NULL,
  `pre_required_subject_id` bigint(20) NOT NULL,
  `subject_name` varchar(255) DEFAULT NULL,
  `subject_type` enum('TU_CHON','BAT_BUOC') DEFAULT NULL,
  `major_id` bigint(20) DEFAULT NULL,
  `semester_pattern_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `credits`, `group_number`, `number_of_excerise`, `number_of_theory`, `pre_required_subject_id`, `subject_name`, `subject_type`, `major_id`, `semester_pattern_id`) VALUES
(1, 2, 0, 0, 30, 0, 'Nhập Môn Tin Học', 'BAT_BUOC', 1001, 1),
(2, 3, 0, 0, 30, 1, 'Lập Trình Hướng Đối Tượng', 'BAT_BUOC', 1001, 2),
(3, 3, 0, 15, 30, 0, 'Nhập Môn Lập Trình', 'BAT_BUOC', 1001, 1),
(4, 2, 0, 0, 30, 0, 'Toán Cao Cấp 1', 'BAT_BUOC', 1001, 1),
(5, 3, 0, 10, 20, 2, 'Lập Trình Cơ Bản', 'BAT_BUOC', 1001, 2),
(6, 3, 0, 0, 30, 0, 'Cấu Trúc Dữ Liệu và Giải Thuật', 'BAT_BUOC', 1001, 2),
(7, 2, 0, 0, 20, 0, 'Toán Cao Cấp 2', 'BAT_BUOC', 1001, 2),
(8, 3, 0, 10, 20, 6, 'Lập Trình Hướng Sự Kiện', 'BAT_BUOC', 1001, 3),
(9, 3, 0, 0, 30, 0, 'Lập Trình Web', 'BAT_BUOC', 1001, 3),
(10, 2, 0, 0, 20, 6, 'Cơ sở dữ liệu', 'BAT_BUOC', 1001, 3),
(11, 3, 0, 10, 20, 6, 'Lập Trình Java', 'BAT_BUOC', 1001, 4),
(12, 3, 0, 0, 30, 6, 'Lập Trình Python', 'BAT_BUOC', 1001, 4),
(13, 2, 0, 0, 20, 6, 'Hệ điều hành', 'BAT_BUOC', 1001, 4),
(14, 3, 0, 10, 20, 10, 'Quản lý dự án phần mềm', 'BAT_BUOC', 1001, 5),
(15, 3, 0, 0, 30, 10, 'Phát triển ứng dụng di động', 'BAT_BUOC', 1001, 5),
(16, 2, 0, 0, 20, 10, 'Hệ thống thông tin quản lý', 'BAT_BUOC', 1001, 5),
(17, 3, 0, 10, 20, 14, 'Tích hợp hệ thống', 'BAT_BUOC', 1001, 6),
(18, 3, 0, 0, 30, 14, 'An toàn và bảo mật thông tin', 'BAT_BUOC', 1001, 6),
(19, 2, 0, 0, 20, 14, 'Tương tác người máy', 'BAT_BUOC', 1001, 6),
(20, 3, 0, 10, 20, 17, 'Thiết kế giao diện người dùng', 'BAT_BUOC', 1001, 7),
(21, 3, 0, 10, 20, 17, 'Thiết kế và phân tích thuật toán', 'BAT_BUOC', 1001, 7),
(22, 3, 0, 0, 30, 17, 'Công nghệ phần mềm', 'BAT_BUOC', 1001, 7),
(23, 2, 0, 0, 20, 17, 'Lập trình Android nâng cao', 'BAT_BUOC', 1001, 7),
(24, 3, 0, 10, 20, 21, 'Trí tuệ nhân tạo', 'TU_CHON', 1001, 8),
(25, 3, 0, 0, 30, 21, 'Xử lý ngôn ngữ tự nhiên', 'BAT_BUOC', 1001, 8),
(26, 2, 0, 0, 20, 21, 'Thị giác máy tính', 'BAT_BUOC', 1001, 8);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `join_school_date` datetime(6) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `place_born` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `rank_alias` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `address`, `avatar`, `date_of_birth`, `email`, `full_name`, `gender`, `join_school_date`, `phone_number`, `place_born`, `department_id`, `user_id`, `rank_alias`) VALUES
(1, '365/33 Gò Vấp TPHCM', 'https://faceinch.vn/upload/news/chup-anh-the-tha-toc-3007.jpg', '1994-04-01 13:17:53.000000', 'lethia@gmail.com', 'Lê Thị A', 'NU', '2016-04-01 13:17:53.000000', '0329139871', 'HỒ CHÍ MINH', 1001, 11, 'Th.s'),
(2, '365/34 Gò Vấp TPHCM', 'https://lh3.googleusercontent.com/proxy/MZgo0t4Wu-yZaGSVP2jMhyZoVUwMV1E6vWJ7ecWpnsZcHUuWvtC5v7eV5A56CI6aUTdoefRI9ahDYbTWljdeW9Is2IshAy7TKQnmDiUuLDpcfJOedbZ1ABj8M4rSmPaVhoLH7u0NUPYEdbw', '1994-04-01 13:17:53.000000', 'lethib@gmail.com', 'Lê Thị B', 'NU', '2016-04-01 13:17:53.000000', '07930107794', 'HÀ NỘI', 1001, 12, 'T.S'),
(3, '365/34 Gò Vấp TPHCM', 'https://lh3.googleusercontent.com/proxy/MZgo0t4Wu-yZaGSVP2jMhyZoVUwMV1E6vWJ7ecWpnsZcHUuWvtC5v7eV5A56CI6aUTdoefRI9ahDYbTWljdeW9Is2IshAy7TKQnmDiUuLDpcfJOedbZ1ABj8M4rSmPaVhoLH7u0NUPYEdbw', '1994-04-01 13:17:53.000000', 'lethic@gmail.com', 'Lê Thị C', 'NU', '2016-04-01 13:17:53.000000', '07930107794', 'HÀ NỘI', 1001, 13, 'Th.s'),
(4, '365/34 Gò Vấp TPHCM', 'https://lh3.googleusercontent.com/proxy/MZgo0t4Wu-yZaGSVP2jMhyZoVUwMV1E6vWJ7ecWpnsZcHUuWvtC5v7eV5A56CI6aUTdoefRI9ahDYbTWljdeW9Is2IshAy7TKQnmDiUuLDpcfJOedbZ1ABj8M4rSmPaVhoLH7u0NUPYEdbw', '1994-04-01 13:17:53.000000', 'lethid@gmail.com', 'Lê Thị D', 'NU', '2016-04-01 13:17:53.000000', '07930107794', 'HÀ NỘI', 1001, 14, 'T.S'),
(5, '365/34 Gò Vấp TPHCM', 'https://lh3.googleusercontent.com/proxy/MZgo0t4Wu-yZaGSVP2jMhyZoVUwMV1E6vWJ7ecWpnsZcHUuWvtC5v7eV5A56CI6aUTdoefRI9ahDYbTWljdeW9Is2IshAy7TKQnmDiUuLDpcfJOedbZ1ABj8M4rSmPaVhoLH7u0NUPYEdbw', '1994-04-01 13:17:53.000000', 'lethie@gmail.com', 'Lê Thị E', 'NU', '2016-04-01 13:17:53.000000', '07930107794', 'HÀ NỘI', 1001, 15, 'Cử Nhân');

-- --------------------------------------------------------

--
-- Table structure for table `time_table`
--

CREATE TABLE `time_table` (
  `id` bigint(20) NOT NULL,
  `lession_type` enum('LY_THUYET','THUC_HANH','ONLINE','THI','TAM_NGUNG') DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `lession_day` enum('T2','T3','T4','T5','T6','T7','TCN') DEFAULT NULL,
  `lession_date` date NOT NULL DEFAULT curdate(),
  `section_class_group_id` bigint(20) DEFAULT NULL,
  `class_group_name` enum('KHONG_PHAN_NHOM','NHOM_1','NHOM_2','NHOM_3') DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `end_lession_time` enum('T1','T2','T3','T4','T5','T6','T7','T8','T9','T10','T11','T12','T13','T14','T15') DEFAULT NULL,
  `start_lession_time` enum('T1','T2','T3','T4','T5','T6','T7','T8','T9','T10','T11','T12','T13','T14','T15') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `time_table`
--

INSERT INTO `time_table` (`id`, `lession_type`, `note`, `room`, `lession_day`, `lession_date`, `section_class_group_id`, `class_group_name`, `teacher_id`, `end_lession_time`, `start_lession_time`) VALUES
(1, 'LY_THUYET', 'ĐÂY LÀ LỊCH Ý THUYẾT NHẬP MÔN TIN HỌC CHO CÁC BẠN NHÓM 1', 'A1.01', 'T2', '2024-04-30', 1, 'NHOM_1', 1, 'T3', 'T1'),
(2, 'THUC_HANH', 'ĐÂY LÀ LỊCH THỰC HÀNH NHẬP MÔN TIN HỌC CHO CÁC BẠN NHÓM 1', 'A1.01', 'T2', '2024-05-04', 1, 'NHOM_1', 2, 'T15', 'T13'),
(3, 'LY_THUYET', 'ĐÂY lÀ LỊCH LÝ THUYẾT TOÁN CAO CẤP CHO CÁC BẠN NHÓM 1', 'A1.03', 'T2', '2024-04-25', 3, 'NHOM_1', 1, 'T3', 'T1'),
(4, 'THUC_HANH', 'ĐÂY lÀ LỊCH THỰC HÀNH TOÁN CAO CẤP CHO CÁC BẠN NHÓM 1', 'A1.03', 'T3', '2024-04-25', 3, 'NHOM_1', 1, 'T6', 'T4'),
(5, 'THUC_HANH', 'ĐÂY lÀ LỊCH LÝ THUYẾT`TCC1` CHO CÁC TẤT CẢ CÁC BẠN -> KO PHÂN NHÓM NHÉ', 'H4.01', 'TCN', '2024-05-01', 4, 'KHONG_PHAN_NHOM', 1, 'T5', 'T1'),
(26, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-05-03', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(27, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-05-10', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(28, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-05-17', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(29, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-05-24', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(30, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-05-31', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(31, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-06-07', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(32, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-06-14', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(33, 'LY_THUYET', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-06-21', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(34, 'THUC_HANH', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-06-28', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(35, 'THUC_HANH', 'Hello Nhập môn LT thực hành', 'A1.03', 'T3', '2024-07-05', 5, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(36, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-01', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(37, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-08', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(38, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-15', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(39, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-22', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(40, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-29', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(41, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-07-06', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(42, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-01', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(43, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-08', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(44, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-15', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(45, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-22', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(46, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-06-29', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(47, 'LY_THUYET', 'Chào các bạn môn Cơ Sở Dỡ Liệu LT', 'A1.04', 'T2', '2024-07-06', 7, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(48, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-06-01', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(49, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-06-08', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(50, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-06-15', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(51, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-06-22', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(52, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-06-29', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(53, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-07-06', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(54, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-07-13', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(55, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-07-20', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(56, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-07-27', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1'),
(57, 'LY_THUYET', 'Chào các bạn môn NHẬP MÔN LẬP TRÌNH nha', 'A1.05', 'T2', '2024-08-03', 8, 'KHONG_PHAN_NHOM', 1, 'T3', 'T1');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `mssv` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `mssv`, `password`, `role_id`) VALUES
(1, '19524791', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(2, '19524792', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(3, '29524791', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(4, '19520001', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(5, '19520002', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(6, '19520003', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(7, '19520004', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(8, '19520005', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(9, '19520006', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(10, '19520007', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 1),
(11, '222111001', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 2),
(12, '222111002', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 2),
(13, '222111003', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 2),
(14, '222111004', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 2),
(15, '222111005', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 2),
(16, '333222111', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 3),
(17, '333222112', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 3),
(18, '333222113', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 3),
(19, '333222114', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 3),
(20, '333222115', '$2a$12$OfH7DXTy2y8q/zEv9rSxeOl5jFbxxVnOhq1Vkfiv61ifVSD/UZVIO', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_335ime4iywc5bi3w73cielmp8` (`registration_section_class_id`);

--
-- Indexes for table `major`
--
ALTER TABLE `major`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_kgbkwh4hrhrsn0j2ehgebruic` (`department_id`),
  ADD UNIQUE KEY `UK_4vbgsjl6mcxrqyvts0hlilhob` (`user_id`);

--
-- Indexes for table `prime_class`
--
ALTER TABLE `prime_class`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_en141dutaap8q3ak14eb9tmcf` (`teacher_id`),
  ADD KEY `FK1vnyeor75t4wy0l61uaino7aa` (`semester_school_id`);

--
-- Indexes for table `registration_section_class`
--
ALTER TABLE `registration_section_class`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8xmqqktu5rm1xp07mqv8p4g82` (`section_class_id`),
  ADD KEY `FKi169wltyfb5ow5knli8codp72` (`section_class_group_id`),
  ADD KEY `FKqyigkbdlk1ifr843a7uwb8f7j` (`student_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `section_class`
--
ALTER TABLE `section_class`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4akkb7uotvoycno76deef45hu` (`subject_id`),
  ADD KEY `FKn8p9jdb2kd6ped3uhfd3befk9` (`teacher_id`),
  ADD KEY `FKgvlav2mw7y9jafr6xfjr9tadi` (`semester_school_id`);

--
-- Indexes for table `section_class_group`
--
ALTER TABLE `section_class_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKavi2lyceph6k64psj829q4m37` (`section_class_id`);

--
-- Indexes for table `semester_pattern`
--
ALTER TABLE `semester_pattern`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `semester_school`
--
ALTER TABLE `semester_school`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bkix9btnoi1n917ll7bplkvg5` (`user_id`),
  ADD KEY `FKkh3m8c2tq2tgrgma1iyn7tvmx` (`department_id`),
  ADD KEY `FKcml1vvjs3bcqyxdcprjrjd2o0` (`major_id`),
  ADD KEY `FKrxo400bdkgf5k7kp4bs1sl5n9` (`prime_class_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpjawa5kgl1xt4bhrbladxcf9i` (`major_id`),
  ADD KEY `FKgffd1692ghkpu8toe6p6m2pux` (`semester_pattern_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_i5wqs2ds2vpmfpbcdxi9m2jvr` (`user_id`),
  ADD KEY `FKd419q6obhj46eoye136y7rjyq` (`department_id`);

--
-- Indexes for table `time_table`
--
ALTER TABLE `time_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKop7jteq9ygq0au82oq5fp87m4` (`section_class_group_id`),
  ADD KEY `FKogarcxx2enq3ewowstdmjthsj` (`teacher_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1020;

--
-- AUTO_INCREMENT for table `grade`
--
ALTER TABLE `grade`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `major`
--
ALTER TABLE `major`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1021;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `prime_class`
--
ALTER TABLE `prime_class`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `registration_section_class`
--
ALTER TABLE `registration_section_class`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `section_class`
--
ALTER TABLE `section_class`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `section_class_group`
--
ALTER TABLE `section_class_group`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `semester_pattern`
--
ALTER TABLE `semester_pattern`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `semester_school`
--
ALTER TABLE `semester_school`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `time_table`
--
ALTER TABLE `time_table`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10019;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `FKjpv2qb9pj6o0gsg8aj1i586f2` FOREIGN KEY (`registration_section_class_id`) REFERENCES `registration_section_class` (`id`);

--
-- Constraints for table `manager`
--
ALTER TABLE `manager`
  ADD CONSTRAINT `FK78bxps40sjq2glpliqw79ewqv` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FKlx8n4x9vqj3lqv8cj9ienwrv6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `prime_class`
--
ALTER TABLE `prime_class`
  ADD CONSTRAINT `FK1vnyeor75t4wy0l61uaino7aa` FOREIGN KEY (`semester_school_id`) REFERENCES `semester_school` (`id`),
  ADD CONSTRAINT `FKbrlmk62lkg42uume2y02fw7ve` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);

--
-- Constraints for table `registration_section_class`
--
ALTER TABLE `registration_section_class`
  ADD CONSTRAINT `FK8xmqqktu5rm1xp07mqv8p4g82` FOREIGN KEY (`section_class_id`) REFERENCES `section_class` (`id`),
  ADD CONSTRAINT `FKi169wltyfb5ow5knli8codp72` FOREIGN KEY (`section_class_group_id`) REFERENCES `section_class_group` (`id`),
  ADD CONSTRAINT `FKqyigkbdlk1ifr843a7uwb8f7j` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `section_class`
--
ALTER TABLE `section_class`
  ADD CONSTRAINT `FK4akkb7uotvoycno76deef45hu` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKgvlav2mw7y9jafr6xfjr9tadi` FOREIGN KEY (`semester_school_id`) REFERENCES `semester_school` (`id`),
  ADD CONSTRAINT `FKn8p9jdb2kd6ped3uhfd3befk9` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);

--
-- Constraints for table `section_class_group`
--
ALTER TABLE `section_class_group`
  ADD CONSTRAINT `FKavi2lyceph6k64psj829q4m37` FOREIGN KEY (`section_class_id`) REFERENCES `section_class` (`id`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FKcml1vvjs3bcqyxdcprjrjd2o0` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`),
  ADD CONSTRAINT `FKk5m148xqefonqw7bgnpm0snwj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKkh3m8c2tq2tgrgma1iyn7tvmx` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FKrxo400bdkgf5k7kp4bs1sl5n9` FOREIGN KEY (`prime_class_id`) REFERENCES `prime_class` (`id`);

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `FKgffd1692ghkpu8toe6p6m2pux` FOREIGN KEY (`semester_pattern_id`) REFERENCES `semester_pattern` (`id`),
  ADD CONSTRAINT `FKpjawa5kgl1xt4bhrbladxcf9i` FOREIGN KEY (`major_id`) REFERENCES `major` (`id`);

--
-- Constraints for table `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `FKd419q6obhj46eoye136y7rjyq` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FKpb6g6pahj1mr2ijg92r7m1xlh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `time_table`
--
ALTER TABLE `time_table`
  ADD CONSTRAINT `FKogarcxx2enq3ewowstdmjthsj` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `FKop7jteq9ygq0au82oq5fp87m4` FOREIGN KEY (`section_class_group_id`) REFERENCES `section_class_group` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
