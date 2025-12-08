package com.example.movies.repository;

import com.example.movies.model.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRepository extends MongoRepository<Favorite, String> {
    List<Favorite> findByUserId(String userId);

    boolean existsByUserIdAndMovieId(String userId, int movieId);

    void deleteByUserIdAndMovieId(String userId, int movieId);
}
