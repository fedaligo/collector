package com.htp.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AllService {
    private static final int CONTINUE = 0;
    private static final int NEXT = 1;

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
        return cleanedInfo;
    }
}
