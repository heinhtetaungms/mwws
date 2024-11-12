package org.mw.mwws.service;

import org.mw.mwws.api.input.TownshipSearch;
import org.mw.mwws.api.output.TownshipDto;
import org.mw.mwws.entity.Township;
import org.mw.mwws.entity.Township_;
import org.mw.mwws.repo.TownshipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownshipService {

    @Autowired
    private TownshipRepo repo;

    public List<TownshipDto> search(TownshipSearch form) {
        return repo.search(cb -> {
            var cq = cb.createQuery(TownshipDto.class);

            var root = cq.from(Township.class);
            TownshipDto.select(cq, root);
            cq.where(form.where(cb, root));

            cq.orderBy(cb.asc(root.get(Township_.id)));

            return cq;
        });
    }

}
