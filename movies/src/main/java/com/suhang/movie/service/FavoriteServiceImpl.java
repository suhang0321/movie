package com.suhang.movie.service;

import static com.suhang.movie.util.CheckUtil.checkArgument;
import static com.suhang.movie.util.CheckUtil.checkState;
import static com.suhang.movie.util.Validator.checkFavoriteId;
import static com.suhang.movie.util.Validator.checkMovieId;
import static com.suhang.movie.util.Validator.checkQuery;
import static com.suhang.movie.util.Validator.checkUserId;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.suhang.movie.dao.FavoriteDao;
import com.suhang.movie.dao.MovieDao;
import com.suhang.movie.model.Favorite;
import com.suhang.movie.model.FavoriteQuery;
import com.suhang.movie.model.RespCode;

/**
 * @author hang.su
 * @since 2017-04-25 下午10:55
 */
@Service("favoriteService")
public class FavoriteServiceImpl implements FavoriteService {

    @Resource
    private FavoriteDao favoriteDao;

    @Resource
    private MovieDao movieDao;

    @Override
    public void create(Favorite favorite) {
        checkFavoriteId(favorite);
        checkState(movieDao.findById(favorite.getMovieId()) != null, RespCode.MOVIE_NOT_EXISTS);
        checkState(favoriteDao.findById(favorite) == null, RespCode.MOVIE_ALREADY_IN_FAVORITES);
        int res = favoriteDao.create(favorite);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public void delete(Favorite favorite) {
        checkFavoriteId(favorite);
        checkState(favoriteDao.findById(favorite) != null, RespCode.MOVIE_NOT_IN_FAVORITES);
        int res = favoriteDao.delete(favorite);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public Favorite findById(Favorite favorite) {
        checkFavoriteId(favorite);
        return favoriteDao.findById(favorite);
    }

    @Override
    public List<Favorite> findByUserId(Long userId) {
        checkUserId(userId);
        return favoriteDao.findByUserId(userId);
    }

    @Override
    public List<Favorite> findByMovieId(Long movieId) {
        checkMovieId(movieId);
        return favoriteDao.findByMovieId(movieId);
    }

    @Override
    public List<Favorite> query(FavoriteQuery query) {
        checkQuery(query);
        checkArgument(onlyOneExist(query), "user id and movie id can only exist one");
        query.ensureNotExceedMaxLimit();
        return favoriteDao.query(query);
    }

    private boolean onlyOneExist(FavoriteQuery query) {
        return (query.getUserId() != null && query.getMovieId() == null) || (query.getUserId() == null && query.getMovieId() != null);
    }
}
