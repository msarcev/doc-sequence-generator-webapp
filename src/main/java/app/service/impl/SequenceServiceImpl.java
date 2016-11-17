package app.service.impl;

import app.model.Sequence;
import app.dao.repository.SequenceRepository;
import app.mybatis.mappers.SequencesMapper;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SequencesMapper sequencesMapper;

    @Autowired
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
        return sequencesMapper.findOne(id);
    }

    @Override
    public Sequence getLastSequence() {
        List<Sequence> list = getAll();
        if (list.size() > 0){
            return list.get(list.size()-1);
        } else {
            return null;
        }
    }

    @Override
    public List<Sequence> getAll(){

        Iterable<Sequence> allSequences = sequencesMapper.findAll();
        List<Sequence> list = new LinkedList<>();

        for (Sequence sequence : allSequences){
            sequence.setFormattedsequence();
            list.add(sequence);
        }

        return list;
    }

}
