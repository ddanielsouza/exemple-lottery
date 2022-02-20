package br.com.dnsouza.exemplelottery.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
  public static String findOne(String str, String regex) {
    return RegexUtil.findOne(str, regex, 1);
  }
  
  public static String findOne(String str, String regex, int group) {

    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    final Matcher matcher = pattern.matcher(str);

    String response = "";
    
    while (matcher.find()) {
      if(matcher.groupCount() > 0) {
        response = matcher.group(group);
      }
    }
    
    return response;
  }
}
