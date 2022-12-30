// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::complaint::type::ComplaintTypeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.complaint.type;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService
 */
@Service("complaintTypeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ComplaintTypeServiceImpl
        extends ComplaintTypeServiceBase {
    public ComplaintTypeServiceImpl(
            ComplaintTypeDao complaintType,
            ComplaintTypeRepository complaintTypeRepository,
            MessageSource messageSource) {

        super(
                complaintType,
                complaintTypeRepository,
                messageSource);
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#findById(Long)
     */
    @Override
    protected ComplaintTypeVO handleFindById(Long id)
            throws Exception {
        ComplaintType type = getComplaintTypeDao().load(id);
        return getComplaintTypeDao().toComplaintTypeVO(type);
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#save(ComplaintTypeVO)
     */
    @Override
    protected ComplaintTypeVO handleSave(ComplaintTypeVO complaintType)
            throws Exception {
        ComplaintType entity = getComplaintTypeDao().complaintTypeVOToEntity(complaintType);
        entity = complaintTypeRepository.saveAndFlush(entity);

        if (complaintType.getId() == null) {
            complaintType = getComplaintTypeDao().toComplaintTypeVO(entity);
        }

        return complaintType;
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        complaintTypeRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#getAll()
     */
    @Override
    protected Collection<ComplaintTypeVO> handleGetAll()
            throws Exception {
        return (Collection<ComplaintTypeVO>) getComplaintTypeDao().loadAll(ComplaintTypeDao.TRANSFORM_COMPLAINTTYPEVO);
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#search(String)
     */
    @Override
    protected Collection<ComplaintTypeVO> handleSearch(String criteria)
            throws Exception {
        Collection<ComplaintType> types = complaintTypeDao.findByCriteria(criteria);
        Collection<ComplaintTypeVO> vos = new ArrayList<>();

        for (ComplaintType type : types) {
            vos.add(getComplaintTypeDao().toComplaintTypeVO(type));
        }

        return vos;
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#getAll(Integer,
     *      Integer)
     */
    @Override
    protected Collection<ComplaintTypeVO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {
        return (Collection<ComplaintTypeVO>) complaintTypeDao.loadAll(complaintTypeDao.TRANSFORM_COMPLAINTTYPEVO,
                pageNumber, pageSize);
    }

}