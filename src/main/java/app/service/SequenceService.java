package app.service;

import app.model.Sequence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SequenceService {

    Page<Sequence> findAllPageable(Pageable pageable);

    Iterable<Sequence> saveSequences(Iterable<Sequence> persons);

    Sequence findById(int id);

    Sequence getLastSequence();

    Sequence filterSequence(String filter);

    List<Sequence> getAll();
}
