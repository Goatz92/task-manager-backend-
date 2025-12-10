package com.goatz.task_manager_pro.controller;

import com.goatz.task_manager_pro.core.exceptions.EntityAlreadyExistsException;
import com.goatz.task_manager_pro.dto.UserInsertDTO;
import com.goatz.task_manager_pro.repository.RoleRepository;
import com.goatz.task_manager_pro.service.UserService;
import com.goatz.task_manager_pro.validator.UserInsertValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("task")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserInsertValidator userInsertValidator;
    private final RoleRepository roleRepository;

        /**
         * Displays a list of all users.
         * Endpoint: GET /task/users
         * @param model Spring MVC model for view attributes
         * @return Thymeleaf template name for the user list
         */
        @GetMapping("/users")
        public String getAllUsers(Model model) {
            model.addAttribute("users", userService.getAllUsers());
            return "user-list";
        }

    /**
     * Displays the user registration form.
     * Loads available roles and an empty UserInsertDTO into the model.
     * Endpoint: GET /task/users/register
     * @param model Spring MVC model for view attributes
     * @return Thymeleaf template name for the registration form
     */
    @GetMapping("/users/register")
    public String getUserForm(Model model) {
        model.addAttribute("userInsertDTO", new UserInsertDTO());
        model.addAttribute("roles", roleRepository.findAll(Sort.by("name")));
        return "user-form2";
    }


    /**
     * Handles user registration form submission.
     * Validates input (annotations + custom validator), saves user if valid, or returns errors.
     * Endpoint: POST /task/users/register
     * @param userInsertDTO DTO containing user registration data
     * @param bindingResult Spring validation result
     * @param model Spring MVC model for view attributes
     * @return Redirect to home on success, or re-render form with errors
     */
    @PostMapping("/users/register")
    public String insertUser(@Valid @ModelAttribute("userInsertDTO") UserInsertDTO userInsertDTO,
                             BindingResult bindingResult, Model model) {
        userInsertValidator.validate(userInsertDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll(Sort.by("name")));
            return "user-form";
        }
        try {
            userService.saveUser(userInsertDTO);
            return "redirect:/";
        } catch (EntityAlreadyExistsException e) {
            model.addAttribute("role", roleRepository.findAll(Sort.by("name")));
            model.addAttribute("errorMessage", e.getMessage());
            return "user-form";
        }
    }
}
