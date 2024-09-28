package pets.cats.data.petscatsaccessapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;
import pets.cats.data.petscatsaccessservices.cat.CatService;

@RestController
@RequestMapping(path = "api/v1/cats")
public class CatController {

    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('cats:write')")
    public CatDTO createCat(@RequestBody CatDTO catDTO) {
        return catService.createCat(catDTO);
    }
}