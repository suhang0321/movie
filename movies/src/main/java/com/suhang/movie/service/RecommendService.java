package com.suhang.movie.service;

import java.util.List;

import com.suhang.movie.model.Movie;

/**
 * @author hang.su
 * @since 2017-04-26 上午11:45
 */
public interface RecommendService {

    List<Movie> recommend(Long userId);
}
