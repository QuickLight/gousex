package com.goucraft.sql;

import code.nextgen.sqlibrary.database.DatabaseConnection;
import code.nextgen.sqlibrary.model.ModelFactory;
import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class gouPlayerFactory extends ModelFactory<gouPlayerModel> {
    private static gouPlayerFactory INSTANCE;

    private final DatabaseConnection connection;

    public gouPlayerFactory(DatabaseConnection connection) {
        super(gouPlayerModel.class); //This is required as Generic Types cannot be obtained at runtime
        this.connection = connection;
        gouPlayerFactory.INSTANCE = this;
    }

    @Override
    public String getTable() {
        return "players";
    }

    @Override
    public DatabaseConnection getDatabase() {
        return connection;
    }

    @Override
    public void log(Level level, String message, Throwable thrown) {
        Bukkit.getLogger().log(level, message, thrown);
    }

    public static gouPlayerFactory getInstance() {
        return INSTANCE;
    }

}
