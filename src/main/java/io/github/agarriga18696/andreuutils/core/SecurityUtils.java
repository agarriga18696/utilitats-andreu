package io.github.agarriga18696.andreuutils.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HexFormat;

/**
 * Utility class for security-related operations.
 *
 * @author Andreu
 * @version 1.0
 */
public final class SecurityUtils {

    private SecurityUtils() {
        /* This utility class should not be instantiated */
    }

    //-------------------------------
    // HASHING
    //-------------------------------

    /**
     * Returns the SHA-256 hash of a password as a hexadecimal string.
     * <p>
     * The {@code char[]} is converted directly to bytes without creating
     * an intermediate {@link String}, reducing the time the password remains
     * in memory in an immutable form.
     * <p>
     * Intermediate byte arrays are cleared after the hash is calculated.
     * The caller should also clear the original password array when it is
     * no longer needed:
     *
     * <pre>
     * Arrays.fill(password, '\0');
     * </pre>
     *
     * @param password Password as a {@code char[]}.
     * @return The SHA-256 hash as a 64-character hexadecimal string.
     */
    public static String hashSha256(char[] password) {
        byte[] bytes = new byte[password.length * 2];

        for (int index = 0; index < password.length; index++) {
            bytes[index * 2] = (byte) (password[index] >> 8);
            bytes[index * 2 + 1] = (byte) password[index];
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(bytes);

            try {
                return HexFormat.of().formatHex(hash);
            } finally {
                Arrays.fill(hash, (byte) 0);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not available.", e);
        } finally {
            Arrays.fill(bytes, (byte) 0);
        }
    }

}