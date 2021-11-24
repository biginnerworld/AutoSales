create table car_sale_announcements (
                                        announcement_id varchar(255) not null,
                                        author_name varchar(255),
                                        brand varchar(20),
                                        deleted boolean,
                                        description varchar(200),
                                        price numeric(19, 2) not null,
                                        publish_date timestamp,
                                        title varchar(50),
                                        primary key (announcement_id)
);
create table users (
                       user_id varchar(255) not null,
                       email varchar(255),
                       is_deleted boolean,
                       password varchar(255),
                       user_role int4,
                       username varchar(30),
                       primary key (user_id)
)