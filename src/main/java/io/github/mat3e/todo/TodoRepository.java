package io.github.mat3e.lang;

import io.github.mat3e.HibernateUtil;
import io.github.mat3e.todo.Todo;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoRepository {
    private final Logger logger = LoggerFactory.getLogger(TodoRepository.class);

    public List<Todo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Todo", Todo.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    public Todo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var todo = session.get(Todo.class, id);
        todo.setDone(!todo.getDone());
        session.update(todo);

        transaction.commit();
        session.close();
        return todo;
    }

    public Todo addTodo(Todo newTodo) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newTodo);

        transaction.commit();
        session.close();
        return newTodo;
    }
}