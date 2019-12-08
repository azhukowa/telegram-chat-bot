package repository;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

public class DbFactory {

    public DbFactory() {
        RocksDB.loadLibrary();
    }

    public RocksDB create(String fileName) throws RocksDBException {
        Options options = new Options().setCreateIfMissing(true);
        return RocksDB.open(options, fileName);

    }
}
