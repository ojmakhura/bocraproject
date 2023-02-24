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
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.config.SystemConfigService
 */
@Service("systemConfigService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class SystemConfigServiceImpl
    extends SystemConfigServiceBase
{
    public SystemConfigServiceImpl(
        SystemConfigDao systemConfig,
        SystemConfigRepository systemConfigRepository,
        MessageSource messageSource
    ) {
        
        super(
            systemConfig,
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
        // TODO implement protected  SystemConfigVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.config.SystemConfigService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#save(SystemConfigVO)
     */
    @Override
    protected SystemConfigVO handleSave(SystemConfigVO systemConfig)
        throws Exception
    {
        // TODO implement protected  SystemConfigVO handleSave(SystemConfigVO systemConfig)
        throw new UnsupportedOperationException("bw.org.bocra.portal.config.SystemConfigService.handleSave(SystemConfigVO systemConfig) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.config.SystemConfigService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#getAll()
     */
    @Override
    protected Collection<SystemConfigVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<SystemConfigVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.config.SystemConfigService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#search(String)
     */
    @Override
    protected Collection<SystemConfigVO> handleSearch(String criteria)
        throws Exception
    {
        // TODO implement protected  Collection<SystemConfigVO> handleSearch(String criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.config.SystemConfigService.handleSearch(String criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.config.SystemConfigService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<SystemConfigVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Collection<SystemConfigVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.org.bocra.portal.config.SystemConfigService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

}