package com.temat27zad1.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public UserController(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/add_user")
    public String addUser(Model model) {
        model.addAttribute("userDto", convertToUserDto(new User()));
        return "add_user";
    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute UserDto userDto) {
        if (userDto != null) {
            userRepository.save(convertToUser(userDto));
        }
        return "redirect:user_list";
    }

    @GetMapping("/user_list")
    public String userList(Model model) {
        if (userRepository.count() > 0) {
            model.addAttribute("userDtoList", userRepository.findAll().stream()
                    .map(this::convertToUserDto).toList());
        }
        return "user_list";
    }

    public UserDto convertToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
