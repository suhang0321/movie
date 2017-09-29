package com.suhang.movie.service;

import static com.suhang.movie.util.CheckUtil.checkArgument;
import static com.suhang.movie.util.CheckUtil.checkState;
import static com.suhang.movie.util.Validator.checkMovieId;
import static com.suhang.movie.util.Validator.checkMovieName;
import static com.suhang.movie.util.Validator.checkQuery;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.suhang.movie.dao.MovieDao;
import com.suhang.movie.model.BinaryStatus;
import com.suhang.movie.model.Movie;
import com.suhang.movie.model.Query;
import com.suhang.movie.model.RespCode;

/**
 * @author hang.su
 * @since 2017-04-25 下午10:47
 */
@Service("movieService")
public class MovieServiceImpl implements MovieService {

    @Resource
    private MovieDao movieDao;

    @Override
    public void create(Movie movie) {
        checkArgument(StringUtils.isNotBlank(movie.getName()), "movie name cannot be blank");
        int res = movieDao.create(movie);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public void update(Movie movie) {
        checkMovieId(movie.getId());
        checkArgument(movie.getName() != null || movie.getDescription() != null || movie.getStatus() != null, "movie name, description, status cannot be all null");
        if (BinaryStatus.OK != BinaryStatus.of(movie.getStatus())) {
            checkState(findById(movie.getId()) != null, RespCode.MOVIE_NOT_EXISTS);
        }
        if (movie.getName() != null) {
            checkMovieName(movie);
        }
        checkArgument(movie.statusValid(), "invalid status");
        int res = movieDao.update(movie);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public void delete(Long id) {
        checkMovieId(id);
        checkState(findById(id) != null, RespCode.MOVIE_NOT_EXISTS);
        int res = movieDao.delete(id);
        checkState(res > 0, RespCode.FAILED_TO_UPDATE);
    }

    @Override
    public Movie findById(Long id) {
        checkMovieId(id);
        return movieDao.findById(id);
    }

    @Override
    public List<Movie> query(Query query) {
        checkQuery(query);
        query.ensureNotExceedMaxLimit();
        return movieDao.query(query);
    }
}
