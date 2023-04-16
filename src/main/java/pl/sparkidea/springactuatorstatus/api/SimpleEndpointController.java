package pl.sparkidea.springactuatorstatus.api;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sparkidea.springactuatorstatus.service.FlipWorkingStatusService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SimpleEndpointController {

    private final FlipWorkingStatusService service;

    public SimpleEndpointController(FlipWorkingStatusService service) {
        this.service = service;
    }

    @PatchMapping("/status")
    FlipStatusDto flipWorkingStatus() {
        Link selfLink = linkTo(methodOn(SimpleEndpointController.class).flipWorkingStatus()).withSelfRel();
        return new FlipStatusDto(service.toggleFlip()).add(selfLink);
    }

    @GetMapping("/status")
    FlipStatusDto currentStatus() {
        Link selfLink = linkTo(methodOn(SimpleEndpointController.class).currentStatus()).withSelfRel();
        return new FlipStatusDto(service.getCurrentStatus()).add(selfLink);
    }
}
