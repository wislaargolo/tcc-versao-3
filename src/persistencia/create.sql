CREATE DOMAIN IF NOT EXISTS SEXO AS VARCHAR DEFAULT 'M' CHECK VALUE IN ('F', 'M'); 

CREATE TABLE IF NOT EXISTS FAZENDA(
    ID_FAZENDA INT AUTO_INCREMENT PRIMARY KEY, 
    NOME_FAZENDA VARCHAR(255) NOT NULL, 
    AREA_FAZENDA DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS ANIMAL(
	ID_ANIMAL INT AUTO_INCREMENT PRIMARY KEY, 
	ID_FAZENDA INT NOT NULL,
    CATEGORIA_ANIMAL VARCHAR(255) NOT NULL, 
    RACA_ANIMAL VARCHAR(255) NOT NULL,
    SEXO_ANIMAL SEXO NOT NULL,
    GMD_ANIMAL DECIMAL(10,2) NOT NULL,
    QUANTIDADE_ANIMAL INT NOT NULL	
);

ALTER TABLE ANIMAL ADD CONSTRAINT IF NOT EXISTS FK_ANIMAL_FAZENDA FOREIGN KEY(ID_FAZENDA) REFERENCES FAZENDA(ID_FAZENDA) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS FORRAGEM(
	ID_FORRAGEM INT AUTO_INCREMENT PRIMARY KEY, 
	ID_FAZENDA INT NOT NULL,
    ESPECIE_FORRAGEM VARCHAR(255) NOT NULL, 
    TAXA_ACUMULO_FORRAGEM DECIMAL(10,2) NOT NULL,
);

ALTER TABLE FORRAGEM ADD CONSTRAINT IF NOT EXISTS FK_FORRAGEM_FAZENDA FOREIGN KEY(ID_FAZENDA) REFERENCES FAZENDA(ID_FAZENDA) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS CENARIO(
	ID_CENARIO INT AUTO_INCREMENT PRIMARY KEY, 
	ID_FAZENDA INT NOT NULL,
	ID_ANIMAL INT NOT NULL,
	ID_FORRAGEM INT NOT NULL,
	DATA_INICIO_CENARIO DATE,
    QTD_DIAS_CENARIO INT NOT NULL,
    QTD_ANIMAIS_CENARIO INT NOT NULL,
    MASSA_INICIAL_CENARIO DECIMAL(10,2) NOT NULL,
    PESO_INICIAL_ANIMAIS DECIMAL(10,2) NOT NULL,
    PESO_FINAL_CENARIO DECIMAL(10,2),
    ACUMULO_CENARIO DECIMAL(10,2),
    CONSUMO_CENARIO DECIMAL(10,2),
    SALDO_CENARIO DECIMAL(10,2)
);

ALTER TABLE CENARIO ADD CONSTRAINT IF NOT EXISTS FK_FAZENDA_CENARIO FOREIGN KEY(ID_FAZENDA) REFERENCES FAZENDA(ID_FAZENDA) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE CENARIO ADD CONSTRAINT IF NOT EXISTS FK_ANIMAL_CENARIO FOREIGN KEY(ID_ANIMAL) REFERENCES ANIMAL(ID_ANIMAL) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE CENARIO ADD CONSTRAINT IF NOT EXISTS FK_FORRAGEM_CENARIO FOREIGN KEY(ID_FORRAGEM) REFERENCES FORRAGEM(ID_FORRAGEM) ON DELETE CASCADE ON UPDATE CASCADE;