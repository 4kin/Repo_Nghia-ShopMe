package com.shopme.admin.user;


import com.shopme.admin.FileUploadUtil;
import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {
    public static final String REDIRECT_USERS = "redirect:/users";
    public static final String USER_FORM = "user_form";
    public static final String USERS = "users";
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return USERS;
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        List<Role> listRoles = service.listRoles();

        User user = new User();
        user.setPassword("sdlkhl;;lk;lkksdjfh");
        user.setEmail("4kin@bk.ru");
        user.setLastName("last name");
        user.setFirstName("first name");

        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("pageTitle", "Create new USER");

        return USER_FORM;
    }

    @PostMapping("/users/save")
    public String saveUser(User user,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("image") MultipartFile multipartFile) throws IOException {
//        System.out.println(user);
//        System.out.println(multipartFile.getOriginalFilename());
        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileName);
            User savedUser = service.save(user);

            String uploadDir = "user-photos/"+savedUser.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }

//        service.save(user);

        redirectAttributes.addFlashAttribute("message", "Привет. Все хорошо сохранено");
        return REDIRECT_USERS;
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable(name = "id") Integer id,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        try {
            User user = service.get(id);
            List<Role> listRoles = service.listRoles();

            model.addAttribute("user", user);
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("pageTitle", "Edit USER (ID: " + id + ")");

            return USER_FORM;
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return REDIRECT_USERS;
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message", "Пользователь удален " + id);
        } catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return REDIRECT_USERS;
    }

    @GetMapping("/users/{id}/enabled/{status}")
    public String updateUserEnabledStatus(@PathVariable(name = "id") Integer id,
                                          @PathVariable(name = "status") boolean enbled,
                                          RedirectAttributes redirectAttributes) {
        service.updateUserEnableStatus(id, enbled);
        String status = enbled ? "enabled" : "disabled";
        String message = "Этот пользователь ИД = " + id + "статус = " + status;
        redirectAttributes.addFlashAttribute("message", message);

        return REDIRECT_USERS;

    }
}
