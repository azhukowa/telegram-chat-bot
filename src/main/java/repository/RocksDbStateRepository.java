package repository;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class RocksDbStateRepository implements IStateRepository {

    private final RocksDB db;

    public RocksDbStateRepository(RocksDB db) {
        this.db = db;
    }

    @Override
    public byte[] get(byte[] key) {
        try {
            return db.get(key);
        } catch (RocksDBException e) {
            throw new StateRepositoryException(e);
        }
    }

    @Override
    public void put(byte[] key, byte[] value) {
        try {
            db.put(key, value);
        } catch (RocksDBException e) {
            throw new StateRepositoryException(e);
        }
    }
}
