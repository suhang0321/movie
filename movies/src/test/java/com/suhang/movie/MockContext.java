package com.suhang.movie;

import com.suhang.movie.model.Favorite;
import com.suhang.movie.model.Movie;
import com.suhang.movie.model.User;
import com.suhang.movie.util.PasswordUtil;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:49
 */
public abstract class MockContext extends MockBase {

    protected Favorite mockFavorite() {
        Favorite favorite = new Favorite();
        favorite.setMovieId(mockLong());
        favorite.setUserId(mockLong());
        return favorite;
    }

    protected Movie mockMovie() {
        return Movie.builder().name(mockStr(6))
            .description(mockStr(140))
            .build();
    }

    protected User mockUser() {
        User user = User.builder()
            .username("user_" + mockStr(6))
            .password("123")
            .build();
        PasswordUtil.encryptPassword(user);
        return user;
    }
}
