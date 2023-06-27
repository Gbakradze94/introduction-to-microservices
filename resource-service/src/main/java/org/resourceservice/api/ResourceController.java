package org.resourceservice.api;

import lombok.RequiredArgsConstructor;
import org.resourceservice.domain.Resource;
import org.resourceservice.service.ResourceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResourceController {

    private ResourceService resourceService;

    @GetMapping("/resources/{id}")
    public Resource getResource(@PathVariable Integer id) {
        return resourceService.getResource(id);
    }
}
