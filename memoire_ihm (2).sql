-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 25 juil. 2024 à 00:46
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `memoire_ihm`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `email` varchar(150) NOT NULL,
  `username` varchar(150) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(150) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`admin_id`, `email`, `username`, `password`, `full_name`, `image`, `gender`, `date`) VALUES
(1, 'bamba@gmail.com', 'bamba', 'bamba123', NULL, 'C:\\Users\\DELL\\Documents\\NetBeansProjects\\yalla-pitie\\src\\admin_directory\\1.jpg', 'Homme', '2024-04-22');

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `appointment_id` int(30) NOT NULL,
  `patient_id` bigint(50) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `diagnosis` varchar(200) DEFAULT NULL,
  `treatment` varchar(200) DEFAULT NULL,
  `mobile_number` bigint(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `date_modify` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `doctor` varchar(50) NOT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `schedule` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `appointment`
--

INSERT INTO `appointment` (`id`, `appointment_id`, `patient_id`, `name`, `gender`, `description`, `diagnosis`, `treatment`, `mobile_number`, `address`, `date`, `date_modify`, `date_delete`, `status`, `doctor`, `specialized`, `schedule`) VALUES
(1, 1, 0, 'bamba', 'Male', 'testD', 'test ', 'test', 1234567890, 'test', '2024-06-14', '2024-06-19', '2024-06-27', 'Active', 'DID-1', 'test', '2024-06-20'),
(2, 2, NULL, 'bamba', 'Male', 'test', NULL, NULL, 11111111, 'test', '2024-06-14', '2024-06-15', '2024-07-10', 'Active', 'DID-1', NULL, '2024-06-16'),
(3, 3, NULL, 'bamba_test', 'male', 'test', NULL, NULL, 33333333333, 'resr', '2024-06-14', '2024-07-01', '2024-07-06', 'Inactive', 'DID-2', NULL, '2024-07-02'),
(4, 4, NULL, 'admin123', 'Female', 'admin123', 'admin123', 'admin12344', 44444, 'admin123', '2024-06-15', '2024-06-28', '2024-07-12', 'Inactive', 'DID-12', 'Allergologue', '2024-07-10'),
(5, 5, NULL, 'bamba fepp', 'Male', 'bamba merci', 'admin diagnostique', 'admin traitement', 774407113, 'bamba partout', '2024-06-19', '2024-07-06', NULL, 'Inactive', 'DID-12', 'Allergologue', '2024-07-11'),
(6, 6, NULL, 'Saliou', 'Homme', 'eefreff', 'def', 'teat', 774999, 'fgrgr', '2024-07-10', NULL, '2024-07-10', 'Active', 'DID-1', 'Allergologue', '2024-07-11'),
(7, 7, NULL, 'Saliou', 'Homme', 'testttt', 'tettttt', 'eeeee', 44444, 'deef', '2024-07-10', NULL, NULL, 'Active', 'DID-1', 'Allergologue', '2024-07-25'),
(8, 8, NULL, 'test', 'Homme', 'admin123', 'admin123', 'admin123', 33333333, 'admin123', '2024-07-10', NULL, NULL, 'Active', 'DID-1', 'Allergologue', '2024-07-19'),
(9, 9, NULL, 'Djibril Fall', 'Femme', 'corona', 'covid', 'thiagatou', 777, 'cite mixta', '2024-07-11', NULL, '2024-07-13', 'Inactive', 'DID-1', 'Allergologue', '2024-07-20');

-- --------------------------------------------------------

--
-- Structure de la table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `doctor_id` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `mobile_number` bigint(100) DEFAULT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `modify_date` date DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `doctor`
--

INSERT INTO `doctor` (`id`, `doctor_id`, `password`, `full_name`, `email`, `gender`, `mobile_number`, `specialized`, `address`, `image`, `date`, `modify_date`, `delete_date`, `status`) VALUES
(1, 'DID-1', 'admin123', 'Dr Bamba ', 'doctor@gmail.com', 'Male', 774407113, 'Allergologue', 'Dr Bamba Kane', 'C:\\Users\\DELL\\Documents\\NetBeansProjects\\yalla-pitie\\src\\Directory\\DID-1.jpg', '2024-04-19', '2024-07-10', NULL, 'Active'),
(9, 'DID-2', 'bamba123', 'bamba', 'bamba', 'Male', 77777, 'Dermatologist', 'bamba', 'C:\\Users\\DELL\\Documents\\NetBeansProjects\\yalla-pitie\\src\\Doctor_Directory\\DID-2.jpg', '2024-04-22', NULL, '2024-07-03', 'Active'),
(11, 'DID-11', 'bamba123', 'bamba', 'bamba@gmail.com', 'Femme', 774407113, 'Ophthalmologue', 'bamba', 'C:\\Users\\DELL\\Documents\\NetBeansProjects\\yalla-pitie\\src\\Doctor_Directory\\DID-11.jpg', '2024-05-13', '2024-07-10', '2024-07-03', 'Inactive'),
(12, 'DID-12', 'admin123', 'test', 'test@gmail.comm', 'Homme', 77123456, 'Allergologue', 'test', 'C:\\Users\\DELL\\Documents\\NetBeansProjects\\yalla-pitie\\src\\Doctor_Directory\\DID-12.jpg', '2024-06-07', '2024-07-22', '2024-07-22', 'Active'),
(13, 'DID-13', 'admin123', 'admin', 'admin', 'Female', 0, 'Dermatologist', 'admin', 'C:\\Users\\DELL\\Documents\\NetBeansProjects\\yalla-pitie\\src\\Doctor_Directory\\DID-13.jpg', '2024-06-07', '2024-07-03', '2024-07-12', 'Confirmez');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `patient_id` bigint(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `mobile_number` bigint(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `diagnostic` varchar(200) DEFAULT NULL,
  `treatment` varchar(200) DEFAULT NULL,
  `doctor` varchar(100) DEFAULT NULL,
  `specialized` varchar(100) DEFAULT NULL,
  `date` date NOT NULL,
  `date_modify` date DEFAULT NULL,
  `date_delete` date DEFAULT NULL,
  `status` varchar(100) NOT NULL,
  `status_pay` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `patient_id`, `password`, `full_name`, `gender`, `mobile_number`, `address`, `image`, `description`, `diagnostic`, `treatment`, `doctor`, `specialized`, `date`, `date_modify`, `date_delete`, `status`, `status_pay`) VALUES
(1, 20240419, 'patient123', 'bamba', 'Male', 774407113, 'aa', NULL, NULL, NULL, NULL, NULL, NULL, '2024-04-22', '2024-07-03', NULL, 'Active', NULL),
(2, 1234, '3333333', 'BAMBA', 'Male', 774407113, 'TEST', NULL, NULL, NULL, NULL, 'DID-1', NULL, '2024-06-20', '2024-07-03', '2024-06-29', 'Confirmez', NULL),
(5, 12344, '333333', 'Bamba Kane', 'Male', 774407113, 'Mbour', NULL, NULL, NULL, NULL, 'DID-1', NULL, '2024-06-26', '2024-07-10', NULL, 'Confirmez', 'PAYE'),
(6, 123, '122hhh', 'alou', 'Male', 7712341212, 'ucad', NULL, NULL, NULL, NULL, 'DID-1', NULL, '2024-06-28', '2024-07-03', '2024-06-29', 'Confirmez', NULL),
(7, 33333, '333333', 'test', 'Homme', 770000000, 'test address', NULL, NULL, NULL, NULL, 'DID-1', NULL, '2024-06-29', '2024-07-08', '2024-07-12', 'Confirmez', NULL),
(8, 123456, '1234', 'bamba kane', 'Homme', 774407113, 'Mbour', NULL, NULL, NULL, NULL, 'DID-1', NULL, '2024-06-29', '2024-07-08', '2024-07-08', 'Confirmez', NULL),
(9, 12345, '22222', 'bamba', 'Femme', 88888, 'test', NULL, NULL, NULL, NULL, 'DID-1', 'Allergologue', '2024-07-03', '2024-07-10', '2024-07-12', 'Confirmez', NULL),
(10, 1234677, '5555', 'alou ndour', 'Female', 0, 'ucad pav', NULL, NULL, NULL, NULL, 'DID-1', 'Allergologue', '2024-07-03', '2024-07-03', '2024-07-03', 'Confirmez', NULL),
(11, 20322, 'djwdjwj', 'Saliou Sarr', 'Enfant', 7777744, 'ddjkjde', NULL, NULL, NULL, NULL, 'DID-1', 'Allergologue', '2024-07-10', '2024-07-10', '2024-07-12', 'Active', 'Paid'),
(12, 2024, 'djsnsndjfs', 'Maty Diongue', NULL, 777676757, 'cincinnati', NULL, NULL, NULL, NULL, 'DID-1', 'Allergologue', '2024-07-11', NULL, NULL, 'Confirmez', 'PAYE'),
(13, 20245, '123459', 'test paiement', NULL, 777777777, 'test', NULL, NULL, NULL, NULL, 'DID-1', 'Allergologue', '2024-07-12', NULL, NULL, 'Confirmez', 'Paid'),
(14, 2025, 'egsggs', 'bamba', NULL, 774407113, 'test', NULL, NULL, NULL, NULL, 'DID-1', 'Allergologue', '2024-07-12', NULL, '2024-07-12', 'Confirmez', 'Paid');

-- --------------------------------------------------------

--
-- Structure de la table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `doctor` varchar(200) DEFAULT NULL,
  `total_days` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `date_checkout` date DEFAULT NULL,
  `date_pay` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `payment`
--

INSERT INTO `payment` (`id`, `patient_id`, `doctor`, `total_days`, `total_price`, `date`, `date_checkout`, `date_pay`) VALUES
(1, 2024, NULL, 3, 1500, '2024-07-11', '2024-07-14', '2024-07-12'),
(2, 2024, NULL, 2, 1000, '2024-07-11', '2024-07-13', '2024-07-12'),
(3, 2024, NULL, 2, 1000, '2024-07-11', '2024-07-13', '2024-07-12'),
(4, 20322, NULL, 18, 9000, '2024-07-10', '2024-07-28', '2024-07-12'),
(5, 20322, NULL, 21, 10500, '2024-07-10', '2024-07-31', '2024-07-12'),
(6, 20322, NULL, 3, 1500, '2024-07-10', '2024-07-13', '2024-07-12'),
(7, 2024, NULL, 2, 1000, '2024-07-11', '2024-07-13', '2024-07-12'),
(8, 12344, NULL, 24, 12000, '2024-06-26', '2024-07-20', '2024-07-12'),
(9, 2025, NULL, 1, 500, '2024-07-12', '2024-07-13', '2024-07-12'),
(10, 20245, NULL, 2, 1000, '2024-07-12', '2024-07-14', '2024-07-12');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Index pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
