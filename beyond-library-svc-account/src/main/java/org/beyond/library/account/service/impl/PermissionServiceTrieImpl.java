package org.beyond.library.account.service.impl;

import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.beyond.library.account.model.entity.Permission;
import org.beyond.library.account.model.param.AddOrUpdatePermission;
import org.beyond.library.account.repository.PermissionRepository;
import org.beyond.library.account.repository.RolePermissionRepository;
import org.beyond.library.account.service.PermissionService;
import org.beyond.library.commons.trie.PathTrie;
import org.beyond.library.framework.exception.ApiException;
import org.beyond.library.framework.exception.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Beyond
 */
@Service
public class PermissionServiceTrieImpl implements PermissionService {

    private PathTrie trie;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public PermissionServiceTrieImpl(final PermissionRepository permissionRepository,
                                     final RolePermissionRepository rolePermissionRepository) {
        this.permissionRepository = permissionRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @PostConstruct
    public void loadCache() {
        List<String> patterns = this.permissionRepository.listAllPattern();
        this.trie = new PathTrie(patterns);
    }

    @Override
    public Permission getPermission(String method, String url) {
        String pattern = trie.parsePattern(url);
        if (StringUtils.isEmpty(pattern)) {
            return null;
        }
        return this.permissionRepository.getByMethodAndPattern(method, pattern);
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
        this.trie.addPath(permission.getPattern());
    }

    @Override
    public void editPermission(final int id, final AddOrUpdatePermission params) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(DataNotFoundException::new);

        boolean updatePattern = !permission.getPattern().equals(params.getPattern());
        String oldPattern = permission.getPattern();

        permission.setName(params.getName());
        permission.setMethod(params.getMethod());
        permission.setPattern(params.getPattern());
        permissionRepository.save(permission);
        if (updatePattern) {
            this.trie.removePath(oldPattern);
            this.trie.addPath(permission.getPattern());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermission(final int id) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(DataNotFoundException::new);
        rolePermissionRepository.deleteByPermissionCode(permission.getCode());
        permissionRepository.deleteById(id);
        this.trie.removePath(permission.getPattern());
    }

}
