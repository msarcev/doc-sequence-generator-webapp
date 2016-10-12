package app.service;

import app.model.Sequence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SequenceService {

    Page<Sequence> findAllPageable(Pageable pageable);

    Iterable<Sequence> save(Iterable<Sequence> persons);

}
