package com.suhang.movie.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.suhang.movie.BaseTestContext;
import com.suhang.movie.model.Favorite;
import com.suhang.movie.model.FavoriteQuery;

/**
 * @author hang.su
 * @since 2017-04-25 下午10:31
 */
public class FavoriteDaoTest extends BaseTestContext {

    @Resource
    private FavoriteDao favoriteDao;

    @Test
    public void create() throws Exception {
        for (int i = 0; i < 2; i++) {
            Favorite favorite = mockFavorite();
            int res = favoriteDao.create(favorite);
            assertTrue(res > 0);
        }
    }

    @Test
    public void delete() throws Exception {
        Favorite favorite = new Favorite();
        favorite.setUserId(2L);
        favorite.setMovieId(2L);
        int res = favoriteDao.delete(favorite);
        assertTrue(res > 0);
    }

    @Test
    public void findById() throws Exception {
        Favorite query = new Favorite();
        query.setUserId(1L);
        query.setMovieId(1L);
        Favorite favorite = favoriteDao.findById(query);
        assertNotNull(favorite);
    }

    @Test
    public void findByUserId() throws Exception {
        List<Favorite> favorites = favoriteDao.findByUserId(1L);
        assertEquals(2, favorites.size());
    }

    @Test
    public void findByMovieId() throws Exception {
        List<Favorite> favorites = favoriteDao.findByMovieId(1L);
        assertEquals(2, favorites.size());
    }

    @Test
    public void query() throws Exception {
        FavoriteQuery query = new FavoriteQuery();
        query.setLastId(0L);
        query.setLimit(10);
        query.setUserId(1L);
        query.setMovieId(1L);
        List<Favorite> favorites = favoriteDao.query(query);
        assertEquals(2, favorites.size());
        query.setUserId(null);
        query.setMovieId(1L);
        favorites = favoriteDao.query(query);
        assertEquals(2, favorites.size());
        query.setMovieId(null);
        favorites = favoriteDao.query(query);
        assertEquals(4, favorites.size());
    }


}
