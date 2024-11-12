package org.mw.mwws.api.output;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.mw.mwws.entity.District;
import org.mw.mwws.entity.District_;
import org.mw.mwws.entity.Division_;

public record DistrictDto(
        int id,
        String name,
        int divisionId,
        String division) {

    public static void select(CriteriaQuery<DistrictDto> cq, Root<District> root) {
        cq.multiselect(
                root.get(District_.id),
                root.get(District_.name),
                root.get(District_.division).get(Division_.id),
                root.get(District_.division).get(Division_.name)
        );
    }
}
