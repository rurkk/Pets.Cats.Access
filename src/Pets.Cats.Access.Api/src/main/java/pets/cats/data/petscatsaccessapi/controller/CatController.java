package pets.cats.data.petscatsaccessapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pets.cats.data.petscatsaccessservices.cat.CatResult;
import pets.cats.data.petscatsaccessservices.dto.CatDTO;
import pets.cats.data.petscatsaccessservices.cat.CatService;

import java.util.concurrent.ExecutionException;

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
    public ResponseEntity<CatResult> createCat(@RequestBody CatDTO catDTO) throws ExecutionException, InterruptedException {
        return catService.createCat(catDTO);
    }
}