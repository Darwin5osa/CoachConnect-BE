/*1. Se crean usuarios para pruebas*/
/*
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, UserName, Password, rol) values ( 'Maria Jose', 'Morales', 22, 'majo@example.com', '+573104587452', 'https://images.unsplash.com/photo-1554151228-14d9def656e4?q=80&w=1372&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'majo', 'majo123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Ana', 'López',25, 'ana@example.com', '+573102345678', 'https://images.unsplash.com/photo-1567532939604-b6b5b0db2604?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'ana', 'ana123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Laura', 'Martínez', 35, 'laura@example.com', '+573207654321', 'https://images.unsplash.com/photo-1613972555283-10f34cf78f32?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'laura', 'laura123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Juan', 'Hernández',28 ,'juan@example.com', '+573198765432', 'https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'juan', 'juan123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('María', 'Gómez', 32,'maria@example.com', '+573112233445', 'https://images.unsplash.com/photo-1584361853901-dd1904bb7987?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'maria', 'maria123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Juan', 'Pérez',30, 'juan@example.com', '+573102345678', 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'juan', 'juan123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Luisa', 'Gómez',28, 'luisa@example.com', '+573154321987', 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'luisa', 'luisa123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Pedro', 'Martínez',35, 'pedro@example.com', '+573207654321', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'pedro', 'pedro123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Laura', 'Hernández',40, 'laura@example.com', '+573198765432', 'https://images.unsplash.com/photo-1551831423-9fb43e616870?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'laura', 'laura123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, UserName, Password, rol) values ('Daniel', 'García',57, 'daniel@example.com', '+573147852369', 'https://images.unsplash.com/photo-1608681299041-cc19878f79f1?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'daniel', 'daniel123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Sofía', 'Martín',33, 'sofia@example.com', '+573112233445', 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'sofia', 'sofia123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Jorge', 'Díaz',35, 'jorge@example.com', '+573165478932', 'https://images.unsplash.com/photo-1520341280432-4749d4d7bcf9?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'jorge', 'jorge123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Ana', 'Gómez', 36, 'ana@example.com', '+573133557799', 'https://images.unsplash.com/photo-1589203832113-de9d078abc30?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'ana', 'ana123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Roberto', 'Martínez', 31, 'roberto@example.com', '+573142536987', 'https://images.unsplash.com/photo-1519058082700-08a0b56da9b4?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'roberto', 'roberto123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('María', 'Hernández', 29, 'maria@example.com', '+573147852369', 'https://images.unsplash.com/photo-1580489944761-15a19d654956?q=80&w=1961&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'maria', 'maria123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Carlos', 'García', 35, 'carlos@example.com', '+573198765432', 'https://images.unsplash.com/photo-1593467685675-5c0c123331c6?q=80&w=2030&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'carlos', 'carlos123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Lorena', 'Martínez', 33, 'lorena@example.com', '+573102030405', 'https://images.unsplash.com/photo-1531123897727-8f129e1688ce?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'lorena', 'lorena123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values ('Marcos', 'Díaz', 40, 'marcos@example.com', '+573456789012', 'https://images.unsplash.com/photo-1504257432389-52343af06ae3?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'marcos', 'marcos123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Valeria', 'Gómez', 36, 'valeria@example.com', '+573112233445', 'https://images.unsplash.com/photo-1592621385645-e41659e8aabe?q=80&w=1988&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'valeria', 'valeria123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Diego', 'Martín', 32, 'diego@example.com', '+573132435465', 'https://images.unsplash.com/photo-1507081323647-4d250478b919?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'diego', 'diego123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Carolina', 'López', 34, 'carolina@example.com', '+573123456789', 'https://images.unsplash.com/photo-1548142813-c348350df52b?q=80&w=1978&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'carolina', 'carolina123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Fernando', 'García', 59, 'fernando@example.com', '+573156789012', 'https://plus.unsplash.com/premium_photo-1672093880932-d8f8b8ece5c1?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'fernando', 'fernando123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Camila', 'Gómez', 37, 'camila@example.com', '+573165432109', 'https://images.unsplash.com/photo-1489424731084-a5d8b219a5bb?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'camila', 'camila123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Martín', 'Díaz', 29, 'martin@example.com', '+573190876543', 'https://images.unsplash.com/photo-1484515991647-c5760fcecfc7?q=80&w=1949&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'martin', 'martin123', 'tutor');
insert into Usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, rol) values  ('Pedro', 'García', 40, 'pedro@example.com', '+573154321987', 'https://images.unsplash.com/photo-1580518324671-c2f0833a3af3?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'pedro', 'pedro123', 'tutor');

select * from Usuario;
/*2. Se crean profesiones*/
/*insert into Profesion (NombreProfesion) values ('Economista');
insert into Profesion (NombreProfesion) values ('Licenciada en Matematicas');
insert into Profesion (NombreProfesion) values ('Biologa');
insert into Profesion (NombreProfesion) values ('Historiador');
insert into Profesion (NombreProfesion) values ('Ingeniero Informático');
insert into Profesion (NombreProfesion) values ('Profesor de Matemáticas');
insert into Profesion (NombreProfesion) values ('Licenciada en Biología');
insert into Profesion (NombreProfesion) values ('Profesor de Historia');
insert into Profesion (NombreProfesion) values ('Ingeniera Química');
insert into Profesion (NombreProfesion) values ('Profesor de Física');
insert into Profesion (NombreProfesion) values ('Profesora de Informática');
insert into Profesion (NombreProfesion) values ('Licenciado en Literatura');
insert into Profesion (NombreProfesion) values ('Profesora de Geografía');
insert into Profesion (NombreProfesion) values ('Profesor de Español');
insert into Profesion (NombreProfesion) values ('Profesora de Arte');
insert into Profesion (NombreProfesion) values ('Profesor de Música');
insert into Profesion (NombreProfesion) values ('Profesora de Educación Física');
insert into Profesion (NombreProfesion) values ('Profesor de Filosofía');
insert into Profesion (NombreProfesion) values ('Profesora de Lengua y Literatura');
insert into Profesion (NombreProfesion) values ('Licenciado en Educación Artística');
insert into Profesion (NombreProfesion) values ('Profesora de Historia del Arte');
insert into Profesion (NombreProfesion) values ('Profesor de Tecnología');
insert into Profesion (NombreProfesion) values ('Profesora de Educación Cívica');
insert into Profesion (NombreProfesion) values ('Profesor de Educación Física');
insert into Profesion (NombreProfesion) values ('Fisico');

select * from Profesion;

/*3. Se crean tutores para pruebas*/
/*insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Economista experta en análisis macro y microeconómico, con habilidades en modelado y comunicación', 5, 1, 1);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de matemáticas con experiencia en enseñanza secundaria y preparación para exámenes de ingreso a la universidad', 5, 2, 2);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de biología con experiencia en enseñanza secundaria y preparación de estudiantes para ferias científicas', 5, 3, 3);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de historia con experiencia en enseñanza secundaria y organización de eventos culturales', 5, 4, 4);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de informática con experiencia en enseñanza secundaria y desarrollo de programas educativos.', 4, 5, 5);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor con experiencia enseñando matemáticas en bachillerato con enfoque en geometría analítica y cálculo avanzado.', 5, 6, 6);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de biología con experiencia en enseñanza secundaria. Experta en biología celular y genética, capacitada para enseñar biotecnología y ecología avanzada.', 5, 7, 7);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de historia con experiencia en educación secundaria, especializado en historia del siglo XX, con énfasis en guerras mundiales y estudios poscoloniales.', 4, 8, 8);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de química  apasionada por la ciencia, con amplia experiencia en enseñanza secundaria te ayuda a dominar la estequiometria, el equilibrio químico y las disoluciones. Te prepara para pruebas de acceso a la universidad y olimpiadas con clases personalizadas, enfoque práctico y experimentos.', 5, 9, 9);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de física apasionado por el universo, con amplia experiencia en enseñanza secundaria, te ayuda a desentrañar los misterios de la mecánica, el electromagnetismo, la termodinámica y la óptica. Te prepara para pruebas de acceso a la universidad y olimpiadas con clases personalizadas, enfoque práctico, experimentos, demostraciones y simulaciones.', 5, 10, 10);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de informática con experiencia en educación secundaria.  con conocimientos en programación web, bases de datos y seguridad informática.', 3, 11, 11);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de literatura con experiencia en educación secundaria. Experto en análisis literario, creación de textos y movimientos literarios, te acompaño en un viaje apasionante por las obras más emblemáticas de la historia', 5, 12, 12);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de geografía con experiencia en educación secundaria', 5, 13, 13);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de español con experiencia en educación secundaria.', 4, 14, 14);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de arte con experiencia en educación secundaria.', 5, 15, 15);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de música con experiencia en educación secundaria.', 5, 16, 16);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de educación física con experiencia en educación secundaria.', 5, 17, 17);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de filosofía con experiencia en educación secundaria. Experto en historia de la filosofía, ética, lógica y epistemología, te guío en una exploración profunda de las ideas que han marcado el pensamiento occidental.', 5, 18, 18);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de lengua y literatura con experiencia en educación secundaria. Experto en gramática, ortografía, redacción, análisis literario y creación de textos, te acompaño en un recorrido apasionante por el universo del lenguaje. ', 5, 19, 19);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de educación artística con experiencia en educación secundaria.', 4, 20, 20);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de historia del arte con experiencia en educación secundaria.', 5, 21, 21);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de tecnología con experiencia en educación secundaria.', 5, 22, 22);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesora de educación cívica con experiencia en educación secundaria.', 3, 23, 23);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de educación física con experiencia en educación secundaria.', 5, 24, 24);
insert into Tutor (Descripcion, Calificacion, Usuario_idUsuario,Profesion_idProfesion) values ('Profesor de física con experiencia en enseñanza secundaria y preparación de estudiantes para olimpiadas científicas.', 4, 25, 25);

select * from Tutor

