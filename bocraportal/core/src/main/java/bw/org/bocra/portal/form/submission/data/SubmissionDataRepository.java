package bw.org.bocra.portal.form.submission.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubmissionDataRepository extends 
                                        JpaRepository<DataField, Long>,
                                        JpaSpecificationExecutor<DataField> {

    
    Page<DataField> findByFormSubmissionIdOrderByRowAscFormFieldPositionAsc(Long id, Pageable pageable);
}
