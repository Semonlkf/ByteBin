package com.zyq.HitBytebin.controller;

import com.zyq.HitBytebin.serivce.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

}
