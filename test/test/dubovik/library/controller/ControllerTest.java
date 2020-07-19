package test.dubovik.library.controller;

import com.dubovik.library.controller.Controller;
import com.dubovik.library.model.dao.BookListDao;
import com.dubovik.library.model.dao.impl.BookListDaoImpl;
import com.dubovik.library.model.entity.BookList;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ControllerTest {

    @Test
    public void testDoBookCommandAdd() throws DaoException {
        BookList.getInstance().clear();
        String type_command = "ADD_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ");
        command_parameters.put("year", "2012");
        Controller.getInstance().doBookCommand(type_command, command_parameters);
        List<CustomBook> actual = BookList.getInstance().getBooks();
        List<CustomBook> expected = new ArrayList<>();
        CustomBook book = new CustomBook("Дифференциальные уравнения",
                2012, new String[]{"Альсевич", "Мазаник"}, "БГУ");
        expected.add(book);
        boolean a = actual.get(0).equals(expected.get(0));
        Assert.assertTrue(a);
    }

    @Test
    public void testDoBookCommandAddFail() throws DaoException {
        BookList.getInstance().clear();
        String type_command = "ADD_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ");
        command_parameters.put("year", null);
        Controller.getInstance().doBookCommand(type_command, command_parameters);
        List<CustomBook> actual = BookList.getInstance().getBooks();
        int expected_size = 0;
        int actual_size = actual.size();
        Assert.assertEquals(actual_size, expected_size);
    }

    @Test
    public void testDoBookRemoveCommand() throws DaoException {
        BookList.getInstance().clear();
        CustomBook book = new CustomBook("Дифференциальные уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        BookList.getInstance().addBook(book);

        String type_command = "REMOVE_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ");
        command_parameters.put("year", "2012");
        Controller.getInstance().doBookCommand(type_command, command_parameters);
        List<CustomBook> actual = BookList.getInstance().getBooks();
        int expected_size = 0;
        int actual_size = actual.size();
        Assert.assertEquals(actual_size, expected_size);
    }

    @Test
    public void testDoBookRemoveCommandFail() throws DaoException {
        BookList.getInstance().clear();
        CustomBook book = new CustomBook("Дифференциальные уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        BookList.getInstance().addBook(book);

        String type_command = "REMOVE_BOOK";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("title", "Дифференциальные уравнения");
        command_parameters.put("authors", "Альсевич Мазаник");
        command_parameters.put("publishing house", "БГУ1");
        command_parameters.put("year", "2012");
        Controller.getInstance().doBookCommand(type_command, command_parameters);
        List<CustomBook> actual = BookList.getInstance().getBooks();
        int expected_size = 1;
        int actual_size = actual.size();
        Assert.assertEquals(actual_size, expected_size);
    }

    @Test
    public void testDoBookFindCommand() throws DaoException {
        BookList.getInstance().clear();
        CustomBook book = new CustomBook("Дифференциальные уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        BookList.getInstance().addBook(book);

        String type_command = "FIND_BY_TITLE";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("key", "Дифференциальные уравнения");
        Map<String, String> found = Controller.getInstance().doBookCommand(type_command, command_parameters);
        String expected = book.toString();
        String key_of_founded_book = "1";
        String actual = found.get(key_of_founded_book);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDoBookFindByAuthorCommand() throws DaoException {
        BookList.getInstance().clear();
        CustomBook book = new CustomBook("Дифференциальные уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        BookList.getInstance().addBook(book);

        String type_command = "FIND_BY_AUTHOR";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("key", "Альсевич");
        Map<String, String> found = Controller.getInstance().doBookCommand(type_command, command_parameters);
        String expected = book.toString();
        String key_of_founded_book = "1";
        String actual = found.get(key_of_founded_book);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDoBookFindCommandFail() throws DaoException {
        BookList.getInstance().clear();
        CustomBook book = new CustomBook("Дифференциальные уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        BookList.getInstance().addBook(book);

        String type_command = "FIND_BY_TITLE";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("key", null);
        Map<String, String> found = Controller.getInstance().doBookCommand(type_command, command_parameters);
        String expected = null;
        String key_of_founded_book = "1";
        String actual = found.get(key_of_founded_book);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDoBookSortCommand() throws DaoException {
        BookList.getInstance().clear();
        CustomBook book1 = (new CustomBook("Дифференциальные уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ"));
        CustomBook book2 = new CustomBook("Алгебраические уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        CustomBook book3 = new CustomBook("Геометрические уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        CustomBook book4 = new CustomBook("Ряды Фурье", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        CustomBook book5 = new CustomBook("Векторные уравнения", 2012,
                new String[]{"Альсевич", "Мазаник"}, "БГУ");
        BookList.getInstance().addBook(book1);
        BookList.getInstance().addBook(book2);
        BookList.getInstance().addBook(book3);
        BookList.getInstance().addBook(book4);
        BookList.getInstance().addBook(book5);

        String type_command = "SORT_BY_TITLE";
        Map<String, String> command_parameters = new HashMap<>();
        command_parameters.put("key", null);
        Map<String, String> actual = Controller.getInstance().doBookCommand(type_command, command_parameters);
        Map<String, String> expected = new HashMap<>();
        expected.put("1", book2.toString());
        expected.put("2", book5.toString());
        expected.put("3", book3.toString());
        expected.put("4", book1.toString());
        expected.put("5", book4.toString());
        Assert.assertEquals(actual, expected);
    }
}