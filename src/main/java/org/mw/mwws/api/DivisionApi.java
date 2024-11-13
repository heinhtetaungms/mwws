package org.mw.mwws.api;

import org.mw.mwws.api.input.DivisionSearch;
import org.mw.mwws.api.output.DivisionDto;
import org.mw.mwws.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
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

    /*@GetMapping
    public List<DivisionDto> search(@RequestBody DivisionSearch form, BindingResult result) {
        return service.search(form);
    }*/

    @GetMapping("/admin")
    @PreAuthorize("hasRole('Admin')")
    public String search(@RequestBody DivisionSearch form, BindingResult result) {
        return "Admin";
    }

    @GetMapping("/driver")
    @PreAuthorize("hasPermission(null, 'ASSIGN_TAXI')")
    public String search2(@RequestBody DivisionSearch form, BindingResult result) {
        return "Driver";
    }

}
