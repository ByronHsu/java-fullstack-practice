package io.github.mat3e.lang;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LangService {
    private final Logger logger = LoggerFactory.getLogger(LangServlet.class);

    private final LangRepository repository = new LangRepository();

    List<LangDTO> findAll () {
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(Collectors.toList());
    }
}
