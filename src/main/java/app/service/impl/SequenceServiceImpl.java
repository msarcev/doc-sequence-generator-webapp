package app.service.impl;

import app.model.Sequence;
import app.dao.repository.SequenceRepository;
import app.mybatis.mappers.SequencesMapper;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service("sequenceService")
public class SequenceServiceImpl implements SequenceService{

    private SequenceRepository sequenceRepository;
    private SequencesMapper sequencesMapper;

    @Autowired
    public SequenceServiceImpl(SequenceRepository sequenceRepository, SequencesMapper sequencesMapper){
        this.sequenceRepository = sequenceRepository;
        this.sequencesMapper = sequencesMapper;
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
    @Transactional
    public Sequence findById(int id) {
        return sequencesMapper.findOne(id);
    }

    @Override
    @Transactional
    public int getLastSequenceId() {
        return sequencesMapper.getLast();
    }

    @Override
    @Transactional
    public int countSequences() {
        return sequencesMapper.getElementsCount();
    }

    @Override
    @Transactional
    public List<Sequence> getAll(){
        return sequencesMapper.findAll();
    }

    public Sequence generateNewSequence() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Sequence sequence = new Sequence(auth.getName(), null, null);

        int count = countSequences();
        int last = getLastSequenceId();

        if (count == 0) {
            sequence.setId(last);
        } else {
            sequence.setId(++last);
        }

        return sequence;
    }
}
