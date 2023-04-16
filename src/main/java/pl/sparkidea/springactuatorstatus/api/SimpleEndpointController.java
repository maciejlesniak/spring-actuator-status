package pl.sparkidea.springactuatorstatus.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.EntityModel;
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

    @Operation(summary = "Changes working status to the next status on the list")
    @ApiResponse(responseCode = "200", description = "A status after the change")
    @PatchMapping("/status")
    EntityModel<FlipStatusDto> flipWorkingStatus() {
        Link selfLink = linkTo(methodOn(SimpleEndpointController.class).flipWorkingStatus()).withSelfRel();
        return EntityModel.of(new FlipStatusDto(service.toggleFlip())).add(selfLink);
    }

    @Operation(summary = "Get information about current working status")
    @ApiResponse(responseCode = "200", description = "A current status")
    @GetMapping("/status")
    EntityModel<FlipStatusDto> currentStatus() {
        Link selfLink = linkTo(methodOn(SimpleEndpointController.class).currentStatus()).withSelfRel();
        return EntityModel.of(new FlipStatusDto(service.getCurrentStatus())).add(selfLink);
    }

}
