create table if not exists shortened_urls
(
    id           VARCHAR PRIMARY KEY,
    original_url VARCHAR not null,
    title        VARCHAR,
    created_at   TIMESTAMP default now()
);
