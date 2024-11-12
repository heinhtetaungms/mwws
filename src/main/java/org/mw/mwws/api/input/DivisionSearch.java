package org.mw.mwws.api.input;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mw.mwws.entity.Division;
import org.mw.mwws.entity.Division_;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public record DivisionSearch(String name) {

    public Predicate[] where(CriteriaBuilder cb, Root<Division> root) {
        var list = new ArrayList<Predicate>();

        if (StringUtils.hasLength(name)) {
            // lower(root.name) like ?
            list.add(cb.like(cb.lower(root.get(Division_.name)),
                    name.toLowerCase().concat("%")));
        }

        return list.toArray(Predicate[]::new);
    }
}
