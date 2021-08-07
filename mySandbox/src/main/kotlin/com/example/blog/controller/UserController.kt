package com.example.blog.controller

import com.example.blog.entity.User
import com.example.blog.repository.UserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

private val logger = KotlinLogging.logger {};
@Controller
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/")
    fun getAllUsers(model: Model): String? {
        logger.info { "Fetching all User." }
        val list: List<User> = userRepository.findAll()
        model.addAttribute("users", list)
        return "list-user"
    }

    @PostMapping(path = ["/createUser"])
    fun createNewUser(@ModelAttribute user: User): String {
        logger.info { "Creating User $user" }
        userRepository.save(user)
        return "redirect:/";
    }

    @GetMapping(path = ["/add"])
    fun addUserById(): String? {
        return "add-user"
    }

    @GetMapping(path = ["/edit/{id}"])
    fun editUserById(model: Model, @PathVariable("id") id: Long): String? {
        model.addAttribute("user", userRepository.findById(id))
        return "edit-user"
    }

    @PostMapping(path = ["/editUser"])
    fun editUser(@ModelAttribute user: User): String? {
        userRepository.save(user)
        return "redirect:/"
    }

    @GetMapping(path = ["/delete/{id}"])
    fun deleteUserById(@PathVariable("id") id: Long): String? {
        userRepository.deleteById(id)
        return "redirect:/"
    }

}
