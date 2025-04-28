-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2025 at 02:19 PM
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
-- Database: `samuk`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `ID_Admin` int(5) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `No_Telpon` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `akun_admin`
--

CREATE TABLE `akun_admin` (
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `ID_Admin` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `ID_Barang` int(11) NOT NULL,
  `Nama` varchar(50) NOT NULL,
  `Guna/Merek` varchar(50) NOT NULL,
  `Ukuran` int(11) NOT NULL,
  `Ketebalan` float NOT NULL,
  `Bahan` varchar(50) NOT NULL,
  `Harga` int(11) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bulat`
--

CREATE TABLE `bulat` (
  `ID_Bulat` int(11) NOT NULL,
  `ID_pesan` int(11) NOT NULL,
  `Φ` int(11) NOT NULL,
  `T` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `kotak`
--

CREATE TABLE `kotak` (
  `ID_Kotak` int(11) NOT NULL,
  `P` int(11) NOT NULL,
  `L` int(11) NOT NULL,
  `T` int(11) NOT NULL,
  `ID_Pesanan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `ID_Pesanan` int(20) NOT NULL,
  `Nama_Barang` varchar(50) NOT NULL,
  `Bahan` varchar(13) NOT NULL,
  `Jumlah` int(5) NOT NULL,
  `Tanggal_Ambil` date NOT NULL,
  `Tanggal_Order` date NOT NULL,
  `Bentuk_Barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`ID_Admin`);

--
-- Indexes for table `akun_admin`
--
ALTER TABLE `akun_admin`
  ADD PRIMARY KEY (`UserName`),
  ADD KEY `FK_ID` (`ID_Admin`);

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`ID_Barang`);

--
-- Indexes for table `bulat`
--
ALTER TABLE `bulat`
  ADD PRIMARY KEY (`ID_Bulat`);

--
-- Indexes for table `kotak`
--
ALTER TABLE `kotak`
  ADD PRIMARY KEY (`ID_Kotak`),
  ADD KEY `FK_Pesanan` (`ID_Pesanan`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`ID_Pesanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `ID_Pesanan` int(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `akun_admin`
--
ALTER TABLE `akun_admin`
  ADD CONSTRAINT `FK_ID` FOREIGN KEY (`ID_Admin`) REFERENCES `admin` (`ID_Admin`);

--
-- Constraints for table `kotak`
--
ALTER TABLE `kotak`
  ADD CONSTRAINT `FK_Pesanan` FOREIGN KEY (`ID_Pesanan`) REFERENCES `pesanan` (`ID_Pesanan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
