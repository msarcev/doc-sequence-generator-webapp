package app.service;

import app.model.Sequence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface SequenceService {

    Page<Sequence> findAllPageable(Pageable pageable);

    Iterable<Sequence> saveSequences(Iterable<Sequence> persons);

    Sequence findById(int id);

    int getLastSequenceId();

    int countSequences();

    List<Sequence> getAll();

    Sequence generateNewSequence();

}
