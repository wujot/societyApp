INSERT INTO USER(username, password, email, enabled) values('admin', '$2a$10$YWeBzWhP30ZzTjnTE4xi.uxoCHU4VovNCo8jv/0zKsnnHFqmPq0MS', 'admin@gmail.com', true);
INSERT INTO USER(username, password, email, enabled) values('user', '$2a$10$1QnMN0yqRr22jw.mcuzZFeT1385sIuYiioL9BcTVhIHx2Rz2AB0ly', 'myemail@gmail.com', true);

INSERT INTO USER_ROLE(username, role) values('admin', 'ROLE_ADMIN');
INSERT INTO USER_ROLE(username, role) values('user', 'ROLE_USER');