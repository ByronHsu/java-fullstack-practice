package io.github.mat3e.todo;

import io.github.mat3e.HibernateUtil;
import io.github.mat3e.lang.TodoRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TodoRepositoryTest {
    @Test
    public void test_findAll_has_value() {
        var todoRepository = new TodoRepository();
        var results = todoRepository.findAll();
        System.out.println(Arrays.toString(results.toArray()));
        assertTrue(true);
    }

    @Test
    public void test_toggleTodo() {
        var test_id = 1;
        var todoRepository = new TodoRepository();
        var old_done = todoRepository.findAll().stream().filter(x -> x.id == test_id).map(x->x.done).findFirst().orElse(true);
        var todo = todoRepository.toggleTodo(test_id);
        var new_done = todoRepository.findAll().stream().filter(x -> x.id == test_id).map(x->x.done).findFirst().orElse(true);
        assertEquals(old_done, !new_done);
    }

    @Test
    public void test_addTodo() {
        var todoRepository = new TodoRepository();
        var newTodo = new Todo("just testing", false);
        var oldLen = todoRepository.findAll().size();
        todoRepository.addTodo(newTodo);
        var newLen = todoRepository.findAll().size();
        assertEquals(oldLen + 1, newLen);
    }
}
