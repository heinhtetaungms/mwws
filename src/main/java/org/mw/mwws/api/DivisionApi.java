package org.mw.mwws.api;

import org.mw.mwws.api.input.DivisionSearch;
import org.mw.mwws.api.output.DivisionDto;
import org.mw.mwws.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("division")
public class DivisionApi {

    @Autowired
    private DivisionService service;

    @GetMapping
    public List<DivisionDto> search(@RequestBody DivisionSearch form) {
        return service.search(form);
    }

}
