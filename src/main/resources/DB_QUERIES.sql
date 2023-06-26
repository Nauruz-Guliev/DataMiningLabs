CREATE OR REPLACE FUNCTION generate_random_posts(amount integer) RETURNS void AS
$$
BEGIN
    FOR i IN 1..amount
        LOOP
            INSERT INTO post
                (title, content, publication_date)
            VALUES ('Article ' || i::varchar(255),
                    'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi nibh arcu, fringilla eu elit vel, pulvinar ultrices neque.',
                    random_between(1683463300000, 1683463309999));
        END LOOP;
END
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION random_between(low BIGINT, high BIGINT)
    RETURNS BIGINT AS
$$
BEGIN
    RETURN floor(random() * (high - low + 1) + low);
END;
$$ language 'plpgsql' STRICT;

SELECT generate_random_posts(300);