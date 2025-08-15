CREATE DATABASE projeto;

DROP DATABASE projeto;
 
USE projeto;




CREATE TABLE endereco (
    id_endereco BIGINT AUTO_INCREMENT PRIMARY KEY,
    logradouro_endereco VARCHAR(100) NOT NULL,
    numero_endereco VARCHAR(20) NOT NULL,
    complemento_endereco VARCHAR(100),
    bairro_endereco VARCHAR(100) NOT NULL,
    cidade_endereco VARCHAR(100) NOT NULL,
    estado_endereco VARCHAR(50) NOT NULL,
    cep_endereco VARCHAR(20) NOT NULL,
    UNIQUE (
        logradouro_endereco,
        numero_endereco,
        complemento_endereco,
        bairro_endereco,
        cidade_endereco,
        estado_endereco,
        cep_endereco
    )
);

CREATE TABLE contato (
    id_contato BIGINT AUTO_INCREMENT PRIMARY KEY,
	telefone_contato VARCHAR(20) NOT NULL,
    instagram_contato VARCHAR(20) NOT NULL
);

CREATE TABLE usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_usuario VARCHAR(50) NOT NULL,
    sobrenome_usuario VARCHAR(45) NOT NULL,
    email_usuario VARCHAR(45) NOT NULL UNIQUE,
    senha_usuario VARCHAR(25) NOT NULL,
    id_endereco BIGINT NOT NULL,
    id_contato BIGINT,
    CONSTRAINT fk_endereco_usuario FOREIGN KEY (id_endereco) REFERENCES endereco(id_endereco) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_contato_usuario FOREIGN KEY (id_contato) REFERENCES contato(id_contato) ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE animal (
    id_animal BIGINT AUTO_INCREMENT PRIMARY KEY,
    especie_animal VARCHAR(15) NOT NULL,
    raca_animal VARCHAR(20) NOT NULL,
    cor_animal VARCHAR(15) NOT NULL,
    porte_animal VARCHAR(10) NOT NULL
);



CREATE TABLE evento (
    id_evento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_endereco BIGINT NOT NULL,
    id_animal BIGINT NOT NULL,
    id_contato BIGINT,
    data_evento DATE NOT NULL,
    comentario TEXT,
    tipo_evento VARCHAR(10),
    CONSTRAINT fk_usuario_evento FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_endereco_evento FOREIGN KEY (id_endereco) REFERENCES endereco(id_endereco) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_contato_evento FOREIGN KEY (id_contato) REFERENCES contato(id_contato) ON DELETE CASCADE ON UPDATE CASCADE
);





SELECT * FROM usuario;
SELECT * FROM endereco;
SELECT * FROM evento;
SELECT * FROM animal;

SELECT u.*, e.*
FROM usuario u
JOIN endereco e ON u.id_endereco = e.id_endereco;




SELECT * FROM evento
WHERE id_usuario = 1;

SELECT e.*
FROM evento e
INNER JOIN (
    SELECT id_animal, MAX(data_evento) AS ultima_data
    FROM evento
    GROUP BY id_animal
) ult
ON e.id_animal = ult.id_animal AND e.data_evento = ult.ultima_data;

