package com.suhang.movie.dao;

import java.util.List;

import com.suhang.movie.model.Query;
import com.suhang.movie.model.Movie;

/**
 * @author hang.su
 * @since 2017-04-25 下午9:53
 */
public interface MovieDao {

    int create(Movie movie);

    int update(Movie movie);

    int delete(Long id);

    Movie findById(Long id);

    List<Movie> query(Query query);
}
