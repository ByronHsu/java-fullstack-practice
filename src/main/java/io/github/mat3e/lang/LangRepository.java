package io.github.mat3e.lang;

import io.github.mat3e.HibernateUtil;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LangRepository {
    private final List<Lang> languages;

    public LangRepository() {
        languages = new ArrayList<>();
        languages.add(new Lang(1, "Hello", "en"));
        languages.add(new Lang(2, "Siemanko", "pl"));
    }

    public Optional<Lang> findById(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Lang.class, id);
        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }

    public List<Lang> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Lang", Lang.class).list();

        transaction.commit();
        session.close();
        return result;
    }
}
