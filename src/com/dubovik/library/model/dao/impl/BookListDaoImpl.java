package com.dubovik.library.model.dao.impl;

import com.dubovik.library.model.dao.BookListDao;
import com.dubovik.library.model.entity.BookList;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class BookListDaoImpl implements BookListDao {
    
    private static BookListDaoImpl instance = new BookListDaoImpl();

    public static BookListDaoImpl getInstance() {
        return instance;
    }

    private BookListDaoImpl(){
    }

    public void addBook(CustomBook add_book) throws DaoException {
        List<CustomBook> buffer = BookList.getInstance().getBooks();
        int index = -1;
        for(int i = 0; i < buffer.size(); i++){
            if(buffer.get(i).equals(add_book)){
                index = i;
                break;
            }
        }
        if(index == -1){
            if(!BookList.getInstance().addBook(add_book)){
                throw new DaoException("Book list is full");
            }
        }else{
            throw new DaoException("Already have this book");
        }
    }

    public void removeBook(CustomBook replace_book) throws DaoException {
        List<CustomBook> buffer = BookList.getInstance().getBooks();
        if(buffer == null){
            throw new DaoException("Book list is null");
        }
        int index = -1;
        for(int i = 0; i < buffer.size(); i++){
            if(buffer.get(i).equals(replace_book)){
                index = i;
                break;
            }
        }
        if(index != -1){
            BookList.getInstance().deleteBook(index);
        }else{
            throw new DaoException("No such book found");
        }
    }

    public List<CustomBook> findByTitle(String title) {
        List<CustomBook> books = BookList.getInstance().getBooks();
        List<CustomBook> found = new ArrayList<>();
        for(CustomBook el : books){
            if(el.getTitle().equals(title)){
                found.add(el);
            }
        }
        return found;
    }

    public List<CustomBook> findByAuthor(String author) {
        List<CustomBook> books = BookList.getInstance().getBooks();
        List<CustomBook> found = new ArrayList<>();
        for(CustomBook el : books){
            for(String current_author : el.getAuthors())
            {
                if(current_author.equals(author)){
                    found.add(el);
                }
            }
        }
        return found;
    }

    public List<CustomBook> sortByTitle() {
        List<CustomBook> books = new ArrayList<>(BookList.getInstance().getBooks());
        books.sort(Comparator.comparing(CustomBook::getTitle));
        return books;
    }

    public List<CustomBook> sortByYear() {
        List<CustomBook> books = new ArrayList<>(BookList.getInstance().getBooks());
        books.sort(Comparator.comparing(CustomBook::getYear));
        return books;
    }
}
