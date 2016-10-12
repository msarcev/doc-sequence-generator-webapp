package app.service.impl;

import app.model.Sequence;
import app.repository.SequenceRepository;
import app.service.SequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

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
    public Iterable<Sequence> saveSequences(Iterable<Sequence> sequence) {
        return sequenceRepository.save(sequence);
    }

    @Override
    public Sequence findById(int id) {
        return sequenceRepository.findOne(id);
    }

    @Override
    public Sequence getLastSequence() {
        Iterable<Sequence> allSequences = sequenceRepository.findAll();
        List<Sequence> list = new LinkedList<>();

        for (Sequence sequence : allSequences){
            list.add(sequence);
        }

        return list.get(list.size()-1);
    }

}
