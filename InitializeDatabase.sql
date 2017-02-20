DROP TABLE bdd_thomas.Joueurs, bdd_thomas.Doublettes, bdd_thomas.Tournoi;

-- Creation des tables
CREATE TABLE bdd_thomas.Joueurs (
	id INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	nom VARCHAR(20) NOT NULL,
	prenom VARCHAR(20) NOT NULL
) ENGINE=INNODB;

CREATE TABLE bdd_thomas.Doublettes (
	id INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	nom1 VARCHAR(20) NOT NULL,
	prenom1 VARCHAR(20) NOT NULL,
	nom2 VARCHAR(20) NOT NULL,
	prenom2 VARCHAR(20) NOT NULL
) ENGINE=INNODB;

CREATE TABLE bdd_thomas.Tournoi (
	eq1 INT NOT NULL,
	eq2 INT NOT NULL,
	scEq1M1 INT NOT NULL,
	scEq2M1 INT NOT NULL,
	winner INT NOT NULL
) ENGINE = INNODB;




