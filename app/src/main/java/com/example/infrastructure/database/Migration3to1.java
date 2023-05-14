package com.example.infrastructure.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration3to1 extends Migration {
    public Migration3to1() {
        super(3, 1);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {

    }
}
