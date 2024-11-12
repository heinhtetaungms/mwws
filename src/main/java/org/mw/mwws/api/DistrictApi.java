package org.mw.mwws.api;

import org.mw.mwws.api.input.DistrictSearch;
import org.mw.mwws.api.output.DistrictDto;
import org.mw.mwws.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictApi {

    @Autowired
    private DistrictService service;

    @GetMapping
    public List<DistrictDto> search(@RequestBody DistrictSearch form) {
        return service.search(form);
    }

}
