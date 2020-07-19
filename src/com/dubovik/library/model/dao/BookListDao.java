package com.dubovik.library.model.dao;

import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;
import com.dubovik.library.model.exception.ServiceException;

import java.util.List;

public interface BookListDao {

    void addBook (CustomBook add_book) throws DaoException, ServiceException;
    void removeBook(CustomBook book) throws DaoException, ServiceException;
    List<CustomBook> findByTitle(String title);
    List<CustomBook> findByAuthor(String author);
    List<CustomBook> sortByTitle();
    List<CustomBook> sortByYear();
}

