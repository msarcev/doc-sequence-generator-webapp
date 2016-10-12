package app.service;

import app.model.Sequence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SequenceService {

    Page<Sequence> findAllPageable(Pageable pageable);

    Iterable<Sequence> saveSequences(Iterable<Sequence> persons);

    Sequence findById(int id);

    Sequence getLastSequence();

    Sequence filterSequence(String filter);
}
