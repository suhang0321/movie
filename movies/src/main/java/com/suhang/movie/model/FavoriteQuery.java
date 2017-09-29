package com.suhang.movie.model;

/**
 * @author hang.su
 * @since 2017-04-26 上午11:06
 */
public class FavoriteQuery extends Query {

    private static final long serialVersionUID = 2817533620638356127L;

    private Long userId;
    private Long movieId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
