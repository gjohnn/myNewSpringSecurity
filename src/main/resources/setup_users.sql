-- Activar la extensión pgcrypto para usar funciones de encriptación
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Agregar un índice único en la columna 'username' para permitir ON CONFLICT

-- Verificar si la tabla está vacía antes de insertar
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM users LIMIT 1) THEN
    ALTER TABLE users ADD CONSTRAINT users_username_unique UNIQUE (username);

        -- Insertar 10,000 usuarios con contraseñas cifradas
        INSERT INTO users (id, username, password)
        SELECT gen_random_uuid(), 
               'user' || generate_series,
               crypt('password' || generate_series, gen_salt('bf'))
        FROM generate_series(1, 10000)
        ON CONFLICT (username) DO NOTHING;
    END IF;
END $$;
