package org.mw.mwws.api;

import org.mw.mwws.api.input.TownshipSearch;
import org.mw.mwws.api.output.TownshipDto;
import org.mw.mwws.service.TownshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("township")
public class TownshipApi {

    @Autowired
    private TownshipService service;

    @GetMapping
    public List<TownshipDto> search(@RequestBody TownshipSearch form) {
        return service.search(form);
    }
}
