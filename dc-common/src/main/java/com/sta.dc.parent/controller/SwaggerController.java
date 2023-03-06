//package com.sta.dc.parent.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////@Api(value = "Swagger2DemoRestController", tags = "REST APIs related to Testing Swagger!!!!")
//@RestController
//@RequestMapping("swagger/test")
//public class SwaggerController {
//
//
//    @GetMapping("/get")
//    @ApiOperation(value = "Get String from the get api", response = String.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success|OK"),
//            @ApiResponse(code = 401, message = "not authorized!"),
//            @ApiResponse(code = 403, message = "forbidden!!!"),
//            @ApiResponse(code = 404, message = "not found!!!") })
//    public String getData(){
//        return "Get is working.";
//    }
//    @PostMapping("/post")
//    @ApiOperation(value = "Get String from the post api", response = String.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success|OK"),
//            @ApiResponse(code = 401, message = "not authorized!"),
//            @ApiResponse(code = 403, message = "forbidden!!!"),
//            @ApiResponse(code = 404, message = "not found!!!") })
//    public String postData(){
//        return "Post is working.";
//    }
//}
