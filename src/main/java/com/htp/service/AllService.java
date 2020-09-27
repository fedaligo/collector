package com.htp.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.htp.security.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AllService {
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;
    private final JwtUtil jwtUtil;

    public int checkIdIsNotRepeated(List<Long> saveIdForMappingByCollection, long idValue) {
        for(int p = 0; p < saveIdForMappingByCollection.size(); ++p) {
            if (saveIdForMappingByCollection.get(p) == idValue) {
                return NEXT;
            }
        }
        return CONTINUE;
    }

    public String getOnlyInfoFromString(String info) {
        Pattern pattern = Pattern.compile("(?<==).+?(?=,|$)");
        Matcher matcher = pattern.matcher(info);
        String cleanedInfo = "";
        while(matcher.find()) {
            if (!matcher.group().contains("http")) {
                cleanedInfo = cleanedInfo + matcher.group();
            }
        }
        return cleanedInfo.toLowerCase();
    }

    public String[] getTokenFromHeaderAndUserNameFromToken(String authorizationHeader){
        String[] info = new String[2];
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            info[0] = authorizationHeader.substring(15, authorizationHeader.length() - 2);
            info[1] = jwtUtil.extractUserName(info[0]);
        }
        return info;
    }
}
