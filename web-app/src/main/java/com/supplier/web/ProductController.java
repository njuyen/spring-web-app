package com.supplier.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.supplier.domain.Product;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> getAll(){
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product get(@PathVariable long id){
        return null;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public Product add(@RequestBody Product product){
        return null;
    }
    
    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public Product delete(@PathVariable long id){
        return null;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public Product update(@PathVariable long id, @RequestBody Product product){
        return null;
    }
}
