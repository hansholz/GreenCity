package greencity.webcontroller;

import greencity.entity.User;
import greencity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Numbers;

@Controller
@AllArgsConstructor
@RequestMapping("/management")
public class ManagementUserController {
    private UserService userService;

    /**
     * Returns management page with all {@link User}.
     *
     * @param model Model that will be configured and returned to user.
     * @param page  Page index you want to retrieve.
     * @param size  Number of records per page.
     * @return View template path {@link String}.
     * @author Vasyl Zhovnir
     */
    @GetMapping("/users")
    public String getAllUsers(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("id").descending());
        model.addAttribute("users", userService.findByPage(paging));
        model.addAttribute("currentPage", page);
        return "core/management_users_list";
    }

    /**
     * Do.
     *
     * @param user s.
     * @return sds.
     */
    @PostMapping("/save")
    public String saveUser(User user) {
        userService.save(user);
        return "redirect:management/users";
    }

    /**
     * Do.
     *
     * @param id s.
     * @return sds.
     */
    @GetMapping("/findById")
    @ResponseBody
    public User findById(Long id) {
        return userService.findById(id);
    }
}
