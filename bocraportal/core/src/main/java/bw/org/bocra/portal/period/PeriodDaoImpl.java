// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.period;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.form.activation.FormActivationRepository;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.period.config.PeriodConfig;
import bw.org.bocra.portal.period.config.PeriodConfigRepository;
import bw.org.bocra.portal.period.config.PeriodConfigVO;
import java.time.LocalDate;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see Period
 */
@Repository("periodDao")
@Transactional
public class PeriodDaoImpl
        extends PeriodDaoBase {

    public PeriodDaoImpl(PeriodConfigRepository periodConfigRepository,
            FormSubmissionRepository formSubmissionRepository,
            FormActivationRepository formActivationRepository,
            PeriodRepository periodRepository) {

        super(periodConfigRepository, formSubmissionRepository, formActivationRepository, periodRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toPeriodVO(
            Period source,
            PeriodVO target) {
        // TODO verify behavior of toPeriodVO
        super.toPeriodVO(source, target);

        if (source.getPeriodConfig() != null && source.getPeriodConfig().getId() != null) {
            PeriodConfigVO config = new PeriodConfigVO();
            config.setId(source.getPeriodConfig().getId());
            config.setPeriodConfigName(source.getPeriodConfig().getPeriodConfigName());
            config.setStartDay(source.getPeriodConfig().getStartDay());
            config.setStartMonth(source.getPeriodConfig().getStartDay());
            config.setRepeat(source.getPeriodConfig().getRepeat());
            config.setRepeatPeriod(source.getPeriodConfig().getRepeatPeriod());
            config.setFinalDay(source.getPeriodConfig().getFinalDay());

            target.setPeriodConfig(config);
        }

        if (source.getNext() != null) {

            PeriodVO next = new PeriodVO();
            next.setId(source.getNext().getId());
            next.setPeriodName(source.getNext().getPeriodName());
            next.setPeriodStart(source.getNext().getPeriodStart());
            next.setPeriodEnd(source.getNext().getPeriodEnd());

            target.setNext(next);
        }

        if (source.getPrevious() != null) {

            PeriodVO prev = new PeriodVO();
            prev.setId(source.getPrevious().getId());
            prev.setPeriodName(source.getPrevious().getPeriodName());
            prev.setPeriodStart(source.getPrevious().getPeriodStart());
            prev.setPeriodEnd(source.getPrevious().getPeriodEnd());

            target.setPrevious(prev);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PeriodVO toPeriodVO(final Period entity) {
        // TODO verify behavior of toPeriodVO
        return super.toPeriodVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Period loadPeriodFromPeriodVO(PeriodVO periodVO) {
        if (periodVO.getId() == null) {
            return Period.Factory.newInstance();
        } else {
            return this.load(periodVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Period periodVOToEntity(PeriodVO periodVO) {
        // TODO verify behavior of periodVOToEntity
        Period entity = this.loadPeriodFromPeriodVO(periodVO);
        this.periodVOToEntity(periodVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void periodVOToEntity(
            PeriodVO source,
            Period target,
            boolean copyIfNull) {
        // TODO verify behavior of periodVOToEntity
        super.periodVOToEntity(source, target, copyIfNull);

        if (source.getPeriodConfig() != null && source.getPeriodConfig().getId() != null) {
            
            try {
                PeriodConfig config = getPeriodConfigDao().load(source.getPeriodConfig().getId());
                target.setPeriodConfig(config);

            } catch (Exception e) {
                throw new IllegalArgumentException(
                    "PeriodDao.periodVOToEntity - 'periodConfig' is invalid."
                );
            }
        } else {
            throw new IllegalArgumentException(
                "PeriodDao.periodVOToEntity - 'periodConfig' or its id can not be null"
            );
        }

        if (source.getNext() != null && source.getNext().getId() != null) {
            try{
                Period next = get(source.getNext().getId());
        
                target.setNext(next);

            } catch (Exception e) {
                throw new IllegalArgumentException(
                    "PeriodDao.periodVOToEntity - 'next' is invalid."
                );
            }
        }

        if (source.getPrevious() != null && source.getPrevious().getId() != null) {
            try{
                Period prev = get(source.getPrevious().getId());

                target.setPrevious(prev); 
            } catch (Exception e) {
                throw new IllegalArgumentException(
                    "PeriodDao.periodVOToEntity - 'previous' is invalid."
                );
            }
        }
    }

    @Override
    protected Collection<Period> handleFindByCriteria(PeriodCriteria criteria) throws Exception {

        Specification<Period> specs = null;
        
        if (criteria.getSearchDate() != null) {
            specs = getPeriodSpecByDate(criteria.getSearchDate());
        }

        if (StringUtils.isNotBlank(criteria.getPeriodName())) {
            if (specs == null) {
                specs = BocraportalSpecifications.<Period, String>findByAttributeContainingIgnoreCase("periodName", criteria.getPeriodName());
            } else {
                specs = specs.and(BocraportalSpecifications.<Period, String>findByAttributeContainingIgnoreCase("periodName", criteria.getPeriodName()));
            }
        }

        return periodRepository.findAll(specs);
    }

    private Specification<Period> getPeriodSpecByDate(LocalDate date) {
        return BocraportalSpecifications.<Period, LocalDate>findByAttributeLessThanEqual("periodStart", date)
            .and(BocraportalSpecifications.<Period, LocalDate>findByAttributeGreaterThanEqual("periodEnd", date));
    }

    public static Specification<Period> findByAttributeLessThan(String attribute, LocalDate attributeValue){
        return (root, cq, cb) -> {
            
            return cb.lessThanOrEqualTo(root.<LocalDate>get(attribute), attributeValue);
        };
    }

    @Override
    protected Collection<Period> handleGetActivePeriods() throws Exception {
        return periodRepository.findAll(getPeriodSpecByDate(LocalDate.now()), Sort.by("periodStart").descending());
    }
}