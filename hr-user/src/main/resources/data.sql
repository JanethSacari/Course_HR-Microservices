INSERT INTO tb_user (name, email, password) VALUES ('X', 'x@gmail.com', '$2a$10$2jBiFe.50eMvxrTqnTIu8eeIgygavNfXxghKelvlYsf9rsgXZj.SG');
INSERT INTO tb_user (name, email, password) VALUES ('Zero', 'zero@gmail.com', '$2a$10$n//FVAoUrslGi5iOE6gCYuiZRFc6nGz1N9gwM0SorLEFl1pgvCdPu');

INSERT INTO tb_role (role_name) VALUES ('ROLE_LEADER');
INSERT INTO tb_role (role_name) VALUES ('`ROLE_RECRUIT');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);