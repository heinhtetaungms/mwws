package org.mw.mwws.repo;

import org.mw.mwws.entity.Permission;
import org.mw.mwws.utils.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepo extends BaseRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}
