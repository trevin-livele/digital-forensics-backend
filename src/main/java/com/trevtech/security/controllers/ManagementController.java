package com.trevtech.security.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {

    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for the management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorised / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping
    public String get(){
        return "Get:: Management controller";
    }
    @PostMapping
    public String post(){
        return "POST:: Management controller";
    }

    @PutMapping
    public String put(){
        return "PUT:: Management controller";
    }
    @DeleteMapping
    public String delete(){
        return "DELETE:: Management controller";
    }
}
