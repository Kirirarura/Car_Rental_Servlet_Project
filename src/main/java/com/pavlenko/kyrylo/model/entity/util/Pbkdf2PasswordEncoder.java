package com.pavlenko.kyrylo.model.entity.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Pbkdf2PasswordEncoder implements PasswordEncoder {

    private static final Logger logger = LogManager.getLogger(Pbkdf2PasswordEncoder.class);

    private static final String ALGORITHM_NAME = "PBKDF2WithHmacSHA1";
    private static final byte[] SALT = {3, 12, 5, 9, 35, 4, 4, 38};
    private static final int ITERATION = 4096;
    private static final int KEY_LENGTH = 128;


    /**
     * Performs encoding of password with using of PBKDF2WithHmacSHA1 algorithm.
     *
     * @param password String password that must be encoded.
     * @return Encoded String password.
     */
    @Override
    public String encode(String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT, ITERATION, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM_NAME);
            return Hex.encodeHexString(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException  e) {
            logger.error("Error during encoding password;");
            throw new IllegalArgumentException(e.getMessage(), e.getCause());
        }
    }
}
