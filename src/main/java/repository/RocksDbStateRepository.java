package repository;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

<<<<<<< HEAD
public class RocksDbStateRepository implements IStateRepository {
=======
public class RocksDbStateRepository implements StateRepository {
>>>>>>> 24e047ebdea9ddba0781584caaf3a3795619d9db
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
