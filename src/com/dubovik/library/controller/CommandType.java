package com.dubovik.library.controller;

import com.dubovik.library.controller.impl.*;

public enum CommandType {

    ADD_BOOK(new AddBookCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    FIND_BY_TITLE(new FindByTitleCommand()),
    FIND_BY_AUTHOR(new FindByAuthorCommand()),
    SORT_BY_TITLE(new SortByTitleCommand()),
    SORT_BY_YEAR(new SortByYearCommand());

    private ActionCommand command;
    CommandType(ActionCommand in_command){
        command = in_command;
    }

    public ActionCommand getCommand(){
        return command;
    }
}
