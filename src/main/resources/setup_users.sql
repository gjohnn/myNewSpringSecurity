-- Activate pgcrypto extension
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Verify if table already exists
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM users LIMIT 1) THEN
    ALTER TABLE users ADD CONSTRAINT users_username_unique UNIQUE (username);

        -- Insert 10,000 users 
        INSERT INTO users (id, username, password)
        SELECT gen_random_uuid(), 
               'user' || generate_series,
               crypt('password' || generate_series, gen_salt('bf'))
        FROM generate_series(1, 10000)
        ON CONFLICT (username) DO NOTHING;
    END IF;
END $$;
