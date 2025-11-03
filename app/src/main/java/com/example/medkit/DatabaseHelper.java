package com.example.medkit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MedicineKit.db";
    private static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // поддержка внешних ключей
        db.execSQL("PRAGMA foreign_keys = ON;");

        // создание таблиц без внешних ключей
        createCategoriesTable(db);
        createFormsTable(db);
        createDosageUnitsTable(db);
        createCountriesTable(db);
        createMediaTypesTable(db);
        createTakingTimesTable(db);
        createScheduleTypesTable(db);
        createConsumersTable(db);

        //  таблицы с внешними ключами
        createMedicinesTable(db);
        createMedicineMediaTable(db);
        createMedicationCoursesTable(db);
        createScheduledIntakesTable(db);
        createNotificationsTable(db);
        createActualIntakesTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // удаление таблиц в обратном порядке создания
        db.execSQL("DROP TABLE IF EXISTS ActualIntakes");
        db.execSQL("DROP TABLE IF EXISTS Notifications");
        db.execSQL("DROP TABLE IF EXISTS ScheduledIntakes");
        db.execSQL("DROP TABLE IF EXISTS MedicationCourses");
        db.execSQL("DROP TABLE IF EXISTS MedicineMedia");
        db.execSQL("DROP TABLE IF EXISTS Medicines");
        db.execSQL("DROP TABLE IF EXISTS Consumers");
        db.execSQL("DROP TABLE IF EXISTS ScheduleTypes");
        db.execSQL("DROP TABLE IF EXISTS TakingTimes");
        db.execSQL("DROP TABLE IF EXISTS MediaTypes");
        db.execSQL("DROP TABLE IF EXISTS Countries");
        db.execSQL("DROP TABLE IF EXISTS DosageUnits");
        db.execSQL("DROP TABLE IF EXISTS Forms");
        db.execSQL("DROP TABLE IF EXISTS Categories");

        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Методы создания таблиц
    private void createCategoriesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Categories (" +
                "CategoryID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Note TEXT" +
                ");";
        db.execSQL(sql);
    }

    private void createFormsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Forms (" +
                "FormID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createDosageUnitsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE DosageUnits (" +
                "DosageUnitID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createCountriesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Countries (" +
                "CountryID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createMediaTypesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE MediaTypes (" +
                "MediaTypeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createTakingTimesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE TakingTimes (" +
                "TakingTimeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Title TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createScheduleTypesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE ScheduleTypes (" +
                "ScheduleTypeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createConsumersTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Consumers (" +
                "ConsumerID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createMedicinesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Medicines (" +
                "MedicineID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "CategoryID INTEGER," +
                "FormID INTEGER NOT NULL," +
                "DosageUnitID INTEGER NOT NULL," +
                "CountryID INTEGER," +
                "DosageValue REAL," +
                "CurrentCount INTEGER," +
                "Description TEXT," +
                "ImagePath TEXT NOT NULL," +
                "ColorCodeHEX TEXT NOT NULL," +
                "ProductionDate DATE," +
                "ExpirationDate DATE," +
                "IsActive BOOLEAN NOT NULL DEFAULT 1," +
                "FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID) ON DELETE SET NULL," +
                "FOREIGN KEY (FormID) REFERENCES Forms(FormID) ON DELETE RESTRICT," +
                "FOREIGN KEY (DosageUnitID) REFERENCES DosageUnits(DosageUnitID) ON DELETE RESTRICT," +
                "FOREIGN KEY (CountryID) REFERENCES Countries(CountryID) ON DELETE SET NULL" +
                ");";
        db.execSQL(sql);
    }

    private void createMedicineMediaTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE MedicineMedia (" +
                "MediaID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MedicineID INTEGER NOT NULL," +
                "MediaTypeID INTEGER NOT NULL," +
                "FilePath TEXT NOT NULL," +
                "UploadDate DATETIME NOT NULL," +
                "FOREIGN KEY (MedicineID) REFERENCES Medicines(MedicineID) ON DELETE CASCADE," +
                "FOREIGN KEY (MediaTypeID) REFERENCES MediaTypes(MediaTypeID) ON DELETE RESTRICT" +
                ");";
        db.execSQL(sql);
    }

    private void createMedicationCoursesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE MedicationCourses (" +
                "CourseID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MedicineID INTEGER NOT NULL," +
                "StartDate DATE NOT NULL," +
                "DurationDays INTEGER NOT NULL," +
                "TakingTimeID INTEGER," +
                "MinutesBefore INTEGER," +
                "ScheduleTypeID INTEGER NOT NULL," +
                "ScheduleValue TEXT NOT NULL," +
                "Comment TEXT," +
                "ConsumerID INTEGER NOT NULL," +
                "IsActive BOOLEAN NOT NULL DEFAULT 1," +
                "FOREIGN KEY (MedicineID) REFERENCES Medicines(MedicineID) ON DELETE CASCADE," +
                "FOREIGN KEY (TakingTimeID) REFERENCES TakingTimes(TakingTimeID) ON DELETE SET NULL," +
                "FOREIGN KEY (ScheduleTypeID) REFERENCES ScheduleTypes(ScheduleTypeID) ON DELETE RESTRICT," +
                "FOREIGN KEY (ConsumerID) REFERENCES Consumers(ConsumerID) ON DELETE RESTRICT" +
                ");";
        db.execSQL(sql);
    }

    private void createScheduledIntakesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE ScheduledIntakes (" +
                "ScheduledIntakeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CourseID INTEGER NOT NULL," +
                "ScheduledDateTime DATETIME NOT NULL," +
                "Dosage REAL NOT NULL," +
                "Status TEXT NOT NULL," +
                "FOREIGN KEY (CourseID) REFERENCES MedicationCourses(CourseID) ON DELETE CASCADE" +
                ");";
        db.execSQL(sql);
    }

    private void createNotificationsTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE Notifications (" +
                "NotificationID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ScheduledIntakeID INTEGER NOT NULL," +
                "NotificationDateTime DATETIME NOT NULL," +
                "NotificationSettingID INTEGER NOT NULL," +
                "Status TEXT NOT NULL," +
                "SentDateTime DATETIME," +
                "DeliveryConfirmation DATETIME," +
                "FOREIGN KEY (ScheduledIntakeID) REFERENCES ScheduledIntakes(ScheduledIntakeID) ON DELETE CASCADE" +
                ");";
        db.execSQL(sql);
    }

    private void createActualIntakesTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE ActualIntakes (" +
                "ActualIntakeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ScheduledIntakeID INTEGER," +
                "ConsumerID INTEGER NOT NULL," +
                "MedicineID INTEGER NOT NULL," +
                "ActualDateTime DATETIME NOT NULL," +
                "ActualDosage REAL," +
                "DateTimeWriteRecord DATETIME NOT NULL," +
                "FOREIGN KEY (ScheduledIntakeID) REFERENCES ScheduledIntakes(ScheduledIntakeID) ON DELETE SET NULL," +
                "FOREIGN KEY (ConsumerID) REFERENCES Consumers(ConsumerID) ON DELETE RESTRICT," +
                "FOREIGN KEY (MedicineID) REFERENCES Medicines(MedicineID) ON DELETE RESTRICT" +
                ");";
        db.execSQL(sql);
    }
}