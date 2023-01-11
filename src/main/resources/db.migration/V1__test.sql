create table category
(
    id          int8 not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table company
(
    id          int8 not null,
    address     varchar(255),
    is_verified boolean,
    name        varchar(255),
    nip         varchar(255),
    id_user     int8,
    primary key (id)
);
create table company_survey
(
    id                 int8 not null,
    is_hidden          boolean,
    id_company         int8,
    id_survey_template int8,
    primary key (id)
);
create table question
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);
create table registered_user
(
    id        int8 not null,
    is_active boolean,
    login     varchar(255),
    mail      varchar(255),
    name      varchar(255),
    password  varchar(255),
    id_role   int8,
    primary key (id)
);
create table role
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);
create table survey_answer
(
    id                int8 not null,
    rating            int4,
    id_company_survey int8,
    id_user           int8,
    id_question       int8,
    primary key (id)
);
create table survey_template
(
    id          int8 not null,
    description varchar(255),
    title       varchar(255),
    id_category int8,
    primary key (id)
);
create table survey_template_question_list
(
    survey_template_list_id int8 not null,
    question_list_id        int8 not null
);
create sequence hibernate_sequence start 1 increment 1;
alter table if exists company
    add constraint FK6cpioj41d2v0qva002th69qrx foreign key (id_user) references registered_user;
alter table if exists company_survey
    add constraint FKmc5vfvkfwv3qm17b48ciw1vx foreign key (id_company) references company;
alter table if exists company_survey
    add constraint FKcid7cggpydyq43gwphpm9fp1j foreign key (id_survey_template) references survey_template;
alter table if exists registered_user
    add constraint FK9kyx0n6638l4nlmg2ik67pqqo foreign key (id_role) references role;
alter table if exists survey_answer
    add constraint FKnfns0h5c72yc7nrbtaafhn64m foreign key (id_company_survey) references company_survey;
alter table if exists survey_answer
    add constraint FKbnug360xebg1fsxcjxv6p292c foreign key (id_user) references registered_user;
alter table if exists survey_answer
    add constraint FKk3tjbb1a2vxcdwpi8n30k0a6r foreign key (id_question) references question;
alter table if exists survey_template
    add constraint FKfvxmva4uwgw9d0ia7r9lqx6b3 foreign key (id_category) references category;
alter table if exists survey_template_question_list
    add constraint FK67uvkgulneawc5861597b4rh4 foreign key (question_list_id) references question;
alter table if exists survey_template_question_list
    add constraint FKq2ijnin71jo0ygkqba4ayfbkd foreign key (survey_template_list_id) references survey_template;

INSERT INTO role (id, name)
VALUES ('registered_user', 1);