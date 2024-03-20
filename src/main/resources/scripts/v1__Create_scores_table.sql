CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE scores
(
    id    UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    word  varchar(10),
    score INT
)