package org.beyond.library.account.service.impl;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.PostConstruct;

import org.beyond.library.account.model.entity.Permission;
import org.beyond.library.account.model.param.AddOrUpdatePermission;
import org.beyond.library.account.repository.PermissionRepository;
import org.beyond.library.account.repository.RolePermissionRepository;
import org.beyond.library.account.service.PermissionService;
import org.beyond.library.framework.exception.ApiException;
import org.beyond.library.framework.exception.DataNotFoundException;
import org.ehcache.Cache;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

/**
 * @author Beyond
 */
//@Service
public class PermissionServiceCacheImpl implements PermissionService {

    private final Cache<String, Permission> cache;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public PermissionServiceCacheImpl(final Cache<String, Permission> cache,
                                      final PermissionRepository permissionRepository,
                                      final RolePermissionRepository rolePermissionRepository) {
        this.cache = cache;
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @PostConstruct
    public void loadCache() {
        permissionRepository.findAll()
            .forEach(x -> cache.put(this.getCacheKey(x), x));
    }

    @Override
    public Permission getPermission(String method, String url) {
        String cacheKey = this.getCacheKey(method, url);
        if (cache.containsKey(cacheKey)) {
            return cache.get(cacheKey);
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        final AtomicReference<Permission> result = new AtomicReference<>();
        cache.forEach(x -> {
            if (antPathMatcher.match(x.getKey(), cacheKey)) {
                result.set(x.getValue());
            }
        });
        return result.get();
    }

    @Override
    public void savePermission(final AddOrUpdatePermission params) {
        boolean exists = this.permissionRepository.existsByCode(params.getCode());
        if (exists) {
            throw new ApiException("已存在code = " + params.getCode() + "的数据");
        }
        Permission permission = new Permission();
        permission.setCode(params.getCode());
        permission.setName(params.getName());
        permission.setMethod(params.getMethod());
        permission.setPattern(params.getPattern());
        permission.setAllowAnonymous(params.isAllowAnonymous());
        permission.setDisabled(false);
        permissionRepository.save(permission);
        cache.put(this.getCacheKey(permission), permission);
    }

    @Override
    public void editPermission(final int id, final AddOrUpdatePermission params) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(DataNotFoundException::new);
        String cacheKey = this.getCacheKey(permission);
        permission.setName(params.getName());
        permission.setMethod(params.getMethod());
        permission.setPattern(params.getPattern());
        permissionRepository.save(permission);
        cache.put(cacheKey, permission);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermission(final int id) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(DataNotFoundException::new);
        rolePermissionRepository.deleteByPermissionCode(permission.getCode());
        permissionRepository.deleteById(id);
        cache.remove(this.getCacheKey(permission));
    }

    private String getCacheKey(final Permission permission) {
        return this.getCacheKey(permission.getMethod(), permission.getPattern());
    }

    private String getCacheKey(final String method, final String pattern) {
        Assert.hasText(method, "Method can not be blank");
        Assert.hasText(pattern, "Pattern can not be blank");
        return String.format("%s-%s", method.toUpperCase(Locale.ROOT),
            pattern.toUpperCase(Locale.ROOT));
    }

}
