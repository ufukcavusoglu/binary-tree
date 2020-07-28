package com.example.binaytree.controller;

import com.example.binaytree.node.Node;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class Controller {

    public Node<Integer> node = new Node<Integer>(1);

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public Node<Integer> find(){
        return node.find.apply(1);
    };

    @RequestMapping(value = "check", method = RequestMethod.GET)
    public String read(){
        return "started";
    };
}
