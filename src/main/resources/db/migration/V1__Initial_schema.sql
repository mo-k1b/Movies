-- Create movies table
CREATE TABLE IF NOT EXISTS movies (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_year INTEGER,
    genre VARCHAR(100),
    director VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create index on title for faster searches
CREATE INDEX IF NOT EXISTS idx_movies_title ON movies(title);

-- Create index on genre for filtering
CREATE INDEX IF NOT EXISTS idx_movies_genre ON movies(genre);
