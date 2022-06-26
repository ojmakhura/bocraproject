// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::authorisation::AuthorisationService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.auth;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.authorisation.AuthorisationService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("authorisationService")
public class AuthorisationServiceImpl
    extends AuthorisationServiceBase
{

    /**
     * @see bw.org.bocra.portal.authorisation.AuthorisationService#findById(Long)
     */
    @Override
    protected  AuthorisationVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        Authorisation authorisation = this.authorisationRepository.getById(id);
        return authorisationDao.toAuthorisationVO(authorisation);
    }

    /**
     * @see bw.org.bocra.portal.authorisation.AuthorisationService#save(AuthorisationVO)
     */
    @Override
    protected  AuthorisationVO handleSave(AuthorisationVO authorisationVO)
        throws Exception
    {
        Authorisation entity = authorisationDao.authorisationVOToEntity(authorisationVO);

        if(authorisationVO.getId() != null) {

            entity = authorisationDao.create(entity);
            return authorisationDao.toAuthorisationVO(entity);

        } else {
            authorisationDao.update(entity);
        }

        return authorisationDao.toAuthorisationVO(entity);
    }

    /**
     * @see bw.org.bocra.portal.authorisation.AuthorisationService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        if(id == null) {
            return false;
        }

        this.authorisationRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.authorisation.AuthorisationService#getAll()
     */
    @Override
    protected  Collection<AuthorisationVO> handleGetAll()
        throws Exception
    {

        return (Collection<AuthorisationVO>) authorisationDao.loadAll(AuthorisationDao.TRANSFORM_AUTHORISATIONVO);
    }

    /**
     * @see bw.org.bocra.portal.authorisation.AuthorisationService#search(AuthorisationCriteria)
     */
    @Override
    protected  Collection<AuthorisationVO> handleSearch(AuthorisationCriteria criteria)
        throws Exception
    {
        return (Collection<AuthorisationVO>) this.authorisationDao.findByCriteria(AuthorisationDao.TRANSFORM_AUTHORISATIONVO, criteria);
    }

    @Override
    protected Collection<AuthorisationVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        Collection<Authorisation> authorisations = null;

        if(pageNumber < 0 || pageSize < 1) {
            authorisations = authorisationRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("url").descending());
            authorisations = authorisationRepository.findAll(pageable).getContent();
        }

        return authorisations == null ? null : authorisationDao.toAuthorisationVOCollection(authorisations);
    }

    @Override
    protected Collection<AuthorisationVO> handleGetAccessTypeCodeAuthorisations(Set<String> roles,
            String accessPointTypeCode) throws Exception {
        
        return (Collection<AuthorisationVO>) this.authorisationDao.findAccessTypeCodeAuthorisations(AuthorisationDao.TRANSFORM_AUTHORISATIONVO, roles, accessPointTypeCode);
    }

}