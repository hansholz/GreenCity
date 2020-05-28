package greencity.controller;

import greencity.service.TagService;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagsController {
    private final TagService tagService;

    /**
     * The method which returns all tags.
     *
     * @return list of {@link String} (tag's names).
     * @author Kovaliv Taras
     */
    @GetMapping
    public ResponseEntity<List<String>> findAllTags(
        @ApiParam(value = "Requested language code")
        @RequestParam(defaultValue = "en") String language) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.findAll(language));
    }
}
