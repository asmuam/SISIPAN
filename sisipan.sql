-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 27, 2023 at 09:14 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sisipan`
--

-- --------------------------------------------------------

--
-- Table structure for table `formasi`
--

CREATE TABLE `formasi` (
  `id` bigint(20) NOT NULL,
  `kode_provinsi` varchar(255) NOT NULL,
  `kode_satker` varchar(255) NOT NULL,
  `kuota_ks` int(11) NOT NULL DEFAULT 0,
  `kuota_st` int(11) NOT NULL DEFAULT 0,
  `nama_satuankerja` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `formasi`
--

INSERT INTO `formasi` (`id`, `kode_provinsi`, `kode_satker`, `kuota_ks`, `kuota_st`, `nama_satuankerja`) VALUES
(1, '11', '1101', 2, 1, 'Simeulue'),
(2, '11', '1102', 1, 1, 'Aceh Singkil'),
(3, '11', '1103', 2, 2, 'Aceh Selatan'),
(4, '11', '1104', 1, 1, 'Aceh Tenggara'),
(5, '11', '1105', 1, 1, 'Aceh Timur'),
(6, '11', '1106', 0, 2, 'Aceh Tengah'),
(7, '11', '1107', 1, 0, 'Aceh Barat'),
(8, '11', '1108', 1, 2, 'Aceh Besar'),
(9, '11', '1109', 2, 1, 'Pidie'),
(10, '11', '1110', 1, 2, 'Bireuen');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id` bigint(20) NOT NULL,
  `asal_daerah` varchar(255) NOT NULL,
  `ipk` float NOT NULL,
  `name` varchar(255) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `prodi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id`, `asal_daerah`, `ipk`, `name`, `nim`, `prodi`) VALUES
(1, 'Jawa Barat', 3.5, 'Adi Prabowo', '212111001', 'Statistika'),
(2, 'Jawa Tengah', 3.6, 'Budi Santoso', '212111002', 'Statistika'),
(3, 'Jawa Timur', 3.7, 'Citra Wijaya', '212111003', 'Statistika'),
(4, 'Kalimantan Selatan', 3.8, 'Dewi Susanti', '212111004', 'Statistika'),
(5, 'Sulawesi Utara', 3.9, 'Eka Putri', '212111005', 'Statistika'),
(6, 'Sumatra Utara', 3.6, 'Faisal Rahman', '212111006', 'Statistika'),
(7, 'Nusa Tenggara Timur', 3.7, 'Gita Puspita', '212111007', 'Statistika'),
(8, 'Papua Barat', 3.8, 'Hendro Wibowo', '212111008', 'Statistika'),
(9, 'Lampung', 3.9, 'Ina Kartika', '212111009', 'Statistika'),
(10, 'Riau', 3.5, 'Joko Susanto', '212111010', 'Statistika'),
(11, 'Jawa Barat', 3.5, 'Adi Prabowo', '212111001', 'Statistika'),
(12, 'Jawa Tengah', 3.6, 'Budi Santoso', '212111002', 'Statistika'),
(13, 'Jawa Timur', 3.7, 'Citra Wijaya', '212111003', 'Statistika'),
(14, 'Kalimantan Selatan', 3.8, 'Dewi Susanti', '212111004', 'Statistika'),
(15, 'Sulawesi Utara', 3.9, 'Eka Putri', '212111005', 'Statistika'),
(16, 'Sumatra Utara', 3.6, 'Faisal Rahman', '212111006', 'Statistika'),
(17, 'Nusa Tenggara Timur', 3.7, 'Gita Puspita', '212111007', 'Statistika'),
(18, 'Papua Barat', 3.8, 'Hendro Wibowo', '212111008', 'Statistika'),
(19, 'Lampung', 3.9, 'Ina Kartika', '212111009', 'Statistika'),
(20, 'Riau', 3.5, 'Joko Susanto', '212111010', 'Statistika'),
(21, 'Bali', 3.6, 'Kartika Sari', '222111011', 'Komputasi Statistik'),
(22, 'Sulawesi Selatan', 3.7, 'Lukman Hakim', '222111012', 'Komputasi Statistik'),
(23, 'Kalimantan Tengah', 3.8, 'Mega Indah', '222111013', 'Komputasi Statistik'),
(24, 'Maluku Utara', 3.9, 'Nina Cahaya', '222111014', 'Komputasi Statistik'),
(25, 'Papua', 3.6, 'Opik Setiawan', '222111015', 'Komputasi Statistik'),
(26, 'Aceh', 3.7, 'Putra Jaya', '222111016', 'Komputasi Statistik'),
(27, 'Bangka Belitung', 3.8, 'Rina Agustina', '222111017', 'Komputasi Statistik'),
(28, 'Gorontalo', 3.9, 'Surya Adi', '222111018', 'Komputasi Statistik'),
(29, 'Sulawesi Tengah', 3.5, 'Titi Kusuma', '222111019', 'Komputasi Statistik'),
(30, 'Papua Barat', 3.6, 'Yoga Prasetyo', '222111020', 'Komputasi Statistik');

-- --------------------------------------------------------

--
-- Table structure for table `pilihan`
--

CREATE TABLE `pilihan` (
  `id` bigint(20) NOT NULL,
  `hasil` varchar(255) NOT NULL,
  `mahasiswa_id` bigint(20) DEFAULT NULL,
  `pilihan_1` bigint(20) DEFAULT NULL,
  `pilihan_2` bigint(20) DEFAULT NULL,
  `pilihan_3` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `id_mhs` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `formasi`
--
ALTER TABLE `formasi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pilihan`
--
ALTER TABLE `pilihan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_l3emowoovlf27ckwlkvojyvgk` (`mahasiswa_id`),
  ADD KEY `FKq059ako1qneedm34u7sq7uqd5` (`pilihan_1`),
  ADD KEY `FKbx6rqe1wf6cw4eh4cb2wkdan4` (`pilihan_2`),
  ADD KEY `FK3saqjuxxkveamutkvk4566p97` (`pilihan_3`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ovljo5atr66ndtanq1cv4cv69` (`id_mhs`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `formasi`
--
ALTER TABLE `formasi`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `pilihan`
--
ALTER TABLE `pilihan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pilihan`
--
ALTER TABLE `pilihan`
  ADD CONSTRAINT `FK3saqjuxxkveamutkvk4566p97` FOREIGN KEY (`pilihan_3`) REFERENCES `formasi` (`id`),
  ADD CONSTRAINT `FKbx6rqe1wf6cw4eh4cb2wkdan4` FOREIGN KEY (`pilihan_2`) REFERENCES `formasi` (`id`),
  ADD CONSTRAINT `FKegu0utq3snf249k0ljhble3js` FOREIGN KEY (`mahasiswa_id`) REFERENCES `mahasiswa` (`id`),
  ADD CONSTRAINT `FKq059ako1qneedm34u7sq7uqd5` FOREIGN KEY (`pilihan_1`) REFERENCES `formasi` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKr13qx5iai7qwksonpp09mowh9` FOREIGN KEY (`id_mhs`) REFERENCES `mahasiswa` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
