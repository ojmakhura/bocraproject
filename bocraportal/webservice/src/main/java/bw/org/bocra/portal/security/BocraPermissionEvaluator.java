package bw.org.bocra.portal.security;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class BocraPermissionEvaluator implements PermissionEvaluator {
    
  protected Logger logger = LoggerFactory.getLogger(PermissionEvaluator.class);

    @Override
    public boolean hasPermission(
      Authentication auth, Object targetDomainObject, Object permission) {
        System.out.println(auth);
        logger.info(String.format("targetType: %s === permission: %s", targetDomainObject.toString(), permission.toString()));
        if ((auth == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        
        return hasPrivilege(auth, targetType, permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(
      Authentication auth, Serializable targetId, String targetType, Object permission) {
        logger.info(String.format("targetType: %s === permission: %s", targetType.toString(), permission.toString()));

        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(), 
          permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
      logger.info(String.format("targetType: %s === permission: %s", targetType, permission));
      return true;
        // for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
        //     if (grantedAuth.getAuthority().startsWith(targetType) && 
        //       grantedAuth.getAuthority().contains(permission)) {
        //         return true;
        //     }
        //}
        //return false;
    }
}
