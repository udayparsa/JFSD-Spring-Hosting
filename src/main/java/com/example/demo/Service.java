package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Service {
  
  @Autowired
  DAO dao;
  
  @GetMapping("/")
  public String fun1() {
    return "This is Home Page";
  }
  
  @GetMapping("/welcome/{name}")
  public String fun2(@PathVariable("name") String name) {
    return "welcome " + name;
  }
  
  @PostMapping("/user")
  public String fun3(@RequestBody User user) {
    dao.insert(user);
    return "User Inserted";
  }
  
  @GetMapping("/user")
  public String fun4(@RequestParam("email") String email) {
    return dao.findUser(email).toString();
  }
  
  
   @GetMapping("/allusers")
   public List<User> fun5() {
	   return dao.find();
   }
   
   
   @GetMapping("/page")
   public String fun6() {
	   return dao.findpage().toString();
   }
   
   @DeleteMapping("/delete")
   public String fun7(@RequestParam("email") String email) {
	   return dao.deleteUser(email);
   }
   
   @PutMapping("/update")
   public String fun8(@RequestBody User user) {
	   System.out.println(user);
	   return dao.editUser(user);
   }
   
   
   @PostMapping("/login")
   public String fun9(@RequestBody User user) {
	   User exist=dao.findUser(user.getEmail());
	   if(exist!=null&&exist.getPassword().equals(user.getPassword())) {
		   return "Login Successfull";
	   }
	   else {
		   return "Login Unsuccessfull";
	   }
   }
  

}