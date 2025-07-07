mostrar el nombre completo 

SELECT CONCAT(nombres," ",apaterno," ",COALESCE(amaterno, " ")) AS "ncompleto"
FROM persona;

SELECT CONCAT(nombres, " ", apaterno) AS "ncompleto"
FROM persona
WHERE amaterno IS NULL;

SELECT edad,COUNT(id_persona)
FROM persona
WHERE sexo="F"
GROUP BY edad;