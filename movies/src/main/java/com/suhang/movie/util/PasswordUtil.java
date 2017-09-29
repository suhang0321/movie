package com.suhang.movie.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.suhang.movie.model.User;

/**
 * @author hang.su
 * @since 2017-04-26 下午6:52
 */
public final class PasswordUtil {

    private static final int HASH_ITERATIONS = 10000;
    private static final RandomNumberGenerator RANDOM_NUMBER_GENERATOR = new SecureRandomNumberGenerator();
    private static final String ALGORITHM_NAME = "SHA-256";

    public static void encryptPassword(User user) {
        user.setSalt(RANDOM_NUMBER_GENERATOR.nextBytes().toHex());
        Hash hash = new SimpleHash(
            ALGORITHM_NAME,
            user.getPassword(),
            ByteSource.Util.bytes(user.getCredentialsSalt()),
            HASH_ITERATIONS);
        user.setPassword(hash.toHex());
    }

    public boolean passwordMatches(String plaintext, User user) {
        Hash computed = new SimpleHash(ALGORITHM_NAME,
            plaintext,
            ByteSource.Util.bytes(user.getCredentialsSalt()),
            HASH_ITERATIONS);
        return computed.equals(Sha256Hash.fromHexString(user.getPassword()));
    }
}
