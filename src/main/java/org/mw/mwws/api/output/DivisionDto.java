package org.mw.mwws.api.output;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.mw.mwws.entity.Division;
import org.mw.mwws.entity.Division_;

public record DivisionDto(int id, String name) {

    public static void select(CriteriaQuery<DivisionDto> cq, Root<Division> root) {
        cq.multiselect(
                root.get(Division_.id),
                root.get(Division_.name)
        );
    }
}
