INSERT INTO link (id, creation_date, last_modified_date, created_by, modified_by, title, url)
                 VALUES (1, NOW(), NOW(), null, null, 'Getting started with Spring Boot 2', 'https://www.chagulu.co.in/letsboot');
                 
INSERT INTO comment (id, creation_date, last_modified_date, created_by, last_modified_by, body, link_id)
                    VALUES (1, NOW(), NOW(), null, null, 'What an awesome idea Chagulu !', 1);