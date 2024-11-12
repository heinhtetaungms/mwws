package org.mw.mwws.service;

import org.mw.mwws.api.input.DivisionSearch;
import org.mw.mwws.api.output.DivisionDto;
import org.mw.mwws.entity.Division;
import org.mw.mwws.entity.Division_;
import org.mw.mwws.repo.DivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionService {

    @Autowired
    private DivisionRepo repo;

    public List<DivisionDto> search(DivisionSearch form) {
        // select new org.mw.mwws.api.output.DivisionDto(d.id, d.name)
        // from Division d
        // where lower(d.name) like lower(:name)

        return repo.search(cb -> {
            var cq = cb.createQuery(DivisionDto.class);

            var root = cq.from(Division.class);
            DivisionDto.select(cq, root);
            cq.where(form.where(cb, root));

            cq.orderBy(cb.asc(root.get(Division_.id)));

            return cq;
        });
    }

}
