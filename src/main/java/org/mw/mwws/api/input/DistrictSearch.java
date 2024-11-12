package org.mw.mwws.api.input;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mw.mwws.entity.District;
import org.mw.mwws.entity.District_;
import org.mw.mwws.entity.Division_;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public record DistrictSearch(String name, Integer divisionId, String division) {

    public Predicate[] where(CriteriaBuilder cb, Root<District> root) {
        var list = new ArrayList<Predicate>();

        if (StringUtils.hasLength(name)) {
            // lower(root.name) like ?
            list.add(cb.like(cb.lower(root.get(District_.name)),
                    name.toLowerCase().concat("%")));
        }

        if (null != divisionId) {
            list.add(cb.equal(root.get(District_.division).get(Division_.id), divisionId));
        }

        if (StringUtils.hasLength(division)) {
            list.add(cb.like(cb.lower(root.get(District_.division).get(Division_.name)),
                    division.toLowerCase().concat("%")));
        }

        return list.toArray(Predicate[]::new);
    }
}
