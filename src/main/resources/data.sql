INSERT INTO especialidade 
(especialidade) 
VALUES 
('Cardiologia'),
('Pediatria'),
('Dermatologia'),
('Neurologia'),
('Ginecologia'),
('Ortopedia');

INSERT INTO medico 
(nome, crm) 
VALUES 
('Dr. Carlos Andrade', 'CRM/RJ 123456'),
('Dra. Juliana Souza', 'CRM/RJ 654321'),
('Dr. Manuel Valente de Paula', 'CRM/RJ 987654'),
('Dr. Thomás Cardoso', 'CRM/RJ 757575'),
('Dra. Fernanda Brandão', 'CRM/RJ 333444'),
('Dr. Roberto Albuquerque', 'CRM/RJ 111222');

INSERT INTO paciente
(nome, cpf, data_nascimento, email, telefone)
VALUES
('Valentina Valente', '00011122233', '1999-11-11', 'valentina.v@hotmail.com', '24993000001'),
('Tiana Pimentel', '11100011133', '1950-01-20', 'tiana.p@gmail.com', '24988000001'),
('Vicente Albuquerque', '88800011199', '1948-08-14', 'albuquerquev@icloud.com', '11900000001'),
('Valéria Coral', '00011122789', '1967-10-30', 'coralvaleria@outlook.com', '21992000001'),
('Camila Mística', '78911122233', '1988-05-12', 'misticamila@yahoo.com', '32998000001'),
('Bárbara Antunes Trindade', '98715552233', '2004-08-07', 'babi_linda123@hotmail.com', '21999000001'),
('Marcos Cordeiro', '44455566677', '1992-03-25', 'marcos.cordeiro@gmail.com', '24981000002');

INSERT INTO medico_especialidade 
(medico_id, especialidade_id) 
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);

INSERT INTO consulta 
(data, status, paciente_id, medico_id) 
VALUES
('2026-06-01 14:00:00', 'AGENDADA', 1, 1),
('2026-06-01 15:30:00', 'AGENDADA', 2, 1),
('2026-06-02 09:00:00', 'AGENDADA', 3, 3),
('2026-06-02 10:00:00', 'AGENDADA', 4, 2),
('2026-06-03 16:00:00', 'AGENDADA', 5, 4),
('2026-05-20 11:00:00', 'REALIZADA', 6, 5);