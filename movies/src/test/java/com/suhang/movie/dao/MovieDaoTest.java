package com.suhang.movie.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.suhang.movie.BaseTestContext;
import com.suhang.movie.model.Movie;
import com.suhang.movie.model.Query;

/**
 * @author hang.su
 * @since 2017-04-25 下午9:59
 */
public class MovieDaoTest extends BaseTestContext {

    @Resource
    private MovieDao movieDao;

    @Test
    public void create() throws Exception {
        for (int i = 0; i < 2; i++) {
            Movie movie = mockMovie();
            movieDao.create(movie);
            Assert.assertNotNull(movie.getId());
        }
    }

    @Test
    public void update() throws Exception {
        int res = movieDao.update(Movie.builder()
            .id(2L)
            .description("new description").build());
        assertTrue(res > 0);
    }

    @Test
    public void delete() throws Exception {
        int res = movieDao.delete(2L);
        assertTrue(res > 0);
    }

    @Test
    public void findById() throws Exception {
        Movie movie = movieDao.findById(1L);
        assertNotNull(movie);
    }

    @Test
    public void query() throws Exception {
        Query query = new Query();
        query.setLastId(0L);
        query.setLimit(10);
        List<Movie> movies = movieDao.query(query);
        assertTrue(movies.size() > 0);
    }

}
