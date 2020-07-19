package com.dubovik.library.service;

import com.dubovik.library.model.dao.impl.BookListDaoImpl;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;
import com.dubovik.library.model.exception.ServiceException;

import java.util.List;

public class BookService {

    private static BookService instance = new BookService();

    public static BookService getInstance() {
        return instance;
    }

    private BookService(){
    }

    public void addBook(CustomBook add_book) throws ServiceException {
        if(add_book == null){
            throw new ServiceException("Add book is null");
        }
        try{
            BookListDaoImpl.getInstance().addBook(add_book);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public void removeBook(CustomBook replace_book) throws ServiceException {
        if(replace_book == null){
            throw new ServiceException("Replace book is null");
        }
        try{
            BookListDaoImpl.getInstance().removeBook(replace_book);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<CustomBook> findByTitle(String title) throws ServiceException{
        if(title == null){
            throw new ServiceException("Find title string is null");
        }
        List<CustomBook> books_by_title = BookListDaoImpl.getInstance().findByTitle(title);
        return books_by_title;
    }

    public List<CustomBook> findByAuthor(String author) throws ServiceException{
        if(author == null){
            throw new ServiceException("Find author string is null");
        }
        List<CustomBook> books_by_author = BookListDaoImpl.getInstance().findByAuthor(author);
        return books_by_author;
    }

    public List<CustomBook> sortByTitle() throws ServiceException{
        List<CustomBook> books_sorted_by_title = BookListDaoImpl.getInstance().sortByTitle();
        return books_sorted_by_title;
    }

    public List<CustomBook> sortByYear() throws ServiceException{
        List<CustomBook> books_sorted_by_year = BookListDaoImpl.getInstance().sortByYear();
        return books_sorted_by_year;
    }
}
