package org.beyond.library.account.service;


import org.beyond.library.account.model.entity.Permission;
import org.beyond.library.account.model.param.AddOrUpdatePermission;

/**
 * @author Beyond
 */
public interface PermissionService {

    /**
     * 获取权限
     *
     * @param method 请求方法
     * @param url    请求url
     * @return permission
     */
    Permission getPermission(String method, String url);

    /**
     * 保存权限
     *
     * @param params params
     */
    void savePermission(AddOrUpdatePermission params);

    /**
     * 编辑权限
     *
     * @param id     权限 id
     * @param params params
     */
    void editPermission(int id, AddOrUpdatePermission params);

    /**
     * 删除权限
     *
     * @param id id
     */
    void deletePermission(int id);

}
