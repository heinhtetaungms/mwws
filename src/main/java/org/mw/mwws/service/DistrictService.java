package org.mw.mwws.service;

import org.mw.mwws.api.input.DistrictSearch;
import org.mw.mwws.api.output.DistrictDto;
import org.mw.mwws.entity.District;
import org.mw.mwws.entity.District_;
import org.mw.mwws.repo.DistrictRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepo repo;

    public List<DistrictDto> search(DistrictSearch form) {
        return repo.search(cb -> {
            var cq = cb.createQuery(DistrictDto.class);

            var root = cq.from(District.class);
            DistrictDto.select(cq, root);
            cq.where(form.where(cb, root));

            cq.orderBy(cb.asc(root.get(District_.id)));

            return cq;
        });
    }

}
