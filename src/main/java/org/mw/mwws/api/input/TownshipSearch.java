package org.mw.mwws.api.input;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.mw.mwws.entity.District_;
import org.mw.mwws.entity.Division_;
import org.mw.mwws.entity.Township;
import org.mw.mwws.entity.Township_;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public record TownshipSearch(String name,
                             Integer districtId, String district,
                             Integer divisionId, String division) {

    public Predicate[] where(CriteriaBuilder cb, Root<Township> root) {
        var list = new ArrayList<Predicate>();

        if (StringUtils.hasLength(name)) {
            // lower(root.name) like ?
            list.add(cb.like(cb.lower(root.get(Township_.name)),
                    name.toLowerCase().concat("%")));
        }

        if (null != districtId) {
            list.add(cb.equal(root.get(Township_.district).get(District_.id), districtId));
        }

        if (StringUtils.hasLength(district)) {
            list.add(cb.like(cb.lower(root.get(Township_.district).get(District_.name)),
                    district.toLowerCase().concat("%")));
        }

        if (null != divisionId) {
            list.add(cb.equal(root.get(Township_.district).get(District_.division).get(Division_.id), divisionId));
        }

        if (StringUtils.hasLength(division)) {
            list.add(cb.like(cb.lower(root.get(Township_.district).get(District_.division).get(Division_.name)),
                    division.toLowerCase().concat("%")));
        }

        return list.toArray(Predicate[]::new);
    }
}
