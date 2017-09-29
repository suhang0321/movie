package com.suhang.movie.util;

import static com.suhang.movie.util.CheckUtil.checkArgument;

import org.apache.commons.lang3.StringUtils;

import com.suhang.movie.model.Favorite;
import com.suhang.movie.model.Movie;
import com.suhang.movie.model.Query;
import com.suhang.movie.model.User;

/**
 * @author hang.su
 * @since 2017-04-26 下午8:25
 */
public class Validator {

    public static void checkQuery(Query query) {
        checkArgument(query.lastIdValid(), "last id cannot be null or lt 0");
        checkArgument(query.limitValid(), "limit cannot be null or le 0");
    }

    public static void checkUserId(Long id) {
        checkArgument(idValid(id), "user id cannot be null or le 0");
    }

    public static void checkUsername(User user) {
        checkUsername(user.getUsername());
    }

    public static void checkUsername(String username) {
        checkArgument(StringUtils.isNotBlank(username), "username cannot be blank");
    }

    public static void checkPassword(User user) {
        checkPassword(user.getPassword());
    }

    public static void checkPassword(String password) {
        checkArgument(StringUtils.isNotBlank(password), "password cannot be blank");
    }

    public static void checkMovieId(Long id) {
        checkArgument(idValid(id), "movie id cannot be null or le 0");
    }

    public static void checkMovieName(Movie movie) {
        checkMovieName(movie.getName());
    }

    public static void checkMovieName(String movieName) {
        checkArgument(StringUtils.isNotBlank(movieName), "movie name cannot be blank");
    }

    public static void checkFavoriteId(Favorite favorite) {
        checkArgument(idValid(favorite.getUserId()) && idValid(favorite.getMovieId()), "user id and movie id cannot be null or le 0");
    }

    private static boolean idValid(Long id) {
        return id != null && id > 0L;
    }
}
