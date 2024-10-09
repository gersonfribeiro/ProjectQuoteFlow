create table empresa
(
    id_empresa uuid         not null
        primary key,
    cnpj       varchar(18)  not null
        constraint ukfxe9m1q4mix5vq46u36ftjdyb
            unique,
    email      varchar(100) not null
        constraint ukbl7riyres37361eujtcpwi7sm
            unique,
    nome       varchar(100) not null
        constraint ukbwgigp9epp6elsfohco9fetet
            unique,
    senha      varchar(100) not null
);

alter table empresa
    owner to postgres;

create table cotacao
(
    id_cotacao     uuid    not null
        primary key,
    data           timestamp(6),
    numero_cotacao integer not null,
    id_empresa     uuid    not null
        constraint fk9t4jcnds6dk4bh8uenxjmgrn5
            references empresa,
    status         varchar(255)
        constraint cotacao_status_cotacao_check
            check ((status)::text = ANY
                   ((ARRAY ['ENVIANDO'::character varying, 'RESPOSTA_PENDENTE'::character varying, 'RESPOSTA_ENVIADO'::character varying, 'RECIBIDO'::character varying, 'ACEITO'::character varying, 'RECUSADO'::character varying])::text[]))
);

alter table cotacao
    owner to postgres;

create table endereco
(
    id_endereco uuid    not null
        primary key,
    bairro      varchar(100),
    cep         varchar(9),
    complemento varchar(100),
    localidade  varchar(100),
    logradouro  varchar(100),
    numero      integer not null,
    uf          varchar(2),
    id_empresa  uuid    not null
        constraint fkrkf6qy5lylncwojqj9uoy7sg2
            references empresa
);

alter table endereco
    owner to postgres;

create table produto
(
    id_produto uuid         not null
        primary key,
    categoria  varchar(255)
        constraint produto_categoria_produto_check
            check ((categoria)::text = ANY
                   ((ARRAY ['MADEIRA'::character varying, 'ALIMENTOS'::character varying, 'LIMPEZA'::character varying, 'ELETRONICOS'::character varying, 'ROUPAS'::character varying, 'COSMETICOS'::character varying, 'UTENSILIOS'::character varying, 'FERRAMENTAS'::character varying, 'MATERIAIS_CONSTRUCAO'::character varying, 'OUTROS'::character varying])::text[])),
    descricao  varchar(100) not null,
    observacao varchar(100),
    sku        integer      not null,
    id_empresa uuid         not null
        constraint fka8xi94qfwnhugaplf8ut9du0w
            references empresa
);

alter table produto
    owner to postgres;

create table cotacao_produtos
(
    id_cotacao uuid    not null
        constraint fkmt7e8jk4b9rhrntcha4gsga9g
            references cotacao,
    id_produto uuid    not null
        constraint fkmq2my29jvy45713rcpmi2kyf4
            references produto,
    quantidade integer not null
);

alter table cotacao_produtos
    owner to postgres;

create table usuarios
(
    id_usuario uuid         not null
        primary key,
    email      varchar(100) not null
        constraint ukbdp41y8e8un0nsxowhgsl783s
            unique,
    nome       varchar(50)  not null,
    senha      varchar(100) not null,
    telefone   varchar(20)
        constraint uk8nued8eqldao8xceiahvs8xgq
            unique,
    id_empresa uuid
        constraint fkh14flt4oer5daidvqhyesag3o
            references empresa
);

alter table usuarios
    owner to postgres;

create table cotacao_empresa_destinataria
(
    id_tabela               uuid         not null
        primary key,
    id_empresa_destinataria uuid         not null
        constraint fkpeqyp8jtxoq2uvknr9r57qqtt
            references empresa,
    id_cotacao_enviada      uuid         not null
        constraint fkgj3gv44149o2strta8mssqf07
            references cotacao,
    data_envio              timestamp(6) not null
);

alter table cotacao_empresa_destinataria
    owner to postgres;

create table resposta_cotacao
(
    id_resposta_cotacao uuid         not null
        primary key,
    data                timestamp(6) not null,
    id_destinatario     uuid         not null
        constraint fkbr4f8vvhochkebp8d2bdrngcj
            references cotacao_empresa_destinataria
);

alter table resposta_cotacao
    owner to postgres;

create table resposta_produto
(
    id_resposta_produto uuid           not null
        primary key,
    observacao          varchar(255),
    preco_oferecido     numeric(38, 2) not null,
    id_produto          uuid           not null
        constraint fk5ql3ofh434xsqdstk6ncinxak
            references produto,
    id_resposta         uuid           not null
        constraint fkee14bncym89ry8mmmiu0q5xg6
            references resposta_cotacao
);

alter table resposta_produto
    owner to postgres;

