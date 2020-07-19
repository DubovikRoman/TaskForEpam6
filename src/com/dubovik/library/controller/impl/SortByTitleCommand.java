package com.dubovik.library.controller.impl;

import com.dubovik.library.controller.ActionCommand;
import com.dubovik.library.controller.CommandRequestParameters;
import com.dubovik.library.model.entity.CustomBook;
import com.dubovik.library.model.exception.ServiceException;
import com.dubovik.library.service.BookService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByTitleCommand implements ActionCommand {

    public Map<String, String> execute(Map<String, String> command_parameters) {
        Map<String, String> result = new HashMap<>();
        try {
            List<CustomBook> sorted_books = BookService.getInstance().sortByTitle();
            int index_of_book = 1;
            for(CustomBook book : sorted_books){
                result.put(String.valueOf(index_of_book), book.toString());
                index_of_book++;
            }
        } catch (ServiceException e) {
            result.put("status", "Error while sorting books by title");
        }
        return result;
    }
}
