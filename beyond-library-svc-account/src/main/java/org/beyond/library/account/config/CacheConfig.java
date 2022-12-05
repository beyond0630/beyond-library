package org.beyond.library.account.config;

import com.beyond.cache.Cache;
import com.beyond.cache.CacheManager;
import org.beyond.library.account.model.entity.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * @author Beyond
 */
@Configuration
public class CacheConfig {

    @Bean
    public Cache<Long, UserRoleWrapper> userRoleCache(CacheManager cacheManager) {
        return cacheManager.createCache(Long.class, UserRoleWrapper.class);
    }

    @Bean
    public Cache<String, RolePermissionWrapper> rolePermissionCache(CacheManager cacheManager) {
        return cacheManager.createCache(String.class, RolePermissionWrapper.class);
    }

    @Bean
    public Cache<String, Permission> permissionCache(CacheManager cacheManager) {
        return cacheManager.createCache(String.class, Permission.class);
    }

    public static final class UserRoleWrapper {

        private Set<String> roleCodes;

        public UserRoleWrapper() {
        }

        public UserRoleWrapper(final Set<String> roleCodes) {
            this.roleCodes = roleCodes;
        }

        public Set<String> getRoleCodes() {
            return roleCodes;
        }

        public void setRoleCodes(final Set<String> roleCodes) {
            this.roleCodes = roleCodes;
        }

    }

    public static final class RolePermissionWrapper {

        private Set<String> permissions;

        public RolePermissionWrapper(final Set<String> permissions) {
            this.permissions = permissions;
        }

        public Set<String> getPermissions() {
            return permissions;
        }

        public void setPermissions(final Set<String> permissions) {
            this.permissions = permissions;
        }

    }

}
