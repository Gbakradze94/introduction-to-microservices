package org.resourceservice.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.resourceservice.domain.Resource;
import org.resourceservice.domain.ResourceRecord;
import org.resourceservice.service.ResourceService;
import org.resourceservice.validation.Mp3FileType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resources")
@RequiredArgsConstructor
@Slf4j
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping(consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceRecord saveResource(@RequestParam("multipartFile") @Mp3FileType MultipartFile multipartFile) throws TikaException, IOException, SAXException {
        return resourceService.saveResource(multipartFile);
    }


    @GetMapping("/{id}")
    public Resource getResource(@PathVariable Long id) {
        return resourceService.getResource(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResourceRecord> delete(@RequestParam("id") int[] ids) {
        return resourceService.deleteByIds(ids);
    }
}
