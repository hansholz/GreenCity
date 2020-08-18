package greencity.webcontroller;

import greencity.dto.user.UserForListDto;
import greencity.entity.User;
import greencity.entity.enums.UserStatus;
import greencity.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/management")
public class ManagementUserController {
    private UserService userService;
    private ModelMapper modelMapper;

    /**
     * Method that returns management page with all {@link User}.
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
        model.addAttribute("allTypes", UserStatus.values());
        return "core/management_users_list";
    }

    /**
     * Method that updates user data.
     *
     * @param userDto dto with updated fields.
     * @return View template path {@link String}.
     * @author Vasyl Zhovnir
     */
    @PostMapping("/update")
    public String updateUser(UserForListDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/management/users";
    }

    /**
     * Method for finding {@link User} by id.
     *
     * @param id of the searched {@link User}.
     * @return dto {@link UserForListDto} of the {@link User}.
     * @author Vasyl Zhovnir
     */
    @GetMapping("/findById")
    @ResponseBody
    public UserForListDto findById(Long id) {
        User byId = userService.findById(id);
        return modelMapper.map(byId, UserForListDto.class);
    }
}
