package repository;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.nio.charset.StandardCharsets;

public interface StateRepository {
    byte[] get(byte[] key);

    void put(byte[] key, byte[] value);

    default byte[] get(long key) {
        return get(String.valueOf(key).getBytes(StandardCharsets.UTF_8));
    }

    default void put(long key, byte[] value) {
        put(String.valueOf(key).getBytes(StandardCharsets.UTF_8), value);
    }

    default void put(long key, String value) {
        put(key, value.getBytes(StandardCharsets.UTF_8));
    }

    class StateRepositoryException extends RuntimeException {
        public StateRepositoryException(Throwable cause) {
            super(cause);
        }
    }
}
