package repository;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public interface StateRepository {
    byte[] get(byte[] key);

    void put(byte[] key, byte[] value);

    class StateRepositoryException extends RuntimeException {
        public StateRepositoryException(Throwable cause) {
            super(cause);
        }
    }
}
