// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::authorisation::AuthorisationService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.access.AccessPoint;
import bw.org.bocra.portal.menu.MenuSection;
import bw.org.bocra.portal.menu.MenuSectionDao;
import bw.org.bocra.portal.menu.MenuSectionRepository;

/**
 * @see bw.org.bocra.portal.authorisation.AuthorisationService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("authorisationService")
public class AuthorisationServiceImpl
    extends AuthorisationServiceBase
{

    public AuthorisationServiceImpl(AuthorisationDao authorisationDao, AuthorisationRepository authorisationRepository,
            MenuSectionDao menuSectionDao, MenuSectionRepository menuSectionRepository, MessageSource messageSource) {

        super(authorisationDao, authorisationRepository, menuSectionDao, menuSectionRepository, messageSource);
        //TODO Auto-generated constructor stub
    }

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

        Authorisation authorisation = this.authorisationRepository.getReferenceById(id);
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
            Set<String> accessPointTypeCode) throws Exception {
        
        return (Collection<AuthorisationVO>) this.authorisationDao.findAccessTypeCodeAuthorisations(AuthorisationDao.TRANSFORM_AUTHORISATIONVO, roles, accessPointTypeCode);
    }

    @Override
    protected AuthorisationVO handleAssignMenuSection(Long authorisationId, Long menuSectionId)
            throws Exception {

        Authorisation authorisation = authorisationDao.load(authorisationId);
        MenuSection menuSection = menuSectionDao.load(menuSectionId);

        authorisation.setMenuSection(menuSection);

        authorisation = authorisationRepository.save(authorisation);

        return authorisationDao.toAuthorisationVO(authorisation);
    }

    @Override
    protected Collection<AuthorisationVO> handleFindByRolesAndUrl(String url, Set<String> roles) throws Exception {
        
        Specification<Authorisation> specs = BocraportalSpecifications.<Authorisation, AccessPoint>findByJoinAttributeLike("accessPoint", "url", url);
        specs = specs.and(
            BocraportalSpecifications.<Authorisation, String>findByAttributeIn("roles", roles)
        );

        // Collection<Authorisation> authorisations = authorisationRepository.findByRolesAndUrl(url, roles);
        Collection<Authorisation> authorisations = authorisationRepository.findAll(specs);
        Collection<AuthorisationVO> vos = new ArrayList<>();

        for(Authorisation auth : authorisations) {
            vos.add(authorisationDao.toAuthorisationVO(auth));
        }

        return vos;
    }

}