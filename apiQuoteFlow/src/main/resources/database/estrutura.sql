create table empresas
(
    id_empresa uuid         not null
        primary key,
    cnpj       varchar(18)  not null
        constraint ukdlxi0rufl6e9lwbo6fkcd5kp5
            unique,
    email      varchar(100) not null
        constraint uk8to9qnxq8i65isd0ys2yfeps0
            unique,
    nome       varchar(100) not null,
    telefone   varchar(11)
);

alter table empresas
    owner to postgres;

create table cotacoes
(
    id_cotacao uuid        not null
        constraint cotacao_pkey
            primary key,
    data       timestamp(6),
    numero     integer     not null,
    status     varchar(50) not null
        constraint cotacao_status_check
            check ((status)::text = ANY
                   ((ARRAY['CONCLUIDO'::character varying, 'PREENCHENDO'::character varying, 'ENVIADO'::character varying, 'ENTREGUE'::character varying, 'RESPOSTA_PENDENTE'::character varying, 'RESPOSTA_RECEBIDA'::character varying, 'ACEITO'::character varying, 'RECUSADO'::character varying])::text[])),
    id_empresa uuid        not null
        constraint fk5xy7gse5lcuygba8ilnxlu1u8
            references empresas
);

alter table cotacoes
    owner to postgres;

create table cotacao_empresa_destinataria
(
    id                      uuid         not null
        primary key,
    data_envio              timestamp(6) not null,
    id_cotacao_enviada      uuid         not null
        constraint fkgj3gv44149o2strta8mssqf07
            references cotacoes,
    id_empresa_destinataria uuid         not null
        constraint fkh2nkd76xrb90a59wytlfonyjj
            references empresas
);

alter table cotacao_empresa_destinataria
    owner to postgres;

create table enderecos
(
    id_endereco uuid    not null
        constraint endereco_pkey
            primary key,
    bairro      varchar(100),
    cep         varchar(9),
    complemento varchar(100),
    localidade  varchar(100),
    logradouro  varchar(100),
    numero      integer not null,
    uf          varchar(2),
    id_empresa  uuid    not null
        constraint fklv1r3qifjxan620lc26wd0dq6
            references empresas
);

alter table enderecos
    owner to postgres;

create table produtos
(
    id_produto uuid         not null
        primary key,
    categoria  varchar(50)  not null
        constraint produtos_categoria_check
            check ((categoria)::text = ANY
                   ((ARRAY ['MADEIRA'::character varying, 'ALIMENTOS'::character varying, 'LIMPEZA'::character varying, 'ELETRONICOS'::character varying, 'ROUPAS'::character varying, 'COSMETICOS'::character varying, 'UTENSILIOS'::character varying, 'FERRAMENTAS'::character varying, 'MATERIAIS_CONSTRUCAO'::character varying, 'OUTROS'::character varying])::text[])),
    descricao  varchar(100) not null,
    observacao varchar(100),
    sku        varchar(8)   not null,
    variacao   varchar(100),
    id_empresa uuid         not null
        constraint fk2ajnijv6wk8c8b65ckibpfc9q
            references empresas
);

alter table produtos
    owner to postgres;

create table cotacao_produtos
(
    id_cotacao uuid not null
        constraint fkmt7e8jk4b9rhrntcha4gsga9g
            references cotacoes,
    id_produto uuid not null
        constraint fkosiwxnjympldt5qimsdci2lr
            references produtos,
    quantidade integer not null,
    id         uuid    not null
        constraint cotacao_produtos_pk
            primary key
);

alter table cotacao_produtos
    owner to postgres;

create table resposta_cotacao
(
    id_resposta             uuid         not null
        primary key,
    data_resposta           timestamp(6) not null,
    id_empresa_destinataria uuid         not null
        constraint fkkr4h1g9v8bb9h1s4lwh5giu22
            references cotacao_empresa_destinataria
);

alter table resposta_cotacao
    owner to postgres;

create table resposta_produto
(
    id_resposta_produto uuid           not null
        primary key,
    observacao          text,
    preco               numeric(38, 2) not null,
    id_produto          uuid           not null
        constraint fk888p4ekdger6f4gqepr8nifuo
            references produtos,
    id_resposta_cotacao uuid           not null
        constraint fkee14bncym89ry8mmmiu0q5xg6
            references resposta_cotacao
);

alter table resposta_produto
    owner to postgres;

create table usuarios
(
    id_usuario uuid         not null
        primary key,
    email      varchar(100) not null
        constraint ukkfsp0s1tflm1cwlj8idhqsad0
            unique,
    nome       varchar(50)  not null,
    senha      varchar(100) not null,
    telefone   varchar(20)
        constraint ukfxjwde537oroaygx8s5hehche
            unique,
    id_empresa uuid
        constraint fkgrju3u0utt7909j9yap1oacng
            references empresas,
    permissao  varchar(20)
);

alter table usuarios
    owner to postgres;
