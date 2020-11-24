CREATE TABLE `authors` (
    `id`     SERIAL NOT NULL,
    `name`   TEXT NOT NULL,
    `email`  TEXT NOT NULL UNIQUE,
    `slug`   TEXT NOT NULL UNIQUE,

    PRIMARY KEY (`id`)
);

CREATE TABLE `comics` (
    `id`         SERIAL NOT NULL,
    `author_id`  INTEGER NOT NULL,
    `slug`       TEXT NOT NULL,
    `title`      TEXT NOT NULL,

    PRIMARY KEY (`author_id`, `slug`),
    FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
);

-- TODO: Change `pub_id` from TEXT to UUID!
CREATE TABLE `comic_pages` (
    `id`         SERIAL,
    `comic_id`   INTEGER NOT NULL,
    `pub_id`     TEXT NOT NULL,

    PRIMARY KEY (`id`),
    FOREIGN KEY (`comic_id`) REFERENCES `comics` (`id`)
);