// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::menu::MenuSectionService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.menu;

import bw.org.bocra.portal.auth.AuthorisationDao;
import bw.org.bocra.portal.auth.AuthorisationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.menu.MenuSectionService
 */
@Service("menuSectionService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class MenuSectionServiceImpl
    extends MenuSectionServiceBase
{
    public MenuSectionServiceImpl(
        MenuSectionDao menuSection,
        MenuSectionRepository menuSectionRepository,
        AuthorisationDao authorisation,
        AuthorisationRepository authorisationRepository,
        MessageSource messageSource
    ) {
        
        super(
            menuSection,
            menuSectionRepository,
            authorisation,
            authorisationRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.menu.MenuSectionService#save(MenuSectionVO)
     */
    @Override
    protected MenuSectionVO handleSave(MenuSectionVO menuSection)
        throws Exception
    {
        MenuSection section = getMenuSectionDao().menuSectionVOToEntity(menuSection);
        section = getMenuSectionRepository().save(section);

        return getMenuSectionDao().toMenuSectionVO(section);
    }

    /**
     * @see bw.org.bocra.portal.menu.MenuSectionService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<MenuSectionVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<MenuSectionVO>) menuSectionDao.loadAll(MenuSectionDao.TRANSFORM_MENUSECTIONVO, pageNumber, pageSize);
    }

    /**
     * @see bw.org.bocra.portal.menu.MenuSectionService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        menuSectionRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.menu.MenuSectionService#getAll()
     */
    @Override
    protected Collection<MenuSectionVO> handleGetAll()
        throws Exception
    {

        return getValueObjectCollections(menuSectionDao.loadAll());
    }

    /**
     * @see bw.org.bocra.portal.menu.MenuSectionService#search(String)
     */
    @Override
    protected Collection<MenuSectionVO> handleSearch(String criteria)
        throws Exception
    {
        Specification<MenuSection> spec = null;

        if(StringUtils.isNotBlank(criteria)) {
            spec = MenuSectionSpecifications.findByDisplayIdContainingIgnoreCase(criteria)
                    .or(MenuSectionSpecifications.findByDisplayNameContainingIgnoreCase(criteria));
        }

        Collection<MenuSection> sections = getMenuSectionRepository().findAll(spec, Sort.by(Direction.ASC, "position"));

        return getValueObjectCollections(sections);
    }

    /**
     * @see bw.org.bocra.portal.menu.MenuSectionService#findById(Long)
     */
    @Override
    protected MenuSectionVO handleFindById(Long id)
        throws Exception
    {
        MenuSection section = menuSectionDao.get(id);
        return getMenuSectionDao().toMenuSectionVO(section);
    }

    @Override
    protected Collection<MenuSectionVO> handleFindByAuthorisationRoles(Set<String> roles) throws Exception {
        
        Collection<MenuSection> sections = menuSectionRepository.findByAutorisationRoles(roles);
        return getValueObjectCollections(sections);
    }

    private Collection<MenuSectionVO> getValueObjectCollections(Collection<MenuSection> sections) {
        Collection<MenuSectionVO> vos = new ArrayList<>();

        for(MenuSection section : sections) {
            vos.add(getMenuSectionDao().toMenuSectionVO(section));
        }

        return vos;
    }

}