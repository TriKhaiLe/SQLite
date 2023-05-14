package com.example.repositories;

import com.example.dao.ClassRoomDao;
import com.example.dao.UserDao;
import com.example.entities.ClassRoom;
import com.example.entities.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ClassRoomRepository {
    ClassRoomDao classRoomDao;

    @Inject
    public ClassRoomRepository(ClassRoomDao classRoomDao) {
        this.classRoomDao = classRoomDao;
    }

    public Flowable<List<ClassRoom>> getAllClassRoom() {
        return classRoomDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
