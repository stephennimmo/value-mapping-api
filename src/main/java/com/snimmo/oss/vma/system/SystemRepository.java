package com.snimmo.oss.vma.system;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SystemRepository implements PanacheRepositoryBase<SystemEntity, Integer> {
}
