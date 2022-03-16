// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::guard::UrlGuardService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.guard;

import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.guard.UrlGuardService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("urlGuardService")
public class UrlGuardServiceImpl
    extends UrlGuardServiceBase
{

    /**
     * @see bw.org.bocra.portal.guard.UrlGuardService#findById(Long)
     */
    @Override
    protected  UrlGuardVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        UrlGuard guard = this.urlGuardRepository.getById(id);
        return urlGuardDao.toUrlGuardVO(guard);
    }

    /**
     * @see bw.org.bocra.portal.guard.UrlGuardService#save(UrlGuardVO)
     */
    @Override
    protected  UrlGuardVO handleSave(UrlGuardVO urlGuardVO)
        throws Exception
    {
        UrlGuard entity = urlGuardDao.urlGuardVOToEntity(urlGuardVO);

        if(urlGuardVO.getId() != null) {

            entity = urlGuardDao.create(entity);
            return urlGuardDao.toUrlGuardVO(entity);

        } else {
            urlGuardDao.update(entity);
        }

        return urlGuardDao.toUrlGuardVO(entity);
    }

    /**
     * @see bw.org.bocra.portal.guard.UrlGuardService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        if(id == null) {
            return false;
        }

        this.urlGuardRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.guard.UrlGuardService#getAll()
     */
    @Override
    protected  Collection<UrlGuardVO> handleGetAll()
        throws Exception
    {

        return (Collection<UrlGuardVO>) urlGuardDao.loadAll(UrlGuardDao.TRANSFORM_URLGUARDVO);
    }

    /**
     * @see bw.org.bocra.portal.guard.UrlGuardService#search(UrlGuardCriteria)
     */
    @Override
    protected  Collection<UrlGuardVO> handleSearch(UrlGuardCriteria criteria)
        throws Exception
    {
        return (Collection<UrlGuardVO>) this.urlGuardDao.findByCriteria(UrlGuardDao.TRANSFORM_URLGUARDVO, criteria);
    }

    @Override
    protected Collection<UrlGuardVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        Collection<UrlGuard> guards = null;

        if(pageNumber < 0 || pageSize < 1) {
            guards = urlGuardRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("url").descending());
            guards = urlGuardRepository.findAll(pageable).getContent();
        }

        return guards == null ? null : urlGuardDao.toUrlGuardVOCollection(guards);
    }

}