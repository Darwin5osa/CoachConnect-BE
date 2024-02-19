/*1. Se crean usuarios para pruebas*/
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Maria Jose', 'Morales', 19, 'mariajose@gmail.com', '+573104587452', 'https://images.unsplash.com/photo-1554151228-14d9def656e4?q=80&w=1372&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'majo', '1234', 'Estudiante');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Ramona', 'Gonzalez', 29, 'ramona@gmail.com', '+573112547852', 'https://plus.unsplash.com/premium_photo-1661772932347-8f23d66165ad?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'ramona', '1234', 'Estudiante');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Lia', 'Ruales', 22, 'liarua@gmail.com', '+573236587412', 'https://images.unsplash.com/photo-1438761681033-6461ffad8d80?q=80&w=1470&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'liarua', '1234', 'Estudiante');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Antonio', 'Rodriguez', 39, 'antonio@gmail.com', '+573167548969', 'https://images.unsplash.com/photo-1506794778202-cad84cf45f1d?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'antonio', '1234', 'Tutor');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Diego Armando', 'Silva', 22, 'diegosilva@gmail.com', '+573112547852', 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'diego', '1234', 'tutor');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Juan', 'Pérez', 35, 'juanperez@example.com', '+573001234567', 'https://images.unsplash.com/photo-1513956589380-bad6acb9b9d4?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'juanito', 'password123', 'Estudiante');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Carlos', 'González', 42, 'cgonzalez@example.com', '+573009876543', 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'cgonz', 'contraseña123', 'Administrador');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values ('Ana', 'López', 28, 'ana.lopez@example.com', '+573005678901', 'https://plus.unsplash.com/premium_photo-1691622500807-6d9eeb9ea06a?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'anita', 'claveSegura', 'Tutor');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values  ('Laura', 'Martínez', 30, 'lauramartinez@example.com', '+573001112233', 'https://images.unsplash.com/photo-1548733865-2ff7802d2b3f?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'lauram', 'claveLaura123', 'Estudiante');
insert into usuario (Nombre, Apellido, Edad, Email, ContactoCelular, Foto, Username, Password, Rol) values  ('Alejandro', 'Rodríguez', 25, 'alejandrorodriguez@example.com', '+573004445566', 'https://images.unsplash.com/photo-1608681299041-cc19878f79f1?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 'alejandro.r', 'passwordAle123', 'Tutor');

/*2. Se crean estudiantes para pruebas*/
insert into estudiante (nivel_Educativo, Usuario_idUsuario) values ('Bachiller', 1);
insert into estudiante (nivel_Educativo, Usuario_idUsuario) values ('Universitario', 2);
insert into estudiante (nivel_Educativo, Usuario_idUsuario) values ('Bachiller', 3);
insert into estudiante (nivel_Educativo, Usuario_idUsuario) values ('Primaria', 4);
insert into estudiante (nivel_Educativo, Usuario_idUsuario) values ('Universitario', 5);
insert into estudiante (nivel_Educativo, Usuario_idUsuario) values ('Bachiller', 8);
insert into estudiante (nivel_Educativo, Usuario_idUsuario) values ('Bachiller', 11);

/*3. Se crean tutores para pruebas*/
insert into tutor (Profesion, Descripcion, Usuario_idUsuario) values ('Licenciado en matematicas','Poseo un profundo entendimiento de conceptos matemáticos fundamentales, incluyendo álgebra, cálculo, geometría, análisis numérico, teoría de números, probabilidad y estadística.', 6);
insert into tutor (Profesion, Descripcion, Usuario_idUsuario) values ('Licenciado en lenguas','cuento con un dominio sólido de uno o más idiomas, incluyendo gramática, vocabulario, pronunciación y habilidades de comprensión auditiva y expresión escrita y oral.', 7);
insert into tutor (Profesion, Descripcion, Usuario_idUsuario) values ('Ingeniero Informatico', 'Competencia en el diseño, desarrollo, implementación y mantenimiento de aplicaciones y sistemas de software, utilizando diversos lenguajes de programación, frameworks y tecnologías.', 10);
insert into tutor (Profesion, Descripcion, Usuario_idUsuario) values ('Licenciado en literatura','Poseo un conocimiento exhaustivo de la literatura mundial, incluyendo obras clásicas y contemporáneas, así como diferentes géneros literarios como la novela, poesía, ensayo, teatro, entre otros.' , 12);

/*4. Se crean administradores para pruebas*/
insert into admin (Usuario_idUsuario) values (9);

/*5. Se crea detalle de reserva para pruebas*/
insert into detalle_reserva (Fecha_Inicio_Reserva, Fecha_Fin_Reserva, Horas_Reservadas, Estudiante_idEstudiante, Tutor_idTutor) values ('2024-02-19 9:00:00', '2024-02-28 9:00:00',216.00,1, 2);
insert into detalle_reserva (Fecha_Inicio_Reserva, Fecha_Fin_Reserva, Horas_Reservadas, Estudiante_idEstudiante, Tutor_idTutor) values ('2024-03-19 9:00:00', '2024-03-30 9:00:00',264.00, 3, 4);
insert into detalle_reserva (Fecha_Inicio_Reserva, Fecha_Fin_Reserva, Horas_Reservadas, Estudiante_idEstudiante, Tutor_idTutor) values ('2024-04-19 9:00:00', '2024-04-30 9:00:00',264.00,5, 1);

/*6. Se crea nivel  para pruebas*/
insert into nivel (Nombre) values ('Primaria');
insert into nivel (Nombre) values ('Bachillerato');
insert into nivel (Nombre) values ('Universidad');
insert into nivel (Nombre) values ('Hobby');

/*7. Se crea tutorias para pruebas*/

insert into tutoria (Nombre, Descripcion, Nivel_idNivel) values ('Matematicas', 'se enfocan en conceptos como álgebra, geometría, trigonometría, cálculo y estadística, que proporcionan herramientas para resolver problemas y comprender el mundo que nos rodea de manera cuantitativa.', 2);
insert into tutoria (Nombre, Descripcion, Nivel_idNivel) values ('Biologia', 'La biología explora la vida: plantas, animales, células y ecosistemas, promoviendo la comprensión del mundo natural.', 1);
insert into tutoria (Nombre, Descripcion, Nivel_idNivel) values ('Quimica', 'Profundiza en la estructura, propiedades y transformaciones de la materia, desde átomos hasta materiales complejos, abordando reacciones, energía y aplicaciones en diversas áreas.', 3);
insert into tutoria (Nombre, Descripcion, Nivel_idNivel) values ('Castellano', 'estudia la lengua española en su estructura, gramática, literatura y uso comunicativo, desarrollando habilidades de lectura, escritura, comprensión y análisis de textos literarios y no literarios.', 2);
insert into tutoria (Nombre, Descripcion, Nivel_idNivel) values ('Arte Plastica', 'Las artes plásticas como hobby abarcan formas de expresión visual como pintura, dibujo, escultura y fotografía, permitiendo explorar la creatividad y desarrollar habilidades.', 4);

/*8. Se crea tutor_tutorias para pruebas*/
insert into tutor_tutoria (Tutor_idTutor, Tutoria_idTutoria) values (6, 1);
insert into tutor_tutoria (Tutor_idTutor, Tutoria_idTutoria) values (7, 4);
insert into tutor_tutoria (Tutor_idTutor, Tutoria_idTutoria) values (4, 4);

