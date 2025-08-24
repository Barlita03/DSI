-- Se necesita saber qué jugadores estuvieron en qué canchas.
SELECT j.jugador_nombre, c.cancha_nombre
FROM jugadores_por_partido jpp
	JOIN jugadores j ON jpp.jugador_id = j.jugador_id
	JOIN partidos p ON jpp.partido_id = p.partido_id
	JOIN canchas c ON p.partido_cancha = c.cancha_id

-- Qué paleta usó un jugador en un partido.
SELECT jpp.partido_id, j.jugador_nombre, p.paleta_nombre
FROM jugadores_por_partido jpp
	JOIN jugadores j ON jpp.jugador_id = j.jugador_id
	JOIN paletas p ON jpp.paleta_codigo = p.paleta_codigo

-- De qué color era la cancha en un partido.
SELECT p.partido_id, cancha_nombre, color_descripcion
FROM partidos p
	JOIN canchas c ON p.partido_cancha = c.cancha_id
	JOIN canchas_color cc ON c.cancha_id = cc.cancha_id
	JOIN colores col ON cc.color_id = col.color_codigo
WHERE p.partido_inicio >= cc.fecha_inicio
  AND (cc.fecha_fin IS NULL OR p.partido_inicio < cc.fecha_fin)

-- Se necesita poder reservar una cancha, no se puede reservar si existe un partido en ese horario.
CREATE OR REPLACE FUNCTION canchas_disponibles(
    inicio TIMESTAMP,
    fin TIMESTAMP
)
RETURNS TABLE (
    cancha_id INT,
    cancha_nombre VARCHAR
) AS $$
BEGIN
    RETURN QUERY
    SELECT c.cancha_id, c.cancha_nombre
    FROM canchas c
    WHERE NOT EXISTS (
        SELECT 1
        FROM partidos p
        WHERE p.partido_cancha = c.cancha_id
          AND (inicio < p.partido_fin)
          AND (fin > p.partido_inicio)
    )
    AND (
        c.cancha_iluminacion = TRUE
        OR (
            EXTRACT(HOUR FROM inicio) >= 12
            AND EXTRACT(HOUR FROM fin) <= 18
        )
    );
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION verificar_reserva()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM canchas_disponibles(NEW.partido_inicio, NEW.partido_fin) cd
        WHERE cd.cancha_id = NEW.partido_cancha
    ) THEN
        RAISE EXCEPTION 'La cancha % no está disponible en el horario solicitado', NEW.partido_cancha;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reservarCancha
BEFORE INSERT ON partidos
FOR EACH ROW
EXECUTE FUNCTION verificar_reserva()

-- Si la cancha no tiene iluminación, sólo se permiten partidos 12:00–18:00
-- La funcion canchas_disponibles() ya lo resuelve

-- Saber las canchas disponibles para un horario determinado.
SELECT * 
FROM canchas_disponibles('2023-05-10 14:00:00', '2023-05-10 16:00:00');

-- Las canchas qué no tienen iluminación, solo aceptan partidos en el horario de 12:00 a 18:00.
-- La funcion canchas_disponibles() ya lo resuelve