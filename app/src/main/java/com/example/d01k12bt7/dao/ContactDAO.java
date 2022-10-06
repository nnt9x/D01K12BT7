package com.example.d01k12bt7.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.d01k12bt7.model.Contact;

import java.util.List;

@Dao
public interface ContactDAO  {
    @Query("SELECT * FROM contacts")
    public List<Contact> getAll();

    @Insert
    public long insert(Contact contact);

    @Delete
    void delete(Contact user);

    @Update
    void update(Contact contact);
}
