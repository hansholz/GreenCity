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
        model.addAttribute("allTypes", UserStatus.values());
        return "core/management_users_list";
    }

    /**
     * Do.
     *
     * @param userDto s.
     * @return sds.
     */
    @PostMapping("/update")
    public String updateUser(UserForListDto userDto) {
        userService.updateUser(userDto);
        return "redirect:/management/users";
    }

    /**
     * Do.
     *
     * @param id s.
     * @return sds.
     */
    @GetMapping("/findById")
    @ResponseBody
    public UserForListDto findById(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        User byId = userService.findById(id);
        return modelMapper.map(byId, UserForListDto.class);
    }
}
