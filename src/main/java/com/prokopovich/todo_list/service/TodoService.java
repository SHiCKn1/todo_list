package com.prokopovich.todo_list.service;

import com.prokopovich.todo_list.entity.TodoEntity;
import com.prokopovich.todo_list.entity.UserEntity;
import com.prokopovich.todo_list.model.Todo;
import com.prokopovich.todo_list.model.User;
import com.prokopovich.todo_list.repository.TodoRepo;
import com.prokopovich.todo_list.repository.UserRepo;
import exception.TodoNotFoundException;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public Todo createTodo(TodoEntity todo, Long userId) {
        UserEntity user = userRepo.findById(userId).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo completeTodo(Long id) {
        TodoEntity todo = todoRepo.findById(id).get();
        todo.setCompleted(!(todo.getCompleted()));
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo getOne(Long id) throws TodoNotFoundException {
        TodoEntity todo = todoRepo.findById(id).get();
        if(todo == null) {
            throw new TodoNotFoundException("Task not found");
        }
        return Todo.toModel(todo);
    }


}
