// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::section::FormSectionService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.section;

import bw.org.bocra.portal.form.FormVO;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.form.section.FormSectionService
 */
@Service("formSectionService")
public class FormSectionServiceImpl
    extends FormSectionServiceBase
{

    /**
     * @see bw.org.bocra.portal.form.section.FormSectionService#findById(Long)
     */
    @Override
    protected  FormSectionVO handleFindById(Long id)
        throws Exception
    {
        return (FormSectionVO) getFormSectionDao().load(FormSectionDao.TRANSFORM_FORMSECTIONVO, id);
    }

    /**
     * @see bw.org.bocra.portal.form.section.FormSectionService#save(FormSectionVO)
     */
    @Override
    protected  FormSectionVO handleSave(FormSectionVO formSection)
        throws Exception
    {
        FormSection section = getFormSectionDao().formSectionVOToEntity(formSection);

        if(formSection.getId() == null) {

        } else {

        }

        return getFormSectionDao().toFormSectionVO(section);
    }

    /**
     * @see bw.org.bocra.portal.form.section.FormSectionService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        formSectionRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.section.FormSectionService#getAll()
     */
    @Override
    protected  Collection<FormSectionVO> handleGetAll()
        throws Exception
    {
        return (Collection<FormSectionVO>) getFormSectionDao().loadAll(FormSectionDao.TRANSFORM_FORMSECTIONVO);
    }

    /**
     * @see bw.org.bocra.portal.form.section.FormSectionService#search(String)
     */
    @Override
    protected  Collection<FormSectionVO> handleSearch(String criteria)
        throws Exception
    {
        Collection<FormSection> sections = getFormSectionDao().findByCriteria(criteria);

        return formSectionDao.toFormSectionVOCollection(sections);
    }

    /**
     * @see bw.org.bocra.portal.form.section.FormSectionService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<FormSectionVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<FormSectionVO>) getFormSectionDao().loadAll(FormSectionDao.TRANSFORM_FORMSECTIONVO, pageNumber, pageSize);
    }

}