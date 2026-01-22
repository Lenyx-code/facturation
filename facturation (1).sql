-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 22 jan. 2026 à 16:29
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `facturation`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `telephone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `nom`, `adresse`, `telephone`) VALUES
(1, 'TALA', 'MBALLA 2', '12345');

-- --------------------------------------------------------

--
-- Structure de la table `factures`
--

CREATE TABLE `factures` (
  `id` int(11) NOT NULL,
  `num_fac` varchar(30) NOT NULL,
  `id_cli` int(11) NOT NULL,
  `montant_payer` float NOT NULL,
  `montant_total` float NOT NULL,
  `reste_a_payer` float NOT NULL DEFAULT current_timestamp(),
  `date_fac` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `factures`
--

INSERT INTO `factures` (`id`, `num_fac`, `id_cli`, `montant_payer`, `montant_total`, `reste_a_payer`, `date_fac`) VALUES
(1, '230023', 1, 4000, 3600, -400, '2026-01-22 14:47:01');

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE `produits` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `qte` int(11) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`id`, `nom`, `qte`, `prix`) VALUES
(1, 'Huille mayor 1L', 20, 1800),
(3, 'Huile mayor 5l', 15, 5500);

-- --------------------------------------------------------

--
-- Structure de la table `prod_factures`
--

CREATE TABLE `prod_factures` (
  `id` int(11) NOT NULL,
  `id_fac` int(11) NOT NULL,
  `id_prod` int(11) NOT NULL,
  `qte` int(11) NOT NULL,
  `sell_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `prod_factures`
--

INSERT INTO `prod_factures` (`id`, `id_fac`, `id_prod`, `qte`, `sell_at`) VALUES
(1, 1, 1, 2, '2026-01-22 14:47:01');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `factures`
--
ALTER TABLE `factures`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `numfac` (`num_fac`),
  ADD KEY `fk_client` (`id_cli`);

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `prod_factures`
--
ALTER TABLE `prod_factures`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product` (`id_prod`),
  ADD KEY `fk_facture` (`id_fac`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `factures`
--
ALTER TABLE `factures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `prod_factures`
--
ALTER TABLE `prod_factures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `factures`
--
ALTER TABLE `factures`
  ADD CONSTRAINT `fk_client` FOREIGN KEY (`id_cli`) REFERENCES `clients` (`id`);

--
-- Contraintes pour la table `prod_factures`
--
ALTER TABLE `prod_factures`
  ADD CONSTRAINT `fk_facture` FOREIGN KEY (`id_fac`) REFERENCES `factures` (`id`),
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`id_prod`) REFERENCES `produits` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
