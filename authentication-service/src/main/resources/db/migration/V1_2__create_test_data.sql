INSERT INTO public."role"(name, create_at)
VALUES ('ADMIN', now());

INSERT INTO public."role"(name, create_at)
VALUES ('USER', now());

INSERT INTO public."user"(email, password, create_at, role_id)
VALUES ('admin@mail.ru', '21232F297A57A5A743894A0E4A801FC3', now(), 1);

INSERT INTO public."user"(email, password, create_at, role_id)
VALUES ('user@mail.ru', '5CC32E366C87C4CB49E4309B75F57D64', now(), 2);

INSERT INTO public."user"(email, password, create_at, role_id)
VALUES ('user2@mail.ru', '5CC32E366C87C4CB49E4309B75F57D64', now(), 2);

INSERT INTO public."user"(email, password, create_at, role_id)
VALUES ('user3@mail.ru', '5CC32E366C87C4CB49E4309B75F57D64', now(), 2);

INSERT INTO public."user"(email, password, create_at, role_id)
VALUES ('user4@mail.ru', '5CC32E366C87C4CB49E4309B75F57D64', now(), 2);
