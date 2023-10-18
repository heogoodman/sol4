package com.basic.myspringboot.service;

import com.basic.myspringboot.dto.UserReqDTO;
import com.basic.myspringboot.dto.UserReqForm;
import com.basic.myspringboot.dto.UserResDTO;
import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//static import
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor //final로 선언된 변수에 대한 생성자를 생성해줌
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    //Constructor Injection @Autowired를 안만들고 사용하는 법
//    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
//        this.userRepository = userRepository;
//        this.modelMapper = modelMapper;
//    }

    public UserResDTO saveUser(UserReqDTO userReqDto) {
        //reqDto => entity 매핑         (요청변수, 엔티티.클래스)
        User user = modelMapper.map(userReqDto, User.class);
        //DB에 저장
        User savedUser = userRepository.save(user); // save는 원래있는 메소드임
        //entity => resDto 매핑      (엔티티객체, 응답클래스)
        return modelMapper.map(savedUser, UserResDTO.class);
    }

    // 조회 메서드인 경우에 readOnly=true 를 설정하면 성능향상에 도움이 된다.
    @Transactional(readOnly = true)  // 자카르타꺼 로쓰면 리드온리못씀.
    public UserResDTO getUserById(Long id){
        User userEntity = userRepository.findById(id) //Optional<User>
                .orElseThrow(() -> new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        //Entity -> ResDTO 로 바꿔줘야함
        UserResDTO userResDto = modelMapper.map(userEntity,UserResDTO.class);
        return userResDto;
    }

    @Transactional(readOnly = true)
    public List<UserResDTO> getUsers() {
        List<User> userList = userRepository.findAll(); //List<User>
        //List<User> => List<UserResDTO> 로 바꾸기 스트림을쓰면 간단히해결가능
        List<UserResDTO> userResDTOList = userList.stream() //Stream<User>
                //map(Function) Function의 추상메서드 R apply(T t)
                .map(user -> modelMapper.map(user, UserResDTO.class))
                // Stream<UserResDTO>
                .collect(toList());//List<UserResDTO> 가된다.
        return userResDTOList;
    }

    public UserResDTO updateUser(String email, UserReqDTO userReqDto) {
        User existUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
        //Dirty Checking 변경감지를 하므로 setter method만 호출해도 update query가 실행이 된다.
        existUser.setName(userReqDto.getName());
        return modelMapper.map(existUser, UserResDTO.class);
    }

    public void updateUserForm(UserReqForm userReqForm) {
        User existUser = userRepository.findById(userReqForm.getId())
                .orElseThrow(() ->
                        new BusinessException(userReqForm.getId() + " User Not Found", HttpStatus.NOT_FOUND));
            existUser.setName(userReqForm.getName());
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id) //Optional<User>
                .orElseThrow(() ->
                        new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }

}