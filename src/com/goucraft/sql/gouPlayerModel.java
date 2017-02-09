package com.goucraft.sql;

import code.nextgen.sqlibrary.database.utils.Result;
import code.nextgen.sqlibrary.model.Model;
import code.nextgen.sqlibrary.model.ModelFactory;
import code.nextgen.sqlibrary.model.annotation.Fillable;
import code.nextgen.sqlibrary.model.annotation.PrimaryKey;
import code.nextgen.sqlibrary.model.annotation.Schema;
import code.nextgen.sqlibrary.model.annotation.UniqueKey;
import code.nextgen.sqlibrary.model.common.UUIDFormat;

import java.util.UUID;

/**
 * Created by Administrator on 2017/2/6 0006.
 */
public class gouPlayerModel extends Model {

    @PrimaryKey(name = "pk_player")
    @Fillable(column = "player_id", sortingIndex = 1)
    @Schema(attributes = "INT NOT NULL AUTO_INCREMENT")
    public Integer playerID;

    @Fillable(column = "player_name", sortingIndex = 2)
    @Schema(attributes = "VARCHAR(255)")
    public String playerName;

    @UniqueKey(name = "uk_player_uuid")
    @Fillable(column = "player_uuid", sortingIndex = 3, formatField = UUIDFormat.class)
    @Schema(attributes = "VARCHAR(50) NOT NULL")
    public UUID playerUUID;

    @Fillable(column = "player_sex", sortingIndex = 4)
    @Schema(attributes = "INT NOT NULL DEFAULT '0'")
    public Integer playerSex;

    @Fillable(column = "player_marry", sortingIndex = 5)
    @Schema(attributes = "INT NOT NULL DEFAULT '0'")
    public Integer playerMarry;

    @Fillable(column = "player_marry_name", sortingIndex = 6)
    @Schema(attributes = "VARCHAR(255) DEFAULT NULL")
    public String playerMarryName;

    @Fillable(column = "player_marry_num_1", sortingIndex = 7)
    @Schema(attributes = "INT NOT NULL DEFAULT '0'")
    public Integer playerMarryNum1;

    @Fillable(column = "player_marry_num_2", sortingIndex = 8)
    @Schema(attributes = "INT NOT NULL DEFAULT '0'")
    public Integer playerMarryNum2;


    public gouPlayerModel(ModelFactory<Model> model, Result result) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
        super(model, result);
    }
}
