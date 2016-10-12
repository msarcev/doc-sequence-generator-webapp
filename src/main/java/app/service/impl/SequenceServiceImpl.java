package app.service.impl;

import app.model.Sequence;
import app.repository.SequenceRepository;
import app.service.SequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sequenceService")
@Transactional
public class SequenceServiceImpl implements SequenceService{

    private SequenceRepository sequenceRepository;

    public SequenceServiceImpl(SequenceRepository sequenceRepository){
        this.sequenceRepository = sequenceRepository;
    }

    @Transactional
    @Override
    public Page<Sequence> findAllPageable(Pageable pageable) {
        return sequenceRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Iterable<Sequence> save(Iterable<Sequence> sequence) {
        return sequenceRepository.save(sequence);
    }

}
