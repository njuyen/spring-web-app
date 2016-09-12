package com.blogger.web;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.domain.Post;

@RestController
@RequestMapping("/products")
public class PostController {
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Post> getAll(){
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post get(@PathVariable long id){
        return null;
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public Post add(@RequestBody Post post){
        return null;
    }
    
    @RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
    public Post delete(@PathVariable long id){
        return null;
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public Post update(@PathVariable long id, @RequestBody Post post){
        return null;
    }
}
