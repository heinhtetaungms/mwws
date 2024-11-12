package org.mw.mwws.api.output;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.mw.mwws.entity.District_;
import org.mw.mwws.entity.Division_;
import org.mw.mwws.entity.Township;
import org.mw.mwws.entity.Township_;

public record TownshipDto(
        int id,
        String name,
        int districtId,
        String district,
        int divisionId,
        String division) {

    public static void select(CriteriaQuery<TownshipDto> cq, Root<Township> root) {
        cq.multiselect(
                root.get(Township_.id),
                root.get(Township_.name),
                root.get(Township_.district).get(District_.id),
                root.get(Township_.district).get(District_.name),
                root.get(Township_.district).get(District_.division).get(Division_.id),
                root.get(Township_.district).get(District_.division).get(Division_.name)
        );
    }
}
