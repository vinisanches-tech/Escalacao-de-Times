CREATE DATABASE IF NOT EXISTS escalacao_times
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;

USE escalacao_times;

CREATE TABLE IF NOT EXISTS time (
    id bigint NOT NULL AUTO_INCREMENT,
    nome_do_clube VARCHAR(255) NOT NULL,
    data DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS integrante (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    funcao VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS composicao_time (
	id BIGINT NOT NULL AUTO_INCREMENT,
    time_id BIGINT NOT NULL,
    integrante_id BIGINT NOT NULL,
    
     CONSTRAINT fk_escalacao_times
        FOREIGN KEY (time_id)
        REFERENCES time(id)
        ON DELETE CASCADE,
        
	 CONSTRAINT fk_escalacao_integrante
        FOREIGN KEY (integrante_id)
        REFERENCES integrante(id)
        ON DELETE CASCADE,
        
	PRIMARY KEY (id)
);


SELECT * FROM integrante;
SELECT * FROM time;
SELECT * FROM composicao_time;
SELECT COUNT(*) FROM integrante;
SELECT COUNT(*) FROM time;

INSERT INTO integrante (nome, funcao) VALUES
('Michael Jordan', 'ala'),
('Denis Rodman', 'ala-pivô'),
('Scottie Pippen', 'ala');

INSERT INTO time (nome_do_clube, data) VALUES
('Chicago Bulls', '1994-01-01'),
('Chicago Bulls', '1995-01-01'),
('Detroit Pistons', '1993-01-01');

INSERT INTO composicao_time (time_id, integrante_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2),
(2, 3),
(3, 2);