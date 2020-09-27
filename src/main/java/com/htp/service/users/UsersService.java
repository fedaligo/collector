package com.htp.service.users;

import com.htp.entity.users.Users;
import com.htp.repository.users.UsersRepository;
import java.util.List;

import com.htp.security.util.JwtUtil;
import com.htp.service.AllService;
import com.htp.service.collection.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final AllService allService;
    private final CollectionService collectionService;
    private final JwtUtil jwtUtil;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users findByUserName(String userName){
        return usersRepository.findByUsername(userName);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }

    public String getRoleFromToken(String authorizationHeader){
        return jwtUtil.extractRole(allService.getTokenFromHeaderAndUserNameFromToken(authorizationHeader)[0]);
    }

    public String saveUser(Users users){
        if(checkUserNameIsExist(users.getUsername())){
            return "User with this userName is already exist";
        }
        if(checkMailIsExist(users.getMail())){
            return "User with this eMail is already exist";
        }
        usersRepository.saveAndFlush(users);
        return "";
    }

    public String updateUser(Users users){
        if(checkUserNameIsExist(users.getUsername())){
            return "User with this userName is already exist";
        }
        if(checkMailIsExist(users.getMail())){
            return "User with this eMail is already exist";
        } else{
            usersRepository.save(users);
            return "";
        }
    }

    private boolean checkUserNameIsExist(String userName){
        Users user = usersRepository.findByUsername(userName);
        if(user == null){
            return false;
        }
        return true;
    }

    private boolean checkMailIsExist(String mail){
        Users user = usersRepository.findUsersByMail(mail);
        if(user == null){
            return false;
        }
        return true;
    }

    public boolean isUserOwnerOrAdminByItemId(Long id, String authorizationHeader){
        if(areTheSameUsersNames(id, authorizationHeader) || isOwnerAdmin(authorizationHeader)){
            return true;
        } else {
            return false;
        }
    }

    private boolean areTheSameUsersNames(Long id, String authorizationHeader){
        return collectionService.findItemById(id).getUserCollection().getUsername()
                .equals(allService.getTokenFromHeaderAndUserNameFromToken(authorizationHeader)[1]);
    }

    private boolean isOwnerAdmin(String authorizationHeader){
        return jwtUtil.extractRole(allService.getTokenFromHeaderAndUserNameFromToken(authorizationHeader)[0])
                .equals("[admin]");
    }

    public String ownerUserName(Long id){
        return collectionService.findItemById(id).getUserCollection().getUsername();
    }
}
