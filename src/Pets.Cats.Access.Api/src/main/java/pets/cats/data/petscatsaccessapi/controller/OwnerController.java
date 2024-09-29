package pets.cats.data.petscatsaccessapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pets.cats.data.petscatsaccessservices.dto.OwnerDTO;
import pets.cats.data.petscatsaccessservices.owner.OwnerService;

@RestController
@RequestMapping(path = "api/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('owners:write')")
    public OwnerDTO createOwner(@RequestBody OwnerDTO owner) {
        return ownerService.createOwner(owner);
    }
}