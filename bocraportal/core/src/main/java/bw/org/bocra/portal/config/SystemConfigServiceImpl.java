// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::config::SystemConfigService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.config;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;

/**
 * @see bw.org.bocra.portal.config.SystemConfigService
 */
@Service("systemConfigService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class SystemConfigServiceImpl
    extends SystemConfigServiceBase
{
    public SystemConfigServiceImpl(
        SystemConfigDao systemConfigDao,
        SystemConfigRepository systemConfigRepository,
        MessageSource messageSource
    ) {
        
        super(
            systemConfigDao,
            systemConfigRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#findById(Long)
     */
    @Override
    protected SystemConfigVO handleFindById(Long id)
        throws Exception
    {
        return systemConfigDao.toSystemConfigVO(systemConfigRepository.getReferenceById(id));
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#save(SystemConfigVO)
     */
    @Override
    protected SystemConfigVO handleSave(SystemConfigVO systemConfig)
        throws Exception
    {
        SystemConfig config = systemConfigDao.systemConfigVOToEntity(systemConfig);
        return systemConfigDao.toSystemConfigVO(systemConfigRepository.save(config));
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {

        systemConfigRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#getAll()
     */
    @Override
    protected Collection<SystemConfigVO> handleGetAll()
        throws Exception
    {
        return systemConfigDao.toSystemConfigVOCollection(systemConfigRepository.findAll());
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#search(String)
     */
    @Override
    protected Collection<SystemConfigVO> handleSearch(String criteria)
        throws Exception
    {

        if(StringUtils.isAllBlank(null))
            return this.getAll();

        Specification<SystemConfig> specs = BocraportalSpecifications.findByAttributeLikeIgnoreCase("name", criteria);

        return systemConfigDao.toSystemConfigVOCollection(systemConfigRepository.findAll(specs, Sort.by("name").ascending()));
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<SystemConfigVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {

        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("name").ascending());
        return systemConfigDao.toSystemConfigVOCollection(systemConfigRepository.findAll(page).getContent());
    }

    @Override
    protected SystemConfigVO handleFindByName(String name) throws Exception {

        Specification<SystemConfig> specs = BocraportalSpecifications.<SystemConfig, String>findByAttribute("name", name);

        Collection<SystemConfig> configs = systemConfigRepository.findAll(specs, Sort.by("name").ascending());
        if(CollectionUtils.isEmpty(configs)) return null;

        return systemConfigDao.toSystemConfigVO(configs.iterator().next());
    }

    @Override
    protected Collection<SystemConfigVO> handleSaveAll(Set<SystemConfigVO> systemConfigs) throws Exception {

        if(CollectionUtils.isEmpty(systemConfigs)) {
            return CollectionUtils.emptyCollection();
        }
        
        Collection<SystemConfig> configs = systemConfigs.stream().map(systemConfigDao::systemConfigVOToEntity).collect(Collectors.toList());
        return systemConfigDao.toSystemConfigVOCollection(systemConfigRepository.saveAll(configs));
    }

}