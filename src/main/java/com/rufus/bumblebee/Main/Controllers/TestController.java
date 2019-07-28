package com.rufus.bumblebee.Main.Controllers;

import com.rufus.bumblebee.Main.Container.TestDataContainer;
import com.rufus.bumblebee.Main.Controllers.Requests.*;
import com.rufus.bumblebee.Main.Services.BaseTestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/basetests")
public class TestController {

    private BaseTestSuiteService service;

    @Autowired
    public TestController(BaseTestSuiteService service) {
        this.service = service;
    }


    @RequestMapping(path = "/setcontainer", method = RequestMethod.GET)
    public @ResponseBody
    TestDataContainer setTestDataContainer(@RequestBody TestDataContainerRequest request) throws Exception {
        if (StringUtils.isEmpty(request.getName())) {
            throw new Exception("Name is null or empty");
        }
        service.createTestDataContainer(request.getName());
        return service.getTestDataContainer();
    }

    @RequestMapping(path = "/boundary", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity createBoundaryTest(@RequestBody BoundaryTestRequest request) {
        if (!service.selectionBoundaryTest(request.getLen(), request.getIncreaseQuantity(), request.getLow(), request.getCap(), request.getNullValue())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/specsymbol", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity createSpecialTest(@RequestBody SpecialLinesTestRequest request) {
        if (!service.selectionSpecialLinesTest(
                request.getSpecialLen(), request.getIncreaseQuantity(), request.getEscSpecial(), request.getSpecial()
        )) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @RequestMapping(path = "/intboundary", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity createIntBoundaryTest(@RequestBody IntBoundaryTestRequest request) {
        if (service.selectionIntBoundary(request.getBoundaryIntEnd(), request.getBoundaryIntStart(), request.getQuantity())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @RequestMapping(path = "/intrange", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity createIntRangeTest(@RequestBody IntRangeTestRequest request) {
        if (service.selectionIntRange(request.getMaxIntVal(), request.getMinIntVal())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }


    @RequestMapping(path = "/savecolumn", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity saveColumn() {
        if (service.startGeneratingData()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().build();
        }

    }

    @RequestMapping(path = "/savemodel", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity saveModel() {
        if (service.saveTests()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

}

