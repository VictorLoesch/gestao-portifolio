-- ============================
-- Tabela pessoa
-- ============================
CREATE TABLE pessoa (
  id BIGINT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  datanascimento DATE,
  rg VARCHAR(14),
  funcionario BOOLEAN,
  gerente BOOLEAN
);

-- ============================
-- Tabela projeto
-- ============================
CREATE TABLE projeto (
  id BIGINT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  data_inicio DATE,
  data_previsao_fim DATE,
  data_fim DATE,
  descricao VARCHAR(5000),
  status VARCHAR(45),
  orcamento FLOAT,
  risco VARCHAR(45),
  idgerente BIGINT NOT NULL,
  CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
    REFERENCES pessoa (id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);