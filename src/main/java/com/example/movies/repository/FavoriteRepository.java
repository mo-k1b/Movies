package com.example.movies.repository;

import com.example.movies.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);

    boolean existsByUserIdAndMovieId(Long userId, int movieId);

    void deleteByUserIdAndMovieId(Long userId, int movieId);
}
