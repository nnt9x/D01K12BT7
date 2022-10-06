package com.example.d01k12bt7.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.d01k12bt7.model.Contact;

@Database(entities = {Contact.class}, version =  1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();
}
