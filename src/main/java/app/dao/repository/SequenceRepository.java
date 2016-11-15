package app.dao.repository;

import app.model.Sequence;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceRepository extends PagingAndSortingRepository<Sequence,Integer>{ }
