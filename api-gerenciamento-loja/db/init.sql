-- Criação das tabelas
CREATE TABLE IF NOT EXISTS tb_cliente (
    id BINARY(16) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK_cliente_cpf (cpf),
    UNIQUE KEY UK_cliente_email (email)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_produto (
    id BINARY(16) NOT NULL,
    data_criacao_cadastro DATETIME(6) NOT NULL,
    estoque INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    valor DOUBLE NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_itemvenda (
    id BINARY(16) NOT NULL,
    quantidade INT NOT NULL,
    produto_id BINARY(16),
    PRIMARY KEY (id),
    CONSTRAINT FK_itemvenda_produto FOREIGN KEY (produto_id) REFERENCES tb_produto (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_vendedor (
    id BINARY(16) NOT NULL,
    email VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK_vendedor_email (email)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_venda (
    id BINARY(16) NOT NULL,
    data_venda DATETIME(6),
    cliente_id BINARY(16),
    vendedor_id BINARY(16),
    PRIMARY KEY (id),
    CONSTRAINT FK_venda_cliente FOREIGN KEY (cliente_id) REFERENCES tb_cliente (id),
    CONSTRAINT FK_venda_vendedor FOREIGN KEY (vendedor_id) REFERENCES tb_vendedor (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tb_venda_itens (
    venda_model_id BINARY(16) NOT NULL,
    itens_id BINARY(16) NOT NULL,
    UNIQUE KEY UK_venda_itens_item (itens_id),
    CONSTRAINT FK_venda_itens_venda FOREIGN KEY (venda_model_id) REFERENCES tb_venda (id),
    CONSTRAINT FK_venda_itens_item FOREIGN KEY (itens_id) REFERENCES tb_itemvenda (id)
) ENGINE=InnoDB;
